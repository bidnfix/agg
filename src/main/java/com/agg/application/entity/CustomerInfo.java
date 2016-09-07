package com.agg.application.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the customer_info database table.
 * 
 */
@Entity
@Table(name="customer_info")
@NamedQuery(name="CustomerInfo.findAll", query="SELECT c FROM CustomerInfo c")
public class CustomerInfo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="quote_id")
	private String quoteId;

	private String address;

	private String city;

	private String email;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="last_update")
	private Date lastUpdate;

	private String name;

	private String phone;

	private byte remorse;

	private String state;

	private byte understand;

	private String zip;

	public CustomerInfo() {
	}

	public String getQuoteId() {
		return this.quoteId;
	}

	public void setQuoteId(String quoteId) {
		this.quoteId = quoteId;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public byte getRemorse() {
		return this.remorse;
	}

	public void setRemorse(byte remorse) {
		this.remorse = remorse;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public byte getUnderstand() {
		return this.understand;
	}

	public void setUnderstand(byte understand) {
		this.understand = understand;
	}

	public String getZip() {
		return this.zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

}