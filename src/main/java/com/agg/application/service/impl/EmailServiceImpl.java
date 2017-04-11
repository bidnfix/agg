package com.agg.application.service.impl;

import java.io.ByteArrayOutputStream;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;

import com.agg.application.dao.DealerDAO;
import com.agg.application.entity.Dealer;
import com.agg.application.entity.Quote;
import com.agg.application.model.AccountDO;
import com.agg.application.model.ContractReportDO;
import com.agg.application.model.QuoteDO;
import com.agg.application.model.ReportDO;
import com.agg.application.service.EmailService;
import com.agg.application.utils.AggConstants;
import com.agg.application.utils.EmailSender;
import com.agg.application.utils.EmailStatus;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
public class EmailServiceImpl implements EmailService {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Value("${admin.email}")
	private String adminEmail;
	
	@Autowired
	EmailSender emailSender;
	
	@Autowired
	private DealerDAO dealerDAO;
	
	@Value("${file.banner.image.path}")
	private String reportImagePath;
	
	@Value("${jrxml.folder.path}")
	private String jrxmlFolderPath;
	
	@Autowired
    private ResourceLoader resourceLoader;
	
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");

	@Override
	@Async
	public void sendAsyncInvoiceEmail(QuoteDO quoteDO, Quote quote, AccountDO accountDO, Dealer dealer, String appUrl) throws Exception{
		logger.info("Inside sendAsyncInvoiceEmail method");
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
		
		/*Map<String, Object> parameterMap = new HashMap<String, Object>();
		//parameterMap.put("imagePath", appUrl+"/assets/images/report_banner.png");
		parameterMap.put("imagePath", System.getProperty("user.dir")+reportImagePath);
		
		List<ReportDO> reportDOList = new ArrayList<ReportDO>();
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
		}*/
		
		Context context = new Context();
		context.setVariable("dealerName", dealer.getName());
		context.setVariable("quoteId", quoteDO.getQuoteId());
		context.setVariable("custName", quoteDO.getDealerName());
		context.setVariable("serialNo", quoteDO.getSerialNumber());
		context.setVariable("appUrl", appUrl);
		
		logger.info("b4 sending invoice email to the dealer: "+dealer.getName());
		//sending email to dealer
		String subject = "Dealer Invoice: "+quoteDO.getQuoteId();
		EmailStatus emailStatus = emailSender.sendMailAsHtml(email, subject, "email/dealer-invoice-template", context);
		if(emailStatus != null){
			logger.info("email status: "+emailStatus.isSuccess());
			if(emailStatus.isSuccess()){
				logger.info("Dealer Invoice Email Send succssfully to dealer: "+emailStatus.getTo());
			}
		}
	}


	@Override
	@Async
	public void sendAsyncPurchaseRequestEmail(QuoteDO quoteDO, Quote quote) throws Exception{
		logger.info("Inside sendAsyncPurchaseRequestEmail method");
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		//parameterMap.put("imagePath", appUrl+"/assets/images/report_banner.png");
		parameterMap.put("imagePath", System.getProperty("user.dir")+reportImagePath);
		
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
		EmailStatus emailStatus = emailSender.sendMailAsAttachment(adminEmail, subject, emailBody, pdfAttachment, "dealerQuote-"+quoteDO.getQuoteId());
		if(emailStatus != null){
			logger.info("emailStatus: "+emailStatus.getStatus());
			logger.info("Dealer Quote pdf Attachment emailed successfully");
		}
		
	}
	
	/**
	 * @param quoteDO
	 * @return
	 * @throws Exception
	 */
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
				reportDO.setDealerAddress(dealer.getAddress()+", "+dealer.getState()+" "+dealer.getZip());
			}else{
				Dealer parentDealer = dealerDAO.findByCode(dealer.getCode());
				if(parentDealer != null){
					reportDO.setAttn(parentDealer.getName());
					reportDO.setDealerAddress(parentDealer.getAddress()+", "+parentDealer.getState()+" "+parentDealer.getZip());
				}
			}
			
		}else{
			reportDO.setAttn("");
			reportDO.setDealerAddress("");
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
	
	/**
	 * @param quoteDO
	 * @return
	 * @throws Exception
	 */
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
				reportDO.setDealerAddress(dealer.getAddress()+", "+dealer.getState()+" "+dealer.getZip());
			}else{
				Dealer parentDealer = dealerDAO.findByCode(dealer.getCode());
				if(parentDealer != null){
					reportDO.setAttn(parentDealer.getName());
					reportDO.setDealerAddress(parentDealer.getAddress()+", "+parentDealer.getState()+" "+parentDealer.getZip());
				}
			}
			
		}else{
			reportDO.setAttn("");
			reportDO.setDealerAddress("");
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
		reportDO.setPrice(currencyFormat.format((quoteDO.getQuoteBasePrice()+quoteDO.getDealerMarkupPrice())));
		reportDO.setQuotePrice(currencyFormat.format(quoteDO.getQuoteBasePrice()));
		reportDO.setDealerMarkup(currencyFormat.format(quoteDO.getDealerMarkupPrice()));
		reportDO.setSpecialConsiderationDesc("None");
		
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


	@Override
	@Async
	public void sendAsyncContractEmail(QuoteDO quoteDO) throws Exception {
		logger.debug("Inside sendAsyncContractEmail method");
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
		
		String[] fileNames = {"ContractDetails-"+"CR-"+quoteDO.getQuoteId(),"CoverageDetails-"+"CR-"+quoteDO.getQuoteId()};
		
		
		String subject = "Contract Details: "+"CR-"+quoteDO.getQuoteId()+" pdf files";
		String emailBody = "PFA Contract Details: "+"CR-"+quoteDO.getQuoteId()+" details.";
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
		
	}
	
	
	/**
	 * @param quoteDO
	 * @return ContractReportDO
	 * @throws Exception
	 */
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


}
