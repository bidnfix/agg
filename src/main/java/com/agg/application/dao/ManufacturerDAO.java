package com.agg.application.dao;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import com.agg.application.entity.Manufacturer;

@Component
public interface ManufacturerDAO extends CrudRepository<Manufacturer, Long> {

	public List<Manufacturer> findAll(Sort sort);
	
	//public List<Manufacturer> findAll();

}
