package com.agg.application.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.sql.Timestamp;
import java.util.List;


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
	@Column(name="machine_id")
	private int machineId;

	@Column(name="base_price")
	private double basePrice;

	@Column(name="e_power")
	private double ePower;

	@Column(name="group_id")
	private int groupId;

	@Column(name="last_update")
	private Timestamp lastUpdate;

	@Column(name="machine_type")
	private String machineType;

	private String model;

	@Temporal(TemporalType.DATE)
	@Column(name="model_year")
	private Date modelYear;

	private double power;

	@Column(name="retail_price")
	private double retailPrice;

	//bi-directional many-to-one association to Manufacturer
	@ManyToOne
	@JoinColumn(name="manf_id")
	private Manufacturer manufacturer;

	//bi-directional many-to-one association to MachineNote
	@OneToMany(mappedBy="machineInfo")
	private List<MachineNote> machineNotes;

	public MachineInfo() {
	}

	public int getMachineId() {
		return this.machineId;
	}

	public void setMachineId(int machineId) {
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

	public int getGroupId() {
		return this.groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	public Timestamp getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public String getMachineType() {
		return this.machineType;
	}

	public void setMachineType(String machineType) {
		this.machineType = machineType;
	}

	public String getModel() {
		return this.model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public Date getModelYear() {
		return this.modelYear;
	}

	public void setModelYear(Date modelYear) {
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

}