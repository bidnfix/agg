package com.agg.application.vo;

import java.util.Date;
import java.util.List;

import com.agg.application.model.CheckDO;

public class AdjudicateClaimFormVO {
	private int id;
	private double totalAdjustmentLaborsCost;
	private double totalAdjustmentPartsCost;
	private double requestedOtherCharges1;
	private double requestedOtherCharges2;
	private double customerOwes;
	private double tra;
	private String extComment;
	private double approvedOtherCharges1;
	private double approvedOtherCharges2;
	private String cheqNo;
	private Date paidDate;
	private List<CheckDO> checkDOList = null;
	
	
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
	 * @return the totalAdjustmentLaborsCost
	 */
	public double getTotalAdjustmentLaborsCost() {
		return totalAdjustmentLaborsCost;
	}

	/**
	 * @param totalAdjustmentLaborsCost the totalAdjustmentLaborsCost to set
	 */
	public void setTotalAdjustmentLaborsCost(double totalAdjustmentLaborsCost) {
		this.totalAdjustmentLaborsCost = totalAdjustmentLaborsCost;
	}

	/**
	 * @return the totalAdjustmentPartsCost
	 */
	public double getTotalAdjustmentPartsCost() {
		return totalAdjustmentPartsCost;
	}

	/**
	 * @param totalAdjustmentPartsCost the totalAdjustmentPartsCost to set
	 */
	public void setTotalAdjustmentPartsCost(double totalAdjustmentPartsCost) {
		this.totalAdjustmentPartsCost = totalAdjustmentPartsCost;
	}

	/**
	 * @return the requestedOtherCharges1
	 */
	public double getRequestedOtherCharges1() {
		return requestedOtherCharges1;
	}

	/**
	 * @param requestedOtherCharges1 the requestedOtherCharges1 to set
	 */
	public void setRequestedOtherCharges1(double requestedOtherCharges1) {
		this.requestedOtherCharges1 = requestedOtherCharges1;
	}

	/**
	 * @return the requestedOtherCharges2
	 */
	public double getRequestedOtherCharges2() {
		return requestedOtherCharges2;
	}

	/**
	 * @param requestedOtherCharges2 the requestedOtherCharges2 to set
	 */
	public void setRequestedOtherCharges2(double requestedOtherCharges2) {
		this.requestedOtherCharges2 = requestedOtherCharges2;
	}

	/**
	 * @return the approvedOtherCharges1
	 */
	public double getApprovedOtherCharges1() {
		return approvedOtherCharges1;
	}

	/**
	 * @param approvedOtherCharges1 the approvedOtherCharges1 to set
	 */
	public void setApprovedOtherCharges1(double approvedOtherCharges1) {
		this.approvedOtherCharges1 = approvedOtherCharges1;
	}

	/**
	 * @return the approvedOtherCharges2
	 */
	public double getApprovedOtherCharges2() {
		return approvedOtherCharges2;
	}

	/**
	 * @param approvedOtherCharges2 the approvedOtherCharges2 to set
	 */
	public void setApprovedOtherCharges2(double approvedOtherCharges2) {
		this.approvedOtherCharges2 = approvedOtherCharges2;
	}

	public String getCheqNo() {
		return cheqNo;
	}

	public void setCheqNo(String cheqNo) {
		this.cheqNo = cheqNo;
	}

	public Date getPaidDate() {
		return paidDate;
	}

	public void setPaidDate(Date paidDate) {
		this.paidDate = paidDate;
	}

	public List<CheckDO> getCheckDOList() {
		return checkDOList;
	}

	public void setCheckDOList(List<CheckDO> checkDOList) {
		this.checkDOList = checkDOList;
	}
	
}
