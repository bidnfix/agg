package com.agg.application.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the machine_notes database table.
 * 
 */
@Entity
@Table(name="machine_notes")
@NamedQuery(name="MachineNote.findAll", query="SELECT m FROM MachineNote m")
public class MachineNote implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	@Column(name="last_update")
	private Timestamp lastUpdate;

	@Lob
	private String notes;

	//bi-directional many-to-one association to MachineInfo
	@ManyToOne
	@JoinColumn(name="machine_id")
	private MachineInfo machineInfo;

	public MachineNote() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Timestamp getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public String getNotes() {
		return this.notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public MachineInfo getMachineInfo() {
		return this.machineInfo;
	}

	public void setMachineInfo(MachineInfo machineInfo) {
		this.machineInfo = machineInfo;
	}

}