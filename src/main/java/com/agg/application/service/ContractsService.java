/**
 * htamada
 */
package com.agg.application.service;

import java.util.List;

import com.agg.application.model.AccountDO;
import com.agg.application.model.ContractDO;
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
	boolean updateContract(ContractDO contractDO);
	int getContractsCount(String contractId);
	List<ContractDO> getActiveContracts();
}
