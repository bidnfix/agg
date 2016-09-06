/**
 * htamada
 */
package com.agg.application.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import com.agg.application.entity.Contracts;

/**
 * @author htamada
 *
 */
@Component
public interface ContractsDAO extends CrudRepository<Contracts, Long>{
	@Query("SELECT c FROM Contracts c WHERE c.machineSerialNo LIKE CONCAT('%', :machineSerialNo, '%') ")
	List<Contracts> findByMachineSerialNo(@Param("machineSerialNo") String machineSerialNo);
	
	@Query("SELECT c FROM Contracts c WHERE c.contractId = :contractId")
	Contracts findByContractId(@Param("contractId") String contractId);
	
	@Query("SELECT c FROM Contracts c, Quote q WHERE c.quoteId = q.id.id and  q.dealer.id = :dealerId")
	List<Contracts> findByDealerId(@Param("dealerId") long dealerId);
	
	List<Contracts> findByStatus(byte status);
}
