package com.agg.application.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the group_constants database table.
 * 
 */
@Entity
@Table(name="group_constants")
@NamedQuery(name="GroupConstant.findAll", query="SELECT g FROM GroupConstant g")
public class GroupConstant implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="group_id")
	private long groupId;

	private double comm;

	private double lol;

	private double msrp;

	@Column(name="old_id")
	private String oldId;

	private String rate;

	private double sales;

	private double tax;

	public GroupConstant() {
	}

	public long getGroupId() {
		return this.groupId;
	}

	public void setGroupId(long groupId) {
		this.groupId = groupId;
	}

	public double getComm() {
		return this.comm;
	}

	public void setComm(double comm) {
		this.comm = comm;
	}

	public double getLol() {
		return this.lol;
	}

	public void setLol(double lol) {
		this.lol = lol;
	}

	public double getMsrp() {
		return this.msrp;
	}

	public void setMsrp(double msrp) {
		this.msrp = msrp;
	}

	public String getOldId() {
		return this.oldId;
	}

	public void setOldId(String oldId) {
		this.oldId = oldId;
	}

	public String getRate() {
		return this.rate;
	}

	public void setRate(String rate) {
		this.rate = rate;
	}

	public double getSales() {
		return this.sales;
	}

	public void setSales(double sales) {
		this.sales = sales;
	}

	public double getTax() {
		return this.tax;
	}

	public void setTax(double tax) {
		this.tax = tax;
	}

}