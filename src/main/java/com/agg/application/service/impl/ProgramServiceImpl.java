package com.agg.application.service.impl;

import java.io.ByteArrayOutputStream;
import java.sql.Timestamp;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
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

import com.agg.application.dao.AccountDAO;
import com.agg.application.dao.CustomerInfoDAO;
import com.agg.application.dao.DealerDAO;
import com.agg.application.dao.MachineInfoDAO;
import com.agg.application.dao.ManufacturerDAO;
import com.agg.application.dao.ProgramDAO;
import com.agg.application.dao.QuoteDAO;
import com.agg.application.dao.UseOfEquipmentDAO;
import com.agg.application.entity.Account;
import com.agg.application.entity.CustomerInfo;
import com.agg.application.entity.Dealer;
import com.agg.application.entity.MachineInfo;
import com.agg.application.entity.Manufacturer;
import com.agg.application.entity.Quote;
import com.agg.application.entity.QuotePK;
import com.agg.application.entity.Sprogram;
import com.agg.application.model.AccountDO;
import com.agg.application.model.CustomerInfoDO;
import com.agg.application.model.DealerDO;
import com.agg.application.model.MachineInfoDO;
import com.agg.application.model.ManufacturerDO;
import com.agg.application.model.ProgramDO;
import com.agg.application.model.QuoteDO;
import com.agg.application.model.ReportDO;
import com.agg.application.service.ProgramService;
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
public class ProgramServiceImpl implements ProgramService {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
	
	@Autowired
	ProgramDAO programDAO;
	
	@Autowired
	DealerDAO dealerDAO;
	
	@Autowired
	AccountDAO accountDAO;
	
	@Autowired
	private ManufacturerDAO manufacturerDAO;
	
	@Autowired
	private MachineInfoDAO machineInfoDAO;
	
	@Autowired
	private QuoteDAO quoteDAO;
	
	@Autowired
	private CustomerInfoDAO customerInfoDAO;
	
	@Autowired
	private UseOfEquipmentDAO useOfEquipmentDAO;
	
	@Autowired
	EmailSender emailSender;
	
	@Autowired
    private ResourceLoader resourceLoader;
	
	@Value("${admin.email}")
	private String adminEmail;
	
	@Value("${file.banner.image.path}")
	private String reportImagePath;
	
