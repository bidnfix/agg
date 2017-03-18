package com.agg.application.service.impl;

import java.io.ByteArrayOutputStream;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import javax.activation.DataSource;
import javax.mail.util.ByteArrayDataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.agg.application.dao.AdminAdjustmentDAO;
import com.agg.application.dao.ClaimsDAO;
import com.agg.application.dao.ContractsDAO;
import com.agg.application.dao.CustomerInfoDAO;
import com.agg.application.dao.DealerDAO;
import com.agg.application.dao.MachineInfoDAO;
import com.agg.application.dao.ManufacturerDAO;
import com.agg.application.dao.PricingDAO;
import com.agg.application.dao.QuoteDAO;
import com.agg.application.dao.UseOfEquipmentDAO;
import com.agg.application.entity.AdminAdjustment;
import com.agg.application.entity.Contracts;
import com.agg.application.entity.CustomerInfo;
import com.agg.application.entity.Dealer;
import com.agg.application.entity.MachineInfo;
import com.agg.application.entity.Manufacturer;
import com.agg.application.entity.Quote;
import com.agg.application.entity.QuotePK;
import com.agg.application.entity.UseOfEquip;
import com.agg.application.model.AccountDO;
import com.agg.application.model.ContractReportDO;
import com.agg.application.model.DealerDO;
import com.agg.application.model.MachineInfoDO;
import com.agg.application.model.ManufacturerDO;
import com.agg.application.model.PricingDO;
import com.agg.application.model.QuoteDO;
import com.agg.application.model.ReportDO;
import com.agg.application.model.UseOfEquipmentDO;
import com.agg.application.model.WorklistDO;
import com.agg.application.service.QuoteService;
import com.agg.application.utils.AggConstants;
import com.agg.application.utils.EmailSender;
import com.agg.application.utils.EmailStatus;
import com.agg.application.utils.Util;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
public class QuoteServiceImpl implements QuoteService {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private UseOfEquipmentDAO useOfEquipmentDAO;
	
	@Autowired
	private MachineInfoDAO machineInfoDAO;
	
	@Autowired
	private PricingDAO pricingDAO;
	
	@Autowired
	private QuoteDAO quoteDAO;
	
	@Autowired
	private DealerDAO dealerDAO;
	
	@Autowired
	private ManufacturerDAO manufacturerDAO;
	
	@Autowired
	private CustomerInfoDAO customerInfoDAO;
	
	@Autowired
	private AdminAdjustmentDAO adminAdjustmentDAO;
	
	@Autowired
	private ClaimsDAO claimsDAO;
	
	@Value("${admin.email}")
	private String adminEmail;
	
	@Autowired
	EmailSender emailSender;
	
	@Autowired
    private ResourceLoader resourceLoader;
	
	@Autowired
	private ContractsDAO contractsDAO;
	
	@Value("${file.banner.image.path}")
	private String reportImagePath;
	
	@Value("${jrxml.folder.path}")
	private String jrxmlFolderPath;
	
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");

	@Override
	public List<UseOfEquipmentDO> getUseOfEquipmentDetails() {
		List<UseOfEquip> useOfEquipList = Util.toList(useOfEquipmentDAO.findAll());
		UseOfEquipmentDO useOfEquipmentDO = null;
		List<UseOfEquipmentDO> useOfEquipmentDOList = new ArrayList<UseOfEquipmentDO>();
		for(UseOfEquip useOfEquip : useOfEquipList){
			useOfEquipmentDO = new UseOfEquipmentDO();
			useOfEquipmentDO.setId(useOfEquip.getId());
			useOfEquipmentDO.setEquipName(useOfEquip.getEquipName());
			
			useOfEquipmentDOList.add(useOfEquipmentDO);
		}
		
		logger.info("useOfEquipmentDOList size: "+useOfEquipmentDOList.size());
		
		return useOfEquipmentDOList;
	}

	@Override
	public Map<String, List<Integer>> getDeductableCoverageTermDetails(boolean coverageExpired, long machineId) {
		MachineInfo machineInfo = machineInfoDAO.findOne(machineId);
		Map<String, List<Integer>> deductableCoverageTermMap = null;
		if(machineInfo != null){
			deductableCoverageTermMap = new HashMap<String, List<Integer>>();
			int groupId = new Long(machineInfo.getGroupConstant().getGroupId()).intValue();
			logger.info("groupId: "+groupId);
			//if manufacturers coverage date expires then consider as used(2) otherwise new(1);
			byte condition = coverageExpired ? (byte)2 : (byte)1;
			List<Integer> deductibleAmtList = pricingDAO.findDeductibleAmount(condition, groupId);
			List<Integer> coverageTermList = pricingDAO.findCoverageTerm(condition, groupId, deductibleAmtList);
			
			logger.info("deductibleAmtList size: "+deductibleAmtList.size()+" coverageTermList size: "+coverageTermList.size());
			
			deductableCoverageTermMap.put("deductibleAmtList", deductibleAmtList);
			deductableCoverageTermMap.put("coverageTermList", coverageTermList);
		}
		
		return deductableCoverageTermMap;
	}

	@Override
	public List<PricingDO> getCoveragePriceDetils(boolean coverageExpired, long machineId, int deductibleAmt, int coverageTerm, int coverageHours) {
		logger.info("Inside getCoveragePriceDetils with coverageExpired: "+coverageExpired+" machineId: "+machineId
				+" deductibleAmt: "+deductibleAmt+" coverageTerm: "+coverageTerm+" coverageHours: "+coverageHours);
		MachineInfo machineInfo = machineInfoDAO.findOne(machineId);
		List<PricingDO> pricingDOList = null;
		if(machineInfo != null){
			int groupId = new Long(machineInfo.getGroupConstant().getGroupId()).intValue();
			logger.info("groupId: "+groupId);
			//if manufacturers coverage date expires then consider as used(2) otherwise new(1);
			byte condition = coverageExpired ? (byte)2 : (byte)1;
			
			if(coverageHours > 0){
				pricingDOList = pricingDAO.findCoverageLevelPricing(condition, groupId, deductibleAmt, coverageTerm, coverageHours);
			}else{
				pricingDOList = pricingDAO.findCoverageLevelPricing(condition, groupId, deductibleAmt, coverageTerm);
			}
			
			logger.info("pricingDOList size: "+pricingDOList.size());
		}
		
		return pricingDOList;
	}

	@Override
	public void saveWarrantyInfo(QuoteDO quoteDO) {
		Quote quote = null;
		if(quoteDO.getId() != 0 && quoteDO.getId() > 0 && quoteDO.getQuoteId() != null && !quoteDO.getQuoteId().isEmpty()){
			QuotePK quotePK = new QuotePK(quoteDO.getQuoteId(), quoteDO.getId());
			quote = quoteDAO.findOne(quotePK);
		}else if(quoteDO.getQuoteId() != null && !quoteDO.getQuoteId().isEmpty()){
			quote = quoteDAO.findByIdQuoteId(quoteDO.getQuoteId());
			if(quote == null){
				quote = new Quote();
				QuotePK quotePK = new QuotePK();
				quotePK.setQuoteId(quoteDO.getQuoteId());
				
				quote.setId(quotePK);
				quote.setStatus(AggConstants.B_QUOTE_STATUS_ESTIMATING_PRICE);
			}
		}else{
			quote = new Quote();
			String quoteId = Util.getQuoteId(AggConstants.CHARSET_AZ_09, AggConstants.QUOTE_ID_LENGTH);
			logger.info("created quoteId: "+quoteId);
			QuotePK quotePK = new QuotePK();
			quotePK.setQuoteId(quoteId);
			
			quote.setId(quotePK);
			quote.setStatus(AggConstants.B_QUOTE_STATUS_ESTIMATING_PRICE);
		}
		quote.setDealer(dealerDAO.findOne(quoteDO.getDealerDO().getId()));
		quote.setManfExpired((quoteDO.isCoverageExpired())?(byte)1:(byte)0);
		quote.setManfEndDate(quoteDO.getCoverageEndDate());
		quote.setManfEndKnown((quoteDO.isCoverageEndDateUnknown())?(byte)1:(byte)0);
		quote.setManfVerified((quoteDO.isCoverageEndDateVerified())?(byte)1:(byte)0);
		quote.setPtHours(quoteDO.getPowerTrainHours());
		quote.setPtMonths(quoteDO.getPowerTrainMonths());
		quote.setHHours(quoteDO.getHydraulicsHours());
		quote.setHMonths(quoteDO.getHydraulicsMonths());
		quote.setMachineHours(quoteDO.getFullMachineHours());
		quote.setMachineMonths(quoteDO.getFullMachineMonths());
		quote.setOtherProv("");
		
		quote.setIsArchive((short)0);
		quote.setCreateDate(new Date());
		//quote.setPrId(0);
		quote.setServicingDealer(0);
		quote.setLastUpdate(new Date());
		
		quote = quoteDAO.save(quote);
		
		if(quote != null && quote.getId() != null){
			logger.info("quoteId: "+quote.getId().getQuoteId()+" and id: "+quote.getId().getId());
			quoteDO.setQuoteId(quote.getId().getQuoteId());
			quoteDO.setId(quote.getId().getId());
			if(quote.getStatus() == AggConstants.B_QUOTE_STATUS_ESTIMATING_PRICE){
				quoteDO.setStatusDesc(AggConstants.QUOTE_STATUS_ESTIMATING_PRICE);
			}else if(quote.getStatus() == AggConstants.B_QUOTE_STATUS_PURCHASE_REQUESTED){
				quoteDO.setStatusDesc(AggConstants.QUOTE_STATUS_PURCHASE_REQUESTED);
			}else if(quote.getStatus() == AggConstants.B_QUOTE_STATUS_INVOICED){
				quoteDO.setStatusDesc(AggConstants.QUOTE_STATUS_INVOICED);
			}else if(quote.getStatus() == AggConstants.B_QUOTE_STATUS_CLOSED){
				quoteDO.setStatusDesc(AggConstants.QUOTE_STATUS_CLOSED);
			}
		}
	}
	
