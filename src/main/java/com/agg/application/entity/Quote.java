package com.agg.application.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the quotes database table.
 * 
 */
@Entity
@Table(name="quotes")
@NamedQuery(name="Quote.findAll", query="SELECT q FROM Quote q")
public class Quote implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private QuotePK id;

	@Column(name="coverage_price")
	private double coveragePrice;
	
	@Column(name="coverage_type")
	private String coverageType;

	@Column(name="coverage_level_hours")
	private int coverageLevelHours;

	@Column(name="coverage_term")
	private int coverageTerm;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="create_date")
	private Date createDate;

	@Column(name="dealer_markup")
	private double dealerMarkup;

	@Column(name="dealer_markup_type")
	private String dealerMarkupType;

	@Column(name="deduct_amount")
	private double deductAmount;

	@Column(name="group_id")
	private int groupId;

	@Column(name="h_hours")
	private int hHours;

	@Column(name="h_months")
	private int hMonths;

	@Column(name="is_archive")
	private short isArchive;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="last_update")
	private Date lastUpdate;

	@Column(name="machine_hours")
	private int machineHours;

	@Column(name="machine_meter_hours")
	private int machineMeterHours;

	@Column(name="machine_model")
	private String machineModel;

	@Column(name="machine_months")
	private int machineMonths;

	@Column(name="machine_power")
	private int machinePower;

	@Column(name="machine_retail_price")
	private double machineRetailPrice;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="machine_sale_date")
	private Date machineSaleDate;

	@Column(name="machine_serial")
	private String machineSerial;

	@Column(name="machine_year")
	private Integer machineYear;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="manf_end_date")
	private Date manfEndDate;

	@Column(name="manf_end_known")
	private byte manfEndKnown;

	@Column(name="manf_expired")
	private byte manfExpired;

	@Column(name="manf_name")
	private String manfName;

	@Column(name="manf_verified")
	private byte manfVerified;

	@Column(name="other_prov")
	private String otherProv;

	@Column(name="pt_hours")
	private int ptHours;

	@Column(name="pt_months")
	private int ptMonths;

	@Column(name="servicing_dealer")
	private int servicingDealer;

	private byte status;

	//bi-directional many-to-one association to Dealer
	@ManyToOne
	@JoinColumn(name="dealer_id")
	private Dealer dealer;

	//bi-directional many-to-one association to Manufacturer
	@ManyToOne
	@JoinColumn(name="manf_id")
	private Manufacturer manufacturer;
	
	//bi-directional many-to-one association to Manufacturer
	@ManyToOne
	@JoinColumn(name="machine_model_id")
	private MachineInfo machineInfo;
	
	@ManyToOne
	@JoinColumn(name="machine_uoe")
	private UseOfEquip useOfEquip;
	
	@ManyToOne
	@JoinColumn(name="pr_id")
	private Sprogram program;
	
	/*@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="quote_id", insertable=false, updatable=false, nullable=true)
	private CustomerInfo customerInfo;*/
	
	

	public Quote() {
	}

	public QuotePK getId() {
		return this.id;
	}

	public void setId(QuotePK id) {
		this.id = id;
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

	/**
	 * @return the machineYear
	 */
	public Integer getMachineYear() {
		return machineYear;
	}

	/**
	 * @param machineYear the machineYear to set
	 */
	public void setMachineYear(Integer machineYear) {
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

	public Dealer getDealer() {
		return this.dealer;
	}

	public void setDealer(Dealer dealer) {
		this.dealer = dealer;
	}

	public Manufacturer getManufacturer() {
		return this.manufacturer;
	}

	public void setManufacturer(Manufacturer manufacturer) {
		this.manufacturer = manufacturer;
	}

	/**
	 * @return the machineInfo
	 */
	public MachineInfo getMachineInfo() {
		return machineInfo;
	}

	/**
	 * @param machineInfo the machineInfo to set
	 */
	public void setMachineInfo(MachineInfo machineInfo) {
		this.machineInfo = machineInfo;
	}

	/**
	 * @return the useOfEquip
	 */
	public UseOfEquip getUseOfEquip() {
		return useOfEquip;
	}

	/**
	 * @param useOfEquip the useOfEquip to set
	 */
	public void setUseOfEquip(UseOfEquip useOfEquip) {
		this.useOfEquip = useOfEquip;
	}

	/**
	 * @return the coveragePrice
	 */
	public double getCoveragePrice() {
		return coveragePrice;
	}

	/**
	 * @param coveragePrice the coveragePrice to set
	 */
	public void setCoveragePrice(double coveragePrice) {
		this.coveragePrice = coveragePrice;
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
	 * @return the program
	 */
	public Sprogram getProgram() {
		return program;
	}

	/**
	 * @param program the program to set
	 */
	public void setProgram(Sprogram program) {
		this.program = program;
	}
	
}