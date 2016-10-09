package com.agg.application.model;

import java.sql.Timestamp;
import java.util.Date;

/**
 * The persistent class for the claims database table.
 * 
 */
public class BugDO {
	private static final long serialVersionUID = 1L;

	private long id;

	private Timestamp createdOn;

	private String description;
	
	private Date discovered;

	private Date fixBy;

	private String notes;

	private int priority;

	private int reportedBy;

	private int status;

	private String url;

	private String userAgent;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		id = id;
	}

	public Timestamp getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Timestamp createdOn) {
		this.createdOn = createdOn;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDiscovered() {
		return discovered;
	}

	public void setDiscovered(Date discovered) {
		this.discovered = discovered;
	}

	public Date getFixBy() {
		return fixBy;
	}

	public void setFixBy(Date fixBy) {
		this.fixBy = fixBy;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public int getReportedBy() {
		return reportedBy;
	}

	public void setReportedBy(int reportedBy) {
		this.reportedBy = reportedBy;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUserAgent() {
		return userAgent;
	}

	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}


}