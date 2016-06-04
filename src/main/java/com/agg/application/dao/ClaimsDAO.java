package com.agg.application.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import com.agg.application.entity.Claims;
import com.agg.application.entity.Quote;

@Component
public interface ClaimsDAO extends CrudRepository<Quote, Long> {
	
	
	
}
