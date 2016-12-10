package com.agg.application.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the claim_events database table.
 * 
 */
@Entity
@Table(name="claim_events")
@NamedQuery(name="ClaimEvent.findAll", query="SELECT c FROM ClaimEvent c")
public class ClaimEvent implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	@Lob
	@Column(name="c_desc")
	private String cDesc;

	@Column(name="claim_id")
	private int claimId;

	@Column(name="last_update")
	private Timestamp lastUpdate;

	@Column(name="member_id")
	private int memberId;

	public ClaimEvent() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCDesc() {
		return this.cDesc;
	}

	public void setCDesc(String cDesc) {
		this.cDesc = cDesc;
	}

	public int getClaimId() {
		return this.claimId;
	}

	public void setClaimId(int claimId) {
		this.claimId = claimId;
	}

	public Timestamp getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public int getMemberId() {
		return this.memberId;
	}

	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}

}