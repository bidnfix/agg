/**
 * htamada
 */
package com.agg.application.vo;

/**
 * @author htamada
 *
 */
public class ClaimPreAuthVO {
	private int id;
	private String cStatus;
	private String extComment;
	private byte cStatusValue;
	/**
	 * 
	 */
	public ClaimPreAuthVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * @return the cStatusValue
	 */
	public byte getcStatusValue() {
		return cStatusValue;
	}

	/**
	 * @param cStatusValue the cStatusValue to set
	 */
	public void setcStatusValue(byte cStatusValue) {
		this.cStatusValue = cStatusValue;
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
	 * @return the cStatus
	 */
	public String getcStatus() {
		return cStatus;
	}
	/**
	 * @param cStatus the cStatus to set
	 */
	public void setcStatus(String cStatus) {
		this.cStatus = cStatus;
	}
	/**
	 * @return the extComment
	 */
	public String getExtComment() {
		return extComment;
	}
	/**
	 * @param extComment the extComment to set
	 */
	public void setExtComment(String extComment) {
		this.extComment = extComment;
	}
	
}
