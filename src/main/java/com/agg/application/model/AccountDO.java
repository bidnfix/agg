package com.agg.application.model;

import java.util.List;

public class AccountDO {
	
	private long id;
	
	private String username;
	
	private int status;
	
	private String roleName;
	
	private String firstName;
	
	private String lastName;
	
	private RoleDO roleDO;
	
	private long dealerId;
	
	private List<UserMenuDO> userMenuDOList = null;
	
	
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

	public long getDealerId() {
		return dealerId;
	}

	public void setDealerId(long dealerId) {
		this.dealerId = dealerId;
	}
	
}
