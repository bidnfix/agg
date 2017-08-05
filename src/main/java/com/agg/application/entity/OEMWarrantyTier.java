package com.agg.application.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.*;


/**
 * The persistent class for the use_of_equip database table.
 * 
 */
@Entity
@Table(name="oem_warranty_tier")
@NamedQuery(name="OEMWarrantyTier.findAll", query="SELECT o FROM OEMWarrantyTier o")
public class OEMWarrantyTier implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long id;

	@Column(name="rem_warr_period_from")
	private long warrantyFrom;
	
	@Column(name="rem_warr_period_to")
	private long warrantyTo;
	
	@Column(name="adjustment_factor")
	private double adjFactor;
	
	@Column(name="last_update")
	private Timestamp lastUpdate;
	
	@Column(name="create_date")
	private Timestamp createDate;

	public OEMWarrantyTier() {
	}

	public long getId() {
		return this.id;
	}

	
	public void setId(long id) {
		this.id = id;
	}

	public long getWarrantyFrom() {
		return warrantyFrom;
	}

	public void setWarrantyFrom(long warrantyFrom) {
		this.warrantyFrom = warrantyFrom;
	}

	public long getWarrantyTo() {
		return warrantyTo;
	}

	public void setWarrantyTo(long warrantyTo) {
		this.warrantyTo = warrantyTo;
	}

	public double getAdjFactor() {
		return adjFactor;
	}

	public void setAdjFactor(double adjFactor) {
		this.adjFactor = adjFactor;
	}

	public Timestamp getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public Timestamp getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}

}