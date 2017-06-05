package com.agg.application.service.impl;

import java.util.List;

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
		
		List<Object[]> claimReport = claimsDAO.findAllClaimsForReport();
		logger.debug(" claimReport "+claimReport.size());
		
		
		return true;
	}
	
	
}
