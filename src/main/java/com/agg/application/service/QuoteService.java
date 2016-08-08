package com.agg.application.service;

import java.util.List;
import java.util.Map;

import com.agg.application.model.PricingDO;
import com.agg.application.model.QuoteDO;
import com.agg.application.model.UseOfEquipmentDO;

public interface QuoteService {

	public List<UseOfEquipmentDO> getUseOfEquipmentDetails();

	public Map<String, List<Integer>> getDeductableCoverageTermDetails(boolean coverageExpired, long machineId);

	public List<PricingDO> getCoveragePriceDetils(boolean coverageExpired, long machineId, int deductibleAmt, int coverageTerm);
	
	public void saveWarrantyInfo(QuoteDO quoteDO);
	
	public void saveMachineInfo(QuoteDO quoteDO);
}
