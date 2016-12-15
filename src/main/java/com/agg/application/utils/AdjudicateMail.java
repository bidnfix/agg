/**
 * htamada
 */
package com.agg.application.utils;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.thymeleaf.context.Context;

import com.agg.application.controller.ClaimsController;
import com.agg.application.entity.Claims;
import com.agg.application.model.AccountDO;
import com.agg.application.model.ClaimMailDO;
import com.agg.application.model.ClaimsDO;
import com.agg.application.model.RoleDO;
import com.agg.application.model.UserDO;
import com.agg.application.service.ClaimsService;
import com.agg.application.service.UserService;

/**
 * @author htamada
 *
 */
public class AdjudicateMail implements Runnable{
	private UserService userService;
	private Context context;
	private EmailSender emailSender;
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private List<String> toEmailList;
	private ClaimsService claimsService;
	private String appUrl;
	
	public List<String> getToEmailList() {
		return toEmailList;
	}

	public void setToEmailList(List<String> toEmailList) {
		this.toEmailList = toEmailList;
	}

	public ClaimsService getClaimsService() {
		return claimsService;
	}

	public void setClaimsService(ClaimsService claimsService) {
		this.claimsService = claimsService;
	}

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

	public AdjudicateMail(){
	}
	
	
	public Context getContext() {
		return context;
	}

	public void setContext(Context context) {
		this.context = context;
	}

	public EmailSender getEmailSender() {
		return emailSender;
	}

	public void setEmailSender(EmailSender emailSender) {
		this.emailSender = emailSender;
	}
	
	public String getAppUrl() {
		return appUrl;
	}

	public void setAppUrl(String appUrl) {
		this.appUrl = appUrl;
	}

	@Override
	public void run() {
		String subject = String.format("%s - Claim Request for Pre-authorization Decision", context.getVariables().get("claimNo").toString());
		
		int id = Integer.parseInt(context.getVariables().get("claimNo").toString());
		ClaimsDO claimsDO = claimsService.getClaim(id);
		ClaimsController con = new ClaimsController();
		int partsCost = con.calcTotalLaborsCost(claimsDO.getClaimLaborDO());
		int laborsCost = con.calcTotalPartsCost(claimsDO.getClaimPartDO());
		int otherCost = claimsDO.getRequestedOtherCharges1() + claimsDO.getRequestedOtherCharges2();
		
		if(toEmailList == null){
			toEmailList = new ArrayList<String>();
		}
		if(claimsDO.getDealerDO() != null && claimsDO.getDealerDO().getInvoiceEmail() != null && !claimsDO.getDealerDO().getInvoiceEmail().isEmpty()){
			toEmailList.add(claimsDO.getDealerDO().getInvoiceEmail());
		}
		
		context.setVariable("claimNo", claimsDO.getClaimId());
		context.setVariable("contractNo", claimsDO.getContractId());
		context.setVariable("totalLaborCost", laborsCost);
		context.setVariable("totalPartsCost", partsCost);
		context.setVariable("totalOtherCost", otherCost);
		context.setVariable("totalClaimCost", (partsCost + laborsCost + otherCost));
		context.setVariable("deductible", claimsDO.getContractDO().getDeductible());
		context.setVariable("lol", claimsDO.getContractDO().getLol());
		context.setVariable("availableLol", claimsDO.getContractDO().getAvailabeLol());
		context.setVariable("externalComments", "");
		
		String statusDesc = "";
		if(claimsDO.getcStatus() != 0){
			if(claimsDO.getcStatus() == AggConstants.CLAIM_STATUS_PRE_AUTHORIZED_APPROVED){
				statusDesc = AggConstants.CLAIM_STATUS_PRE_AUTHORIZED_APPROVED_DESC;
			}else if(claimsDO.getcStatus() == AggConstants.CLAIM_STATUS_PRE_AUTHORIZED_REJECTED){
				statusDesc = AggConstants.CLAIM_STATUS_PRE_AUTHORIZED_REJECTED_DESC;
			}else if(claimsDO.getcStatus() == AggConstants.CLAIM_STATUS_PRE_AUTHORIZED_APPROVED_WITH_ADJUSMENTS){
				statusDesc = AggConstants.CLAIM_STATUS_PRE_AUTHORIZED_APPROVED_WITH_ADJUSMENTS_DESC;
			}
		}
		context.setVariable("status", statusDesc);
		
		
		if(null != toEmailList && !toEmailList.isEmpty()){
			for(String toEmail: toEmailList){
				//admin.getEmail();
				EmailStatus emailStatus = emailSender.sendMailAsHtml(toEmail, subject, "email/claim-preauth-template", context);
				logger.info("email status: "+emailStatus.isSuccess());
				logger.info(String.format("ClaimRequest for Pre-authorization email sent to %s : %s ", toEmail, emailStatus.isSuccess() ? "successful" : "failed"));
			}
		}
	}
}
