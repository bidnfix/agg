package com.agg.application.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the notifications database table.
 * 
 */
@Entity
@Table(name="notifications")
@NamedQuery(name="Notification.findAll", query="SELECT n FROM Notification n")
public class Notification implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	@Lob
	private String content;

	@Column(name="is_archive")
	private int isArchive;

	@Column(name="last_update")
	private Timestamp lastUpdate;

	@Column(name="notfication_type")
	private int notficationType;

	private int status;

	private String subject;

	//bi-directional many-to-one association to Dealer
	@ManyToOne
	private Dealer dealer;

	public Notification() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getIsArchive() {
		return this.isArchive;
	}

	public void setIsArchive(int isArchive) {
		this.isArchive = isArchive;
	}

	public Timestamp getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public int getNotficationType() {
		return this.notficationType;
	}

	public void setNotficationType(int notficationType) {
		this.notficationType = notficationType;
	}

	public int getStatus() {
		return this.status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getSubject() {
		return this.subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public Dealer getDealer() {
		return this.dealer;
	}

	public void setDealer(Dealer dealer) {
		this.dealer = dealer;
	}

}