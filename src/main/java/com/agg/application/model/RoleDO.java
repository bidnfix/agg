package com.agg.application.model;

public class RoleDO {
	
	private long id;
	
	private String name;
	
	private long accountTypeId;

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
	 * @return the accountTypeId
	 */
	public long getAccountTypeId() {
		return accountTypeId;
	}

	/**
	 * @param accountTypeId the accountTypeId to set
	 */
	public void setAccountTypeId(long accountTypeId) {
		this.accountTypeId = accountTypeId;
	}
	
	
}
