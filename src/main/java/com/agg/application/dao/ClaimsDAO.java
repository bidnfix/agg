package com.agg.application.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.agg.application.entity.ClaimReport;
import com.agg.application.entity.Claims;
import com.agg.application.model.ClaimsDO;

@Component
public interface ClaimsDAO extends CrudRepository<Claims, Integer> {
	@Query("SELECT c FROM Claims c WHERE c.cStatus = :cStatus ORDER BY c.lastUpdate DESC")
	List<Claims> findAllByCStatus(@Param("cStatus") byte cStatus);
	
	@Query("SELECT c FROM Claims c WHERE c.cStatus = :cStatus AND c.dealerId = :dealerId ORDER BY c.lastUpdate DESC")
	List<Claims> findAllByCStatus(@Param("cStatus") byte cStatus, @Param("dealerId") int dealerId);
	
	@Modifying
	@Transactional
	@Query("UPDATE Claims c SET c.cStatus = :cStatus WHERE c.id = :id ")
	void updateStatus(@Param("id") int id, @Param("cStatus") byte cStatus);
	
	@Query("select new com.agg.application.model.ClaimsDO(claims.claimId, cus.name, dealer.name, claims.serial, quotes.manufacturer.manfName, "
			+ "quotes.machineInfo.model, claims.cStatus, claims.cheqNo, claims.paidDate, claims.lastUpdate) from "
			+ "Claims claims, Contracts contracts, Quote quotes, CustomerInfo cus, Dealer dealer "
			+ "where claims.contractId = contracts.contractId "
			+ "and contracts.quoteId = quotes.id.id "
			+ "and quotes.id.quoteId = cus.quoteId "
			+ "and claims.dealerId = :dealerId "
			+ "and claims.dealerId = dealer.id "
			+ "ORDER BY claims.lastUpdate DESC")
	List<ClaimsDO> findClaimsInfo(@Param("dealerId") int dealerId);
	
	@Query("select new com.agg.application.model.ClaimsDO(claims.claimId, cus.name, dealer.name, claims.serial, quotes.manufacturer.manfName, "
			+ "quotes.machineInfo.model, claims.cStatus, claims.cheqNo, claims.paidDate, claims.lastUpdate) from "
			+ "Claims claims, Contracts contracts, Quote quotes, CustomerInfo cus, Dealer dealer "
			+ "where claims.contractId = contracts.contractId "
			+ "and contracts.quoteId = quotes.id.id "
			+ "and quotes.id.quoteId = cus.quoteId "
			+ "and claims.dealerId = dealer.id "
			+ "ORDER BY claims.lastUpdate DESC")
	List<ClaimsDO> findClaimsInfo();
	
	@Query("select new com.agg.application.model.ClaimsDO(claims.claimId, cus.name, dealer.name, claims.serial, quotes.manufacturer.manfName, "
			+ "quotes.machineInfo.model, claims.cStatus, claims.cheqNo, claims.paidDate, claims.lastUpdate) from "
			+ "Claims claims, Contracts contracts, Quote quotes, CustomerInfo cus, Dealer dealer "
			+ "where claims.contractId = contracts.contractId "
			+ "and contracts.quoteId = quotes.id.id "
			+ "and quotes.id.quoteId = cus.quoteId "
			+ "and claims.dealerId = dealer.id "
			+ "and claims.cStatus = :cStatus "
			+ "ORDER BY claims.lastUpdate DESC")
	List<ClaimsDO> findApprovedClaims(@Param("cStatus") byte cStatus);
	
	@Query("select new com.agg.application.model.ClaimsDO(claims.claimId, cus.name, dealer.name, claims.serial, quotes.manufacturer.manfName, "
			+ "quotes.machineInfo.model, claims.cStatus, claims.cheqNo, claims.paidDate, claims.lastUpdate) from "
			+ "Claims claims, Contracts contracts, Quote quotes, CustomerInfo cus, Dealer dealer "
			+ "where claims.contractId = contracts.contractId "
			+ "and contracts.quoteId = quotes.id.id "
			+ "and quotes.id.quoteId = cus.quoteId "
			+ "and claims.dealerId = dealer.id "
			+ "and claims.cStatus = :cStatus "
			+ "and dealer.id = :dealerId "
			+ "ORDER BY claims.lastUpdate DESC")
	List<ClaimsDO> findApprovedClaims(@Param("cStatus") byte cStatus, @Param("dealerId") long dealerId);
	
