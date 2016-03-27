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
	private long lId;

	@Column(name="created_by")
	private String createdBy;

	@Column(name="is_head")
	private byte isHead;

	@Column(name="l_address1")
	private String lAddress1;
	
	@Column(name="l_address2")
	private String lAddress2;

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
	
	@ManyToOne
	@JoinColumn(name="dealer_id")
	private Dealer dealer;

	public Location() {
	}

	public long getLId() {
		return this.lId;
	}

	public void setLId(long lId) {
		this.lId = lId;
	}

	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public byte getIsHead() {
		return this.isHead;
	}

	public void setIsHead(byte isHead) {
		this.isHead = isHead;
	}

	/**
	 * @return the lAddress1
	 */
	public String getlAddress1() {
		return lAddress1;
	}

	/**
	 * @param lAddress1 the lAddress1 to set
	 */
	public void setlAddress1(String lAddress1) {
		this.lAddress1 = lAddress1;
	}

	/**
	 * @return the lAddress2
	 */
	public String getlAddress2() {
		return lAddress2;
	}

	/**
	 * @param lAddress2 the lAddress2 to set
	 */
	public void setlAddress2(String lAddress2) {
		this.lAddress2 = lAddress2;
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

	/**
	 * @return the dealer
	 */
	public Dealer getDealer() {
		return dealer;
	}

	/**
	 * @param dealer the dealer to set
	 */
	public void setDealer(Dealer dealer) {
		this.dealer = dealer;
	}
	
}