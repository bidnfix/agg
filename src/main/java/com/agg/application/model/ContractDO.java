/**
 * htamada
 */
package com.agg.application.model;

import java.util.Date;

/**
 * @author htamada
 *
 */
public class ContractDO {
	/**
	 * 
	 */
	public ContractDO() {
		super();
		// TODO Auto-generated constructor stub
	}
	private long id;
	private String contractId;
	private long quoteId;
	private int status;
	private String machineSerialNo;
	private String coverageType;
	private int coverageTermMonths;
	private int coverageLevelHours;
	private double deductible;
	private double lol;
	private double availabeLol;
	private Date inceptionDate;
	private Date expirationDate;
	private int expirationUsageHours;
	private String comments;
	private Date lastUpdatedDate;
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
	 * @return the contractId
	 */
	public String getContractId() {
		return contractId;
	}
	/**
	 * @param contractId the contractId to set
	 */
	public void setContractId(String contractId) {
		this.contractId = contractId;
	}
	/**
	 * @return the quoteId
	 */
	public long getQuoteId() {
		return quoteId;
	}
	/**
	 * @param quoteId the quoteId to set
	 */
	public void setQuoteId(long quoteId) {
		this.quoteId = quoteId;
	}
	/**
	 * @return the status
	 */
	public int getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(int status) {
		this.status = status;
	}
	/**
	 * @return the machineSerialNo
	 */
	public String getMachineSerialNo() {
		return machineSerialNo;
	}
	/**
	 * @param machineSerialNo the machineSerialNo to set
	 */
	public void setMachineSerialNo(String machineSerialNo) {
		this.machineSerialNo = machineSerialNo;
	}
	/**
	 * @return the coverageType
	 */
	public String getCoverageType() {
		return coverageType;
	}
	/**
	 * @param coverageType the coverageType to set
	 */
	public void setCoverageType(String coverageType) {
		this.coverageType = coverageType;
	}
	/**
	 * @return the coverageTermMonths
	 */
	public int getCoverageTermMonths() {
		return coverageTermMonths;
	}
	/**
	 * @param coverageTermMonths the coverageTermMonths to set
	 */
	public void setCoverageTermMonths(int coverageTermMonths) {
		this.coverageTermMonths = coverageTermMonths;
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
	 * @return the deductible
	 */
	public double getDeductible() {
		return deductible;
	}
	/**
	 * @param deductible the deductible to set
	 */
	public void setDeductible(double deductible) {
		this.deductible = deductible;
	}
	/**
	 * @return the lol
	 */
	public double getLol() {
		return lol;
	}
	/**
	 * @param lol the lol to set
	 */
	public void setLol(double lol) {
		this.lol = lol;
	}
	/**
	 * @return the availabeLol
	 */
	public double getAvailabeLol() {
		return availabeLol;
	}
	/**
	 * @param availabeLol the availabeLol to set
	 */
	public void setAvailabeLol(double availabeLol) {
		this.availabeLol = availabeLol;
	}
	/**
	 * @return the inceptionDate
	 */
	public Date getInceptionDate() {
		return inceptionDate;
	}
	/**
	 * @param inceptionDate the inceptionDate to set
	 */
	public void setInceptionDate(Date inceptionDate) {
		this.inceptionDate = inceptionDate;
	}
	/**
	 * @return the expirationDate
	 */
	public Date getExpirationDate() {
		return expirationDate;
	}
	/**
	 * @param expirationDate the expirationDate to set
	 */
	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}
	/**
	 * @return the expirationUsageHours
	 */
	public int getExpirationUsageHours() {
		return expirationUsageHours;
	}
	/**
	 * @param expirationUsageHours the expirationUsageHours to set
	 */
	public void setExpirationUsageHours(int expirationUsageHours) {
		this.expirationUsageHours = expirationUsageHours;
	}
	/**
	 * @return the comments
	 */
	public String getComments() {
		return comments;
	}
	/**
	 * @param comments the comments to set
	 */
	public void setComments(String comments) {
		this.comments = comments;
	}
	/**
	 * @return the lastUpdatedDate
	 */
	public Date getLastUpdatedDate() {
		return lastUpdatedDate;
	}
	/**
	 * @param lastUpdatedDate the lastUpdatedDate to set
	 */
	public void setLastUpdatedDate(Date lastUpdatedDate) {
		this.lastUpdatedDate = lastUpdatedDate;
	}
	
}
