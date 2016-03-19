package com.agg.application.entity;

import java.io.Serializable;

import javax.persistence.*;

import java.sql.Timestamp;


/**
 * The persistent class for the locations database table.
 * 
 */
@Entity
@Table(name="locations")
@NamedQuery(name="Location.findAll", query="SELECT l FROM Location l")
public class Location implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name="l_id")
	private int lId;

	@Column(name="created_by")
	private int createdBy;

	@Column(name="is_head")
	private byte isHead;

	@Column(name="l_address")
	private String lAddress;

	@Column(name="l_city")
	private String lCity;

	@Column(name="l_country")
	private String lCountry;

	@Column(name="l_email")
	private String lEmail;

	@Column(name="l_is_archived")
	private byte lIsArchived;
	
	@Column(name="l_last_update")
	private Timestamp lLastUpdate;

	@Column(name="l_parent")
	private int lParent;

	@Column(name="l_phone")
	private String lPhone;

	@Column(name="l_state")
	private String lState;

	@Column(name="l_title")
	private String lTitle;

	@Column(name="l_url")
	private String lUrl;

	@Column(name="l_zip")
	private String lZip;

	public Location() {
	}

	public int getLId() {
		return this.lId;
	}

	public void setLId(int lId) {
		this.lId = lId;
	}

	public int getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}

	public byte getIsHead() {
		return this.isHead;
	}

	public void setIsHead(byte isHead) {
		this.isHead = isHead;
	}

	public String getLAddress() {
		return this.lAddress;
	}

	public void setLAddress(String lAddress) {
		this.lAddress = lAddress;
	}

	public String getLCity() {
		return this.lCity;
	}

	public void setLCity(String lCity) {
		this.lCity = lCity;
	}

	public String getLCountry() {
		return this.lCountry;
	}

	public void setLCountry(String lCountry) {
		this.lCountry = lCountry;
	}

	public String getLEmail() {
		return this.lEmail;
	}

	public void setLEmail(String lEmail) {
		this.lEmail = lEmail;
	}

	public byte getLIsArchived() {
		return this.lIsArchived;
	}

	public void setLIsArchived(byte lIsArchived) {
		this.lIsArchived = lIsArchived;
	}

	public Timestamp getLLastUpdate() {
		return this.lLastUpdate;
	}

	public void setLLastUpdate(Timestamp lLastUpdate) {
		this.lLastUpdate = lLastUpdate;
	}

	public int getLParent() {
		return this.lParent;
	}

	public void setLParent(int lParent) {
		this.lParent = lParent;
	}

	public String getLPhone() {
		return this.lPhone;
	}

	public void setLPhone(String lPhone) {
		this.lPhone = lPhone;
	}

	public String getLState() {
		return this.lState;
	}

	public void setLState(String lState) {
		this.lState = lState;
	}

	public String getLTitle() {
		return this.lTitle;
	}

	public void setLTitle(String lTitle) {
		this.lTitle = lTitle;
	}

	public String getLUrl() {
		return this.lUrl;
	}

	public void setLUrl(String lUrl) {
		this.lUrl = lUrl;
	}

	public String getLZip() {
		return this.lZip;
	}

	public void setLZip(String lZip) {
		this.lZip = lZip;
	}

}