package com.agg.application.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the machine_model database table.
 * 
 */
@Entity
@Table(name="machine_model")
@NamedQuery(name="MachineModel.findAll", query="SELECT m FROM MachineModel m")
public class MachineModel implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="model_id")
	private int modelId;

	@Column(name="model_name")
	private String modelName;

	//bi-directional many-to-one association to MachineInfo
	@ManyToOne
	@JoinColumn(name="machine_id")
	private MachineInfo machineInfo;

	public MachineModel() {
	}

	public int getModelId() {
		return this.modelId;
	}

	public void setModelId(int modelId) {
		this.modelId = modelId;
	}

	public String getModelName() {
		return this.modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	public MachineInfo getMachineInfo() {
		return this.machineInfo;
	}

	public void setMachineInfo(MachineInfo machineInfo) {
		this.machineInfo = machineInfo;
	}

}