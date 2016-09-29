/**
 * htamada
 */
package com.agg.application.utils;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.agg.application.model.AccountDO;
import com.agg.application.model.ClaimMailDO;
import com.agg.application.model.RoleDO;
import com.agg.application.model.UserDO;
import com.agg.application.service.UserService;

/**
 * @author htamada
 *
 */
public class ClaimMail implements Runnable{
	private ClaimMailDO claimMailDO;
	private String subject = "Claim Request for Pre-authorization – %s – %s";
	private String toMailAddress;
	private UserService userService;
	
	@Autowired
	EmailSender emailSender;
	
	/**
	 * @return the userService
	 */
	public UserService getUserService() {
		return userService;
	}

	/**
	 * @param userService the userService to set
	 */
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public ClaimMail(){
	}
	
	@Override
	public void run() {
		AccountDO accountDO = new AccountDO();
		RoleDO role = new RoleDO();
		role.setAccountType(AggConstants.ACCOUNT_TYPE_ADMIN);
		accountDO.setRoleDO(role);
		List<UserDO> adminAccounts = userService.getUsers(accountDO);
		if(null != adminAccounts && !adminAccounts.isEmpty()){
			for(UserDO admin: adminAccounts){
				claimMailDO.setUserFirstName(admin.getFirstName());
				toMailAddress=admin.getEmail();
				EmailStatus emailStatus = emailSender.sendMailAsText(toMailAddress, 
						String.format(subject, claimMailDO.getClaimID(), claimMailDO.getDealerName()), buildClaimMailBody());
			}
		}
	}
	
	private String buildClaimMailBody(){
		StringBuilder bodyStrBuilder = new StringBuilder();
		bodyStrBuilder.append("<html><body>");
		bodyStrBuilder.append(String.format("Dear %s<br>", claimMailDO.getUserFirstName()));
		bodyStrBuilder.append("&nbsp;&nbsp;&nbsp;&nbsp;This is to inform you that a dealer has submitted a request for Pre-authorization. Below are the information. Please review and take a decision on AgGuard application.<br>");
		bodyStrBuilder.append("<br>");
		bodyStrBuilder.append(String.format("Claim#:  %s<br>", claimMailDO.getClaimID()));
		bodyStrBuilder.append(String.format("Dealer Name: %s<br>", claimMailDO.getDealerName()));
		bodyStrBuilder.append(String.format("Contract #: %s<br>", claimMailDO.getContractId()));
		bodyStrBuilder.append(String.format("Total Labor Cost: %s<br>", claimMailDO.getTotalLaborCost()));
		bodyStrBuilder.append(String.format("Total Parts Cost: %s<br>", claimMailDO.getTotalPartsCost()));
		bodyStrBuilder.append(String.format("Total Other Costs: %s<br>", claimMailDO.getTotalOtherCosts()));
		bodyStrBuilder.append(String.format("Total Claim Cost: %s<br>", claimMailDO.getTotalClaimCost()));
		bodyStrBuilder.append(String.format("Deductible: %s<br>", claimMailDO.getDeductible()));
		bodyStrBuilder.append(String.format("LOL: %s<br>", claimMailDO.getLol()));
		bodyStrBuilder.append(String.format("Available LOL: %s<br>", claimMailDO.getAvailableLol()));
		bodyStrBuilder.append(String.format("External Comments: %s<br>", claimMailDO.getExtComments()));
		bodyStrBuilder.append("<br>");
		bodyStrBuilder.append("Regards,<br>");
		bodyStrBuilder.append("AgGuard Admin<br>");
		bodyStrBuilder.append("</body></html>");
		return bodyStrBuilder.toString();
	}
	/**
	 * @return the claimMailDO
	 */
	public ClaimMailDO getClaimMailDO() {
		return claimMailDO;
	}
	/**
	 * @param claimMailDO the claimMailDO to set
	 */
	public void setClaimMailDO(ClaimMailDO claimMailDO) {
		this.claimMailDO = claimMailDO;
	}
	/**
	 * @return the subject
	 */
	public String getSubject() {
		return subject;
	}
	/**
	 * @param subject the subject to set
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}

	/**
	 * @return the toMailAddress
	 */
	public String getToMailAddress() {
		return toMailAddress;
	}

	/**
	 * @param toMailAddress the toMailAddress to set
	 */
	public void setToMailAddress(String toMailAddress) {
		this.toMailAddress = toMailAddress;
	}

}