	@Override
	public void saveMachineInfo(QuoteDO quoteDO) {
		Quote quote = null;
		if(quoteDO.getId() != 0 && quoteDO.getId() > 0 && quoteDO.getQuoteId() != null && !quoteDO.getQuoteId().isEmpty()){
			QuotePK quotePK = new QuotePK(quoteDO.getQuoteId(), quoteDO.getId());
			quote = quoteDAO.findOne(quotePK);
		}else if(quoteDO.getQuoteId() != null && !quoteDO.getQuoteId().isEmpty()){
			quote = quoteDAO.findByIdQuoteId(quoteDO.getQuoteId());
			if(quote == null){
				quote = new Quote();
				QuotePK quotePK = new QuotePK();
				quotePK.setQuoteId(quoteDO.getQuoteId());
				
				quote.setId(quotePK);
				quote.setStatus(AggConstants.B_QUOTE_STATUS_ESTIMATING_PRICE);
			}
		}
		if(quote != null){
			quote.setDealer(dealerDAO.findOne(quoteDO.getDealerDO().getId()));
			quote.setManfExpired((quoteDO.isCoverageExpired())?(byte)1:(byte)0);
			quote.setManfEndDate(quoteDO.getCoverageEndDate());
			quote.setManfEndKnown((quoteDO.isCoverageEndDateUnknown())?(byte)1:(byte)0);
			quote.setManfVerified((quoteDO.isCoverageEndDateVerified())?(byte)1:(byte)0);
			quote.setPtHours(quoteDO.getPowerTrainHours());
			quote.setPtMonths(quoteDO.getPowerTrainMonths());
			quote.setHHours(quoteDO.getHydraulicsHours());
			quote.setHMonths(quoteDO.getHydraulicsMonths());
			quote.setMachineHours(quoteDO.getFullMachineHours());
			quote.setMachineMonths(quoteDO.getFullMachineMonths());
			quote.setOtherProv("");
			
			Manufacturer manufacturer = manufacturerDAO.findOne(quoteDO.getManufacturerDO().getId());
			if(manufacturer != null){
				quote.setManufacturer(manufacturer);
				quote.setManfName(manufacturer.getManfName());
			}
			
			MachineInfo machineInfo = machineInfoDAO.findOne(quoteDO.getMachineInfoDO().getMachineId());
			if(machineInfo != null){
				quote.setMachineModel(machineInfo.getModel());
				quote.setMachineInfo(machineInfo);
				quote.setGroupId(new Long(machineInfo.getGroupConstant().getGroupId()).intValue());
			}
			
			quote.setMachinePower(quoteDO.getHorsePower());
			quote.setMachineSerial(quoteDO.getSerialNumber());
			quote.setMachineRetailPrice(quoteDO.getRetailPrice());
			quote.setMachineMeterHours(quoteDO.getMeterHours());
			quote.setMachineYear(quoteDO.getModelYear());
			quote.setUseOfEquip(useOfEquipmentDAO.findOne(quoteDO.getUseOfEquipmentDO().getId()));
			quote.setMachineSaleDate(quoteDO.getEstSaleDate());
			quote.setIsArchive((short)0);
			quote.setCreateDate(new Date());
			//quote.setPrId(0);
			quote.setServicingDealer(0);
			quote.setLastUpdate(new Date());
			
			quote = quoteDAO.save(quote);
			
			if(quote != null && quote.getId() != null){
				logger.info("quoteId: "+quote.getId().getQuoteId()+" and id: "+quote.getId().getId());
				quoteDO.setQuoteId(quote.getId().getQuoteId());
				quoteDO.setId(quote.getId().getId());
				if(quote.getStatus() == AggConstants.B_QUOTE_STATUS_ESTIMATING_PRICE){
					quoteDO.setStatusDesc(AggConstants.QUOTE_STATUS_ESTIMATING_PRICE);
				}else if(quote.getStatus() == AggConstants.B_QUOTE_STATUS_PURCHASE_REQUESTED){
					quoteDO.setStatusDesc(AggConstants.QUOTE_STATUS_PURCHASE_REQUESTED);
				}else if(quote.getStatus() == AggConstants.B_QUOTE_STATUS_INVOICED){
					quoteDO.setStatusDesc(AggConstants.QUOTE_STATUS_INVOICED);
				}else if(quote.getStatus() == AggConstants.B_QUOTE_STATUS_CLOSED){
					quoteDO.setStatusDesc(AggConstants.QUOTE_STATUS_CLOSED);
				}
			}
		
		}
	}
	
	@Override
	@Transactional
	public void saveCoverageInfo(QuoteDO quoteDO) {
		Quote quote = null;
		if(quoteDO.getId() != 0 && quoteDO.getId() > 0 && quoteDO.getQuoteId() != null && !quoteDO.getQuoteId().isEmpty()){
			QuotePK quotePK = new QuotePK(quoteDO.getQuoteId(), quoteDO.getId());
			quote = quoteDAO.findOne(quotePK);
		}else if(quoteDO.getQuoteId() != null && !quoteDO.getQuoteId().isEmpty()){
			quote = quoteDAO.findByIdQuoteId(quoteDO.getQuoteId());
			if(quote == null){
				quote = new Quote();
				QuotePK quotePK = new QuotePK();
				quotePK.setQuoteId(quoteDO.getQuoteId());
				
				quote.setId(quotePK);
				quote.setStatus(AggConstants.B_QUOTE_STATUS_ESTIMATING_PRICE);
			}
		}
		if(quote != null){
			quote.setDealer(dealerDAO.findOne(quoteDO.getDealerDO().getId()));
			quote.setManfExpired((quoteDO.isCoverageExpired())?(byte)1:(byte)0);
			quote.setManfEndDate(quoteDO.getCoverageEndDate());
			quote.setManfEndKnown((quoteDO.isCoverageEndDateUnknown())?(byte)1:(byte)0);
			quote.setManfVerified((quoteDO.isCoverageEndDateVerified())?(byte)1:(byte)0);
			quote.setPtHours(quoteDO.getPowerTrainHours());
			quote.setPtMonths(quoteDO.getPowerTrainMonths());
			quote.setHHours(quoteDO.getHydraulicsHours());
			quote.setHMonths(quoteDO.getHydraulicsMonths());
			quote.setMachineHours(quoteDO.getFullMachineHours());
			quote.setMachineMonths(quoteDO.getFullMachineMonths());
			quote.setOtherProv("");
			
			Manufacturer manufacturer = manufacturerDAO.findOne(quoteDO.getManufacturerDO().getId());
			if(manufacturer != null){
				quote.setManufacturer(manufacturer);
				quote.setManfName(manufacturer.getManfName());
			}
			
			MachineInfo machineInfo = machineInfoDAO.findOne(quoteDO.getMachineInfoDO().getMachineId());
			if(machineInfo != null){
				quote.setMachineModel(machineInfo.getModel());
				quote.setMachineInfo(machineInfo);
				quote.setGroupId(new Long(machineInfo.getGroupConstant().getGroupId()).intValue());
			}
			
			quote.setMachinePower(quoteDO.getHorsePower());
			quote.setMachineSerial(quoteDO.getSerialNumber());
			quote.setMachineRetailPrice(quoteDO.getRetailPrice());
			quote.setMachineMeterHours(quoteDO.getMeterHours());
			quote.setMachineYear(quoteDO.getModelYear());
			quote.setUseOfEquip(useOfEquipmentDAO.findOne(quoteDO.getUseOfEquipmentDO().getId()));
			quote.setMachineSaleDate(quoteDO.getEstSaleDate());
			
			quote.setDealerMarkupType(quoteDO.getDealerMarkupType());
			quote.setDealerMarkup(quoteDO.getDealerMarkup());
			quote.setDeductAmount(quoteDO.getDeductiblePrice());
			quote.setCoverageTerm(quoteDO.getCoverageTerm());
			quote.setCoverageLevelHours(quoteDO.getCoverageHours());
			quote.setCoverageType(quoteDO.getCoverageType());
			quote.setCoveragePrice(quoteDO.getQuoteBasePrice());
			
			quote.setIsArchive((short)0);
			quote.setCreateDate(new Date());
			//quote.setPrId(0);
			quote.setServicingDealer(0);
			quote.setLastUpdate(new Date());
			
			CustomerInfo customerInfo = new CustomerInfo();
			customerInfo.setQuoteId(quoteDO.getQuoteId());
			customerInfo.setAddress((quoteDO.getDealerAddress() != null)?quoteDO.getDealerAddress():"");
			customerInfo.setCity(quoteDO.getDealerCity());
			customerInfo.setEmail(quoteDO.getDealerEmail());
			customerInfo.setName(quoteDO.getDealerName());
			customerInfo.setPhone((quoteDO.getDealerPhone() != null)?quoteDO.getDealerPhone():"");
			customerInfo.setRemorse((quoteDO.isCustRemorsePeriod())?(byte)1:(byte)0);
			customerInfo.setState((quoteDO.getDealerState() != null)?quoteDO.getDealerState():"");
			customerInfo.setUnderstand((quoteDO.isCustUnderstandCoverage())?(byte)1:(byte)0);
			customerInfo.setZip((quoteDO.getDealerZip() != null)?quoteDO.getDealerZip():"");
			customerInfo.setLastUpdate(new Date());
			
			//TODO
			customerInfoDAO.save(customerInfo);
			
			quote = quoteDAO.save(quote);
			
			if(quote != null && quote.getId() != null){
				logger.info("quoteId: "+quote.getId().getQuoteId()+" and id: "+quote.getId().getId());
				quoteDO.setQuoteId(quote.getId().getQuoteId());
				quoteDO.setId(quote.getId().getId());
				if(quote.getStatus() == AggConstants.B_QUOTE_STATUS_ESTIMATING_PRICE){
					quoteDO.setStatusDesc(AggConstants.QUOTE_STATUS_ESTIMATING_PRICE);
				}else if(quote.getStatus() == AggConstants.B_QUOTE_STATUS_PURCHASE_REQUESTED){
					quoteDO.setStatusDesc(AggConstants.QUOTE_STATUS_PURCHASE_REQUESTED);
				}else if(quote.getStatus() == AggConstants.B_QUOTE_STATUS_INVOICED){
					quoteDO.setStatusDesc(AggConstants.QUOTE_STATUS_INVOICED);
				}else if(quote.getStatus() == AggConstants.B_QUOTE_STATUS_CLOSED){
					quoteDO.setStatusDesc(AggConstants.QUOTE_STATUS_CLOSED);
				}
			}
		
		}
	}
	
