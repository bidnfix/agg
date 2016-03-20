package com.agg.application.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import com.agg.application.entity.MachineModel;
import com.agg.application.entity.MachineType;

@Component
public interface MachineModelDAO extends CrudRepository<MachineModel, Long> {

	//List<MachineType> findByManufacturerManfId(int typeId);
	
	List<MachineModel> findByMachineTypeMachineTypeId(int typeId);

}
