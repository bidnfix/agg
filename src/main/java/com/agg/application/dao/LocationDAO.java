package com.agg.application.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import com.agg.application.entity.Location;

@Component
public interface LocationDAO extends CrudRepository<Location, Long>{

	public List<Location> findByDealerId(long dealerId);
}
