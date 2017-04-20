/**
 * htamada
 */
package com.agg.application.model;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * @author htamada
 *
 */
public class ContractDO {
	/**
	 * 
	 */
	public ContractDO() {
		super();
		// TODO Auto-generated constructor stub
	}
	private long id;
	private String contractId;
	private long quoteId;
	private int status;
	private String machineSerialNo;
	private String machineModel;
	private ManufacturerDO manufacturerDO; 
	private String coverageType;
	private int coverageTermMonths;
	private int coverageLevelHours;
	private double deductible;
	private double lol;
	private double availabeLol;
	private Timestamp inceptionDate;
	private Timestamp expirationDate;
	private int expirationUsageHours;
	private String comments;
	private Date lastUpdatedDate;
	private String cheqNo;
	private Timestamp receivedDate;
	private List<CheckDO> checkDOList;
	
	public ContractDO(long id, String contractId, String machineSerialNo, double lol, Date inceptionDate,
			Date expirationDate, int expirationUsageHours, int status, Date lastUpdatedDate, String cheqNo,
			Date receivedDate) {
		this.id = id;
		this.contractId = contractId;
		this.machineSerialNo = machineSerialNo;
		this.lol = lol;
		if(inceptionDate != null){
			this.inceptionDate = new java.sql.Timestamp(inceptionDate.getTime());
		}
		if(expirationDate != null){
			this.expirationDate = new java.sql.Timestamp(expirationDate.getTime());
		}
		this.expirationUsageHours = expirationUsageHours;
		this.status = status;
		this.lastUpdatedDate = lastUpdatedDate;
		this.cheqNo = cheqNo;
		if(receivedDate != null){
			this.receivedDate = new java.sql.Timestamp(receivedDate.getTime());
		}
		
	}
	
	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
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
	 * @return the quoteId
	 */
	public long getQuoteId() {
		return quoteId;
	}
	/**
	 * @param quoteId the quoteId to set
	 */
	public void setQuoteId(long quoteId) {
		this.quoteId = quoteId;
	}
	/**
	 * @return the status
	 */
	public int getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(int status) {
		this.status = status;
	}
	/**
	 * @return the machineSerialNo
	 */
	public String getMachineSerialNo() {
		return machineSerialNo;
	}
	/**
	 * @param machineSerialNo the machineSerialNo to set
	 */
	public void setMachineSerialNo(String machineSerialNo) {
		this.machineSerialNo = machineSerialNo;
	}
	
	/**
	 * @return the machineModel
	 */
	public String getMachineModel() {
		return machineModel;
	}
	/**
	 * @param machineModel the machineModel to set
	 */
	public void setMachineModel(String machineModel) {
		this.machineModel = machineModel;
	}
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
	/**
	 * @return the coverageTermMonths
	 */
	public int getCoverageTermMonths() {
		return coverageTermMonths;
	}
	/**
	 * @param coverageTermMonths the coverageTermMonths to set
	 */
	public void setCoverageTermMonths(int coverageTermMonths) {
		this.coverageTermMonths = coverageTermMonths;
	}
	/**
	 * @return the coverageLevelHours
	 */
	public int getCoverageLevelHours() {
		return coverageLevelHours;
	}
	/**
	 * @param coverageLevelHours the coverageLevelHours to set
	 */
	public void setCoverageLevelHours(int coverageLevelHours) {
		this.coverageLevelHours = coverageLevelHours;
	}
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
	public Timestamp getInceptionDate() {
		return inceptionDate;
	}

	public void setInceptionDate(Timestamp inceptionDate) {
		this.inceptionDate = inceptionDate;
	}

	public Timestamp getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(Timestamp expirationDate) {
		this.expirationDate = expirationDate;
	}

	/**
	 * @return the expirationUsageHours
	 */
	public int getExpirationUsageHours() {
		return expirationUsageHours;
	}
	/**
	 * @param expirationUsageHours the expirationUsageHours to set
	 */
	public void setExpirationUsageHours(int expirationUsageHours) {
		this.expirationUsageHours = expirationUsageHours;
	}
	/**
	 * @return the comments
	 */
	public String getComments() {
		return comments;
	}
	/**
	 * @param comments the comments to set
	 */
	public void setComments(String comments) {
		this.comments = comments;
	}
	/**
	 * @return the lastUpdatedDate
	 */
	public Date getLastUpdatedDate() {
		return lastUpdatedDate;
	}
	/**
	 * @param lastUpdatedDate the lastUpdatedDate to set
	 */
	public void setLastUpdatedDate(Date lastUpdatedDate) {
		this.lastUpdatedDate = lastUpdatedDate;
	}
	/**
	 * @return the manufacturerDO
	 */
	public ManufacturerDO getManufacturerDO() {
		return manufacturerDO;
	}
	/**
	 * @param manufacturerDO the manufacturerDO to set
	 */
	public void setManufacturerDO(ManufacturerDO manufacturerDO) {
		this.manufacturerDO = manufacturerDO;
	}

	/**
	 * @return the cheqNo
	 */
	public String getCheqNo() {
		return cheqNo;
	}

	/**
	 * @param cheqNo the cheqNo to set
	 */
	public void setCheqNo(String cheqNo) {
		this.cheqNo = cheqNo;
	}

	public Timestamp getReceivedDate() {
		return receivedDate;
	}

	public void setReceivedDate(Timestamp receivedDate) {
		this.receivedDate = receivedDate;
	}

	public List<CheckDO> getCheckDOList() {
		return checkDOList;
	}

	public void setCheckDOList(List<CheckDO> checkDOList) {
		this.checkDOList = checkDOList;
	}
	
}
