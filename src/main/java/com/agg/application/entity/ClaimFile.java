package com.agg.application.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the claim_files database table.
 * 
 */
@Entity
@Table(name="claim_files")
@NamedQuery(name="ClaimFile.findAll", query="SELECT c FROM ClaimFile c")
public class ClaimFile implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	@Column(name="claim_id")
	private int claimId;

	@Column(name="file_name")
	private String fileName;

	public ClaimFile() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getClaimId() {
		return this.claimId;
	}

	public void setClaimId(int claimId) {
		this.claimId = claimId;
	}

	public String getFileName() {
		return this.fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

}