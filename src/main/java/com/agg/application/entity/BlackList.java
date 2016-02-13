package com.agg.application.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the black_list database table.
 * 
 */
@Entity
@Table(name="black_list")
@NamedQuery(name="BlackList.findAll", query="SELECT b FROM BlackList b")
public class BlackList implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	@Temporal(TemporalType.DATE)
	@Column(name="block_date")
	private Date blockDate;

	@Column(name="machine_id")
	private int machineId;

	@Lob
	private String reason;

	private String serial;

	public BlackList() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getBlockDate() {
		return this.blockDate;
	}

	public void setBlockDate(Date blockDate) {
		this.blockDate = blockDate;
	}

	public int getMachineId() {
		return this.machineId;
	}

	public void setMachineId(int machineId) {
		this.machineId = machineId;
	}

	public String getReason() {
		return this.reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getSerial() {
		return this.serial;
	}

	public void setSerial(String serial) {
		this.serial = serial;
	}

}