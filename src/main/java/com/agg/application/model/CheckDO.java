package com.agg.application.model;

import java.util.Date;

public class CheckDO {

	private long id;
	
	private String checkNo;
	
	private Date receivedDate;
	
	private double amount;
	
	private String checkAmount;
	
	private String checkRecievedDate;
	
	public String getCheckNo() {
		return checkNo;
	}

	public void setCheckNo(String checkNo) {
		this.checkNo = checkNo;
	}

	public Date getReceivedDate() {
		return receivedDate;
	}

	public void setReceivedDate(Date receivedDate) {
		this.receivedDate = receivedDate;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCheckAmount() {
		return checkAmount;
	}

	public void setCheckAmount(String checkAmount) {
		this.checkAmount = checkAmount;
	}

	public String getCheckRecievedDate() {
		return checkRecievedDate;
	}

	public void setCheckRecievedDate(String checkRecievedDate) {
		this.checkRecievedDate = checkRecievedDate;
	}
	
}
