package com.agg.application.model;

public class OEMWarrantyTierDO {
	
	private long id;
	
	private long warrantyFrom;
	
	private long warrantyTo;
	
	private double adjFactor;
	
	public OEMWarrantyTierDO()
	{
		
	}
	
	public OEMWarrantyTierDO(long id, long warrantyFrom, long warrantyTo, double adjFactor)
	{
		this.id = id;
		this.warrantyFrom = warrantyFrom;
		this.warrantyTo = warrantyTo;
		this.adjFactor = adjFactor;
	}

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	public long getWarrantyFrom() {
		return warrantyFrom;
	}

	public void setWarrantyFrom(long warrantyFrom) {
		this.warrantyFrom = warrantyFrom;
	}

	public long getWarrantyTo() {
		return warrantyTo;
	}

	public void setWarrantyTo(long warrantyTo) {
		this.warrantyTo = warrantyTo;
	}

	public double getAdjFactor() {
		return adjFactor;
	}

	public void setAdjFactor(double adjFactor) {
		this.adjFactor = adjFactor;
	}

}
