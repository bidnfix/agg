package com.agg.application.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import com.agg.application.entity.Quote;

public interface QuoteCustomDAO extends Repository<Quote, Long>{
	@Query("SELECT q FROM Quote q WHERE q.machineSerial = :machineSerial")
	List<Quote> findByMachineSerial(@Param("machineSerial") String serialNo);
}
