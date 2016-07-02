package com.agg.application.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import com.agg.application.entity.BugReport;

@Component
public interface ReportBugDAO extends CrudRepository<BugReport, Long> {
	
	
	
}
