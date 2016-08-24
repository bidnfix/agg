package com.agg.application.service.impl;

import java.io.ByteArrayOutputStream;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.activation.DataSource;
import javax.mail.util.ByteArrayDataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.agg.application.dao.CustomerInfoDAO;
import com.agg.application.dao.DealerDAO;
import com.agg.application.dao.MachineInfoDAO;
import com.agg.application.dao.ManufacturerDAO;
import com.agg.application.dao.PricingDAO;
import com.agg.application.dao.QuoteDAO;
import com.agg.application.dao.UseOfEquipmentDAO;
import com.agg.application.entity.CustomerInfo;
import com.agg.application.entity.Dealer;
import com.agg.application.entity.MachineInfo;
import com.agg.application.entity.Manufacturer;
import com.agg.application.entity.Quote;
import com.agg.application.entity.QuotePK;
import com.agg.application.entity.UseOfEquip;
import com.agg.application.model.AccountDO;
import com.agg.application.model.DealerDO;
import com.agg.application.model.MachineInfoDO;
import com.agg.application.model.ManufacturerDO;
import com.agg.application.model.PricingDO;
import com.agg.application.model.QuoteDO;
import com.agg.application.model.ReportDO;
import com.agg.application.model.UseOfEquipmentDO;
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
	
	@Value("${admin.email}")
	private String adminEmail;
	
	@Autowired
	EmailSender emailSender;
	
	@Autowired
    private ResourceLoader resourceLoader;

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
			byte condition = coverageExpired ? (byte)1 : (byte)2;
			List<Integer> deductibleAmtList = pricingDAO.findDeductibleAmount(condition, groupId);
			List<Integer> coverageTermList = pricingDAO.findCoverageTerm(condition, groupId, deductibleAmtList);
			
			logger.info("deductibleAmtList size: "+deductibleAmtList.size()+" coverageTermList size: "+coverageTermList.size());
			
			deductableCoverageTermMap.put("deductibleAmtList", deductibleAmtList);
			deductableCoverageTermMap.put("coverageTermList", coverageTermList);
		}
		
		return deductableCoverageTermMap;
	}

	@Override
	public List<PricingDO> getCoveragePriceDetils(boolean coverageExpired, long machineId, int deductibleAmt, int coverageTerm) {
		MachineInfo machineInfo = machineInfoDAO.findOne(machineId);
		List<PricingDO> pricingDOList = null;
		if(machineInfo != null){
			int groupId = new Long(machineInfo.getGroupConstant().getGroupId()).intValue();
			logger.info("groupId: "+groupId);
			//if manufacturers coverage date expires then consider as used(2) otherwise new(1);
			byte condition = coverageExpired ? (byte)1 : (byte)2;
			
			pricingDOList = pricingDAO.findCoverageLevelPricing(condition, groupId, deductibleAmt, coverageTerm);
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
				quote.setStatus((byte)1);
			}
		}else{
			quote = new Quote();
			String quoteId = Util.getQuoteId(AggConstants.CHARSET_AZ_09, AggConstants.QUOTE_ID_LENGTH);
			logger.info("created quoteId: "+quoteId);
			QuotePK quotePK = new QuotePK();
			quotePK.setQuoteId(quoteId);
			
			quote.setId(quotePK);
			quote.setStatus((byte)1);
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
		quote.setPrId(0);
		quote.setServicingDealer(0);
		quote.setLastUpdate(new Date());
		
		quote = quoteDAO.save(quote);
		
		if(quote != null && quote.getId() != null){
			logger.info("quoteId: "+quote.getId().getQuoteId()+" and id: "+quote.getId().getId());
			quoteDO.setQuoteId(quote.getId().getQuoteId());
			quoteDO.setId(quote.getId().getId());
			if(quote.getStatus() == 1){
				quoteDO.setStatusDesc(AggConstants.QUOTE_STATUS_ESTIMATING_PRICE);
			}else if(quote.getStatus() == 4){
				quoteDO.setStatusDesc(AggConstants.QUOTE_STATUS_PURCHASE_REQUESTED);
			}else if(quote.getStatus() == 5){
				quoteDO.setStatusDesc(AggConstants.QUOTE_STATUS_INVOICED);
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
				quote.setStatus((byte)1);
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
			quote.setPrId(0);
			quote.setServicingDealer(0);
			quote.setLastUpdate(new Date());
			
			quote = quoteDAO.save(quote);
			
			if(quote != null && quote.getId() != null){
				logger.info("quoteId: "+quote.getId().getQuoteId()+" and id: "+quote.getId().getId());
				quoteDO.setQuoteId(quote.getId().getQuoteId());
				quoteDO.setId(quote.getId().getId());
				if(quote.getStatus() == 1){
					quoteDO.setStatusDesc(AggConstants.QUOTE_STATUS_ESTIMATING_PRICE);
				}else if(quote.getStatus() == 4){
					quoteDO.setStatusDesc(AggConstants.QUOTE_STATUS_PURCHASE_REQUESTED);
				}else if(quote.getStatus() == 5){
					quoteDO.setStatusDesc(AggConstants.QUOTE_STATUS_INVOICED);
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
				quote.setStatus((byte)1);
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
			quote.setPrId(0);
			quote.setServicingDealer(0);
			quote.setLastUpdate(new Date());
			
			CustomerInfo customerInfo = new CustomerInfo();
			customerInfo.setQuoteId(quoteDO.getQuoteId());
			customerInfo.setAddress(quoteDO.getDealerAddress());
			customerInfo.setCity(quoteDO.getDealerCity());
			customerInfo.setEmail(quoteDO.getDealerEmail());
			customerInfo.setName(quoteDO.getDealerName());
			customerInfo.setPhone(quoteDO.getDealerPhone());
			customerInfo.setRemorse((quoteDO.isCustRemorsePeriod())?(byte)1:(byte)0);
			customerInfo.setState(quoteDO.getDealerState());
			customerInfo.setUnderstand((quoteDO.isCustUnderstandCoverage())?(byte)1:(byte)0);
			customerInfo.setZip(quoteDO.getDealerZip());
			
			//TODO
			customerInfoDAO.save(customerInfo);
			
			quote = quoteDAO.save(quote);
			
			if(quote != null && quote.getId() != null){
				logger.info("quoteId: "+quote.getId().getQuoteId()+" and id: "+quote.getId().getId());
				quoteDO.setQuoteId(quote.getId().getQuoteId());
				quoteDO.setId(quote.getId().getId());
				if(quote.getStatus() == 1){
					quoteDO.setStatusDesc(AggConstants.QUOTE_STATUS_ESTIMATING_PRICE);
				}else if(quote.getStatus() == 4){
					quoteDO.setStatusDesc(AggConstants.QUOTE_STATUS_PURCHASE_REQUESTED);
				}else if(quote.getStatus() == 5){
					quoteDO.setStatusDesc(AggConstants.QUOTE_STATUS_INVOICED);
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
				quote.setStatus((byte)1);
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
			quote.setPrId(0);
			quote.setServicingDealer(0);
			quote.setLastUpdate(new Date());
			
			//purchased status to 4
			quote.setStatus((byte)4);
			
			CustomerInfo customerInfo = new CustomerInfo();
			customerInfo.setQuoteId(quoteDO.getQuoteId());
			customerInfo.setAddress(quoteDO.getDealerAddress());
			customerInfo.setCity(quoteDO.getDealerCity());
			customerInfo.setEmail(quoteDO.getDealerEmail());
			customerInfo.setName(quoteDO.getDealerName());
			customerInfo.setPhone(quoteDO.getDealerPhone());
			customerInfo.setRemorse((quoteDO.isCustRemorsePeriod())?(byte)1:(byte)0);
			customerInfo.setState(quoteDO.getDealerState());
			customerInfo.setUnderstand((quoteDO.isCustUnderstandCoverage())?(byte)1:(byte)0);
			customerInfo.setZip(quoteDO.getDealerZip());
			
			//TODO
			customerInfoDAO.save(customerInfo);
			
			quote = quoteDAO.save(quote);
			
			if(quote != null && quote.getId() != null){
				logger.info("quoteId: "+quote.getId().getQuoteId()+" and id: "+quote.getId().getId());
				quoteDO.setQuoteId(quote.getId().getQuoteId());
				quoteDO.setId(quote.getId().getId());
				if(quote.getStatus() == 1){
					quoteDO.setStatusDesc(AggConstants.QUOTE_STATUS_ESTIMATING_PRICE);
				}else if(quote.getStatus() == 4){
					quoteDO.setStatusDesc(AggConstants.QUOTE_STATUS_PURCHASE_REQUESTED);
				}else if(quote.getStatus() == 5){
					quoteDO.setStatusDesc(AggConstants.QUOTE_STATUS_INVOICED);
				}
			}
			
			Map<String, Object> parameterMap = new HashMap<String, Object>();
			parameterMap.put("imagePath", appUrl+"/assets/images/logo.png");
			
			List<ReportDO> reportDOList = new ArrayList<ReportDO>();
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
			EmailStatus emailStatus = emailSender.sendMailAsAttachment(adminEmail, subject, emailBody, pdfAttachment, "dealerQuote");
			if(emailStatus != null){
				logger.info("emailStatus: "+emailStatus.getStatus());
				logger.info("Dealer Quote pdf Attachment emailed successfully");
			}
			
		}
	}

	private ReportDO getQuoteReportDO(QuoteDO quoteDO) throws Exception{
		ReportDO reportDO = new ReportDO();
		
		SimpleDateFormat reportDateFormat = new SimpleDateFormat("MM/dd/yyyy");
		Locale locale = new Locale("en", "US");
		NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(locale);
		
		reportDO.setDealerName(quoteDO.getDealerDO().getName());
		reportDO.setQuoteDate((quoteDO.getEstSaleDate() != null)?reportDateFormat.format(quoteDO.getEstSaleDate()):"");
		//TODO
		reportDO.setAttn("");
		//TODO
		reportDO.setQuoteExpires("");
		reportDO.setQuoteId(quoteDO.getQuoteId());
		reportDO.setAddress(quoteDO.getDealerAddress()+", "+quoteDO.getDealerState()+" "+quoteDO.getDealerZip());
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
		reportDO.setWarrantyEndDate((quoteDO.getCoverageEndDate() != null)?reportDateFormat.format(quoteDO.getCoverageEndDate()):"");
		reportDO.setLimitOfLiability(currencyFormat.format(quoteDO.getMachineInfoDO().getLol()));
		reportDO.setPrice(currencyFormat.format(quoteDO.getCustomerPrice()));
		reportDO.setQuotePrice(currencyFormat.format(quoteDO.getQuoteBasePrice()));
		reportDO.setDealerMarkup(currencyFormat.format(quoteDO.getDealerMarkupPrice()));
		reportDO.setSpecialConsiderationDesc("None");
		
		return reportDO;
	}

	@Override
	public ReportDO getQuoteReportDetails(String quoteId) {
		logger.info("Inside getQuoteReportDetails with quoteId: "+quoteId); 
		ReportDO reportDO = null;
		
		Quote quote = quoteDAO.findByIdQuoteId(quoteId);
		if(quote != null){
			reportDO = new ReportDO();
			
			SimpleDateFormat reportDateFormat = new SimpleDateFormat("MM/dd/yyyy");
			Locale locale = new Locale("en", "US");
			NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(locale);
			
			reportDO.setDealerName(quote.getDealer().getName());
			reportDO.setQuoteDate((quote.getMachineSaleDate() != null)?reportDateFormat.format(quote.getMachineSaleDate()):"");
			//TODO
			reportDO.setAttn("");
			//TODO
			reportDO.setQuoteExpires("");
			reportDO.setQuoteId(quoteId);
			CustomerInfo customerInfo = customerInfoDAO.findOne(quoteId);
			if(customerInfo != null){
				reportDO.setAddress(customerInfo.getAddress()+", "+customerInfo.getState()+" "+customerInfo.getZip());
				reportDO.setEmail(customerInfo.getEmail());
				reportDO.setPhone(customerInfo.getPhone());
			}
			reportDO.setOutStandingDesc(AggConstants.QUOTE_REPORT_OUT_STANDING_DESC);
			reportDO.setManufacturerName(quote.getManufacturer().getManfName());
			reportDO.setModelName(quote.getMachineInfo().getModel());
			reportDO.setModelSerialNo(quote.getMachineSerial());
			reportDO.setEquipment(quote.getUseOfEquip().getEquipName());
			reportDO.setRetailPrice(currencyFormat.format(quote.getMachineRetailPrice()));
			reportDO.setCurrentHours(quote.getMachineHours()+"");
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
			reportDO.setWarrantyEndDate((quote.getManfEndDate() != null)?reportDateFormat.format(quote.getManfEndDate()):"");
			reportDO.setLimitOfLiability(currencyFormat.format(quote.getMachineInfo().getGroupConstant().getLol()));
			String dealerMarkupType = quote.getDealerMarkupType();
			double coveragePrice = quote.getCoveragePrice();
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
		}
		
		return reportDO;
	}

	
	@Override
	public List<QuoteDO> getQuotes(AccountDO accountDO) {
		List<QuoteDO> quoteDOList = null;
		if(accountDO.getRoleDO().getAccountType().equalsIgnoreCase(AggConstants.ACCOUNT_TYPE_ADMIN)){
			List<Quote> quoteList = Util.toList(quoteDAO.findAll());
			quoteDOList = getQuoteDetails(quoteList);
		}else{
			List<Quote> quoteList = Util.toList(quoteDAO.findByDealerId(accountDO.getDealerId()));
			quoteDOList = getQuoteDetails(quoteList);
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
				
				quoteDO.setDealerDO(dealerDO);
			}
			quoteDO.setCoverageExpired((quote.getManfExpired() == 1)?true:false);
			quoteDO.setCoverageEndDate(quote.getManfEndDate());
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
			
			MachineInfo machineInfo = quote.getMachineInfo();
			if(machineInfo != null){
				MachineInfoDO machineInfoDO = new MachineInfoDO();
				machineInfoDO.setMachineId(machineInfo.getMachineId());
				machineInfoDO.setMachineType(machineInfo.getMachineType().getMachineType());
				machineInfoDO.setModel(machineInfo.getModel());
				machineInfoDO.setModelYear(machineInfo.getModelYear());
				machineInfoDO.setEPower(machineInfo.getEPower());
				
				quoteDO.setMachineInfoDO(machineInfoDO);
			}
			
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
			quoteDO.setDealerMarkup(quote.getDealerMarkup());
			quoteDO.setDealerMarkupType(quote.getDealerMarkupType());
			quoteDO.setDeductiblePrice(quote.getDeductAmount());
			quoteDO.setCoverageTerm(quote.getCoverageTerm());
			quoteDO.setCoverageHours(quote.getCoverageLevelHours());
			quoteDO.setCoverageType(quote.getCoverageType());
			quoteDO.setQuoteBasePrice(quote.getCoveragePrice());
			quoteDO.setStatus(quote.getStatus());
			String statusDesc = "";
			if(quote.getStatus() == 0){
				statusDesc = AggConstants.QUOTE_STATUS_ACRHIVE;
			}else if(quote.getStatus() == 1){
				statusDesc = AggConstants.QUOTE_STATUS_ESTIMATING_PRICE;
			}else if(quote.getStatus() == 4){
				statusDesc = AggConstants.QUOTE_STATUS_PURCHASE_REQUESTED;
			}else if(quote.getStatus() == 5){
				statusDesc = AggConstants.QUOTE_STATUS_INVOICED;
			}
			quoteDO.setStatusDesc(statusDesc);
			
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
		}
		
		return quoteDO;
	}

}

