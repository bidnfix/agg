package com.agg.application.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the prog_manf_model_xref database table.
 * 
 */
@Embeddable
public class ProgManfModelXrefPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="prog_id", insertable=false, updatable=false)
	private int progId;

	@Column(name="machine_info_id", insertable=false, updatable=false)
	private int machineInfoId;

	public ProgManfModelXrefPK() {
	}
	public int getProgId() {
		return this.progId;
	}
	public void setProgId(int progId) {
		this.progId = progId;
	}
	public int getMachineInfoId() {
		return this.machineInfoId;
	}
	public void setMachineInfoId(int machineInfoId) {
		this.machineInfoId = machineInfoId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof ProgManfModelXrefPK)) {
			return false;
		}
		ProgManfModelXrefPK castOther = (ProgManfModelXrefPK)other;
		return 
			(this.progId == castOther.progId)
			&& (this.machineInfoId == castOther.machineInfoId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.progId;
		hash = hash * prime + this.machineInfoId;
		
		return hash;
	}
}