package com.agg.application.model;

public class PricingDO {
	
	private int coverageLevelHours;
	
	private int ptBasePrice;
	
	private int phBasePrice;
	
	private int plBasePrice;
	
	public PricingDO(int coverageLevelHours, int ptBasePrice, int phBasePrice, int plBasePrice){
		this.coverageLevelHours = coverageLevelHours;
		this.ptBasePrice = ptBasePrice;
		this.phBasePrice = phBasePrice;
		this.plBasePrice = plBasePrice;
	}

	/**
	 * @return the coverageLevelHours
	 */
	public int getCoverageLevelHours() {
		return coverageLevelHours;
	}

	/**
	 * @param coverageLevelHours the coverageLevelHours to set
	 */
	public void setCoverageLevelHours(int coverageLevelHours) {
		this.coverageLevelHours = coverageLevelHours;
	}

	/**
	 * @return the ptBasePrice
	 */
	public int getPtBasePrice() {
		return ptBasePrice;
	}

	/**
	 * @param ptBasePrice the ptBasePrice to set
	 */
	public void setPtBasePrice(int ptBasePrice) {
		this.ptBasePrice = ptBasePrice;
	}

	/**
	 * @return the phBasePrice
	 */
	public int getPhBasePrice() {
		return phBasePrice;
	}

	/**
	 * @param phBasePrice the phBasePrice to set
	 */
	public void setPhBasePrice(int phBasePrice) {
		this.phBasePrice = phBasePrice;
	}

	/**
	 * @return the plBasePrice
	 */
	public int getPlBasePrice() {
		return plBasePrice;
	}

	/**
	 * @param plBasePrice the plBasePrice to set
	 */
	public void setPlBasePrice(int plBasePrice) {
		this.plBasePrice = plBasePrice;
	}

}
