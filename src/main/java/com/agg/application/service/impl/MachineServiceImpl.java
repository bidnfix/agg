package com.agg.application.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agg.application.dao.MachineDAO;
import com.agg.application.dao.MachineTypeDAO;
import com.agg.application.entity.MachineType;
import com.agg.application.entity.Manufacturer;
import com.agg.application.model.ManufacturerDO;
import com.agg.application.service.MachineService;
import com.google.common.collect.Lists;

@Service
public class MachineServiceImpl implements MachineService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private MachineDAO machineDAO;
	
	@Autowired
	private MachineTypeDAO machineTypeDAO;

	@Override
	public List<ManufacturerDO> getManufacturerDetails() {
		logger.debug(machineDAO.findAll().toString());
		List<Manufacturer>  manufacturerModels =  Lists.newArrayList(machineDAO.findAll());
		List<ManufacturerDO> manufacturerDOList = null;
		if(!manufacturerModels.isEmpty()){
			manufacturerDOList = new ArrayList<ManufacturerDO>();
			ManufacturerDO manufacturerDO = null;
			Manufacturer manufacturer = null;
			Iterator<Manufacturer> it = manufacturerModels.iterator();
			while(it.hasNext()){
				manufacturerDO = new ManufacturerDO();
				manufacturer = it.next();
				manufacturerDO.setId(manufacturer.getManfId());
				manufacturerDO.setName(manufacturer.getManfName());
				manufacturerDOList.add(manufacturerDO);
			}
		}
		logger.debug(""+manufacturerDOList.size());
		return manufacturerDOList;
	}
	
	@Override
	public List<ManufacturerDO> getMachineTypeById(int typeId) {
		logger.debug("--> "+machineTypeDAO.findByMachineTypeId(typeId).toString());
		List<MachineType>  machineTypeList =  machineTypeDAO.findByMachineTypeId(typeId);
		List<ManufacturerDO> machineTypeDOList = null;
		if(machineTypeList != null && !machineTypeList.isEmpty()){
			machineTypeDOList = new ArrayList<ManufacturerDO>();
			ManufacturerDO machineTypeDO = null;
			MachineType machineType = null;
			Iterator<MachineType> it = machineTypeList.iterator();
			while(it.hasNext()){
				machineTypeDO = new ManufacturerDO();
				machineType = it.next();
				machineTypeDO.setId(machineType.getMachineTypeId());
				machineTypeDO.setName(machineType.getMachineType());
				machineTypeDOList.add(machineTypeDO);
			}
		}
		logger.debug("Machine Type List size: "+machineTypeDOList.size());
		return machineTypeDOList;
	}

	

}
