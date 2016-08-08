package com.agg.application.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agg.application.dao.DealerDAO;
import com.agg.application.dao.MachineInfoDAO;
import com.agg.application.dao.ManufacturerDAO;
import com.agg.application.dao.PricingDAO;
import com.agg.application.dao.QuoteDAO;
import com.agg.application.dao.UseOfEquipmentDAO;
import com.agg.application.entity.MachineInfo;
import com.agg.application.entity.Manufacturer;
import com.agg.application.entity.Quote;
import com.agg.application.entity.QuotePK;
import com.agg.application.entity.UseOfEquip;
import com.agg.application.model.PricingDO;
import com.agg.application.model.QuoteDO;
import com.agg.application.model.UseOfEquipmentDO;
import com.agg.application.service.QuoteService;
import com.agg.application.utils.AggConstants;
import com.agg.application.utils.Util;

@Service
public class QuoteServiceImpl implements QuoteService {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private UseOfEquipmentDAO useOfEquipmentDAO;
	
	@Autowired
	private MachineInfoDAO machineInfoDAO;
	
	@Autowired
	private PricingDAO pricingDAO;
	
	@Autowired
	private QuoteDAO quoteDAO;
	
	@Autowired
	private DealerDAO dealerDAO;
	
	@Autowired
	private ManufacturerDAO manufacturerDAO;

	@Override
	public List<UseOfEquipmentDO> getUseOfEquipmentDetails() {
		List<UseOfEquip> useOfEquipList = Util.toList(useOfEquipmentDAO.findAll());
		UseOfEquipmentDO useOfEquipmentDO = null;
		List<UseOfEquipmentDO> useOfEquipmentDOList = new ArrayList<UseOfEquipmentDO>();
		for(UseOfEquip useOfEquip : useOfEquipList){
			useOfEquipmentDO = new UseOfEquipmentDO();
			useOfEquipmentDO.setId(useOfEquip.getId());
			useOfEquipmentDO.setEquipName(useOfEquip.getEquipName());
			
			useOfEquipmentDOList.add(useOfEquipmentDO);
		}
		
		logger.info("useOfEquipmentDOList size: "+useOfEquipmentDOList.size());
		
		return useOfEquipmentDOList;
	}

	@Override
	public Map<String, List<Integer>> getDeductableCoverageTermDetails(boolean coverageExpired, long machineId) {
		MachineInfo machineInfo = machineInfoDAO.findOne(machineId);
		Map<String, List<Integer>> deductableCoverageTermMap = null;
		if(machineInfo != null){
			deductableCoverageTermMap = new HashMap<String, List<Integer>>();
			int groupId = new Long(machineInfo.getGroupConstant().getGroupId()).intValue();
			logger.info("groupId: "+groupId);
			//if manufacturers coverage date expires then consider as used(2) otherwise new(1);
			byte condition = coverageExpired ? (byte)1 : (byte)2;
			List<Integer> deductibleAmtList = pricingDAO.findDeductibleAmount(condition, groupId);
			List<Integer> coverageTermList = pricingDAO.findCoverageTerm(condition, groupId, deductibleAmtList);
			
			logger.info("deductibleAmtList size: "+deductibleAmtList.size()+" coverageTermList size: "+coverageTermList.size());
			
			deductableCoverageTermMap.put("deductibleAmtList", deductibleAmtList);
			deductableCoverageTermMap.put("coverageTermList", coverageTermList);
		}
		
		return deductableCoverageTermMap;
	}

	@Override
	public List<PricingDO> getCoveragePriceDetils(boolean coverageExpired, long machineId, int deductibleAmt, int coverageTerm) {
		MachineInfo machineInfo = machineInfoDAO.findOne(machineId);
		List<PricingDO> pricingDOList = null;
		if(machineInfo != null){
			int groupId = new Long(machineInfo.getGroupConstant().getGroupId()).intValue();
			logger.info("groupId: "+groupId);
			//if manufacturers coverage date expires then consider as used(2) otherwise new(1);
			byte condition = coverageExpired ? (byte)1 : (byte)2;
			
			pricingDOList = pricingDAO.findCoverageLevelPricing(condition, groupId, deductibleAmt, coverageTerm);
			logger.info("pricingDOList size: "+pricingDOList.size());
		}
		
		return pricingDOList;
	}

