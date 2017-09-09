package com.agg.application.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the use_of_equip database table.
 * 
 */
@Entity
@Table(name="use_of_equip")
@NamedQuery(name="UseOfEquip.findAll", query="SELECT u FROM UseOfEquip u")
public class UseOfEquip implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long id;

	@Column(name="equip_name")
	private String equipName;
	
	@Column(name="discount")
	private double discount;

	public UseOfEquip() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getEquipName() {
		return this.equipName;
	}

	public void setEquipName(String equipName) {
		this.equipName = equipName;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

}