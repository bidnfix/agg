package com.agg.application.model;

public class MachineDO {
	
	private ManufacturerDO manufacturerDO;
	
	private ManufacturerDO machineTypeDO;
	
	private MachineModelDO machineModelDO;
	
	private double enginePower;
	
	private int groupId;

	public ManufacturerDO getManufacturerDO() {
		return manufacturerDO;
	}

	public void setManufacturerDO(ManufacturerDO manufacturerDO) {
		this.manufacturerDO = manufacturerDO;
	}

	public ManufacturerDO getMachineTypeDO() {
		return machineTypeDO;
	}

	public void setMachineTypeDO(ManufacturerDO machineTypeDO) {
		this.machineTypeDO = machineTypeDO;
	}

	public MachineModelDO getMachineModelDO() {
		return machineModelDO;
	}

	public void setMachineModelDO(MachineModelDO machineModelDO) {
		this.machineModelDO = machineModelDO;
	}

	public double getEnginePower() {
		return enginePower;
	}

	public void setEnginePower(double enginePower) {
		this.enginePower = enginePower;
	}

	public int getGroupId() {
		return groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}
	
	
}
