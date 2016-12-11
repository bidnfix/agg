/**
 * htamada
 */
package com.agg.application.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agg.application.dao.ContractsDAO;
import com.agg.application.dao.ManufacturerDAO;
import com.agg.application.dao.QuoteDAO;
import com.agg.application.entity.Contracts;
import com.agg.application.entity.Dealer;
import com.agg.application.entity.Manufacturer;
import com.agg.application.entity.Quote;
import com.agg.application.model.AccountDO;
import com.agg.application.model.ContractDO;
import com.agg.application.model.DealerDO;
import com.agg.application.model.ManufacturerDO;
import com.agg.application.service.ContractsService;
import com.agg.application.utils.AggConstants;
import com.agg.application.utils.Util;

/**
 * @author htamada
 *
 */
@Service
public class ContractsServiceImpl implements ContractsService{
	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private ContractsDAO contractDAO;
	
	@Autowired
	private QuoteDAO quoteDAO;
	
	@Autowired
	private ManufacturerDAO manufacturerDAO;

	/* (non-Javadoc)
	 * @see com.agg.application.service.ContractsService#saveContract(com.agg.application.model.ContractDO)
	 */
	@Override
	@Transactional
	public long saveContract(ContractDO contract) {
		LOGGER.debug("In saveContract");
		Contracts response = null;
		Contracts record = new Contracts();
		record.setContractId(contract.getContractId());
		record.setQuoteId(contract.getQuoteId());
		record.setStatus(contract.getStatus());
		record.setMachineSerialNo(contract.getMachineSerialNo());
		record.setCoverageType(contract.getCoverageType());
		record.setCoverageTermMonths(contract.getCoverageTermMonths());
		record.setCoverageLevelHours(contract.getCoverageLevelHours());
		record.setDeductible(contract.getDeductible());
		record.setLol(contract.getLol());
		record.setAvailabeLol(contract.getAvailabeLol());
		record.setInceptionDate(contract.getInceptionDate());
		record.setExpirationDate(contract.getExpirationDate());
		record.setExpirationUsageHours(contract.getExpirationUsageHours());
		record.setComments(contract.getComments());
		record.setLastUpdatedDate(contract.getLastUpdatedDate());
		try{
			response = contractDAO.save(record);
		}catch(Exception e){
			LOGGER.error(e.getMessage());
		}
		return response.getId();
	}

	/* (non-Javadoc)
	 * @see com.agg.application.service.ContractsService#getAllContracts()
	 */
	@Override
	public List<ContractDO> getAllContracts(AccountDO accountDO) {
		List<Contracts> contracts = null;
		if(accountDO.getRoleDO().getAccountType().equalsIgnoreCase(AggConstants.ACCOUNT_TYPE_ADMIN)){
			contracts = Util.toList(contractDAO.findAll());
		}else{
			contracts = Util.toList(contractDAO.findByDealerId(accountDO.getDealerId()));
		}
		return formatEntityToDO(contracts);
	}
	
	/* (non-Javadoc)
	 * @see com.agg.application.service.ContractsService#getAllContractsByMachineSerialNo(java.lang.String)
	 */
	@Override
	public List<ContractDO> getAllContractsByMachineSerialNo(String machineSerialNo) {
		List<Contracts> contracts = Util.toList(contractDAO.findByMachineSerialNo(machineSerialNo));
		return formatEntityToDO(contracts);
	}
	
	private List<ContractDO> formatEntityToDO(final List<Contracts> contractsList){
		LOGGER.debug("Inside formatEntityToDO method with contractsList size: "+contractsList.size());
		List<ContractDO> contractsDOList = new LinkedList<ContractDO>();
		ManufacturerDO manufacturerDO = null;
		ContractDO contractDO = null;
		for(Contracts item : contractsList){
			contractDO = new ContractDO();
			contractDO.setId(item.getId());
			contractDO.setContractId(item.getContractId());
			contractDO.setQuoteId(item.getQuoteId());
			contractDO.setStatus(item.getStatus());
			contractDO.setMachineSerialNo(item.getMachineSerialNo());
			contractDO.setCoverageType(item.getCoverageType());
			contractDO.setCoverageTermMonths(item.getCoverageTermMonths());
			contractDO.setCoverageLevelHours(item.getCoverageLevelHours());
			contractDO.setDeductible(item.getDeductible());
			contractDO.setLol(item.getLol());
			contractDO.setAvailabeLol(item.getAvailabeLol());
			contractDO.setInceptionDate(item.getInceptionDate());
			contractDO.setExpirationDate(item.getExpirationDate());
			contractDO.setExpirationUsageHours(item.getExpirationUsageHours());
			contractDO.setComments(item.getComments());
			contractDO.setLastUpdatedDate(item.getLastUpdatedDate());
			
			Quote quote = quoteDAO.findOne((int)item.getQuoteId());
			if(null != quote){
				manufacturerDO = new ManufacturerDO();
				Manufacturer manf = manufacturerDAO.findOne(Long.valueOf(quote.getManufacturer().getManfId()));
				manufacturerDO.setId(quote.getManufacturer().getManfId());
				manufacturerDO.setName(manf.getManfName());
				contractDO.setManufacturerDO(manufacturerDO);
				contractDO.setMachineModel(quote.getMachineModel());
			}
			
			contractsDOList.add(contractDO);
		}
		return contractsDOList;
	}

