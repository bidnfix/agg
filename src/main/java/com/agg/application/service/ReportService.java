package com.agg.application.service;

import java.util.List;

public interface ReportService {

	List<Object[]> generateClaimReport();
	
	List<Object[]> generateQuoteReport();
	
}
