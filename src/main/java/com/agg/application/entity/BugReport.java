
package com.agg.application.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.sql.Timestamp;


/**
 * The persistent class for the bug_report database table.
 * 
 */
@Entity
@Table(name="bug_report")
@NamedQuery(name="BugReport.findAll", query="SELECT b FROM BugReport b")
public class BugReport implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long id;

	@Column(name="created_on")
	private Timestamp createdOn;

	@Lob
	private String description;

	@Temporal(TemporalType.DATE)
	private Date discovered;

	@Temporal(TemporalType.DATE)
	@Column(name="fix_by")
	private Date fixBy;

	@Lob
	private String notes;

	private int priority;

	@Column(name="reported_by")
	private int reportedBy;

	private int status;

	private String url;

	@Column(name="user_agent")
	private String userAgent;

	public BugReport() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Timestamp getCreatedOn() {
		return this.createdOn;
	}

	public void setCreatedOn(Timestamp createdOn) {
		this.createdOn = createdOn;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDiscovered() {
		return this.discovered;
	}

	public void setDiscovered(Date discovered) {
		this.discovered = discovered;
	}

	public Date getFixBy() {
		return this.fixBy;
	}

	public void setFixBy(Date fixBy) {
		this.fixBy = fixBy;
	}

	public String getNotes() {
		return this.notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public int getPriority() {
		return this.priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public int getReportedBy() {
		return this.reportedBy;
	}

	public void setReportedBy(int reportedBy) {
		this.reportedBy = reportedBy;
	}

	public int getStatus() {
		return this.status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUserAgent() {
		return this.userAgent;
	}

	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}

}