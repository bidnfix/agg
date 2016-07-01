package com.agg.application.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agg.application.dao.MachineInfoDAO;
import com.agg.application.dao.PricingDAO;
import com.agg.application.dao.UseOfEquipmentDAO;
import com.agg.application.entity.MachineInfo;
import com.agg.application.entity.UseOfEquip;
import com.agg.application.model.PricingDO;
import com.agg.application.model.UseOfEquipmentDO;
import com.agg.application.service.QuoteService;
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


}

