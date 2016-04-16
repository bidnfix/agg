package com.agg.application.model;

import java.io.Serializable;
import javax.persistence.*;


public class GroupDO{
	private static final long serialVersionUID = 1L;

	private int groupId;

	private double comm;

	private double lol;

	private double msrp;

	private String oldId;

	private String rate;

	private double sales;

	private double tax;

	public int getGroupId() {
		return this.groupId;
	}

	public void setGroupId(int groupId) {
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