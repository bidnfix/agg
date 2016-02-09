package com.agg.application.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the sclass database table.
 * 
 */
@Entity
@Table(name="sclass")
@NamedQuery(name="Sclass.findAll", query="SELECT s FROM Sclass s")
public class Sclass implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="sc_id")
	private int scId;

	@Column(name="pr_id")
	private int prId;

	@Column(name="sc_is_active")
	private byte scIsActive;

	@Column(name="sc_last_update")
	private Timestamp scLastUpdate;

	@Column(name="sc_lol")
	private int scLol;

	@Column(name="sc_name")
	private String scName;

	public Sclass() {
	}

	public int getScId() {
		return this.scId;
	}

	public void setScId(int scId) {
		this.scId = scId;
	}

	public int getPrId() {
		return this.prId;
	}

	public void setPrId(int prId) {
		this.prId = prId;
	}

	public byte getScIsActive() {
		return this.scIsActive;
	}

	public void setScIsActive(byte scIsActive) {
		this.scIsActive = scIsActive;
	}

	public Timestamp getScLastUpdate() {
		return this.scLastUpdate;
	}

	public void setScLastUpdate(Timestamp scLastUpdate) {
		this.scLastUpdate = scLastUpdate;
	}

	public int getScLol() {
		return this.scLol;
	}

	public void setScLol(int scLol) {
		this.scLol = scLol;
	}

	public String getScName() {
		return this.scName;
	}

	public void setScName(String scName) {
		this.scName = scName;
	}

}