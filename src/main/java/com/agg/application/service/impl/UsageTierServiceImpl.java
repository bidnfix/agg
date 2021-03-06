package com.agg.application.service.impl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.agg.application.dao.UsageTierDAO;
import com.agg.application.entity.UsageTier;
import com.agg.application.model.UsageTierDO;
import com.agg.application.service.UsageTierService;

@Service
public class UsageTierServiceImpl implements UsageTierService{

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	
	@Autowired
	private UsageTierDAO usageTierDAO;
	
	
	@Override
	public List<UsageTierDO> getUsageTiers() {
		logger.debug("Inside getUsageTiers()");
		List<UsageTierDO> usageTierLst = usageTierDAO.findAllUsageTier();
	
		return usageTierLst;
	}
	
	@Override
	public UsageTierDO getUsageTier(long id) {
		logger.debug("Inside getUsageTier()");
		UsageTierDO usageTierDO = null;
		
		UsageTier usageTier = usageTierDAO.findOne(id);
		if(usageTier != null)
		{
			usageTierDO = new UsageTierDO();
			usageTierDO.setId(usageTier.getId());
			usageTierDO.setUsageFrom(usageTier.getUsageFrom());
			usageTierDO.setUsageTo(usageTier.getUsageTo());
			usageTierDO.setAdjFactor(usageTier.getAdjFactor());
		}
	
		return usageTierDO;
	}
	
	
	@Override
	@Transactional
	public long saveUsageTier(UsageTierDO usageTierDO) throws Exception{
		logger.debug("In saveUsageTier");
		UsageTier usageTier = new UsageTier();
		Timestamp date = new Timestamp(new Date().getTime());
		
		//macineInfo.setModel((machineDO.getMachineModelDO().getModelId());
		usageTier.setUsageFrom(usageTierDO.getUsageFrom());
		usageTier.setUsageTo(usageTierDO.getUsageTo());
		usageTier.setAdjFactor(usageTierDO.getAdjFactor());
		
		//equipment.setLastUpdate(date);
		usageTier = usageTierDAO.save(usageTier);
			
		logger.debug("usageTier.getId()"+usageTier.getId());
		return usageTier.getId();
	}
	@Override
	@Transactional
	public long editUsageTier(UsageTierDO usageTierDO) throws Exception{
		logger.debug("In editUsageTier : "+usageTierDO.getAdjFactor());
		UsageTier usageTier = usageTierDAO.findOne(usageTierDO.getId());
		Timestamp date = new Timestamp(new Date().getTime());
		
		usageTier.setUsageFrom(usageTierDO.getUsageFrom());
		usageTier.setUsageTo(usageTierDO.getUsageTo());
		usageTier.setAdjFactor(usageTierDO.getAdjFactor());
		
		try {
			usageTier = usageTierDAO.save(usageTier);
		}
	    catch (DataIntegrityViolationException e) {
	    	logger.error("UsageTier already exist");
	    }	
		
		return usageTier.getId();
	}
	
	@Override
	@Transactional
	public long deleteUsageTier(Long id) throws Exception{
		logger.debug("In deleteUsageTier : "+id);
		//UsageTier usageTier = usageTierDAO.findOne(id);
		
		try {
			 usageTierDAO.delete(id);
		}
	    catch (DataIntegrityViolationException e) {
	    	logger.error("UsageTier already exist");
	    }	
		
		return 1;
	}

	@Override
	public double getUsageTierAdjFactor(long meterHours) {
		double adjFactor = 0;
		List<Double> adjFactorList = usageTierDAO.getUsageTierAdjFactor(meterHours);
		if(adjFactorList != null && !adjFactorList.isEmpty()){
			if(adjFactorList.get(0) != null){
				adjFactor = adjFactorList.get(0);
			}
		}
		
		return adjFactor;
	}
}
