package com.agg.application.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import com.agg.application.entity.Quote;
import com.agg.application.entity.QuotePK;

@Component
public interface QuoteDAO extends CrudRepository<Quote, Long> {
	
	//public Quote find(QuotePK quotePK);

}
