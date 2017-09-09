package com.agg.application.model;

public class MachineDO {
	
	private ManufacturerDO manufacturerDO;
	
	private MachineTypeDO machineTypeDO;
	
	//private MachineModelDO machineModelDO;
	
	private String model;
	
    private double enginePower;
	
	private GroupDO groupDO;
	
	private long machineId;
	
	private long groupId;
	
	private String manfName;
	
	private String machineType;
	
	private double adjFactor;
	
	public MachineDO(){
		
	}
	
	public MachineDO(long machineId, String manfName, String machineType, String model, double ePower, double adjFactor, long groupId){
		this.machineId = machineId;
		this.manfName = manfName;
		this.machineType = machineType;
		this.model = model;
		this.enginePower = ePower;
		this.adjFactor = adjFactor;
		this.groupId = new Long(groupId).intValue();
	}
	

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

	public GroupDO getGroupDO() {
		return groupDO;
	}

	public void setGroupDO(GroupDO groupDO) {
		this.groupDO = groupDO;
	}

	public long getGroupId() {
		return groupId;
	}

	public void setGroupId(long groupId) {
		this.groupId = groupId;
	}

	/**
	 * @return the manfName
	 */
	public String getManfName() {
		return manfName;
	}

	/**
	 * @param manfName the manfName to set
	 */
	public void setManfName(String manfName) {
		this.manfName = manfName;
	}

	/**
	 * @return the machineType
	 */
	public String getMachineType() {
		return machineType;
	}

	/**
	 * @param machineType the machineType to set
	 */
	public void setMachineType(String machineType) {
		this.machineType = machineType;
	}

	public double getAdjFactor() {
		return adjFactor;
	}

	public void setAdjFactor(double adjFactor) {
		this.adjFactor = adjFactor;
	}
	
	
}
