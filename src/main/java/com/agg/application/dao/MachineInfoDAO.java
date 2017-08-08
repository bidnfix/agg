package com.agg.application.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import com.agg.application.entity.MachineInfo;
import com.agg.application.model.MachineDO;
import com.agg.application.model.MachineInfoDO;

@Component
public interface MachineInfoDAO extends CrudRepository<MachineInfo, Long> {

	List<MachineInfo> findByMachineTypeMachineTypeIdOrderByModelAsc(int typeId);
	
	List<MachineInfo> findByManufacturerManfIdOrderByModelAsc(long manfId);
	
	@Query("select new com.agg.application.model.MachineDO(machineInfo.machineId, machineInfo.manufacturer.manfName, machineInfo.machineType.machineType, "
			+ "machineInfo.model, machineInfo.ePower, machineInfo.adjFactor, machineInfo.groupConstant.groupId) from MachineInfo machineInfo")
	List<MachineDO> findAllMachineInfos();
	
	@Query("SELECT machineInfo FROM MachineInfo machineInfo WHERE machineInfo.machineId IN :machineID")
	List<MachineInfo> findMachineInfoById(@Param("machineID") List<Long> machineID);
	
	@Query("select new com.agg.application.model.MachineInfoDO(machineInfo.machineId, machineInfo.manufacturer.manfName, machineInfo.machineType.machineType, "
			+ "machineInfo.model, machineInfo.ePower, machineInfo.groupConstant.groupId) from MachineInfo machineInfo where machineInfo.manufacturer.manfId = :manfId and "
			+ "machineInfo.machineType.machineTypeId = :machineTypeId")
	List<MachineInfoDO> findMachineModels(@Param("manfId")long manfId, @Param("machineTypeId")long machineTypeId);
}
