package com.agg.application.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import com.agg.application.entity.Dealer;

@Component
public interface DealerDAO extends CrudRepository<Dealer, Long>{

}
