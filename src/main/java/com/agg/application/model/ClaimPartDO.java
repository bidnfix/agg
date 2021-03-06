/**
 * htamada
 */
package com.agg.application.model;

/**
 * @author htamada
 *
 */
public class ClaimPartDO {
	private int id;
	private int claimId;
	private String partNo;
	private String partDescr;
	private double qty;
	private double unitPrice;
	private String partTotal;
	private double adjQty;
	private double adjUnitPrice;
	private String partAdjTotal;
	
	/**
	 * 
	 */
	public ClaimPartDO() {
		super();
		// TODO Auto-generated constructor stub
	}
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
	 * @return the claimId
	 */
	public int getClaimId() {
		return claimId;
	}
	/**
	 * @param claimId the claimId to set
	 */
	public void setClaimId(int claimId) {
		this.claimId = claimId;
	}
	/**
	 * @return the partNo
	 */
	public String getPartNo() {
		return partNo;
	}
	/**
	 * @param partNo the partNo to set
	 */
	public void setPartNo(String partNo) {
		this.partNo = partNo;
	}
	/**
	 * @return the partDescr
	 */
	public String getPartDescr() {
		return partDescr;
	}
	/**
	 * @param partDescr the partDescr to set
	 */
	public void setPartDescr(String partDescr) {
		this.partDescr = partDescr;
	}
	
	/**
	 * @return the qty
	 */
	public double getQty() {
		return qty;
	}
	/**
	 * @param qty the qty to set
	 */
	public void setQty(double qty) {
		this.qty = qty;
	}
	/**
	 * @return the unitPrice
	 */
	public double getUnitPrice() {
		return unitPrice;
	}
	/**
	 * @param unitPrice the unitPrice to set
	 */
	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}
	public String getPartTotal() {
		return partTotal;
	}
	public void setPartTotal(String partTotal) {
		this.partTotal = partTotal;
	}
	public double getAdjQty() {
		return adjQty;
	}
	public void setAdjQty(double adjQty) {
		this.adjQty = adjQty;
	}
	public double getAdjUnitPrice() {
		return adjUnitPrice;
	}
	public void setAdjUnitPrice(double adjUnitPrice) {
		this.adjUnitPrice = adjUnitPrice;
	}
	public String getPartAdjTotal() {
		return partAdjTotal;
	}
	public void setPartAdjTotal(String partAdjTotal) {
		this.partAdjTotal = partAdjTotal;
	}
	
}
