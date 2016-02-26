package com.agg.application.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import com.agg.application.entity.Manufacturer;

@Component
public interface MachineDAO extends CrudRepository<Manufacturer, Long> {

		//Optional<Manufacturer> findAllManufacturer();

}
