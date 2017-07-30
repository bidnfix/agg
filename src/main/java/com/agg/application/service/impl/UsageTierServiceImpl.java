package com.agg.application.service.impl;

import java.util.Date;
import java.sql.Timestamp;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
	public List<UsageTierDO> getUsageTier() {
		logger.debug("Inside getUsageTier()");
		List<UsageTierDO> usageTierLst = usageTierDAO.findAllUsageTier();
	
		return usageTierLst;
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
/*
	@Override
	public UseOfEquipmentDO getUseOfEquip(long id) {
		logger.debug("In getUseOfEquip");
		UseOfEquip useOfEquip = usageTierDAO.findOne(id);
		UseOfEquipmentDO useOfEquipmentDO = null;
		
		if(useOfEquip != null){
			useOfEquipmentDO = new UseOfEquipmentDO();
			
			useOfEquipmentDO.setId(useOfEquip.getId());
			useOfEquipmentDO.setDiscount(useOfEquip.getDiscount());
			useOfEquipmentDO.setEquipName(useOfEquip.getEquipName());
		}
		
		return useOfEquipmentDO;
	}
	
	@Override
	@Transactional
	public long editEquipment(UseOfEquipmentDO equipmentDO) throws Exception{
		logger.debug("In editEquipment : "+equipmentDO.getId());
		UseOfEquip useOfEquip = usageTierDAO.findOne(equipmentDO.getId());
		Timestamp date = new Timestamp(new Date().getTime());
		
		
		

		useOfEquip.setEquipName(equipmentDO.getEquipName());
		useOfEquip.setDiscount(equipmentDO.getDiscount());
		
		try {
			useOfEquip = usageTierDAO.save(useOfEquip);
		}
	    catch (DataIntegrityViolationException e) {
	    	logger.error("Equipment already exist");
	    }	
		
		return useOfEquip.getId();
	}*/
}
