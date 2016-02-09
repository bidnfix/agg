package com.agg.application.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the claim_notes_admin database table.
 * 
 */
@Entity
@Table(name="claim_notes_admin")
@NamedQuery(name="ClaimNotesAdmin.findAll", query="SELECT c FROM ClaimNotesAdmin c")
public class ClaimNotesAdmin implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	@Column(name="claim_id")
	private int claimId;

	@Column(name="dealer_id")
	private int dealerId;

	@Column(name="last_update")
	private Timestamp lastUpdate;

	@Lob
	private String notes;

	public ClaimNotesAdmin() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getClaimId() {
		return this.claimId;
	}

	public void setClaimId(int claimId) {
		this.claimId = claimId;
	}

	public int getDealerId() {
		return this.dealerId;
	}

	public void setDealerId(int dealerId) {
		this.dealerId = dealerId;
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

}