package com.agg.application.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

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
	
	@Temporal(TemporalType.DATE)
	@Column(name="model_year")
	private Date modelYear;
	
	@Column(name="base_price")
	private double basePrice;

	@Column(name="e_power")
	private double ePower;

	@Column(name="group_id")
	private int groupId;

	@Column(name="last_update")
	private Timestamp lastUpdate;


	private double power;

	@Column(name="retail_price")
	private double retailPrice;

	//bi-directional many-to-one association to MachineInfo
	@ManyToOne
	@JoinColumn(name="machine_type_id")
	private MachineType machineType;

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

	/**
	 * @return the machineType
	 */
	public MachineType getMachineType() {
		return machineType;
	}

	/**
	 * @param machineType the machineType to set
	 */
	public void setMachineType(MachineType machineType) {
		this.machineType = machineType;
	}

	/**
	 * @return the modelYear
	 */
	public Date getModelYear() {
		return modelYear;
	}

	/**
	 * @param modelYear the modelYear to set
	 */
	public void setModelYear(Date modelYear) {
		this.modelYear = modelYear;
	}

	/**
	 * @return the basePrice
	 */
	public double getBasePrice() {
		return basePrice;
	}

	/**
	 * @param basePrice the basePrice to set
	 */
	public void setBasePrice(double basePrice) {
		this.basePrice = basePrice;
	}

	/**
	 * @return the ePower
	 */
	public double getePower() {
		return ePower;
	}

	/**
	 * @param ePower the ePower to set
	 */
	public void setePower(double ePower) {
		this.ePower = ePower;
	}

	/**
	 * @return the groupId
	 */
	public int getGroupId() {
		return groupId;
	}

	/**
	 * @param groupId the groupId to set
	 */
	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	/**
	 * @return the lastUpdate
	 */
	public Timestamp getLastUpdate() {
		return lastUpdate;
	}

	/**
	 * @param lastUpdate the lastUpdate to set
	 */
	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	
	/**
	 * @return the power
	 */
	public double getPower() {
		return power;
	}

	/**
	 * @param power the power to set
	 */
	public void setPower(double power) {
		this.power = power;
	}

	/**
	 * @return the retailPrice
	 */
	public double getRetailPrice() {
		return retailPrice;
	}

	/**
	 * @param retailPrice the retailPrice to set
	 */
	public void setRetailPrice(double retailPrice) {
		this.retailPrice = retailPrice;
	}
	
}