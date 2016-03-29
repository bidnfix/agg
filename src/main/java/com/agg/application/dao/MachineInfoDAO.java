package com.agg.application.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import com.agg.application.entity.MachineInfo;

@Component
public interface MachineInfoDAO extends CrudRepository<MachineInfo, Long> {


}
