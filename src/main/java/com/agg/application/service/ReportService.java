package com.agg.application.service;

import java.util.List;
import java.util.Map;

public interface ReportService {

	List<Object[]> generateClaimReport();
	
	List<Object[]> generateQuoteReport();
	
	List<Object[]> generateActiveContractsReport();
	
	Map<String, List<Object>> getContractReportDetails();
	
}
