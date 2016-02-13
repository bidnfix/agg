package com.agg.application.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the user_track database table.
 * 
 */
@Entity
@Table(name="user_track")
@NamedQuery(name="UserTrack.findAll", query="SELECT u FROM UserTrack u")
public class UserTrack implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	@Column(name="area_code")
	private String areaCode;

	private String city;

	@Column(name="complete_address")
	private String completeAddress;

	@Column(name="country_code")
	private String countryCode;

	@Column(name="country_code3")
	private String countryCode3;

	@Column(name="country_name")
	private String countryName;

	private int distance;

	@Column(name="dma_code")
	private String dmaCode;

	@Column(name="last_update")
	private Timestamp lastUpdate;

	private String latitude;

	private String longitude;

	@Column(name="postal_code")
	private String postalCode;

	private String region;

	@Column(name="user_id")
	private int userId;

	@Column(name="user_ip")
	private String userIp;

	public UserTrack() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAreaCode() {
		return this.areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCompleteAddress() {
		return this.completeAddress;
	}

	public void setCompleteAddress(String completeAddress) {
		this.completeAddress = completeAddress;
	}

	public String getCountryCode() {
		return this.countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getCountryCode3() {
		return this.countryCode3;
	}

	public void setCountryCode3(String countryCode3) {
		this.countryCode3 = countryCode3;
	}

	public String getCountryName() {
		return this.countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public int getDistance() {
		return this.distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}

	public String getDmaCode() {
		return this.dmaCode;
	}

	public void setDmaCode(String dmaCode) {
		this.dmaCode = dmaCode;
	}

	public Timestamp getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public String getLatitude() {
		return this.latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return this.longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getPostalCode() {
		return this.postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getRegion() {
		return this.region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public int getUserId() {
		return this.userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserIp() {
		return this.userIp;
	}

	public void setUserIp(String userIp) {
		this.userIp = userIp;
	}

}