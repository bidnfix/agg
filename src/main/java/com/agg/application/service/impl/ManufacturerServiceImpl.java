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
import org.springframework.transaction.annotation.Transactional;

import com.agg.application.dao.ManufacturerDAO;
import com.agg.application.entity.MachineInfo;
import com.agg.application.entity.Manufacturer;
import com.agg.application.model.MachineInfoDO;
import com.agg.application.model.ManufacturerDO;
import com.agg.application.service.ManufacturerService;
import com.google.common.collect.Lists;

@Service
public class ManufacturerServiceImpl implements ManufacturerService{

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private ManufacturerDAO manufacturerDAO;
	
	@Override
	public List<ManufacturerDO> getManufacturers() {
		logger.debug("Inside getManufacturers()");
		
		List<ManufacturerDO> manfDOLst = null;
		
		List<Manufacturer> manufacturerLst = Lists.newArrayList(manufacturerDAO.findAll());
		
		if(!manufacturerLst.isEmpty())
		{
			Manufacturer manufacturer = null;
			ManufacturerDO manufacturerDO = null;
			manfDOLst = new ArrayList<ManufacturerDO>();
			Iterator<Manufacturer> it = manufacturerLst.iterator();
			while(it.hasNext()){
				manufacturerDO = new ManufacturerDO();
				manufacturer = it.next();
				manufacturerDO.setId(manufacturer.getManfId());
				manufacturerDO.setName(manufacturer.getManfName());
				manufacturerDO.setAdjFactor(manufacturer.getAdjFactor());
				manfDOLst.add(manufacturerDO);
			}
		}
		
		return manfDOLst;
	}
	
	@Override
	public ManufacturerDO getManufacturer(long id) {
		logger.debug("Inside getManufacturer()");
		ManufacturerDO manufacturerDO = null;
		//MachineInfoDO machineInfoDO = null;
		List<MachineInfoDO> machineInfoDOLst = null;
		
		Manufacturer manufacturer = manufacturerDAO.findOne(id);
		if(manufacturer != null)
		{
			machineInfoDOLst = new ArrayList<MachineInfoDO>();
			manufacturerDO = new ManufacturerDO();
			manufacturerDO.setId(manufacturer.getManfId());
			manufacturerDO.setName(manufacturer.getManfName());
			manufacturerDO.setAdjFactor(manufacturer.getAdjFactor());
			//manufacturerDO.setMachineInfo(manufacturer.getMachineInfos());
			
			if(!manufacturer.getMachineInfos().isEmpty())
			{
				List<MachineInfo> machineInfoLst = manufacturer.getMachineInfos();
				
				Iterator<MachineInfo> it = machineInfoLst.iterator();
				MachineInfoDO machineInfoDO = null;
				MachineInfo machineInfo = null;
				
				while(it.hasNext())
				{
					machineInfoDO = new MachineInfoDO();
					machineInfo = it.next();
					machineInfoDO.setMachineType(machineInfo.getMachineType().getMachineType());
					machineInfoDO.setModel(machineInfo.getModel());
					machineInfoDO.setePower(machineInfo.getePower());
					machineInfoDO.setGroupId((int)machineInfo.getGroupConstant().getGroupId());
					machineInfoDOLst.add(machineInfoDO);
				}
			}
			manufacturerDO.setMachineInfoDO(machineInfoDOLst);
			
			//logger.debug("manufacturer.getMachineInfos() "+manufacturer.getMachineInfos().size());
		}
	
		return manufacturerDO;
	}
	
	
	@Override
	@Transactional
	public long saveManufacturer(ManufacturerDO manufacturerDO) throws Exception{
		logger.debug("In saveManufacturer "+manufacturerDO.getAdjFactor());
		Manufacturer manufacturer = new Manufacturer();
		Timestamp date = new Timestamp(new Date().getTime());
		
		//macineInfo.setModel((machineDO.getMachineModelDO().getModelId());
		manufacturer.setManfName(manufacturerDO.getName());
		manufacturer.setAdjFactor(manufacturerDO.getAdjFactor());
		
		//oemWarrantyTier.setLastUpdate(date);
		//oemWarrantyTier.setCreateDate(date);
		manufacturer = manufacturerDAO.save(manufacturer);
			
		logger.debug("manufacturer.getId()"+manufacturer.getManfId());
		return manufacturer.getManfId();
	}
	
	/*
	@Override
	@Transactional
	public long editOEMWarrantyTier(OEMWarrantyTierDO oemWarrantyTierDO) throws Exception{
		logger.debug("In editOEMWarrantyTier : "+oemWarrantyTierDO.getAdjFactor());
		OEMWarrantyTier oemWarrantyTier = oemWarrantyTierDAO.findOne(oemWarrantyTierDO.getId());
		Timestamp date = new Timestamp(new Date().getTime());
		
		oemWarrantyTier.setWarrantyFrom(oemWarrantyTierDO.getWarrantyFrom());
		oemWarrantyTier.setWarrantyTo(oemWarrantyTierDO.getWarrantyTo());
		oemWarrantyTier.setAdjFactor(oemWarrantyTierDO.getAdjFactor());
		oemWarrantyTier.setLastUpdate(date);
		
		try {
			oemWarrantyTier = oemWarrantyTierDAO.save(oemWarrantyTier);
		}
	    catch (DataIntegrityViolationException e) {
	    	logger.error("OEMWarrantyTier already exist");
	    }	
		
		return oemWarrantyTier.getId();
	}*/
}
