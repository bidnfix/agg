/**
 * htamada
 */
package com.agg.application.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.agg.application.entity.ClaimLabor;

/**
 * @author htamada
 *
 */
public interface ClaimLaborDAO extends CrudRepository<ClaimLabor, Integer>{
	@Query("SELECT c FROM ClaimLabor c WHERE c.claimId IN :claimID")
	List<ClaimLabor> findAllByClaimID(@Param("claimID") List<Integer> claimId);
	
	@Query("delete FROM ClaimLabor c WHERE c.claimId = :claimId")
	@Modifying
	int deleteClaimLaborsByClaimId(@Param("claimId") int claimId);
}
