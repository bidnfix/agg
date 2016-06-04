package com.agg.application.model;

import java.util.List;
import java.util.Set;

public class AccountDO {
	
	private String username;
	
	private byte status;
	
	private String roleName;
	
	private String firstName;
	
	private String lastName;
	
	private List<UserMenuDO> userMenuDOList = null;

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
	 * @return the userMenuDOList
	 */
	public List<UserMenuDO> getUserMenuDOList() {
		return userMenuDOList;
	}

	/**
	 * @param userMenuDOList the userMenuDOList to set
	 */
	public void setUserMenuDOList(List<UserMenuDO> userMenuDOList) {
		this.userMenuDOList = userMenuDOList;
	}

}
