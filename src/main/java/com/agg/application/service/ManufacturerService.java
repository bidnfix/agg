package com.agg.application.service;

import java.util.List;

import com.agg.application.model.ManufacturerDO;

public interface ManufacturerService {
	
	public List<ManufacturerDO> getManufacturers();
	
	public ManufacturerDO getManufacturer(long id);
	
	public long saveManufacturer(ManufacturerDO manufacturerDO) throws Exception;
	
	public long editManufacturer(ManufacturerDO manufacturerDO) throws Exception;
}
