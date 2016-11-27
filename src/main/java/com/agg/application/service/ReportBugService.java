package com.agg.application.service;

import java.util.List;

import com.agg.application.model.BugDO;
import com.agg.application.model.QuoteDO;

public interface ReportBugService {

	public Long saveBug(BugDO bugDO);
	public Integer getBugId();
	public List<BugDO> getBugs();
	
}
