package com.agg.application.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agg.application.dao.MachineDAO;
import com.agg.application.entity.Manufacturer;
import com.agg.application.model.ManufacturerDO;
import com.agg.application.service.MachineService;

@Service
public class MachineServiceImpl implements MachineService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private MachineDAO machineDAO;

	@Override
	public List<ManufacturerDO> getManufacturerDetails() {
		logger.debug(machineDAO.findAll().toString());
		Iterable<Manufacturer>  manufacturerIterator =  machineDAO.findAll();
		List<ManufacturerDO> manufacturerDOList = null;
		if(manufacturerIterator != null && manufacturerIterator.iterator() != null){
			manufacturerDOList = new ArrayList<ManufacturerDO>();
			ManufacturerDO manufacturerDO = null;
			Manufacturer manufacturer = null;
			while(manufacturerIterator.iterator().hasNext()){
				manufacturerDO = new ManufacturerDO();
				manufacturer = manufacturerIterator.iterator().next();
				manufacturerDO.setId(manufacturer.getManfId());
				manufacturerDO.setName(manufacturer.getManfName());
				manufacturerDOList.add(manufacturerDO);
				
			}
		}
		
		return manufacturerDOList;
	}

	

}
