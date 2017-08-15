package com.agg.application.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the admin_adjustments database table.
 * 
 */
@Entity
@Table(name="admin_adjustments")
@NamedQuery(name="AdminAdjustment.findAll", query="SELECT a FROM AdminAdjustment a")
public class AdminAdjustment implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="quote_id")
	private String quoteId;

	@Column(name="base_price")
	private double basePrice;

	@Lob
	@Column(name="c_conditions")
	private String cConditions;

	@Column(name="contract_cost")
	private double contractCost;

	@Column(name="contract_ver")
	private String contractVer;

	@Lob
	@Column(name="deal_history")
	private String dealHistory;

	@Temporal(TemporalType.DATE)
	@Column(name="expiration_date")
	private Date expirationDate;

	@Column(name="expiration_hours")
	private int expirationHours;

	@Temporal(TemporalType.DATE)
	@Column(name="inception_date")
	private Date inceptionDate;

	@Temporal(TemporalType.DATE)
	@Column(name="invoice_date")
	private Date invoiceDate;

	@Column(name="invoice_ver")
	private String invoiceVer;

	@Temporal(TemporalType.DATE)
	@Column(name="last_update")
	private Date lastUpdate;

	private double lol;

	@Lob
	@Column(name="special_consideration")
	private String specialConsideration;
	
	@Column(name="coverage_hours")
	private int coverageHours;

	@Column(name="coverage_term")
	private int coverageTerm;
	
	@Column(name="coverage_type")
	private String coverageType;
	
	@Column(name="dealer_adj_factor")
	private double dealerAdjFactor;
	
	@Column(name="uoe_adj_factor")
	private double uoeAdjFactor;
	
	@Column(name="machine_adj_factor")
	private double machineAdjFactor;
	
	@Column(name="usage_tier_adj_factor")
	private double usageTierAdjFactor;
	
	@Column(name="oem_warranty_adj_factor")
	private double oemWarrantyAdjFactor;

	public AdminAdjustment() {
	}

	public String getQuoteId() {
		return this.quoteId;
	}

	public void setQuoteId(String quoteId) {
		this.quoteId = quoteId;
	}

	/**
	 * @return the basePrice
	 */
	public double getBasePrice() {
		return basePrice;
	}

	/**
	 * @param basePrice the basePrice to set
	 */
	public void setBasePrice(double basePrice) {
		this.basePrice = basePrice;
	}

	public String getCConditions() {
		return this.cConditions;
	}

	public void setCConditions(String cConditions) {
		this.cConditions = cConditions;
	}

	public double getContractCost() {
		return this.contractCost;
	}

	public void setContractCost(double contractCost) {
		this.contractCost = contractCost;
	}

	public String getContractVer() {
		return this.contractVer;
	}

	public void setContractVer(String contractVer) {
		this.contractVer = contractVer;
	}

	public String getDealHistory() {
		return this.dealHistory;
	}

	public void setDealHistory(String dealHistory) {
		this.dealHistory = dealHistory;
	}

	public Date getExpirationDate() {
		return this.expirationDate;
	}

	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}

	public int getExpirationHours() {
		return this.expirationHours;
	}

	public void setExpirationHours(int expirationHours) {
		this.expirationHours = expirationHours;
	}

	public Date getInceptionDate() {
		return this.inceptionDate;
	}

	public void setInceptionDate(Date inceptionDate) {
		this.inceptionDate = inceptionDate;
	}

	public Date getInvoiceDate() {
		return this.invoiceDate;
	}

	public void setInvoiceDate(Date invoiceDate) {
		this.invoiceDate = invoiceDate;
	}

	public String getInvoiceVer() {
		return this.invoiceVer;
	}

	public void setInvoiceVer(String invoiceVer) {
		this.invoiceVer = invoiceVer;
	}

	public Date getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public double getLol() {
		return this.lol;
	}

	public void setLol(double lol) {
		this.lol = lol;
	}

	public String getSpecialConsideration() {
		return this.specialConsideration;
	}

	public void setSpecialConsideration(String specialConsideration) {
		this.specialConsideration = specialConsideration;
	}

	public int getCoverageHours() {
		return coverageHours;
	}

	public void setCoverageHours(int coverageHours) {
		this.coverageHours = coverageHours;
	}

	public int getCoverageTerm() {
		return coverageTerm;
	}

	public void setCoverageTerm(int coverageTerm) {
		this.coverageTerm = coverageTerm;
	}

	public String getCoverageType() {
		return coverageType;
	}

	public void setCoverageType(String coverageType) {
		this.coverageType = coverageType;
	}

	public double getDealerAdjFactor() {
		return dealerAdjFactor;
	}

	public void setDealerAdjFactor(double dealerAdjFactor) {
		this.dealerAdjFactor = dealerAdjFactor;
	}

	public double getUoeAdjFactor() {
		return uoeAdjFactor;
	}

	public void setUoeAdjFactor(double uoeAdjFactor) {
		this.uoeAdjFactor = uoeAdjFactor;
	}

	public double getMachineAdjFactor() {
		return machineAdjFactor;
	}

	public void setMachineAdjFactor(double machineAdjFactor) {
		this.machineAdjFactor = machineAdjFactor;
	}

	public double getUsageTierAdjFactor() {
		return usageTierAdjFactor;
	}

	public void setUsageTierAdjFactor(double usageTierAdjFactor) {
		this.usageTierAdjFactor = usageTierAdjFactor;
	}

	public double getOemWarrantyAdjFactor() {
		return oemWarrantyAdjFactor;
	}

	public void setOemWarrantyAdjFactor(double oemWarrantyAdjFactor) {
		this.oemWarrantyAdjFactor = oemWarrantyAdjFactor;
	}

}