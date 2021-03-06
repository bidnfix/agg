/**
 * htamada
 */
package com.agg.application.vo;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import com.agg.application.utils.Util;

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
	private double requestedOtherCharges1;
	private double requestedOtherCharges2;
	private double totalAdjustedPartsCost;
	private double totalAdjustedLaborCost;
	private double approvedOtherCharges1;
	private double approvedOtherCharges2;
	private List<ClaimPartVO> claimPartVOList;
	private List<ClaimLabourVO> claimLabourVOList;
	private byte cStatusValue;
	private double deductible;
	private double lol;
	private double availabeLol;
	private String extComments;
	private String dealerName;
	private String dealerAddress;
	private String dealerCity;
	private String dealerState;
	private String dealerZip;
	private String dealerPhone;
	private String dealerEmail;
	
	
	/**
	 * @return the deductible
	 */
	public double getDeductible() {
		return deductible;
	}
	/**
	 * @param deductible the deductible to set
	 */
	public void setDeductible(double deductible) {
		this.deductible = deductible;
	}
	/**
	 * @return the lol
	 */
	public double getLol() {
		return lol;
	}
	/**
	 * @param lol the lol to set
	 */
	public void setLol(double lol) {
		this.lol = lol;
	}
	/**
	 * @return the availabeLol
	 */
	public double getAvailabeLol() {
		return availabeLol;
	}
	/**
	 * @param availabeLol the availabeLol to set
	 */
	public void setAvailabeLol(double availabeLol) {
		this.availabeLol = availabeLol;
	}
	/**
	 * @return the extComments
	 */
	public String getExtComments() {
		return extComments;
	}
	/**
	 * @param extComments the extComments to set
	 */
	public void setExtComments(String extComments) {
		this.extComments = extComments;
	}
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
	 * @return the claimLabourVOList
	 */
	public List<ClaimLabourVO> getClaimLabourVOList() {
		return claimLabourVOList;
	}
	/**
	 * @param claimLabourVOList the claimLabourVOList to set
	 */
	public void setClaimLabourVOList(List<ClaimLabourVO> claimLabourVOList) {
		this.claimLabourVOList = claimLabourVOList;
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
	/**
	 * @return the claimPartVOList
	 */
	public List<ClaimPartVO> getClaimPartVOList() {
		return claimPartVOList;
	}
	/**
	 * @param claimPartVOList the claimPartVOList to set
	 */
	public void setClaimPartVOList(List<ClaimPartVO> claimPartVOList) {
		this.claimPartVOList = claimPartVOList;
	}
	/**
	 * @return the cStatusValue
	 */
	public byte getcStatusValue() {
		if(null != this.cStatus){
			this.cStatusValue = Util.getClaimStatusCode(this.cStatus);
		}		
		return cStatusValue;
	}
	/**
	 * @param cStatusValue the cStatusValue to set
	 */
	public void setcStatusValue(byte cStatusValue) {
		this.cStatusValue = cStatusValue;
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
