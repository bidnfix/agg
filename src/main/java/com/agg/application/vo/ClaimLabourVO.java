package com.agg.application.vo;

public class ClaimLabourVO {
	private int id;
	private String laborNo;
	private String laborDescr;
	private double laborHrs;
	private double laborHourlyRate;
	/**
	 * 
	 */
	public ClaimLabourVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the laborNo
	 */
	public String getLaborNo() {
		return laborNo;
	}
	/**
	 * @param laborNo the laborNo to set
	 */
	public void setLaborNo(String laborNo) {
		this.laborNo = laborNo;
	}
	/**
	 * @return the laborDescr
	 */
	public String getLaborDescr() {
		return laborDescr;
	}
	/**
	 * @param laborDescr the laborDescr to set
	 */
	public void setLaborDescr(String laborDescr) {
		this.laborDescr = laborDescr;
	}
	
	/**
	 * @return the laborHrs
	 */
	public double getLaborHrs() {
		return laborHrs;
	}

	/**
	 * @param laborHrs the laborHrs to set
	 */
	public void setLaborHrs(double laborHrs) {
		this.laborHrs = laborHrs;
	}

	/**
	 * @return the laborHourlyRate
	 */
	public double getLaborHourlyRate() {
		return laborHourlyRate;
	}

	/**
	 * @param laborHourlyRate the laborHourlyRate to set
	 */
	public void setLaborHourlyRate(double laborHourlyRate) {
		this.laborHourlyRate = laborHourlyRate;
	}
	
}
