/**
 * htamada
 */
package com.agg.application.utils;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.thymeleaf.context.Context;

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
	private UserService userService;
	private Context context;
	private EmailSender emailSender;
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
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

	@Override
	public void run() {
		String subject = String.format("Claim Request for Pre-authorization – %s – %s", context.getVariables().get("claimNo").toString(), 
				context.getVariables().get("dealerName").toString());
		AccountDO accountDO = new AccountDO();
		RoleDO role = new RoleDO();
		role.setAccountType(AggConstants.ACCOUNT_TYPE_ADMIN);
		accountDO.setRoleDO(role);
		List<UserDO> adminAccounts = userService.getUsers(accountDO);
		if(null != adminAccounts && !adminAccounts.isEmpty()){
			for(UserDO admin: adminAccounts){
				context.setVariable("userFirstName", admin.getFirstName());
				//admin.getEmail();
				EmailStatus emailStatus = emailSender.sendMailAsHtml("hariverma.tamada@gmail.com", subject, "email/fileaclaim-template", context);
				logger.info("email status: "+emailStatus.isSuccess());
				if(emailStatus.isSuccess()){
					logger.info(String.format("New claim %s from dealer %s, approval mail send to admin %s  ", context.getVariables().get("claimNo").toString(), 
							context.getVariables().get("dealerName").toString(), emailStatus.getTo(), "successfully"));
				}
			}
		}
	}
}
