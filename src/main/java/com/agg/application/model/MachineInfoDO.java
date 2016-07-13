package com.agg.application.model;

import java.sql.Timestamp;
import java.util.Date;

public class MachineInfoDO {
	
		private long machineId;
		
		private ManufacturerDO manufacturerDO;

		private double basePrice;

		private double ePower;

		private int groupId;

		private Timestamp lastUpdate;

		private String machineType;

		private String model;

		private Date modelYear;

		private double power;

		private double retailPrice;
		
		private double lol;
		
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

		public Date getModelYear() {
			return this.modelYear;
		}

		public void setModelYear(Date modelYear) {
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

	}

