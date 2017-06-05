package com.agg.application.service.impl;

import java.util.List;
import java.util.Set;

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
		
		List<Object[]> claimReport = claimsDAO.findAllClaimsForReport();
		logger.debug(" claimReport "+claimReport.size());
		
		if(claimReport != null || claimReport.size() != 0)
		{
			workbook = new XSSFWorkbook();
			sheet = workbook.createSheet("Claim Report");
			
			
		}
		
		
		
		
		return true;
	}
	
	
}
