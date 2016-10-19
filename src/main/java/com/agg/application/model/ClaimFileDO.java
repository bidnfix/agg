/**
 * htamada
 */
package com.agg.application.model;

import javax.persistence.Column;
import javax.persistence.Id;

/**
 * @author htamada
 *
 */
public class ClaimFileDO {
	private int id;

	private int claimId;

	private String fileName;

	/**
	 * 
	 */
	public ClaimFileDO() {
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
	public int getClaimId() {
		return claimId;
	}

	/**
	 * @param claimId the claimId to set
	 */
	public void setClaimId(int claimId) {
		this.claimId = claimId;
	}

	/**
	 * @return the fileName
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * @param fileName the fileName to set
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
}
