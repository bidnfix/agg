package com.agg.application.model;


import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.sql.Timestamp;



public class ClaimReportExcelDO {

	private double approvedOtherCharges1;

	private double approvedOtherCharges2;

	private String causeOfFailure;

	private String checkNumber;

	private String claimNumber;

	private String claimStatus;

	private String contractId;

	private String corrective_Action;

	private int createdBy;

	private Date createdDate;

	private String customerComplaint;

	private String dealerName;

	private Date failureDate;

	private int hoursAtBreakdown;

	private byte isArchived;

	private Timestamp lastUpdate;

	private Date paidDate;

	private Date reportedOn;

	private double requestedOtherCharges1;

	private double requestedOtherCharges2;

	private String serial_Number;

	private double totalAdjustedLaborCost;

	private double totalAdjustedPartsCost;

	private String updatedBy;

	private String workOrder;

	public ClaimReportExcelDO() {
	}

	public double getApprovedOtherCharges1() {
		return approvedOtherCharges1;
	}

	public void setApprovedOtherCharges1(double approvedOtherCharges1) {
		this.approvedOtherCharges1 = approvedOtherCharges1;
	}

	public double getApprovedOtherCharges2() {
		return approvedOtherCharges2;
	}

	public void setApprovedOtherCharges2(double approvedOtherCharges2) {
		this.approvedOtherCharges2 = approvedOtherCharges2;
	}

	public String getCauseOfFailure() {
		return causeOfFailure;
	}

	public void setCauseOfFailure(String causeOfFailure) {
		this.causeOfFailure = causeOfFailure;
	}

	public String getCheckNumber() {
		return checkNumber;
	}

	public void setCheckNumber(String checkNumber) {
		this.checkNumber = checkNumber;
	}

	public String getClaimNumber() {
		return claimNumber;
	}

	public void setClaimNumber(String claimNumber) {
		this.claimNumber = claimNumber;
	}

	public String getClaimStatus() {
		return claimStatus;
	}

	public void setClaimStatus(String claimStatus) {
		this.claimStatus = claimStatus;
	}

	public String getContractId() {
		return contractId;
	}

	public void setContractId(String contractId) {
		this.contractId = contractId;
	}

	public String getCorrective_Action() {
		return corrective_Action;
	}

	public void setCorrective_Action(String corrective_Action) {
		this.corrective_Action = corrective_Action;
	}

	public int getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getCustomerComplaint() {
		return customerComplaint;
	}

	public void setCustomerComplaint(String customerComplaint) {
		this.customerComplaint = customerComplaint;
	}

	public String getDealerName() {
		return dealerName;
	}

	public void setDealerName(String dealerName) {
		this.dealerName = dealerName;
	}

	public Date getFailureDate() {
		return failureDate;
	}

	public void setFailureDate(Date failureDate) {
		this.failureDate = failureDate;
	}

	public int getHoursAtBreakdown() {
		return hoursAtBreakdown;
	}

	public void setHoursAtBreakdown(int hoursAtBreakdown) {
		this.hoursAtBreakdown = hoursAtBreakdown;
	}

	public byte getIsArchived() {
		return isArchived;
	}

	public void setIsArchived(byte isArchived) {
		this.isArchived = isArchived;
	}

	public Timestamp getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public Date getPaidDate() {
		return paidDate;
	}

	public void setPaidDate(Date paidDate) {
		this.paidDate = paidDate;
	}

	public Date getReportedOn() {
		return reportedOn;
	}

	public void setReportedOn(Date reportedOn) {
		this.reportedOn = reportedOn;
	}

	public double getRequestedOtherCharges1() {
		return requestedOtherCharges1;
	}

	public void setRequestedOtherCharges1(double requestedOtherCharges1) {
		this.requestedOtherCharges1 = requestedOtherCharges1;
	}

	public double getRequestedOtherCharges2() {
		return requestedOtherCharges2;
	}

	public void setRequestedOtherCharges2(double requestedOtherCharges2) {
		this.requestedOtherCharges2 = requestedOtherCharges2;
	}

	public String getSerial_Number() {
		return serial_Number;
	}

	public void setSerial_Number(String serial_Number) {
		this.serial_Number = serial_Number;
	}

	public double getTotalAdjustedLaborCost() {
		return totalAdjustedLaborCost;
	}

	public void setTotalAdjustedLaborCost(double totalAdjustedLaborCost) {
		this.totalAdjustedLaborCost = totalAdjustedLaborCost;
	}

	public double getTotalAdjustedPartsCost() {
		return totalAdjustedPartsCost;
	}

	public void setTotalAdjustedPartsCost(double totalAdjustedPartsCost) {
		this.totalAdjustedPartsCost = totalAdjustedPartsCost;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public String getWorkOrder() {
		return workOrder;
	}

	public void setWorkOrder(String workOrder) {
		this.workOrder = workOrder;
	}

	

}



