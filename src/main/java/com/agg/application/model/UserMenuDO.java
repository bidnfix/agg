package com.agg.application.model;

import java.util.List;

public class UserMenuDO implements Comparable<UserMenuDO>{

	private long id;
	
	private String name;
	
	private String url;
	
	private List<UserSubMenuDO> userSubMenuDOList = null;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @return the userSubMenuDOList
	 */
	public List<UserSubMenuDO> getUserSubMenuDOList() {
		return userSubMenuDOList;
	}

	/**
	 * @param userSubMenuDOList the userSubMenuDOList to set
	 */
	public void setUserSubMenuDOList(List<UserSubMenuDO> userSubMenuDOList) {
		this.userSubMenuDOList = userSubMenuDOList;
	}

	@Override
	public int compareTo(UserMenuDO userMenuDO) {
		return Long.valueOf(this.id - userMenuDO.id).intValue();
	}
	
}
