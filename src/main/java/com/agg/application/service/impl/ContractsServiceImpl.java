/**
 * htamada
 */
package com.agg.application.service.impl;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agg.application.dao.AdminAdjustmentDAO;
import com.agg.application.dao.CheckDAO;
import com.agg.application.dao.ContractsDAO;
import com.agg.application.dao.CustomerInfoDAO;
import com.agg.application.dao.ManufacturerDAO;
import com.agg.application.dao.QuoteDAO;
import com.agg.application.entity.AdminAdjustment;
import com.agg.application.entity.Check;
import com.agg.application.entity.Contracts;
import com.agg.application.entity.CustomerInfo;
import com.agg.application.entity.Dealer;
import com.agg.application.entity.MachineInfo;
import com.agg.application.entity.Manufacturer;
import com.agg.application.entity.Quote;
import com.agg.application.entity.UseOfEquip;
import com.agg.application.model.AccountDO;
import com.agg.application.model.CheckDO;
import com.agg.application.model.ContractDO;
import com.agg.application.model.ContractReportDO;
import com.agg.application.model.DealerDO;
import com.agg.application.model.MachineInfoDO;
import com.agg.application.model.ManufacturerDO;
import com.agg.application.model.QuoteDO;
import com.agg.application.model.UseOfEquipmentDO;
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
	private CustomerInfoDAO customerInfoDAO;
	
	@Autowired
	private AdminAdjustmentDAO adminAdjustmentDAO;
	
	@Autowired
	private ManufacturerDAO manufacturerDAO;
	
	@Autowired
	private CheckDAO checkDAO;
	
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");

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
		record.setCheqNo(contract.getCheqNo());
		record.setReceivedDate(contract.getReceivedDate());
		
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
		//List<Contracts> contracts = null;
		List<ContractDO> contractDOList = null;
		if(accountDO.getRoleDO().getAccountType().equalsIgnoreCase(AggConstants.ACCOUNT_TYPE_ADMIN)){
			//contracts = Util.toList(contractDAO.findAll());
			contractDOList = contractDAO.findAllContracts();
		}else{
			//contracts = Util.toList(contractDAO.findByDealerId(accountDO.getDealerId()));
			contractDOList = contractDAO.findAllContracts(accountDO.getDealerId());
		}
		//return formatEntityToDO(contracts);
		return contractDOList;
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
			if(item.getInceptionDate() != null){
				contractDO.setInceptionDate(new java.sql.Timestamp(item.getInceptionDate().getTime()));
			}
			if(item.getExpirationDate() != null){
				contractDO.setExpirationDate(new java.sql.Timestamp(item.getExpirationDate().getTime()));
			}
			contractDO.setExpirationUsageHours(item.getExpirationUsageHours());
			contractDO.setComments(item.getComments());
			contractDO.setLastUpdatedDate(item.getLastUpdatedDate());
			contractDO.setCheqNo(item.getCheqNo());
			if(item.getReceivedDate() != null){
				contractDO.setReceivedDate(new java.sql.Timestamp(item.getReceivedDate().getTime()));
			}
			
			Set<Check> checks = item.getChecks();
			if(checks != null && !checks.isEmpty()){
				CheckDO checkDO = null;
				List<CheckDO> checkDOList = new ArrayList<CheckDO>();
				for(Check check : checks){
					checkDO = new CheckDO();
					checkDO.setAmount(check.getCheckAmount());
					checkDO.setCheckNo(check.getCheckNo());
					checkDO.setReceivedDate(check.getReceivedDate());
					
					checkDOList.add(checkDO);
				}
				contractDO.setCheckDOList(checkDOList);
			}
			
			Quote quote = quoteDAO.findOne((int)item.getQuoteId());
			if(null != quote){
				manufacturerDO = new ManufacturerDO();
				Manufacturer manf = manufacturerDAO.findOne(Long.valueOf(quote.getManufacturer().getManfId()));
				manufacturerDO.setId(quote.getManufacturer().getManfId());
				manufacturerDO.setName(manf.getManfName());
				contractDO.setManufacturerDO(manufacturerDO);
				contractDO.setMachineModel(quote.getMachineInfo().getModel());
				
				contractDO.setQuoteDO(getQuoteDetails(quote));
			}
			
			contractsDOList.add(contractDO);
		}
		return contractsDOList;
	}
	
	/**
	 * @param quote
	 * @return QuoteDO
	 */
	private QuoteDO getQuoteDetails(Quote quote) {
		QuoteDO quoteDO = null;
		if(quote != null){
			quoteDO = new QuoteDO();
			quoteDO.setId(quote.getId().getId());
			quoteDO.setQuoteId(quote.getId().getQuoteId());
			Dealer dealer = quote.getDealer();
			if(dealer != null){
				DealerDO dealerDO = new DealerDO();
				dealerDO.setCode(dealer.getCode());
				dealerDO.setId(dealer.getId());
				dealerDO.setName(dealer.getName());
				dealerDO.setInvoiceEmail(dealer.getInvoiceEmail());
				dealerDO.setMarketEmail(dealer.getMarketEmail());
				dealerDO.setCity(dealer.getCity());
				dealerDO.setAddress1(dealer.getAddress());
				dealerDO.setAddress2(dealer.getAddress2());
				dealerDO.setState(dealer.getState());
				dealerDO.setZip(dealer.getZip());
				dealerDO.setPhone(dealer.getPhone());
				
				quoteDO.setDealerDO(dealerDO);
			}
			quoteDO.setCoverageExpired((quote.getManfExpired() == 1)?true:false);
			if(quote.getManfEndDate() != null){
				quoteDO.setCoverageEndDate(new java.sql.Timestamp(quote.getManfEndDate().getTime()));
			}
			if(quote.getManfEndDate() != null){
				quoteDO.setCoverageEndDateStr(dateFormat.format(quote.getManfEndDate()));
			}
			quoteDO.setCoverageEndDateUnknown((quote.getManfEndKnown() == 1)?true:false);
			quoteDO.setCoverageEndDateVerified((quote.getManfVerified() == 1)?true:false);
			quoteDO.setPowerTrainMonths(quote.getPtMonths());
			quoteDO.setPowerTrainHours(quote.getPtHours());
			quoteDO.setHydraulicsMonths(quote.getHMonths());
			quoteDO.setHydraulicsHours(quote.getHHours());
			quoteDO.setFullMachineHours(quote.getMachineHours());
			quoteDO.setFullMachineMonths(quote.getMachineMonths());
			
			Manufacturer manufacturer = quote.getManufacturer();
			if(manufacturer != null){
				ManufacturerDO manufacturerDO = new ManufacturerDO();
				manufacturerDO.setId(manufacturer.getManfId());
				manufacturerDO.setName(manufacturer.getManfName());
				
				quoteDO.setManufacturerDO(manufacturerDO);
			}
			
			AdminAdjustment adminAdjustment = adminAdjustmentDAO.findOne(quote.getId().getQuoteId());
			MachineInfo machineInfo = quote.getMachineInfo();
			if(machineInfo != null){
				MachineInfoDO machineInfoDO = new MachineInfoDO();
				machineInfoDO.setMachineId(machineInfo.getMachineId());
				machineInfoDO.setMachineType(machineInfo.getMachineType().getMachineType());
				machineInfoDO.setModel(machineInfo.getModel());
				machineInfoDO.setModelYear((machineInfo.getModelYear() != null)?machineInfo.getModelYear():0);
				machineInfoDO.setEPower(machineInfo.getEPower());
				machineInfoDO.setLol(machineInfo.getGroupConstant().getLol());
				machineInfoDO.setLolToDisplay(machineInfo.getGroupConstant().getLol());
				if(quote.getProgram() != null){
					machineInfoDO.setLol(quote.getProgram().getPrLol());
					machineInfoDO.setLolToDisplay(quote.getProgram().getPrLol());
				}
				if(adminAdjustment != null && adminAdjustment.getLol() > 0){
					machineInfoDO.setLol(adminAdjustment.getLol());
				}
				
				quoteDO.setMachineInfoDO(machineInfoDO);
			}
			
			if(quote.getProgram() != null){
				quoteDO.setProgram(quote.getProgram().getPrName());
			}
			
			quoteDO.setGroupId(quote.getGroupId());
			quoteDO.setHorsePower(quote.getMachinePower());
			quoteDO.setSerialNumber(quote.getMachineSerial());
			if(quote.getMachineSerial() == null){
				quoteDO.setSerialNumberUnknown(true);
			}
			quoteDO.setRetailPrice(quote.getMachineRetailPrice());
			quoteDO.setMeterHours(quote.getMachineMeterHours());
			quoteDO.setModelYear((quote.getMachineYear() != null)?quote.getMachineYear():0);
			
			UseOfEquip useOfEquip = quote.getUseOfEquip();
			if(useOfEquip != null){
				UseOfEquipmentDO useOfEquipmentDO = new UseOfEquipmentDO();
				useOfEquipmentDO.setId(useOfEquip.getId());
				useOfEquipmentDO.setEquipName(useOfEquip.getEquipName());
				
				quoteDO.setUseOfEquipmentDO(useOfEquipmentDO);
			}
			if(quote.getMachineSaleDate() != null){
				quoteDO.setEstSaleDate(new java.sql.Timestamp(quote.getMachineSaleDate().getTime()));
			}
			if(quote.getMachineSaleDate() != null){
				quoteDO.setEstSaleDateStr(dateFormat.format(quote.getMachineSaleDate()));
			}
			quoteDO.setOtherProv(quote.getOtherProv());
			quoteDO.setDealerMarkup(quote.getDealerMarkup());
			quoteDO.setDealerMarkupType(quote.getDealerMarkupType());
			quoteDO.setDeductiblePrice(quote.getDeductAmount());
			quoteDO.setCoverageTerm(quote.getCoverageTerm());
			quoteDO.setCoverageHours(quote.getCoverageLevelHours());
			quoteDO.setCoverageType(quote.getCoverageType());
			quoteDO.setQuoteBasePrice(quote.getCoveragePrice());
			quoteDO.setQuoteBasePriceToDisplay(quote.getCoveragePrice());
			if(adminAdjustment != null){
				if(adminAdjustment.getBasePrice() > 0){
					quoteDO.setQuoteBasePrice(adminAdjustment.getBasePrice());
				}
				
				if(adminAdjustment.getCoverageTerm() > 0){
					quoteDO.setAdjustedcoverageTerm(adminAdjustment.getCoverageTerm());
				}else{
					quoteDO.setAdjustedcoverageTerm(quote.getCoverageTerm());
				}
				
				if(adminAdjustment.getCoverageHours() > 0){
					quoteDO.setAdjustedCoverageHours(adminAdjustment.getCoverageHours());
				}else{
					quoteDO.setAdjustedCoverageHours(quote.getCoverageLevelHours());
				}
				
				if(adminAdjustment.getCoverageType() != null && !adminAdjustment.getCoverageType().isEmpty()){
					quoteDO.setAdjustedCoverageType(adminAdjustment.getCoverageType());
				}else{
					quoteDO.setAdjustedCoverageType(quote.getCoverageType());
				}
			}else{
				quoteDO.setAdjustedcoverageTerm(quote.getCoverageTerm());
				quoteDO.setAdjustedCoverageHours(quote.getCoverageLevelHours());
				quoteDO.setAdjustedCoverageType(quote.getCoverageType());
				quoteDO.setInceptionDate(new java.sql.Timestamp(new Date().getTime()));
				
				int coverageTerm = quote.getCoverageTerm();
				int coverageHours = quote.getCoverageLevelHours();
				
				if(quote.getManfExpired() == 1){
					Calendar cal = Calendar.getInstance();
					cal.setTime(new Date());
					cal.add(Calendar.MONTH, coverageTerm);
					quoteDO.setExpirationDate(new java.sql.Timestamp(cal.getTime().getTime()));
				}else{
					int manfCoverageTerm = 0;
					if(quote.getCoverageType() != null){
						if(quote.getCoverageType().equalsIgnoreCase("PT")){
							manfCoverageTerm = quote.getPtMonths();
						}else if(quote.getCoverageType().equalsIgnoreCase("PH")){
							manfCoverageTerm = quote.getHMonths();
						}else if(quote.getCoverageType().equalsIgnoreCase("PL")){
							manfCoverageTerm = quote.getMachineMonths();
						}
						int finalCoverageTerm = coverageTerm - manfCoverageTerm;
						if(quote.getManfEndDate() != null){
							Calendar cal = Calendar.getInstance();
							cal.setTime(quote.getManfEndDate());
							cal.add(Calendar.MONTH, finalCoverageTerm);
							quoteDO.setExpirationDate(new java.sql.Timestamp(cal.getTime().getTime()));
						}
					}
				}
				
				if(quote.getManfExpired() == 1){
					quoteDO.setExpirationHours(coverageHours+quote.getMachineMeterHours());
				}else{
					quoteDO.setExpirationHours(coverageHours);
				}
			}
			quoteDO.setStatus(quote.getStatus());
			quoteDO.setIsArchive(quote.getIsArchive());
			quoteDO.setLastUpdate(quote.getLastUpdate());
			String statusDesc = "";
			if(quote.getIsArchive() == AggConstants.B_QUOTE_STATUS_ACRHIVE){
				statusDesc = AggConstants.QUOTE_STATUS_ACRHIVE;
			}else if(quote.getStatus() == AggConstants.B_QUOTE_STATUS_ESTIMATING_PRICE){
				statusDesc = AggConstants.QUOTE_STATUS_ESTIMATING_PRICE;
			}else if(quote.getStatus() == AggConstants.B_QUOTE_STATUS_PURCHASE_REQUESTED){
				statusDesc = AggConstants.QUOTE_STATUS_PURCHASE_REQUESTED;
			}else if(quote.getStatus() == AggConstants.B_QUOTE_STATUS_INVOICED){
				statusDesc = AggConstants.QUOTE_STATUS_INVOICED;
			}else if(quote.getStatus() == AggConstants.B_QUOTE_STATUS_CLOSED){
				statusDesc = AggConstants.QUOTE_STATUS_CLOSED;
			}
			quoteDO.setStatusDesc(statusDesc);
			quoteDO.setStatus(quote.getStatus());
			
			String dealerMarkupType = quote.getDealerMarkupType();
			double coveragePrice = quote.getCoveragePrice();
			double dealerMarkupPrice = 0.0;
			if(dealerMarkupType != null && !dealerMarkupType.isEmpty()){
				if(dealerMarkupType.equalsIgnoreCase("price")){
					dealerMarkupPrice = quote.getDealerMarkup();
				}else{
					dealerMarkupPrice = (coveragePrice * quote.getDealerMarkup())/100.0;
				}
			}
			quoteDO.setDealerMarkupPrice(dealerMarkupPrice);
			
			CustomerInfo customerInfo = customerInfoDAO.findOne(quote.getId().getQuoteId());
			if(customerInfo != null){
				quoteDO.setDealerName(customerInfo.getName());
				quoteDO.setDealerAddress(customerInfo.getAddress());
				quoteDO.setDealerCity(customerInfo.getCity());
				quoteDO.setDealerEmail(customerInfo.getEmail());
				quoteDO.setDealerPhone(customerInfo.getPhone());
				quoteDO.setDealerState(customerInfo.getState());
				quoteDO.setDealerZip(customerInfo.getZip());
				quoteDO.setCustRemorsePeriod((customerInfo.getRemorse() == 1)?true:false);
				quoteDO.setCustUnderstandCoverage((customerInfo.getUnderstand() == 1)?true:false);
			}
			
			if(adminAdjustment != null){
				if(adminAdjustment.getBasePrice() > 0){
					quoteDO.setAdjustedBasePrice(adminAdjustment.getBasePrice());
				}else{
					quoteDO.setAdjustedBasePrice(quote.getCoveragePrice());
				}
				if(adminAdjustment.getLol() > 0){
					quoteDO.setAdjustedLol(adminAdjustment.getLol());
				}else{
					if(quoteDO.getMachineInfoDO() != null){
						quoteDO.setAdjustedLol(quoteDO.getMachineInfoDO().getLol());
					}
				}
				
				int coverageTerm = quote.getCoverageTerm();
				int coverageHours = quote.getCoverageLevelHours();
				
				
				if(adminAdjustment.getCoverageTerm() > 0){
					coverageTerm = adminAdjustment.getCoverageTerm();
				}
				
				if(adminAdjustment.getCoverageHours() > 0){
					coverageHours = adminAdjustment.getCoverageHours();
				}
				
				if(adminAdjustment.getInceptionDate() != null){
					quoteDO.setInceptionDate(new java.sql.Timestamp(adminAdjustment.getInceptionDate().getTime()));
				}else{
					quoteDO.setInceptionDate(new java.sql.Timestamp(new Date().getTime()));
				}
				
				if(adminAdjustment.getExpirationDate() != null){
					quoteDO.setExpirationDate(new java.sql.Timestamp(adminAdjustment.getExpirationDate().getTime()));
				}else{
					if(quote.getManfExpired() == 1){
						Calendar cal = Calendar.getInstance();
						if(adminAdjustment.getInceptionDate() != null){
							cal.setTime(adminAdjustment.getInceptionDate());
						}else{
							cal.setTime(new Date());
						}
						cal.add(Calendar.MONTH, coverageTerm);
						quoteDO.setExpirationDate(new java.sql.Timestamp(cal.getTime().getTime()));
					}else{
						int manfCoverageTerm = 0;
						if(quote.getCoverageType() != null){
							if(quote.getCoverageType().equalsIgnoreCase("PT")){
								manfCoverageTerm = quote.getPtMonths();
							}else if(quote.getCoverageType().equalsIgnoreCase("PH")){
								manfCoverageTerm = quote.getHMonths();
							}else if(quote.getCoverageType().equalsIgnoreCase("PL")){
								manfCoverageTerm = quote.getMachineMonths();
							}
							int finalCoverageTerm = coverageTerm - manfCoverageTerm;
							if(quote.getManfEndDate() != null){
								Calendar cal = Calendar.getInstance();
								cal.setTime(quote.getManfEndDate());
								cal.add(Calendar.MONTH, finalCoverageTerm);
								quoteDO.setExpirationDate(new java.sql.Timestamp(cal.getTime().getTime()));
							}
						}
					}
				}
				
				if(adminAdjustment.getExpirationHours() > 0){
					quoteDO.setExpirationHours(adminAdjustment.getExpirationHours());
				}else{
					if(quote.getManfExpired() == 1){
						quoteDO.setExpirationHours(coverageHours+quote.getMachineMeterHours());
					}else{
						quoteDO.setExpirationHours(coverageHours);
					}
				}
				
				quoteDO.setSpecialConsiderations(adminAdjustment.getSpecialConsideration());
				quoteDO.setCondsForCoverage(adminAdjustment.getCConditions());
				quoteDO.setDealHistory(adminAdjustment.getDealHistory());
			}
		}
		
		return quoteDO;
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
		//List<Contracts> contracts = null;
		List<ContractDO> contractDOList = null;
		if(accountDO.getRoleDO().getAccountType().equalsIgnoreCase(AggConstants.ACCOUNT_TYPE_ADMIN)){
			//contracts = Util.toList(contractDAO.findActive());
			contractDOList = contractDAO.findContractsByStatus(AggConstants.B_ACTIVE_CONTRACT);
		}else{
			//contracts = Util.toList(contractDAO.findActiveByDealerId(accountDO.getDealerId()));
			contractDOList = contractDAO.findContractsByStatusAndDelaerId(AggConstants.B_ACTIVE_CONTRACT, accountDO.getDealerId());
		}
		//return formatEntityToDO(contracts);
		
		return contractDOList;
	}
	
	@Override
	public List<ContractDO> getActiveContractDetails(AccountDO accountDO) {
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
		//List<Contracts> contracts = null;
		List<ContractDO> contractDOList = null;
		if(accountDO.getRoleDO().getAccountType().equalsIgnoreCase(AggConstants.ACCOUNT_TYPE_ADMIN)){
			//contracts = Util.toList(contractDAO.findInactive());
			contractDOList = contractDAO.findContractsByStatus(AggConstants.B_INACTIVE_CONTRACT);
		}else{
			//contracts = Util.toList(contractDAO.findInactiveByDealerId(accountDO.getDealerId()));
			contractDOList = contractDAO.findContractsByStatusAndDelaerId(AggConstants.B_INACTIVE_CONTRACT, accountDO.getDealerId());
		}
		//return formatEntityToDO(contracts);
		return contractDOList;
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
	public boolean updateContract(ContractDO contractDO, AccountDO accountDO) {
		List<Contracts> contractsList = contractDAO.findByIdAndContractId(contractDO.getId(), contractDO.getContractId());
		boolean cond = false;
		if(contractsList != null && !contractsList.isEmpty()){
			Date currDate = new Date();
			Contracts contracts = contractsList.get(0);
			contracts.setAvailabeLol(contractDO.getAvailabeLol());
			contracts.setInceptionDate(contractDO.getInceptionDate());
			contracts.setExpirationDate(contractDO.getExpirationDate());
			contracts.setComments(contractDO.getComments());
			contracts.setStatus(contractDO.getStatus());
			contracts.setLastUpdatedDate(currDate);
			contracts.setCheqNo(contractDO.getCheqNo());
			contracts.setReceivedDate(contractDO.getReceivedDate());
			contracts.setMachineSerialNo(contractDO.getMachineSerialNo());
			contracts.setCoverageType(contractDO.getCoverageType());
			contracts.setCoverageTermMonths(contractDO.getCoverageTermMonths());
			contracts.setCoverageLevelHours(contractDO.getCoverageLevelHours());
			contracts.setDeductible(contractDO.getDeductible());
			
			LOGGER.debug("InceptionDate: "+contractDO.getInceptionDate()+" ExpirationDate: "+contractDO.getExpirationDate()+" ReceivedDate: "+contractDO.getReceivedDate());
			
			List<CheckDO> checkDOList = contractDO.getCheckDOList();
			if(checkDOList != null && !checkDOList.isEmpty()){
				Set<Check> checks = new HashSet<Check>();
				Check check = null;
				for(CheckDO checkDO : checkDOList){
					if(checkDO.getId() > 0){
						check = checkDAO.findOne(checkDO.getId());
					}else{
						check = new Check();
						check.setCreatedBy(accountDO.getUsername());
						check.setCreatedDate(currDate);
					}
					
					check.setCheckNo(checkDO.getCheckNo());
					check.setReceivedDate(checkDO.getReceivedDate());
					check.setCheckAmount(checkDO.getAmount());
					check.setUpdatedBy(accountDO.getUsername());
					check.setUpdatedDate(currDate);
					
					checks.add(check);
				}
				
				contracts.setChecks(checks);
			}
			
			CustomerInfo customerInfo = customerInfoDAO.findOne(contractDO.getQuoteDO().getQuoteId());
			if(customerInfo != null){
				customerInfo.setName(contractDO.getQuoteDO().getDealerName());
				customerInfo.setAddress(contractDO.getQuoteDO().getDealerAddress());
				customerInfo.setState(contractDO.getQuoteDO().getDealerState());
				customerInfo.setPhone(contractDO.getQuoteDO().getDealerPhone());
				customerInfo.setCity(contractDO.getQuoteDO().getDealerCity());
				customerInfo.setZip(contractDO.getQuoteDO().getDealerZip());
				customerInfo.setEmail(contractDO.getQuoteDO().getDealerEmail());
				
				customerInfoDAO.save(customerInfo);
			}
			
			AdminAdjustment adjustment = adminAdjustmentDAO.findOne(contractDO.getQuoteDO().getQuoteId());
			if(adjustment != null){
				adjustment.setBasePrice(contractDO.getQuoteDO().getAdjustedBasePrice());
				
				adminAdjustmentDAO.save(adjustment);
			}
			
			
			contractDAO.save(contracts);
			
			cond = true;
		}
		return cond;
	}

	@Override
	public int getContractsCount(String contractId) {
		return contractDAO.getContractsCount(contractId);
	}

	@Override
	public ContractReportDO getContractReportDetails(long id, String contractId) {
		List<Contracts> contractsList = contractDAO.findByIdAndContractId(id, contractId);
		ContractReportDO contractReportDO = null;
		if(contractsList != null && !contractsList.isEmpty()){
			Contracts contract = contractsList.get(0);
			contractReportDO = new ContractReportDO();
			Quote quote = quoteDAO.findOne(new Long(contract.getQuoteId()).intValue());
			CustomerInfo customerInfo = customerInfoDAO.findOne(quote.getId().getQuoteId());
			AdminAdjustment adminAdjustment = adminAdjustmentDAO.findOne(quote.getId().getQuoteId());
			
			Locale locale = new Locale("en", "US");
			NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(locale);
			
			contractReportDO.setContractId(contract.getContractId());
			contractReportDO.setInceptionDate((contract.getInceptionDate() != null)?dateFormat.format(contract.getInceptionDate()):"");
			contractReportDO.setCoverageTerm(contract.getCoverageTermMonths());
			contractReportDO.setCoverageHours(contract.getCoverageLevelHours());
			contractReportDO.setExpirationDate((contract.getExpirationDate() != null)?dateFormat.format(contract.getExpirationDate()):"");
			contractReportDO.setDeductibleAmount(currencyFormat.format(contract.getDeductible()));
			contractReportDO.setExpirationHours(contract.getExpirationUsageHours()+"");
			String coverageType = contract.getCoverageType();
			if(coverageType != null && !coverageType.isEmpty()){
				if(coverageType.equalsIgnoreCase("PT")){
					contractReportDO.setCoverageType("Powertrain");
				}else if(coverageType.equalsIgnoreCase("PH")){
					contractReportDO.setCoverageType("Powertrain + Hydraulic");
				}else if(coverageType.equalsIgnoreCase("PL")){
					contractReportDO.setCoverageType("Powertrain + Hydraulic + Platform");
				}
			}
			//contractReportDO.setCoverageType(contract.getCoverageType());
			contractReportDO.setLol(currencyFormat.format(contract.getLol()));
			contractReportDO.setManufacturer(quote.getManufacturer().getManfName());
			contractReportDO.setSerialNo(contract.getMachineSerialNo());
			contractReportDO.setMachineModel(quote.getMachineInfo().getModel());
			contractReportDO.setManfEndDate((quote.getManfEndDate() != null)?dateFormat.format(quote.getManfEndDate()):"");
			contractReportDO.setUseOfEquipment((quote.getUseOfEquip() != null)?quote.getUseOfEquip().getEquipName():"");
			contractReportDO.setMachineHours(quote.getMachineMeterHours());
			contractReportDO.setCustomerAddress1(customerInfo.getAddress());
			contractReportDO.setCustomerAddress2(customerInfo.getCity()+" "+customerInfo.getState());
			contractReportDO.setCustomerAddress3(customerInfo.getZip());
			contractReportDO.setCustomerContact(customerInfo.getName());
			contractReportDO.setCustomerPhone(customerInfo.getPhone());
			contractReportDO.setCustomerEmail(customerInfo.getEmail());
			contractReportDO.setDealerAddress1(quote.getDealer().getAddress());
			contractReportDO.setDealerAddress2(quote.getDealer().getAddress2());
			contractReportDO.setDealerAddress3(quote.getDealer().getCity()+" "+quote.getDealer().getState()+" "+quote.getDealer().getZip());
			contractReportDO.setDealerContact(quote.getDealer().getName());
			contractReportDO.setDealerEmail(quote.getDealer().getInvoiceEmail());
			contractReportDO.setDealerPhone(quote.getDealer().getPhone());
			contractReportDO.setServiceProviderAddr1(quote.getDealer().getAddress());
			contractReportDO.setServiceProviderAddr2(quote.getDealer().getAddress2());
			contractReportDO.setServiceProviderAddr3(quote.getDealer().getCity()+" "+quote.getDealer().getState()+" "+quote.getDealer().getZip());
			contractReportDO.setServiceProviderContact(quote.getDealer().getName());
			contractReportDO.setServiceProviderEmail(quote.getDealer().getInvoiceEmail());
			contractReportDO.setServiceProviderPhone(quote.getDealer().getPhone());
			contractReportDO.setSpecialConsiderations(adminAdjustment.getSpecialConsideration());
		}
		
		return contractReportDO;
	}
	
}
