package com.agg.application.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the machine_type database table.
 * 
 */
@Entity
@Table(name="machine_type")
@NamedQuery(name="MachineType.findAll", query="SELECT m FROM MachineType m")
public class MachineType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name="machine_type_id")
	private long machineTypeId;

	@Column(name="machine_type")
	private String machineType;

	public MachineType() {
	}

	public long getMachineTypeId() {
		return this.machineTypeId;
	}

	public void setMachineTypeId(long machineTypeId) {
		this.machineTypeId = machineTypeId;
	}

	public String getMachineType() {
		return this.machineType;
	}

	public void setMachineType(String machineType) {
		this.machineType = machineType;
	}

}