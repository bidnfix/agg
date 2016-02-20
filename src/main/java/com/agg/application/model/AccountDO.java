package com.agg.application.model;

public class AccountDO {
	
	private String username;
	
	private byte status;
	
	private String roleName;

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the status
	 */
	public byte getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(byte status) {
		this.status = status;
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
	
}
