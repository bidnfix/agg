/**
 * htamada
 */
package com.agg.application.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import com.agg.application.entity.ClaimPart;

/**
 * @author htamada
 *
 */
@Component
public interface ClaimPartDAO extends CrudRepository<ClaimPart, Integer>{
	@Query("SELECT c FROM ClaimPart c WHERE c.claimId IN :claimID")
	List<ClaimPart> findAllByClaimID(@Param("claimID") List<Integer> claimId);
	
	@Query("delete FROM ClaimPart c WHERE c.claimId = :claimId")
	@Modifying
	int deleteClaimPartsByClaimId(@Param("claimId") int claimId);
}
