/**
 * htamada
 */
package com.agg.application.vo;

/**
 * @author htamada
 *
 */
public class ClaimPartVO {
	private int id;
	private String partNo;
	private String partDescr;
	private double qty;
	private double unitPrice;
	/**
	 * 
	 */
	public ClaimPartVO() {
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
	
	
}
