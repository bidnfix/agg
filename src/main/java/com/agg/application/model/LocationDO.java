package com.agg.application.model;

public class LocationDO {
	
	private long id;
	
	private boolean headQuarter;
	
	private DealerDO dealerDO;
	
	private String title;
	
	private String address1;
	
	private String address2;
	
	private String city;
	
	private String state;
	
	private String zip;
	
	private String email;
	
	private String phone;
	
	private String locationUrl;
	

	/**
	 * @return the headQuarter
	 */
	public boolean isHeadQuarter() {
		return headQuarter;
	}

	/**
	 * @param headQuarter the headQuarter to set
	 */
	public void setHeadQuarter(boolean headQuarter) {
		this.headQuarter = headQuarter;
	}

	/**
	 * @return the dealerDO
	 */
	public DealerDO getDealerDO() {
		return dealerDO;
	}

	/**
	 * @param dealerDO the dealerDO to set
	 */
	public void setDealerDO(DealerDO dealerDO) {
		this.dealerDO = dealerDO;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the address1
	 */
	public String getAddress1() {
		return address1;
	}

	/**
	 * @param address1 the address1 to set
	 */
	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	/**
	 * @return the address2
	 */
	public String getAddress2() {
		return address2;
	}

	/**
	 * @param address2 the address2 to set
	 */
	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * @return the zip
	 */
	public String getZip() {
		return zip;
	}

	/**
	 * @param zip the zip to set
	 */
	public void setZip(String zip) {
		this.zip = zip;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * @param phone the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * @return the locationUrl
	 */
	public String getLocationUrl() {
		return locationUrl;
	}

	/**
	 * @param locationUrl the locationUrl to set
	 */
	public void setLocationUrl(String locationUrl) {
		this.locationUrl = locationUrl;
	}

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}
	
}
