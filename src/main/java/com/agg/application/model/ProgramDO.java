package com.agg.application.model;

import java.sql.Timestamp;

import com.agg.application.entity.Dealer;

public class ProgramDO {
	
	private Long prId;

	private byte prAServicing;

	private int prCHours;

	private int prCTerm;

	private String prCType;

	private byte prCondition;

	private int prCost;

	private int prDeductible;

	private String prDesc;
	

	private String prGroup;

	private byte prIsActive;

	private byte prIsArchive;

	private Timestamp prLastUpdate;

	private double prLol;

	private String prName;

	private DealerDO dealer;

	public Long getPrId() {
		return prId;
	}

	public void setPrId(Long prId) {
		this.prId = prId;
	}

	public byte getPrAServicing() {
		return prAServicing;
	}

	public void setPrAServicing(byte prAServicing) {
		this.prAServicing = prAServicing;
	}

	public int getPrCHours() {
		return prCHours;
	}

	public void setPrCHours(int prCHours) {
		this.prCHours = prCHours;
	}

	public int getPrCTerm() {
		return prCTerm;
	}

	public void setPrCTerm(int prCTerm) {
		this.prCTerm = prCTerm;
	}

	public String getPrCType() {
		return prCType;
	}

	public void setPrCType(String prCType) {
		this.prCType = prCType;
	}

	public byte getPrCondition() {
		return prCondition;
	}

	public void setPrCondition(byte prCondition) {
		this.prCondition = prCondition;
	}

	public int getPrCost() {
		return prCost;
	}

	public void setPrCost(int prCost) {
		this.prCost = prCost;
	}

	public int getPrDeductible() {
		return prDeductible;
	}

	public void setPrDeductible(int prDeductible) {
		this.prDeductible = prDeductible;
	}

	public String getPrDesc() {
		return prDesc;
	}

	public void setPrDesc(String prDesc) {
		this.prDesc = prDesc;
	}

	public String getPrGroup() {
		return prGroup;
	}

	public void setPrGroup(String prGroup) {
		this.prGroup = prGroup;
	}

	public byte getPrIsActive() {
		return prIsActive;
	}

	public void setPrIsActive(byte prIsActive) {
		this.prIsActive = prIsActive;
	}

	public byte getPrIsArchive() {
		return prIsArchive;
	}

	public void setPrIsArchive(byte prIsArchive) {
		this.prIsArchive = prIsArchive;
	}

	public Timestamp getPrLastUpdate() {
		return prLastUpdate;
	}

	public void setPrLastUpdate(Timestamp prLastUpdate) {
		this.prLastUpdate = prLastUpdate;
	}

	public double getPrLol() {
		return prLol;
	}

	public void setPrLol(double prLol) {
		this.prLol = prLol;
	}

	public String getPrName() {
		return prName;
	}

	public void setPrName(String prName) {
		this.prName = prName;
	}

	public DealerDO getDealer() {
		return dealer;
	}

	public void setDealer(DealerDO dealer) {
		this.dealer = dealer;
	}

}
