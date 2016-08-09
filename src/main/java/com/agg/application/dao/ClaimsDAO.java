package com.agg.application.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import com.agg.application.entity.Claims;

@Component
public interface ClaimsDAO extends CrudRepository<Claims, Long> {
	
}
