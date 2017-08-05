package com.agg.application.service;

import java.util.List;

import com.agg.application.model.OEMWarrantyTierDO;

public interface OEMWarrantyTierService {
	
	public List<OEMWarrantyTierDO> getOEMWarrantyTiers();
	
	public OEMWarrantyTierDO getOEMWarrantyTier(long id);
	
	public long saveOEMWarrantyTier(OEMWarrantyTierDO oemWarrantyTierDO) throws Exception;
	
	public long editOEMWarrantyTier(OEMWarrantyTierDO oemWarrantyTierDO) throws Exception;
}
