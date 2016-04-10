package com.agg.application.model;

public class MachineDO {
	
	private ManufacturerDO manufacturerDO;
	
	private MachineTypeDO machineTypeDO;
	
	//private MachineModelDO machineModelDO;
	
	private String model;
	
    private double enginePower;
	
	private int groupId;
	
	private long machineId;
	
	

	public long getMachineId() {
		return machineId;
	}

	public void setMachineId(long machineId) {
		this.machineId = machineId;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public ManufacturerDO getManufacturerDO() {
		return manufacturerDO;
	}

	public void setManufacturerDO(ManufacturerDO manufacturerDO) {
		this.manufacturerDO = manufacturerDO;
	}

	public MachineTypeDO getMachineTypeDO() {
		return machineTypeDO;
	}

	public void setMachineTypeDO(MachineTypeDO machineTypeDO) {
		this.machineTypeDO = machineTypeDO;
	}

	/*public MachineModelDO getMachineModelDO() {
		return machineModelDO;
	}

	public void setMachineModelDO(MachineModelDO machineModelDO) {
		this.machineModelDO = machineModelDO;
	}*/

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
