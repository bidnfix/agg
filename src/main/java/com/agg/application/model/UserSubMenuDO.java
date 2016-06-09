package com.agg.application.model;

public class UserSubMenuDO implements Comparable<UserSubMenuDO> {
	
	private long id;
	
	private String name;
	
	private String url;

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

	@Override
	public int compareTo(UserSubMenuDO userSubMenuDO) {
		return Long.valueOf(this.id - userSubMenuDO.id).intValue();
	}
	
}
