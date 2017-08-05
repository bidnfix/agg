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

import com.agg.application.dao.OEMWarrantyTierDAO;
import com.agg.application.entity.OEMWarrantyTier;
import com.agg.application.model.OEMWarrantyTierDO;
import com.agg.application.service.OEMWarrantyTierService;

@Service
public class OEMWarrantyTierServiceImpl implements OEMWarrantyTierService{

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	
	@Autowired
	private OEMWarrantyTierDAO oemWarrantyTierDAO;
	
	
	@Override
	public List<OEMWarrantyTierDO> getOEMWarrantyTiers() {
		logger.debug("Inside getOEMWarrantyTiers()");
		List<OEMWarrantyTierDO> oemWarrantyTierLst = oemWarrantyTierDAO.findAllOEMWarrantyTier();
	
		return oemWarrantyTierLst;
	}
	
	@Override
	public OEMWarrantyTierDO getOEMWarrantyTier(long id) {
		logger.debug("Inside getOEMWarrantyTier()");
		OEMWarrantyTierDO OEMWarrantyTierDO = null;
		
		OEMWarrantyTier OEMWarrantyTier = oemWarrantyTierDAO.findOne(id);
		if(OEMWarrantyTier != null)
		{
			OEMWarrantyTierDO = new OEMWarrantyTierDO();
			OEMWarrantyTierDO.setId(OEMWarrantyTier.getId());
			OEMWarrantyTierDO.setWarrantyFrom(OEMWarrantyTier.getWarrantyFrom());
			OEMWarrantyTierDO.setWarrantyTo(OEMWarrantyTier.getWarrantyTo());
			OEMWarrantyTierDO.setAdjFactor(OEMWarrantyTier.getAdjFactor());
		}
	
		return OEMWarrantyTierDO;
	}
	
	
	@Override
	@Transactional
	public long saveOEMWarrantyTier(OEMWarrantyTierDO OEMWarrantyTierDO) throws Exception{
		logger.debug("In saveOEMWarrantyTier");
		OEMWarrantyTier OEMWarrantyTier = new OEMWarrantyTier();
		Timestamp date = new Timestamp(new Date().getTime());
		
		//macineInfo.setModel((machineDO.getMachineModelDO().getModelId());
		OEMWarrantyTier.setWarrantyFrom(OEMWarrantyTierDO.getWarrantyFrom());
		OEMWarrantyTier.setWarrantyTo(OEMWarrantyTierDO.getWarrantyTo());
		OEMWarrantyTier.setAdjFactor(OEMWarrantyTierDO.getAdjFactor());
		
		//equipment.setLastUpdate(date);
		OEMWarrantyTier = oemWarrantyTierDAO.save(OEMWarrantyTier);
			
		logger.debug("OEMWarrantyTier.getId()"+OEMWarrantyTier.getId());
		return OEMWarrantyTier.getId();
	}
	@Override
	@Transactional
	public long editOEMWarrantyTier(OEMWarrantyTierDO oemWarrantyTierDO) throws Exception{
		logger.debug("In editOEMWarrantyTier : "+oemWarrantyTierDO.getAdjFactor());
		OEMWarrantyTier oemWarrantyTier = oemWarrantyTierDAO.findOne(oemWarrantyTierDO.getId());
		Timestamp date = new Timestamp(new Date().getTime());
		
		oemWarrantyTier.setWarrantyFrom(oemWarrantyTierDO.getWarrantyFrom());
		oemWarrantyTier.setWarrantyTo(oemWarrantyTierDO.getWarrantyTo());
		oemWarrantyTier.setAdjFactor(oemWarrantyTierDO.getAdjFactor());
		
		try {
			oemWarrantyTier = oemWarrantyTierDAO.save(oemWarrantyTier);
		}
	    catch (DataIntegrityViolationException e) {
	    	logger.error("OEMWarrantyTier already exist");
	    }	
		
		return oemWarrantyTier.getId();
	}
}
