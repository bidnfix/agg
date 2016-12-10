package com.agg.application.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import com.agg.application.entity.CustomerInfo;

@Component
public interface CustomerInfoDAO extends CrudRepository<CustomerInfo, String>{
	
}
