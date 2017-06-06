package com.agg.application.service.impl;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
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
			
			for(Object[] datatype : claimReport)
			{
				Row row = sheet.createRow(rowNum++);
				int colNum = 0;
				
				for(Object field : datatype)
				{
					Cell cell = row.createCell(colNum++);
					if(field instanceof String)
					{
						cell.setCellValue((String) field);
					}
					if(field instanceof Integer)
					{
						cell.setCellValue((Integer) field);
					}
				}
				
			}
			
			
		}
		
		try{
			FileOutputStream outputStream = new FileOutputStream("D:/Myfile.xlsx");
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