	@Override
	@Transactional
	public void savePurchaseInfo(QuoteDO quoteDO, String appUrl) throws Exception{
		Quote quote = null;
		if(quoteDO.getId() != 0 && quoteDO.getId() > 0 && quoteDO.getQuoteId() != null && !quoteDO.getQuoteId().isEmpty()){
			QuotePK quotePK = new QuotePK(quoteDO.getQuoteId(), quoteDO.getId());
			quote = quoteDAO.findOne(quotePK);
		}else if(quoteDO.getQuoteId() != null && !quoteDO.getQuoteId().isEmpty()){
			quote = quoteDAO.findByIdQuoteId(quoteDO.getQuoteId());
			if(quote == null){
				quote = new Quote();
				QuotePK quotePK = new QuotePK();
				quotePK.setQuoteId(quoteDO.getQuoteId());
				
				quote.setId(quotePK);
				quote.setStatus(AggConstants.B_QUOTE_STATUS_ESTIMATING_PRICE);
			}
		}
		if(quote != null){
			quote.setDealer(dealerDAO.findOne(quoteDO.getDealerDO().getId()));
			quote.setManfExpired((quoteDO.isCoverageExpired())?(byte)1:(byte)0);
			quote.setManfEndDate(quoteDO.getCoverageEndDate());
			quote.setManfEndKnown((quoteDO.isCoverageEndDateUnknown())?(byte)1:(byte)0);
			quote.setManfVerified((quoteDO.isCoverageEndDateVerified())?(byte)1:(byte)0);
			quote.setPtHours(quoteDO.getPowerTrainHours());
			quote.setPtMonths(quoteDO.getPowerTrainMonths());
			quote.setHHours(quoteDO.getHydraulicsHours());
			quote.setHMonths(quoteDO.getHydraulicsMonths());
			quote.setMachineHours(quoteDO.getFullMachineHours());
			quote.setMachineMonths(quoteDO.getFullMachineMonths());
			quote.setOtherProv("");
			
			Manufacturer manufacturer = manufacturerDAO.findOne(quoteDO.getManufacturerDO().getId());
			if(manufacturer != null){
				quote.setManufacturer(manufacturer);
				quote.setManfName(manufacturer.getManfName());
			}
			
			MachineInfo machineInfo = machineInfoDAO.findOne(quoteDO.getMachineInfoDO().getMachineId());
			if(machineInfo != null){
				quote.setMachineModel(machineInfo.getModel());
				quote.setMachineInfo(machineInfo);
				quote.setGroupId(new Long(machineInfo.getGroupConstant().getGroupId()).intValue());
			}
			
			quote.setMachinePower(quoteDO.getHorsePower());
			quote.setMachineSerial(quoteDO.getSerialNumber());
			quote.setMachineRetailPrice(quoteDO.getRetailPrice());
			quote.setMachineMeterHours(quoteDO.getMeterHours());
			quote.setMachineYear(quoteDO.getModelYear());
			quote.setUseOfEquip(useOfEquipmentDAO.findOne(quoteDO.getUseOfEquipmentDO().getId()));
			quote.setMachineSaleDate(quoteDO.getEstSaleDate());
			
			quote.setDealerMarkupType(quoteDO.getDealerMarkupType());
			quote.setDealerMarkup(quoteDO.getDealerMarkup());
			quote.setDeductAmount(quoteDO.getDeductiblePrice());
			quote.setCoverageTerm(quoteDO.getCoverageTerm());
			quote.setCoverageLevelHours(quoteDO.getCoverageHours());
			quote.setCoverageType(quoteDO.getCoverageType());
			quote.setCoveragePrice(quoteDO.getQuoteBasePrice());
			
			quote.setIsArchive((short)0);
			quote.setCreateDate(new Date());
			//quote.setPrId(0);
			quote.setServicingDealer(0);
			quote.setLastUpdate(new Date());
			
			//purchased status to 4
			quote.setStatus(AggConstants.B_QUOTE_STATUS_PURCHASE_REQUESTED);
			
			CustomerInfo customerInfo = null;
			customerInfo = customerInfoDAO.findOne(quoteDO.getQuoteId());
			if(customerInfo == null){
				customerInfo = new CustomerInfo();
				customerInfo.setQuoteId(quoteDO.getQuoteId());
			}
			customerInfo.setAddress((quoteDO.getDealerAddress() != null)?quoteDO.getDealerAddress():"");
			customerInfo.setCity(quoteDO.getDealerCity());
			customerInfo.setEmail(quoteDO.getDealerEmail());
			customerInfo.setName(quoteDO.getDealerName());
			customerInfo.setPhone((quoteDO.getDealerPhone() != null)?quoteDO.getDealerPhone():"");
			customerInfo.setRemorse((quoteDO.isCustRemorsePeriod())?(byte)1:(byte)0);
			customerInfo.setState((quoteDO.getDealerState() != null)?quoteDO.getDealerState():"");
			customerInfo.setUnderstand((quoteDO.isCustUnderstandCoverage())?(byte)1:(byte)0);
			customerInfo.setZip((quoteDO.getDealerZip() != null)?quoteDO.getDealerZip():"");
			customerInfo.setLastUpdate(new Date());
			
			//TODO
			customerInfoDAO.save(customerInfo);
			
			quote = quoteDAO.save(quote);
			
			if(quote != null && quote.getId() != null){
				logger.info("quoteId: "+quote.getId().getQuoteId()+" and id: "+quote.getId().getId());
				quoteDO.setQuoteId(quote.getId().getQuoteId());
				quoteDO.setId(quote.getId().getId());
				if(quote.getStatus() == AggConstants.B_QUOTE_STATUS_ESTIMATING_PRICE){
					quoteDO.setStatusDesc(AggConstants.QUOTE_STATUS_ESTIMATING_PRICE);
				}else if(quote.getStatus() == AggConstants.B_QUOTE_STATUS_PURCHASE_REQUESTED){
					quoteDO.setStatusDesc(AggConstants.QUOTE_STATUS_PURCHASE_REQUESTED);
				}else if(quote.getStatus() == AggConstants.B_QUOTE_STATUS_INVOICED){
					quoteDO.setStatusDesc(AggConstants.QUOTE_STATUS_INVOICED);
				}else if(quote.getStatus() == AggConstants.B_QUOTE_STATUS_CLOSED){
					quoteDO.setStatusDesc(AggConstants.QUOTE_STATUS_CLOSED);
				}
			}
			
			Map<String, Object> parameterMap = new HashMap<String, Object>();
			//parameterMap.put("imagePath", appUrl+"/assets/images/report_banner.png");
			parameterMap.put("imagePath", System.getProperty("user.dir")+reportImagePath);
			
			List<ReportDO> reportDOList = new ArrayList<ReportDO>();
			quoteDO.setCreateDate(quote.getCreateDate());
			reportDOList.add(getQuoteReportDO(quoteDO));
			JRDataSource jrDataSource = null;
			if(!reportDOList.isEmpty()){
				jrDataSource = new JRBeanCollectionDataSource(reportDOList);
			}else{
				jrDataSource = new JREmptyDataSource();
			}
			JasperReport jasperReport = JasperCompileManager.compileReport(resourceLoader.getResource("classpath:/jrxml/rpt_dealerQuote.jrxml").getInputStream());
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameterMap, jrDataSource);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			JasperExportManager.exportReportToPdfStream(jasperPrint, baos);
			DataSource pdfAttachment =  new ByteArrayDataSource(baos.toByteArray(), "application/pdf");
			String subject = "Dealer Quote: "+quoteDO.getQuoteId()+" pdf";
			String emailBody = "PFA Dealer Quote: "+quoteDO.getQuoteId()+" details.";
			EmailStatus emailStatus = emailSender.sendMailAsAttachment(adminEmail, subject, emailBody, pdfAttachment, "dealerQuote-"+quoteDO.getQuoteId());
			if(emailStatus != null){
				logger.info("emailStatus: "+emailStatus.getStatus());
				logger.info("Dealer Quote pdf Attachment emailed successfully");
			}
			
		}
	}

	private ReportDO getQuoteReportDO(QuoteDO quoteDO) throws Exception{
		ReportDO reportDO = new ReportDO();
		
		//SimpleDateFormat reportDateFormat = new SimpleDateFormat("MM/dd/yyyy");
		Locale locale = new Locale("en", "US");
		NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(locale);
		
		reportDO.setDealerName(quoteDO.getDealerDO().getName());
		reportDO.setQuoteDate((quoteDO.getEstSaleDate() != null)?dateFormat.format(quoteDO.getEstSaleDate()):"");
		
		//TODO with created_by field
		Dealer dealer = dealerDAO.findByCode(quoteDO.getDealerDO().getCode());
		if(dealer != null){
			if(dealer.getParentCode() != 0 && dealer.getParentCode() == dealer.getCode()){
				reportDO.setAttn(dealer.getName());
			}else{
				Dealer parentDealer = dealerDAO.findByCode(dealer.getCode());
				if(parentDealer != null){
					reportDO.setAttn(parentDealer.getName());
				}
			}
			
		}else{
			reportDO.setAttn("");
		}
		
		//TODO with purchase requested date
		Date createdDate = quoteDO.getCreateDate();
		if(createdDate != null){
			reportDO.setQuoteExpires(dateFormat.format(getQuoteExpirationDate(createdDate)));
		}else{
			reportDO.setQuoteExpires("");
		}
		reportDO.setQuoteId(quoteDO.getQuoteId());
		reportDO.setAddress(quoteDO.getDealerAddress()+", "+quoteDO.getDealerState()+" "+quoteDO.getDealerZip());
		reportDO.setCustName(quoteDO.getDealerName());
		reportDO.setOutStandingDesc(AggConstants.QUOTE_REPORT_OUT_STANDING_DESC);
		reportDO.setManufacturerName(quoteDO.getManufacturerDO().getName());
		reportDO.setModelName(quoteDO.getMachineInfoDO().getModel());
		reportDO.setModelSerialNo(quoteDO.getSerialNumber());
		reportDO.setEquipment(quoteDO.getUseOfEquipmentDO().getEquipName());
		reportDO.setRetailPrice(currencyFormat.format(quoteDO.getRetailPrice()));
		reportDO.setCurrentHours(quoteDO.getMeterHours()+"");
		reportDO.setMachineStatus(quoteDO.getMachineCondition());
		reportDO.setEmail(quoteDO.getDealerEmail());
		reportDO.setPhone(quoteDO.getDealerPhone());
		reportDO.setCoverageDesc(AggConstants.QUOTE_REPORT_COVERAGE_DESC);
		reportDO.setCoverageTerm(quoteDO.getCoverageTerm());
		reportDO.setCoverageHours(quoteDO.getCoverageHours());
		reportDO.setDeductibleAmount(currencyFormat.format(quoteDO.getDeductiblePrice()));
		reportDO.setCoverageType(quoteDO.getCoverageTypeDesc());
		reportDO.setPowerTrainMonths(quoteDO.getPowerTrainMonths());
		reportDO.setPowerTrainHours(quoteDO.getPowerTrainHours());
		reportDO.setHydraulicMonths(quoteDO.getHydraulicsMonths());
		reportDO.setHydraulicHours(quoteDO.getHydraulicsHours());
		reportDO.setFullMachineHours(quoteDO.getFullMachineHours());
		reportDO.setFullMachineMonths(quoteDO.getFullMachineMonths());
		reportDO.setWarrantyEndDate((quoteDO.getCoverageEndDate() != null)?dateFormat.format(quoteDO.getCoverageEndDate()):"");
		reportDO.setLimitOfLiability(currencyFormat.format(quoteDO.getMachineInfoDO().getLol()));
		reportDO.setPrice(currencyFormat.format(quoteDO.getCustomerPrice()));
		reportDO.setQuotePrice(currencyFormat.format(quoteDO.getQuoteBasePrice()));
		reportDO.setDealerMarkup(currencyFormat.format(quoteDO.getDealerMarkupPrice()));
		reportDO.setSpecialConsiderationDesc("None");
		
		return reportDO;
	}

	@Override
	public ReportDO getQuoteReportDetails(String quoteId, String reportType) {
		logger.info("Inside getQuoteReportDetails with quoteId: "+quoteId+" and reportType: "+reportType); 
		ReportDO reportDO = null;
		
		Quote quote = quoteDAO.findByIdQuoteId(quoteId);
		if(quote != null){
			reportDO = new ReportDO();
			
			//SimpleDateFormat reportDateFormat = new SimpleDateFormat("MM/dd/yyyy");
			Locale locale = new Locale("en", "US");
			NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(locale);
			
			reportDO.setDealerName(quote.getDealer().getName());
			reportDO.setQuoteDate((quote.getMachineSaleDate() != null)?dateFormat.format(quote.getMachineSaleDate()):"");
			
			//TODO with created_by field
			Dealer dealer = quote.getDealer();
			if(dealer != null){
				if(dealer.getParentCode() != 0 && dealer.getParentCode() == dealer.getCode()){
					reportDO.setAttn(dealer.getName());
				}else{
					Dealer parentDealer = dealerDAO.findByCode(dealer.getCode());
					if(parentDealer != null){
						reportDO.setAttn(parentDealer.getName());
					}
				}
				
			}else{
				reportDO.setAttn("");
			}
			
			Date createdDate = quote.getCreateDate();
			if(createdDate != null){
				reportDO.setQuoteExpires(dateFormat.format(getQuoteExpirationDate(createdDate)));
			}else{
				reportDO.setQuoteExpires("");
			}
			reportDO.setQuoteId(quoteId);
			CustomerInfo customerInfo = customerInfoDAO.findOne(quoteId);
			if(customerInfo != null){
				reportDO.setAddress(customerInfo.getAddress()+", "+customerInfo.getState()+" "+customerInfo.getZip());
				reportDO.setEmail(customerInfo.getEmail());
				reportDO.setPhone(customerInfo.getPhone());
				reportDO.setCustName(customerInfo.getName());
			}
			reportDO.setOutStandingDesc((reportType != null && reportType.equals(AggConstants.REPORT_TYPE_INVOICE))
					? AggConstants.INVOICE_REPORT_OUT_STANDING_DESC : AggConstants.QUOTE_REPORT_OUT_STANDING_DESC);
			reportDO.setManufacturerName(quote.getManufacturer().getManfName());
			reportDO.setModelName(quote.getMachineInfo().getModel());
			reportDO.setModelSerialNo(quote.getMachineSerial());
			if(quote.getUseOfEquip() != null){
				reportDO.setEquipment(quote.getUseOfEquip().getEquipName());
			}else{
				reportDO.setEquipment("");
			}
			reportDO.setRetailPrice(currencyFormat.format(quote.getMachineRetailPrice()));
			reportDO.setCurrentHours(quote.getMachineMeterHours()+"");
			if((quote.getManfExpired() == 1) || (quote.getManfEndDate() != null && (quote.getManfEndDate().compareTo(new Date()) < 0))){
				reportDO.setMachineStatus(AggConstants.MACHINE_STATUS_USED);
			}else{
				reportDO.setMachineStatus(AggConstants.MACHINE_STATUS_NEW);
			}
			
			reportDO.setCoverageDesc(AggConstants.QUOTE_REPORT_COVERAGE_DESC);
			reportDO.setCoverageTerm(quote.getCoverageTerm());
			reportDO.setCoverageHours(quote.getCoverageLevelHours());
			reportDO.setDeductibleAmount(currencyFormat.format(quote.getDeductAmount()));
			String coverageType = quote.getCoverageType();
			if(coverageType != null && !coverageType.isEmpty()){
				if(coverageType.equalsIgnoreCase("PT")){
					reportDO.setCoverageType("Powertrain");
				}else if(coverageType.equalsIgnoreCase("PH")){
					reportDO.setCoverageType("Powertrain + Hydraulic");
				}else if(coverageType.equalsIgnoreCase("PL")){
					reportDO.setCoverageType("Powertrain + Hydraulic + Platform");
				}
			}
			reportDO.setPowerTrainMonths(quote.getPtMonths());
			reportDO.setPowerTrainHours(quote.getPtHours());
			reportDO.setHydraulicMonths(quote.getHMonths());
			reportDO.setHydraulicHours(quote.getHHours());
			reportDO.setFullMachineHours(quote.getMachineHours());
			reportDO.setFullMachineMonths(quote.getMachineMonths());
			reportDO.setWarrantyEndDate((quote.getManfEndDate() != null)?dateFormat.format(quote.getManfEndDate()):"");
			double lol = quote.getMachineInfo().getGroupConstant().getLol();
			double coveragePrice = quote.getCoveragePrice();
			AdminAdjustment adminAdjustment = adminAdjustmentDAO.findOne(quote.getId().getQuoteId());
			if(quote.getProgram() != null){
				lol = quote.getProgram().getPrLol();
			}
			if(adminAdjustment != null){
				if(adminAdjustment.getLol() > 0){
					lol = adminAdjustment.getLol();
				}
				if(adminAdjustment.getBasePrice() > 0){
					coveragePrice = adminAdjustment.getBasePrice();
				}
				
				reportDO.setInvoiceDate((adminAdjustment.getInvoiceDate() != null)?dateFormat.format(adminAdjustment.getInvoiceDate()):"");
			}
			reportDO.setLimitOfLiability(currencyFormat.format(lol));
			String dealerMarkupType = quote.getDealerMarkupType();
			double customerPrice = 0.0;
			double dealerMarkupPrice = 0.0;
			if(dealerMarkupType != null && !dealerMarkupType.isEmpty()){
				if(dealerMarkupType.equalsIgnoreCase("price")){
					dealerMarkupPrice = quote.getDealerMarkup();
				}else{
					dealerMarkupPrice = (coveragePrice * quote.getDealerMarkup())/100.0;
				}
			}
			customerPrice = coveragePrice + dealerMarkupPrice;
			reportDO.setPrice(currencyFormat.format(customerPrice));
			reportDO.setQuotePrice(currencyFormat.format(coveragePrice));
			reportDO.setDealerMarkup(currencyFormat.format(dealerMarkupPrice));
			reportDO.setSpecialConsiderationDesc("None");
			reportDO.setAmountDue(currencyFormat.format(coveragePrice));
			//reportDO
		}
		
		return reportDO;
	}

	
	@Override
	public List<QuoteDO> getQuotes(AccountDO accountDO) {
		List<QuoteDO> quoteDOList = null;
		if(accountDO.getRoleDO().getAccountType().equalsIgnoreCase(AggConstants.ACCOUNT_TYPE_ADMIN)){
			//List<Quote> quoteList = Util.toList(quoteDAO.findAll());
			//quoteDOList = getQuoteDetails(quoteList);
			quoteDOList = quoteDAO.findAllQuotes(AggConstants.B_QUOTE_STATUS_UNACRHIVE);
		}else{
			//List<Quote> quoteList = Util.toList(quoteDAO.findByDealerId(accountDO.getDealerId()));
			//quoteDOList = getQuoteDetails(quoteList);
			quoteDOList = quoteDAO.findAllQuotesByDealer(accountDO.getDealerId(), AggConstants.B_QUOTE_STATUS_UNACRHIVE);
		}
		
		return quoteDOList;
	}
	
	@Override
	public List<QuoteDO> getArchivedQuotes(AccountDO accountDO) {
		List<QuoteDO> quoteDOList = null;
		if(accountDO.getRoleDO().getAccountType().equalsIgnoreCase(AggConstants.ACCOUNT_TYPE_ADMIN)){
			quoteDOList = quoteDAO.findAllQuotes(AggConstants.B_QUOTE_STATUS_ACRHIVE);
		}else{
			quoteDOList = quoteDAO.findAllQuotesByDealer(accountDO.getDealerId(), AggConstants.B_QUOTE_STATUS_ACRHIVE);
		}
		
		return quoteDOList;
	}
	
	/**
	 * @param quoteList
	 * @return
	 */
	private List<QuoteDO> getQuoteDetails(List<Quote> quoteList){
		List<QuoteDO> quoteDOList = null;
		if(quoteList != null && !quoteList.isEmpty()){
			quoteDOList = new ArrayList<QuoteDO>();
			for(Quote quote : quoteList){
				quoteDOList.add(getQuoteDetails(quote));
			}
		}
		
		return quoteDOList;
	}

	/**
	 * @param quote
	 * @return
	 */
	private QuoteDO getQuoteDetails(Quote quote) {
		QuoteDO quoteDO = null;
		if(quote != null){
			quoteDO = new QuoteDO();
			quoteDO.setId(quote.getId().getId());
			quoteDO.setQuoteId(quote.getId().getQuoteId());
			Dealer dealer = quote.getDealer();
			if(dealer != null){
				DealerDO dealerDO = new DealerDO();
				dealerDO.setCode(dealer.getCode());
				dealerDO.setId(dealer.getId());
				dealerDO.setName(dealer.getName());
				dealerDO.setInvoiceEmail(dealer.getInvoiceEmail());
				dealerDO.setMarketEmail(dealer.getMarketEmail());
				dealerDO.setCity(dealer.getCity());
				dealerDO.setAddress1(dealer.getAddress());
				dealerDO.setAddress2(dealer.getAddress2());
				dealerDO.setState(dealer.getState());
				dealerDO.setZip(dealer.getZip());
				dealerDO.setPhone(dealer.getPhone());
				
				quoteDO.setDealerDO(dealerDO);
			}
			quoteDO.setCoverageExpired((quote.getManfExpired() == 1)?true:false);
			quoteDO.setCoverageEndDate(quote.getManfEndDate());
			if(quote.getManfEndDate() != null){
				quoteDO.setCoverageEndDateStr(dateFormat.format(quote.getManfEndDate()));
			}
			quoteDO.setCoverageEndDateUnknown((quote.getManfEndKnown() == 1)?true:false);
			quoteDO.setCoverageEndDateVerified((quote.getManfVerified() == 1)?true:false);
			quoteDO.setPowerTrainMonths(quote.getPtMonths());
			quoteDO.setPowerTrainHours(quote.getPtHours());
			quoteDO.setHydraulicsMonths(quote.getHMonths());
			quoteDO.setHydraulicsHours(quote.getHHours());
			quoteDO.setFullMachineHours(quote.getMachineHours());
			quoteDO.setFullMachineMonths(quote.getMachineMonths());
			
			Manufacturer manufacturer = quote.getManufacturer();
			if(manufacturer != null){
				ManufacturerDO manufacturerDO = new ManufacturerDO();
				manufacturerDO.setId(manufacturer.getManfId());
				manufacturerDO.setName(manufacturer.getManfName());
				
				quoteDO.setManufacturerDO(manufacturerDO);
			}
			
			AdminAdjustment adminAdjustment = adminAdjustmentDAO.findOne(quote.getId().getQuoteId());
			MachineInfo machineInfo = quote.getMachineInfo();
			if(machineInfo != null){
				MachineInfoDO machineInfoDO = new MachineInfoDO();
				machineInfoDO.setMachineId(machineInfo.getMachineId());
				machineInfoDO.setMachineType(machineInfo.getMachineType().getMachineType());
				machineInfoDO.setModel(machineInfo.getModel());
				machineInfoDO.setModelYear(machineInfo.getModelYear());
				machineInfoDO.setEPower(machineInfo.getEPower());
				machineInfoDO.setLol(machineInfo.getGroupConstant().getLol());
				machineInfoDO.setLolToDisplay(machineInfo.getGroupConstant().getLol());
				if(quote.getProgram() != null){
					machineInfoDO.setLol(quote.getProgram().getPrLol());
					machineInfoDO.setLolToDisplay(quote.getProgram().getPrLol());
				}
				if(adminAdjustment != null && adminAdjustment.getLol() > 0){
					machineInfoDO.setLol(adminAdjustment.getLol());
				}
				
				quoteDO.setMachineInfoDO(machineInfoDO);
			}
			
			if(quote.getProgram() != null){
				quoteDO.setProgram(quote.getProgram().getPrName());
			}
			
			quoteDO.setGroupId(quote.getGroupId());
			quoteDO.setHorsePower(quote.getMachinePower());
			quoteDO.setSerialNumber(quote.getMachineSerial());
			if(quote.getMachineSerial() == null){
				quoteDO.setSerialNumberUnknown(true);
			}
			quoteDO.setRetailPrice(quote.getMachineRetailPrice());
			quoteDO.setMeterHours(quote.getMachineMeterHours());
			quoteDO.setModelYear((quote.getMachineYear() != null)?quote.getMachineYear():0);
			
			UseOfEquip useOfEquip = quote.getUseOfEquip();
			if(useOfEquip != null){
				UseOfEquipmentDO useOfEquipmentDO = new UseOfEquipmentDO();
				useOfEquipmentDO.setId(useOfEquip.getId());
				useOfEquipmentDO.setEquipName(useOfEquip.getEquipName());
				
				quoteDO.setUseOfEquipmentDO(useOfEquipmentDO);
			}
			
			quoteDO.setEstSaleDate(quote.getMachineSaleDate());
			if(quote.getMachineSaleDate() != null){
				quoteDO.setEstSaleDateStr(dateFormat.format(quote.getMachineSaleDate()));
			}
			quoteDO.setOtherProv(quote.getOtherProv());
			quoteDO.setDealerMarkup(quote.getDealerMarkup());
			quoteDO.setDealerMarkupType(quote.getDealerMarkupType());
			quoteDO.setDeductiblePrice(quote.getDeductAmount());
			quoteDO.setCoverageTerm(quote.getCoverageTerm());
			quoteDO.setCoverageHours(quote.getCoverageLevelHours());
			quoteDO.setCoverageType(quote.getCoverageType());
			quoteDO.setQuoteBasePrice(quote.getCoveragePrice());
			quoteDO.setQuoteBasePriceToDisplay(quote.getCoveragePrice());
			if(adminAdjustment != null && adminAdjustment.getBasePrice() > 0){
				quoteDO.setQuoteBasePrice(adminAdjustment.getBasePrice());
			}
			quoteDO.setStatus(quote.getStatus());
			quoteDO.setLastUpdate(quote.getLastUpdate());
			String statusDesc = "";
			if(quote.getIsArchive() == AggConstants.B_QUOTE_STATUS_ACRHIVE){
				statusDesc = AggConstants.QUOTE_STATUS_ACRHIVE;
			}else if(quote.getStatus() == AggConstants.B_QUOTE_STATUS_ESTIMATING_PRICE){
				statusDesc = AggConstants.QUOTE_STATUS_ESTIMATING_PRICE;
			}else if(quote.getStatus() == AggConstants.B_QUOTE_STATUS_PURCHASE_REQUESTED){
				statusDesc = AggConstants.QUOTE_STATUS_PURCHASE_REQUESTED;
			}else if(quote.getStatus() == AggConstants.B_QUOTE_STATUS_INVOICED){
				statusDesc = AggConstants.QUOTE_STATUS_INVOICED;
			}else if(quote.getStatus() == AggConstants.B_QUOTE_STATUS_CLOSED){
				statusDesc = AggConstants.QUOTE_STATUS_CLOSED;
			}
			quoteDO.setStatusDesc(statusDesc);
			quoteDO.setStatus(quote.getStatus());
			
			String dealerMarkupType = quote.getDealerMarkupType();
			double coveragePrice = quote.getCoveragePrice();
			double dealerMarkupPrice = 0.0;
			if(dealerMarkupType != null && !dealerMarkupType.isEmpty()){
				if(dealerMarkupType.equalsIgnoreCase("price")){
					dealerMarkupPrice = quote.getDealerMarkup();
				}else{
					dealerMarkupPrice = (coveragePrice * quote.getDealerMarkup())/100.0;
				}
			}
			quoteDO.setDealerMarkupPrice(dealerMarkupPrice);
			
			CustomerInfo customerInfo = customerInfoDAO.findOne(quote.getId().getQuoteId());
			if(customerInfo != null){
				quoteDO.setDealerName(customerInfo.getName());
				quoteDO.setDealerAddress(customerInfo.getAddress());
				quoteDO.setDealerCity(customerInfo.getCity());
				quoteDO.setDealerEmail(customerInfo.getEmail());
				quoteDO.setDealerPhone(customerInfo.getPhone());
				quoteDO.setDealerState(customerInfo.getState());
				quoteDO.setDealerZip(customerInfo.getZip());
				quoteDO.setCustRemorsePeriod((customerInfo.getRemorse() == 1)?true:false);
				quoteDO.setCustUnderstandCoverage((customerInfo.getUnderstand() == 1)?true:false);
			}
			
			if(adminAdjustment != null){
				if(adminAdjustment.getBasePrice() > 0){
					quoteDO.setAdjustedBasePrice(adminAdjustment.getBasePrice());
				}else{
					quoteDO.setAdjustedBasePrice(quote.getCoveragePrice());
				}
				if(adminAdjustment.getLol() > 0){
					quoteDO.setAdjustedLol(adminAdjustment.getLol());
				}else{
					if(quoteDO.getMachineInfoDO() != null){
						quoteDO.setAdjustedLol(quoteDO.getMachineInfoDO().getLol());
					}
				}
				
				quoteDO.setSpecialConsiderations(adminAdjustment.getSpecialConsideration());
				quoteDO.setCondsForCoverage(adminAdjustment.getCConditions());
				quoteDO.setInceptionDate(adminAdjustment.getInceptionDate());
				quoteDO.setExpirationDate(adminAdjustment.getExpirationDate());
				quoteDO.setExpirationHours(adminAdjustment.getExpirationHours());
				quoteDO.setDealHistory(adminAdjustment.getDealHistory());
			}
			
			if(quote.getCoverageLevelHours() > 0){
				boolean coverageExpired = true;
				if(quoteDO.isCoverageExpired()){
					coverageExpired = true;
		    	}else if(quoteDO.getCoverageEndDate() != null && quoteDO.getCoverageEndDate().after(new Date())){
		    		coverageExpired = false;
		    	}
				
				List<PricingDO> pricingDOList = getCoveragePriceDetils(coverageExpired, (quoteDO.getMachineInfoDO()!=null)?quoteDO.getMachineInfoDO().getMachineId():0, 
						new Double(quoteDO.getDeductiblePrice()).intValue(), quoteDO.getCoverageTerm(), quote.getCoverageLevelHours());
				Set<String> coverageTypeSet = null;
				if(pricingDOList != null && !pricingDOList.isEmpty()){
					coverageTypeSet = new HashSet<String>();
					for(PricingDO pricingDO : pricingDOList){
						if(pricingDO.getPhBasePrice() > 0){
							coverageTypeSet.add("PH");
						}
						if(pricingDO.getPlBasePrice() > 0){
							coverageTypeSet.add("PL");
						}
						if(pricingDO.getPtBasePrice() > 0){
							coverageTypeSet.add("PT");
						}
					}
					quoteDO.setCoverageTypeSet(coverageTypeSet);
					//logger.info("coverageTypeSet size: "+coverageTypeSet.size());
				}
				
			}
			
		}
		
		return quoteDO;
	}

	@Override
	public QuoteDO getQuote(AccountDO accountDetails, int id, String quoteId) {
		QuoteDO quoteDO = null;
		if(id > 0 && quoteId != null && !quoteId.isEmpty()){
			QuotePK quotePK = new QuotePK(quoteId, id);
			quoteDO = getQuoteDetails(quoteDAO.findOne(quotePK));
		}
		
		return quoteDO;
	}

	@Override
	public boolean archiveQuote(QuoteDO quoteDO) {
		boolean condition = false;
		if(quoteDO.getId() != 0 && quoteDO.getId() > 0 && quoteDO.getQuoteId() != null && !quoteDO.getQuoteId().isEmpty()){
			QuotePK quotePK = new QuotePK(quoteDO.getQuoteId(), quoteDO.getId());
			Quote quote = quoteDAO.findOne(quotePK);
			if(quote != null){
				quote.setIsArchive((short)1);;
				quoteDAO.save(quote);
				
				quoteDO.setIsArchive((short)1);
				condition = true;
			}
		}
		
		return condition;
	}

	@Override
	@Transactional
	public boolean updateQuote(QuoteDO quoteDO) {
		boolean condition = false;
		Quote quote = null;
		if(quoteDO.getId() != 0 && quoteDO.getId() > 0 && quoteDO.getQuoteId() != null && !quoteDO.getQuoteId().isEmpty()){
			QuotePK quotePK = new QuotePK(quoteDO.getQuoteId(), quoteDO.getId());
			quote = quoteDAO.findOne(quotePK);
		}else if(quoteDO.getQuoteId() != null && !quoteDO.getQuoteId().isEmpty()){
			quote = quoteDAO.findByIdQuoteId(quoteDO.getQuoteId());
		}
		if(quote != null){
			quote.setDealer(dealerDAO.findOne(quoteDO.getDealerDO().getId()));
			quote.setManfExpired((quoteDO.isCoverageExpired())?(byte)1:(byte)0);
			quote.setManfEndDate(quoteDO.getCoverageEndDate());
			//quote.setManfEndKnown((quoteDO.isCoverageEndDateUnknown())?(byte)1:(byte)0);
			//quote.setManfVerified((quoteDO.isCoverageEndDateVerified())?(byte)1:(byte)0);
			//quote.setPtHours(quoteDO.getPowerTrainHours());
			//quote.setPtMonths(quoteDO.getPowerTrainMonths());
			//quote.setHHours(quoteDO.getHydraulicsHours());
			//quote.setHMonths(quoteDO.getHydraulicsMonths());
			//quote.setMachineHours(quoteDO.getFullMachineHours());
			//quote.setMachineMonths(quoteDO.getFullMachineMonths());
			quote.setOtherProv(quoteDO.getOtherProv());
			
			Manufacturer manufacturer = manufacturerDAO.findOne(quoteDO.getManufacturerDO().getId());
			if(manufacturer != null){
				quote.setManufacturer(manufacturer);
				quote.setManfName(manufacturer.getManfName());
			}
			
			MachineInfo machineInfo = machineInfoDAO.findOne(quoteDO.getMachineInfoDO().getMachineId());
			if(machineInfo != null){
				quote.setMachineModel(machineInfo.getModel());
				quote.setMachineInfo(machineInfo);
				quote.setGroupId(new Long(machineInfo.getGroupConstant().getGroupId()).intValue());
			}
			
			quote.setMachinePower(quoteDO.getHorsePower());
			quote.setMachineSerial(quoteDO.getSerialNumber());
			quote.setMachineRetailPrice(quoteDO.getRetailPrice());
			quote.setMachineMeterHours(quoteDO.getMeterHours());
			quote.setMachineYear(quoteDO.getModelYear());
			quote.setUseOfEquip(useOfEquipmentDAO.findOne(quoteDO.getUseOfEquipmentDO().getId()));
			quote.setMachineSaleDate(quoteDO.getEstSaleDate());
			
			quote.setDealerMarkupType(quoteDO.getDealerMarkupType());
			quote.setDealerMarkup(quoteDO.getDealerMarkup());
			quote.setDeductAmount(quoteDO.getDeductiblePrice());
			quote.setCoverageTerm(quoteDO.getCoverageTerm());
			quote.setCoverageLevelHours(quoteDO.getCoverageHours());
			quote.setCoverageType(quoteDO.getCoverageType());
			
			boolean coverageExpired = true;
			if(quoteDO.isCoverageExpired()){
				coverageExpired = true;
	    	}else if(quoteDO.getCoverageEndDate() != null && quoteDO.getCoverageEndDate().after(new Date())){
	    		coverageExpired = false;
	    	}
			List<PricingDO> pricingDOList = getCoveragePriceDetils(coverageExpired, quoteDO.getMachineInfoDO().getMachineId(), 
					new Double(quoteDO.getDeductiblePrice()).intValue(), quoteDO.getCoverageTerm(), quote.getCoverageLevelHours());
			if(pricingDOList != null && !pricingDOList.isEmpty()){
				PricingDO pricingDO = pricingDOList.get(0);
				if(quoteDO.getCoverageType() != null){
					if(quote.getProgram() != null){
						quote.setCoveragePrice(quote.getProgram().getPrCost());
					}else{
						if(quoteDO.getCoverageType().equals("PT")){
							quote.setCoveragePrice(pricingDO.getPtBasePrice());
						}else if(quoteDO.getCoverageType().equals("PH")){
							quote.setCoveragePrice(pricingDO.getPhBasePrice());
						}else if(quoteDO.getCoverageType().equals("PL")){
							quote.setCoveragePrice(pricingDO.getPlBasePrice());
						}
					}
				}
			}
			
			//quote.setIsArchive((short)0);
			//quote.setCreateDate(new Date());
			//quote.setPrId(0);
			quote.setServicingDealer(0);
			quote.setLastUpdate(new Date());
			
			if(quoteDO.getStatus() > quote.getStatus()){
				quote.setStatus(quoteDO.getStatus());
			}
			
			CustomerInfo customerInfo = customerInfoDAO.findOne(quoteDO.getQuoteId());
			if(customerInfo == null){
				customerInfo = new CustomerInfo();
				customerInfo.setQuoteId(quoteDO.getQuoteId());
			}
			customerInfo.setAddress((quoteDO.getDealerAddress() != null)?quoteDO.getDealerAddress():"");
			customerInfo.setCity(quoteDO.getDealerCity());
			customerInfo.setEmail(quoteDO.getDealerEmail());
			customerInfo.setName(quoteDO.getDealerName());
			customerInfo.setPhone((quoteDO.getDealerPhone() != null)?quoteDO.getDealerPhone():"");
			customerInfo.setRemorse((quoteDO.isCustRemorsePeriod())?(byte)1:(byte)0);
			customerInfo.setState((quoteDO.getDealerState() != null)?quoteDO.getDealerState():"");
			customerInfo.setUnderstand((quoteDO.isCustUnderstandCoverage())?(byte)1:(byte)0);
			customerInfo.setZip((quoteDO.getDealerZip() != null)?quoteDO.getDealerZip():"");
			customerInfo.setLastUpdate(new Date());
			
			//TODO
			customerInfoDAO.save(customerInfo);
			
			AdminAdjustment adminAdjustment = adminAdjustmentDAO.findOne(quoteDO.getQuoteId());
			if(adminAdjustment == null){
				adminAdjustment = new AdminAdjustment(); 
			}
			
			adminAdjustment.setQuoteId(quoteDO.getQuoteId());
			adminAdjustment.setBasePrice(quoteDO.getAdjustedBasePrice());
			adminAdjustment.setLol(quoteDO.getAdjustedLol());
			adminAdjustment.setSpecialConsideration(quoteDO.getSpecialConsiderations());
			adminAdjustment.setCConditions(quoteDO.getCondsForCoverage());
			adminAdjustment.setDealHistory(quoteDO.getDealHistory());
			adminAdjustment.setLastUpdate(new Date());
			adminAdjustment.setDealHistory(quoteDO.getDealHistory());
			
			adminAdjustmentDAO.save(adminAdjustment);
			
			quote = quoteDAO.save(quote);
			
			if(quote != null && quote.getId() != null){
				logger.info("quoteId: "+quote.getId().getQuoteId()+" and id: "+quote.getId().getId());
				if(quote.getStatus() == AggConstants.B_QUOTE_STATUS_ESTIMATING_PRICE){
					quoteDO.setStatusDesc(AggConstants.QUOTE_STATUS_ESTIMATING_PRICE);
				}else if(quote.getStatus() == AggConstants.B_QUOTE_STATUS_PURCHASE_REQUESTED){
					quoteDO.setStatusDesc(AggConstants.QUOTE_STATUS_PURCHASE_REQUESTED);
				}else if(quote.getStatus() == AggConstants.B_QUOTE_STATUS_INVOICED){
					quoteDO.setStatusDesc(AggConstants.QUOTE_STATUS_INVOICED);
				}else if(quote.getStatus() == AggConstants.B_QUOTE_STATUS_CLOSED){
					quoteDO.setStatusDesc(AggConstants.QUOTE_STATUS_CLOSED);
				}
				
				condition = true;
			}
			
		}
		
		return condition;
	}

	@Override
	@Transactional
	public boolean invoiceQuote(QuoteDO quoteDO, AccountDO accountDO, String appUrl) throws Exception{
		boolean condition = false;
		Quote quote = null;
		if(quoteDO.getId() != 0 && quoteDO.getId() > 0 && quoteDO.getQuoteId() != null && !quoteDO.getQuoteId().isEmpty()){
			QuotePK quotePK = new QuotePK(quoteDO.getQuoteId(), quoteDO.getId());
			quote = quoteDAO.findOne(quotePK);
		}else if(quoteDO.getQuoteId() != null && !quoteDO.getQuoteId().isEmpty()){
			quote = quoteDAO.findByIdQuoteId(quoteDO.getQuoteId());
		}
		if(quote != null){
			Dealer dealer = dealerDAO.findOne(quoteDO.getDealerDO().getId());
			quote.setDealer(dealer);
			quote.setManfExpired((quoteDO.isCoverageExpired())?(byte)1:(byte)0);
			quote.setManfEndDate(quoteDO.getCoverageEndDate());
			//quote.setManfEndKnown((quoteDO.isCoverageEndDateUnknown())?(byte)1:(byte)0);
			//quote.setManfVerified((quoteDO.isCoverageEndDateVerified())?(byte)1:(byte)0);
			//quote.setPtHours(quoteDO.getPowerTrainHours());
			//quote.setPtMonths(quoteDO.getPowerTrainMonths());
			//quote.setHHours(quoteDO.getHydraulicsHours());
			//quote.setHMonths(quoteDO.getHydraulicsMonths());
			//quote.setMachineHours(quoteDO.getFullMachineHours());
			//quote.setMachineMonths(quoteDO.getFullMachineMonths());
			quote.setOtherProv(quoteDO.getOtherProv());
			
			Manufacturer manufacturer = manufacturerDAO.findOne(quoteDO.getManufacturerDO().getId());
			if(manufacturer != null){
				quote.setManufacturer(manufacturer);
				quote.setManfName(manufacturer.getManfName());
			}
			
			MachineInfo machineInfo = machineInfoDAO.findOne(quoteDO.getMachineInfoDO().getMachineId());
			if(machineInfo != null){
				quote.setMachineModel(machineInfo.getModel());
				quote.setMachineInfo(machineInfo);
				quote.setGroupId(new Long(machineInfo.getGroupConstant().getGroupId()).intValue());
			}
			
			quote.setMachinePower(quoteDO.getHorsePower());
			quote.setMachineSerial(quoteDO.getSerialNumber());
			quote.setMachineRetailPrice(quoteDO.getRetailPrice());
			quote.setMachineMeterHours(quoteDO.getMeterHours());
			quote.setMachineYear(quoteDO.getModelYear());
			quote.setUseOfEquip(useOfEquipmentDAO.findOne(quoteDO.getUseOfEquipmentDO().getId()));
			quote.setMachineSaleDate(quoteDO.getEstSaleDate());
			
			quote.setDealerMarkupType(quoteDO.getDealerMarkupType());
			quote.setDealerMarkup(quoteDO.getDealerMarkup());
			quote.setDeductAmount(quoteDO.getDeductiblePrice());
			quote.setCoverageTerm(quoteDO.getCoverageTerm());
			quote.setCoverageLevelHours(quoteDO.getCoverageHours());
			quote.setCoverageType(quoteDO.getCoverageType());
			
			boolean coverageExpired = true;
			if(quoteDO.isCoverageExpired()){
				coverageExpired = true;
	    	}else if(quoteDO.getCoverageEndDate() != null && quoteDO.getCoverageEndDate().after(new Date())){
	    		coverageExpired = false;
	    	}
			
			if(quote.getProgram() == null){
				List<PricingDO> pricingDOList = getCoveragePriceDetils(coverageExpired, quoteDO.getMachineInfoDO().getMachineId(), 
						new Double(quoteDO.getDeductiblePrice()).intValue(), quoteDO.getCoverageTerm(), quote.getCoverageLevelHours());
				if(pricingDOList != null && !pricingDOList.isEmpty()){
					PricingDO pricingDO = pricingDOList.get(0);
					if(quoteDO.getCoverageType() != null){
						if(quoteDO.getCoverageType().equals("PT")){
							quote.setCoveragePrice(pricingDO.getPtBasePrice());
						}else if(quoteDO.getCoverageType().equals("PH")){
							quote.setCoveragePrice(pricingDO.getPhBasePrice());
						}else if(quoteDO.getCoverageType().equals("PL")){
							quote.setCoveragePrice(pricingDO.getPlBasePrice());
						}
					}
				}
			}
			
			//quote.setIsArchive((short)0);
			//quote.setCreateDate(new Date());
			//quote.setPrId(0);
			quote.setServicingDealer(0);
			quote.setLastUpdate(new Date());
			
			quote.setStatus(AggConstants.B_QUOTE_STATUS_INVOICED);
			
			CustomerInfo customerInfo = null;
			customerInfo = customerInfoDAO.findOne(quoteDO.getQuoteId());
			if(customerInfo == null){
				customerInfo = new CustomerInfo();
				customerInfo.setQuoteId(quoteDO.getQuoteId());
			}
			customerInfo.setAddress((quoteDO.getDealerAddress() != null)?quoteDO.getDealerAddress():"");
			customerInfo.setCity(quoteDO.getDealerCity());
			customerInfo.setEmail(quoteDO.getDealerEmail());
			customerInfo.setName(quoteDO.getDealerName());
			customerInfo.setPhone((quoteDO.getDealerPhone() != null)?quoteDO.getDealerPhone():"");
			customerInfo.setRemorse((quoteDO.isCustRemorsePeriod())?(byte)1:(byte)0);
			customerInfo.setState((quoteDO.getDealerState() != null)?quoteDO.getDealerState():"");
			customerInfo.setUnderstand((quoteDO.isCustUnderstandCoverage())?(byte)1:(byte)0);
			customerInfo.setZip((quoteDO.getDealerZip() != null)?quoteDO.getDealerZip():"");
			customerInfo.setLastUpdate(new Date());
			
			//TODO
			customerInfoDAO.save(customerInfo);
			
			AdminAdjustment adminAdjustment = adminAdjustmentDAO.findOne(quoteDO.getQuoteId());
			if(adminAdjustment == null){
				adminAdjustment = new AdminAdjustment(); 
			}
			
			adminAdjustment.setQuoteId(quoteDO.getQuoteId());
			adminAdjustment.setBasePrice(quoteDO.getAdjustedBasePrice());
			adminAdjustment.setLol(quoteDO.getAdjustedLol());
			adminAdjustment.setSpecialConsideration(quoteDO.getSpecialConsiderations());
			adminAdjustment.setCConditions(quoteDO.getCondsForCoverage());
			adminAdjustment.setLastUpdate(new Date());
			adminAdjustment.setInvoiceDate(new Date());
			adminAdjustment.setDealHistory(quoteDO.getDealHistory());
			
			adminAdjustmentDAO.save(adminAdjustment);
			
			quote = quoteDAO.save(quote);
			
			if(quote != null && quote.getId() != null){
				logger.info("quoteId: "+quote.getId().getQuoteId()+" and id: "+quote.getId().getId());
				if(quote.getIsArchive() == 1){
					quoteDO.setStatusDesc(AggConstants.QUOTE_STATUS_ACRHIVE);
				}else if(quote.getStatus() == AggConstants.B_QUOTE_STATUS_ESTIMATING_PRICE){
					quoteDO.setStatusDesc(AggConstants.QUOTE_STATUS_ESTIMATING_PRICE);
				}else if(quote.getStatus() == AggConstants.B_QUOTE_STATUS_PURCHASE_REQUESTED){
					quoteDO.setStatusDesc(AggConstants.QUOTE_STATUS_PURCHASE_REQUESTED);
				}else if(quote.getStatus() == AggConstants.B_QUOTE_STATUS_INVOICED){
					quoteDO.setStatusDesc(AggConstants.QUOTE_STATUS_INVOICED);
				}else if(quote.getStatus() == AggConstants.B_QUOTE_STATUS_CLOSED){
					quoteDO.setStatusDesc(AggConstants.QUOTE_STATUS_CLOSED);
				}
				
				String email = null;
				if(accountDO.getRoleDO().getAccountType().equalsIgnoreCase(AggConstants.ACCOUNT_TYPE_ADMIN)){
					email = adminEmail;
					if(dealer != null && dealer.getInvoiceEmail() != null){
						if(email != null){
							email += "," + dealer.getInvoiceEmail();
						}else{
							email = dealer.getInvoiceEmail();
						}
					}
				}else{
					long dealerId = accountDO.getDealerId();
					if(dealerId > 0){
						Dealer dealerr = dealerDAO.findOne(dealerId);
						if(dealerr != null){
							email = dealerr.getInvoiceEmail();
							if(dealerr.getCode() != dealerr.getParentCode()){
								Dealer parentDealer = dealerDAO.findByCode(dealerr.getParentCode());
								if(parentDealer != null){
									if(email != null){
										email += "," + parentDealer.getInvoiceEmail();
									}else{
										email = parentDealer.getInvoiceEmail();
									}
								}
							}
						}
					}
				}
				
				Map<String, Object> parameterMap = new HashMap<String, Object>();
				//parameterMap.put("imagePath", appUrl+"/assets/images/report_banner.png");
				parameterMap.put("imagePath", System.getProperty("user.dir")+reportImagePath);
				
				List<ReportDO> reportDOList = new ArrayList<ReportDO>();
				quoteDO.setCreateDate(quote.getCreateDate());
				reportDOList.add(getInvoiceReportDO(quoteDO));
				JRDataSource jrDataSource = null;
				if(!reportDOList.isEmpty()){
					jrDataSource = new JRBeanCollectionDataSource(reportDOList);
				}else{
					jrDataSource = new JREmptyDataSource();
				}
				JasperReport jasperReport = JasperCompileManager.compileReport(resourceLoader.getResource("classpath:/jrxml/rpt_dealerInvoice.jrxml").getInputStream());
				JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameterMap, jrDataSource);
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				JasperExportManager.exportReportToPdfStream(jasperPrint, baos);
				DataSource pdfAttachment =  new ByteArrayDataSource(baos.toByteArray(), "application/pdf");
				String subject = "Dealer Invoice: "+quoteDO.getQuoteId()+" pdf";
				String emailBody = "PFA Dealer Invoice: "+quoteDO.getQuoteId()+" details.";
				EmailStatus emailStatus = emailSender.sendMailAsAttachment(email, subject, emailBody, pdfAttachment, "dealerInvoice-"+quoteDO.getQuoteId());
				if(emailStatus != null){
					logger.info("emailStatus: "+emailStatus.getStatus());
					logger.info("Dealer Quote pdf Attachment emailed successfully to "+email);
				}
				condition = true;
			}
			
		}
		return condition;
	}
	
	@Override
	public List<QuoteDO> getEstPriceQuotes(AccountDO accountDO) {
		List<QuoteDO> quoteDOList = null;
		if(accountDO.getRoleDO().getAccountType().equalsIgnoreCase(AggConstants.ACCOUNT_TYPE_ADMIN)){
			/*List<Quote> quoteList = Util.toList(quoteDAO.findByStatus(AggConstants.B_QUOTE_STATUS_ESTIMATING_PRICE));
			//logger.debug("quoteList for estPrice --------------> "+ quoteList.size());
			quoteDOList = getQuoteDetails(quoteList);*/
			quoteDOList = quoteDAO.findEstPriceQuotesByStatus(AggConstants.B_QUOTE_STATUS_ESTIMATING_PRICE, AggConstants.B_QUOTE_STATUS_UNACRHIVE);
		}else{
			/*List<Quote> quoteList = Util.toList(quoteDAO.findByStatusAndDealerId(AggConstants.B_QUOTE_STATUS_ESTIMATING_PRICE, accountDO.getDealerId()));
			//logger.debug("quoteList for estPrice --------------> "+ quoteList.size());
			quoteDOList = getQuoteDetails(quoteList);*/
			quoteDOList = quoteDAO.findEstPriceQuotesByStatusAndDealerId(AggConstants.B_QUOTE_STATUS_ESTIMATING_PRICE, accountDO.getDealerId(), AggConstants.B_QUOTE_STATUS_UNACRHIVE);
		}
		if(quoteDOList != null && !quoteDOList.isEmpty()){
			CustomerInfo customerInfo = null;
			for(QuoteDO quoteDO : quoteDOList){
				customerInfo = customerInfoDAO.findOne(quoteDO.getQuoteId());
				if(customerInfo != null){
					quoteDO.setDealerCustName(customerInfo.getName());
				}
				
				Quote quote = quoteDAO.findByIdQuoteId(quoteDO.getQuoteId());
				if(quote.getMachineInfo() != null){
					quoteDO.setMachineModel(quote.getMachineInfo().getModel());
				}
			}
		}
		return quoteDOList;
	}
	
	@Override
	public List<QuoteDO> getPurchaseReqQuotes(AccountDO accountDO) {
		List<QuoteDO> quoteDOList = null;
		if(accountDO.getRoleDO().getAccountType().equalsIgnoreCase(AggConstants.ACCOUNT_TYPE_ADMIN)){
			/*List<Quote> quoteList = Util.toList(quoteDAO.findByStatus(AggConstants.B_QUOTE_STATUS_PURCHASE_REQUESTED));
			//logger.debug("quoteList for purchaseReq--------------> "+ quoteList.size());
			quoteDOList = getQuoteDetails(quoteList);*/
			quoteDOList = quoteDAO.findByQuoteStatus(AggConstants.B_QUOTE_STATUS_PURCHASE_REQUESTED, AggConstants.B_QUOTE_STATUS_UNACRHIVE);
		}else{
			/*List<Quote> quoteList = Util.toList(quoteDAO.findByStatusAndDealerId(AggConstants.B_QUOTE_STATUS_PURCHASE_REQUESTED, accountDO.getDealerId()));
			//logger.debug("quoteList for purchaseReq --------------> "+ quoteList.size());
			quoteDOList = getQuoteDetails(quoteList);*/
			quoteDOList = quoteDAO.findByDealerStatusAndDealerId(AggConstants.B_QUOTE_STATUS_PURCHASE_REQUESTED, accountDO.getDealerId(), AggConstants.B_QUOTE_STATUS_UNACRHIVE);
		}
		return quoteDOList;
	}
	
	@Override
	public List<QuoteDO> getInvoicedQuotes(AccountDO accountDO) {
		List<QuoteDO> quoteDOList = null;
		if(accountDO.getRoleDO().getAccountType().equalsIgnoreCase(AggConstants.ACCOUNT_TYPE_ADMIN)){
			/*List<Quote> quoteList = Util.toList(quoteDAO.findByStatus(AggConstants.B_QUOTE_STATUS_INVOICED));
			//logger.debug("quoteList for getInvoicedQuotes--------------> "+ quoteList.size());
			quoteDOList = getQuoteDetails(quoteList);*/
			quoteDOList = quoteDAO.findByQuoteStatus(AggConstants.B_QUOTE_STATUS_INVOICED, AggConstants.B_QUOTE_STATUS_UNACRHIVE);
		}else{
			/*List<Quote> quoteList = Util.toList(quoteDAO.findByStatusAndDealerId(AggConstants.B_QUOTE_STATUS_INVOICED, accountDO.getDealerId()));
			//logger.debug("quoteList for getInvoicedQuotes--------------> "+ quoteList.size());
			quoteDOList = getQuoteDetails(quoteList);*/
			quoteDOList = quoteDAO.findByDealerStatusAndDealerId(AggConstants.B_QUOTE_STATUS_INVOICED, accountDO.getDealerId(), AggConstants.B_QUOTE_STATUS_UNACRHIVE);
		}
		return quoteDOList;
	}
	
	@Override
	public WorklistDO getWorklistCount(AccountDO accountDO)
	{
		WorklistDO worklistDO = new WorklistDO();
		long estPrice = 0; 
		long purchaseReq = 0;
		long invoiced = 0;
		long activeContract = 0;
		long inactiveContract = 0;
		long claims = 0;
		
		if(accountDO.getRoleDO().getAccountType().equalsIgnoreCase(AggConstants.ACCOUNT_TYPE_ADMIN)){
			estPrice = quoteDAO.countByEstPrice();
			purchaseReq = quoteDAO.countByPurRequested();
			invoiced = quoteDAO.countByInvoiced();
			
			//activeContract = contractsDAO.countByStatus(AggConstants.B_ACTIVE_CONTRACT);
			//inactiveContract = contractsDAO.countByStatus(AggConstants.B_INACTIVE_CONTRACT);
			
			activeContract = contractsDAO.countByActive();
			inactiveContract = contractsDAO.countByInactive();
			//claims = claimsDAO.count();
			claims = claimsDAO.findClaimsCountByAdmin((byte)AggConstants.CLAIM_STATUS_DRAFT);
			
		}else{
			estPrice = quoteDAO.countByEstPrice(accountDO.getDealerId());
			purchaseReq = quoteDAO.countByPurRequested(accountDO.getDealerId());
			invoiced = quoteDAO.countByInvoiced(accountDO.getDealerId());
			/*activeContract = contractsDAO.countByStatus(AggConstants.B_ACTIVE_CONTRACT, accountDO.getDealerId());
			inactiveContract = contractsDAO.countByStatus(AggConstants.B_INACTIVE_CONTRACT, accountDO.getDealerId());*/
			activeContract = contractsDAO.countActiveContractByDealer(accountDO.getDealerId());
			inactiveContract = contractsDAO.countInActiveContractByDealer(accountDO.getDealerId());
			claims = claimsDAO.findClaimsCountByDealer(new Long(accountDO.getDealerId()).intValue());
		}
				
		worklistDO.setEstPrice(estPrice);
		worklistDO.setPurchaseReq(purchaseReq);
		worklistDO.setInvoiced(invoiced);
		worklistDO.setActContracts(activeContract);
		worklistDO.setExpContracts(inactiveContract);
		worklistDO.setClaims(claims);
		
		logger.debug("claims -->"+claims);
		logger.debug("estPrice -->"+estPrice);
		return worklistDO;
	}
	
	private ReportDO getInvoiceReportDO(QuoteDO quoteDO) throws Exception{
		ReportDO reportDO = new ReportDO();
		
		//SimpleDateFormat reportDateFormat = new SimpleDateFormat("MM/dd/yyyy");
		Locale locale = new Locale("en", "US");
		NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(locale);
		
		reportDO.setDealerName(quoteDO.getDealerDO().getName());
		reportDO.setQuoteDate((quoteDO.getEstSaleDate() != null)?dateFormat.format(quoteDO.getEstSaleDate()):"");
		reportDO.setInvoiceDate(dateFormat.format(new Date()));
		//TODO with created_by field
		Dealer dealer = dealerDAO.findByCode(quoteDO.getDealerDO().getCode());
		if(dealer != null){
			if(dealer.getParentCode() != 0 && dealer.getParentCode() == dealer.getCode()){
				reportDO.setAttn(dealer.getName());
			}else{
				Dealer parentDealer = dealerDAO.findByCode(dealer.getCode());
				if(parentDealer != null){
					reportDO.setAttn(parentDealer.getName());
				}
			}
			
		}else{
			reportDO.setAttn("");
		};
		
		//TODO with purchase requested date
		Date createdDate = quoteDO.getCreateDate();
		if(createdDate != null){
			reportDO.setQuoteExpires(dateFormat.format(getQuoteExpirationDate(createdDate)));
		}else{
			reportDO.setQuoteExpires("");
		}
		reportDO.setQuoteId(quoteDO.getQuoteId());
		reportDO.setAddress(quoteDO.getDealerAddress()+", "+quoteDO.getDealerState()+" "+quoteDO.getDealerZip());
		reportDO.setCustName(quoteDO.getDealerName());
		reportDO.setOutStandingDesc(AggConstants.INVOICE_REPORT_OUT_STANDING_DESC);
		reportDO.setManufacturerName(quoteDO.getManufacturerDO().getName());
		reportDO.setModelName(quoteDO.getMachineInfoDO().getModel());
		reportDO.setModelSerialNo(quoteDO.getSerialNumber());
		reportDO.setEquipment(quoteDO.getUseOfEquipmentDO().getEquipName());
		reportDO.setRetailPrice(currencyFormat.format(quoteDO.getRetailPrice()));
		reportDO.setCurrentHours(quoteDO.getMeterHours()+"");
		reportDO.setMachineStatus(quoteDO.getMachineCondition());
		reportDO.setEmail(quoteDO.getDealerEmail());
		reportDO.setPhone(quoteDO.getDealerPhone());
		reportDO.setCoverageDesc(AggConstants.QUOTE_REPORT_COVERAGE_DESC);
		reportDO.setCoverageTerm(quoteDO.getCoverageTerm());
		reportDO.setCoverageHours(quoteDO.getCoverageHours());
		reportDO.setDeductibleAmount(currencyFormat.format(quoteDO.getDeductiblePrice()));
		reportDO.setCoverageType(quoteDO.getCoverageTypeDesc());
		reportDO.setPowerTrainMonths(quoteDO.getPowerTrainMonths());
		reportDO.setPowerTrainHours(quoteDO.getPowerTrainHours());
		reportDO.setHydraulicMonths(quoteDO.getHydraulicsMonths());
		reportDO.setHydraulicHours(quoteDO.getHydraulicsHours());
		reportDO.setFullMachineHours(quoteDO.getFullMachineHours());
		reportDO.setFullMachineMonths(quoteDO.getFullMachineMonths());
		reportDO.setWarrantyEndDate((quoteDO.getCoverageEndDate() != null)?dateFormat.format(quoteDO.getCoverageEndDate()):"");
		reportDO.setLimitOfLiability(currencyFormat.format(quoteDO.getMachineInfoDO().getLol()));
		reportDO.setPrice(currencyFormat.format((quoteDO.getQuoteBasePrice()+quoteDO.getDealerMarkupPrice())));
		reportDO.setQuotePrice(currencyFormat.format(quoteDO.getQuoteBasePrice()));
		reportDO.setDealerMarkup(currencyFormat.format(quoteDO.getDealerMarkupPrice()));
		reportDO.setAmountDue(currencyFormat.format(quoteDO.getQuoteBasePrice()));
		reportDO.setSpecialConsiderationDesc("None");
		
		return reportDO;
	}

	@Override
	@Transactional
	public boolean createContract(QuoteDO quoteDO, AccountDO accountDO, String appUrl) throws Exception{
		boolean condition = false;
		
		AdminAdjustment adminAdjustment = adminAdjustmentDAO.findOne(quoteDO.getQuoteId());
		if(adminAdjustment != null){
			adminAdjustment.setBasePrice(quoteDO.getAdjustedBasePrice());
			adminAdjustment.setLol(quoteDO.getAdjustedLol());
			adminAdjustment.setSpecialConsideration(quoteDO.getSpecialConsiderations());
			adminAdjustment.setCConditions(quoteDO.getCondsForCoverage());
			adminAdjustment.setLastUpdate(new Date());
			adminAdjustment.setInceptionDate(quoteDO.getInceptionDate());
			adminAdjustment.setExpirationDate(quoteDO.getExpirationDate());
			adminAdjustment.setExpirationHours(quoteDO.getExpirationHours());
			
			adminAdjustmentDAO.save(adminAdjustment);
		}
		
		Contracts contracts = new Contracts();
		contracts.setAvailabeLol(quoteDO.getMachineInfoDO().getLol());
		contracts.setComments(quoteDO.getComments());
		contracts.setContractId("CR-"+quoteDO.getQuoteId());
		contracts.setCoverageLevelHours(quoteDO.getCoverageHours());
		contracts.setCoverageTermMonths(quoteDO.getCoverageTerm());
		contracts.setCoverageType(quoteDO.getCoverageType());
		contracts.setDeductible(quoteDO.getDeductiblePrice());
		contracts.setExpirationDate(quoteDO.getExpirationDate());
		contracts.setExpirationUsageHours(quoteDO.getExpirationHours());
		contracts.setInceptionDate(quoteDO.getInceptionDate());
		contracts.setLastUpdatedDate(new Date());
		contracts.setLol(quoteDO.getMachineInfoDO().getLol());
		contracts.setMachineSerialNo(quoteDO.getSerialNumber());
		Quote quote = quoteDAO.findByIdQuoteId(quoteDO.getQuoteId());
		contracts.setQuoteId(quote.getId().getId());
		contracts.setStatus(1);
		contracts.setCheqNo(quoteDO.getCheqNo());
		contracts.setReceivedDate(quoteDO.getReceivedDate());
		
		contracts = contractsDAO.save(contracts);
		
		quote.setStatus(AggConstants.B_QUOTE_STATUS_CLOSED);
		quoteDAO.save(quote);
		
		//TODO send Active Contract mail 
		
		condition = true;
		
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		//parameterMap.put("imagePath", appUrl+"/assets/images/report_banner.png");
		parameterMap.put("imagePath", System.getProperty("user.dir")+reportImagePath);
		parameterMap.put("styleSheetPath", System.getProperty("user.dir")+jrxmlFolderPath+"aggStyles.jrtx");
		
		JRDataSource jrDataSource = null;
		DataSource[] pdfAttachments = new DataSource[2];
		List<ContractReportDO> reportDOList = null;
		JasperReport jasperReport = null;
		JasperPrint jasperPrint = null;
		ByteArrayOutputStream baos = null;
		DataSource pdfAttachment = null;
		
		reportDOList = new ArrayList<ContractReportDO>();
		reportDOList.add(getContractReportDO(quoteDO));
		if(!reportDOList.isEmpty()){
			jrDataSource = new JRBeanCollectionDataSource(reportDOList);
		}else{
			jrDataSource = new JREmptyDataSource();
		}
		jasperReport = JasperCompileManager.compileReport(resourceLoader.getResource("classpath:/jrxml/rpt_contractDetails.jrxml").getInputStream());
		jasperPrint = JasperFillManager.fillReport(jasperReport, parameterMap, jrDataSource);
		baos = new ByteArrayOutputStream();
		JasperExportManager.exportReportToPdfStream(jasperPrint, baos);
		pdfAttachment =  new ByteArrayDataSource(baos.toByteArray(), "application/pdf");
		pdfAttachments[0] = pdfAttachment;
		
		
		if(!reportDOList.isEmpty()){
			jrDataSource = new JRBeanCollectionDataSource(reportDOList);
		}else{
			jrDataSource = new JREmptyDataSource();
		}
		jasperReport = JasperCompileManager.compileReport(resourceLoader.getResource("classpath:/jrxml/rpt_coverageDetails.jrxml").getInputStream());
		jasperPrint = JasperFillManager.fillReport(jasperReport, parameterMap, jrDataSource);
		baos = new ByteArrayOutputStream();
		JasperExportManager.exportReportToPdfStream(jasperPrint, baos);
		pdfAttachment =  new ByteArrayDataSource(baos.toByteArray(), "application/pdf");
		pdfAttachments[1] = pdfAttachment;
		
		String[] fileNames = {"ContractDetails-"+contracts.getContractId(),"CoverageDetails-"+contracts.getContractId()};
		
		
		String subject = "Contract Details: "+contracts.getContractId()+" pdf files";
		String emailBody = "PFA Contract Details: "+contracts.getContractId()+" details.";
		String emails = "";
		if(quoteDO.getDealerEmail() != null && !quoteDO.getDealerEmail().isEmpty()){
			emails = quoteDO.getDealerEmail()+","+quoteDO.getDealerDO().getInvoiceEmail();
		}else{
			emails = quoteDO.getDealerDO().getInvoiceEmail();
		}
		EmailStatus emailStatus = emailSender.sendMailAsAttachment(emails, subject, emailBody, pdfAttachments, fileNames);
		if(emailStatus != null){
			logger.info("emailStatus: "+emailStatus.getStatus());
			logger.info("Contract pdf Attachment files emailed successfully to "+quoteDO.getDealerEmail()+" & "+quoteDO.getDealerDO().getInvoiceEmail());
		}
		
		return condition;
	}

	private ContractReportDO getContractReportDO(QuoteDO quoteDO) throws Exception{
		ContractReportDO reportDO = new ContractReportDO();
		
		Locale locale = new Locale("en", "US");
		NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(locale);
		
		reportDO.setContractId("CR-"+quoteDO.getQuoteId());
		reportDO.setInceptionDate((quoteDO.getInceptionDate() != null)?dateFormat.format(quoteDO.getInceptionDate()):"");
		reportDO.setCoverageTerm(quoteDO.getCoverageTerm());
		reportDO.setCoverageHours(quoteDO.getCoverageHours());
		reportDO.setExpirationDate((quoteDO.getExpirationDate() != null)?dateFormat.format(quoteDO.getExpirationDate()):"");
		reportDO.setDeductibleAmount(currencyFormat.format(quoteDO.getDeductAmount()));
		reportDO.setExpirationHours(quoteDO.getExpirationHours()+"");
		reportDO.setCoverageType(quoteDO.getCoverageType());
		reportDO.setLol(currencyFormat.format(quoteDO.getAdjustedLol()));
		reportDO.setManufacturer(quoteDO.getManufacturerDO().getName());
		reportDO.setSerialNo(quoteDO.getSerialNumber());
		reportDO.setMachineModel(quoteDO.getMachineInfoDO().getModel());
		reportDO.setManfEndDate((quoteDO.getCoverageEndDate() != null)?dateFormat.format(quoteDO.getCoverageEndDate()):"");
		reportDO.setUseOfEquipment(quoteDO.getUseOfEquipmentDO().getEquipName());
		reportDO.setMachineHours(quoteDO.getMeterHours());
		reportDO.setCustomerAddress1(quoteDO.getDealerAddress());
		reportDO.setCustomerAddress2(quoteDO.getDealerCity()+" "+quoteDO.getDealerState());
		reportDO.setCustomerAddress3(quoteDO.getDealerZip());
		reportDO.setCustomerContact(quoteDO.getDealerName());
		reportDO.setCustomerPhone(quoteDO.getDealerPhone());
		reportDO.setCustomerEmail(quoteDO.getDealerEmail());
		reportDO.setDealerAddress1(quoteDO.getDealerDO().getAddress1());
		reportDO.setDealerAddress2(quoteDO.getDealerDO().getAddress2());
		reportDO.setDealerAddress3(quoteDO.getDealerCity()+" "+quoteDO.getDealerState()+" "+quoteDO.getDealerZip());
		reportDO.setDealerContact(quoteDO.getDealerDO().getName());
		reportDO.setDealerEmail(quoteDO.getDealerDO().getInvoiceEmail());
		reportDO.setDealerPhone(quoteDO.getDealerDO().getPhone());
		reportDO.setServiceProviderAddr1(quoteDO.getDealerDO().getAddress1());
		reportDO.setServiceProviderAddr2(quoteDO.getDealerDO().getAddress2());
		reportDO.setServiceProviderAddr3(quoteDO.getDealerCity()+" "+quoteDO.getDealerState()+" "+quoteDO.getDealerZip());
		reportDO.setServiceProviderContact(quoteDO.getDealerDO().getName());
		reportDO.setServiceProviderEmail(quoteDO.getDealerDO().getInvoiceEmail());
		reportDO.setServiceProviderPhone(quoteDO.getDealerDO().getPhone());
		reportDO.setSpecialConsiderations(quoteDO.getSpecialConsiderations());
		
		return reportDO;
	}
	
	/**
	 * @param createdDate
	 * @return quoteDO
	 */
	private Date getQuoteExpirationDate(Date createdDate){
		Calendar cal = Calendar.getInstance();
		cal.setTime(createdDate);
		cal.add(Calendar.DATE, AggConstants.QUOTE_EXPIRATION_DAYS);
		return cal.getTime();
	}

}

