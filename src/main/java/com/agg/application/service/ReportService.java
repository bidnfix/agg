package com.agg.application.service;

import java.util.List;
import java.util.Map;

import com.agg.application.model.GraphReportDO;

public interface ReportService {

	List<Object[]> generateClaimReport();
	
	List<Object[]> generateQuoteReport();
	
	List<Object[]> generateActiveContractsReport();
	
	Map<Integer, List<GraphReportDO>> getContractReportDetails();
	
}
