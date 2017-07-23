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


public class QuoteExcelView extends AbstractExcelView
{
@SuppressWarnings("unchecked")
protected void buildExcelDocument(Map<String, Object> model,
        HSSFWorkbook workbook,
        HttpServletRequest request,
        HttpServletResponse response)
{
	List<Object[]> quoteReport = (List<Object[]>)model.get("workbook");

	HSSFSheet sheet = null;
	int rowNum = 0;
	
	if(quoteReport != null && !quoteReport.isEmpty())
	{
		logger.debug(" quoteReport "+quoteReport.size());
		sheet = workbook.createSheet("Invoice Quote Report");
		
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
		header.createCell(35).setCellValue("Invoice_Price");
		header.createCell(36).setCellValue("lol");
		header.createCell(37).setCellValue("contract_cost");
		header.createCell(38).setCellValue("special_consideration");
		header.createCell(39).setCellValue("c_conditions");
		header.createCell(40).setCellValue("invoice_date");
		header.createCell(41).setCellValue("inception_date");
		header.createCell(42).setCellValue("expiration_date");
		header.createCell(43).setCellValue("expiration_hours");
		header.createCell(44).setCellValue("deal_history");
		header.createCell(45).setCellValue("last_update");
		
		CellStyle cellDateStyle = workbook.createCellStyle();
		CreationHelper createDateHelper = workbook.getCreationHelper();
		cellDateStyle.setDataFormat(createDateHelper.createDataFormat().getFormat("m/d/yy"));
		
		CellStyle cellTimestampStyle = workbook.createCellStyle();
		CreationHelper createTimestampHelper = workbook.getCreationHelper();
		cellTimestampStyle.setDataFormat(createTimestampHelper.createDataFormat().getFormat("m/d/yy h:mm"));
		
		Row row = null;
		int colNum = 0;
		Cell cell = null;
		
		for(Object[] datatype : quoteReport)
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
				if(field instanceof Integer)
				{
					cell.setCellValue((Integer) field);
				}
				if(field instanceof Boolean)
				{
					cell.setCellValue((Boolean) field);
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