package com.agg.application.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import com.agg.application.entity.AdminAdjustment;

@Component
public interface AdminAdjustmentDAO extends CrudRepository<AdminAdjustment, String>{
	
}
