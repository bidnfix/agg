package com.agg.application.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 * The persistent class for the machine_info database table.
 * 
 */
@Entity
@Table(name="machine_info")
@NamedQuery(name="MachineInfo.findAll", query="SELECT m FROM MachineInfo m")
public class MachineInfo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name="machine_id")
	private long machineId;

	@Column(name="base_price")
	private double basePrice;

	@Column(name="e_power")
	private double ePower;

	@Column(name="last_update")
	private Timestamp lastUpdate;

	@Column(name="model")
	private String model;

	//@Temporal(TemporalType.DATE)
	@Column(name="model_year")
	private Integer modelYear;

	private double power;

	@Column(name="retail_price")
	private double retailPrice;
	
	@Column(name="adjustment_factor")
	private double adjFactor;
	
	//bi-directional many-to-one association to Manufacturer
	@ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinColumn(name="group_id")
	private GroupConstant groupConstant;

	//bi-directional many-to-one association to Manufacturer
	@ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinColumn(name="manf_id")
	private Manufacturer manufacturer;

	@ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinColumn(name="machine_type_id")
	private MachineType machineType;

	//bi-directional many-to-one association to MachineNote
	 @OneToMany(mappedBy="machineInfo")
	 private List<MachineNote> machineNotes;
		
	/*//bi-directional many-to-one association to MachineModel
	@OneToMany(mappedBy="machineInfo")
	private List<MachineModel> machineModels;*/

	public double getePower() {
		return ePower;
	}

	public void setePower(double ePower) {
		this.ePower = ePower;
	}

	public MachineType getMachineType() {
		return machineType;
	}

	public void setMachineType(MachineType machineType) {
		this.machineType = machineType;
	}

	public MachineInfo() {
	}

	public long getMachineId() {
		return this.machineId;
	}

	public void setMachineId(long machineId) {
		this.machineId = machineId;
	}

	public double getBasePrice() {
		return this.basePrice;
	}

	public void setBasePrice(double basePrice) {
		this.basePrice = basePrice;
	}

	public double getEPower() {
		return this.ePower;
	}

	public void setEPower(double ePower) {
		this.ePower = ePower;
	}


	public Timestamp getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public String getModel() {
		return this.model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public Integer getModelYear() {
		return modelYear;
	}

	public void setModelYear(Integer modelYear) {
		this.modelYear = modelYear;
	}

	public double getPower() {
		return this.power;
	}

	public void setPower(double power) {
		this.power = power;
	}

	public double getRetailPrice() {
		return this.retailPrice;
	}

	public void setRetailPrice(double retailPrice) {
		this.retailPrice = retailPrice;
	}

	public Manufacturer getManufacturer() {
		return this.manufacturer;
	}

	public void setManufacturer(Manufacturer manufacturer) {
		this.manufacturer = manufacturer;
	}

	/*public List<MachineModel> getMachineModels() {
		return this.machineModels;
	}

	public void setMachineModels(List<MachineModel> machineModels) {
		this.machineModels = machineModels;
	}*/

	/*public MachineModel addMachineModel(MachineModel machineModel) {
		getMachineModels().add(machineModel);
		machineModel.setMachineInfo(this);

		return machineModel;
	}

	public MachineModel removeMachineModel(MachineModel machineModel) {
		getMachineModels().remove(machineModel);
		machineModel.setMachineInfo(null);

		return machineModel;
	}*/

	public List<MachineNote> getMachineNotes() {
		return this.machineNotes;
	}

	public void setMachineNotes(List<MachineNote> machineNotes) {
		this.machineNotes = machineNotes;
	}

	public MachineNote addMachineNote(MachineNote machineNote) {
		getMachineNotes().add(machineNote);
		machineNote.setMachineInfo(this);

		return machineNote;
	}

	public MachineNote removeMachineNote(MachineNote machineNote) {
		getMachineNotes().remove(machineNote);
		machineNote.setMachineInfo(null);

		return machineNote;
	}
	
	public GroupConstant getGroupConstant() {
		return groupConstant;
	}

	public void setGroupConstant(GroupConstant groupConstant) {
		this.groupConstant = groupConstant;
	}

	public double getAdjFactor() {
		return adjFactor;
	}

	public void setAdjFactor(double adjFactor) {
		this.adjFactor = adjFactor;
	}
	
}