	@Override
	public List<ProgramDO> getPrograms(AccountDO accountDO) {
		logger.debug("In getPrograms");
		List<Sprogram> programList = null;
		if(accountDO.getRoleDO().getAccountType().equalsIgnoreCase(AggConstants.ACCOUNT_TYPE_ADMIN)){
			programList = Util.toList(programDAO.findAll());
		}else{
			Account account = accountDAO.findOne(accountDO.getId());
			if(account != null){
				Dealer dealer = account.getDealer();
				if(dealer != null){
					if(dealer.getCode() == dealer.getParentCode()){
						//fetching dealer program details.
						programList = programDAO.findByDealerIdAndPrIsActive(dealer.getId(), (byte)1);
					}else{
						Dealer parentDealer = dealerDAO.findByCode(dealer.getParentCode());
						if(parentDealer != null){
							//fetching dealer and parent dealer program details.
							programList = programDAO.findByDealerIds(dealer.getId(), parentDealer.getId());
						}
					}
				}
				
			}
		}
		
		List<ProgramDO> programDOList = null;
		if(!programList.isEmpty()){
			//logger.debug("programList size:"+programList.size());
			programDOList = new ArrayList<ProgramDO>();
			ProgramDO programDO = null;
			Sprogram program = null;
			DealerDO dealerDO = null;
			ManufacturerDO manfDO = null;
			List<MachineInfoDO> machineInfoDOList = null; 
			
			Iterator<Sprogram> it = programList.iterator();
			while(it.hasNext()){
				programDO = new ProgramDO();
				program = it.next();
				
				programDO.setName(program.getPrName());
				programDO.setcType(program.getPrCType());
				programDO.setPrId(program.getPrId());
				programDO.setCondition(program.getPrCondition());
				programDO.setcType(program.getPrCType());
				programDO.setcTerm(program.getPrCTerm());
				programDO.setcHours(program.getPrCHours());
				programDO.setDeductible(program.getPrDeductible());
				programDO.setLol(program.getPrLol());
				programDO.setCost(program.getPrCost());
				programDO.setDesc(program.getPrDesc());
				programDO.setIsActive(program.getPrIsActive());
				
				dealerDO = new DealerDO();
				Dealer dealer = program.getDealer();
				if(dealer!=null)
				{
					dealerDO.setName(dealer.getName());
					dealerDO.setId(dealer.getId());
				}
				programDO.setDealerDO(dealerDO);
				
				manfDO = new ManufacturerDO();
				Manufacturer manf = program.getManufacturer();
				if(manf!=null)
				{
					manfDO.setName(manf.getManfName());
					manfDO.setId(manf.getManfId());
				}
				programDO.setManufacturerDO(manfDO);
				
				machineInfoDOList = new ArrayList<MachineInfoDO>();
				List<MachineInfo> machineInfoLst = program.getMachineInfos();
				if(machineInfoLst!=null)
				{
					for(MachineInfo macineInfo : machineInfoLst)
					{
						MachineInfoDO macInfDO = new MachineInfoDO();
						macInfDO.setModel(macineInfo.getModel());
						macInfDO.setMachineId(macineInfo.getMachineId());
						macInfDO.setModelYear((macineInfo.getModelYear() != null)?macineInfo.getModelYear():0);
						macInfDO.setMachineId(macineInfo.getMachineId());
						
						machineInfoDOList.add(macInfDO);
					}
					programDO.setMachineInfoDOList(machineInfoDOList);
				}
				programDO.setDealerDO(dealerDO);
				
				programDOList.add(programDO);
			}
		}
		//logger.debug(""+programDOList.size());
		
		return programDOList;
	}

	@Override
	public Long saveProgram(ProgramDO program) {
		logger.debug("In saveProgram");
		Timestamp date = new Timestamp(new Date().getTime());
		Sprogram progEnt = new Sprogram();
		progEnt.setPrName(program.getName());
		progEnt.setPrDesc(program.getDesc());
		progEnt.setPrIsActive(program.getIsActive());
		progEnt.setPrAServicing(program.getaServicing());
		progEnt.setPrCondition(program.getCondition());
		progEnt.setPrCType(program.getcType());
		progEnt.setDealer(dealerDAO.findOne(Long.valueOf(program.getDealerDO().getId())));
		progEnt.setPrGroup(program.getGroup());
		progEnt.setPrDeductible(program.getDeductible());
		progEnt.setPrCType(program.getcType());
		progEnt.setPrCHours(program.getcHours());
		progEnt.setPrCTerm(program.getcTerm());
		progEnt.setPrCost(program.getCost());
		progEnt.setPrLol(program.getLol());
		progEnt.setPrIsArchive((byte)0);
		progEnt.setManufacturer(manufacturerDAO.findOne(Long.valueOf(program.getManufacturerDO().getId())));
		
		//List<MachineInfoDO> machineInfoDOs =  program.getMachineInfoDOList();
		
		List<MachineInfoDO> machineInfoDOs =  program.getMachineModelList();
		
		
		
		MachineInfo machineInfo = null;
		List<MachineInfo> machineInfoList = new ArrayList<MachineInfo>();
		for(MachineInfoDO machineInfoDO : machineInfoDOs)
		{
			//logger.debug("--machineInfoDO--"+machineInfoDO.getMachineId());
			machineInfo = machineInfoDAO.findOne(machineInfoDO.getMachineId());
			if(machineInfo!=null)
			{
				//logger.debug("--machineInfo --"+machineInfo.getMachineId());
				machineInfoList.add(machineInfo);
			}
		}
		
		progEnt.setMachineInfos(machineInfoList);
		
		progEnt.setPrLastUpdate(date);
		progEnt = programDAO.save(progEnt);
		return progEnt.getPrId();
	}
	
