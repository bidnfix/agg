package com.agg.application.service;

import java.sql.SQLException;
import java.util.List;

import com.agg.application.model.GroupDO;
import com.agg.application.model.MachineDO;
import com.agg.application.model.MachineInfoDO;
import com.agg.application.model.MachineModelDO;
import com.agg.application.model.MachineTypeDO;
import com.agg.application.model.ManufacturerDO;

public interface MachineService {

	List<MachineDO> getmachineInfo();
	
	List<ManufacturerDO> getManufacturerDetails();
	
	//List<ManufacturerDO> getMachineTypeById(int typeId);
	List<MachineTypeDO> getMachineTypes();
	
	List<MachineModelDO> getMachineModel(int typeId);
	
	public List<MachineInfoDO> getManfModel(long manfId);
	
	public long saveMachineInfo(MachineDO machineDO) throws Exception;
	
	public long editMachineInfo(MachineDO machineDO);
	
	public MachineDO getMachine(long id);
	
	public List<GroupDO> getGroups();

}
