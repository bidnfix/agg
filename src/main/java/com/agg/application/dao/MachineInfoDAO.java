package com.agg.application.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import com.agg.application.entity.MachineInfo;
import com.agg.application.model.MachineDO;

@Component
public interface MachineInfoDAO extends CrudRepository<MachineInfo, Long> {

	List<MachineInfo> findByMachineTypeMachineTypeIdOrderByModelAsc(int typeId);
	
	List<MachineInfo> findByManufacturerManfIdOrderByModelAsc(long manfId);
	
	@Query("select new com.agg.application.model.MachineDO(machineInfo.machineId, machineInfo.manufacturer.manfName, machineInfo.machineType.machineType, "
			+ "machineInfo.model, machineInfo.ePower, machineInfo.groupConstant.groupId) from MachineInfo machineInfo")
	List<MachineDO> findAllMachineInfos();
}