	@Override
	@Transactional
	public QuoteDO saveProgramsAsDealr(QuoteDO quoteDO, AccountDO accountDO, String appUrl) throws Exception{
		logger.debug("In saveProgramsAsDealr");
		Timestamp date = new Timestamp(new Date().getTime());
		
		QuoteDO newQuoteDO = null;
		
		if(quoteDO != null)
		{
			CustomerInfoDO custDO = quoteDO.getCustomerInfoDO();
			Quote quote = new Quote();
			String quoteId = Util.getQuoteId(AggConstants.CHARSET_AZ_09, AggConstants.QUOTE_ID_LENGTH);
			logger.info("created quoteId: "+quoteId);
			QuotePK quotePK = new QuotePK();
			quotePK.setQuoteId(quoteId);
			quote.setId(quotePK);
			
			if(accountDO.getRoleDO().getAccountType().equalsIgnoreCase(AggConstants.ACCOUNT_TYPE_ADMIN)){
				quote.setDealer(dealerDAO.findOne(quoteDO.getDealerDO().getId()));
			}else{
				quote.setDealer(dealerDAO.findOne(accountDO.getDealerId()));
			}
			
			logger.debug("quoteDO.getDealerDO().getId() "+quoteDO.getDealerDO().getId());
			logger.debug("accountDO.getDealerId() "+accountDO.getDealerId());
			
			//quote.setManfExpired((quoteDO.isCoverageExpired())?(byte)1:(byte)0);
			
			//logger.debug("quoteDO.getCondition()  "+quoteDO.getCondition());
			
			quote.setManfExpired((quoteDO.getCondition()==0)?(byte)1:(byte)0);
			quote.setManfEndDate(quoteDO.getCoverageEndDate());
			//quote.setManfEndKnown((quoteDO.isCoverageEndDateUnknown())?(byte)1:(byte)0);
			quote.setManfEndKnown((byte)1);
			quote.setManfVerified((quoteDO.isCoverageEndDateVerified())?(byte)1:(byte)0);
			quote.setPtHours(AggConstants.COVERAGE_TYPE_PT_HOURS);
			quote.setPtMonths(AggConstants.COVERAGE_TYPE_PT_MONTHS);
			quote.setHHours(AggConstants.COVERAGE_TYPE_PH_HOURS);
			quote.setHMonths(AggConstants.COVERAGE_TYPE_PH_MONTHS);
			quote.setMachineHours(AggConstants.COVERAGE_TYPE_PL_HOURS);
			quote.setMachineMonths(AggConstants.COVERAGE_TYPE_PL_MONTHS);
			quote.setMachineMeterHours(quoteDO.getMachineMeterHours());
			quote.setOtherProv(quoteDO.getProvisions());
			
			Manufacturer manufacturer = manufacturerDAO.findOne(quoteDO.getManufacturerDO().getId());
			if(manufacturer != null){
				quote.setManufacturer(manufacturer);
				quote.setManfName(manufacturer.getManfName());
			}
			if(quoteDO.getMachineInfoDO() != null)
			{			
				MachineInfo machineInfo = machineInfoDAO.findOne(quoteDO.getMachineInfoDO().getMachineId());
				if(machineInfo != null){
					quote.setMachineModel(machineInfo.getModel());
					quote.setMachineInfo(machineInfo);
					quote.setGroupId(new Long(machineInfo.getGroupConstant().getGroupId()).intValue());
				}
			}
			
			quote.setMachinePower(quoteDO.getHorsePower());
			quote.setMachineSerial(quoteDO.getSerialNumber());
			quote.setMachineRetailPrice(quoteDO.getRetailPrice());
			quote.setMachineYear(quoteDO.getModelYear());
			quote.setUseOfEquip(useOfEquipmentDAO.findOne(quoteDO.getUseOfEquipmentDO().getId()));
			
			logger.debug("quoteDO.getUseOfEquipmentDO().getId() "+quoteDO.getUseOfEquipmentDO().getId());
			
			quote.setMachineSaleDate(new Date());
			
			//TODO
			quote.setDealerMarkupType("price");
			quote.setDealerMarkup(quoteDO.getDealerMarkup());
			quote.setDeductAmount(quoteDO.getDeductible());
			quote.setCoverageTerm(quoteDO.getcTerm());
			quote.setCoverageLevelHours(quoteDO.getcHours());
			quote.setCoverageType(quoteDO.getcType());
			quote.setCoveragePrice(quoteDO.getCost());
			
			quote.setIsArchive((short)0);
			quote.setCreateDate(new Date());
			if(quoteDO.getProgramDO() != null)
			{
				
				quote.setProgram(programDAO.findOne(quoteDO.getProgramDO().getPrId()));
			}
			
			quote.setServicingDealer(0);
			quote.setLastUpdate(new Date());
			
			//purchased status to 4
			quote.setStatus((byte)4);
			
			quote = quoteDAO.save(quote);
			
			newQuoteDO = new QuoteDO();
			//logger.debug("To test 1111: "+quote.getId().getId());
			newQuoteDO.setQuoteId(quote.getId().getQuoteId());
			
			Quote newQuote = quoteDAO.findByIdQuoteId(quote.getId().getQuoteId());
			if(newQuote != null)
			{
				if(newQuote.getId() != null)
				newQuoteDO.setId(newQuote.getId().getId());
			}
			//logger.debug("To test 2222: "+quoteDO.getId());
			
			
			
			if(custDO != null)
			{
				CustomerInfo custInfo = new CustomerInfo();
				custInfo.setQuoteId(quoteId);
				custInfo.setName(custDO.getName());
				custInfo.setAddress((custDO.getAddress() != null)?custDO.getAddress():"");
				custInfo.setCity(custDO.getCity());
				custInfo.setState((custDO.getState() != null)?custDO.getState():"");
				custInfo.setZip((custDO.getZip() != null)?custDO.getZip():"");
				custInfo.setPhone((custDO.getPhone() != null)?custDO.getPhone():"");
				custInfo.setEmail(custDO.getEmail());
				custInfo.setRemorse((quoteDO.isCustRemorsePeriod())?(byte)1:(byte)0);
				custInfo.setUnderstand((quoteDO.isCustUnderstandCoverage())?(byte)1:(byte)0);
				
				logger.debug("quoteDO.isCustRemorsePeriod() "+quoteDO.isCustRemorsePeriod());
				logger.debug("quoteDO.isCustUnderstandCoverage() "+quoteDO.isCustUnderstandCoverage());
				custInfo.setLastUpdate(date);
				custInfo = customerInfoDAO.save(custInfo);
			}
			
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
			
			//******** Code for report which being commented for now ********//
			
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
			EmailStatus emailStatus = emailSender.sendMailAsAttachment(adminEmail, subject, emailBody, pdfAttachment, "dealerQuote");
			if(emailStatus != null){
				logger.info("emailStatus: "+emailStatus.getStatus());
				logger.info("Dealer Quote pdf Attachment emailed successfully");
			}
			
		}
		
		return newQuoteDO;
		
		/*
		int generatedQuoteId = 0;
		if(quoteDO != null)
		{
			CustomerInfoDO custDO = quoteDO.getCustomerInfoDO();
			Quote quote = new Quote();
			String quoteId = Util.getQuoteId(AggConstants.CHARSET_AZ_09, AggConstants.QUOTE_ID_LENGTH);
			logger.info("created quoteId: "+quoteId);
			QuotePK quotePK = new QuotePK();
			quotePK.setQuoteId(quoteId);
			quote.setId(quotePK);
			
			logger.debug("quoteDO.getProgramDO()"+quoteDO.getProgramDO().getcTerm());
			logger.debug("quoteDO.getProgramDO()"+quoteDO.getProgramDO().getDeductible());
			logger.debug("quoteDO.getProgramDO()"+quoteDO.getProgramDO().getCost());
			
			quote.setCoverageTerm(quoteDO.getProgramDO().getcTerm());
			
			if(quoteDO.getProgramDO() != null)
			{
				quote.setProgram(programDAO.findOne(Long.valueOf(quoteDO.getProgramDO().getPrId())));
				//logger.debug("quote..getProgramDO "+quoteDO.getProgramDO().getPrId());
				quote.setCoverageTerm(quoteDO.getProgramDO().getcTerm());
				quote.setDeductAmount(quoteDO.getProgramDO().getDeductible());
				quote.setCoverageLevelHours(quoteDO.getProgramDO().getcHours());
				quote.setCoverageTerm(quoteDO.getProgramDO().getcTerm());
				quote.setCoverageType(quoteDO.getProgramDO().getcType());
			}
			
			if(quoteDO.getManufacturerDO() != null)
			{
				Manufacturer manf = manufacturerDAO.findOne(Long.valueOf(quoteDO.getManufacturerDO().getId()));
				quote.setManufacturer(manf);
				quote.setManfName(manf.getManfName());
				logger.debug("quote..getManufacturer "+quote.getManufacturer());
			}
			
			if(quoteDO.getMachineInfoDO() != null)
			{
				logger.debug("quoteDO.getMachineInfoDO().getMachineId() "+quoteDO.getMachineInfoDO().getMachineId());
				MachineInfo machineInfo = machineInfoDAO.findOne(Long.valueOf(quoteDO.getMachineInfoDO().getMachineId()));
				quote.setMachineInfo(machineInfo);
				quote.setMachineModel(machineInfo.getModel());
				quote.setGroupId(new Long(machineInfo.getGroupConstant().getGroupId()).intValue());
				
				logger.debug("quote..getMachineInfoDO "+quote.getMachineInfo());
			}
			
			logger.info("accountDO.getDealerId() "+accountDO.getDealerId());
			
			if(quoteDO.getDealerDO() != null)
			{
				quote.setDealer(dealerDAO.findOne(Long.valueOf(quoteDO.getDealerDO().getId())));
				logger.debug("quote..getDealerDO "+quoteDO.getDealerDO().getId());
			}
			
			quote.setMachineSerial(quoteDO.getMachineSerial());
			
			quote.setManfExpired((byte)0);
			quote.setManfEndDate(date);
			quote.setManfEndKnown((byte)0);
			quote.setManfVerified((byte)0);
			quote.setPtHours(0);
			quote.setPtMonths(0);
			quote.setHHours(0);
			quote.setHMonths(0);
			quote.setMachineHours(0);
			quote.setMachineMonths(0);
			quote.setOtherProv("");
			
			quote.setIsArchive((short)0);
			quote.setCreateDate(date);
			
			if(quoteDO.getProgramDO() != null)
			{
				Sprogram program = programDAO.findOne(Long.valueOf(quoteDO.getProgramDO().getPrId()));
				quote.setProgram(program);
			}
			
			quote.setServicingDealer(0);
			quote.setLastUpdate(date);
			
			quote.setStatus((byte)4);
			
			quote = quoteDAO.save(quote);
			
			generatedQuoteId = quote.getId().getId();
			
			if(custDO != null)
			{
				CustomerInfo custInfo = new CustomerInfo();
				custInfo.setQuoteId(quoteId);
				custInfo.setName(custDO.getName());
				custInfo.setAddress(custDO.getAddress());
				custInfo.setCity(custDO.getCity());
				custInfo.setState(custDO.getState());
				custInfo.setZip(custDO.getZip());
				custInfo.setPhone(custDO.getPhone());
				custInfo.setEmail(custDO.getEmail());
				custInfo.setLastUpdate(date);
				custInfo = customerInfoDAO.save(custInfo);
				
			}
		}
		return generatedQuoteId;*/
		
		
	}

