package com.agg.application.entity;


import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.sql.Timestamp;


/**
 * The persistent class for the claims database table.
 * 
 */
@Entity
@Table(name="claims")
@NamedQuery(name="Claims.findAll", query="SELECT c FROM Claims c")
public class Claims implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="claim_id")
	private int claimId;

	@Column(name="c_level")
	private String cLevel;

	@Column(name="c_status")
	private byte cStatus;

	@Lob
	@Column(name="cause_fail")
	private String causeFail;

	@Lob
	@Column(name="corrective_action")
	private String correctiveAction;

	@Lob
	@Column(name="cust_complaint")
	private String custComplaint;

	@Column(name="dealer_address1")
	private String dealerAddress1;

	@Column(name="dealer_address2")
	private String dealerAddress2;

	@Column(name="dealer_city")
	private String dealerCity;

	@Column(name="dealer_contact")
	private String dealerContact;

	@Column(name="dealer_email")
	private String dealerEmail;

	@Column(name="dealer_id")
	private int dealerId;

	@Column(name="dealer_name")
	private String dealerName;

	@Column(name="dealer_phone")
	private String dealerPhone;

	@Column(name="dealer_state")
	private String dealerState;

	@Column(name="dealer_zip")
	private String dealerZip;

	@Temporal(TemporalType.DATE)
	@Column(name="fail_date")
	private Date failDate;

	@Column(name="hourly_rate")
	private int hourlyRate;

	@Column(name="hours_break_down")
	private int hoursBreakDown;

	@Column(name="is_archived")
	private byte isArchived;

	@Column(name="labour_hours")
	private int labourHours;

	@Column(name="last_update")
	private Timestamp lastUpdate;

	private String manf;

	private String model;

	@Column(name="new_claim_id")
	private String newClaimId;

	@Column(name="other_charges1")
	private int otherCharges1;

	@Column(name="other_charges2")
	private int otherCharges2;

	@Column(name="other_manf")
	private String otherManf;

	@Column(name="other_model")
	private String otherModel;

	@Column(name="parts_total")
	private int partsTotal;

	@Lob
	@Column(name="pre_auth")
	private String preAuth;

	@Column(name="contract_id")
	private String contractId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="report_date")
	private Date reportDate;

	private String serial;

	@Column(name="work_order")
	private String workOrder;

	public Claims() {
	}

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

	public String getContractId() {
		return this.contractId;
	}

	public void setContractId(String contractId) {
		this.contractId = contractId;
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