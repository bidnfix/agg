package com.agg.application.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import com.agg.application.entity.Sprogram;

@Component
public interface ProgramDAO extends CrudRepository<Sprogram, Long>{

	public List<Sprogram> findByDealerId(long id);

}
