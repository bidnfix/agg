/**
 * htamada
 */
package com.agg.application.service.impl;

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
import com.agg.application.entity.Manufacturer;
import com.agg.application.entity.Quote;
import com.agg.application.entity.QuotePK;
import com.agg.application.model.ContractDO;
import com.agg.application.model.ManufacturerDO;
import com.agg.application.service.ContractsService;
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
	public List<ContractDO> getAllContracts() {
		List<Contracts> contracts = Util.toList(contractDAO.findAll());
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
		List<ContractDO> contractsDOList = new LinkedList<>();
		ManufacturerDO manufacturerDO = null;
		for(Contracts item : contractsList){
			ContractDO contractDO = new ContractDO();
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
			contractsDOList.add(contractDO);
			Quote quote = quoteDAO.findOne((int)item.getQuoteId());
			if(null != quote){
				manufacturerDO = new ManufacturerDO();
				Manufacturer manf = manufacturerDAO.findOne(Long.valueOf(quote.getManufacturer().getManfId()));
				manufacturerDO.setId(quote.getManufacturer().getManfId());
				manufacturerDO.setName(manf.getManfName());
				contractDO.setManufacturerDO(manufacturerDO);
				contractDO.setMachineModel(quote.getMachineModel());
			}
		}
		return contractsDOList;
	}
}
