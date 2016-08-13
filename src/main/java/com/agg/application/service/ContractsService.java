/**
 * htamada
 */
package com.agg.application.service;

import java.util.List;

import com.agg.application.model.ContractDO;
import com.agg.application.model.DealerDO;

/**
 * @author htamada
 *
 */
public interface ContractsService {
	long saveContract(ContractDO contract);
	List<ContractDO> getAllContracts();
	List<ContractDO> getAllContractsByMachineSerialNo(String machineSerialNo);
	DealerDO getDealer(final String contractId);
}
