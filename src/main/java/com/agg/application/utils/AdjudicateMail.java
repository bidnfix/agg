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
	private double tra;
	private double customerOwes;
	private int id;
	private int dealerId;
	
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
	
	/**
	 * @return the tra
	 */
	public double getTra() {
		return tra;
	}

	/**
	 * @param tra the tra to set
	 */
	public void setTra(double tra) {
		this.tra = tra;
	}

	/**
	 * @return the customerOwes
	 */
	public double getCustomerOwes() {
		return customerOwes;
	}

	/**
	 * @param customerOwes the customerOwes to set
	 */
	public void setCustomerOwes(double customerOwes) {
		this.customerOwes = customerOwes;
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
	
	public int getDealerId() {
		return dealerId;
	}

	public void setDealerId(int dealerId) {
		this.dealerId = dealerId;
	}

	@Override
	public void run() {
		ClaimsDO claimsDO = claimsService.getClaim(id, dealerId);
		ClaimsController con = new ClaimsController();
		double laborsCost = con.calcTotalLaborsCost(claimsDO.getClaimLaborDO());
		double partsCost = con.calcTotalPartsCost(claimsDO.getClaimPartDO());
		double otherCost = claimsDO.getRequestedOtherCharges1() + claimsDO.getRequestedOtherCharges2();
		
		String subject = String.format("AgGuard – %s – Adjudication Complete", claimsDO.getClaimId());
		
		if(toEmailList == null){
			toEmailList = new ArrayList<String>();
		}
		if(claimsDO.getDealerDO() != null && claimsDO.getDealerDO().getInvoiceEmail() != null && !claimsDO.getDealerDO().getInvoiceEmail().isEmpty()){
			toEmailList.add(claimsDO.getDealerDO().getInvoiceEmail());
		}
		
		Locale locale = new Locale("en", "US");
		NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(locale);
		
		Context context = new Context();
		context.setVariable("appUrl", appUrl);
		context.setVariable("claimNo", claimsDO.getClaimId());
		context.setVariable("contractNo", claimsDO.getContractId());
		context.setVariable("reqLaborCost", currencyFormat.format(laborsCost));
		context.setVariable("adjLaborCost", currencyFormat.format(claimsDO.getTotalAdjustedLaborCost()));
		context.setVariable("reqPartsCost", currencyFormat.format(partsCost));
		context.setVariable("adjPartsCost", currencyFormat.format(claimsDO.getTotalAdjustedPartsCost()));
		context.setVariable("reqOther1Cost", currencyFormat.format(claimsDO.getRequestedOtherCharges1()));
		context.setVariable("adjOther1Cost", currencyFormat.format(claimsDO.getApprovedOtherCharges1()));
		context.setVariable("reqOther2Cost", currencyFormat.format(claimsDO.getRequestedOtherCharges2()));
		context.setVariable("adjOther2Cost", currencyFormat.format(claimsDO.getApprovedOtherCharges2()));
		context.setVariable("totalReqClaimCost", currencyFormat.format((partsCost + laborsCost + otherCost)));
		context.setVariable("totalAdjAmount", currencyFormat.format((claimsDO.getTotalAdjustedLaborCost() + claimsDO.getTotalAdjustedPartsCost() + claimsDO.getApprovedOtherCharges1()+claimsDO.getApprovedOtherCharges2())));
		context.setVariable("tra", currencyFormat.format(tra));
		context.setVariable("availableLol", currencyFormat.format(claimsDO.getContractDO().getAvailabeLol()));
		
		String statusDesc = "";
		if(claimsDO.getcStatus() != 0){
			if(claimsDO.getcStatus() == AggConstants.CLAIM_STATUS_CLOSED){
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
