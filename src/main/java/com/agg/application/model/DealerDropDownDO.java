package com.agg.application.model;

public class DealerDropDownDO {
	
	public DealerDropDownDO(long id, String name, long code, String city, long parentCode){
		this.id = id;
		this.name = name;
		this.code = code;
		this.city = city;
		this.parentCode = parentCode;
	}

	private long id;
	
	private String name;
	
	private long code;
	
	private String city;
	
	private long parentCode;

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

	public long getCode() {
		return code;
	}

	public void setCode(long code) {
		this.code = code;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public long getParentCode() {
		return parentCode;
	}

	public void setParentCode(long parentCode) {
		this.parentCode = parentCode;
	}
	
}