	@Override
	public ProgramDO getProgram(Long id, AccountDO accountDO) {
		Sprogram program = programDAO.findOne(id);
		//ProgramDO programDO = new ProgramDO();
		
		ProgramDO programDO = null;
		//Sprogram program = null;
		DealerDO dealerDO = null;
		ManufacturerDO manfDO = null;
		List<MachineInfoDO> machineInfoDOList = null; 
		
		if(program != null)
		{
			programDO = new ProgramDO();
		
			/*programDO.setPrId(program.getPrId());
			programDO.setName(program.getPrName());
			programDO.setDesc(program.getPrDesc());
			programDO.setcType(program.getPrCType());
			
			DealerDO dealerDO = new DealerDO();
			Dealer dealer = program.getDealer();
			if(dealer!=null)
			{
				dealerDO.setName(dealer.getName());
				dealerDO.setId(dealer.getId());
			}
			programDO.setDealerDO(dealerDO);*/
	
			
			programDO.setName(program.getPrName());
			programDO.setcType(program.getPrCType());
			programDO.setPrId(program.getPrId());
			programDO.setCondition(program.getPrCondition());
			programDO.setcType(program.getPrCType());
			programDO.setcTerm(program.getPrCTerm());
			programDO.setcHours(program.getPrCHours());
			programDO.setDeductible(program.getPrDeductible());
			programDO.setLol(program.getPrLol());
			programDO.setCost(program.getPrCost());
			programDO.setDesc(program.getPrDesc());
			programDO.setIsActive(program.getPrIsActive());
			programDO.setaServicing(program.getPrAServicing());
			programDO.setIsArchive(program.getPrIsArchive());
			
			dealerDO = new DealerDO();
			Dealer dealer = program.getDealer();
			if(dealer!=null)
			{
				dealerDO.setName(dealer.getName());
				dealerDO.setId(dealer.getId());
			}
			programDO.setDealerDO(dealerDO);
			
			manfDO = new ManufacturerDO();
			Manufacturer manf = program.getManufacturer();
			if(manf!=null)
			{
				manfDO.setName(manf.getManfName());
				manfDO.setId(manf.getManfId());
			}
			programDO.setManufacturerDO(manfDO);
			
			machineInfoDOList = new ArrayList<MachineInfoDO>();
			List<MachineInfo> machineInfoLst = program.getMachineInfos();
			if(machineInfoLst!=null)
			{
				
				logger.debug("machineInfoLst size "+machineInfoLst.size());
				for(MachineInfo macineInfo : machineInfoLst)
				{
					MachineInfoDO macInfDO = new MachineInfoDO();
					macInfDO.setModel(macineInfo.getModel());
					macInfDO.setMachineId(macineInfo.getMachineId());
					macInfDO.setModelYear((macineInfo.getModelYear()!=null)?macineInfo.getModelYear():0);
					macInfDO.setMachineId(macineInfo.getMachineId());
					
					machineInfoDOList.add(macInfDO);
				}
				programDO.setMachineInfoDOList(machineInfoDOList);
			}
			programDO.setDealerDO(dealerDO);
		
		}
	
		
		
		return programDO;
	}

