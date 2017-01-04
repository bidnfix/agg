package com.agg.application.model;

import java.util.List;

public class ClaimReportDO {
	
	private String dealerName;
	
	private String contact;
	
	private String address;
	
	private String city;
	
	private String state;
	
	private String zip;
	
	private String phone;
	
	private String email;
	
	private String serialNumber;
	
	private String claimId;
	
	private String failureDate;
	
	private String quoteId;
	
	private String reportedOn;
	
	private long breakdownHrs;
	
	private String manufacturer;
	
	private String machineModel;
	
	private long laborTotalHrs;
	
	private String hourlyRate;
	
	private String laborCost;
	
	private String partsTotal;
	
	private String otherCharges1;
	
	private String otherCharges2;
	
	private String totalClaim;
	
	private String customerComplaint;
	
	private String causeOfFailure;
	
	private String correctiveAction;
	
	private List<ClaimFileDO> claimFileDOList;

	public String getDealerName() {
		return dealerName;
	}

	public void setDealerName(String dealerName) {
		this.dealerName = dealerName;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getClaimId() {
		return claimId;
	}

	public void setClaimId(String claimId) {
		this.claimId = claimId;
	}

	public String getFailureDate() {
		return failureDate;
	}

	public void setFailureDate(String failureDate) {
		this.failureDate = failureDate;
	}

	public String getQuoteId() {
		return quoteId;
	}

	public void setQuoteId(String quoteId) {
		this.quoteId = quoteId;
	}

	public String getReportedOn() {
		return reportedOn;
	}

	public void setReportedOn(String reportedOn) {
		this.reportedOn = reportedOn;
	}

	public long getBreakdownHrs() {
		return breakdownHrs;
	}

	public void setBreakdownHrs(long breakdownHrs) {
		this.breakdownHrs = breakdownHrs;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getMachineModel() {
		return machineModel;
	}

	public void setMachineModel(String machineModel) {
		this.machineModel = machineModel;
	}

	public long getLaborTotalHrs() {
		return laborTotalHrs;
	}

	public void setLaborTotalHrs(long laborTotalHrs) {
		this.laborTotalHrs = laborTotalHrs;
	}

	public String getHourlyRate() {
		return hourlyRate;
	}

	public void setHourlyRate(String hourlyRate) {
		this.hourlyRate = hourlyRate;
	}

	public String getLaborCost() {
		return laborCost;
	}

	public void setLaborCost(String laborCost) {
		this.laborCost = laborCost;
	}

	public String getPartsTotal() {
		return partsTotal;
	}

	public void setPartsTotal(String partsTotal) {
		this.partsTotal = partsTotal;
	}

	public String getOtherCharges1() {
		return otherCharges1;
	}

	public void setOtherCharges1(String otherCharges1) {
		this.otherCharges1 = otherCharges1;
	}

	public String getOtherCharges2() {
		return otherCharges2;
	}

	public void setOtherCharges2(String otherCharges2) {
		this.otherCharges2 = otherCharges2;
	}

	public String getTotalClaim() {
		return totalClaim;
	}

	public void setTotalClaim(String totalClaim) {
		this.totalClaim = totalClaim;
	}

	public String getCustomerComplaint() {
		return customerComplaint;
	}

	public void setCustomerComplaint(String customerComplaint) {
		this.customerComplaint = customerComplaint;
	}

	public String getCauseOfFailure() {
		return causeOfFailure;
	}

	public void setCauseOfFailure(String causeOfFailure) {
		this.causeOfFailure = causeOfFailure;
	}

	public String getCorrectiveAction() {
		return correctiveAction;
	}

	public void setCorrectiveAction(String correctiveAction) {
		this.correctiveAction = correctiveAction;
	}

	public List<ClaimFileDO> getClaimFileDOList() {
		return claimFileDOList;
	}

	public void setClaimFileDOList(List<ClaimFileDO> claimFileDOList) {
		this.claimFileDOList = claimFileDOList;
	}

}
