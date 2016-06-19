package com.agg.application.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

/**
 * The persistent class for the claims database table.
 * 
 */
public class BugDO {
	private static final long serialVersionUID = 1L;

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

	public int getClaimId() {
		return this.claimId;
	}

	public void setClaimId(int claimId) {
		this.claimId = claimId;
	}

	public String getCLevel() {
		return this.cLevel;
	}

	public void setCLevel(String cLevel) {
		this.cLevel = cLevel;
	}

	public byte getCStatus() {
		return this.cStatus;
	}

	public void setCStatus(byte cStatus) {
		this.cStatus = cStatus;
	}

	public String getCauseFail() {
		return this.causeFail;
	}

	public void setCauseFail(String causeFail) {
		this.causeFail = causeFail;
	}

	public String getCorrectiveAction() {
		return this.correctiveAction;
	}

	public void setCorrectiveAction(String correctiveAction) {
		this.correctiveAction = correctiveAction;
	}

	public String getCustComplaint() {
		return this.custComplaint;
	}

	public void setCustComplaint(String custComplaint) {
		this.custComplaint = custComplaint;
	}

	public String getDealerAddress1() {
		return this.dealerAddress1;
	}

	public void setDealerAddress1(String dealerAddress1) {
		this.dealerAddress1 = dealerAddress1;
	}

	public String getDealerAddress2() {
		return this.dealerAddress2;
	}

	public void setDealerAddress2(String dealerAddress2) {
		this.dealerAddress2 = dealerAddress2;
	}

	public String getDealerCity() {
		return this.dealerCity;
	}

	public void setDealerCity(String dealerCity) {
		this.dealerCity = dealerCity;
	}

	public String getDealerContact() {
		return this.dealerContact;
	}

	public void setDealerContact(String dealerContact) {
		this.dealerContact = dealerContact;
	}

	public String getDealerEmail() {
		return this.dealerEmail;
	}

	public void setDealerEmail(String dealerEmail) {
		this.dealerEmail = dealerEmail;
	}

	public int getDealerId() {
		return this.dealerId;
	}

	public void setDealerId(int dealerId) {
		this.dealerId = dealerId;
	}

	public String getDealerName() {
		return this.dealerName;
	}

	public void setDealerName(String dealerName) {
		this.dealerName = dealerName;
	}

	public String getDealerPhone() {
		return this.dealerPhone;
	}

	public void setDealerPhone(String dealerPhone) {
		this.dealerPhone = dealerPhone;
	}

	public String getDealerState() {
		return this.dealerState;
	}

	public void setDealerState(String dealerState) {
		this.dealerState = dealerState;
	}

	public String getDealerZip() {
		return this.dealerZip;
	}

	public void setDealerZip(String dealerZip) {
		this.dealerZip = dealerZip;
	}

	public Date getFailDate() {
		return this.failDate;
	}

	public void setFailDate(Date failDate) {
		this.failDate = failDate;
	}

	public int getHourlyRate() {
		return this.hourlyRate;
	}

	public void setHourlyRate(int hourlyRate) {
		this.hourlyRate = hourlyRate;
	}

	public int getHoursBreakDown() {
		return this.hoursBreakDown;
	}

	public void setHoursBreakDown(int hoursBreakDown) {
		this.hoursBreakDown = hoursBreakDown;
	}

	public byte getIsArchived() {
		return this.isArchived;
	}

	public void setIsArchived(byte isArchived) {
		this.isArchived = isArchived;
	}

	public int getLabourHours() {
		return this.labourHours;
	}

	public void setLabourHours(int labourHours) {
		this.labourHours = labourHours;
	}

	public Timestamp getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public String getManf() {
		return this.manf;
	}

	public void setManf(String manf) {
		this.manf = manf;
	}

	public String getModel() {
		return this.model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getNewClaimId() {
		return this.newClaimId;
	}

	public void setNewClaimId(String newClaimId) {
		this.newClaimId = newClaimId;
	}

	public int getOtherCharges1() {
		return this.otherCharges1;
	}

	public void setOtherCharges1(int otherCharges1) {
		this.otherCharges1 = otherCharges1;
	}

	public int getOtherCharges2() {
		return this.otherCharges2;
	}

	public void setOtherCharges2(int otherCharges2) {
		this.otherCharges2 = otherCharges2;
	}

	public String getOtherManf() {
		return this.otherManf;
	}

	public void setOtherManf(String otherManf) {
		this.otherManf = otherManf;
	}

	public String getOtherModel() {
		return this.otherModel;
	}

	public void setOtherModel(String otherModel) {
		this.otherModel = otherModel;
	}

	public int getPartsTotal() {
		return this.partsTotal;
	}

	public void setPartsTotal(int partsTotal) {
		this.partsTotal = partsTotal;
	}

	public String getPreAuth() {
		return this.preAuth;
	}

	public void setPreAuth(String preAuth) {
		this.preAuth = preAuth;
	}

	public String getQuoteId() {
		return this.quoteId;
	}

	public void setQuoteId(String quoteId) {
		this.quoteId = quoteId;
	}

	public Date getReportDate() {
		return this.reportDate;
	}

	public void setReportDate(Date reportDate) {
		this.reportDate = reportDate;
	}

	public String getSerial() {
		return this.serial;
	}

	public void setSerial(String serial) {
		this.serial = serial;
	}

	public String getWorkOrder() {
		return this.workOrder;
	}

	public void setWorkOrder(String workOrder) {
		this.workOrder = workOrder;
	}

}