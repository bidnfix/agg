package com.agg.application.vo;

public class AdjudicateClaimFormVO {
	private int id;
	private int totalAdjustmentLaborsCost;
	private int totalAdjustmentPartsCost;
	private int requestedOtherCharges1;
	private int requestedOtherCharges2;
	private double customerOwes;
	private double tra;
	private String extComment;
	private int approvedOtherCharges1;
	private int approvedOtherCharges2;
	
	
	public AdjudicateClaimFormVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public int getTotalAdjustmentPartsCost() {
		return totalAdjustmentPartsCost;
	}
	public void setTotalAdjustmentPartsCost(int totalAdjustmentPartsCost) {
		this.totalAdjustmentPartsCost = totalAdjustmentPartsCost;
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

	/**
	 * @return the approvedOtherCharges1
	 */
	public int getApprovedOtherCharges1() {
		return approvedOtherCharges1;
	}

	/**
	 * @param approvedOtherCharges1 the approvedOtherCharges1 to set
	 */
	public void setApprovedOtherCharges1(int approvedOtherCharges1) {
		this.approvedOtherCharges1 = approvedOtherCharges1;
	}

	/**
	 * @return the approvedOtherCharges2
	 */
	public int getApprovedOtherCharges2() {
		return approvedOtherCharges2;
	}

	/**
	 * @param approvedOtherCharges2 the approvedOtherCharges2 to set
	 */
	public void setApprovedOtherCharges2(int approvedOtherCharges2) {
		this.approvedOtherCharges2 = approvedOtherCharges2;
	}
	
}
