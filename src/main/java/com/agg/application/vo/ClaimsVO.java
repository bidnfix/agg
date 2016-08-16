/**
 * htamada
 */
package com.agg.application.vo;

import java.sql.Timestamp;
import java.util.Date;

import com.agg.application.utils.AggConstants;

/**
 * @author htamada
 *
 */

public class ClaimsVO {
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
	private String cStatus;
	private Timestamp lastUpdate;
	private int requestedOtherCharges1;
	private int requestedOtherCharges2;
	private int totalAdjustedPartsCost;
	private int totalAdjustedLaborCost;
	private int approvedOtherCharges1;
	private int approvedOtherCharges2;
	private String laborNo;
	private String laborDescr;
	private int laborHrs;
	private int laborHourlyRate;
	private ClaimPartVO claimPartVO;
	private byte cStatusValue;
	
	/**
	 * 
	 */
	public ClaimsVO() {
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
	public String getcStatus() {
		return cStatus;
	}
	
	
	
	public void setcStatus(String cStatus) {
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
	 * @return the laborNo
	 */
	public String getLaborNo() {
		return laborNo;
	}
	/**
	 * @param laborNo the laborNo to set
	 */
	public void setLaborNo(String laborNo) {
		this.laborNo = laborNo;
	}
	/**
	 * @return the laborDescr
	 */
	public String getLaborDescr() {
		return laborDescr;
	}
	/**
	 * @param laborDescr the laborDescr to set
	 */
	public void setLaborDescr(String laborDescr) {
		this.laborDescr = laborDescr;
	}
	/**
	 * @return the laborHrs
	 */
	public int getLaborHrs() {
		return laborHrs;
	}
	/**
	 * @param laborHrs the laborHrs to set
	 */
	public void setLaborHrs(int laborHrs) {
		this.laborHrs = laborHrs;
	}
	/**
	 * @return the laborHourlyRate
	 */
	public int getLaborHourlyRate() {
		return laborHourlyRate;
	}
	/**
	 * @param laborHourlyRate the laborHourlyRate to set
	 */
	public void setLaborHourlyRate(int laborHourlyRate) {
		this.laborHourlyRate = laborHourlyRate;
	}
	/**
	 * @return the claimPartVO
	 */
	public ClaimPartVO getClaimPartVO() {
		return claimPartVO;
	}
	/**
	 * @param claimPartVO the claimPartVO to set
	 */
	public void setClaimPartVO(ClaimPartVO claimPartVO) {
		this.claimPartVO = claimPartVO;
	}
	/**
	 * @return the cStatusValue
	 */
	public byte getcStatusValue() {
		switch(this.cStatus.toLowerCase()){
			case "open": 
				this.cStatusValue = AggConstants.CLAIM_STATUS_OPEN;
				break;
			case "pre_authorized_requested":
				this.cStatusValue = AggConstants.CLAIM_STATUS_PRE_AUTHORIZED_REQUESTED;
				break;
			case "submitted":
				this.cStatusValue = AggConstants.CLAIM_STATUS_SUBMITTED;
				break;
			case "closed":
				this.cStatusValue = AggConstants.CLAIM_STATUS_CLOSED;
				break;
			case "pre_authorized_approved":
				this.cStatusValue = AggConstants.CLAIM_STATUS_PRE_AUTHORIZED_APPROVED;
				break;
			case "pre_authorized_rejected":
				this.cStatusValue = AggConstants.CLAIM_STATUS_PRE_AUTHORIZED_REJECTED;
				break;
			case "pre_authorized_approved_with_adjustments":
				this.cStatusValue = AggConstants.CLAIM_STATUS_PRE_AUTHORIZED_APPROVED_WITH_ADJUSMENTS;
				break;
			case "pending":
				this.cStatusValue = AggConstants.CLAIM_STATUS_PENDING;
				break;
			case "draft":
				this.cStatusValue = AggConstants.CLAIM_STATUS_DRAFT;
				break;
			default:
				this.cStatusValue = 0;
		}
		return cStatusValue;
	}
	/**
	 * @param cStatusValue the cStatusValue to set
	 */
	public void setcStatusValue(byte cStatusValue) {
		this.cStatusValue = cStatusValue;
	}
	
}
