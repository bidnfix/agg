package com.agg.application.model;

public class WorklistDO {
	
	private long expContracts;
	
	private long actContracts;
	
	private long estPrice;
	
	private long invoiced;
	
	private long purchaseReq;
	
	private long claims;

	public long getExpContracts() {
		return expContracts;
	}

	public void setExpContracts(long expContracts) {
		this.expContracts = expContracts;
	}

	public long getActContracts() {
		return actContracts;
	}

	public void setActContracts(long actContracts) {
		this.actContracts = actContracts;
	}

	public long getEstPrice() {
		return estPrice;
	}

	public void setEstPrice(long estPrice) {
		this.estPrice = estPrice;
	}

	public long getInvoiced() {
		return invoiced;
	}

	public void setInvoiced(long invoiced) {
		this.invoiced = invoiced;
	}

	public long getPurchaseReq() {
		return purchaseReq;
	}

	public void setPurchaseReq(long purchaseReq) {
		this.purchaseReq = purchaseReq;
	}

	public long getClaims() {
		return claims;
	}

	public void setClaims(long claims) {
		this.claims = claims;
	}

}
