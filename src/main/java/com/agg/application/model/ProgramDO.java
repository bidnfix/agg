package com.agg.application.model;

import java.sql.Timestamp;
import java.util.List;

public class ProgramDO {
	
	private Long prId;

	private byte aServicing;

	private int cHours;

	private int cTerm;

	private String cType;

	private byte condition;

	private int cost;

	private int deductible;

	private String desc;
	

	private String group;

	private byte isActive;

	private byte isArchive;

	private Timestamp lastUpdate;

	private double lol;

	private String name;

	private DealerDO dealerDO;
	
	private ManufacturerDO manufacturerDO;
	
	private List<MachineInfoDO> machineInfoDOList;
	
	private List<MachineInfoDO> machineModelList;
	
	public List<MachineInfoDO> getMachineModelList() {
		return machineModelList;
	}

	public void setMachineModelList(List<MachineInfoDO> machineModelList) {
		this.machineModelList = machineModelList;
	}

	public List<MachineInfoDO> getMachineInfoDOList() {
		return machineInfoDOList;
	}

	public void setMachineInfoDOList(List<MachineInfoDO> machineInfoDOList) {
		this.machineInfoDOList = machineInfoDOList;
	}

	public DealerDO getDealerDO() {	
		return dealerDO;
	}

	public void setDealerDO(DealerDO dealerDO) {
		this.dealerDO = dealerDO;
	}

	public ManufacturerDO getManufacturerDO() {
		return manufacturerDO;
	}

	public void setManufacturerDO(ManufacturerDO manufacturerDO) {
		this.manufacturerDO = manufacturerDO;
	}

	public Long getPrId() {
		return prId;
	}

	public void setPrId(Long prId) {
		this.prId = prId;
	}

	public byte getaServicing() {
		return aServicing;
	}

	public void setaServicing(byte aServicing) {
		this.aServicing = aServicing;
	}

	public int getcHours() {
		return cHours;
	}

	public void setcHours(int cHours) {
		this.cHours = cHours;
	}

	public int getcTerm() {
		return cTerm;
	}

	public void setcTerm(int cTerm) {
		this.cTerm = cTerm;
	}

	public String getcType() {
		return cType;
	}

	public void setcType(String cType) {
		this.cType = cType;
	}

	public byte getCondition() {
		return condition;
	}

	public void setCondition(byte condition) {
		this.condition = condition;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public int getDeductible() {
		return deductible;
	}

	public void setDeductible(int deductible) {
		this.deductible = deductible;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public byte getIsActive() {
		return isActive;
	}

	public void setIsActive(byte isActive) {
		this.isActive = isActive;
	}

	public byte getIsArchive() {
		return isArchive;
	}

	public void setIsArchive(byte isArchive) {
		this.isArchive = isArchive;
	}

	public Timestamp getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public double getLol() {
		return lol;
	}

	public void setLol(double lol) {
		this.lol = lol;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	

	
}
