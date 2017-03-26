package com.agg.application.model;

import java.util.Date;
import java.util.Set;

import com.agg.application.utils.AggConstants;

public class QuoteDO {
	
	private int id;
	
	private String quoteId;

	private int cPtHHours;

	private int cPtHPlHours;

	private int cPtHours;

	private int coverageLevelHours;

	private int coverageTerm;

	private Date createDate;

	private double dealerMarkup;

	private String dealerMarkupType;

	private double deductAmount;

	private int groupId;

	private int hHours;

	private int hMonths;

	private short isArchive;

	private Date lastUpdate;

	private int machineHours;

	private int machineMeterHours;

	private String machineModel;

	private String machineModelNum;

	private int machineMonths;

	private int machinePower;

	private double machineRetailPrice;

	private Date machineSaleDate;

	private String machineSerial;

	private String machineUoe;

	private Date machineYear;

	private Date manfEndDate;

	private byte manfEndKnown;

	private byte manfExpired;

	private String manfName;

	private byte manfVerified;

	private String otherProv;

	private int prId;

	private int ptHours;

	private int ptMonths;

	private int servicingDealer;

	private byte status;

	private DealerDO dealerDO;

	private ManufacturerDO manufacturerDO;
	
	private boolean coverageExpired;
	
	private Date coverageEndDate;
	
	private boolean coverageEndDateUnknown;
	
	private boolean coverageEndDateVerified;
	
	private int powerTrainMonths;
	
	private int powerTrainHours;
	
	private int hydraulicsMonths;
	
	private int hydraulicsHours;
	
	private int fullMachineMonths;
	
	private int fullMachineHours;
	
	private MachineInfoDO machineInfoDO;
	
	private ProgramDO programDO;
	
	private int horsePower;
	
	private String serialNumber;
	
	private boolean serialNumberUnknown;
	
	private double retailPrice;
	
	private int meterHours;
	
	private int modelYear;
	
	private UseOfEquipmentDO useOfEquipmentDO;
	
	private Date estSaleDate;
	
	private double deductiblePrice;
	
	private double coeragePrice;
	
	private String dealerName;
	
	private String dealerAddress;
	
	private boolean custRemorsePeriod;
	
	private String dealerCity;
	
	private String dealerState;
	
	private String dealerZip;
	
	private String dealerPhone;
	
	private String dealerEmail;
	
	private String statusDesc;
	
	private int coverageHours;
	
	private double quoteBasePrice;
	
	private String coverageType;
	
	private String machineCondition;
	
	private String coverageTypeDesc;
	
	private double customerPrice;
	
	private double dealerMarkupPrice;
	
	private boolean custUnderstandCoverage;
	
	private String coverageEndDateStr;
	
	private String estSaleDateStr;
	
	private CustomerInfoDO customerInfoDO;
	
	private String program;
	
	private double adjustedBasePrice;
	
	private double adjustedLol;
	
	private String specialConsiderations;
	
	private String condsForCoverage;
	
	private Date inceptionDate;
	
	private Date expirationDate;
	
	private int expirationHours;
	
	private String dealHistory;
	
	private Set<String> coverageTypeSet;
	
	//Added for view as dealer
	private int cHours;

	private int cTerm;

	private String cType;

	private byte condition;

	private int cost;

	private int deductible;

	private String desc;
	
	private double quoteBasePriceToDisplay;
	
	private String provisions;
	
	private int lol;
	
	private String dealerCustName;
	
	private String cheqNo;
	
	private Date receivedDate;
	
	private String comments;
	
	public QuoteDO(){
		
	}
	
