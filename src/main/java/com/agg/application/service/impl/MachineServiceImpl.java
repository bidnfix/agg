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
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.agg.application.dao.GroupConstantDAO;
import com.agg.application.dao.GroupDAO;
import com.agg.application.dao.MachineInfoDAO;
import com.agg.application.dao.MachineTypeDAO;
import com.agg.application.dao.ManufacturerDAO;
import com.agg.application.entity.GroupConstant;
import com.agg.application.entity.MachineInfo;
import com.agg.application.entity.MachineType;
import com.agg.application.entity.Manufacturer;
import com.agg.application.model.GroupDO;
import com.agg.application.model.MachineDO;
import com.agg.application.model.MachineInfoDO;
import com.agg.application.model.MachineModelDO;
import com.agg.application.model.MachineTypeDO;
import com.agg.application.model.ManufacturerDO;
import com.agg.application.service.MachineService;
import com.google.common.collect.Lists;

@Service
public class MachineServiceImpl implements MachineService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private ManufacturerDAO manufacturerDAO;
	
	@Autowired
	private MachineTypeDAO machineTypeDAO;
	
	@Autowired
	private MachineInfoDAO machineInfoDAO;
	
	@Autowired
	private GroupDAO groupDAO;
	
	@Autowired
	private GroupConstantDAO groupConstantDAO;
	
	
	@Override
	public List<MachineDO> getMachineInfo() {
		logger.debug("Inside getmachineInfo()");
		List<MachineDO> machineDOList = machineInfoDAO.findAllMachineInfos();
		/*List<MachineInfo>  machineInfoList =  Lists.newArrayList(machineInfoDAO.findAll());
		
		List<MachineDO> machineDOList = null;
		if(!machineInfoList.isEmpty()){
			logger.debug("machineInfoList size:"+machineInfoList.size());
			machineDOList = new ArrayList<MachineDO>();
			MachineDO machineDO = null;
			MachineInfo machineInfo = null;
			ManufacturerDO manufacturerDO = null;
			MachineTypeDO machineTypeDO = null;
			GroupDO groupDO = null;
			
			Iterator<MachineInfo> it = machineInfoList.iterator();
			while(it.hasNext()){
				machineDO = new MachineDO();
				machineInfo = it.next();
				logger.debug("machineInfo.getMachineType() " +machineInfo.getMachineType().getMachineType());
				//logger.debug("machineInfo.getManufacturer() " +machineInfo.getManufacturer().getManfName());
				manufacturerDO = new ManufacturerDO();
				Manufacturer manufacturer = machineInfo.getManufacturer();
				if(manufacturer!=null)
				{
					manufacturerDO.setId(manufacturer.getManfId());
					manufacturerDO.setName(manufacturer.getManfName());
					machineDO.setManufacturerDO(manufacturerDO);
				}
				
				//logger.debug("machineInfo.getMachineType().getMachineType() "+machineInfo.getMachineType().getMachineType());
				machineDO.setMachineId(machineInfo.getMachineId());
			    	
				machineTypeDO = new MachineTypeDO();
				MachineType machineType = machineInfo.getMachineType();
				machineTypeDO.setId((int)machineType.getMachineTypeId());
				machineTypeDO.setName(machineType.getMachineType());
				machineDO.setMachineTypeDO(machineTypeDO);
				
				
				//machineDO.setMachineType(machineInfo.getMachineType().getMachineType());
				machineDO.setModel(machineInfo.getModel());
				machineDO.setEnginePower(machineInfo.getePower());
				//machineDO.setGroupId(machineInfo.getGroupId());
				groupDO = new GroupDO();
				groupDO.setGroupId(machineInfo.getGroupConstant().getGroupId());
				machineDO.setGroupDO(groupDO);
				machineDO.setGroupId(machineInfo.getGroupConstant().getGroupId());
				
				machineDOList.add(machineDO);
			}
		}
		//logger.debug(""+machineDOList.size());
		 */		
		return machineDOList;
	}
	
	@Override
	public MachineDO getMachine(long id) {
		logger.debug("In saveMachineInfo");
		MachineInfo machine = machineInfoDAO.findOne(id);
		MachineDO machineDO = null;
		ManufacturerDO manufacturerDO = null;
		MachineTypeDO machineTypeDO = null;
		//GroupDO groupDO = null;
		
		if(machine != null){
			machineDO = new MachineDO();
			
			manufacturerDO = new ManufacturerDO();
			Manufacturer manufacturer = machine.getManufacturer();
			manufacturerDO.setId(manufacturer.getManfId());
			manufacturerDO.setName(manufacturer.getManfName());
			machineDO.setManufacturerDO(manufacturerDO);
			//logger.debug("machineInfo.getMachineType().getMachineType() "+machineInfo.getMachineType().getMachineType());
			machineDO.setMachineId(machine.getMachineId());
		    	
			machineTypeDO = new MachineTypeDO();
			MachineType machineType = machine.getMachineType();
			machineTypeDO.setId((int)machineType.getMachineTypeId());
			machineTypeDO.setName(machineType.getMachineType());
			machineDO.setMachineTypeDO(machineTypeDO);
			machineDO.setMachineType(machineType.getMachineType());
			machineDO.setManfName(manufacturer.getManfName());
			
			
			//machineDO.setMachineType(machine.getMachineType().getMachineType());
			machineDO.setModel(machine.getModel());
			machineDO.setEnginePower(machine.getePower());
			machineDO.setAdjFactor(machine.getAdjFactor());
			
			//groupDO = new GroupDO();
			//groupDO.setGroupId(machine.getGroupConstant().getGroupId());
			machineDO.setGroupId(machine.getGroupConstant().getGroupId());
		}
		
		logger.debug("In getMachine : "+machineDO);
		
		return machineDO;
	}
	
	@Override
	public List<ManufacturerDO> getManufacturerDetails() {
		logger.debug("Inside getManufacturerDetails()");
		List<Manufacturer>  manufacturerModels =  Lists.newArrayList(manufacturerDAO.findAll(new Sort(Direction.ASC, "manfName")));
		
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
		//logger.debug(""+manufacturerDOList.size());
		return manufacturerDOList;
	}
		
	@Override
	public List<MachineTypeDO> getMachineTypes() {
		logger.debug("Inside getMachineTypes()");
		List<MachineType>  machineTypeList =  Lists.newArrayList(machineTypeDAO.findAll());
		List<MachineTypeDO> machineTypeDOList = null;
		if(machineTypeList != null && !machineTypeList.isEmpty()){
			machineTypeDOList = new ArrayList<MachineTypeDO>();
			MachineTypeDO machineTypeDO = null;
			MachineType machineType = null;
			Iterator<MachineType> it = machineTypeList.iterator();
			while(it.hasNext()){
				machineTypeDO = new MachineTypeDO();
				machineType = it.next();
				machineTypeDO.setId((int)machineType.getMachineTypeId());
				machineTypeDO.setName(machineType.getMachineType());
				machineTypeDOList.add(machineTypeDO);
			}
		}
		//logger.debug("Machine Type List size: "+machineTypeDOList.size());
		return machineTypeDOList;
	}
	
	@Override
	public List<MachineModelDO> getMachineModel(int typeId) {
		logger.debug("Inside getMachineModelById()");
		List<MachineInfo>  machineModelList =  machineInfoDAO.findByMachineTypeMachineTypeIdOrderByModelAsc(typeId);
		List<MachineModelDO> machineModelDOList = null;
		if(machineModelList != null && !machineModelList.isEmpty()){
			machineModelDOList = new ArrayList<MachineModelDO>();
			MachineModelDO machineModelDO = null;
			MachineInfo machineModel = null;
			GroupDO groupDO = null;
			Iterator<MachineInfo> it = machineModelList.iterator();
			while(it.hasNext()){
				machineModelDO = new MachineModelDO();
				machineModel = it.next();
				//machineModelDO.setModelId(machineModel.getModel());
				machineModelDO.setModelName(machineModel.getModel());
				machineModelDO.setModelYear((machineModel.getModelYear() != null)?machineModel.getModelYear():0);
				machineModelDO.setePower(machineModel.getePower());
				//machineModelDO.setePower(machineModel.getePower());
				machineModelDO.setRetailPrice(machineModel.getRetailPrice());
				machineModelDO.setBasePrice(machineModel.getBasePrice());
				
/*				groupDO = new GroupDO();
				groupDO.setGroupId(machineModel.getGroupId().getGroupId());
				machineModelDO.setGroupDO(groupDO);*/
				
				machineModelDO.setGroupId(machineModel.getGroupConstant().getGroupId());
				//machineModelDO.setMachineTypeId(machineModel.getMachineTypeId());
				machineModelDOList.add(machineModelDO);
			}
		}
		//logger.debug("Machine Type List size: "+machineModelDOList.size());
		return machineModelDOList;
	}
	
	@Override
	public List<MachineInfoDO> getManfModel(long manfId) {
		logger.debug("Inside getManfModel()");
		List<MachineInfo>  machineInfoList =  machineInfoDAO.findByManufacturerManfIdOrderByModelAsc(manfId);
		List<MachineInfoDO> machineInfoDOList = null;
		if(machineInfoList != null && !machineInfoList.isEmpty()){
			machineInfoDOList = new ArrayList<MachineInfoDO>();
			MachineInfoDO machineInfoDO = null;
			MachineInfo machineModel = null;
			GroupDO groupDO = null;
			Iterator<MachineInfo> it = machineInfoList.iterator();
			while(it.hasNext()){
				machineInfoDO = new MachineInfoDO();
				machineModel = it.next();
				//machineModelDO.setModelId(machineModel.getModel());
				machineInfoDO.setModel(machineModel.getModel());
				machineInfoDO.setMachineId(machineModel.getMachineId());
				machineInfoDO.setMachineType(machineModel.getMachineType().getMachineType());
				machineInfoDO.setLol(machineModel.getGroupConstant().getLol());
				machineInfoDO.setGroupId(Integer.valueOf(String.valueOf(machineModel.getGroupConstant().getGroupId())));
				
				machineInfoDO.setAdjFactor(machineModel.getAdjFactor());
				
				machineInfoDOList.add(machineInfoDO);
			}
		}
//		logger.debug("Machine Type List size: "+machineInfoDOList.size());
		return machineInfoDOList;
	}

	@Override
	@Transactional
	public long saveMachineInfo(MachineDO machineDO) throws Exception{
		logger.debug("In saveMachineInfo");
		MachineInfo machineInfo = new MachineInfo();
		Timestamp date = new Timestamp(new Date().getTime());
		
		//macineInfo.setModel((machineDO.getMachineModelDO().getModelId());
		machineInfo.setManufacturer(manufacturerDAO.findOne(Long.valueOf(machineDO.getManufacturerDO().getId())));
		machineInfo.setModel(machineDO.getModel());
		machineInfo.setMachineType(machineTypeDAO.findOne(Long.valueOf(machineDO.getMachineTypeDO().getId())));
		logger.debug(machineDO.getMachineTypeDO().getName());
		logger.debug(machineDO.getModel());
		//macineInfo.setModelYear(sqlDate);
		logger.debug(date.toString());
		machineInfo.setePower(machineDO.getEnginePower());
		logger.debug("Engne power "+machineDO.getEnginePower());
		//macineInfo.setePower(machineModelDO.getePower());
		//macineInfo.setRetailPrice(machineDO.getRetailPrice());
		//macineInfo.setBasePrice(machineDO.getBasePrice());

		machineInfo.setAdjFactor(machineDO.getAdjFactor());
		machineInfo.setGroupConstant(groupConstantDAO.findOne(Long.valueOf(machineDO.getGroupId())));
		

		machineInfo.setLastUpdate(date);
		machineInfo = machineInfoDAO.save(machineInfo);
			
		
		return machineInfo.getMachineId();
	}
	
	@Override
	@Transactional
	public long editMachineInfo(MachineDO machineDO) throws Exception{
		logger.debug("In editMachineInfo : "+machineDO.getMachineId());
		MachineInfo machineInfo = machineInfoDAO.findOne(machineDO.getMachineId());
		Timestamp date = new Timestamp(new Date().getTime());
		
		//macineInfo.setModel((machineDO.getMachineModelDO().getModelId());
		machineInfo.setManufacturer(manufacturerDAO.findOne(Long.valueOf(machineDO.getManufacturerDO().getId())));
		machineInfo.setModel(machineDO.getModel());
		machineInfo.setMachineType(machineTypeDAO.findOne(Long.valueOf(machineDO.getMachineTypeDO().getId())));
		logger.debug(machineDO.getMachineTypeDO().getName());
		logger.debug(machineDO.getModel());
		//macineInfo.setModelYear(sqlDate);
		logger.debug(date.toString());
		machineInfo.setePower(machineDO.getEnginePower());
		logger.debug("Engne power "+machineDO.getEnginePower());
		
		machineInfo.setAdjFactor(machineDO.getAdjFactor());
		//macineInfo.setePower(machineModelDO.getePower());
		//macineInfo.setRetailPrice(machineDO.getRetailPrice());
		//macineInfo.setBasePrice(machineDO.getBasePrice());
		logger.debug("machineDO.getGroupId() -->"+machineDO.getGroupId());
		
		logger.debug("machineInfo.getModelYear -->"+machineInfo.getModelYear());
		//machineInfo.setModelYear(machineInfo.getModelYear().getYear());

		machineInfo.setGroupConstant(groupConstantDAO.findOne(Long.valueOf(machineDO.getGroupId())));
		

		machineInfo.setLastUpdate(date);
		
		try {
			machineInfo = machineInfoDAO.save(machineInfo);
		}
	    catch (DataIntegrityViolationException e) {
	    	logger.error("Machine already exist");
	    }	
		
		return machineInfo.getMachineId();
	}
	
	

	@Override
	public List<GroupDO> getGroups() {
		logger.debug("Inside getGroups()");
		List<GroupConstant>  groupList =  Lists.newArrayList(groupDAO.findAll());
		List<GroupDO> groupDOList = null;
		if(groupList != null && !groupList.isEmpty()){
			groupDOList = new ArrayList<GroupDO>();
			GroupDO groupDO = null;
			GroupConstant groupConstant = null;
			Iterator<GroupConstant> it = groupList.iterator();
			while(it.hasNext()){
				groupDO = new GroupDO();
				groupConstant = it.next();
				groupDO.setGroupId(groupConstant.getGroupId());
				groupDO.setComm(groupConstant.getComm());
				groupDO.setLol(groupConstant.getLol());
				groupDO.setMsrp(groupConstant.getMsrp());
				groupDO.setOldId(groupConstant.getOldId());
				groupDO.setRate(groupConstant.getRate());
				groupDO.setSales(groupConstant.getSales());
				groupDO.setTax(groupConstant.getTax());
				StringBuffer tooltip = new StringBuffer().append("Old Group "+groupConstant.getGroupId()+" \r")
						.append(" MRP "+groupConstant.getMsrp()+" \r")
						.append(" Sales "+groupConstant.getSales()+" \r")
						.append(" Tax "+groupConstant.getTax()+" \r")
						.append(" LOL "+groupConstant.getLol()+" \r")
						.append(" Rate "+groupConstant.getRate());
				
				//logger.debug("Tooltip 1"+tooltip);
				groupDO.setTips(tooltip.toString());
				groupDOList.add(groupDO);
			}
		}
		//logger.debug("Groups size: "+groupDOList.size());
		return groupDOList;
	}
}
