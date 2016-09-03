package com.agg.application.model;

public class WorklistDO {
	
	private int expContracts;
	
	private int actContracts;
	
	private int estPrice;
	
	private int invoiced;
	
	private int purchaseReq;
	
	private int claims;

	public int getExpContracts() {
		return expContracts;
	}

	public void setExpContracts(int expContracts) {
		this.expContracts = expContracts;
	}

	public int getActContracts() {
		return actContracts;
	}

	public void setActContracts(int actContracts) {
		this.actContracts = actContracts;
	}

	public int getEstPrice() {
		return estPrice;
	}

	public void setEstPrice(int estPrice) {
		this.estPrice = estPrice;
	}

	public int getInvoiced() {
		return invoiced;
	}

	public void setInvoiced(int invoiced) {
		this.invoiced = invoiced;
	}

	public int getPurchaseReq() {
		return purchaseReq;
	}

	public void setPurchaseReq(int purchaseReq) {
		this.purchaseReq = purchaseReq;
	}

	public int getClaims() {
		return claims;
	}

	public void setClaims(int claims) {
		this.claims = claims;
	}
		
}
