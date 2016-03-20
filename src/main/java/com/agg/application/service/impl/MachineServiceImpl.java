package com.agg.application.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agg.application.dao.MachineDAO;
import com.agg.application.dao.MachineModelDAO;
import com.agg.application.dao.MachineTypeDAO;
import com.agg.application.entity.MachineInfo;
import com.agg.application.entity.MachineModel;
import com.agg.application.entity.MachineType;
import com.agg.application.entity.Manufacturer;
import com.agg.application.model.MachineModelDO;
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
	
	@Autowired
	private MachineModelDAO machineModelDAO;

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
		logger.debug("Inside getMachineTypeById()");
		List<MachineType>  machineTypeList =  machineTypeDAO.findByManufacturerManfId(typeId);
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
	
	@Override
	public List<MachineModelDO> getMachineModelById(int typeId) {
		logger.debug("Inside getMachineModelById()");
		List<MachineModel>  machineModelList =  machineModelDAO.findByMachineTypeMachineTypeId(typeId);
		List<MachineModelDO> machineModelDOList = null;
		if(machineModelList != null && !machineModelList.isEmpty()){
			machineModelDOList = new ArrayList<MachineModelDO>();
			MachineModelDO machineModelDO = null;
			MachineModel machineModel = null;
			Iterator<MachineModel> it = machineModelList.iterator();
			while(it.hasNext()){
				machineModelDO = new MachineModelDO();
				machineModel = it.next();
				machineModelDO.setModelId(machineModel.getModelId());
				machineModelDO.setModelName(machineModel.getModelName());
				machineModelDO.setModelYear(machineModel.getModelYear());
				machineModelDO.setPower(machineModel.getPower());
				machineModelDO.setePower(machineModel.getePower());
				machineModelDO.setRetailPrice(machineModel.getRetailPrice());
				machineModelDO.setBasePrice(machineModel.getBasePrice());
				machineModelDO.setGroupId(machineModel.getGroupId());
				//machineModelDO.setMachineTypeId(machineModel.getMachineTypeId());
				machineModelDOList.add(machineModelDO);
			}
		}
		logger.debug("Machine Type List size: "+machineModelDOList.size());
		return machineModelDOList;
	}

	@Override
	@Transactional
	public long saveMachineInfo(MachineModelDO machineModelDO) {
		logger.debug("In saveMachineInfo");
		MachineInfo macineInfo = new MachineInfo();
		Timestamp date = new Timestamp(new Date().getTime());
		
		
		//macineInfo.setModelId(machineModelDO.getModelId());
		macineInfo.setModel(machineModelDO.getModelName());
		macineInfo.setModelYear(machineModelDO.getModelYear());
		macineInfo.setPower(machineModelDO.getPower());
		//macineInfo.setePower(machineModelDO.getePower());
		macineInfo.setRetailPrice(machineModelDO.getRetailPrice());
		macineInfo.setBasePrice(machineModelDO.getBasePrice());
		macineInfo.setGroupId(machineModelDO.getGroupId());
		
		
		macineInfo.setLastUpdate(date);
		//TODO
		
		
		
		//dealerNote = dealerNoteDAO.save(dealerNote);
		
		//return dealer.getDealerId();
		return 1;
	}
	

}
