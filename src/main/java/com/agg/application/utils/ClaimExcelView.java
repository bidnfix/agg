package com.agg.application.utils;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.web.servlet.view.document.AbstractExcelView;


public class ClaimExcelView extends AbstractExcelView
{
@SuppressWarnings("unchecked")
protected void buildExcelDocument(Map<String, Object> model,
        HSSFWorkbook workbook,
        HttpServletRequest request,
        HttpServletResponse response)
{
	List<Object[]> claimReport = (List<Object[]>)model.get("workbook");
	int rowNum = 0;
	HSSFSheet sheet = null;
	if(claimReport != null && !claimReport.isEmpty())
	{
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
		
		CellStyle cellDateStyle = workbook.createCellStyle();
		CreationHelper createDateHelper = workbook.getCreationHelper();
		cellDateStyle.setDataFormat(createDateHelper.createDataFormat().getFormat("m/d/yy"));
		
		CellStyle cellTimestampStyle = workbook.createCellStyle();
		CreationHelper createTimestampHelper = workbook.getCreationHelper();
		cellTimestampStyle.setDataFormat(createTimestampHelper.createDataFormat().getFormat("m/d/yy h:mm"));
		
		Row row = null;
		int colNum = 0;
		Cell cell = null;
		for(Object[] datatype : claimReport)
		{
			row = sheet.createRow(rowNum++);
			colNum = 0;
			
			for(Object field : datatype)
			{
				//if(field !=null)
				//logger.debug("Field type "+field +"  "+field.getClass().getSimpleName());
				//logger.debug("Field type "+field +"  "+(field instanceof java.util.Date));
				
				cell = row.createCell(colNum++);
				
				if(field instanceof String)
				{
					cell.setCellValue((String) field);
				}
				if(field instanceof Boolean)
				{
					cell.setCellValue((Boolean) field);
				}
				if(field instanceof Integer)
				{
					cell.setCellValue((Integer) field);
				}
				if(field instanceof Byte)
				{
					cell.setCellValue((Byte) field);
				}
				if(field instanceof Short)
				{
					cell.setCellValue((Short) field);
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
					cell.setCellValue((Date) field);
					cell.setCellStyle(cellDateStyle);
				}
				if(field instanceof Timestamp)
				{
					cell.setCellValue((Timestamp) field);
					cell.setCellStyle(cellTimestampStyle);
				}
			}
		}
		
	}
}
}