	/* (non-Javadoc)
	 * @see com.agg.application.service.ContractsService#getDealer(java.lang.String)
	 */
	@Override
	public DealerDO getDealer(String contractId) {
		DealerDO dealerDO = null;
		Contracts contract = contractDAO.findByContractId(contractId);
		if(null != contract){
			Quote quote = quoteDAO.findOne((int)contract.getQuoteId());
			Dealer dealer = quote.getDealer();
			if(null != dealer){
				dealerDO = new DealerDO();
				dealerDO.setId(dealer.getId());
				dealerDO.setName(dealer.getName());
				dealerDO.setCode(dealer.getCode());
				dealerDO.setAddress1(dealer.getAddress());
				dealerDO.setAddress2(dealer.getAddress2());
				dealerDO.setCity(dealer.getCity());
				dealerDO.setState(dealer.getState());
				dealerDO.setZip(dealer.getZip());
				dealerDO.setPhone(dealer.getPhone());
				dealerDO.setInvoiceEmail(dealer.getInvoiceEmail());
				dealerDO.setMarketEmail(dealer.getMarketEmail());
			}
		}
		return dealerDO;
	}
	
	@Override
	public List<ContractDO> getActiveContracts(AccountDO accountDO) {
		List<Contracts> contracts = null;
		if(accountDO.getRoleDO().getAccountType().equalsIgnoreCase(AggConstants.ACCOUNT_TYPE_ADMIN)){
			contracts = Util.toList(contractDAO.findActive());
		}else{
			contracts = Util.toList(contractDAO.findActiveByDealerId(accountDO.getDealerId()));
		}
		return formatEntityToDO(contracts);
	}
	
	@Override
	public List<ContractDO> getActiveContracts() {
		List<Contracts> contracts = null;
		contracts = Util.toList(contractDAO.findActive());
		return formatEntityToDO(contracts);
	}
	
	@Override
	public List<ContractDO> getInactiveContracts(AccountDO accountDO) {
		List<Contracts> contracts = null;
		if(accountDO.getRoleDO().getAccountType().equalsIgnoreCase(AggConstants.ACCOUNT_TYPE_ADMIN)){
			contracts = Util.toList(contractDAO.findInactive());
		}else{
			contracts = Util.toList(contractDAO.findInactiveByDealerId(accountDO.getDealerId()));
		}
		return formatEntityToDO(contracts);
	}

	@Override
	public ContractDO getContract(long id, String contractId) {
		List<Contracts> contractsList = contractDAO.findByIdAndContractId(id, contractId);
		List<ContractDO> contractDOList = formatEntityToDO(contractsList);
		if(contractDOList != null && !contractDOList.isEmpty()){
			return contractDOList.get(0);
		}
		
		return null;
	}
	
	@Override
	public ContractDO getContract(String contractId) {
		Contracts contracts = contractDAO.findByContractId(contractId);
		List<Contracts> contractsList = new ArrayList<Contracts>();
		contractsList.add(contracts);
		List<ContractDO> contractDOList = formatEntityToDO(contractsList);
		if(contractDOList != null && !contractDOList.isEmpty()){
			return contractDOList.get(0);
		}
		
		return null;
	}

	@Override
	public boolean updateContract(ContractDO contractDO) {
		List<Contracts> contractsList = contractDAO.findByIdAndContractId(contractDO.getId(), contractDO.getContractId());
		boolean cond = false;
		if(contractsList != null && !contractsList.isEmpty()){
			Contracts contracts = contractsList.get(0);
			contracts.setAvailabeLol(contractDO.getAvailabeLol());
			contracts.setInceptionDate(contractDO.getInceptionDate());
			contracts.setExpirationDate(contractDO.getExpirationDate());
			contracts.setComments(contractDO.getComments());
			contracts.setStatus(contractDO.getStatus());
			contracts.setLastUpdatedDate(new Date());
			
			contractDAO.save(contracts);
			
			cond = true;
		}
		return cond;
	}

	@Override
	public int getContractsCount(String contractId) {
		return contractDAO.getContractsCount(contractId);
	}
	
}