	@Override
	public void deleteProgram(Long id) {
		Sprogram sProgram = programDAO.findOne(id);
		programDAO.delete(id);;
	}
	
	@Override
	@Transactional
	public long editProgram(ProgramDO program) {
		logger.debug("In editProgram : ");
		if(program != null)
		{
			Sprogram progEnt = programDAO.findOne(program.getPrId());
			Timestamp date = new Timestamp(new Date().getTime());
			
			/*sProgram.setPrDesc(programDO.getDesc());
			sProgram.setPrDesc(programDO.getDesc());
			sProgram.setDealer(dealerDAO.findOne(Long.valueOf(programDO.getDealerDO().getId())));
	
			sProgram.setPrLastUpdate(date);*/
			
			progEnt.setPrName(program.getName());
			progEnt.setPrDesc(program.getDesc());
			progEnt.setPrIsActive(program.getIsActive());
			progEnt.setPrAServicing(program.getaServicing());
			progEnt.setPrCondition(program.getCondition());
			progEnt.setPrCType(program.getcType());
			//logger.debug("-->"+program.getDealerDO());
			if(program.getDealerDO() != null)
			{
				progEnt.setDealer(dealerDAO.findOne(Long.valueOf(program.getDealerDO().getId())));
			}
			//progEnt.setDealer(dealerDAO.findOne(Long.valueOf(14)));
			progEnt.setPrGroup(program.getGroup());
			progEnt.setPrDeductible(program.getDeductible());
			progEnt.setPrCType(program.getcType());
			progEnt.setPrCHours(program.getcHours());
			progEnt.setPrCTerm(program.getcTerm());
			progEnt.setPrCost(program.getCost());
			progEnt.setPrLol(program.getLol());
			progEnt.setPrIsArchive((byte)0);
			if(program.getManufacturerDO() != null)
			{
				progEnt.setManufacturer(manufacturerDAO.findOne(Long.valueOf(program.getManufacturerDO().getId())));
			}
			//progEnt.setManufacturer(manufacturerDAO.findOne(Long.valueOf(8)));
			//TODO To be implemented after dealer services
			//progEnt.setDealer(dealer);
			
			//List<MachineInfoDO> machineInfoDOs =  program.getMachineInfoDOList();
			
			List<MachineInfoDO> machineInfoDOs =  program.getMachineModelList();
			
			if(machineInfoDOs!=null)
			{
				logger.debug("machineInfoDOs size "+machineInfoDOs.size());
			}
			
			MachineInfo machineInfo = null;
			List<MachineInfo> machineInfoList = new ArrayList<MachineInfo>();
			for(MachineInfoDO machineInfoDO : machineInfoDOs)
			{
				logger.debug("--machineInfoDO--"+machineInfoDO.getMachineId());
				machineInfo = machineInfoDAO.findOne(machineInfoDO.getMachineId());
				if(machineInfo!=null)
				{
					//logger.debug("--machineInfo --"+machineInfo.getMachineId());
					machineInfoList.add(machineInfo);
				}
			}
			
			progEnt.setMachineInfos(machineInfoList);
			
			progEnt.setPrLastUpdate(date);
			
			progEnt = programDAO.save(progEnt);
			
			return progEnt.getPrId();
		}
		return 0;
	}
	
