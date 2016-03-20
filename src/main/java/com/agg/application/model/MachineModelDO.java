package com.agg.application.model;

import java.sql.Timestamp;
import java.util.Date;

public class MachineModelDO {
	
	private int modelId;
	
	private String modelName;
	
	private Date modelYear;
	
	private double basePrice;

	private double ePower;

	private int groupId;

	private Timestamp lastUpdate;

	private String model;

	private double power;

	private double retailPrice;

	private int machineTypeId;

	public int getModelId() {
		return modelId;
	}

	public void setModelId(int modelId) {
		this.modelId = modelId;
	}

	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	public Date getModelYear() {
		return modelYear;
	}

	public void setModelYear(Date modelYear) {
		this.modelYear = modelYear;
	}

	public double getBasePrice() {
		return basePrice;
	}

	public void setBasePrice(double basePrice) {
		this.basePrice = basePrice;
	}

	public double getePower() {
		return ePower;
	}

	public void setePower(double ePower) {
		this.ePower = ePower;
	}

	public int getGroupId() {
		return groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	public Timestamp getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public double getPower() {
		return power;
	}

	public void setPower(double power) {
		this.power = power;
	}

	public double getRetailPrice() {
		return retailPrice;
	}

	public void setRetailPrice(double retailPrice) {
		this.retailPrice = retailPrice;
	}

	public int getMachineTypeId() {
		return machineTypeId;
	}

	public void setMachineTypeId(int machineTypeId) {
		this.machineTypeId = machineTypeId;
	}


}
