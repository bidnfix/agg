package com.agg.application.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import com.agg.application.entity.Sequence;

@Component
public interface SequenceDAO extends CrudRepository<Sequence, Long>{
	
	public Sequence findBySeqType(String seqType);
}
