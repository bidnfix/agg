package com.agg.application.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the manufacturer database table.
 * 
 */
@Entity
@Table(name="manufacturer")
@NamedQuery(name="Manufacturer.findAll", query="SELECT m FROM Manufacturer m")
public class Manufacturer implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name="manf_id")
	private long manfId;

	@Column(name="manf_name")
	private String manfName;

	//bi-directional many-to-one association to MachineInfo
	@OneToMany(mappedBy="manufacturer")
	private List<MachineInfo> machineInfos;

/*	//bi-directional many-to-one association to MachineType
	@OneToMany(mappedBy="manufacturer")
	private List<MachineType> machineTypes;
*/
	//bi-directional many-to-one association to Quote
	@OneToMany(mappedBy="manufacturer")
	private List<Quote> quotes;

	public Manufacturer() {
	}

	public long getManfId() {
		return this.manfId;
	}

	public void setManfId(long manfId) {
		this.manfId = manfId;
	}

	public String getManfName() {
		return this.manfName;
	}

	public void setManfName(String manfName) {
		this.manfName = manfName;
	}

	public List<MachineInfo> getMachineInfos() {
		return this.machineInfos;
	}

	public void setMachineInfos(List<MachineInfo> machineInfos) {
		this.machineInfos = machineInfos;
	}

	public MachineInfo addMachineInfo(MachineInfo machineInfo) {
		getMachineInfos().add(machineInfo);
		machineInfo.setManufacturer(this);

		return machineInfo;
	}

	public MachineInfo removeMachineInfo(MachineInfo machineInfo) {
		getMachineInfos().remove(machineInfo);
		machineInfo.setManufacturer(null);

		return machineInfo;
	}

/*	public List<MachineType> getMachineTypes() {
		return this.machineTypes;
	}

	public void setMachineTypes(List<MachineType> machineTypes) {
		this.machineTypes = machineTypes;
	}

	public MachineType addMachineType(MachineType machineType) {
		getMachineTypes().add(machineType);
		//machineType.setManufacturer(this);

		return machineType;
	}

	public MachineType removeMachineType(MachineType machineType) {
		getMachineTypes().remove(machineType);
		//machineType.setManufacturer(null);

		return machineType;
	}
*/
	public List<Quote> getQuotes() {
		return this.quotes;
	}

	public void setQuotes(List<Quote> quotes) {
		this.quotes = quotes;
	}

	public Quote addQuote(Quote quote) {
		getQuotes().add(quote);
		quote.setManufacturer(this);

		return quote;
	}

	public Quote removeQuote(Quote quote) {
		getQuotes().remove(quote);
		quote.setManufacturer(null);

		return quote;
	}

}