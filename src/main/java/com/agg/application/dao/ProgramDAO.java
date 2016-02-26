package com.agg.application.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import com.agg.application.entity.Sprogram;

@Component
public interface ProgramDAO extends CrudRepository<Sprogram, Long>{

}
