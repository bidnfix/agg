package com.agg.application.model;

public class UsageTierDO {
	
	private long id;
	
	private long usageFrom;
	
	private long usageTo;
	
	private double adjFactor;
	
	public UsageTierDO()
	{
		
	}
	
	public UsageTierDO(long id, long usageFrom, long usageTo, double adjFactor)
	{
		this.id = id;
		this.usageFrom = usageFrom;
		this.usageTo = usageTo;
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

	public long getUsageFrom() {
		return usageFrom;
	}

	public void setUsageFrom(long usageFrom) {
		this.usageFrom = usageFrom;
	}

	public long getUsageTo() {
		return usageTo;
	}

	public void setUsageTo(long usageTo) {
		this.usageTo = usageTo;
	}

	public double getAdjFactor() {
		return adjFactor;
	}

	public void setAdjFactor(double adjFactor) {
		this.adjFactor = adjFactor;
	}

}
