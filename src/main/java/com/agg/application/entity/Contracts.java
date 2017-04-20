/**
 * htamada
 */
package com.agg.application.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
	
	//@Temporal(TemporalType.DATE)
	@Column(name="inception_date")
	private Timestamp inceptionDate;
	
	//@Temporal(TemporalType.DATE)
	@Column(name="expiration_date")
	private Timestamp expirationDate;
	
	@Column(name="expiration_usage_hours")
	private int expirationUsageHours;
	
	@Column(name="comments")
	private String comments;
	
	@Temporal(TemporalType.DATE)
	@Column(name="last_updated_date")
	private Date lastUpdatedDate;
	
	@Column(name="cheq_no")
	private String cheqNo;
	
	//@Temporal(TemporalType.DATE)
	@Column(name="received_date")
	private Timestamp receivedDate;
	
	@OneToMany(mappedBy = "contract", cascade = CascadeType.ALL)
	private Set<Check> checks;
	
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

	public Timestamp getInceptionDate() {
		return inceptionDate;
	}

	public void setInceptionDate(Timestamp inceptionDate) {
		this.inceptionDate = inceptionDate;
	}

	public Timestamp getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(Timestamp expirationDate) {
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

	/**
	 * @return the cheqNo
	 */
	public String getCheqNo() {
		return cheqNo;
	}

	/**
	 * @param cheqNo the cheqNo to set
	 */
	public void setCheqNo(String cheqNo) {
		this.cheqNo = cheqNo;
	}

	public Timestamp getReceivedDate() {
		return receivedDate;
	}

	public void setReceivedDate(Timestamp receivedDate) {
		this.receivedDate = receivedDate;
	}

	public Set<Check> getChecks() {
		return checks;
	}

	public void setChecks(Set<Check> checks) {
		this.checks = checks;
	}
	
}
