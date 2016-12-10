package com.agg.application.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the pricing database table.
 * 
 */
@Entity
@Table(name="pricing")
@NamedQuery(name="Pricing.findAll", query="SELECT p FROM Pricing p")
public class Pricing implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private PricingPK id;

	@Column(name="ph_base_price")
	private int phBasePrice;

	@Column(name="ph_national_price")
	private double phNationalPrice;

	@Column(name="pl_base_price")
	private int plBasePrice;

	@Column(name="pt_base_price")
	private int ptBasePrice;

	@Column(name="pt_national_price")
	private double ptNationalPrice;

	public Pricing() {
	}

	public PricingPK getId() {
		return this.id;
	}

	public void setId(PricingPK id) {
		this.id = id;
	}

	public int getPhBasePrice() {
		return this.phBasePrice;
	}

	public void setPhBasePrice(int phBasePrice) {
		this.phBasePrice = phBasePrice;
	}

	public double getPhNationalPrice() {
		return this.phNationalPrice;
	}

	public void setPhNationalPrice(double phNationalPrice) {
		this.phNationalPrice = phNationalPrice;
	}

	public int getPlBasePrice() {
		return this.plBasePrice;
	}

	public void setPlBasePrice(int plBasePrice) {
		this.plBasePrice = plBasePrice;
	}

	public int getPtBasePrice() {
		return this.ptBasePrice;
	}

	public void setPtBasePrice(int ptBasePrice) {
		this.ptBasePrice = ptBasePrice;
	}

	public double getPtNationalPrice() {
		return this.ptNationalPrice;
	}

	public void setPtNationalPrice(double ptNationalPrice) {
		this.ptNationalPrice = ptNationalPrice;
	}

}