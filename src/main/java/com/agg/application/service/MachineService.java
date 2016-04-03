package com.agg.application.service;

import java.util.List;

import com.agg.application.model.MachineDO;
import com.agg.application.model.MachineModelDO;
import com.agg.application.model.ManufacturerDO;

public interface MachineService {

	List<ManufacturerDO> getManufacturerDetails();
	
	List<ManufacturerDO> getMachineTypeById(int typeId);
	
	List<MachineModelDO> getMachineModel(int typeId);
	
	public long saveMachineInfo(MachineDO machineDO);

}
