/**
 * htamada
 */
package com.agg.application.model;

/**
 * @author htamada
 *
 */
public class ClaimLaborDO {
	private int id;
	private int claimId;
	private String laborNo;
	private String laborDescr;
	private int laborHrs;
	private double rate;
	private String laborTotal;
	/**
	 * 
	 */
	public ClaimLaborDO() {
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
	 * @return the claimId
	 */
	public int getClaimId() {
		return claimId;
	}
	/**
	 * @param claimId the claimId to set
	 */
	public void setClaimId(int claimId) {
		this.claimId = claimId;
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
	public int getLaborHrs() {
		return laborHrs;
	}
	/**
	 * @param laborHrs the laborHrs to set
	 */
	public void setLaborHrs(int laborHrs) {
		this.laborHrs = laborHrs;
	}
	/**
	 * @return the rate
	 */
	public double getRate() {
		return rate;
	}
	/**
	 * @param rate the rate to set
	 */
	public void setRate(double rate) {
		this.rate = rate;
	}
	public String getLaborTotal() {
		return laborTotal;
	}
	public void setLaborTotal(String laborTotal) {
		this.laborTotal = laborTotal;
	}
	
}
