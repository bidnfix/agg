package com.agg.application.model;

public class UseOfEquipmentDO {
	
	private long id;
	
	private String equipName;
	
	private double discount;
	
	public UseOfEquipmentDO()
	{
		
	}
	
	public UseOfEquipmentDO(long id, String equipName, double discount)
	{
		this.id = id;
		this.equipName = equipName;
		this.discount = discount;
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

	/**
	 * @return the equipName
	 */
	public String getEquipName() {
		return equipName;
	}

	/**
	 * @param equipName the equipName to set
	 */
	public void setEquipName(String equipName) {
		this.equipName = equipName;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}
		
}
