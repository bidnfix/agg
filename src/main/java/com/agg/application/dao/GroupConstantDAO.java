package com.agg.application.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import com.agg.application.entity.GroupConstant;

@Component
public interface GroupConstantDAO extends CrudRepository<GroupConstant, Long> {
	
}
