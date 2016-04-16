package com.agg.application.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import com.agg.application.entity.Zip;

@Component
public interface ZipDAO extends CrudRepository<Zip, Long>{
	
}
