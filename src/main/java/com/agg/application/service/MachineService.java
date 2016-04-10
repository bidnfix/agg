package com.agg.application.service;

import java.util.List;

import com.agg.application.model.GroupDO;
import com.agg.application.model.MachineDO;
import com.agg.application.model.MachineInfoDO;
import com.agg.application.model.MachineModelDO;
import com.agg.application.model.ManufacturerDO;

public interface MachineService {

	List<MachineDO> getmachineInfo();
	
	List<ManufacturerDO> getManufacturerDetails();
	
	List<ManufacturerDO> getMachineTypeById(int typeId);
	
	List<MachineModelDO> getMachineModel(int typeId);
	
	public long saveMachineInfo(MachineDO machineDO);
	
	public MachineDO getMachine(long id);
	
	public List<GroupDO> getGroups();

}
