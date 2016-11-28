package com.agg.application.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agg.application.dao.ReportBugDAO;
import com.agg.application.entity.BugReport;
import com.agg.application.model.BugDO;
import com.agg.application.service.ReportBugService;
import com.google.common.collect.Lists;

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
		bug.setDiscovered(date);
		bug.setUserAgent("NA");
		bug.setFixBy(date);
		bug.setReportedBy(1);
		bug.setCreatedOn(date);
		bug.setUrl(bugDO.getUrl());
		bug.setNotes(bugDO.getNotes());
		bug = reportBugDAO.save(bug);
		return bug.getId();
	}
	
	@Override
	public Integer getBugId() {
		
		Integer bugId = reportBugDAO.findMaxReportId();
		logger.debug("bugId -- "+bugId);
		
		return bugId;
	}
	
	@Override
	public List<BugDO> getBugs(){
		
		List<BugReport>  bugInfoList =  Lists.newArrayList(reportBugDAO.findAll());

		List<BugDO> bugDOList = null;
		if(!bugInfoList.isEmpty()){
			logger.debug("bugInfoList size:"+bugInfoList.size());
			bugDOList = new ArrayList<BugDO>();
			BugDO bugDO = null;
			BugReport bugReport = null;
			
			Iterator<BugReport> it = bugInfoList.iterator();
			while(it.hasNext()){
				bugDO = new BugDO();
				bugReport = it.next();
				
				logger.debug("bugReport.getId() " + bugReport.getId());
						    	
				bugDO.setId(bugReport.getId());
				bugDO.setDescription(bugReport.getDescription());
				bugDO.setDiscovered(bugReport.getDiscovered());
				bugDO.setFixBy(bugReport.getFixBy());
				bugDO.setNotes(bugReport.getNotes());
				bugDO.setPriority(bugReport.getPriority());
				bugDO.setStatus(bugReport.getStatus());
				bugDO.setUrl(bugReport.getUrl());
				bugDO.setUserAgent(bugReport.getUserAgent());
				
				bugDOList.add(bugDO);
			}
		}
		return bugDOList;
	}
	
	@Override
	public BugDO getBug(long id){
		
		BugReport  bugReport =  reportBugDAO.findOne(id);

			BugDO bugDO = null;
			if(bugReport != null){
				bugDO = new BugDO();
				
				logger.debug("bugReport.getId() " + bugReport.getId());
				bugDO.setId(bugReport.getId());
				bugDO.setDescription(bugReport.getDescription());
				bugDO.setDiscovered(bugReport.getDiscovered());
				bugDO.setFixBy(bugReport.getFixBy());
				bugDO.setNotes(bugReport.getNotes());
				bugDO.setPriority(bugReport.getPriority());
				bugDO.setStatus(bugReport.getStatus());
				bugDO.setUrl(bugReport.getUrl());
				bugDO.setUserAgent(bugReport.getUserAgent());
			}
		
		return bugDO;
	}
	
	
	}
