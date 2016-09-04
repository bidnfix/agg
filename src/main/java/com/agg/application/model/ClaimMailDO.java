/**
 * htamada
 */
package com.agg.application.model;

import java.util.List;

/**
 * @author htamada
 *
 */
public class ClaimMailDO {
	private String userFirstName;
	private String claimID;
	private String dealerName;
	private String contractId;
	private String totalLaborCost;
	private String totalPartsCost;
	private String totalOtherCosts;
	private String totalClaimCost;
	private String deductible;
	private String lol;
	private String availableLol;
	private String extComments;
	/**
	 * @return the userFirstName
	 */
	public String getUserFirstName() {
		return userFirstName;
	}
	/**
	 * @param userFirstName the userFirstName to set
	 */
	public void setUserFirstName(String userFirstName) {
		this.userFirstName = userFirstName;
	}
	/**
	 * @return the claimID
	 */
	public String getClaimID() {
		return claimID;
	}
	/**
	 * @param claimID the claimID to set
	 */
	public void setClaimID(String claimID) {
		this.claimID = claimID;
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
	 * @return the totalLaborCost
	 */
	public String getTotalLaborCost() {
		return totalLaborCost;
	}
	/**
	 * @param totalLaborCost the totalLaborCost to set
	 */
	public void setTotalLaborCost(String totalLaborCost) {
		this.totalLaborCost = totalLaborCost;
	}
	/**
	 * @return the totalPartsCost
	 */
	public String getTotalPartsCost() {
		return totalPartsCost;
	}
	/**
	 * @param totalPartsCost the totalPartsCost to set
	 */
	public void setTotalPartsCost(String totalPartsCost) {
		this.totalPartsCost = totalPartsCost;
	}
	public void setTotalPartsCost(List<ClaimPartDO> partsDO) {
		this.totalPartsCost = "";
		int tpc = 0;
		for(ClaimPartDO part: partsDO){
			tpc += part.getQty() * part.getUnitPrice();
		}
		this.totalPartsCost = String.valueOf(tpc);
	}
	/**
	 * @return the totalOtherCosts
	 */
	public String getTotalOtherCosts() {
		return totalOtherCosts;
	}
	/**
	 * @param totalOtherCosts the totalOtherCosts to set
	 */
	public void setTotalOtherCosts(String totalOtherCosts) {
		this.totalOtherCosts = totalOtherCosts;
	}
	/**
	 * @return the totalClaimCost
	 */
	public String getTotalClaimCost() {
		int cost = Integer.parseInt(totalLaborCost) + Integer.parseInt(totalPartsCost) + Integer.parseInt(totalOtherCosts);
		setTotalClaimCost(String.valueOf(cost));
		return this.totalClaimCost;
	}
	/**
	 * @param totalClaimCost the totalClaimCost to set
	 */
	public void setTotalClaimCost(String totalClaimCost) {
		this.totalClaimCost = totalClaimCost;
	}
	/**
	 * @return the deductible
	 */
	public String getDeductible() {
		return deductible;
	}
	/**
	 * @param deductible the deductible to set
	 */
	public void setDeductible(String deductible) {
		this.deductible = deductible;
	}
	/**
	 * @return the lol
	 */
	public String getLol() {
		return lol;
	}
	/**
	 * @param lol the lol to set
	 */
	public void setLol(String lol) {
		this.lol = lol;
	}
	/**
	 * @return the availableLol
	 */
	public String getAvailableLol() {
		return availableLol;
	}
	/**
	 * @param availableLol the availableLol to set
	 */
	public void setAvailableLol(String availableLol) {
		this.availableLol = availableLol;
	}
	/**
	 * @return the extComments
	 */
	public String getExtComments() {
		return extComments;
	}
	/**
	 * @param extComments the extComments to set
	 */
	public void setExtComments(String extComments) {
		this.extComments = extComments;
	}
	
}
