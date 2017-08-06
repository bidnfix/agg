package com.agg.application.model;

import java.util.List;

import com.agg.application.entity.MachineInfo;

public class ManufacturerDO {
	
	private long id;
	
	private String name;
	
	private double adjFactor;
	
	private List<MachineInfo> machineInfo;
	
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

	public List<MachineInfo> getMachineInfo() {
		return machineInfo;
	}

	public void setMachineInfo(List<MachineInfo> machineInfo) {
		this.machineInfo = machineInfo;
	}
	
}
