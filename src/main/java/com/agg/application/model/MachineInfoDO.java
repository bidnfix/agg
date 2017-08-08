package com.agg.application.model;

import java.sql.Timestamp;

public class MachineInfoDO {
	
		private long machineId;
		
		private ManufacturerDO manufacturerDO;

		private double basePrice;

		private double ePower;

		private int groupId;

		private Timestamp lastUpdate;

		private String machineType;

		private String model;

		private int modelYear;

		private double power;

		private double retailPrice;
		
		private double lol;
		
		private double lolToDisplay;
		
		private String manfName;
		
		private double adjFactor;
		
		public MachineInfoDO(){
			
		}
		
		public MachineInfoDO(long machineId, String manfName, String machineType, String model, double ePower, long groupId){
			this.machineId = machineId;
			this.manfName = manfName;
			this.machineType = machineType;
			this.model = model;
			this.ePower = ePower;
			this.groupId = new Long(groupId).intValue();
		}
		
		public String getMachineType() {
			return machineType;
		}

		public void setMachineType(String machineType) {
			this.machineType = machineType;
		}

		public long getMachineId() {
			return this.machineId;
		}

		public void setMachineId(long machineId) {
			this.machineId = machineId;
		}

		public double getBasePrice() {
			return this.basePrice;
		}

		public void setBasePrice(double basePrice) {
			this.basePrice = basePrice;
		}

		public double getEPower() {
			return this.ePower;
		}

		public void setEPower(double ePower) {
			this.ePower = ePower;
		}

		public int getGroupId() {
			return this.groupId;
		}

		public void setGroupId(int groupId) {
			this.groupId = groupId;
		}

		public Timestamp getLastUpdate() {
			return this.lastUpdate;
		}

		public void setLastUpdate(Timestamp lastUpdate) {
			this.lastUpdate = lastUpdate;
		}

		public String getModel() {
			return this.model;
		}

		public void setModel(String model) {
			this.model = model;
		}

		public int getModelYear() {
			return this.modelYear;
		}

		public void setModelYear(int modelYear) {
			this.modelYear = modelYear;
		}

		public double getPower() {
			return this.power;
		}

		public void setPower(double power) {
			this.power = power;
		}

		public double getRetailPrice() {
			return this.retailPrice;
		}

		public void setRetailPrice(double retailPrice) {
			this.retailPrice = retailPrice;
		}
		
		public ManufacturerDO getManufacturerDO() {
			return manufacturerDO;
		}

		public void setManufacturerDO(ManufacturerDO manufacturerDO) {
			this.manufacturerDO = manufacturerDO;
		}

		public double getePower() {
			return ePower;
		}

		public void setePower(double ePower) {
			this.ePower = ePower;
		}

		/**
		 * @return the lol
		 */
		public double getLol() {
			return lol;
		}

		/**
		 * @param lol the lol to set
		 */
		public void setLol(double lol) {
			this.lol = lol;
		}

		public double getLolToDisplay() {
			return lolToDisplay;
		}

		public void setLolToDisplay(double lolToDisplay) {
			this.lolToDisplay = lolToDisplay;
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

		public double getAdjFactor() {
			return adjFactor;
		}

		public void setAdjFactor(double adjFactor) {
			this.adjFactor = adjFactor;
		}
		
	}

