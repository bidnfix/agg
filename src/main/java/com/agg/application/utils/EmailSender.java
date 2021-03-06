package com.agg.application.utils;

import javax.activation.DataSource;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Component
public class EmailSender {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	 
    @Autowired
    private JavaMailSender javaMailSender;
    
    @Autowired
    private TemplateEngine templateEngine;
    
    @Value("${spring.mail.username}")
	private String fromEmail;
 
    public EmailStatus sendMailAsText(String to, String subject, String text) {
        return sendMail(to, subject, text, false, null, null);
    }
    
    public EmailStatus sendMailAsHtml(String to, String subject, String templateName, Context context) {
        String body = templateEngine.process(templateName, context);
        return sendHtmlMail(to, subject, body);
    }
    
    public EmailStatus sendMailAsAttachment(String to, String subject, String body, DataSource aAttachment, String attachmentName) {
    	 DataSource[] aAttachments = {aAttachment};
    	 String[] attachmentNames = {attachmentName};
        return sendMail(to, subject, body, false, aAttachments, attachmentNames);
    }
    
    public EmailStatus sendMailAsAttachment(String to, String subject, String body, DataSource[] aAttachment, String[] attachmentName) {
        return sendMail(to, subject, body, false, aAttachment, attachmentName);
    }
 
    private EmailStatus sendHtmlMail(String to, String subject, String htmlBody) {
        return sendMail(to, subject, htmlBody, true, null, null);
    }
 
    private EmailStatus sendMail(String to, String subject, String text, Boolean isHtml, DataSource[] aAttachments, String[] attachmentNames) {
        try {
            MimeMessage mail = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mail, true);
            helper.setFrom(fromEmail);
            helper.setTo(InternetAddress.parse(to));
            helper.setSubject(subject);
            helper.setText(text, isHtml);
            if(aAttachments != null){
            	if(aAttachments.length == attachmentNames.length){
            		for(int i=0; i<aAttachments.length; i++){
            			helper.addAttachment(attachmentNames[i], aAttachments[i]);
            		}
            	}else{
            		throw new Exception("Files and its names length are not matching");
            	}
            	
            }
            javaMailSender.send(mail);
            logger.info("Send email '{}' to: {}", subject, to);
            return new EmailStatus(to, subject, text).success();
        } catch (Exception e) {
        	logger.error("Exception occured while sending the email ", e);
        	logger.error(String.format("Problem with sending email to: {}, error message: {}", to, e.getMessage()));
            return new EmailStatus(to, subject, text).error(e.getMessage());
        }
    }
    
}
