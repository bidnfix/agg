package com.agg.application.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.agg.application.entity.Claims;

@Component
public interface ClaimsDAO extends CrudRepository<Claims, Long> {
	@Query("SELECT c FROM Claims c WHERE c.cStatus = :cStatus")
	List<Claims> findAllByCStatus(@Param("cStatus") byte cStatus);
	
	@Modifying
	@Transactional
	@Query("UPDATE Claims c SET c.cStatus = :cStatus WHERE c.id = :id ")
	void updateStatus(@Param("id") int id, @Param("cStatus") byte cStatus);
}
