package com.agg.application.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import com.agg.application.entity.Check;

@Component
public interface CheckDAO extends CrudRepository<Check, Long> {

	@Query("delete FROM Check c WHERE c.contract.id = :contractId")
	@Modifying
	int deleteChecksByContractId(@Param("contractId") long contractId);
	
	List<Check> findByClaimId(int claimId);
}
