/**
 * htamada
 */
package com.agg.application.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * @author htamada
 *
 */
@Entity
@Table(name="claim_labor")
@NamedQuery(name="ClaimLabor.findAll", query="SELECT c FROM ClaimPart c")
public class ClaimLabor {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(name="claim_id")
	private int claimId;
	
	@Column(name="labor_no")
	private String laborNo;
	
	@Column(name="labor_descr")
	private String laborDescr;
	
	@Column(name="labor_hrs")
	private double laborHrs;
	
	private double rate;

	/**
	 * 
	 */
	public ClaimLabor() {
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

}
