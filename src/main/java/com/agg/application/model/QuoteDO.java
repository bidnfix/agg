package com.agg.application.model;

import java.sql.Timestamp;
import java.util.Date;

import com.agg.application.entity.Dealer;
import com.agg.application.entity.Manufacturer;
import com.agg.application.entity.QuotePK;

public class QuoteDO {
	
	private QuotePK id;

	private int cPtHHours;

	private int cPtHPlHours;

	private int cPtHours;

	private int coverageLevelHours;

	private int coverageTerm;

	private Date createDate;

	private double dealerMarkup;

	private String dealerMarkupType;

	private double deductAmount;

	private int groupId;

	private int hHours;

	private int hMonths;

	private short isArchive;

	private Timestamp lastUpdate;

	private int machineHours;

	private int machineMeterHours;

	private String machineModel;

	private String machineModelNum;

	private int machineMonths;

	private int machinePower;

	private double machineRetailPrice;

	private Date machineSaleDate;

	private String machineSerial;

	private String machineUoe;

	private Date machineYear;

	private Date manfEndDate;

	private byte manfEndKnown;

	private byte manfExpired;

	private String manfName;

	private byte manfVerified;

	private String otherProv;

	private int prId;

	private int ptHours;

	private int ptMonths;

	private int servicingDealer;

	private byte status;

	private Dealer dealer;

	private Manufacturer manufacturer;

	public QuotePK getId() {
		return this.id;
	}

	public void setId(QuotePK id) {
		this.id = id;
	}

	public int getCPtHHours() {
		return this.cPtHHours;
	}

	public void setCPtHHours(int cPtHHours) {
		this.cPtHHours = cPtHHours;
	}

	public int getCPtHPlHours() {
		return this.cPtHPlHours;
	}

	public void setCPtHPlHours(int cPtHPlHours) {
		this.cPtHPlHours = cPtHPlHours;
	}

	public int getCPtHours() {
		return this.cPtHours;
	}

	public void setCPtHours(int cPtHours) {
		this.cPtHours = cPtHours;
	}

	public int getCoverageLevelHours() {
		return this.coverageLevelHours;
	}

	public void setCoverageLevelHours(int coverageLevelHours) {
		this.coverageLevelHours = coverageLevelHours;
	}

	public int getCoverageTerm() {
		return this.coverageTerm;
	}

	public void setCoverageTerm(int coverageTerm) {
		this.coverageTerm = coverageTerm;
	}

	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public double getDealerMarkup() {
		return this.dealerMarkup;
	}

	public void setDealerMarkup(double dealerMarkup) {
		this.dealerMarkup = dealerMarkup;
	}

	public String getDealerMarkupType() {
		return this.dealerMarkupType;
	}

	public void setDealerMarkupType(String dealerMarkupType) {
		this.dealerMarkupType = dealerMarkupType;
	}

	public double getDeductAmount() {
		return this.deductAmount;
	}

	public void setDeductAmount(double deductAmount) {
		this.deductAmount = deductAmount;
	}

	public int getGroupId() {
		return this.groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	public int getHHours() {
		return this.hHours;
	}

	public void setHHours(int hHours) {
		this.hHours = hHours;
	}

	public int getHMonths() {
		return this.hMonths;
	}

	public void setHMonths(int hMonths) {
		this.hMonths = hMonths;
	}

	public short getIsArchive() {
		return this.isArchive;
	}

	public void setIsArchive(short isArchive) {
		this.isArchive = isArchive;
	}

	public Timestamp getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public int getMachineHours() {
		return this.machineHours;
	}

	public void setMachineHours(int machineHours) {
		this.machineHours = machineHours;
	}

	public int getMachineMeterHours() {
		return this.machineMeterHours;
	}

	public void setMachineMeterHours(int machineMeterHours) {
		this.machineMeterHours = machineMeterHours;
	}

	public String getMachineModel() {
		return this.machineModel;
	}

	public void setMachineModel(String machineModel) {
		this.machineModel = machineModel;
	}

	public String getMachineModelNum() {
		return this.machineModelNum;
	}

	public void setMachineModelNum(String machineModelNum) {
		this.machineModelNum = machineModelNum;
	}

	public int getMachineMonths() {
		return this.machineMonths;
	}

	public void setMachineMonths(int machineMonths) {
		this.machineMonths = machineMonths;
	}

	public int getMachinePower() {
		return this.machinePower;
	}

	public void setMachinePower(int machinePower) {
		this.machinePower = machinePower;
	}

	public double getMachineRetailPrice() {
		return this.machineRetailPrice;
	}

	public void setMachineRetailPrice(double machineRetailPrice) {
		this.machineRetailPrice = machineRetailPrice;
	}

	public Date getMachineSaleDate() {
		return this.machineSaleDate;
	}

	public void setMachineSaleDate(Date machineSaleDate) {
		this.machineSaleDate = machineSaleDate;
	}

	public String getMachineSerial() {
		return this.machineSerial;
	}

	public void setMachineSerial(String machineSerial) {
		this.machineSerial = machineSerial;
	}

	public String getMachineUoe() {
		return this.machineUoe;
	}

	public void setMachineUoe(String machineUoe) {
		this.machineUoe = machineUoe;
	}

	public Date getMachineYear() {
		return this.machineYear;
	}

	public void setMachineYear(Date machineYear) {
		this.machineYear = machineYear;
	}

	public Date getManfEndDate() {
		return this.manfEndDate;
	}

	public void setManfEndDate(Date manfEndDate) {
		this.manfEndDate = manfEndDate;
	}

	public byte getManfEndKnown() {
		return this.manfEndKnown;
	}

	public void setManfEndKnown(byte manfEndKnown) {
		this.manfEndKnown = manfEndKnown;
	}

	public byte getManfExpired() {
		return this.manfExpired;
	}

	public void setManfExpired(byte manfExpired) {
		this.manfExpired = manfExpired;
	}

	public String getManfName() {
		return this.manfName;
	}

	public void setManfName(String manfName) {
		this.manfName = manfName;
	}

	public byte getManfVerified() {
		return this.manfVerified;
	}

	public void setManfVerified(byte manfVerified) {
		this.manfVerified = manfVerified;
	}

	public String getOtherProv() {
		return this.otherProv;
	}

	public void setOtherProv(String otherProv) {
		this.otherProv = otherProv;
	}

	public int getPrId() {
		return this.prId;
	}

	public void setPrId(int prId) {
		this.prId = prId;
	}

	public int getPtHours() {
		return this.ptHours;
	}

	public void setPtHours(int ptHours) {
		this.ptHours = ptHours;
	}

	public int getPtMonths() {
		return this.ptMonths;
	}

	public void setPtMonths(int ptMonths) {
		this.ptMonths = ptMonths;
	}

	public int getServicingDealer() {
		return this.servicingDealer;
	}

	public void setServicingDealer(int servicingDealer) {
		this.servicingDealer = servicingDealer;
	}

	public byte getStatus() {
		return this.status;
	}

	public void setStatus(byte status) {
		this.status = status;
	}

	public Dealer getDealer() {
		return this.dealer;
	}

	public void setDealer(Dealer dealer) {
		this.dealer = dealer;
	}

	public Manufacturer getManufacturer() {
		return this.manufacturer;
	}

	public void setManufacturer(Manufacturer manufacturer) {
		this.manufacturer = manufacturer;
	}
}
