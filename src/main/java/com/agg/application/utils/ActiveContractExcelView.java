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


public class ActiveContractExcelView extends AbstractExcelView
{
@SuppressWarnings("unchecked")
protected void buildExcelDocument(Map<String, Object> model,
        HSSFWorkbook workbook,
        HttpServletRequest request,
        HttpServletResponse response)
{
	List<Object[]> contractReport = (List<Object[]>)model.get("workbook");
	int rowNum = 0;
	HSSFSheet sheet = null;
	if(contractReport != null && !contractReport.isEmpty())
	{
		sheet = workbook.createSheet("Contract Report");
		
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
		
		for(Object[] datatype : contractReport)
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
}
}