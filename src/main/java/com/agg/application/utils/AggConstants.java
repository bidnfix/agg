package com.agg.application.utils;

public class AggConstants {
	
	public static final int ACTIVE = 1;
	
	public static final int PENDING = 2;
	
	public static final int TERMINATED = 0;
	
	public static final String DEALER_ADMIN = "Dealer Admin";
	
	public static final String ACCOUNT_TYPE_DEALER = "dealer";
	
	public static final String ACCOUNT_TYPE_ADMIN = "admin";
	
	public static final String SEQUENCE_TYPE_DEALER = "dealer";
	
	public static final char[] CHARSET_AZ_09 = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();
	
	public static final int QUOTE_ID_LENGTH = 5;
	
	public static final String QUOTE_STATUS_ESTIMATING_PRICE = "Estimating Price";
	
	public static final String QUOTE_STATUS_PURCHASE_REQUESTED = "Purchase Requested";
	
	public static final String QUOTE_STATUS_INVOICED = "Invoiced";
	
	public static final String QUOTE_STATUS_ACRHIVE = "Archive";
	
	public static final String MACHINE_STATUS_USED = "Used";
	
	public static final String MACHINE_STATUS_NEW= "New";
	
	public static final int CLAIM_STATUS_OPEN = 1;
	
	public static final int CLAIM_STATUS_PRE_AUTHORIZED_REQUESTED = 2;
	
	public static final int CLAIM_STATUS_SUBMITTED = 3;
	
	public static final int CLAIM_STATUS_CLOSED = 4;
	
	public static final int CLAIM_STATUS_PRE_AUTHORIZED_APPROVED = 5;
	
	public static final int CLAIM_STATUS_PRE_AUTHORIZED_REJECTED = 6;
	
	public static final int CLAIM_STATUS_PRE_AUTHORIZED_APPROVED_WITH_ADJUSMENTS = 7;
	
	public static final int CLAIM_STATUS_PENDING = 8;
	
	public static final int CLAIM_STATUS_DRAFT = 9;
	
	public static final String QUOTE_REPORT_OUT_STANDING_DESC = "Thank you for considering AgGuard coverage, we appreciate the opportunity to earn your trust. "
			+ "This quote reflects the information provided in your request for an extended service Contract based on the current terms and "
			+ "conditions provided on our website. Our pricing is dynamic, so it is possible the price will increase after your initial quote request; "
			+ "however, we guarantee this price for at least 90 days so that you can work out other aspects of the deal. If the price goes down, "
			+ "then you will get the new, lower price. If you need more than a 90-day price lock, then please let us know and we will try to accommodate your needs. "
			+ "It is important for you to double-check the details reflected on this quote for accuracy. Inaccurate information may result in an incorrect quote "
			+ "and void the price guarantee.";
	
	public static final String QUOTE_REPORT_COVERAGE_DESC = " Coverage begins upon the acceptance of any Outstanding Conditions (noted above) and the receipt of good funds. "
			+ "Our responsibility begins when the Manufacturer's coverage ends. Coverage is for the specified time period in months or hours of use, "
			+ "whichever is reached first.";
	
	
	
	
}
