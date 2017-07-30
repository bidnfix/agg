package com.agg.application.service;

import java.util.List;

import com.agg.application.model.UsageTierDO;

public interface UsageTierService {
	
	public List<UsageTierDO> getUsageTier();
	
	public long saveUsageTier(UsageTierDO usageTierDO) throws Exception;
	
	//public UseOfEquipmentDO getUseOfEquip(long id);
	
	//public long editEquipment(UseOfEquipmentDO equipmentDO) throws Exception;
}
