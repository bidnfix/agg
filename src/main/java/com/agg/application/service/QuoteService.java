package com.agg.application.service;

import java.util.List;
import java.util.Map;

import com.agg.application.model.UseOfEquipmentDO;

public interface QuoteService {

	public List<UseOfEquipmentDO> getUseOfEquipmentDetails();

	public Map<String, List<Long>> getDeductableCoverageTermDetails(boolean coverageExpired, long machineId);
}
