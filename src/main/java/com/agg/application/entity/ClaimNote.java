package com.agg.application.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;


/**
 * The persistent class for the claim_notes database table.
 * 
 */
@Entity
@Table(name="claim_notes")
@NamedQuery(name="ClaimNote.findAll", query="SELECT c FROM ClaimNote c")
public class ClaimNote implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	@Column(name="claim_id")
	private int claimId;

	@Column(name="account_id")
	private long accountId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="last_update")
	private Date lastUpdate;

	@Lob
	private String notes;
	
	@Column(name="note_type")
	private char noteType;

	public ClaimNote() {
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

	public Date getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public String getNotes() {
		return this.notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public long getAccountId() {
		return accountId;
	}

	public void setAccountId(long accountId) {
		this.accountId = accountId;
	}

	public char getNoteType() {
		return noteType;
	}

	public void setNoteType(char noteType) {
		this.noteType = noteType;
	}

	
}