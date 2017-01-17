package com.agg.application.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import com.agg.application.entity.Sprogram;

@Component
public interface ProgramDAO extends CrudRepository<Sprogram, Long>{

	public List<Sprogram> findByDealerId(long id);
	
	public List<Sprogram> findByDealerIdAndPrIsActive(long id, byte isActive);
	
	@Query("SELECT program FROM Sprogram program WHERE program.dealer.id IN (:dealerId, :parentDealerId)")
	public List<Sprogram> findByDealerIds(@Param("dealerId") long dealerId, @Param("parentDealerId") long parentDealerId);

}
