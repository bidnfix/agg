package com.agg.application.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import com.agg.application.entity.UsageTier;
import com.agg.application.model.UsageTierDO;

@Component
public interface UsageTierDAO extends CrudRepository<UsageTier, Long>{
	
	@Query("select new com.agg.application.model.UsageTierDO(usageTier.id, usageTier.usageFrom, usageTier.usageTo, "
			+ "usageTier.adjFactor) from UsageTier usageTier")
	List<UsageTierDO> findAllUsageTier();

}