	public QuoteDO(int id, String quoteId, String dealerName, String custName, String machineModel, Date machineSaleDate, byte status, Date lastUpdate, short isArchive){
		this.id = id;
		this.quoteId = quoteId;
		this.dealerName = dealerName;
		this.dealerCustName = custName;
		this.machineModel = machineModel;
		this.machineSaleDate = machineSaleDate;
		
		String statusDesc = "";
		if(isArchive == AggConstants.B_QUOTE_STATUS_ACRHIVE){
			statusDesc = AggConstants.QUOTE_STATUS_ACRHIVE;
		}else if(status == AggConstants.B_QUOTE_STATUS_ESTIMATING_PRICE){
			statusDesc = AggConstants.QUOTE_STATUS_ESTIMATING_PRICE;
		}else if(status == AggConstants.B_QUOTE_STATUS_PURCHASE_REQUESTED){
			statusDesc = AggConstants.QUOTE_STATUS_PURCHASE_REQUESTED;
		}else if(status == AggConstants.B_QUOTE_STATUS_INVOICED){
			statusDesc = AggConstants.QUOTE_STATUS_INVOICED;
		}else if(status == AggConstants.B_QUOTE_STATUS_CLOSED){
			statusDesc = AggConstants.QUOTE_STATUS_CLOSED;
		}
		
		this.statusDesc = statusDesc;
		this.status = status;
		this.lastUpdate = lastUpdate;
	}
	
	public QuoteDO(int id, String quoteId, String dealerName, Date machineSaleDate, byte status, Date lastUpdate, short isArchive){
		this.id = id;
		this.quoteId = quoteId;
		this.dealerName = dealerName;
		this.machineSaleDate = machineSaleDate;
		
		String statusDesc = "";
		if(isArchive == AggConstants.B_QUOTE_STATUS_ACRHIVE){
			statusDesc = AggConstants.QUOTE_STATUS_ACRHIVE;
		}else if(status == AggConstants.B_QUOTE_STATUS_ESTIMATING_PRICE){
			statusDesc = AggConstants.QUOTE_STATUS_ESTIMATING_PRICE;
		}else if(status == AggConstants.B_QUOTE_STATUS_PURCHASE_REQUESTED){
			statusDesc = AggConstants.QUOTE_STATUS_PURCHASE_REQUESTED;
		}else if(status == AggConstants.B_QUOTE_STATUS_INVOICED){
			statusDesc = AggConstants.QUOTE_STATUS_INVOICED;
		}else if(status == AggConstants.B_QUOTE_STATUS_CLOSED){
			statusDesc = AggConstants.QUOTE_STATUS_CLOSED;
		}
		
		this.statusDesc = statusDesc;
		this.status = status;
		this.lastUpdate = lastUpdate;
	}
	
	public int getcHours() {
		return cHours;
	}

	public void setcHours(int cHours) {
		this.cHours = cHours;
	}

	public int getcTerm() {
		return cTerm;
	}

	public void setcTerm(int cTerm) {
		this.cTerm = cTerm;
	}

	public String getcType() {
		return cType;
	}

	public void setcType(String cType) {
		this.cType = cType;
	}

	public byte getCondition() {
		return condition;
	}