	@Override
	public List<MachineInfoDO> getModelByCodes(List<Long> modelLst) {
		logger.debug("Inside getModelByCodes()");
		List<MachineInfo>  machineInfoList =  machineInfoDAO.findMachineInfoById(modelLst);
		List<MachineInfoDO> machineInfoDOList = null;
		if(machineInfoList != null && !machineInfoList.isEmpty()){
			machineInfoDOList = new ArrayList<MachineInfoDO>();
			MachineInfoDO machineInfoDO = null;
			MachineInfo machineModel = null;
			long manfId = 0;
			ManufacturerDO manfDO = null;
			Iterator<MachineInfo> it = machineInfoList.iterator();
			while(it.hasNext()){
				machineInfoDO = new MachineInfoDO();
				machineModel = it.next();
				//machineModelDO.setModelId(machineModel.getModel());
				if(manfId == 0)
				{
					manfId= machineModel.getManufacturer().getManfId();
				}
				
				if(manfId != 0 && manfId!=machineModel.getManufacturer().getManfId())
				{
					logger.debug("Model selected from different Manufacturer");
					machineInfoDOList = null;
					break;
				}
				
				machineInfoDO.setModel(machineModel.getModel());
				machineInfoDO.setMachineId(machineModel.getMachineId());
				machineInfoDO.setMachineType(machineModel.getMachineType().getMachineType());
				machineInfoDO.setLol(machineModel.getGroupConstant().getLol());
				machineInfoDO.setGroupId(Integer.valueOf(String.valueOf(machineModel.getGroupConstant().getGroupId())));
				
				Manufacturer manf = manufacturerDAO.findOne(machineModel.getManufacturer().getManfId());
				if(manf!=null)
				{
					manfDO = new ManufacturerDO();
					manfDO.setName(manf.getManfName());
					manfDO.setId(manf.getManfId());
				}
				machineInfoDO.setManufacturerDO(manfDO);
				
				machineInfoDOList.add(machineInfoDO);
			}
		}
		return machineInfoDOList;
	}
	
