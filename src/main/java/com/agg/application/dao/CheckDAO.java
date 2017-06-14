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
	
	@Query(value = "select sum(check_amount) as checkAmt from check_info where claim_id = :claimId "
			+ "union "
			+ "select check_no as check_no from check_info where claim_id = :claimId and received_date in "
			+ "(select max(received_date) from check_info where claim_id = :claimId) ", nativeQuery = true)
	List<Object> findCheckAmtAndChkNo(@Param("claimId") int claimId);
}
