/**
 * htamada
 */
package com.agg.application.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
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
	
	@Query("SELECT c FROM Contracts c WHERE c.servicingDealer.id = :dealerId")
	List<Contracts> findByDealerId(@Param("dealerId") long dealerId);
	
	List<Contracts> findByStatus(byte status);
	
	@Query("SELECT COUNT(*) FROM Contracts c WHERE c.status= :status")
	public int countByStatus(@Param("status")byte status);
	
	@Query("SELECT COUNT(*) FROM Contracts c WHERE c.status= 1")
	public int countByActive();
	
	@Query("SELECT COUNT(*) FROM Contracts c WHERE c.status= 1 and c.servicingDealer.id = :dealerId")
	public int countActiveContractByDealer(@Param("dealerId") long dealerId);
	
	@Query("SELECT COUNT(*) FROM Contracts c WHERE c.status= 2")
	public int countByInactive();
	
	@Query("SELECT COUNT(*) FROM Contracts c WHERE c.status= 2 and c.servicingDealer.id = :dealerId")
	public int countInActiveContractByDealer(@Param("dealerId") long dealerId);
	
	/*@Query("SELECT COUNT(*) FROM Contracts c WHERE c.status= :status and c.dealer.id = :dealerId")
	public int countByStatus(@Param("status")byte status, @Param("dealerId")long dealerId);*/
	
	@Query("SELECT c FROM Contracts c WHERE c.status = 1")
	List<Contracts> findActive();
	
	@Query("SELECT c FROM Contracts c WHERE c.status = 2")
	List<Contracts> findInactive();
	
	@Query("SELECT c FROM Contracts c WHERE c.status = 1 and c.servicingDealer.id = :dealerId")
	List<Contracts> findActiveByDealerId(@Param("dealerId") long dealerId);
	
	@Query("SELECT c FROM Contracts c WHERE c.status = 2 and c.servicingDealer.id = :dealerId")
	List<Contracts> findInactiveByDealerId(@Param("dealerId") long dealerId);
	
	List<Contracts> findByIdAndContractId(long id, String contractId);
	
	@Query("SELECT COUNT(*) AS contractsCount FROM Contracts c WHERE c.contractId = :contractId")
	int getContractsCount(@Param("contractId") String contractId);
	
	@Query("select new com.agg.application.model.ContractDO(contract.id, contract.contractId, contract.machineSerialNo, contract.lol, contract.inceptionDate, "
			+ "contract.expirationDate, contract.expirationUsageHours, contract.status, contract.lastUpdatedDate, contract.cheqNo, contract.receivedDate) from Contracts contract")
	public List<ContractDO> findAllContracts();
	
	@Query("select new com.agg.application.model.ContractDO(contract.id, contract.contractId, contract.machineSerialNo, contract.lol, contract.inceptionDate, "
			+ "contract.expirationDate, contract.expirationUsageHours, contract.status, contract.lastUpdatedDate, contract.cheqNo, contract.receivedDate) "
			+ "from Contracts contract "
			+ "where contract.servicingDealer.id = :dealerId")
	public List<ContractDO> findAllContracts(@Param("dealerId") long dealerId);
	
	@Query("select new com.agg.application.model.ContractDO(contract.id, contract.contractId, contract.machineSerialNo, contract.lol, contract.inceptionDate, "
			+ "contract.expirationDate, contract.expirationUsageHours, contract.status, contract.lastUpdatedDate, contract.cheqNo, contract.receivedDate) "
			+ "from Contracts contract "
			+ "where contract.status = :status")
	public List<ContractDO> findContractsByStatus(@Param("status") int status);
	
	@Query("select new com.agg.application.model.ContractDO(contract.id, contract.contractId, contract.machineSerialNo, contract.lol, contract.inceptionDate, "
			+ "contract.expirationDate, contract.expirationUsageHours, contract.status, contract.lastUpdatedDate, contract.cheqNo, contract.receivedDate) "
			+ "from Contracts contract "
			+ "where contract.servicingDealer.id = :dealerId "
			+ "and contract.status = :status")
	public List<ContractDO> findContractsByStatusAndDelaerId(@Param("status") int status, @Param("dealerId") long dealerId);

	@Query("SELECT COUNT(*) AS count   "
			+ "from Contracts contract where contract.status = :status")
	public long getContractsCountByStatus(@Param("status") int status);

	@Query("select new com.agg.application.model.ContractDO(contract.id, contract.contractId, contract.machineSerialNo, contract.lol, contract.inceptionDate, "
			+ "contract.expirationDate, contract.expirationUsageHours, contract.status, contract.lastUpdatedDate, contract.cheqNo, contract.receivedDate) "
			+ "from Contracts contract "
			+ "where contract.status = :status")
	public List<ContractDO> findActiveContractsByStatus(@Param("status") int status, Pageable pageable);

	@Query("SELECT COUNT(*) AS count   "
			+ "from Contracts contract where contract.status = :status and (contract.contractId like %:searchText% or contract.machineSerialNo like %:searchText% or"
			+ " contract.lol like %:searchText% or contract.inceptionDate like %:searchText% or contract.expirationDate like %:searchText% or"
			+ " contract.expirationUsageHours like %:searchText% or contract.status like %:searchText% or contract.lastUpdatedDate like %:searchText%)")
	public long getContractsCountByStatusForSearch(@Param("status") int status, @Param("searchText") String searchText);

	@Query("select new com.agg.application.model.ContractDO(contract.id, contract.contractId, contract.machineSerialNo, contract.lol, contract.inceptionDate, "
			+ "contract.expirationDate, contract.expirationUsageHours, contract.status, contract.lastUpdatedDate, contract.cheqNo, contract.receivedDate) "
			+ "from Contracts contract where contract.status = :status and (contract.contractId like %:searchText% or contract.machineSerialNo like %:searchText% or"
			+ " contract.lol like %:searchText% or contract.inceptionDate like %:searchText% or contract.expirationDate like %:searchText% or"
			+ " contract.expirationUsageHours like %:searchText% or contract.status like %:searchText% or contract.lastUpdatedDate like %:searchText%)")
	public List<ContractDO> findActiveContractsByStatusForSearch(@Param("status") int status, @Param("searchText") String searchText, Pageable pageable);
	
	@Query("select COUNT(*) "
			+ "from Contracts contract "
			+ "where contract.servicingDealer.id = :dealerId "
			+ "and contract.status = :status")
	public long findContractsCountByStatusAndDelaerId(@Param("status") int status, @Param("dealerId") long dealerId);
	
	@Query("select COUNT(*) "
			+ "from Contracts contract "
			+ "where contract.servicingDealer.id = :dealerId "
			+ "and contract.status = :status and (contract.contractId like %:searchText% or contract.machineSerialNo like %:searchText% or"
			+ " contract.lol like %:searchText% or contract.inceptionDate like %:searchText% or contract.expirationDate like %:searchText% or"
			+ " contract.expirationUsageHours like %:searchText% or contract.status like %:searchText% or contract.lastUpdatedDate like %:searchText%)")
	public long findContractsCountByStatusAndDelaerIdForSearch(@Param("status") int status, @Param("dealerId") long dealerId, @Param("searchText") String searchText);
	
	@Query("select new com.agg.application.model.ContractDO(contract.id, contract.contractId, contract.machineSerialNo, contract.lol, contract.inceptionDate, "
			+ "contract.expirationDate, contract.expirationUsageHours, contract.status, contract.lastUpdatedDate, contract.cheqNo, contract.receivedDate) "
			+ "from Contracts contract "
			+ "where contract.servicingDealer.id = :dealerId "
			+ "and contract.status = :status and (contract.contractId like %:searchText% or contract.machineSerialNo like %:searchText% or"
			+ " contract.lol like %:searchText% or contract.inceptionDate like %:searchText% or contract.expirationDate like %:searchText% or"
			+ " contract.expirationUsageHours like %:searchText% or contract.status like %:searchText% or contract.lastUpdatedDate like %:searchText%)")
	public List<ContractDO> findContractsByStatusAndDelaerIdForSearch(@Param("status") int status, @Param("dealerId") long dealerId, @Param("searchText") String searchText, Pageable pageable);
	
	@Query("select new com.agg.application.model.ContractDO(contract.id, contract.contractId, contract.machineSerialNo, contract.lol, contract.inceptionDate, "
			+ "contract.expirationDate, contract.expirationUsageHours, contract.status, contract.lastUpdatedDate, contract.cheqNo, contract.receivedDate) "
			+ "from Contracts contract "
			+ "where contract.servicingDealer.id = :dealerId "
			+ "and contract.status = :status")
	public List<ContractDO> findContractsByStatusAndDelaerId(@Param("status") int status, @Param("dealerId") long dealerId, Pageable pageable);
	
	@Query(value="select * from Active_Contract", nativeQuery=true)
	List<Object[]> findAllActiveContractrReport();
	
	@Query("select year(contract.createdDate) as year, month(contract.createdDate) as month, count(contract.id) as total "
			+ "from Contracts contract "
			+ "where contract.status = :status "
			+ "group by  month(contract.createdDate), year(contract.createdDate) "
			+ "order by year asc, month asc")
	public List<Object[]> findContractDetails(@Param("status") int status);
	
	
	@Query(value="select claim.contract_id, count(claim.contract_id) as claimCount, machine.model, contract.machine_serial_no, contract.lol, contract.availabe_lol, "
			+ "(contract.lol - contract.availabe_lol) as claimAmount, quote.quote_id, quote.id, quote.machine_model_id "
			+ "from claims claim, contracts contract, quotes quote, machine_info machine "
			+ "where contract.status = :status "
			+ "and claim.contract_id = contract.contract_id "
			+ "and contract.quote_id = quote.id "
			+ "and quote.machine_model_id = machine.machine_id "
			+ "group by claim.contract_id "
			+ "having count(claim.contract_id) > 0 "
			+ "order by claimCount desc limit :claimCountLimit", nativeQuery=true)
	/*@Query("select claim.contractId, count(claim.contractId) as claimCount, machine.model, contract.machineSerialNo, contract.lol, contract.availabeLol, "
			+ "(contract.lol - contract.availabeLol) as claimAmount, quote.id.quoteId, quote.id.id, quote.machineInfo.machineId "
			+ "from Claims claim, Contracts contract, Quote quote, MachineInfo machine "
			+ "where contract.status = :status "
			+ "and claim.contractId = contract.contractId "
			+ "and contract.quoteId = quote.id.id "
			+ "and quote.machineInfo.machineId = machine.machineId "
			+ "group by claim.contractId "
			+ "having count(claim.contractId) > 0 "
			+ "order by claimCount desc limit :claimCountLimit")*/
	List<Object[]> findActiveContractClaimsReporDetails(@Param("status") int status, @Param("claimCountLimit") int claimLimit);
	
	
	@Query(value="select * from v_top10_contract_claim_report", nativeQuery=true)
	List<Object[]> findActiveContractClaimsReporDetails();
	
	
}
