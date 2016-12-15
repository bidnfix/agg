package com.agg.application.vo;

public class AdjudicateClaimFormVO {
	private int id;
	private int totalAdjustmentLaborsCost;
	private int totalAdjustmentPartsCost;
	public int getTotalAdjustmentPartsCost() {
		return totalAdjustmentPartsCost;
	}
	public void setTotalAdjustmentPartsCost(int totalAdjustmentPartsCost) {
		this.totalAdjustmentPartsCost = totalAdjustmentPartsCost;
	}
	private int requestedOtherCharges1;
	private int requestedOtherCharges2;
	private double customerOwes;
	private double tra;
	private String extComment;
	public AdjudicateClaimFormVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getId() {
		return id;
	}
	/**
	 * @return the totalAdjustmentLaborsCost
	 */
	public int getTotalAdjustmentLaborsCost() {
		return totalAdjustmentLaborsCost;
	}
	/**
	 * @param totalAdjustmentLaborsCost the totalAdjustmentLaborsCost to set
	 */
	public void setTotalAdjustmentLaborsCost(int totalAdjustmentLaborsCost) {
		this.totalAdjustmentLaborsCost = totalAdjustmentLaborsCost;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getRequestedOtherCharges1() {
		return requestedOtherCharges1;
	}
	public void setRequestedOtherCharges1(int requestedOtherCharges1) {
		this.requestedOtherCharges1 = requestedOtherCharges1;
	}
	public int getRequestedOtherCharges2() {
		return requestedOtherCharges2;
	}
	public void setRequestedOtherCharges2(int requestedOtherCharges2) {
		this.requestedOtherCharges2 = requestedOtherCharges2;
	}
	public double getCustomerOwes() {
		return customerOwes;
	}
	public void setCustomerOwes(double customerOwes) {
		this.customerOwes = customerOwes;
	}
	public double getTra() {
		return tra;
	}
	public void setTra(double tra) {
		this.tra = tra;
	}
	public String getExtComment() {
		return extComment;
	}
	public void setExtComment(String extComment) {
		this.extComment = extComment;
	}
}
