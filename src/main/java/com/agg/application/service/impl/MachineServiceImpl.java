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
import com.agg.application.dao.MachineInfoDAO;
import com.agg.application.dao.MachineTypeDAO;
import com.agg.application.entity.MachineInfo;
import com.agg.application.entity.MachineType;
import com.agg.application.entity.Manufacturer;
import com.agg.application.model.MachineDO;
import com.agg.application.model.MachineInfoDO;
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
	private MachineInfoDAO machineInfoDAO;

	
	@Override
	public List<MachineInfoDO> getmachineInfo() {
		logger.debug("Inside getmachineInfo()");
		List<MachineInfo>  machineInfoList =  Lists.newArrayList(machineInfoDAO.findAll());
		
		List<MachineInfoDO> machineInfoDOList = null;
		if(!machineInfoList.isEmpty()){
			logger.debug("machineInfoList size:"+machineInfoList.size());
			machineInfoDOList = new ArrayList<MachineInfoDO>();
			MachineInfoDO machineInfoDO = null;
			MachineInfo machineInfo = null;
			ManufacturerDO manufacturerDO = null;
			
			Iterator<MachineInfo> it = machineInfoList.iterator();
			while(it.hasNext()){
				machineInfoDO = new MachineInfoDO();
				machineInfo = it.next();
				logger.debug("machineInfo.getMachineType() " +machineInfo.getMachineType().getMachineType());
				//logger.debug("machineInfo.getManufacturer() " +machineInfo.getManufacturer().getManfName());
				manufacturerDO = new ManufacturerDO();
				Manufacturer manufacturer = machineInfo.getMachineType().getManufacturer();
				manufacturerDO.setId(manufacturer.getManfId());
				manufacturerDO.setName(manufacturer.getManfName());
				machineInfoDO.setManufacturerDO(manufacturerDO);
				logger.debug("machineInfo.getMachineType().getMachineType() "+machineInfo.getMachineType().getMachineType());
				machineInfoDO.setMachineId(machineInfo.getMachineId());
				machineInfoDO.setMachineType(machineInfo.getMachineType().getMachineType());
				machineInfoDO.setModel(machineInfo.getModel());
				machineInfoDO.setPower(machineInfo.getPower());
				machineInfoDO.setGroupId(machineInfo.getGroupId());
				machineInfoDOList.add(machineInfoDO);
			}
		}
		logger.debug(""+machineInfoDOList.size());
		return machineInfoDOList;
	}
	
	@Override
	public List<ManufacturerDO> getManufacturerDetails() {
		logger.debug("Inside getManufacturerDetails()");
		List<Manufacturer>  manufacturerModels =  Lists.newArrayList(machineDAO.findAll());
		
		List<ManufacturerDO> manufacturerDOList = null;
		if(!manufacturerModels.isEmpty()){
			logger.debug("manufacturerModels size:"+manufacturerModels.size());
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
	public List<MachineModelDO> getMachineModel(int typeId) {
		logger.debug("Inside getMachineModelById()");
		List<MachineInfo>  machineModelList =  machineInfoDAO.findByMachineTypeMachineTypeId(typeId);
		List<MachineModelDO> machineModelDOList = null;
		if(machineModelList != null && !machineModelList.isEmpty()){
			machineModelDOList = new ArrayList<MachineModelDO>();
			MachineModelDO machineModelDO = null;
			MachineInfo machineModel = null;
			Iterator<MachineInfo> it = machineModelList.iterator();
			while(it.hasNext()){
				machineModelDO = new MachineModelDO();
				machineModel = it.next();
				//machineModelDO.setModelId(machineModel.getModel());
				machineModelDO.setModelName(machineModel.getModel());
				machineModelDO.setModelYear(machineModel.getModelYear());
				machineModelDO.setPower(machineModel.getPower());
				//machineModelDO.setePower(machineModel.getePower());
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
	public long saveMachineInfo(MachineDO machineDO) {
		logger.debug("In saveMachineInfo");
		MachineInfo macineInfo = new MachineInfo();
		Timestamp date = new Timestamp(new Date().getTime());
		
		java.sql.Date sqlDate = new java.sql.Date(date.getTime());
		
		
		//macineInfo.setModel((machineDO.getMachineModelDO().getModelId());
		macineInfo.setModel(machineDO.getModelName());
		macineInfo.setMachineType(machineTypeDAO.findOne(Long.valueOf(machineDO.getMachineTypeDO().getId())));
		logger.debug(machineDO.getMachineTypeDO().getName());
		logger.debug(machineDO.getModelName());
		//macineInfo.setModelYear(sqlDate);
		logger.debug(date.toString());
		macineInfo.setPower(machineDO.getEnginePower());
		logger.debug("Engne power "+machineDO.getEnginePower());
		//macineInfo.setePower(machineModelDO.getePower());
		//macineInfo.setRetailPrice(machineDO.getRetailPrice());
		//macineInfo.setBasePrice(machineDO.getBasePrice());
		macineInfo.setGroupId(machineDO.getGroupId());
		
		macineInfo.setLastUpdate(date);
		
		macineInfo = machineInfoDAO.save(macineInfo);
		
		return macineInfo.getMachineId();
	}
	
	@Override
	public MachineInfoDO getMachine(long id) {
		logger.debug("In saveMachineInfo");
		MachineInfo machine = machineInfoDAO.findOne((long)id);
		MachineInfoDO machineDO = null;
		ManufacturerDO manufacturerDO = null;
		if(machine != null){
			machineDO = new MachineInfoDO();
			
			manufacturerDO = new ManufacturerDO();
			Manufacturer manufacturer = machine.getMachineType().getManufacturer();
			manufacturerDO.setId(manufacturer.getManfId());
			manufacturerDO.setName(manufacturer.getManfName());
			
			machineDO.setManufacturerDO(manufacturerDO);
			machineDO.setMachineType(machine.getMachineType().getMachineType());
			machineDO.setModel(machine.getModel());
			machineDO.setGroupId(machine.getGroupId());
		}
		
		logger.debug("In saveMachineInfo : "+machineDO);
		
		return machineDO;
	}

}
