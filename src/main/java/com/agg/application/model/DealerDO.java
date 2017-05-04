package com.agg.application.model;

public class DealerDO {
	
	private long id;
	
	private String name;
	
	private long code;

	private String userName;
	
	private String password;
	
	private String firstName;
	
	private String lastName;
	
	private String address1;
	
	private String address2;
	
	private String city;
	
	private String state;
	
	private String marketEmail;
	
	private String invoiceEmail;
	
	private String phone;
	
	private String dealerUrl;
	
	private RoleDO roleDO;
	
	private String notes;
	
	private String roleName;
	
	private String zip;
	
	private int status;
	
	private long parentCode;
	
	private DealerDO parentDealerDO;
	
	public DealerDO(){
		
	}
	
	public DealerDO(long id, long code, String name, String state, String marketEmail, String invoiceEmail, String phone, long parentCode, String rTitle, int status){
		this.id = id;
		this.code = code;
		this.name = name;
		this.state = state;
		this.marketEmail = marketEmail;
		this.invoiceEmail = invoiceEmail;
		this.phone = phone;
		this.parentCode = parentCode;
		this.roleName = rTitle;
		this.status = status;
	}
	
	public DealerDO(long id, long code, String name, String state, String marketEmail, String invoiceEmail, String phone, long parentCode, int status){
		this.id = id;
		this.code = code;
		this.name = name;
		this.state = state;
		this.marketEmail = marketEmail;
		this.invoiceEmail = invoiceEmail;
		this.phone = phone;
		this.parentCode = parentCode;
		this.status = status;
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

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
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
	 * @return the marketEmail
	 */
	public String getMarketEmail() {
		return marketEmail;
	}

	/**
	 * @param marketEmail the marketEmail to set
	 */
	public void setMarketEmail(String marketEmail) {
		this.marketEmail = marketEmail;
	}

	/**
	 * @return the invoiceEmail
	 */
	public String getInvoiceEmail() {
		return invoiceEmail;
	}

	/**
	 * @param invoiceEmail the invoiceEmail to set
	 */
	public void setInvoiceEmail(String invoiceEmail) {
		this.invoiceEmail = invoiceEmail;
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
	 * @return the dealerUrl
	 */
	public String getDealerUrl() {
		return dealerUrl;
	}

	/**
	 * @param dealerUrl the dealerUrl to set
	 */
	public void setDealerUrl(String dealerUrl) {
		this.dealerUrl = dealerUrl;
	}

	/**
	 * @return the roleDO
	 */
	public RoleDO getRoleDO() {
		return roleDO;
	}

	/**
	 * @param roleDO the roleDO to set
	 */
	public void setRoleDO(RoleDO roleDO) {
		this.roleDO = roleDO;
	}

	/**
	 * @return the notes
	 */
	public String getNotes() {
		return notes;
	}

	/**
	 * @param notes the notes to set
	 */
	public void setNotes(String notes) {
		this.notes = notes;
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
	 * @return the status
	 */
	public int getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(int status) {
		this.status = status;
	}

	/**
	 * @return the code
	 */
	public long getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(long code) {
		this.code = code;
	}

	/**
	 * @return the roleName
	 */
	public String getRoleName() {
		return roleName;
	}

	/**
	 * @param roleName the roleName to set
	 */
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	/**
	 * @return the parentCode
	 */
	public long getParentCode() {
		return parentCode;
	}

	/**
	 * @param parentCode the parentCode to set
	 */
	public void setParentCode(long parentCode) {
		this.parentCode = parentCode;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the parentDealerDO
	 */
	public DealerDO getParentDealerDO() {
		return parentDealerDO;
	}

	/**
	 * @param parentDealerDO the parentDealerDO to set
	 */
	public void setParentDealerDO(DealerDO parentDealerDO) {
		this.parentDealerDO = parentDealerDO;
	}
	
}
