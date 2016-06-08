package com.agg.application.utils;

import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
 
    public EmailStatus sendMailAsText(String to, String subject, String text) {
        return sendMail(to, subject, text, false);
    }
    
    public EmailStatus sendMailAsHtml(String to, String subject, String templateName, Context context) {
        String body = templateEngine.process(templateName, context);
        return sendHtmlMail(to, subject, body);
    }
 
    private EmailStatus sendHtmlMail(String to, String subject, String htmlBody) {
        return sendMail(to, subject, htmlBody, true);
    }
 
    private EmailStatus sendMail(String to, String subject, String text, Boolean isHtml) {
        try {
            MimeMessage mail = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mail, true);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(text, isHtml);
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