	private ReportDO getQuoteReportDO(QuoteDO quoteDO) throws Exception{
		ReportDO reportDO = new ReportDO();
		
		//SimpleDateFormat reportDateFormat = new SimpleDateFormat("MM/dd/yyyy");
		Locale locale = new Locale("en", "US");
		NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(locale);
		
		reportDO.setQuoteDate((quoteDO.getEstSaleDate() != null)?dateFormat.format(quoteDO.getEstSaleDate()):dateFormat.format(new Date()));
		
		//TODO with created_by field
		Dealer dealer = dealerDAO.findOne(quoteDO.getDealerDO().getId());
		if(dealer != null){
			reportDO.setDealerName(dealer.getName());
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
		reportDO.setAddress(quoteDO.getCustomerInfoDO().getAddress()+", "+quoteDO.getCustomerInfoDO().getState()+" "+quoteDO.getCustomerInfoDO().getZip());
		reportDO.setCustName(quoteDO.getCustomerInfoDO().getName());
		reportDO.setOutStandingDesc(AggConstants.QUOTE_REPORT_OUT_STANDING_DESC);
		reportDO.setManufacturerName(quoteDO.getManufacturerDO().getName());	
		reportDO.setModelName(quoteDO.getMachineInfoDO().getModel());
		reportDO.setModelSerialNo(quoteDO.getSerialNumber());
		//TODO
		reportDO.setEquipment("");
		//TODO
		reportDO.setRetailPrice("");
		reportDO.setCurrentHours(quoteDO.getMachineMeterHours()+"");
		if(quoteDO.getCoverageEndDate() != null && quoteDO.getCoverageEndDate().after(new Date())){
			reportDO.setMachineStatus(AggConstants.MACHINE_STATUS_NEW);
		}else{
			reportDO.setMachineStatus(AggConstants.MACHINE_STATUS_USED);
		}
		
		reportDO.setEmail(quoteDO.getCustomerInfoDO().getEmail());
		reportDO.setPhone(quoteDO.getCustomerInfoDO().getPhone());
		reportDO.setCoverageDesc(AggConstants.QUOTE_REPORT_COVERAGE_DESC);
		reportDO.setCoverageTerm(quoteDO.getcTerm());
		reportDO.setCoverageHours(quoteDO.getcHours());
		reportDO.setDeductibleAmount(currencyFormat.format(quoteDO.getDeductible()));
		if(quoteDO.getcType() != null && !quoteDO.getcType().isEmpty()){
			reportDO.setCoverageType((quoteDO.getcType().equalsIgnoreCase(AggConstants.COVERAGE_TYPE_PT)) ? AggConstants.COVERAGE_TYPE_PT_DESC
					: (quoteDO.getcType().equalsIgnoreCase(AggConstants.COVERAGE_TYPE_PH)) ? AggConstants.COVERAGE_TYPE_PH_DESC
							: (quoteDO.getcType().equalsIgnoreCase(AggConstants.COVERAGE_TYPE_PL)) ? AggConstants.COVERAGE_TYPE_PL_DESC : "");
		}
		reportDO.setPowerTrainMonths(AggConstants.COVERAGE_TYPE_PT_MONTHS);
		reportDO.setPowerTrainHours(AggConstants.COVERAGE_TYPE_PT_HOURS);
		reportDO.setHydraulicMonths(AggConstants.COVERAGE_TYPE_PH_MONTHS);
		reportDO.setHydraulicHours(AggConstants.COVERAGE_TYPE_PH_HOURS);
		reportDO.setFullMachineHours(AggConstants.COVERAGE_TYPE_PL_HOURS);
		reportDO.setFullMachineMonths(AggConstants.COVERAGE_TYPE_PL_MONTHS);
		reportDO.setWarrantyEndDate((quoteDO.getCoverageEndDate() != null)?dateFormat.format(quoteDO.getCoverageEndDate()):"");
		reportDO.setLimitOfLiability(currencyFormat.format(quoteDO.getLol()));
		//TODO
		reportDO.setPrice(currencyFormat.format(quoteDO.getCost()));
		reportDO.setQuotePrice(currencyFormat.format(quoteDO.getCost()));
		//TODO
		reportDO.setDealerMarkup(currencyFormat.format(0));
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
}

