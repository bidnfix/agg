package com.agg.application.model;

import java.util.Date;
import java.util.List;

public class ClaimsDO {
	/**
	 * @return the coverageType
	 */
	public String getCoverageType() {
		return coverageType;
	}
	/**
	 * @param coverageType the coverageType to set
	 */
	public void setCoverageType(String coverageType) {
		this.coverageType = coverageType;
	}
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
	private Date lastUpdate;
	private double requestedOtherCharges1;
	private double requestedOtherCharges2;
	private double totalAdjustedPartsCost;
	private double totalAdjustedLaborCost;
	private double approvedOtherCharges1;
	private double approvedOtherCharges2;
	private List<ClaimPartDO> claimPartDO;
	private List<ClaimLaborDO> claimLaborDO;
	private String custName;
	private String dealerName;
	private String manufacturer;
	private String machineModel;
	private ContractDO contractDO;
	private List<ClaimFileDO> claimFileDO;
	private String coverageType;
	private DealerDO dealerDO;
	private double tra;
	private double customerOwesAmount;
	private String comments;
	private String cheqNo;
	private Date paidDate;
	private long createdBy;
	private long updatedBy;
	private Date createdDate;
	private String createdUser;
	private String updatedUser;
	private List<ClaimNoteDO> claimsNoteList;
	
	public List<ClaimFileDO> getClaimFileDO() {
		return claimFileDO;
	}
	public void setClaimFileDO(List<ClaimFileDO> claimFileDO) {
		this.claimFileDO = claimFileDO;
	}
	/**
	 * @return the contractDO
	 */
	public ContractDO getContractDO() {
		return contractDO;
	}
	/**
	 * @param contractDO the contractDO to set
	 */
	public void setContractDO(ContractDO contractDO) {
		this.contractDO = contractDO;
	}

	public ClaimsDO(String claimId, String custName, String dealerName, String serialNo, String manfName,
			String machineModel, byte status, String cheqNo, Date paidDate) {
		this.claimId = claimId;
		this.custName = custName;
		this.dealerName = dealerName;
		this.serial = serialNo;
		this.manufacturer = manfName;
		this.machineModel = machineModel;
		this.cStatus = status;
		this.cheqNo = cheqNo;
		this.paidDate = paidDate;
	}
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
	
	public List<ClaimLaborDO> getClaimLaborDO() {
		return claimLaborDO;
	}
	public void setClaimLaborDO(List<ClaimLaborDO> claimLaborDO) {
		this.claimLaborDO = claimLaborDO;
	}
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	public String getDealerName() {
		return dealerName;
	}
	public void setDealerName(String dealerName) {
		this.dealerName = dealerName;
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
	public DealerDO getDealerDO() {
		return dealerDO;
	}
	public void setDealerDO(DealerDO dealerDO) {
		this.dealerDO = dealerDO;
	}
	public double getTra() {
		return tra;
	}
	public void setTra(double tra) {
		this.tra = tra;
	}
	public double getCustomerOwesAmount() {
		return customerOwesAmount;
	}
	public void setCustomerOwesAmount(double customerOwesAmount) {
		this.customerOwesAmount = customerOwesAmount;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
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
	public long getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(long createdBy) {
		this.createdBy = createdBy;
	}
	public long getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(long updatedBy) {
		this.updatedBy = updatedBy;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public String getCreatedUser() {
		return createdUser;
	}
	public void setCreatedUser(String createdUser) {
		this.createdUser = createdUser;
	}
	public String getUpdatedUser() {
		return updatedUser;
	}
	public void setUpdatedUser(String updatedUser) {
		this.updatedUser = updatedUser;
	}
	public List<ClaimNoteDO> getClaimsNoteList() {
		return claimsNoteList;
	}
	public void setClaimsNoteList(List<ClaimNoteDO> claimsNoteList) {
		this.claimsNoteList = claimsNoteList;
	}
	
}