package com.agg.application.service.impl;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agg.application.dao.ClaimsDAO;
import com.agg.application.dao.QuoteDAO;
import com.agg.application.service.ReportService;

@Service
public class ReportServiceImpl implements ReportService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private ClaimsDAO claimsDAO;
	
	@Autowired
	private QuoteDAO quoteDAO;	
	
	
	@Override
	public Object generateClaimReport() {
		logger.info("Inside generateClaimReport()");
		
		XSSFWorkbook workbook = null;
		XSSFSheet sheet = null;
		int rowNum = 0;
		
		List<Object[]> claimReport = claimsDAO.findAllClaimsForReport();
		
		
		if(claimReport != null || claimReport.size() != 0)
		{
			logger.debug(" claimReport "+claimReport.size());
			workbook = new XSSFWorkbook();
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
		
		/*try{
			FileOutputStream outputStream = new FileOutputStream("D:/ClaimReport.xlsx");
			workbook.write(outputStream);
			workbook.close();
		}catch(FileNotFoundException e)
		{
			e.printStackTrace();
		}catch(IOException e)
		{
			e.printStackTrace();
		}*/
		
		return workbook;
	}
	
	
	@Override
	public Object generateQuoteReport() {
		
		logger.info("Inside generateQuoteReport()");
		XSSFWorkbook workbook = null;
		XSSFSheet sheet = null;
		int rowNum = 0;
		
		List<Object[]> quoteReport = quoteDAO.findAllQuotesForReport();
		
		
		if(quoteReport != null || quoteReport.size() != 0)
		{
			logger.debug(" quoteReport "+quoteReport.size());
			workbook = new XSSFWorkbook();
			sheet = workbook.createSheet("Claim Report");
			
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
			FileOutputStream outputStream = new FileOutputStream("D:/QuoteReport.xlsx");
			workbook.write(outputStream);
			workbook.close();
		}catch(FileNotFoundException e)
		{
			e.printStackTrace();
		}catch(IOException e)
		{
			e.printStackTrace();
		}
		
		return workbook;
	}
	
}