	@Override
	public void saveWarrantyInfo(QuoteDO quoteDO) {
		Quote quote = null;
		if(quoteDO.getId() != 0 && quoteDO.getId() > 0 && quoteDO.getQuoteId() != null && !quoteDO.getQuoteId().isEmpty()){
			QuotePK quotePK = new QuotePK(quoteDO.getQuoteId(), quoteDO.getId());
			quote = quoteDAO.findOne(quotePK);
		}else if(quoteDO.getQuoteId() != null && !quoteDO.getQuoteId().isEmpty()){
			quote = quoteDAO.findByIdQuoteId(quoteDO.getQuoteId());
			if(quote == null){
				quote = new Quote();
				QuotePK quotePK = new QuotePK();
				quotePK.setQuoteId(quoteDO.getQuoteId());
				
				quote.setId(quotePK);
				quote.setStatus((byte)1);
			}
		}else{
			quote = new Quote();
			String quoteId = Util.getQuoteId(AggConstants.CHARSET_AZ_09, AggConstants.QUOTE_ID_LENGTH);
			logger.info("created quoteId: "+quoteId);
			QuotePK quotePK = new QuotePK();
			quotePK.setQuoteId(quoteId);
			
			quote.setId(quotePK);
			quote.setStatus((byte)1);
		}
		quote.setDealer(dealerDAO.findOne(quoteDO.getDealerDO().getId()));
		quote.setManfExpired((quoteDO.isCoverageExpired())?(byte)1:(byte)0);
		quote.setManfEndDate(quoteDO.getCoverageEndDate());
		quote.setManfEndKnown((quoteDO.isCoverageEndDateUnknown())?(byte)1:(byte)0);
		quote.setManfVerified((quoteDO.isCoverageEndDateVerified())?(byte)1:(byte)0);
		quote.setPtHours(quoteDO.getPowerTrainHours());
		quote.setPtMonths(quoteDO.getPowerTrainMonths());
		quote.setHHours(quoteDO.getHydraulicsHours());
		quote.setHMonths(quoteDO.getHydraulicsMonths());
		quote.setMachineHours(quoteDO.getFullMachineHours());
		quote.setMachineMonths(quoteDO.getFullMachineMonths());
		quote.setOtherProv("");
		
		/*quote.setManfName("");
		quote.setMachineModel("");
		quote.setMachineModelNum("");
		quote.setGroupId(0);
		quote.setMachinePower(0);
		quote.setMachineSerial("");
		quote.setMachineRetailPrice(0.0);
		quote.setMachineMeterHours(0);
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, 0);
		quote.setMachineYear(cal.getTime());
		quote.setDealerMarkupType("");
		quote.setDealerMarkup(0.0);
		quote.setDeductAmount(0.0);
		quote.setCoverageTerm(0);
		quote.setCoverageLevelHours(0);
		quote.setCPtHours(0);
		quote.setCPtHHours(0);
		quote.setCPtHPlHours(0);
		
		quote.setMachineSaleDate(new Date());
		quote.setMachineUoe("");*/
		
/*		if(quoteDO.getManufacturerDO() != null){
			quote.setManufacturer(manufacturerDAO.findOne(quoteDO.getManufacturerDO().getId()));
			quote.setManfName(quoteDO.getManufacturerDO().getName());
		}
		if(quoteDO.getMachineInfoDO() != null){
			quote.setMachineModel(quoteDO.getMachineInfoDO().getModel());
			quote.setMachineModelNum(quoteDO.getMachineInfoDO().getModel());
			quote.setGroupId(quoteDO.getMachineInfoDO().getGroupId());
		}
		quote.setMachinePower(quoteDO.getHorsePower());
		quote.setMachineSerial(quoteDO.getSerialNumber());
		quote.setMachineRetailPrice(quoteDO.getRetailPrice());
		quote.setMachineMeterHours(quoteDO.getMeterHours());
		if(quoteDO.getModelYear() > 0){
			Calendar cal = Calendar.getInstance();
			cal.set(Calendar.YEAR, quoteDO.getModelYear());
			quote.setMachineYear(cal.getTime());
		}
		if(quoteDO.getUseOfEquipmentDO() != null){
			quote.setMachineUoe(quoteDO.getUseOfEquipmentDO().getEquipName());
		}
		quote.setMachineSaleDate(quoteDO.getEstSaleDate());
		quote.setDealerMarkupType(quoteDO.getDealerMarkupType());
		quote.setDealerMarkup(quoteDO.getDealerMarkup());
		quote.setDeductAmount(quoteDO.getDeductiblePrice());
		quote.setCoverageTerm(quoteDO.getCoverageTerm());
		quote.setCoverageLevelHours(quoteDO.getCoverageLevelHours());
		quote.setCPtHours(quoteDO.getCPtHours());
		quote.setCPtHHours(quoteDO.getCPtHHours());
		quote.setCPtHPlHours(quoteDO.getCPtHPlHours());*/
		quote.setIsArchive((short)0);
		quote.setCreateDate(new Date());
		quote.setPrId(0);
		quote.setServicingDealer(0);
		quote.setLastUpdate(new Date());
		
		quote = quoteDAO.save(quote);
		
		if(quote != null && quote.getId() != null){
			logger.info("quoteId: "+quote.getId().getQuoteId()+" and id: "+quote.getId().getId());
			quoteDO.setQuoteId(quote.getId().getQuoteId());
			quoteDO.setId(quote.getId().getId());
			if(quote.getStatus() == 1){
				quoteDO.setStatusDesc(AggConstants.QUOTE_STATUS_ESTIMATING_PRICE);
			}else if(quote.getStatus() == 4){
				quoteDO.setStatusDesc(AggConstants.QUOTE_STATUS_PURCHASE_REQUESTED);
			}else if(quote.getStatus() == 5){
				quoteDO.setStatusDesc(AggConstants.QUOTE_STATUS_INVOICED);
			}
		}
	}
	
