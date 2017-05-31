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
	
	private String contractId;
	
	private String contractExpirationDate;
	
	private String lol;
	
	private String deductibleAmount;
	
	private long usageHoursCovered;
	
	private String availableLol;
	
	private String coverageType;
	
	private String claimStatus;
	
	private String workOrderNumber;
	
	private String chequeNo;
	
	private String paidDate;
	
	private String totalReqPartsCost;
	
	private String totalAdjPartsCost;
	
	private String totalReqLaborCost;
	
	private String totalAdjLaborCost;
	
	private String totalReqClaimCost;
	
	private String totalAdjClaimCost;
	
	private String totalReimbursedAmount;
	
	private String totalAmtOwnedByCustomer;
	
	private List<ClaimFileDO> claimFileDOList;
	
	private List<ClaimPartDO> claimPartDOList;
	
	private List<ClaimLaborDO> claimLaborDOList;
	
	private List<ClaimNoteDO> claimNoteDOList;
	
	private String reqOtherCharges1;
	
	private String reqOtherCharges2;
	
	private String adjOtherCharges1;
	
	private String adjOtherCharges2;
	
	private String dealersName;
	
	private String dealerAddress;
	
	private String dealerCity;
	
	private String dealerState;
	
	private String dealerZip;
	
	private String dealerPhone;
	
	private String dealerEmail;
	
	private String receivedDate;
	
	private String checkAmount;
	
	private List<CheckDO> checkDos;
	
	private String totalChequeAmount;
	
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

	public String getContractId() {
		return contractId;
	}

	public void setContractId(String contractId) {
		this.contractId = contractId;
	}

	public String getContractExpirationDate() {
		return contractExpirationDate;
	}

	public void setContractExpirationDate(String contractExpirationDate) {
		this.contractExpirationDate = contractExpirationDate;
	}

	public String getLol() {
		return lol;
	}

	public void setLol(String lol) {
		this.lol = lol;
	}

	public String getDeductibleAmount() {
		return deductibleAmount;
	}

	public void setDeductibleAmount(String deductibleAmount) {
		this.deductibleAmount = deductibleAmount;
	}

	public long getUsageHoursCovered() {
		return usageHoursCovered;
	}

	public void setUsageHoursCovered(long usageHoursCovered) {
		this.usageHoursCovered = usageHoursCovered;
	}

	public String getAvailableLol() {
		return availableLol;
	}

	public void setAvailableLol(String availableLol) {
		this.availableLol = availableLol;
	}

	public String getCoverageType() {
		return coverageType;
	}

	public void setCoverageType(String coverageType) {
		this.coverageType = coverageType;
	}

	public String getClaimStatus() {
		return claimStatus;
	}

	public void setClaimStatus(String claimStatus) {
		this.claimStatus = claimStatus;
	}

	public String getWorkOrderNumber() {
		return workOrderNumber;
	}

	public void setWorkOrderNumber(String workOrderNumber) {
		this.workOrderNumber = workOrderNumber;
	}

	public String getChequeNo() {
		return chequeNo;
	}

	public void setChequeNo(String chequeNo) {
		this.chequeNo = chequeNo;
	}

	public String getPaidDate() {
		return paidDate;
	}

	public void setPaidDate(String paidDate) {
		this.paidDate = paidDate;
	}

	public String getTotalReqPartsCost() {
		return totalReqPartsCost;
	}

	public void setTotalReqPartsCost(String totalReqPartsCost) {
		this.totalReqPartsCost = totalReqPartsCost;
	}

	public String getTotalAdjPartsCost() {
		return totalAdjPartsCost;
	}

	public void setTotalAdjPartsCost(String totalAdjPartsCost) {
		this.totalAdjPartsCost = totalAdjPartsCost;
	}

	public String getTotalReqLaborCost() {
		return totalReqLaborCost;
	}

	public void setTotalReqLaborCost(String totalReqLaborCost) {
		this.totalReqLaborCost = totalReqLaborCost;
	}

	public String getTotalAdjLaborCost() {
		return totalAdjLaborCost;
	}

	public void setTotalAdjLaborCost(String totalAdjLaborCost) {
		this.totalAdjLaborCost = totalAdjLaborCost;
	}

	public String getTotalReqClaimCost() {
		return totalReqClaimCost;
	}

	public void setTotalReqClaimCost(String totalReqClaimCost) {
		this.totalReqClaimCost = totalReqClaimCost;
	}

	public String getTotalAdjClaimCost() {
		return totalAdjClaimCost;
	}

	public void setTotalAdjClaimCost(String totalAdjClaimCost) {
		this.totalAdjClaimCost = totalAdjClaimCost;
	}

	public String getTotalReimbursedAmount() {
		return totalReimbursedAmount;
	}

	public void setTotalReimbursedAmount(String totalReimbursedAmount) {
		this.totalReimbursedAmount = totalReimbursedAmount;
	}

	public String getTotalAmtOwnedByCustomer() {
		return totalAmtOwnedByCustomer;
	}

	public void setTotalAmtOwnedByCustomer(String totalAmtOwnedByCustomer) {
		this.totalAmtOwnedByCustomer = totalAmtOwnedByCustomer;
	}

	public List<ClaimFileDO> getClaimFileDOList() {
		return claimFileDOList;
	}

	public void setClaimFileDOList(List<ClaimFileDO> claimFileDOList) {
		this.claimFileDOList = claimFileDOList;
	}

	public List<ClaimPartDO> getClaimPartDOList() {
		return claimPartDOList;
	}

	public void setClaimPartDOList(List<ClaimPartDO> claimPartDOList) {
		this.claimPartDOList = claimPartDOList;
	}

	public List<ClaimLaborDO> getClaimLaborDOList() {
		return claimLaborDOList;
	}

	public void setClaimLaborDOList(List<ClaimLaborDO> claimLaborDOList) {
		this.claimLaborDOList = claimLaborDOList;
	}

	public List<ClaimNoteDO> getClaimNoteDOList() {
		return claimNoteDOList;
	}

	public void setClaimNoteDOList(List<ClaimNoteDO> claimNoteDOList) {
		this.claimNoteDOList = claimNoteDOList;
	}

	public String getReqOtherCharges1() {
		return reqOtherCharges1;
	}

	public void setReqOtherCharges1(String reqOtherCharges1) {
		this.reqOtherCharges1 = reqOtherCharges1;
	}

	public String getReqOtherCharges2() {
		return reqOtherCharges2;
	}

	public void setReqOtherCharges2(String reqOtherCharges2) {
		this.reqOtherCharges2 = reqOtherCharges2;
	}

	public String getAdjOtherCharges1() {
		return adjOtherCharges1;
	}

	public void setAdjOtherCharges1(String adjOtherCharges1) {
		this.adjOtherCharges1 = adjOtherCharges1;
	}

	public String getAdjOtherCharges2() {
		return adjOtherCharges2;
	}

	public void setAdjOtherCharges2(String adjOtherCharges2) {
		this.adjOtherCharges2 = adjOtherCharges2;
	}

	public String getDealersName() {
		return dealersName;
	}

	public void setDealersName(String dealersName) {
		this.dealersName = dealersName;
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

	public String getReceivedDate() {
		return receivedDate;
	}

	public void setReceivedDate(String receivedDate) {
		this.receivedDate = receivedDate;
	}

	public String getCheckAmount() {
		return checkAmount;
	}

	public void setCheckAmount(String checkAmount) {
		this.checkAmount = checkAmount;
	}

	public List<CheckDO> getCheckDos() {
		return checkDos;
	}

	public void setCheckDos(List<CheckDO> checkDos) {
		this.checkDos = checkDos;
	}

	public String getTotalChequeAmount() {
		return totalChequeAmount;
	}

	public void setTotalChequeAmount(String totalChequeAmount) {
		this.totalChequeAmount = totalChequeAmount;
	}
	
}
