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
import com.agg.application.model.ContractDO;

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
	
	@Query("SELECT c FROM Contracts c WHERE c.contractId IN :contractId")
	List<Contracts> findAllByContractID(@Param("contractId") List<String> contractId);
	
	@Query("SELECT c FROM Contracts c, Quote q WHERE c.quoteId = q.id.id and  q.dealer.id = :dealerId")
	List<Contracts> findByDealerId(@Param("dealerId") long dealerId);
	
	List<Contracts> findByStatus(byte status);
	
	@Query("SELECT COUNT(*) FROM Contracts c WHERE c.status= :status")
	public int countByStatus(@Param("status")byte status);
	
	@Query("SELECT COUNT(*) FROM Contracts c WHERE c.status= 1")
	public int countByActive();
	
	@Query("SELECT COUNT(*) FROM Contracts c, Quote q WHERE c.status= 1 and  c.quoteId = q.id.id and  q.dealer.id = :dealerId")
	public int countActiveContractByDealer(@Param("dealerId") long dealerId);
	
	@Query("SELECT COUNT(*) FROM Contracts c WHERE c.status= 2")
	public int countByInactive();
	
	@Query("SELECT COUNT(*) FROM Contracts c, Quote q WHERE c.status= 2 and  c.quoteId = q.id.id and  q.dealer.id = :dealerId")
	public int countInActiveContractByDealer(@Param("dealerId") long dealerId);
	
	/*@Query("SELECT COUNT(*) FROM Contracts c WHERE c.status= :status and c.dealer.id = :dealerId")
	public int countByStatus(@Param("status")byte status, @Param("dealerId")long dealerId);*/
	
	@Query("SELECT c FROM Contracts c WHERE c.status = 1")
	List<Contracts> findActive();
	
	@Query("SELECT c FROM Contracts c WHERE c.status = 2")
	List<Contracts> findInactive();
	
	@Query("SELECT c FROM Contracts c, Quote q WHERE c.quoteId = q.id.id and c.status = 1 and  q.dealer.id = :dealerId")
	List<Contracts> findActiveByDealerId(@Param("dealerId") long dealerId);
	
	@Query("SELECT c FROM Contracts c, Quote q WHERE c.quoteId = q.id.id and c.status = 2 and  q.dealer.id = :dealerId")
	List<Contracts> findInactiveByDealerId(@Param("dealerId") long dealerId);
	
	List<Contracts> findByIdAndContractId(long id, String contractId);
	
	@Query("SELECT COUNT(*) AS contractsCount FROM Contracts c WHERE c.contractId = :contractId")
	int getContractsCount(@Param("contractId") String contractId);
	
	@Query("select new com.agg.application.model.ContractDO(contract.id, contract.contractId, contract.machineSerialNo, contract.lol, contract.inceptionDate, "
			+ "contract.expirationDate, contract.expirationUsageHours, contract.status, contract.lastUpdatedDate, contract.cheqNo, contract.receivedDate) from Contracts contract")
	public List<ContractDO> findAllContracts();
	
	@Query("select new com.agg.application.model.ContractDO(contract.id, contract.contractId, contract.machineSerialNo, contract.lol, contract.inceptionDate, "
			+ "contract.expirationDate, contract.expirationUsageHours, contract.status, contract.lastUpdatedDate, contract.cheqNo, contract.receivedDate) "
			+ "from Contracts contract, Quote quote "
			+ "where contract.quoteId=quote.id.id and quote.dealer.id = :dealerId")
	public List<ContractDO> findAllContracts(@Param("dealerId") long dealerId);
}
