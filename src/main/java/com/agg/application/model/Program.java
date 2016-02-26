package com.agg.application.model;

import java.sql.Timestamp;

public class Program {
	
	private int id;
	
	private String name;
	
	private String description;

	private int hours;
	
	private int term;

	private String type;

	private int cost;

	private String group;

	private byte isActive;

	private double prLol;

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
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
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the hours
	 */
	public int getHours() {
		return hours;
	}

	/**
	 * @param hours the hours to set
	 */
	public void setHours(int hours) {
		this.hours = hours;
	}

	/**
	 * @return the term
	 */
	public int getTerm() {
		return term;
	}

	/**
	 * @param term the term to set
	 */
	public void setTerm(int term) {
		this.term = term;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the cost
	 */
	public int getCost() {
		return cost;
	}

	/**
	 * @param cost the cost to set
	 */
	public void setCost(int cost) {
		this.cost = cost;
	}

	/**
	 * @return the group
	 */
	public String getGroup() {
		return group;
	}

	/**
	 * @param group the group to set
	 */
	public void setGroup(String group) {
		this.group = group;
	}

	/**
	 * @return the isActive
	 */
	public byte getIsActive() {
		return isActive;
	}

	/**
	 * @param isActive the isActive to set
	 */
	public void setIsActive(byte isActive) {
		this.isActive = isActive;
	}

	/**
	 * @return the prLol
	 */
	public double getPrLol() {
		return prLol;
	}

	/**
	 * @param prLol the prLol to set
	 */
	public void setPrLol(double prLol) {
		this.prLol = prLol;
	}

}
