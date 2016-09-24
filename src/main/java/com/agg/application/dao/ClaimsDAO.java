package com.agg.application.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.agg.application.entity.Claims;
import com.agg.application.model.ClaimsDO;

@Component
public interface ClaimsDAO extends CrudRepository<Claims, Long> {
	@Query("SELECT c FROM Claims c WHERE c.cStatus = :cStatus")
	List<Claims> findAllByCStatus(@Param("cStatus") byte cStatus);
	
	@Query("SELECT c FROM Claims c WHERE c.cStatus = :cStatus AND c.dealerId = :dealerId")
	List<Claims> findAllByCStatus(@Param("cStatus") byte cStatus, @Param("dealerId") int dealerId);
	
	@Modifying
	@Transactional
	@Query("UPDATE Claims c SET c.cStatus = :cStatus WHERE c.id = :id ")
	void updateStatus(@Param("id") int id, @Param("cStatus") byte cStatus);
	
	@Query("select new com.agg.application.model.ClaimsDO(claims.claimId, cus.name, dealer.name, claims.serial, quotes.manfName, "
			+ "quotes.machineModel, claims.cStatus) from "
			+ "Claims claims, Contracts contracts, Quote quotes, CustomerInfo cus, Dealer dealer "
			+ "where claims.contractId = contracts.contractId "
			+ "and contracts.quoteId = quotes.id.id "
			+ "and quotes.id.quoteId = cus.quoteId "
			+ "and claims.dealerId = :dealerId "
			+ "and claims.dealerId = dealer.id")
	List<ClaimsDO> findClaimsInfo(@Param("dealerId") int dealerId);
	
	@Query("select new com.agg.application.model.ClaimsDO(claims.claimId, cus.name, dealer.name, claims.serial, quotes.manfName, "
			+ "quotes.machineModel, claims.cStatus) from "
			+ "Claims claims, Contracts contracts, Quote quotes, CustomerInfo cus, Dealer dealer "
			+ "where claims.contractId = contracts.contractId "
			+ "and contracts.quoteId = quotes.id.id "
			+ "and quotes.id.quoteId = cus.quoteId "
			+ "and claims.dealerId = dealer.id")
	List<ClaimsDO> findClaimsInfo();
}
