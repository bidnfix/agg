package com.agg.application.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import com.agg.application.entity.Quote;
import com.agg.application.entity.QuotePK;

@Component
public interface QuoteDAO extends CrudRepository<Quote, Long> {
	
	//public Quote find(QuotePK quotePK);
	
	@Query("SELECT q FROM Quote q WHERE q.machineSerial LIKE CONCAT('%', :machineSerial, '%')")
	List<Quote> findByMachineSerial(@Param("machineSerial") String serialNo);
}
