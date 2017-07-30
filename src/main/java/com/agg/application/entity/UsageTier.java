package com.agg.application.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.*;


/**
 * The persistent class for the use_of_equip database table.
 * 
 */
@Entity
@Table(name="usage_tier")
@NamedQuery(name="UsageTier.findAll", query="SELECT u FROM UsageTier u")
public class UsageTier implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long id;

	@Column(name="usage_from")
	private long usageFrom;
	
	@Column(name="usage_to")
	private long usageTo;
	
	@Column(name="adjustment_factor")
	private double adjFactor;
	
	@Column(name="last_update")
	private Timestamp lastUpdate;

	public UsageTier() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getUsageFrom() {
		return usageFrom;
	}

	public void setUsageFrom(long usageFrom) {
		this.usageFrom = usageFrom;
	}

	public long getUsageTo() {
		return usageTo;
	}

	public void setUsageTo(long usageTo) {
		this.usageTo = usageTo;
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
	

}