	@Query("select new com.agg.application.model.ClaimsDO(claims.claimId, cus.name, dealer.name, claims.serial, quotes.manufacturer.manfName, "
			+ "quotes.machineInfo.model, claims.cStatus, claims.cheqNo, claims.paidDate, claims.lastUpdate) from "
			+ "Claims claims, Contracts contracts, Quote quotes, CustomerInfo cus, Dealer dealer "
			+ "where claims.contractId = contracts.contractId "
			+ "and contracts.quoteId = quotes.id.id "
			+ "and quotes.id.quoteId = cus.quoteId "
			+ "and claims.dealerId = dealer.id "
			+ "and claims.cStatus in (:cStatuses) "
			+ "ORDER BY claims.lastUpdate DESC")
	List<ClaimsDO> findRejectedClaims(@Param("cStatuses") List<Byte> cStatuses);
	
	@Query("select new com.agg.application.model.ClaimsDO(claims.claimId, cus.name, dealer.name, claims.serial, quotes.manufacturer.manfName, "
			+ "quotes.machineInfo.model, claims.cStatus, claims.cheqNo, claims.paidDate, claims.lastUpdate) from "
			+ "Claims claims, Contracts contracts, Quote quotes, CustomerInfo cus, Dealer dealer "
			+ "where claims.contractId = contracts.contractId "
			+ "and contracts.quoteId = quotes.id.id "
			+ "and quotes.id.quoteId = cus.quoteId "
			+ "and claims.dealerId = dealer.id "
			+ "and claims.cStatus in (:cStatuses) "
			+ "and dealer.id = :dealerId "
			+ "ORDER BY claims.lastUpdate DESC")
	List<ClaimsDO> findRejectedClaims(@Param("cStatuses") List<Byte> cStatuses, @Param("dealerId") long dealerId);
	
	@Query("select new com.agg.application.model.ClaimsDO(claims.claimId, cus.name, dealer.name, claims.serial, quotes.manufacturer.manfName, "
			+ "quotes.machineInfo.model, claims.cStatus, claims.cheqNo, claims.paidDate, claims.lastUpdate) from "
			+ "Claims claims, Contracts contracts, Quote quotes, CustomerInfo cus, Dealer dealer "
			+ "where claims.contractId = contracts.contractId "
			+ "and contracts.quoteId = quotes.id.id "
			+ "and quotes.id.quoteId = cus.quoteId "
			+ "and claims.cStatus != :cStatus "
			+ "and claims.dealerId = dealer.id "
			+ "ORDER BY claims.lastUpdate DESC")
	List<ClaimsDO> findAdminClaimsInfo(@Param("cStatus") byte cStatus);
	
	@Query("SELECT COUNT(*) AS claimsCount FROM Claims c WHERE c.contractId = :contractId")
	int getContractsCount(@Param("contractId") String contractId);
	
	@Query("SELECT COUNT(*) FROM Claims c WHERE c.dealerId = :dealerId")
	long findClaimsCountByDealer(@Param("dealerId") int dealerId);
	
	@Query("SELECT COUNT(*) FROM Claims c WHERE c.cStatus != :cStatus and c.dealerId > 0")
	long findClaimsCountByAdmin(@Param("cStatus") byte cStatus);
	
	@Query("SELECT c FROM Claims c WHERE c.claimId = :claimId ORDER BY c.lastUpdate DESC")
	Claims findClaimsByClaimId(@Param("claimId")String claimId);
	
	Claims findByIdAndClaimId(int id, String claimId);
	
	@Query("SELECT COUNT(*) FROM Claims c WHERE c.cStatus = :cStatus and c.dealerId > 0")
	long findClaimsCountByStatus(@Param("cStatus") byte cStatus);
	
	@Query(value="select work_order from Claim_Report_v", nativeQuery=true)
	List<Object[]> findAllClaimsForReport();
}
