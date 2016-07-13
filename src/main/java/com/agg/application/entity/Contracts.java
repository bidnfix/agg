/**
 * htamada
 */
package com.agg.application.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.NamedQuery;

/**
 * @author htamada
 *
 */
@Entity
@Table(name="contracts")
@NamedQuery(name="Contracts.findAll", query="SELECT c FROM Contracts c")
public class Contracts implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	//@GeneratedValue(strategy=GenerationType.AUTO)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name="contract_id")
	private String contractId;

	@Column(name="quote_id")
	private long quoteId;
	
	@Column(name="status")
	private int status;
	
	@Column(name="machine_serial_no")
	private String machineSerialNo;
	
	@Column(name="coverage_type")
	private String coverageType;
	
	@Column(name="coverage_term_months")
	private int coverageTermMonths;
	
	@Column(name="coverage_level_hours")
	private int coverageLevelHours;
	
	@Column(name="deductible")
	private double deductible;
	
	@Column(name="lol")
	private double lol;
	
	@Column(name="availabe_lol")
	private double availabeLol;
	
	@Temporal(TemporalType.DATE)
	@Column(name="inception_date")
	private Date inceptionDate;
	
	@Temporal(TemporalType.DATE)
	@Column(name="expiration_date")
	private Date expirationDate;
	
	@Column(name="expiration_usage_hours")
	private int expirationUsageHours;
	
	@Column(name="comments")
	private String comments;
	
	@Temporal(TemporalType.DATE)
	@Column(name="last_updated_date")
	private Date lastUpdatedDate;
	
	/**
	 * 
	 */
	public Contracts() {
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
