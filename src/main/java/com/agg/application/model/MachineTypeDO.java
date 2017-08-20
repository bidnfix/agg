package com.agg.application.model;

public class MachineTypeDO {
	
	private int id;
	
	private String name;
	
	public MachineTypeDO(){
		
	}
	
	public MachineTypeDO(long id, String name){
		this.id = Integer.valueOf(String.valueOf(id));
		this.name = name;
	}
	
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
	
}
