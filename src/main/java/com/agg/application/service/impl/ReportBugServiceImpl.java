package com.agg.application.service.impl;

import java.sql.Timestamp;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agg.application.dao.ReportBugDAO;
import com.agg.application.entity.BugReport;
import com.agg.application.model.BugDO;
import com.agg.application.service.ReportBugService;

@Service
public class ReportBugServiceImpl implements ReportBugService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private ReportBugDAO reportBugDAO;
	
	
	@Override
	public Long saveBug(BugDO bugDO) {
		logger.debug("In saveBug");
		Timestamp date = new Timestamp(new Date().getTime());
		BugReport bug = new BugReport();
		
		bug.setDescription(bugDO.getDescription());
		bug.setPriority(bugDO.getPriority());
		bug.setStatus(bugDO.getStatus());
		bug.setCreatedOn(date);
		bug = reportBugDAO.save(bug);
		return bug.getId();
	}
	
	
	}
