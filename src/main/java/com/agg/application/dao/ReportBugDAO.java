package com.agg.application.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import com.agg.application.entity.BugReport;
import com.agg.application.entity.Dealer;

@Component
public interface ReportBugDAO extends CrudRepository<BugReport, Long> {
	
	@Query("select max(id) from BugReport")
	public Integer findMaxReportId();
	
	
}
