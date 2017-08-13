package com.agg.application.service;

import java.util.List;

import com.agg.application.model.UsageTierDO;

public interface UsageTierService {
	
	public List<UsageTierDO> getUsageTiers();
	
	public UsageTierDO getUsageTier(long id);
	
	public long saveUsageTier(UsageTierDO usageTierDO) throws Exception;
	
	public long editUsageTier(UsageTierDO usageTierDO) throws Exception;

	public double getUsageTierAdjFactor(long meterHours);
	
	//public UseOfEquipmentDO getUseOfEquip(long id);
	
	//public long editEquipment(UseOfEquipmentDO equipmentDO) throws Exception;
}
