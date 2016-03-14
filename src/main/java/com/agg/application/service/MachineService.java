package com.agg.application.service;

import java.util.List;

import com.agg.application.model.ManufacturerDO;

public interface MachineService {

	List<ManufacturerDO> getManufacturerDetails();
	
	List<ManufacturerDO> getMachineTypeById(int typeId);

}
