package com.agg.application.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import com.agg.application.entity.DealerNote;

@Component
public interface DealerNoteDAO extends CrudRepository<DealerNote, Long>{

}
