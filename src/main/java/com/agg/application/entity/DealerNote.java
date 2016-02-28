package com.agg.application.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the dealer_notes database table.
 * 
 */
@Entity
@Table(name="dealer_notes")
@NamedQuery(name="DealerNote.findAll", query="SELECT d FROM DealerNote d")
public class DealerNote implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private int id;

	/*@Column(name="dealer_id")
	private int dealerId;*/

	@Column(name="last_update")
	private Timestamp lastUpdate;

	@Lob
	private String notes;

	//bi-directional one-to-one association to Dealer
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="dealer_id")
	private Dealer dealer;

	public DealerNote() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	/*public int getDealerId() {
		return this.dealerId;
	}

	public void setDealerId(int dealerId) {
		this.dealerId = dealerId;
	}*/

	public Timestamp getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public String getNotes() {
		return this.notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public Dealer getDealer() {
		return this.dealer;
	}

	public void setDealer(Dealer dealer) {
		this.dealer = dealer;
	}

}