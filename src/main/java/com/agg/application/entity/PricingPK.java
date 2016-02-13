package com.agg.application.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the pricing database table.
 * 
 */
@Embeddable
public class PricingPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="group_id")
	private int groupId;

	private byte condition;

	@Column(name="coverage_term")
	private int coverageTerm;

	@Column(name="deductible_amount")
	private int deductibleAmount;

	@Column(name="c_level_hours")
	private int cLevelHours;

	public PricingPK() {
	}
	public int getGroupId() {
		return this.groupId;
	}
	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}
	public byte getCondition() {
		return this.condition;
	}
	public void setCondition(byte condition) {
		this.condition = condition;
	}
	public int getCoverageTerm() {
		return this.coverageTerm;
	}
	public void setCoverageTerm(int coverageTerm) {
		this.coverageTerm = coverageTerm;
	}
	public int getDeductibleAmount() {
		return this.deductibleAmount;
	}
	public void setDeductibleAmount(int deductibleAmount) {
		this.deductibleAmount = deductibleAmount;
	}
	public int getCLevelHours() {
		return this.cLevelHours;
	}
	public void setCLevelHours(int cLevelHours) {
		this.cLevelHours = cLevelHours;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof PricingPK)) {
			return false;
		}
		PricingPK castOther = (PricingPK)other;
		return 
			(this.groupId == castOther.groupId)
			&& (this.condition == castOther.condition)
			&& (this.coverageTerm == castOther.coverageTerm)
			&& (this.deductibleAmount == castOther.deductibleAmount)
			&& (this.cLevelHours == castOther.cLevelHours);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.groupId;
		hash = hash * prime + ((int) this.condition);
		hash = hash * prime + this.coverageTerm;
		hash = hash * prime + this.deductibleAmount;
		hash = hash * prime + this.cLevelHours;
		
		return hash;
	}
}