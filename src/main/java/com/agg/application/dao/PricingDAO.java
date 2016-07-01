package com.agg.application.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import com.agg.application.entity.Pricing;
import com.agg.application.model.PricingDO;

@Component
public interface PricingDAO extends CrudRepository<Pricing, Long>{
	

	@Query("SELECT distinct p.id.deductibleAmount FROM Pricing p "
			+ "WHERE p.id.condition = :condition "
			+ "and p.id.groupId = :groupId "
			+ "and p.id.cLevelHours > 0 "
			+ "and (p.ptBasePrice > 0 or p.phBasePrice > 0 or p.plBasePrice > 0)")
	List<Integer> findDeductibleAmount(@Param("condition") byte condition, @Param("groupId") int groupId);
	
	@Query("SELECT distinct p.id.coverageTerm FROM Pricing p "
			+ "WHERE p.id.condition = :condition "
			+ "and p.id.groupId = :groupId "
			+ "and p.id.cLevelHours > 0 "
			+ "and (p.ptBasePrice > 0 or p.phBasePrice > 0 or p.plBasePrice > 0) "
			+ "and p.id.deductibleAmount in (:decutibleAmount)")
	List<Integer> findCoverageTerm(@Param("condition") byte condition, @Param("groupId") int groupId, @Param("decutibleAmount") List<Integer> decutibleAmounts);
	
	@Query("SELECT new com.agg.application.model.PricingDO(p.id.cLevelHours, p.ptBasePrice, p.phBasePrice, p.plBasePrice) "
			+ "FROM Pricing p "
			+ "WHERE p.id.condition = :condition "
			+ "and p.id.groupId = :groupId "
			+ "and p.id.cLevelHours > 0 "
			+ "and (p.ptBasePrice > 0 or p.phBasePrice > 0 or p.plBasePrice > 0) "
			+ "and p.id.coverageTerm = :coverageTerm "
			+ "and p.id.deductibleAmount = :deductibleAmt")
	List<PricingDO> findCoverageLevelPricing(@Param("condition") byte condition, @Param("groupId") int groupId, @Param("deductibleAmt") int deductibleAmt, @Param("coverageTerm") int coverageTerm);
	
	
}
