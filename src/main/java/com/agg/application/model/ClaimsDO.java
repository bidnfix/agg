package com.agg.application.model;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public class ClaimsDO {
	private int id;
	private String claimId;
	private String contractId;
	private int dealerId;
	private String serial;
	private Date failDate;
	private Date reportDate;
	private String workOrder;
	private int hoursBreakDown;
	private int preauthApprovedAmt;
	private String custComplaint;
	private String causeFail;
	private String correctiveAction;
	private byte isArchived;
	private byte cStatus;
	private Timestamp lastUpdate;
	private int requestedOtherCharges1;
	private int requestedOtherCharges2;
	private int totalAdjustedPartsCost;
	private int totalAdjustedLaborCost;
	private int approvedOtherCharges1;
	private int approvedOtherCharges2;
	private List<ClaimPartDO> claimPartDO;
	private ClaimLaborDO claimLaborDO;
	/**
	 * 
	 */
	public ClaimsDO() {
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
	public Timestamp getLastUpdate() {
		return lastUpdate;
	}
	/**
	 * @param lastUpdate the lastUpdate to set
	 */
	public void setLastUpdate(Timestamp lastUpdate) {
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
	/**
	 * @return the claimPartDO
	 */
	public List<ClaimPartDO> getClaimPartDO() {
		return claimPartDO;
	}
	/**
	 * @param claimPartDO the claimPartDO to set
	 */
	public void setClaimPartDO(List<ClaimPartDO> claimPartDO) {
		this.claimPartDO = claimPartDO;
	}
	/**
	 * @return the claimLaborDO
	 */
	public ClaimLaborDO getClaimLaborDO() {
		return claimLaborDO;
	}
	/**
	 * @param claimLaborDO the claimLaborDO to set
	 */
	public void setClaimLaborDO(ClaimLaborDO claimLaborDO) {
		this.claimLaborDO = claimLaborDO;
	}
}