package com.agg.application.entity;


import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.sql.Timestamp;


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
	private int requestedOtherCharges1;
	
	@Column(name="requested_other_charges2")
	private int requestedOtherCharges2;
	
	@Column(name="total_adjusted_parts_cost")
	private int totalAdjustedPartsCost;
	
	@Column(name="total_adjusted_labor_cost")
	private int totalAdjustedLaborCost;
	
	@Column(name="approved_other_charges1")
	private int approvedOtherCharges1;
	
	
	@Column(name="approved_other_charges2")
	private int approvedOtherCharges2;
	
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
	public int getRequestedOtherCharges1() {
		return requestedOtherCharges1;
	}

	/**
	 * @param requestedOtherCharges1 the requestedOtherCharges1 to set
	 */
	public void setRequestedOtherCharges1(int requestedOtherCharges1) {
		this.requestedOtherCharges1 = requestedOtherCharges1;
	}

	/**
	 * @return the requestedOtherCharges2
	 */
	public int getRequestedOtherCharges2() {
		return requestedOtherCharges2;
	}

	/**
	 * @param requestedOtherCharges2 the requestedOtherCharges2 to set
	 */
	public void setRequestedOtherCharges2(int requestedOtherCharges2) {
		this.requestedOtherCharges2 = requestedOtherCharges2;
	}

	/**
	 * @return the totalAdjustedPartsCost
	 */
	public int getTotalAdjustedPartsCost() {
		return totalAdjustedPartsCost;
	}

	/**
	 * @param totalAdjustedPartsCost the totalAdjustedPartsCost to set
	 */
	public void setTotalAdjustedPartsCost(int totalAdjustedPartsCost) {
		this.totalAdjustedPartsCost = totalAdjustedPartsCost;
	}

	/**
	 * @return the totalAdjustedLaborCost
	 */
	public int getTotalAdjustedLaborCost() {
		return totalAdjustedLaborCost;
	}

	/**
	 * @param totalAdjustedLaborCost the totalAdjustedLaborCost to set
	 */
	public void setTotalAdjustedLaborCost(int totalAdjustedLaborCost) {
		this.totalAdjustedLaborCost = totalAdjustedLaborCost;
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