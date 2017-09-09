package com.agg.application.model;

import java.util.List;

import com.agg.application.entity.MachineInfo;

public class ManufacturerDO {
	
	private long id;
	
	private String name;
	
	private double adjFactor;
	
	private List<MachineInfoDO> machineInfoDO;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getAdjFactor() {
		return adjFactor;
	}

	public void setAdjFactor(double adjFactor) {
		this.adjFactor = adjFactor;
	}

	public List<MachineInfoDO> getMachineInfoDO() {
		return machineInfoDO;
	}

	public void setMachineInfoDO(List<MachineInfoDO> machineInfoDO) {
		this.machineInfoDO = machineInfoDO;
	}
	
}
