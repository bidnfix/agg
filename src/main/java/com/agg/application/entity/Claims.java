package com.agg.application.entity;


import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the claims database table.
 * 
 */
@Entity
@Table(name="claims")
@NamedQuery(name="Claims.findAll", query="SELECT c FROM Claims c")
public class Claims implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	private int id;
	
	@Column(name="claim_id")
	private String claimId;
	
	@Column(name="contract_id")
	private String contractId;
	
	@Column(name="dealer_id")
	private int dealerId;
	
	private String serial;
	
	@Temporal(TemporalType.DATE)
	@Column(name="fail_date")
	private Date failDate;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="report_date")
	private Date reportDate;

	@Column(name="work_order")
	private String workOrder;
	
	@Column(name="hours_break_down")
	private int hoursBreakDown;
	
	@Column(name="preauth_approved_amt")
	private int preauthApprovedAmt;
	
	@Lob
	@Column(name="cust_complaint")
	private String custComplaint;
	
	@Lob
	@Column(name="cause_fail")
	private String causeFail;
	
	@Lob
	@Column(name="corrective_action")
	private String correctiveAction;
	
	@Column(name="is_archived")
	private byte isArchived;
	
	@Column(name="c_status")
	private byte cStatus;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="last_update")
	private Date lastUpdate;
	
	@Column(name="requested_other_charges1")
	private double requestedOtherCharges1;
	
	@Column(name="requested_other_charges2")
	private double requestedOtherCharges2;
	
	@Column(name="total_adjusted_parts_cost")
	private double totalAdjustedPartsCost;
	
	@Column(name="total_adjusted_labor_cost")
	private double totalAdjustedLaborCost;
	
	@Column(name="approved_other_charges1")
	private double approvedOtherCharges1;
	
	
	@Column(name="approved_other_charges2")
	private double approvedOtherCharges2;
	
	@Column(name="cheq_no")
	private String cheqNo;
	
	@Column(name="paid_date")
	private Date paidDate;
	
	@Column(name="created_by")
	private long crtaedBy;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_date")
	private Date createdDate;
	
	@Column(name="updated_by")
	private long updatedBy;
	
	@OneToMany(mappedBy = "claim", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<Check> checks;
	
	@Column(name="dealer_name")
	private String dealerName; 
	
	@Column(name="dealer_address")
	private String dealerAddress;
	
	@Column(name="dealer_city")
	private String dealerCity;
	
	@Column(name="dealer_state")
	private String dealerState;
	
	@Column(name="dealer_zip")
	private String dealerZip;
	
	@Column(name="dealer_phone")
	private String dealerPhone;
	
	@Column(name="dealer_email")
	private String dealerEmail;
	
	
	public Claims() {
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
	public String getClaimId() {
		return claimId;
	}

	/**
	 * @param claimId the claimId to set
	 */
	public void setClaimId(String claimId) {
		this.claimId = claimId;
	}

	/**
	 * @return the contractId
	 */
	public String getContractId() {
		return contractId;
	}

	/**
	 * @param contractId the contractId to set
	 */
	public void setContractId(String contractId) {
		this.contractId = contractId;
	}

	/**
	 * @return the dealerId
	 */
	public int getDealerId() {
		return dealerId;
	}

	/**
	 * @param dealerId the dealerId to set
	 */
	public void setDealerId(int dealerId) {
		this.dealerId = dealerId;
	}

	/**
	 * @return the serial
	 */
	public String getSerial() {
		return serial;
	}

	/**
	 * @param serial the serial to set
	 */
	public void setSerial(String serial) {
		this.serial = serial;
	}

	/**
	 * @return the failDate
	 */
	public Date getFailDate() {
		return failDate;
	}

	/**
	 * @param failDate the failDate to set
	 */
	public void setFailDate(Date failDate) {
		this.failDate = failDate;
	}

	/**
	 * @return the reportDate
	 */
	public Date getReportDate() {
		return reportDate;
	}

	/**
	 * @param reportDate the reportDate to set
	 */
	public void setReportDate(Date reportDate) {
		this.reportDate = reportDate;
	}

	/**
	 * @return the workOrder
	 */
	public String getWorkOrder() {
		return workOrder;
	}

	/**
	 * @param workOrder the workOrder to set
	 */
	public void setWorkOrder(String workOrder) {
		this.workOrder = workOrder;
	}

	/**
	 * @return the hoursBreakDown
	 */
	public int getHoursBreakDown() {
		return hoursBreakDown;
	}

	/**
	 * @param hoursBreakDown the hoursBreakDown to set
	 */
	public void setHoursBreakDown(int hoursBreakDown) {
		this.hoursBreakDown = hoursBreakDown;
	}

	/**
	 * @return the preauthApprovedAmt
	 */
	public int getPreauthApprovedAmt() {
		return preauthApprovedAmt;
	}

	/**
	 * @param preauthApprovedAmt the preauthApprovedAmt to set
	 */
	public void setPreauthApprovedAmt(int preauthApprovedAmt) {
		this.preauthApprovedAmt = preauthApprovedAmt;
	}

	/**
	 * @return the custComplaint
	 */
	public String getCustComplaint() {
		return custComplaint;
	}

	/**
	 * @param custComplaint the custComplaint to set
	 */
	public void setCustComplaint(String custComplaint) {
		this.custComplaint = custComplaint;
	}

	/**
	 * @return the causeFail
	 */
	public String getCauseFail() {
		return causeFail;
	}

	/**
	 * @param causeFail the causeFail to set
	 */
	public void setCauseFail(String causeFail) {
		this.causeFail = causeFail;
	}

	/**
	 * @return the correctiveAction
	 */
	public String getCorrectiveAction() {
		return correctiveAction;
	}

	/**
	 * @param correctiveAction the correctiveAction to set
	 */
	public void setCorrectiveAction(String correctiveAction) {
		this.correctiveAction = correctiveAction;
	}

	/**
	 * @return the isArchived
	 */
	public byte getIsArchived() {
		return isArchived;
	}

	/**
	 * @param isArchived the isArchived to set
	 */
	public void setIsArchived(byte isArchived) {
		this.isArchived = isArchived;
	}

	/**
	 * @return the cStatus
	 */
	public byte getcStatus() {
		return cStatus;
	}

	/**
	 * @param cStatus the cStatus to set
	 */
	public void setcStatus(byte cStatus) {
		this.cStatus = cStatus;
	}

	/**
	 * @return the lastUpdate
	 */
	public Date getLastUpdate() {
		return lastUpdate;
	}

	/**
	 * @param lastUpdate the lastUpdate to set
	 */
	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
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
	 * @return the totalAdjustedPartsCost
	 */
	public double getTotalAdjustedPartsCost() {
		return totalAdjustedPartsCost;
	}

	/**
	 * @param totalAdjustedPartsCost the totalAdjustedPartsCost to set
	 */
	public void setTotalAdjustedPartsCost(double totalAdjustedPartsCost) {
		this.totalAdjustedPartsCost = totalAdjustedPartsCost;
	}

	/**
	 * @return the totalAdjustedLaborCost
	 */
	public double getTotalAdjustedLaborCost() {
		return totalAdjustedLaborCost;
	}

	/**
	 * @param totalAdjustedLaborCost the totalAdjustedLaborCost to set
	 */
	public void setTotalAdjustedLaborCost(double totalAdjustedLaborCost) {
		this.totalAdjustedLaborCost = totalAdjustedLaborCost;
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

	public long getCrtaedBy() {
		return crtaedBy;
	}

	public void setCrtaedBy(long crtaedBy) {
		this.crtaedBy = crtaedBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public long getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(long updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Set<Check> getChecks() {
		return checks;
	}

	public void setChecks(Set<Check> checks) {
		this.checks = checks;
	}

	public String getDealerName() {
		return dealerName;
	}

	public void setDealerName(String dealerName) {
		this.dealerName = dealerName;
	}

	public String getDealerAddress() {
		return dealerAddress;
	}

	public void setDealerAddress(String dealerAddress) {
		this.dealerAddress = dealerAddress;
	}

	public String getDealerCity() {
		return dealerCity;
	}

	public void setDealerCity(String dealerCity) {
		this.dealerCity = dealerCity;
	}

	public String getDealerState() {
		return dealerState;
	}

	public void setDealerState(String dealerState) {
		this.dealerState = dealerState;
	}

	public String getDealerZip() {
		return dealerZip;
	}

	public void setDealerZip(String dealerZip) {
		this.dealerZip = dealerZip;
	}

	public String getDealerPhone() {
		return dealerPhone;
	}

	public void setDealerPhone(String dealerPhone) {
		this.dealerPhone = dealerPhone;
	}

	public String getDealerEmail() {
		return dealerEmail;
	}

	public void setDealerEmail(String dealerEmail) {
		this.dealerEmail = dealerEmail;
	}
	
}