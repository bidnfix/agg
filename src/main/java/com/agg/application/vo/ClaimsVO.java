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
	private String claimId;
	private String contractId;
	private String serial;
	private Date failDate;
	private Date reportDate;
	private String manf;
	private String otherManf;
	private String model;
	private String otherModel;
	private String workOrder;
	private int hoursBreakDown;
	private int labourHours;
	private int hourlyRate;
	private int partsTotal;
	private int otherCharges1;
	private int otherCharges2;
	private String custComplaint;
	private String causeFail;
	private String correctiveAction;
	private String preAuth;
	private byte isArchived;
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
	 * @return the otherManf
	 */
	public String getOtherManf() {
		return otherManf;
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
	 * @param otherManf the otherManf to set
	 */
	public void setOtherManf(String otherManf) {
		this.otherManf = otherManf;
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
	
}
