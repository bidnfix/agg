/**
 * htamada
 */
package com.agg.application.vo;

import java.sql.Timestamp;
import java.util.Date;

/**
 * @author htamada
 *
 */

public class ClaimsVO {
	private int claimId;

	private String cLevel;

	private byte cStatus;

	private String causeFail;

	private String correctiveAction;

	private String custComplaint;

	private String dealerAddress1;

	private String dealerAddress2;

	private String dealerCity;

	private String dealerContact;

	private String dealerEmail;

	private int dealerId;

	private String dealerName;

	private String dealerPhone;

	private String dealerState;

	private String dealerZip;

	private Date failDate;

	private int hourlyRate;

	private int hoursBreakDown;

	private byte isArchived;

	private int labourHours;

	private Timestamp lastUpdate;

	private String manf;

	private String model;

	private String newClaimId;

	private int otherCharges1;

	private int otherCharges2;

	private String otherManf;

	private String otherModel;

	private int partsTotal;

	private String preAuth;

	private String quoteId;

	private Date reportDate;

	private String serial;

	private String workOrder;

	/**
	 * @return the claimId
	 */
	public int getClaimId() {
		return claimId;
	}

	/**
	 * @param claimId the claimId to set
	 */
	public void setClaimId(int claimId) {
		this.claimId = claimId;
	}

	/**
	 * @return the cLevel
	 */
	public String getcLevel() {
		return cLevel;
	}

	/**
	 * @param cLevel the cLevel to set
	 */
	public void setcLevel(String cLevel) {
		this.cLevel = cLevel;
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
	 * @return the dealerAddress1
	 */
	public String getDealerAddress1() {
		return dealerAddress1;
	}

	/**
	 * @param dealerAddress1 the dealerAddress1 to set
	 */
	public void setDealerAddress1(String dealerAddress1) {
		this.dealerAddress1 = dealerAddress1;
	}

	/**
	 * @return the dealerAddress2
	 */
	public String getDealerAddress2() {
		return dealerAddress2;
	}

	/**
	 * @param dealerAddress2 the dealerAddress2 to set
	 */
	public void setDealerAddress2(String dealerAddress2) {
		this.dealerAddress2 = dealerAddress2;
	}

	/**
	 * @return the dealerCity
	 */
	public String getDealerCity() {
		return dealerCity;
	}

	/**
	 * @param dealerCity the dealerCity to set
	 */
	public void setDealerCity(String dealerCity) {
		this.dealerCity = dealerCity;
	}

	/**
	 * @return the dealerContact
	 */
	public String getDealerContact() {
		return dealerContact;
	}

	/**
	 * @param dealerContact the dealerContact to set
	 */
	public void setDealerContact(String dealerContact) {
		this.dealerContact = dealerContact;
	}

	/**
	 * @return the dealerEmail
	 */
	public String getDealerEmail() {
		return dealerEmail;
	}

	/**
	 * @param dealerEmail the dealerEmail to set
	 */
	public void setDealerEmail(String dealerEmail) {
		this.dealerEmail = dealerEmail;
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
	 * @return the dealerName
	 */
	public String getDealerName() {
		return dealerName;
	}

	/**
	 * @param dealerName the dealerName to set
	 */
	public void setDealerName(String dealerName) {
		this.dealerName = dealerName;
	}

	/**
	 * @return the dealerPhone
	 */
	public String getDealerPhone() {
		return dealerPhone;
	}

	/**
	 * @param dealerPhone the dealerPhone to set
	 */
	public void setDealerPhone(String dealerPhone) {
		this.dealerPhone = dealerPhone;
	}

	/**
	 * @return the dealerState
	 */
	public String getDealerState() {
		return dealerState;
	}

	/**
	 * @param dealerState the dealerState to set
	 */
	public void setDealerState(String dealerState) {
		this.dealerState = dealerState;
	}

	/**
	 * @return the dealerZip
	 */
	public String getDealerZip() {
		return dealerZip;
	}

	/**
	 * @param dealerZip the dealerZip to set
	 */
	public void setDealerZip(String dealerZip) {
		this.dealerZip = dealerZip;
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
	 * @return the hourlyRate
	 */
	public int getHourlyRate() {
		return hourlyRate;
	}

	/**
	 * @param hourlyRate the hourlyRate to set
	 */
	public void setHourlyRate(int hourlyRate) {
		this.hourlyRate = hourlyRate;
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
	 * @return the labourHours
	 */
	public int getLabourHours() {
		return labourHours;
	}

	/**
	 * @param labourHours the labourHours to set
	 */
	public void setLabourHours(int labourHours) {
		this.labourHours = labourHours;
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
	 * @return the manf
	 */
	public String getManf() {
		return manf;
	}

	/**
	 * @param manf the manf to set
	 */
	public void setManf(String manf) {
		this.manf = manf;
	}

	/**
	 * @return the model
	 */
	public String getModel() {
		return model;
	}

	/**
	 * @param model the model to set
	 */
	public void setModel(String model) {
		this.model = model;
	}

	/**
	 * @return the newClaimId
	 */
	public String getNewClaimId() {
		return newClaimId;
	}

	/**
	 * @param newClaimId the newClaimId to set
	 */
	public void setNewClaimId(String newClaimId) {
		this.newClaimId = newClaimId;
	}

	/**
	 * @return the otherCharges1
	 */
	public int getOtherCharges1() {
		return otherCharges1;
	}

	/**
	 * @param otherCharges1 the otherCharges1 to set
	 */
	public void setOtherCharges1(int otherCharges1) {
		this.otherCharges1 = otherCharges1;
	}

	/**
	 * @return the otherCharges2
	 */
	public int getOtherCharges2() {
		return otherCharges2;
	}

	/**
	 * @param otherCharges2 the otherCharges2 to set
	 */
	public void setOtherCharges2(int otherCharges2) {
		this.otherCharges2 = otherCharges2;
	}

	/**
	 * @return the otherManf
	 */
	public String getOtherManf() {
		return otherManf;
	}

	/**
	 * @param otherManf the otherManf to set
	 */
	public void setOtherManf(String otherManf) {
		this.otherManf = otherManf;
	}

	/**
	 * @return the otherModel
	 */
	public String getOtherModel() {
		return otherModel;
	}

	/**
	 * @param otherModel the otherModel to set
	 */
	public void setOtherModel(String otherModel) {
		this.otherModel = otherModel;
	}

	/**
	 * @return the partsTotal
	 */
	public int getPartsTotal() {
		return partsTotal;
	}

	/**
	 * @param partsTotal the partsTotal to set
	 */
	public void setPartsTotal(int partsTotal) {
		this.partsTotal = partsTotal;
	}

	/**
	 * @return the preAuth
	 */
	public String getPreAuth() {
		return preAuth;
	}

	/**
	 * @param preAuth the preAuth to set
	 */
	public void setPreAuth(String preAuth) {
		this.preAuth = preAuth;
	}

	/**
	 * @return the quoteId
	 */
	public String getQuoteId() {
		return quoteId;
	}

	/**
	 * @param quoteId the quoteId to set
	 */
	public void setQuoteId(String quoteId) {
		this.quoteId = quoteId;
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
	
	

}
