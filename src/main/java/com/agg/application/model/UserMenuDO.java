package com.agg.application.model;

import java.util.Set;

public class UserMenuDO {

	private int id;
	
	private String name;
	
	private String url;
	
	private Set<UserSubMenuDO> userSubMenuDOSet = null;

	public int getId() {
		return id;
	}

	public void setId(int id) {
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

	public Set<UserSubMenuDO> getUserSubMenuDOSet() {
		return userSubMenuDOSet;
	}

	public void setUserSubMenuDOSet(Set<UserSubMenuDO> userSubMenuDOSet) {
		this.userSubMenuDOSet = userSubMenuDOSet;
	}
	
}
