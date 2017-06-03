/**
 * htamada
 */
package com.agg.application.utils;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.thymeleaf.context.Context;

import com.agg.application.model.AccountDO;
import com.agg.application.service.UserService;

/**
 * @author htamada
 *
 */
public class ClaimMail implements Runnable{
	private UserService userService;
	private Context context;
	private EmailSender emailSender;
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private String toEmail;
	
	private String adminName;
	
	
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
	
	public String getToEmail() {
		return toEmail;
	}

	public void setToEmail(String toEmail) {
		this.toEmail = toEmail;
	}
	
	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	@Override
	public void run() {
		String subject = String.format("Claim Request for Pre-authorization – %s – %s", context.getVariables().get("claimNo").toString(), 
				context.getVariables().get("dealerName").toString());
		List<AccountDO> adminAccounts = userService.getAdminDetails();
		if(null != adminAccounts && !adminAccounts.isEmpty()){
			logger.debug("adminAccounts size: "+adminAccounts.size());
			context.setVariable("userFirstName", adminAccounts.get(0).getFirstName());
			//admin.getEmail();
			EmailStatus emailStatus = emailSender.sendMailAsHtml(toEmail, subject, "email/fileaclaim-template", context);
			logger.info("email status: "+emailStatus.isSuccess());
			if(emailStatus.isSuccess()){
				logger.info(String.format("New claim %s from dealer %s, approval mail send to admin %s  ", context.getVariables().get("claimNo").toString(), 
						context.getVariables().get("dealerName").toString(), emailStatus.getTo(), "successfully"));
			}
		}
	}
}
