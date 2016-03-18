package com.agg.application.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import com.agg.application.entity.Location;

@Component
public interface LocationDAO extends CrudRepository<Location, Long>{

}
