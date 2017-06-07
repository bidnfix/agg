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
import com.agg.application.service.ReportService;

@Service
public class ReportServiceImpl implements ReportService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private ClaimsDAO claimsDAO;
	
	
	@Override
	public boolean generateClaimReport() {
		
		XSSFWorkbook workbook = null;
		XSSFSheet sheet = null;
		int rowNum = 0;
		
		List<Object[]> claimReport = claimsDAO.findAllClaimsForReport();
		logger.debug(" claimReport "+claimReport.size());
		
		if(claimReport != null || claimReport.size() != 0)
		{
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
						    createHelper.createDataFormat().getFormat("m/d/yy h:mm"));
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
			FileOutputStream outputStream = new FileOutputStream("D:/ClaimReport.xlsx");
			workbook.write(outputStream);
			workbook.close();
		}catch(FileNotFoundException e)
		{
			e.printStackTrace();
		}catch(IOException e)
		{
			e.printStackTrace();
		}
		
		
		
		
		return true;
	}
	
	
}
