package com.agg.application.model;

import java.util.Date;


public class ClaimNoteDO {
	
	private int id;
	private int claimId;
	private long accountId;
	private Date lastUpdate;
	private String notes;
	private char noteType;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getClaimId() {
		return claimId;
	}
	public void setClaimId(int claimId) {
		this.claimId = claimId;
	}
	public Date getLastUpdate() {
		return lastUpdate;
	}
	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}
	public String getNotes() {
		return notes;
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
