package com.agg.application.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.sql.Timestamp;


/**
 * The persistent class for the Claim_Report_v database table.
 * 
 */
@Entity
@NamedQuery(name="Claim_Report_v.findAll", query="SELECT c FROM Claim_Report_v c")
public class ClaimReport implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="approved_other_charges1")
	private double approvedOtherCharges1;

	@Column(name="approved_other_charges2")
	private double approvedOtherCharges2;

	@Lob
	@Column(name="Cause_of_Failure")
	private String cause_of_Failure;

	@Column(name="Check_Number")
	private String check_Number;

	@Column(name="Claim_Number")
	private String claim_Number;

	@Column(name="Claim_status")
	private String claim_status;

	@Column(name="contract_id")
	private String contractId;

	@Lob
	@Column(name="Corrective_Action")
	private String corrective_Action;

	@Column(name="created_by")
	private int createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_date")
	private Date createdDate;

	@Lob
	@Column(name="Customer_Complaint")
	private String customer_Complaint;

	@Column(name="Dealer_Name")
	private String dealer_Name;

	@Temporal(TemporalType.DATE)
	@Column(name="Failure_Date")
	private Date failure_Date;

	@Column(name="Hours_at_Breakdown")
	private int hours_at_Breakdown;

	@Column(name="is_archived")
	private byte isArchived;

	@Column(name="last_update")
	private Timestamp lastUpdate;

	@Temporal(TemporalType.DATE)
	@Column(name="paid_date")
	private Date paidDate;

	@Temporal(TemporalType.DATE)
	@Column(name="Reported_On")
	private Date reported_On;

	@Column(name="requested_other_charges1")
	private double requestedOtherCharges1;

	@Column(name="requested_other_charges2")
	private double requestedOtherCharges2;

	@Column(name="Serial_Number")
	private String serial_Number;

	@Column(name="total_adjusted_labor_cost")
	private double totalAdjustedLaborCost;

	@Column(name="total_adjusted_parts_cost")
	private double totalAdjustedPartsCost;

	@Lob
	@Column(name="Updated_By")
	private String updated_By;

	@Column(name="work_order")
	private String workOrder;

	public ClaimReport() {
	}

	public double getApprovedOtherCharges1() {
		return this.approvedOtherCharges1;
	}

	public void setApprovedOtherCharges1(double approvedOtherCharges1) {
		this.approvedOtherCharges1 = approvedOtherCharges1;
	}

	public double getApprovedOtherCharges2() {
		return this.approvedOtherCharges2;
	}

	public void setApprovedOtherCharges2(double approvedOtherCharges2) {
		this.approvedOtherCharges2 = approvedOtherCharges2;
	}

	public String getCause_of_Failure() {
		return this.cause_of_Failure;
	}

	public void setCause_of_Failure(String cause_of_Failure) {
		this.cause_of_Failure = cause_of_Failure;
	}

	public String getCheck_Number() {
		return this.check_Number;
	}

	public void setCheck_Number(String check_Number) {
		this.check_Number = check_Number;
	}

	public String getClaim_Number() {
		return this.claim_Number;
	}

	public void setClaim_Number(String claim_Number) {
		this.claim_Number = claim_Number;
	}

	public String getClaim_status() {
		return this.claim_status;
	}

	public void setClaim_status(String claim_status) {
		this.claim_status = claim_status;
	}

	public String getContractId() {
		return this.contractId;
	}

	public void setContractId(String contractId) {
		this.contractId = contractId;
	}

	public String getCorrective_Action() {
		return this.corrective_Action;
	}

	public void setCorrective_Action(String corrective_Action) {
		this.corrective_Action = corrective_Action;
	}

	public int getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getCustomer_Complaint() {
		return this.customer_Complaint;
	}

	public void setCustomer_Complaint(String customer_Complaint) {
		this.customer_Complaint = customer_Complaint;
	}

	public String getDealer_Name() {
		return this.dealer_Name;
	}

	public void setDealer_Name(String dealer_Name) {
		this.dealer_Name = dealer_Name;
	}

	public Date getFailure_Date() {
		return this.failure_Date;
	}

	public void setFailure_Date(Date failure_Date) {
		this.failure_Date = failure_Date;
	}

	public int getHours_at_Breakdown() {
		return this.hours_at_Breakdown;
	}

	public void setHours_at_Breakdown(int hours_at_Breakdown) {
		this.hours_at_Breakdown = hours_at_Breakdown;
	}

	public byte getIsArchived() {
		return this.isArchived;
	}

	public void setIsArchived(byte isArchived) {
		this.isArchived = isArchived;
	}

	public Timestamp getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public Date getPaidDate() {
		return this.paidDate;
	}

	public void setPaidDate(Date paidDate) {
		this.paidDate = paidDate;
	}

	public Date getReported_On() {
		return this.reported_On;
	}

	public void setReported_On(Date reported_On) {
		this.reported_On = reported_On;
	}

	public double getRequestedOtherCharges1() {
		return this.requestedOtherCharges1;
	}

	public void setRequestedOtherCharges1(double requestedOtherCharges1) {
		this.requestedOtherCharges1 = requestedOtherCharges1;
	}

	public double getRequestedOtherCharges2() {
		return this.requestedOtherCharges2;
	}

	public void setRequestedOtherCharges2(double requestedOtherCharges2) {
		this.requestedOtherCharges2 = requestedOtherCharges2;
	}

	public String getSerial_Number() {
		return this.serial_Number;
	}

	public void setSerial_Number(String serial_Number) {
		this.serial_Number = serial_Number;
	}

	public double getTotalAdjustedLaborCost() {
		return this.totalAdjustedLaborCost;
	}

	public void setTotalAdjustedLaborCost(double totalAdjustedLaborCost) {
		this.totalAdjustedLaborCost = totalAdjustedLaborCost;
	}

	public double getTotalAdjustedPartsCost() {
		return this.totalAdjustedPartsCost;
	}

	public void setTotalAdjustedPartsCost(double totalAdjustedPartsCost) {
		this.totalAdjustedPartsCost = totalAdjustedPartsCost;
	}

	public String getUpdated_By() {
		return this.updated_By;
	}

	public void setUpdated_By(String updated_By) {
		this.updated_By = updated_By;
	}

	public String getWorkOrder() {
		return this.workOrder;
	}

	public void setWorkOrder(String workOrder) {
		this.workOrder = workOrder;
	}

}


