/**
 * htamada
 */
package com.agg.application.utils;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.thymeleaf.context.Context;

import com.agg.application.controller.ClaimsController;
import com.agg.application.model.ClaimsDO;
import com.agg.application.service.ClaimsService;

/**
 * @author htamada
 *
 */
public class AdjudicateMail implements Runnable{
	private EmailSender emailSender;
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private List<String> toEmailList;
	private ClaimsService claimsService;
	private String appUrl;
	private ClaimsDO claimsDO;
	
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

	public AdjudicateMail(){
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
	
	public ClaimsDO getClaimsDO() {
		return claimsDO;
	}

	public void setClaimsDO(ClaimsDO claimsDO) {
		this.claimsDO = claimsDO;
	}

	@Override
	public void run() {
		ClaimsDO claimsDOO = claimsService.getClaim(claimsDO.getId());
		ClaimsController con = new ClaimsController();
		int laborsCost = con.calcTotalLaborsCost(claimsDOO.getClaimLaborDO());
		int partsCost = con.calcTotalPartsCost(claimsDOO.getClaimPartDO());
		int otherCost = claimsDOO.getRequestedOtherCharges1() + claimsDOO.getRequestedOtherCharges2();
		
		String subject = String.format("AgGuard – %s – Adjudication Complete", claimsDOO.getClaimId());
		
		if(toEmailList == null){
			toEmailList = new ArrayList<String>();
		}
		if(claimsDOO.getDealerDO() != null && claimsDOO.getDealerDO().getInvoiceEmail() != null && !claimsDOO.getDealerDO().getInvoiceEmail().isEmpty()){
			toEmailList.add(claimsDOO.getDealerDO().getInvoiceEmail());
		}
		
		Locale locale = new Locale("en", "US");
		NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(locale);
		
		Context context = new Context();
		context.setVariable("appUrl", appUrl);
		context.setVariable("claimNo", claimsDOO.getClaimId());
		context.setVariable("contractNo", claimsDOO.getContractId());
		context.setVariable("reqLaborCost", currencyFormat.format(laborsCost));
		context.setVariable("adjLaborCost", currencyFormat.format(claimsDO.getTotalAdjustedLaborCost()));
		context.setVariable("reqPartsCost", currencyFormat.format(partsCost));
		context.setVariable("adjPartsCost", currencyFormat.format(claimsDO.getTotalAdjustedPartsCost()));
		context.setVariable("reqOther1Cost", currencyFormat.format(claimsDOO.getRequestedOtherCharges1()));
		context.setVariable("adjOther1Cost", currencyFormat.format(claimsDO.getRequestedOtherCharges1()));
		context.setVariable("reqOther2Cost", currencyFormat.format(claimsDOO.getRequestedOtherCharges2()));
		context.setVariable("adjOther2Cost", currencyFormat.format(claimsDO.getRequestedOtherCharges2()));
		context.setVariable("totalReqClaimCost", currencyFormat.format((partsCost + laborsCost + otherCost)));
		context.setVariable("totalAdjAmount", currencyFormat.format((claimsDO.getTotalAdjustedLaborCost() + claimsDO.getTotalAdjustedPartsCost() + claimsDO.getRequestedOtherCharges1()+claimsDO.getRequestedOtherCharges2())));
		context.setVariable("tra", claimsDO.getTra());
		context.setVariable("availableLol", claimsDOO.getContractDO().getAvailabeLol());
		
		String statusDesc = "";
		if(claimsDOO.getcStatus() != 0){
			if(claimsDOO.getcStatus() == AggConstants.CLAIM_STATUS_CLOSED){
				statusDesc = AggConstants.CLAIM_STATUS_CLOSED_DESC;
			}
		}
		context.setVariable("status", statusDesc);
		
		
		if(null != toEmailList && !toEmailList.isEmpty()){
			for(String toEmail: toEmailList){
				//admin.getEmail();
				EmailStatus emailStatus = emailSender.sendMailAsHtml(toEmail, subject, "email/adjudicateaclaim-template", context);
				logger.info("email status: "+emailStatus.isSuccess());
				logger.info(String.format("ClaimRequest for Adjucate-Close email sent to %s : %s ", toEmail, emailStatus.isSuccess() ? "successful" : "failed"));
			}
		}
	}
}
