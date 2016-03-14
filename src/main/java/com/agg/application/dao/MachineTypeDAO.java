package com.agg.application.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import com.agg.application.entity.MachineType;

@Component
public interface MachineTypeDAO extends CrudRepository<MachineType, Long> {

	List<MachineType> findByMachineTypeId(int typeId);

}
