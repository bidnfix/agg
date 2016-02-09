package com.agg.application.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the sprogram database table.
 * 
 */
@Entity
@Table(name="sprogram")
@NamedQuery(name="Sprogram.findAll", query="SELECT s FROM Sprogram s")
public class Sprogram implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="pr_id")
	private int prId;

	@Column(name="pr_a_servicing")
	private byte prAServicing;

	@Column(name="pr_c_hours")
	private int prCHours;

	@Column(name="pr_c_term")
	private int prCTerm;

	@Column(name="pr_c_type")
	private String prCType;

	@Column(name="pr_condition")
	private byte prCondition;

	@Column(name="pr_cost")
	private int prCost;

	@Column(name="pr_deductible")
	private int prDeductible;

	@Lob
	@Column(name="pr_desc")
	private String prDesc;

	@Column(name="pr_group")
	private String prGroup;

	@Column(name="pr_is_active")
	private byte prIsActive;

	@Column(name="pr_is_archive")
	private byte prIsArchive;

	@Column(name="pr_last_update")
	private Timestamp prLastUpdate;

	@Column(name="pr_lol")
	private double prLol;

	@Column(name="pr_name")
	private String prName;

	//bi-directional many-to-one association to Dealer
	@ManyToOne
	private Dealer dealer;

	public Sprogram() {
	}

	public int getPrId() {
		return this.prId;
	}

	public void setPrId(int prId) {
		this.prId = prId;
	}

	public byte getPrAServicing() {
		return this.prAServicing;
	}

	public void setPrAServicing(byte prAServicing) {
		this.prAServicing = prAServicing;
	}

	public int getPrCHours() {
		return this.prCHours;
	}

	public void setPrCHours(int prCHours) {
		this.prCHours = prCHours;
	}

	public int getPrCTerm() {
		return this.prCTerm;
	}

	public void setPrCTerm(int prCTerm) {
		this.prCTerm = prCTerm;
	}

	public String getPrCType() {
		return this.prCType;
	}

	public void setPrCType(String prCType) {
		this.prCType = prCType;
	}

	public byte getPrCondition() {
		return this.prCondition;
	}

	public void setPrCondition(byte prCondition) {
		this.prCondition = prCondition;
	}

	public int getPrCost() {
		return this.prCost;
	}

	public void setPrCost(int prCost) {
		this.prCost = prCost;
	}

	public int getPrDeductible() {
		return this.prDeductible;
	}

	public void setPrDeductible(int prDeductible) {
		this.prDeductible = prDeductible;
	}

	public String getPrDesc() {
		return this.prDesc;
	}

	public void setPrDesc(String prDesc) {
		this.prDesc = prDesc;
	}

	public String getPrGroup() {
		return this.prGroup;
	}

	public void setPrGroup(String prGroup) {
		this.prGroup = prGroup;
	}

	public byte getPrIsActive() {
		return this.prIsActive;
	}

	public void setPrIsActive(byte prIsActive) {
		this.prIsActive = prIsActive;
	}

	public byte getPrIsArchive() {
		return this.prIsArchive;
	}

	public void setPrIsArchive(byte prIsArchive) {
		this.prIsArchive = prIsArchive;
	}

	public Timestamp getPrLastUpdate() {
		return this.prLastUpdate;
	}

	public void setPrLastUpdate(Timestamp prLastUpdate) {
		this.prLastUpdate = prLastUpdate;
	}

	public double getPrLol() {
		return this.prLol;
	}

	public void setPrLol(double prLol) {
		this.prLol = prLol;
	}

	public String getPrName() {
		return this.prName;
	}

	public void setPrName(String prName) {
		this.prName = prName;
	}

	public Dealer getDealer() {
		return this.dealer;
	}

	public void setDealer(Dealer dealer) {
		this.dealer = dealer;
	}

}