package com.agg.application.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import com.agg.application.entity.UseOfEquip;

@Component
public interface UseOfEquipmentDAO extends CrudRepository<UseOfEquip, Long>{
	

}
