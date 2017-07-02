package com.agg.application.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agg.application.dao.CheckDAO;
import com.agg.application.dao.ClaimsDAO;
import com.agg.application.dao.ContractsDAO;
import com.agg.application.dao.QuoteDAO;
import com.agg.application.model.GraphReportDO;
import com.agg.application.service.ReportService;
import com.agg.application.utils.AggConstants;

@Service
public class ReportServiceImpl implements ReportService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private ClaimsDAO claimsDAO;
	
	@Autowired
	private QuoteDAO quoteDAO;
	
	@Autowired
	private ContractsDAO contractDAO;
	
	@Autowired
	private CheckDAO checkDAO;
	
	
	@Override
	public List<Object[]> generateClaimReport() {
		logger.info("Inside generateClaimReport()");
		
/*		HSSFWorkbook workbook = null;
		HSSFSheet sheet = null;
		int rowNum = 0;*/
		
		return claimsDAO.findAllClaimsForReport();
		
		
		/*if(claimReport != null || claimReport.size() != 0)
		{
			logger.debug(" claimReport "+claimReport.size());
			workbook = new HSSFWorkbook();
			sheet = workbook.createSheet("Claim Report");
			
			//Creating header
			Row header = sheet.createRow(rowNum++);
			header.createCell(0).setCellValue("Claim_Number");
			header.createCell(1).setCellValue("contract_id");
			header.createCell(2).setCellValue("Dealer_Name");
			header.createCell(3).setCellValue("Serial_Number");
			header.createCell(4).setCellValue("Failure_Date");
			header.createCell(5).setCellValue("Reported_On");
			header.createCell(6).setCellValue("work_order");
			header.createCell(7).setCellValue("Hours_at_Breakdown");
			header.createCell(8).setCellValue("Customer_Complaint");
			header.createCell(9).setCellValue("Cause_of_Failure");
			header.createCell(10).setCellValue("Corrective_Action");
			header.createCell(11).setCellValue("is_archived");
			header.createCell(12).setCellValue("Claim_status");
			header.createCell(13).setCellValue("last_update");
			header.createCell(14).setCellValue("requested_other_charges1");
			header.createCell(15).setCellValue("requested_other_charges2");
			header.createCell(16).setCellValue("total_adjusted_parts_cost");
			header.createCell(17).setCellValue("total_adjusted_labor_cost");
			header.createCell(18).setCellValue("approved_other_charges1");
			header.createCell(19).setCellValue("approved_other_charges2");
			header.createCell(20).setCellValue("Check_Number");
			header.createCell(21).setCellValue("paid_date");
			header.createCell(22).setCellValue("created_by");
			header.createCell(23).setCellValue("created_date");
			header.createCell(24).setCellValue("Updated_By");
			
			for(Object[] datatype : claimReport)
			{
				Row row = sheet.createRow(rowNum++);
				int colNum = 0;
				
				for(Object field : datatype)
				{
					//if(field !=null)
					//logger.debug("Field type "+field +"  "+field.getClass().getSimpleName());
					//logger.debug("Field type "+field +"  "+(field instanceof java.util.Date));
					
					Cell cell = row.createCell(colNum++);
					
					if(field instanceof String)
					{
						cell.setCellValue((String) field);
					}
					if(field instanceof Integer)
					{
						cell.setCellValue((Integer) field);
					}
					if(field instanceof Byte)
					{
						cell.setCellValue((Byte) field);
					}
					if(field instanceof Double)
					{
						cell.setCellValue((Double) field);
					}
					if(field instanceof Float)
					{
						cell.setCellValue((Float) field);
					}
					if(field instanceof Date)
					{
						CellStyle cellStyle = workbook.createCellStyle();
						CreationHelper createHelper = workbook.getCreationHelper();
						cellStyle.setDataFormat(
						    createHelper.createDataFormat().getFormat("m/d/yy"));
						//cell = row.createCell(1);
						cell.setCellValue(new Date());
						
						
						
						cell.setCellValue((Date) field);
						cell.setCellStyle(cellStyle);
					}
					if(field instanceof Timestamp)
					{
						CellStyle cellStyle = workbook.createCellStyle();
						CreationHelper createHelper = workbook.getCreationHelper();
						cellStyle.setDataFormat(
						    createHelper.createDataFormat().getFormat("m/d/yy h:mm"));
						//cell = row.createCell(1);
						cell.setCellValue(new Date());
						
						cell.setCellValue((Timestamp) field);
						cell.setCellStyle(cellStyle);
					}
				}
			}
			
		}
		
		try{
			FileOutputStream outputStream = new FileOutputStream("D:/ClaimReport.xls");
			workbook.write(outputStream);
			workbook.close();
		}catch(FileNotFoundException e)
		{
			e.printStackTrace();
		}catch(IOException e)
		{
			e.printStackTrace();
		}*/
		
		//return claimReport;
	}
	
	
	@Override
	public List<Object[]> generateQuoteReport() {
		
		logger.info("Inside generateQuoteReport()");
		/*HSSFWorkbook workbook = null;
		HSSFSheet sheet = null;
		int rowNum = 0;
		
		//return quoteDAO.findAllQuotesForReport();
		
		List<Object[]> quoteReport = quoteDAO.findAllQuotesForReport();
		
		if(quoteReport != null || quoteReport.size() != 0)
		{
			logger.debug(" quoteReport "+quoteReport.size());
			workbook = new HSSFWorkbook();
			sheet = workbook.createSheet("Quote Report");
			
			//Creating header
			Row header = sheet.createRow(rowNum++);
			header.createCell(0).setCellValue("quote_id");
			header.createCell(1).setCellValue("status");
			header.createCell(2).setCellValue("is_archive");
			header.createCell(3).setCellValue("dealer_id");
			header.createCell(4).setCellValue("dealer_name");
			header.createCell(5).setCellValue("manf_expired");
			header.createCell(6).setCellValue("manf_end_date");
			header.createCell(7).setCellValue("manf_end_known");
			header.createCell(8).setCellValue("manf_verified");
			header.createCell(9).setCellValue("pt_months");
			header.createCell(10).setCellValue("pt_hours");
			header.createCell(11).setCellValue("h_months");
			header.createCell(12).setCellValue("h_hours");
			header.createCell(13).setCellValue("machine_months");
			header.createCell(14).setCellValue("machine_hours");
			header.createCell(15).setCellValue("other_prov");
			header.createCell(16).setCellValue("manf_id");
			header.createCell(17).setCellValue("manf_name");
			header.createCell(18).setCellValue("machine_model");
			header.createCell(19).setCellValue("group_id");
			header.createCell(20).setCellValue("machine_power");
			header.createCell(21).setCellValue("machine_serial");
			header.createCell(22).setCellValue("machine_retail_price");
			header.createCell(23).setCellValue("machine_meter_hours");
			header.createCell(24).setCellValue("machine_year");
			header.createCell(25).setCellValue("machine_uoe");
			header.createCell(26).setCellValue("machine_sale_date");
			header.createCell(27).setCellValue("dealer_markup_type");
			header.createCell(28).setCellValue("dealer_markup");
			header.createCell(29).setCellValue("deduct_amount");
			header.createCell(30).setCellValue("coverage_term");
			header.createCell(31).setCellValue("coverage_level_hours");
			header.createCell(32).setCellValue("coverage_type");
			header.createCell(33).setCellValue("coverage_price");
			header.createCell(34).setCellValue("create_date");
			header.createCell(35).setCellValue("last_update");
			header.createCell(36).setCellValue("base_price");
			header.createCell(37).setCellValue("lol");
			header.createCell(38).setCellValue("contract_cost");
			header.createCell(39).setCellValue("special_consideration");
			header.createCell(40).setCellValue("c_conditions");
			header.createCell(41).setCellValue("invoice_date");
			header.createCell(42).setCellValue("inception_date");
			header.createCell(43).setCellValue("expiration_date");
			header.createCell(44).setCellValue("expiration_hours");
			header.createCell(45).setCellValue("deal_history");
			
			
			
			for(Object[] datatype : quoteReport)
			{
				Row row = sheet.createRow(rowNum++);
				int colNum = 0;
				
				for(Object field : datatype)
				{
					if(field !=null)
					logger.debug("Field type "+field +"  "+field.getClass().getSimpleName());
					//logger.debug("Field type "+field +"  "+(field instanceof java.util.Date));
					
					Cell cell = row.createCell(colNum++);
					
					if(field instanceof String)
					{
						cell.setCellValue((String) field);
					}
					if(field instanceof Integer)
					{
						cell.setCellValue((Integer) field);
					}
					if(field instanceof Byte)
					{
						cell.setCellValue((Byte) field);
					}
					if(field instanceof Double)
					{
						cell.setCellValue((Double) field);
					}
					if(field instanceof Float)
					{
						cell.setCellValue((Float) field);
					}
					if(field instanceof Short)
					{
						cell.setCellValue((Short) field);
					}
					if(field instanceof Date)
					{
						CellStyle cellStyle = workbook.createCellStyle();
						CreationHelper createHelper = workbook.getCreationHelper();
						cellStyle.setDataFormat(
						    createHelper.createDataFormat().getFormat("m/d/yy"));
						//cell = row.createCell(1);
						cell.setCellValue(new Date());
						
						
						
						cell.setCellValue((Date) field);
						cell.setCellStyle(cellStyle);
					}
					if(field instanceof Timestamp)
					{
						CellStyle cellStyle = workbook.createCellStyle();
						CreationHelper createHelper = workbook.getCreationHelper();
						cellStyle.setDataFormat(
						    createHelper.createDataFormat().getFormat("m/d/yy h:mm"));
						//cell = row.createCell(1);
						cell.setCellValue(new Date());
						
						cell.setCellValue((Timestamp) field);
						cell.setCellStyle(cellStyle);
					}
				}
			}
			
		}
		
		try{
			FileOutputStream outputStream = new FileOutputStream("D:/QuoteReport.xls");
			workbook.write(outputStream);
			workbook.close();
		}catch(FileNotFoundException e)
		{
			e.printStackTrace();
		}catch(IOException e)
		{
			e.printStackTrace();
		}*/
		
		return quoteDAO.findAllQuotesForReport();
		
	}
	
	
	@Override
	public List<Object[]> generateActiveContractsReport() {
		logger.info("Inside generateClaimReport()");
		
/*		HSSFWorkbook workbook = null;
		HSSFSheet sheet = null;
		int rowNum = 0;*/
		
		return contractDAO.findAllActiveContractrReport();
	}


	@Override
	public Map<Integer, List<GraphReportDO>> getContractReportDetails() {
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		logger.debug("b4 year: "+year);
		year = year - AggConstants.REPORT_PREVIOUS_YEARS_COUNT;
		logger.debug("after year: "+year);
		List<Object[]> contractDataList = contractDAO.findContractDetails(AggConstants.ACTIVE, year);
		Map<Integer, List<GraphReportDO>> contracCountMap = null;
		if(contractDataList != null && !contractDataList.isEmpty()){
			logger.debug("contractDataList size: "+contractDataList.size());
			contracCountMap = new HashMap<Integer, List<GraphReportDO>>(); 
			List<GraphReportDO> graphReportDOList = null;
			GraphReportDO graphReportDO = null;
			for(Object[] intArr : contractDataList){
				graphReportDOList = contracCountMap.get(Integer.valueOf(intArr[0].toString()));
				if(graphReportDOList == null){
					graphReportDOList = new ArrayList<GraphReportDO>();
					contracCountMap.put(Integer.valueOf(intArr[0].toString()), graphReportDOList);
				}
				
				graphReportDO = new GraphReportDO();
				graphReportDO.setMonth(Integer.valueOf(intArr[1].toString()));
				graphReportDO.setTotal(Integer.valueOf(intArr[2].toString()));
				
				graphReportDOList.add(graphReportDO);
				
				logger.debug("year: "+intArr[0].toString()+", month: "+intArr[1].toString()+" & total: "+intArr[2].toString());
			}
			
			logger.debug("contracCountMap size: "+contracCountMap.size());
		}
		return contracCountMap;
	}
	
	@Override
	public Map<Integer, List<GraphReportDO>> getContractReportChkAmtDetails() {
		List<Object[]> checkDataList = quoteDAO.findContractAmountDetails(AggConstants.B_QUOTE_STATUS_CLOSED, AggConstants.REPORT_PREVIOUS_YEARS_COUNT);
		Map<Integer, List<GraphReportDO>> checkAmountMap = null;
		if(checkDataList != null && !checkDataList.isEmpty()){
			logger.debug("checkDataList size: "+checkDataList.size());
			checkAmountMap = new HashMap<Integer, List<GraphReportDO>>(); 
			List<GraphReportDO> graphReportDOList = null;
			GraphReportDO graphReportDO = null;
			for(Object[] objArr : checkDataList){
				graphReportDOList = checkAmountMap.get(Integer.valueOf(objArr[0].toString()));
				if(graphReportDOList == null){
					graphReportDOList = new ArrayList<GraphReportDO>();
					checkAmountMap.put(Integer.valueOf(objArr[0].toString()), graphReportDOList);
				}
				
				graphReportDO = new GraphReportDO();
				graphReportDO.setMonth(Integer.valueOf(objArr[1].toString()));
				graphReportDO.setCheckAmount(Double.valueOf(objArr[2].toString()));
				
				graphReportDOList.add(graphReportDO);
				
				//logger.debug("year: "+objArr[0].toString()+", month: "+objArr[1].toString()+" & total: "+objArr[2].toString());
			}
			
			logger.debug("checkAmountMap size: "+checkAmountMap.size());
		}
		return checkAmountMap;
	}


	@Override
	public List<Object[]> getContractClaimsReportDetails(int topClaimsCount) {
		
		List<Object[]> topClaimsList = contractDAO.findActiveContractClaimsReporDetails();
		if(topClaimsList != null && !topClaimsList.isEmpty()){
			logger.info("topClaimsList size: "+topClaimsList.size());
		}
		
		return topClaimsList;
	}
	
}
