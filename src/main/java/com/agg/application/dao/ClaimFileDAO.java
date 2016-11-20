/**
 * htamada
 */
package com.agg.application.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import com.agg.application.entity.ClaimFile;

/**
 * @author htamada
 *
 */
@Component
public interface ClaimFileDAO extends CrudRepository<ClaimFile, Integer> {
	@Query("SELECT c FROM ClaimFile c WHERE c.claimId IN :claimID")
	List<ClaimFile> findAllByClaimID(@Param("claimID") List<Integer> claimId);
}
