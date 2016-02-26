package com.agg.application.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agg.application.dao.MachineDAO;
import com.agg.application.entity.Manufacturer;
import com.agg.application.service.MachineService;

@Service
public class MachineServiceImpl implements MachineService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private MachineDAO machineDAO;

	@Override
	public Iterable<Manufacturer> getManufacturerDetails() {
		logger.debug(machineDAO.findAll().toString());
		return machineDAO.findAll();
	}

	

}
