/**
 * htamada
 */
package com.agg.application.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.agg.application.model.AccountDO;
import com.agg.application.model.ContractDO;
import com.agg.application.model.ContractReportDO;
import com.agg.application.model.DealerDO;

/**
 * @author htamada
 *
 */
public interface ContractsService {
	long saveContract(ContractDO contract);
	List<ContractDO> getAllContracts(AccountDO accountDO);
	List<ContractDO> getAllContractsByMachineSerialNo(String machineSerialNo);
	DealerDO getDealer(final String contractId);
	List<ContractDO> getActiveContracts(AccountDO accountDO);
	List<ContractDO> getInactiveContracts(AccountDO accountDO);
	ContractDO getContract(long id, String contractId);
	boolean updateContract(ContractDO contractDO, AccountDO accountDO);
	int getContractsCount(String contractId);
	List<ContractDO> getActiveContracts();
	ContractDO getContract(String contractId);
	ContractReportDO getContractReportDetails(long id, String contractId);
	List<ContractDO> getActiveContractDetails(AccountDO accountDetails);
	long getActiveContractsCount(AccountDO accountDo);
	List<ContractDO> getActiveContracts(AccountDO accountDo, Pageable pageable);
	long getActiveContractsSearchCount(AccountDO accountDo, String searchText);
	List<ContractDO> getActiveContractsForSearch(AccountDO accountDo, String searchText, Pageable pageable);
}