	public void setCondition(byte condition) {
		this.condition = condition;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public int getDeductible() {
		return deductible;
	}

	public void setDeductible(int deductible) {
		this.deductible = deductible;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public CustomerInfoDO getCustomerInfoDO() {
		return customerInfoDO;
	}

	public void setCustomerInfoDO(CustomerInfoDO customerInfoDO) {
		this.customerInfoDO = customerInfoDO;
	}

	public int getcPtHHours() {
		return cPtHHours;
	}

	public void setcPtHHours(int cPtHHours) {
		this.cPtHHours = cPtHHours;
	}

	public String getQuoteId() {
		return quoteId;
	}

	public ProgramDO getProgramDO() {
		return programDO;
	}

	public void setProgramDO(ProgramDO programDO) {
		this.programDO = programDO;
	}

	public void setQuoteId(String quoteId) {
		this.quoteId = quoteId;
	}

	public int getcPtHPlHours() {
		return cPtHPlHours;
	}

	public void setcPtHPlHours(int cPtHPlHours) {
		this.cPtHPlHours = cPtHPlHours;
	}

	public int getcPtHours() {
		return cPtHours;
	}

	public void setcPtHours(int cPtHours) {
		this.cPtHours = cPtHours;
	}

	public int gethHours() {
		return hHours;
	}

	public void sethHours(int hHours) {
		this.hHours = hHours;
	}

	public int gethMonths() {
		return hMonths;
	}

	public void sethMonths(int hMonths) {
		this.hMonths = hMonths;
	}

	public ManufacturerDO getManufacturerDO() {
		return manufacturerDO;
	}

	public void setManufacturerDO(ManufacturerDO manufacturerDO) {
		this.manufacturerDO = manufacturerDO;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCPtHHours() {
		return this.cPtHHours;
	}

	public void setCPtHHours(int cPtHHours) {
		this.cPtHHours = cPtHHours;
	}

	public int getCPtHPlHours() {
		return this.cPtHPlHours;
	}

	public void setCPtHPlHours(int cPtHPlHours) {
		this.cPtHPlHours = cPtHPlHours;
	}

	public int getCPtHours() {
		return this.cPtHours;
	}

	public void setCPtHours(int cPtHours) {
		this.cPtHours = cPtHours;
	}

	public int getCoverageLevelHours() {
		return this.coverageLevelHours;
	}

	public void setCoverageLevelHours(int coverageLevelHours) {
		this.coverageLevelHours = coverageLevelHours;
	}

	public int getCoverageTerm() {
		return this.coverageTerm;
	}

	public void setCoverageTerm(int coverageTerm) {
		this.coverageTerm = coverageTerm;
	}

	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public double getDealerMarkup() {
		return this.dealerMarkup;
	}

	public void setDealerMarkup(double dealerMarkup) {
		this.dealerMarkup = dealerMarkup;
	}

	public String getDealerMarkupType() {
		return this.dealerMarkupType;
	}

	public void setDealerMarkupType(String dealerMarkupType) {
		this.dealerMarkupType = dealerMarkupType;
	}

	public double getDeductAmount() {
		return this.deductAmount;
	}

	public void setDeductAmount(double deductAmount) {
		this.deductAmount = deductAmount;
	}

	public int getGroupId() {
		return this.groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	public int getHHours() {
		return this.hHours;
	}

	public void setHHours(int hHours) {
		this.hHours = hHours;
	}

	public int getHMonths() {
		return this.hMonths;
	}

	public void setHMonths(int hMonths) {
		this.hMonths = hMonths;
	}

	public short getIsArchive() {
		return this.isArchive;
	}

	public void setIsArchive(short isArchive) {
		this.isArchive = isArchive;
	}

	public Date getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public int getMachineHours() {
		return this.machineHours;
	}

	public void setMachineHours(int machineHours) {
		this.machineHours = machineHours;
	}

	public int getMachineMeterHours() {
		return this.machineMeterHours;
	}

	public void setMachineMeterHours(int machineMeterHours) {
		this.machineMeterHours = machineMeterHours;
	}

	public String getMachineModel() {
		return this.machineModel;
	}

	public void setMachineModel(String machineModel) {
		this.machineModel = machineModel;
	}

	public String getMachineModelNum() {
		return this.machineModelNum;
	}

	public void setMachineModelNum(String machineModelNum) {
		this.machineModelNum = machineModelNum;
	}

	public int getMachineMonths() {
		return this.machineMonths;
	}

	public void setMachineMonths(int machineMonths) {
		this.machineMonths = machineMonths;
	}

	public int getMachinePower() {
		return this.machinePower;
	}

	public void setMachinePower(int machinePower) {
		this.machinePower = machinePower;
	}

	public double getMachineRetailPrice() {
		return this.machineRetailPrice;
	}

	public void setMachineRetailPrice(double machineRetailPrice) {
		this.machineRetailPrice = machineRetailPrice;
	}

	public Date getMachineSaleDate() {
		return this.machineSaleDate;
	}

	public void setMachineSaleDate(Date machineSaleDate) {
		this.machineSaleDate = machineSaleDate;
	}

	public String getMachineSerial() {
		return this.machineSerial;
	}

	public void setMachineSerial(String machineSerial) {
		this.machineSerial = machineSerial;
	}

	public String getMachineUoe() {
		return this.machineUoe;
	}

	public void setMachineUoe(String machineUoe) {
		this.machineUoe = machineUoe;
	}

	public Date getMachineYear() {
		return this.machineYear;
	}

	public void setMachineYear(Date machineYear) {
		this.machineYear = machineYear;
	}

	public Date getManfEndDate() {
		return this.manfEndDate;
	}

	public void setManfEndDate(Date manfEndDate) {
		this.manfEndDate = manfEndDate;
	}

	public byte getManfEndKnown() {
		return this.manfEndKnown;
	}

	public void setManfEndKnown(byte manfEndKnown) {
		this.manfEndKnown = manfEndKnown;
	}

	public byte getManfExpired() {
		return this.manfExpired;
	}

	public void setManfExpired(byte manfExpired) {
		this.manfExpired = manfExpired;
	}

	public String getManfName() {
		return this.manfName;
	}

	public void setManfName(String manfName) {
		this.manfName = manfName;
	}

	public byte getManfVerified() {
		return this.manfVerified;
	}

	public void setManfVerified(byte manfVerified) {
		this.manfVerified = manfVerified;
	}

	public String getOtherProv() {
		return this.otherProv;
	}

	public void setOtherProv(String otherProv) {
		this.otherProv = otherProv;
	}

	public int getPrId() {
		return this.prId;
	}

	public void setPrId(int prId) {
		this.prId = prId;
	}

	public int getPtHours() {
		return this.ptHours;
	}

	public void setPtHours(int ptHours) {
		this.ptHours = ptHours;
	}

	public int getPtMonths() {
		return this.ptMonths;
	}

	public void setPtMonths(int ptMonths) {
		this.ptMonths = ptMonths;
	}

	public int getServicingDealer() {
		return this.servicingDealer;
	}

	public void setServicingDealer(int servicingDealer) {
		this.servicingDealer = servicingDealer;
	}

	public byte getStatus() {
		return this.status;
	}

	public void setStatus(byte status) {
		this.status = status;
	}

	/**
	 * @return the dealerDO
	 */
	public DealerDO getDealerDO() {
		return dealerDO;
	}

	/**
	 * @param dealerDO the dealerDO to set
	 */
	public void setDealerDO(DealerDO dealerDO) {
		this.dealerDO = dealerDO;
	}

	/**
	 * @return the coverageExpired
	 */
	public boolean isCoverageExpired() {
		return coverageExpired;
	}

	/**
	 * @param coverageExpired the coverageExpired to set
	 */
	public void setCoverageExpired(boolean coverageExpired) {
		this.coverageExpired = coverageExpired;
	}

	/**
	 * @return the coverageEndDate
	 */
	public Date getCoverageEndDate() {
		return coverageEndDate;
	}

	/**
	 * @param coverageEndDate the coverageEndDate to set
	 */
	public void setCoverageEndDate(Date coverageEndDate) {
		this.coverageEndDate = coverageEndDate;
	}

	/**
	 * @return the coverageEndDateUnknown
	 */
	public boolean isCoverageEndDateUnknown() {
		return coverageEndDateUnknown;
	}

	/**
	 * @param coverageEndDateUnknown the coverageEndDateUnknown to set
	 */
	public void setCoverageEndDateUnknown(boolean coverageEndDateUnknown) {
		this.coverageEndDateUnknown = coverageEndDateUnknown;
	}

	/**
	 * @return the coverageEndDateVerified
	 */
	public boolean isCoverageEndDateVerified() {
		return coverageEndDateVerified;
	}

	/**
	 * @param coverageEndDateVerified the coverageEndDateVerified to set
	 */
	public void setCoverageEndDateVerified(boolean coverageEndDateVerified) {
		this.coverageEndDateVerified = coverageEndDateVerified;
	}

	/**
	 * @return the powerTrainMonths
	 */
	public int getPowerTrainMonths() {
		return powerTrainMonths;
	}

	/**
	 * @param powerTrainMonths the powerTrainMonths to set
	 */
	public void setPowerTrainMonths(int powerTrainMonths) {
		this.powerTrainMonths = powerTrainMonths;
	}

	/**
	 * @return the powerTrainHours
	 */
	public int getPowerTrainHours() {
		return powerTrainHours;
	}

	/**
	 * @param powerTrainHours the powerTrainHours to set
	 */
	public void setPowerTrainHours(int powerTrainHours) {
		this.powerTrainHours = powerTrainHours;
	}

	/**
	 * @return the hydraulicsMonths
	 */
	public int getHydraulicsMonths() {
		return hydraulicsMonths;
	}

	/**
	 * @param hydraulicsMonths the hydraulicsMonths to set
	 */
	public void setHydraulicsMonths(int hydraulicsMonths) {
		this.hydraulicsMonths = hydraulicsMonths;
	}

	/**
	 * @return the hydraulicsHours
	 */
	public int getHydraulicsHours() {
		return hydraulicsHours;
	}

	/**
	 * @param hydraulicsHours the hydraulicsHours to set
	 */
	public void setHydraulicsHours(int hydraulicsHours) {
		this.hydraulicsHours = hydraulicsHours;
	}

	/**
	 * @return the fullMachineMonths
	 */
	public int getFullMachineMonths() {
		return fullMachineMonths;
	}

	/**
	 * @param fullMachineMonths the fullMachineMonths to set
	 */
	public void setFullMachineMonths(int fullMachineMonths) {
		this.fullMachineMonths = fullMachineMonths;
	}

	/**
	 * @return the fullMachineHours
	 */
	public int getFullMachineHours() {
		return fullMachineHours;
	}

	/**
	 * @param fullMachineHours the fullMachineHours to set
	 */
	public void setFullMachineHours(int fullMachineHours) {
		this.fullMachineHours = fullMachineHours;
	}

	/**
	 * @return the machineInfoDO
	 */
	public MachineInfoDO getMachineInfoDO() {
		return machineInfoDO;
	}

	/**
	 * @param machineInfoDO the machineInfoDO to set
	 */
	public void setMachineInfoDO(MachineInfoDO machineInfoDO) {
		this.machineInfoDO = machineInfoDO;
	}

	/**
	 * @return the horsePower
	 */
	public int getHorsePower() {
		return horsePower;
	}

	/**
	 * @param horsePower the horsePower to set
	 */
	public void setHorsePower(int horsePower) {
		this.horsePower = horsePower;
	}

	/**
	 * @return the serialNumber
	 */
	public String getSerialNumber() {
		return serialNumber;
	}

	/**
	 * @param serialNumber the serialNumber to set
	 */
	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	/**
	 * @return the serialNumberUnknown
	 */
	public boolean isSerialNumberUnknown() {
		return serialNumberUnknown;
	}

	/**
	 * @param serialNumberUnknown the serialNumberUnknown to set
	 */
	public void setSerialNumberUnknown(boolean serialNumberUnknown) {
		this.serialNumberUnknown = serialNumberUnknown;
	}

	/**
	 * @return the retailPrice
	 */
	public double getRetailPrice() {
		return retailPrice;
	}

	/**
	 * @param retailPrice the retailPrice to set
	 */
	public void setRetailPrice(double retailPrice) {
		this.retailPrice = retailPrice;
	}

	/**
	 * @return the meterHours
	 */
	public int getMeterHours() {
		return meterHours;
	}

	/**
	 * @param meterHours the meterHours to set
	 */
	public void setMeterHours(int meterHours) {
		this.meterHours = meterHours;
	}

	/**
	 * @return the modelYear
	 */
	public int getModelYear() {
		return modelYear;
	}

	/**
	 * @param modelYear the modelYear to set
	 */
	public void setModelYear(int modelYear) {
		this.modelYear = modelYear;
	}

	/**
	 * @return the useOfEquipmentDO
	 */
	public UseOfEquipmentDO getUseOfEquipmentDO() {
		return useOfEquipmentDO;
	}

	/**
	 * @param useOfEquipmentDO the useOfEquipmentDO to set
	 */
	public void setUseOfEquipmentDO(UseOfEquipmentDO useOfEquipmentDO) {
		this.useOfEquipmentDO = useOfEquipmentDO;
	}

	/**
	 * @return the estSaleDate
	 */
	public Date getEstSaleDate() {
		return estSaleDate;
	}

	/**
	 * @param estSaleDate the estSaleDate to set
	 */
	public void setEstSaleDate(Date estSaleDate) {
		this.estSaleDate = estSaleDate;
	}

	/**
	 * @return the deductiblePrice
	 */
	public double getDeductiblePrice() {
		return deductiblePrice;
	}

	/**
	 * @param deductiblePrice the deductiblePrice to set
	 */
	public void setDeductiblePrice(double deductiblePrice) {
		this.deductiblePrice = deductiblePrice;
	}

	/**
	 * @return the coeragePrice
	 */
	public double getCoeragePrice() {
		return coeragePrice;
	}

	/**
	 * @param coeragePrice the coeragePrice to set
	 */
	public void setCoeragePrice(double coeragePrice) {
		this.coeragePrice = coeragePrice;
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
	 * @return the dealerAddress
	 */
	public String getDealerAddress() {
		return dealerAddress;
	}

	/**
	 * @param dealerAddress the dealerAddress to set
	 */
	public void setDealerAddress(String dealerAddress) {
		this.dealerAddress = dealerAddress;
	}

	/**
	 * @return the custRemorsePeriod
	 */
	public boolean isCustRemorsePeriod() {
		return custRemorsePeriod;
	}

	/**
	 * @param custRemorsePeriod the custRemorsePeriod to set
	 */
	public void setCustRemorsePeriod(boolean custRemorsePeriod) {
		this.custRemorsePeriod = custRemorsePeriod;
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
	 * @return the statusDesc
	 */
	public String getStatusDesc() {
		return statusDesc;
	}

	/**
	 * @param statusDesc the statusDesc to set
	 */
	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}

	/**
	 * @return the coverageHours
	 */
	public int getCoverageHours() {
		return coverageHours;
	}

	/**
	 * @param coverageHours the coverageHours to set
	 */
	public void setCoverageHours(int coverageHours) {
		this.coverageHours = coverageHours;
	}

	/**
	 * @return the quoteBasePrice
	 */
	public double getQuoteBasePrice() {
		return quoteBasePrice;
	}

	/**
	 * @param quoteBasePrice the quoteBasePrice to set
	 */
	public void setQuoteBasePrice(double quoteBasePrice) {
		this.quoteBasePrice = quoteBasePrice;
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
	 * @return the machineCondition
	 */
	public String getMachineCondition() {
		return machineCondition;
	}

	/**
	 * @param machineCondition the machineCondition to set
	 */
	public void setMachineCondition(String machineCondition) {
		this.machineCondition = machineCondition;
	}

	/**
	 * @return the coverageTypeDesc
	 */
	public String getCoverageTypeDesc() {
		return coverageTypeDesc;
	}

	/**
	 * @param coverageTypeDesc the coverageTypeDesc to set
	 */
	public void setCoverageTypeDesc(String coverageTypeDesc) {
		this.coverageTypeDesc = coverageTypeDesc;
	}

	/**
	 * @return the customerPrice
	 */
	public double getCustomerPrice() {
		return customerPrice;
	}

	/**
	 * @param customerPrice the customerPrice to set
	 */
	public void setCustomerPrice(double customerPrice) {
		this.customerPrice = customerPrice;
	}

	/**
	 * @return the dealerMarkupPrice
	 */
	public double getDealerMarkupPrice() {
		return dealerMarkupPrice;
	}

	/**
	 * @param dealerMarkupPrice the dealerMarkupPrice to set
	 */
	public void setDealerMarkupPrice(double dealerMarkupPrice) {
		this.dealerMarkupPrice = dealerMarkupPrice;
	}

	public boolean isCustUnderstandCoverage() {
		return custUnderstandCoverage;
	}

	public void setCustUnderstandCoverage(boolean custUnderstandCoverage) {
		this.custUnderstandCoverage = custUnderstandCoverage;
	}

	/**
	 * @return the coverageEndDateStr
	 */
	public String getCoverageEndDateStr() {
		return coverageEndDateStr;
	}

	/**
	 * @param coverageEndDateStr the coverageEndDateStr to set
	 */
	public void setCoverageEndDateStr(String coverageEndDateStr) {
		this.coverageEndDateStr = coverageEndDateStr;
	}

	/**
	 * @return the estSaleDateStr
	 */
	public String getEstSaleDateStr() {
		return estSaleDateStr;
	}

	/**
	 * @param estSaleDateStr the estSaleDateStr to set
	 */
	public void setEstSaleDateStr(String estSaleDateStr) {
		this.estSaleDateStr = estSaleDateStr;
	}

	/**
	 * @return the program
	 */
	public String getProgram() {
		return program;
	}

	/**
	 * @param program the program to set
	 */
	public void setProgram(String program) {
		this.program = program;
	}

	/**
	 * @return the adjustedBasePrice
	 */
	public double getAdjustedBasePrice() {
		return adjustedBasePrice;
	}

	/**
	 * @param adjustedBasePrice the adjustedBasePrice to set
	 */
	public void setAdjustedBasePrice(double adjustedBasePrice) {
		this.adjustedBasePrice = adjustedBasePrice;
	}

	/**
	 * @return the adjustedLol
	 */
	public double getAdjustedLol() {
		return adjustedLol;
	}

	/**
	 * @param adjustedLol the adjustedLol to set
	 */
	public void setAdjustedLol(double adjustedLol) {
		this.adjustedLol = adjustedLol;
	}

	/**
	 * @return the specialConsiderations
	 */
	public String getSpecialConsiderations() {
		return specialConsiderations;
	}

	/**
	 * @param specialConsiderations the specialConsiderations to set
	 */
	public void setSpecialConsiderations(String specialConsiderations) {
		this.specialConsiderations = specialConsiderations;
	}

	/**
	 * @return the condsForCoverage
	 */
	public String getCondsForCoverage() {
		return condsForCoverage;
	}

	/**
	 * @param condsForCoverage the condsForCoverage to set
	 */
	public void setCondsForCoverage(String condsForCoverage) {
		this.condsForCoverage = condsForCoverage;
	}

	/**
	 * @return the inceptionDate
	 */
	public Date getInceptionDate() {
		return inceptionDate;
	}

	/**
	 * @param inceptionDate the inceptionDate to set
	 */
	public void setInceptionDate(Date inceptionDate) {
		this.inceptionDate = inceptionDate;
	}

	/**
	 * @return the expirationDate
	 */
	public Date getExpirationDate() {
		return expirationDate;
	}

	/**
	 * @param expirationDate the expirationDate to set
	 */
	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}

	/**
	 * @return the expirationHours
	 */
	public int getExpirationHours() {
		return expirationHours;
	}

	/**
	 * @param expirationHours the expirationHours to set
	 */
	public void setExpirationHours(int expirationHours) {
		this.expirationHours = expirationHours;
	}

	/**
	 * @return the dealHistory
	 */
	public String getDealHistory() {
		return dealHistory;
	}

	/**
	 * @param dealHistory the dealHistory to set
	 */
	public void setDealHistory(String dealHistory) {
		this.dealHistory = dealHistory;
	}

	/**
	 * @return the coverageTypeSet
	 */
	public Set<String> getCoverageTypeSet() {
		return coverageTypeSet;
	}

	/**
	 * @param coverageTypeSet the coverageTypeSet to set
	 */
	public void setCoverageTypeSet(Set<String> coverageTypeSet) {
		this.coverageTypeSet = coverageTypeSet;
	}

	public double getQuoteBasePriceToDisplay() {
		return quoteBasePriceToDisplay;
	}

	public void setQuoteBasePriceToDisplay(double quoteBasePriceToDisplay) {
		this.quoteBasePriceToDisplay = quoteBasePriceToDisplay;
	}

	public String getProvisions() {
		return provisions;
	}

	public void setProvisions(String provisions) {
		this.provisions = provisions;
	}

	public int getLol() {
		return lol;
	}

	public void setLol(int lol) {
		this.lol = lol;
	}

	/**
	 * @return the dealerCustName
	 */
	public String getDealerCustName() {
		return dealerCustName;
	}

	/**
	 * @param dealerCustName the dealerCustName to set
	 */
	public void setDealerCustName(String dealerCustName) {
		this.dealerCustName = dealerCustName;
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

	/**
	 * @return the receivedDate
	 */
	public Date getReceivedDate() {
		return receivedDate;
	}

	/**
	 * @param receivedDate the receivedDate to set
	 */
	public void setReceivedDate(Date receivedDate) {
		this.receivedDate = receivedDate;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}
	
}