	@Override
	public void saveMachineInfo(QuoteDO quoteDO) {
		Quote quote = null;
		if(quoteDO.getId() != 0 && quoteDO.getId() > 0 && quoteDO.getQuoteId() != null && !quoteDO.getQuoteId().isEmpty()){
			QuotePK quotePK = new QuotePK(quoteDO.getQuoteId(), quoteDO.getId());
			quote = quoteDAO.findOne(quotePK);
		}else if(quoteDO.getQuoteId() != null && !quoteDO.getQuoteId().isEmpty()){
			quote = quoteDAO.findByIdQuoteId(quoteDO.getQuoteId());
			if(quote == null){
				quote = new Quote();
				QuotePK quotePK = new QuotePK();
				quotePK.setQuoteId(quoteDO.getQuoteId());
				
				quote.setId(quotePK);
				quote.setStatus((byte)1);
			}
		}
		if(quote != null){
			quote.setDealer(dealerDAO.findOne(quoteDO.getDealerDO().getId()));
			quote.setManfExpired((quoteDO.isCoverageExpired())?(byte)1:(byte)0);
			quote.setManfEndDate(quoteDO.getCoverageEndDate());
			quote.setManfEndKnown((quoteDO.isCoverageEndDateUnknown())?(byte)1:(byte)0);
			quote.setManfVerified((quoteDO.isCoverageEndDateVerified())?(byte)1:(byte)0);
			quote.setPtHours(quoteDO.getPowerTrainHours());
			quote.setPtMonths(quoteDO.getPowerTrainMonths());
			quote.setHHours(quoteDO.getHydraulicsHours());
			quote.setHMonths(quoteDO.getHydraulicsMonths());
			quote.setMachineHours(quoteDO.getFullMachineHours());
			quote.setMachineMonths(quoteDO.getFullMachineMonths());
			quote.setOtherProv("");
			
			Manufacturer manufacturer = manufacturerDAO.findOne(quoteDO.getManufacturerDO().getId());
			if(manufacturer != null){
				quote.setManufacturer(manufacturer);
				quote.setManfName(manufacturer.getManfName());
			}
			
			MachineInfo machineInfo = machineInfoDAO.findOne(quoteDO.getMachineInfoDO().getMachineId());
			if(machineInfo != null){
				quote.setMachineModel(machineInfo.getModel());
				quote.setMachineInfo(machineInfo);
				quote.setGroupId(new Long(machineInfo.getGroupConstant().getGroupId()).intValue());
			}
			
			quote.setMachinePower(quoteDO.getHorsePower());
			quote.setMachineSerial(quoteDO.getSerialNumber());
			quote.setMachineRetailPrice(quoteDO.getRetailPrice());
			quote.setMachineMeterHours(quoteDO.getMeterHours());
			quote.setMachineYear(quoteDO.getModelYear());
			/*if(quoteDO.getModelYear() > 0){
				Calendar cal = Calendar.getInstance();
				cal.set(Calendar.YEAR, quoteDO.getModelYear());
				quote.setMachineYear(cal.getTime());
			}*/
			quote.setUseOfEquip(useOfEquipmentDAO.findOne(quoteDO.getUseOfEquipmentDO().getId()));
			quote.setMachineSaleDate(quoteDO.getEstSaleDate());
			quote.setIsArchive((short)0);
			quote.setCreateDate(new Date());
			quote.setPrId(0);
			quote.setServicingDealer(0);
			quote.setLastUpdate(new Date());
			
			quote = quoteDAO.save(quote);
			
			if(quote != null && quote.getId() != null){
				logger.info("quoteId: "+quote.getId().getQuoteId()+" and id: "+quote.getId().getId());
				quoteDO.setQuoteId(quote.getId().getQuoteId());
				quoteDO.setId(quote.getId().getId());
				if(quote.getStatus() == 1){
					quoteDO.setStatusDesc(AggConstants.QUOTE_STATUS_ESTIMATING_PRICE);
				}else if(quote.getStatus() == 4){
					quoteDO.setStatusDesc(AggConstants.QUOTE_STATUS_PURCHASE_REQUESTED);
				}else if(quote.getStatus() == 5){
					quoteDO.setStatusDesc(AggConstants.QUOTE_STATUS_INVOICED);
				}
			}
		
		}
	}

}

