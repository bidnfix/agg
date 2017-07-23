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
@Table(name="claim_part")
@NamedQuery(name="ClaimPart.findAll", query="SELECT c FROM ClaimPart c")
public class ClaimPart {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(name="claim_id")
	private int claimId;
	
	@Column(name="part_no")
	private String partNo;
	
	@Column(name="part_descr")
	private String partDescr;
	
	private double qty;
	
	@Column(name="unit_price")
	private double unitPrice;
	
	@Column(name="adj_qty")
	private double adjQty;
	
	@Column(name="adj_unit_price")
	private double adjUnitPrice;

	/**
	 * 
	 */
	public ClaimPart() {
		super();
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
	 * @return the partNo
	 */
	public String getPartNo() {
		return partNo;
	}

	/**
	 * @param partNo the partNo to set
	 */
	public void setPartNo(String partNo) {
		this.partNo = partNo;
	}

	/**
	 * @return the partDescr
	 */
	public String getPartDescr() {
		return partDescr;
	}

	/**
	 * @param partDescr the partDescr to set
	 */
	public void setPartDescr(String partDescr) {
		this.partDescr = partDescr;
	}

	/**
	 * @return the qty
	 */
	public double getQty() {
		return qty;
	}

	/**
	 * @param qty the qty to set
	 */
	public void setQty(double qty) {
		this.qty = qty;
	}

	/**
	 * @return the unitPrice
	 */
	public double getUnitPrice() {
		return unitPrice;
	}

	/**
	 * @param unitPrice the unitPrice to set
	 */
	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}

	/**
	 * @return the adjQty
	 */
	public double getAdjQty() {
		return adjQty;
	}

	/**
	 * @param adjQty the adjQty to set
	 */
	public void setAdjQty(double adjQty) {
		this.adjQty = adjQty;
	}

	/**
	 * @return the adjUnitPrice
	 */
	public double getAdjUnitPrice() {
		return adjUnitPrice;
	}

	/**
	 * @param adjUnitPrice the adjUnitPrice to set
	 */
	public void setAdjUnitPrice(double adjUnitPrice) {
		this.adjUnitPrice = adjUnitPrice;
	}
	
}
