package com.agg.application.service.impl;

import java.math.BigInteger;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ResourceLoader;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
import com.agg.application.entity.Check;
import com.agg.application.entity.Contracts;
import com.agg.application.entity.CustomerInfo;
import com.agg.application.entity.Dealer;
import com.agg.application.entity.MachineInfo;
import com.agg.application.entity.Manufacturer;
import com.agg.application.entity.Quote;
import com.agg.application.entity.QuotePK;
import com.agg.application.entity.UseOfEquip;
import com.agg.application.model.AccountDO;
import com.agg.application.model.CheckDO;
import com.agg.application.model.DealerDO;
import com.agg.application.model.MachineInfoDO;
import com.agg.application.model.ManufacturerDO;
import com.agg.application.model.PricingDO;
import com.agg.application.model.QuoteDO;
import com.agg.application.model.ReportDO;
import com.agg.application.model.UseOfEquipmentDO;
import com.agg.application.model.WorklistDO;
import com.agg.application.service.EmailService;
import com.agg.application.service.QuoteService;
import com.agg.application.utils.AggConstants;
import com.agg.application.utils.EmailSender;
import com.agg.application.utils.Util;
import com.google.common.collect.Lists;

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
	
	@Autowired
	private EmailService emailService;
	
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
			
			//updating quote summary additional info.
			quote.setOtherProv(quoteDO.getOtherProv());
			
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
			quote.setOtherProv(quoteDO.getOtherProv());
			
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
				
				quoteDO.setCreateDate(quote.getCreateDate());
				
				emailService.sendAsyncPurchaseRequestEmail(quoteDO, quote);
			}
		}
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
					reportDO.setDealerAddress(dealer.getAddress() + ", "
							+ ((dealer.getCity() != null && !dealer.getCity().isEmpty()) ? dealer.getCity() + ", " : "")
							+ dealer.getState() + " " + dealer.getZip());
				}else{
					Dealer parentDealer = dealerDAO.findByCode(dealer.getCode());
					if(parentDealer != null){
						reportDO.setAttn(parentDealer.getName());
						reportDO.setDealerAddress(parentDealer.getAddress() + ", "
								+ ((parentDealer.getCity() != null && !parentDealer.getCity().isEmpty())
										? parentDealer.getCity() + ", " : "")
								+ parentDealer.getState() + " " + parentDealer.getZip());
					}
				}
				
			}else{
				reportDO.setAttn("");
				reportDO.setDealerAddress("");
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
				reportDO.setAddress(customerInfo.getAddress() + ", "
						+ ((customerInfo.getCity() != null && !customerInfo.getCity().isEmpty())
								? customerInfo.getCity() + ", " : "")
						+ customerInfo.getState() + " " + customerInfo.getZip());
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
			reportDO.setDeductibleAmount(currencyFormat.format(quote.getDeductAmount()));
			reportDO.setPowerTrainMonths(quote.getPtMonths());
			reportDO.setPowerTrainHours(quote.getPtHours());
			reportDO.setHydraulicMonths(quote.getHMonths());
			reportDO.setHydraulicHours(quote.getHHours());
			reportDO.setFullMachineHours(quote.getMachineHours());
			reportDO.setFullMachineMonths(quote.getMachineMonths());
			reportDO.setWarrantyEndDate((quote.getManfEndDate() != null)?dateFormat.format(quote.getManfEndDate()):"Unknown");
			double lol = quote.getMachineInfo().getGroupConstant().getLol();
			double coveragePrice = quote.getCoveragePrice();
			String coverageType = quote.getCoverageType();
			int coverageTerm = quote.getCoverageTerm();
			int coverageHours = quote.getCoverageLevelHours();
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
				
				if(adminAdjustment.getCoverageType() != null && !adminAdjustment.getCoverageType().isEmpty()){
					coverageType = adminAdjustment.getCoverageType();
				}
				
				if(adminAdjustment.getCoverageTerm() > 0){
					coverageTerm = adminAdjustment.getCoverageTerm();
				}
				
				if(adminAdjustment.getCoverageHours() > 0){
					coverageHours = adminAdjustment.getCoverageHours();
				}
				
				if(adminAdjustment.getInceptionDate() != null){
					reportDO.setInceptionDate(dateFormat.format(adminAdjustment.getInceptionDate()));
				}else{
					reportDO.setInceptionDate(dateFormat.format(new Date()));
				}
				
				if(adminAdjustment.getExpirationDate() != null){
					reportDO.setExpirationDate(dateFormat.format(adminAdjustment.getExpirationDate()));
				}else{
					if(quote.getManfExpired() == 1){
						Calendar cal = Calendar.getInstance();
						if(adminAdjustment.getInceptionDate() != null){
							cal.setTime(adminAdjustment.getInceptionDate());
						}else{
							cal.setTime(new Date());
						}
						cal.add(Calendar.MONTH, coverageTerm);
						reportDO.setExpirationDate(dateFormat.format(cal.getTime()));
					}else{
						int manfCoverageTerm = 0;
						if(quote.getCoverageType() != null){
							if(quote.getCoverageType().equalsIgnoreCase("PT")){
								manfCoverageTerm = quote.getPtMonths();
							}else if(quote.getCoverageType().equalsIgnoreCase("PH")){
								manfCoverageTerm = quote.getHMonths();
							}else if(quote.getCoverageType().equalsIgnoreCase("PL")){
								manfCoverageTerm = quote.getMachineMonths();
							}
							int finalCoverageTerm = coverageTerm - manfCoverageTerm;
							if(quote.getManfEndDate() != null){
								Calendar cal = Calendar.getInstance();
								cal.setTime(quote.getManfEndDate());
								cal.add(Calendar.MONTH, finalCoverageTerm);
								reportDO.setExpirationDate(dateFormat.format(cal.getTime()));
							}
						}
					}
				}
				
				if(adminAdjustment.getExpirationHours() > 0){
					reportDO.setExpirationHours(adminAdjustment.getExpirationHours());
				}else{
					if(quote.getManfExpired() == 1){
						reportDO.setExpirationHours(coverageHours+quote.getMachineMeterHours());
					}else{
						reportDO.setExpirationHours(coverageHours);
					}
				}
				reportDO.setSpecialConsiderationDesc((adminAdjustment.getSpecialConsideration() != null
						&& !adminAdjustment.getSpecialConsideration().isEmpty())
								? adminAdjustment.getSpecialConsideration() : "None");
			}else{
				reportDO.setSpecialConsiderationDesc("None");
			}
			if(coverageType != null && !coverageType.isEmpty()){
				if(coverageType.equalsIgnoreCase("PT")){
					reportDO.setCoverageType("Powertrain");
				}else if(coverageType.equalsIgnoreCase("PH")){
					reportDO.setCoverageType("Powertrain + Hydraulic");
				}else if(coverageType.equalsIgnoreCase("PL")){
					reportDO.setCoverageType("Powertrain + Hydraulic + Platform");
				}
			}
			reportDO.setCoverageTerm(coverageTerm);
			reportDO.setCoverageHours(coverageHours);
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
	private List<QuoteDO> getQuoteDetails(List<Quote> quoteList, AccountDO accountDO){
		List<QuoteDO> quoteDOList = null;
		if(quoteList != null && !quoteList.isEmpty()){
			quoteDOList = new ArrayList<QuoteDO>();
			for(Quote quote : quoteList){
				quoteDOList.add(getQuoteDetails(quote, accountDO));
			}
		}
		
		return quoteDOList;
	}

	/**
	 * @param quote
	 * @return
	 */
	private QuoteDO getQuoteDetails(Quote quote, AccountDO accountDO) {
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
			if(quote.getManfEndDate() != null){
				quoteDO.setCoverageEndDate(new java.sql.Timestamp(quote.getManfEndDate().getTime()));
			}
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
				machineInfoDO.setModelYear((machineInfo.getModelYear() != null)?machineInfo.getModelYear():0);
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
			if(quote.getMachineSaleDate() != null){
				quoteDO.setEstSaleDate(new java.sql.Timestamp(quote.getMachineSaleDate().getTime()));
			}
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
			if(adminAdjustment != null){
				if(adminAdjustment.getBasePrice() > 0){
					quoteDO.setQuoteBasePrice(adminAdjustment.getBasePrice());
				}
				
				if(adminAdjustment.getCoverageTerm() > 0){
					quoteDO.setAdjustedcoverageTerm(adminAdjustment.getCoverageTerm());
				}else{
					quoteDO.setAdjustedcoverageTerm(quote.getCoverageTerm());
				}
				
				if(adminAdjustment.getCoverageHours() > 0){
					quoteDO.setAdjustedCoverageHours(adminAdjustment.getCoverageHours());
				}else{
					quoteDO.setAdjustedCoverageHours(quote.getCoverageLevelHours());
				}
				
				if(adminAdjustment.getCoverageType() != null && !adminAdjustment.getCoverageType().isEmpty()){
					quoteDO.setAdjustedCoverageType(adminAdjustment.getCoverageType());
				}else{
					quoteDO.setAdjustedCoverageType(quote.getCoverageType());
				}
			}else{
				quoteDO.setAdjustedcoverageTerm(quote.getCoverageTerm());
				quoteDO.setAdjustedCoverageHours(quote.getCoverageLevelHours());
				quoteDO.setAdjustedCoverageType(quote.getCoverageType());
				quoteDO.setInceptionDate(new java.sql.Timestamp(new Date().getTime()));
				
				int coverageTerm = quote.getCoverageTerm();
				int coverageHours = quote.getCoverageLevelHours();
				
				if(quote.getManfExpired() == 1){
					Calendar cal = Calendar.getInstance();
					cal.setTime(new Date());
					cal.add(Calendar.MONTH, coverageTerm);
					quoteDO.setExpirationDate(new java.sql.Timestamp(cal.getTime().getTime()));
				}else{
					int manfCoverageTerm = 0;
					if(quote.getCoverageType() != null){
						if(quote.getCoverageType().equalsIgnoreCase("PT")){
							manfCoverageTerm = quote.getPtMonths();
						}else if(quote.getCoverageType().equalsIgnoreCase("PH")){
							manfCoverageTerm = quote.getHMonths();
						}else if(quote.getCoverageType().equalsIgnoreCase("PL")){
							manfCoverageTerm = quote.getMachineMonths();
						}
						int finalCoverageTerm = coverageTerm - manfCoverageTerm;
						if(quote.getManfEndDate() != null){
							Calendar cal = Calendar.getInstance();
							cal.setTime(quote.getManfEndDate());
							cal.add(Calendar.MONTH, finalCoverageTerm);
							quoteDO.setExpirationDate(new java.sql.Timestamp(cal.getTime().getTime()));
						}
					}
				}
				
				if(quote.getManfExpired() == 1){
					quoteDO.setExpirationHours(coverageHours+quote.getMachineMeterHours());
				}else{
					quoteDO.setExpirationHours(coverageHours);
				}
			}
			quoteDO.setStatus(quote.getStatus());
			quoteDO.setIsArchive(quote.getIsArchive());
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
				
				int coverageTerm = quote.getCoverageTerm();
				int coverageHours = quote.getCoverageLevelHours();
				
				
				if(adminAdjustment.getCoverageTerm() > 0){
					coverageTerm = adminAdjustment.getCoverageTerm();
				}
				
				if(adminAdjustment.getCoverageHours() > 0){
					coverageHours = adminAdjustment.getCoverageHours();
				}
				
				if(adminAdjustment.getInceptionDate() != null){
					quoteDO.setInceptionDate(new java.sql.Timestamp(adminAdjustment.getInceptionDate().getTime()));
				}else{
					quoteDO.setInceptionDate(new java.sql.Timestamp(new Date().getTime()));
				}
				
				if(adminAdjustment.getExpirationDate() != null){
					quoteDO.setExpirationDate(new java.sql.Timestamp(adminAdjustment.getExpirationDate().getTime()));
				}else{
					if(quote.getManfExpired() == 1){
						Calendar cal = Calendar.getInstance();
						if(adminAdjustment.getInceptionDate() != null){
							cal.setTime(adminAdjustment.getInceptionDate());
						}else{
							cal.setTime(new Date());
						}
						cal.add(Calendar.MONTH, coverageTerm);
						quoteDO.setExpirationDate(new java.sql.Timestamp(cal.getTime().getTime()));
					}else{
						int manfCoverageTerm = 0;
						if(quote.getCoverageType() != null){
							if(quote.getCoverageType().equalsIgnoreCase("PT")){
								manfCoverageTerm = quote.getPtMonths();
							}else if(quote.getCoverageType().equalsIgnoreCase("PH")){
								manfCoverageTerm = quote.getHMonths();
							}else if(quote.getCoverageType().equalsIgnoreCase("PL")){
								manfCoverageTerm = quote.getMachineMonths();
							}
							int finalCoverageTerm = coverageTerm - manfCoverageTerm;
							if(quote.getManfEndDate() != null){
								Calendar cal = Calendar.getInstance();
								cal.setTime(quote.getManfEndDate());
								cal.add(Calendar.MONTH, finalCoverageTerm);
								quoteDO.setExpirationDate(new java.sql.Timestamp(cal.getTime().getTime()));
							}
						}
					}
				}
				
				if(adminAdjustment.getExpirationHours() > 0){
					quoteDO.setExpirationHours(adminAdjustment.getExpirationHours());
				}else{
					if(quote.getManfExpired() == 1){
						quoteDO.setExpirationHours(coverageHours+quote.getMachineMeterHours());
					}else{
						quoteDO.setExpirationHours(coverageHours);
					}
				}
				
				quoteDO.setSpecialConsiderations(adminAdjustment.getSpecialConsideration());
				quoteDO.setCondsForCoverage(adminAdjustment.getCConditions());
				/*quoteDO.setInceptionDate(adminAdjustment.getInceptionDate());
				quoteDO.setExpirationDate(adminAdjustment.getExpirationDate());
				quoteDO.setExpirationHours(adminAdjustment.getExpirationHours());*/
				quoteDO.setDealHistory(adminAdjustment.getDealHistory());
			}
			
			if(quote.getCoverageLevelHours() > 0){
				//boolean coverageExpired = true;
				boolean coverageExpired = false;
				if(quoteDO.isCoverageExpired()){
					coverageExpired = true;
		    	}/*else if(quoteDO.getCoverageEndDate() != null && quoteDO.getCoverageEndDate().after(new Date())){
		    		coverageExpired = false;
		    	}*/
				
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
			
			if(accountDO != null && accountDO.getRoleDO().getAccountType().equalsIgnoreCase(AggConstants.ACCOUNT_TYPE_ADMIN)){
				quoteDO.setAdmin(true);
			}
			
		}
		
		return quoteDO;
	}

	@Override
	public QuoteDO getQuote(AccountDO accountDetails, int id, String quoteId) {
		QuoteDO quoteDO = null;
		if(id > 0 && quoteId != null && !quoteId.isEmpty()){
			QuotePK quotePK = new QuotePK(quoteId, id);
			quoteDO = getQuoteDetails(quoteDAO.findOne(quotePK), accountDetails);
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
	public boolean updateQuote(QuoteDO quoteDO, AccountDO accountDO, String appUrl) throws Exception{
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
			
			//boolean coverageExpired = true;
			boolean coverageExpired = false;
			if(quoteDO.isCoverageExpired()){
				coverageExpired = true;
	    	}/*else if(quoteDO.getCoverageEndDate() != null && quoteDO.getCoverageEndDate().after(new Date())){
	    		coverageExpired = false;
	    	}*/
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
			
			//if(quoteDO.getStatus() > quote.getStatus()){
				quote.setStatus(quoteDO.getStatus());
			//}
			
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
			adminAdjustment.setSpecialConsideration(quoteDO.getSpecialConsiderations());
			adminAdjustment.setCConditions(quoteDO.getCondsForCoverage());
			adminAdjustment.setDealHistory(quoteDO.getDealHistory());
			adminAdjustment.setLastUpdate(new Date());
			if(accountDO.getRoleDO().getAccountType().equalsIgnoreCase(AggConstants.ACCOUNT_TYPE_ADMIN)){
				adminAdjustment.setBasePrice(quoteDO.getAdjustedBasePrice());
				adminAdjustment.setLol(quoteDO.getAdjustedLol());
				adminAdjustment.setCoverageTerm(quoteDO.getAdjustedcoverageTerm());
				adminAdjustment.setCoverageHours(quoteDO.getAdjustedCoverageHours());
				adminAdjustment.setCoverageType(quoteDO.getAdjustedCoverageType());
				if(quoteDO.getStatus() > AggConstants.B_QUOTE_STATUS_ESTIMATING_PRICE){
					adminAdjustment.setInceptionDate(quoteDO.getInceptionDate());
					adminAdjustment.setExpirationDate(quoteDO.getExpirationDate());
					adminAdjustment.setExpirationHours(quoteDO.getExpirationHours());
				}
			}
			if(quoteDO.getStatus() == AggConstants.B_QUOTE_STATUS_INVOICED){
				if(adminAdjustment.getInvoiceDate() == null){
					adminAdjustment.setInvoiceDate(new Date());
				}
			}
			
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
				
				
				if(quoteDO.getStatus() == AggConstants.B_QUOTE_STATUS_PURCHASE_REQUESTED){
					emailService.sendAsyncPurchaseRequestEmail(quoteDO, quote);
				}else if(quoteDO.getStatus() == AggConstants.B_QUOTE_STATUS_INVOICED){
					emailService.sendAsyncInvoiceEmail(quoteDO, quote, accountDO, dealer, appUrl);
				}
				
				condition = true;
			}
			
		}
		
		logger.debug("Exit of updateQuote method with condition: "+condition);
		
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
			
			//boolean coverageExpired = true;
			boolean coverageExpired = false;
			if(quoteDO.isCoverageExpired()){
				coverageExpired = true;
	    	}/*else if(quoteDO.getCoverageEndDate() != null && quoteDO.getCoverageEndDate().after(new Date())){
	    		coverageExpired = false;
	    	}*/
			
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
			adminAdjustment.setSpecialConsideration(quoteDO.getSpecialConsiderations());
			adminAdjustment.setCConditions(quoteDO.getCondsForCoverage());
			adminAdjustment.setLastUpdate(new Date());
			adminAdjustment.setInvoiceDate(new Date());
			adminAdjustment.setDealHistory(quoteDO.getDealHistory());
			adminAdjustment.setBasePrice(quoteDO.getAdjustedBasePrice());
			adminAdjustment.setLol(quoteDO.getAdjustedLol());
			adminAdjustment.setCoverageTerm(quoteDO.getAdjustedcoverageTerm());
			adminAdjustment.setCoverageHours(quoteDO.getAdjustedCoverageHours());
			adminAdjustment.setCoverageType(quoteDO.getAdjustedCoverageType());
			adminAdjustment.setInceptionDate(quoteDO.getInceptionDate());
			adminAdjustment.setExpirationDate(quoteDO.getExpirationDate());
			adminAdjustment.setExpirationHours(quoteDO.getExpirationHours());
			
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
				
				quoteDO.setCreateDate(quote.getCreateDate());
				
				emailService.sendAsyncInvoiceEmail(quoteDO, quote, accountDO, dealer, appUrl);
				
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
			//quoteDOList = quoteDAO.findByQuoteStatus(AggConstants.B_QUOTE_STATUS_INVOICED, AggConstants.B_QUOTE_STATUS_UNACRHIVE);
			quoteDOList = quoteDAO.findInvoiceQuotes(AggConstants.B_QUOTE_STATUS_INVOICED, AggConstants.B_QUOTE_STATUS_UNACRHIVE);
		}else{
			/*List<Quote> quoteList = Util.toList(quoteDAO.findByStatusAndDealerId(AggConstants.B_QUOTE_STATUS_INVOICED, accountDO.getDealerId()));
			//logger.debug("quoteList for getInvoicedQuotes--------------> "+ quoteList.size());
			quoteDOList = getQuoteDetails(quoteList);*/
			//quoteDOList = quoteDAO.findByDealerStatusAndDealerId(AggConstants.B_QUOTE_STATUS_INVOICED, accountDO.getDealerId(), AggConstants.B_QUOTE_STATUS_UNACRHIVE);
			quoteDOList = quoteDAO.findInvoiceQuotesByDealer(AggConstants.B_QUOTE_STATUS_INVOICED, AggConstants.B_QUOTE_STATUS_UNACRHIVE, accountDO.getDealerId());
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
			/*estPrice = quoteDAO.countByEstPrice();
			purchaseReq = quoteDAO.countByPurRequested();
			invoiced = quoteDAO.countByInvoiced();*/
			
			estPrice = quoteDAO.countByStatus(AggConstants.B_QUOTE_STATUS_ESTIMATING_PRICE, AggConstants.B_QUOTE_STATUS_UNACRHIVE);
			purchaseReq = quoteDAO.countByStatus(AggConstants.B_QUOTE_STATUS_PURCHASE_REQUESTED, AggConstants.B_QUOTE_STATUS_UNACRHIVE);
			invoiced = quoteDAO.countByStatus(AggConstants.B_QUOTE_STATUS_INVOICED, AggConstants.B_QUOTE_STATUS_UNACRHIVE);
			
			
			
			//activeContract = contractsDAO.countByStatus(AggConstants.B_ACTIVE_CONTRACT);
			//inactiveContract = contractsDAO.countByStatus(AggConstants.B_INACTIVE_CONTRACT);
			
			activeContract = contractsDAO.countByActive();
			inactiveContract = contractsDAO.countByInactive();
			//claims = claimsDAO.count();
			claims = claimsDAO.findClaimsCountByAdmin((byte)AggConstants.CLAIM_STATUS_DRAFT);
			
		}else{
			/*estPrice = quoteDAO.countByEstPrice(accountDO.getDealerId());
			purchaseReq = quoteDAO.countByPurRequested(accountDO.getDealerId());
			invoiced = quoteDAO.countByInvoiced(accountDO.getDealerId());*/
			
			estPrice = quoteDAO.countByStatus(AggConstants.B_QUOTE_STATUS_ESTIMATING_PRICE, AggConstants.B_QUOTE_STATUS_UNACRHIVE, accountDO.getDealerId());
			purchaseReq = quoteDAO.countByStatus(AggConstants.B_QUOTE_STATUS_PURCHASE_REQUESTED, AggConstants.B_QUOTE_STATUS_UNACRHIVE, accountDO.getDealerId());
			invoiced = quoteDAO.countByStatus(AggConstants.B_QUOTE_STATUS_INVOICED, AggConstants.B_QUOTE_STATUS_UNACRHIVE, accountDO.getDealerId());
			
			
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

	@Override
	@Transactional
	public boolean createContract(QuoteDO quoteDO, AccountDO accountDO, String appUrl) throws Exception{
		boolean condition = false;
		
		/*AdminAdjustment adminAdjustment = adminAdjustmentDAO.findOne(quoteDO.getQuoteId());
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
		}*/
		
		Date currDate = new Date();
		
		Contracts contracts = new Contracts();
		contracts.setAvailabeLol(quoteDO.getMachineInfoDO().getLol());
		contracts.setComments(quoteDO.getComments());
		contracts.setContractId("CR-"+quoteDO.getQuoteId());
		contracts.setCoverageLevelHours(quoteDO.getCoverageHours());
		contracts.setCoverageTermMonths(quoteDO.getCoverageTerm());
		contracts.setCoverageType(quoteDO.getCoverageType());
		contracts.setDeductible(quoteDO.getDeductiblePrice());
		contracts.setExpirationDate(quoteDO.getContractExpirationDate());
		contracts.setExpirationUsageHours(quoteDO.getContractExpirationHours());
		contracts.setInceptionDate(quoteDO.getContractInceptionDate());
		contracts.setLastUpdatedDate(currDate);
		contracts.setLol(quoteDO.getMachineInfoDO().getLol());
		contracts.setMachineSerialNo(quoteDO.getSerialNumber());
		Quote quote = quoteDAO.findByIdQuoteId(quoteDO.getQuoteId());
		contracts.setQuoteId(quote.getId().getId());
		contracts.setStatus(AggConstants.ACTIVE);
		contracts.setCheqNo(quoteDO.getCheqNo());
		contracts.setReceivedDate(quoteDO.getReceivedDate());
		contracts.setServicingDealer(quote.getDealer());
		
		List<CheckDO> checkDOList = quoteDO.getCheckDOList();
		if(checkDOList != null && !checkDOList.isEmpty()){
			Check check = null;
			Set<Check> checks = new HashSet<Check>();
			for(CheckDO checkDO : checkDOList){
				check = new Check();
				check.setCheckNo(checkDO.getCheckNo());
				check.setReceivedDate(checkDO.getReceivedDate());
				check.setCheckAmount(checkDO.getAmount());
				check.setCreatedBy(accountDO.getUsername());
				check.setCreatedDate(currDate);
				check.setUpdatedBy(accountDO.getUsername());
				check.setUpdatedDate(currDate);
				check.setContract(contracts);
				
				checks.add(check);
			}
			
			contracts.setChecks(checks);
		}
		
		
		
		contracts = contractsDAO.save(contracts);
		
		quote.setStatus(AggConstants.B_QUOTE_STATUS_CLOSED);
		quoteDAO.save(quote);
		
		condition = true;
		
		//sending Active Contract mail 
		emailService.sendAsyncContractEmail(quoteDO);
		
		return condition;
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

	@Override
	public long getQuotesCount(AccountDO accountDo) {
		long count = 0;
		if (accountDo.getRoleDO().getAccountType().equalsIgnoreCase(AggConstants.ACCOUNT_TYPE_ADMIN)) {
			//quoteDos = quoteDAO.findAllQuotes(AggConstants.B_QUOTE_STATUS_UNACRHIVE);
			count = quoteDAO.findQuotesCount(AggConstants.B_QUOTE_STATUS_UNACRHIVE);
		} else {
			count = quoteDAO.findAllQuotesCountByDealer(accountDo.getDealerId(), AggConstants.B_QUOTE_STATUS_UNACRHIVE);
		}
		return count;
	}

	@Override
	public long getQuotesSearchCount(AccountDO accountDo, String searchText, String statusSearch) {
		long count = 0;
		if (accountDo.getRoleDO().getAccountType().equalsIgnoreCase(AggConstants.ACCOUNT_TYPE_ADMIN)) {
			//quoteDos = quoteDAO.findAllQuotes(AggConstants.B_QUOTE_STATUS_UNACRHIVE);
			count = quoteDAO.findQuotesCountForSearch(AggConstants.B_QUOTE_STATUS_UNACRHIVE, searchText, statusSearch);
		} else {
			count = quoteDAO.findAllQuotesCountByDealerForSearch(accountDo.getDealerId(), AggConstants.B_QUOTE_STATUS_UNACRHIVE, searchText, statusSearch);
		}
		return count;
	}

	@Override
	public List<QuoteDO> getAllQuotesForSearch(AccountDO accountDo, String searchText, Pageable pageable) {
		List<QuoteDO> quoteDos = null;
		if (accountDo.getRoleDO().getAccountType().equalsIgnoreCase(AggConstants.ACCOUNT_TYPE_ADMIN)) {
			//quoteDos = quoteDAO.findAllQuotes(AggConstants.B_QUOTE_STATUS_UNACRHIVE);
			quoteDos = quoteDAO.findQuotesForSearchByStatus(AggConstants.B_QUOTE_STATUS_UNACRHIVE, searchText, pageable);
		} else {
			quoteDos = quoteDAO.findAllQuotesByDealerForSearch(accountDo.getDealerId(), AggConstants.B_QUOTE_STATUS_UNACRHIVE, searchText, pageable);
		}
		return quoteDos;
	}

	@Override
	public List<QuoteDO> getAllQuotes(AccountDO accountDo, Pageable pageable) {
		List<QuoteDO> quoteDos = null;
		if (accountDo.getRoleDO().getAccountType().equalsIgnoreCase(AggConstants.ACCOUNT_TYPE_ADMIN)) {
			//quoteDos = quoteDAO.findAllQuotes(AggConstants.B_QUOTE_STATUS_UNACRHIVE);
			quoteDos = quoteDAO.findQuotesByStatus(AggConstants.B_QUOTE_STATUS_UNACRHIVE, pageable);
		} else {
			quoteDos = quoteDAO.findAllQuotesByDealer(accountDo.getDealerId(), AggConstants.B_QUOTE_STATUS_UNACRHIVE, pageable);
		}
		return quoteDos;
	}
	
	@Override
	public List<QuoteDO> getAllQuotesForSearch(AccountDO accountDo, String searchText, int noOfRows,
			int pageLength, String properties, String orderDirection, String statusSearch, Pageable pageable) {
		List<QuoteDO> quoteDos = null;
		if (accountDo.getRoleDO().getAccountType().equalsIgnoreCase(AggConstants.ACCOUNT_TYPE_ADMIN)) {
			//quoteDos = quoteDAO.findAllQuotes(AggConstants.B_QUOTE_STATUS_UNACRHIVE);
			/*List<Object[]>	quoteResult = quoteDAO.findQuotesForSearchByStatus(AggConstants.B_QUOTE_STATUS_UNACRHIVE, searchText, noOfRows, pageLength,
					properties, orderDirection, statusSearch);*/
			List<Object[]>	quoteResult = quoteDAO.findQuotesForSearchByStatus(AggConstants.B_QUOTE_STATUS_UNACRHIVE, searchText, /*noOfRows, pageLength,
					properties+" "+orderDirection,*/ statusSearch, pageable);
			quoteDos = prepareQuoteDos(quoteResult);
		} else {
			//quoteDos = quoteDAO.findAllQuotesByDealerForSearch1(accountDo.getDealerId(), AggConstants.B_QUOTE_STATUS_UNACRHIVE, searchText, new PageRequest(0, 10));
			List<Object[]>	quoteResult = quoteDAO.findAllQuotesByDealerForSearch(accountDo.getDealerId(), AggConstants.B_QUOTE_STATUS_UNACRHIVE, searchText, /*noOfRows, pageLength,
					properties, orderDirection,*/ statusSearch, pageable);
			quoteDos = prepareQuoteDos(quoteResult);
		}
		return quoteDos;
	}

	private List<QuoteDO> prepareQuoteDos(List<Object[]> quoteResult) {
		List<QuoteDO> quoteDos = Lists.newArrayList();
		if (!quoteResult.isEmpty()) {
			for (Object[] objects : quoteResult) {
				BigInteger status = (BigInteger) objects[6];
				QuoteDO quoteDo = new QuoteDO((int) objects[0], (String) objects[1], (String) objects[2], (String) objects[3], (String) objects[4],
						(Date) objects[5], (byte) status.intValue(), (Date) objects[7], (short) objects[8]);
				quoteDos.add(quoteDo);
			}
		}
		//Collections.sort(quoteDos, (q1, q2) -> q2.getCreateDate().compareTo(q1.getCreateDate()));
		return quoteDos;
	}


	
}

