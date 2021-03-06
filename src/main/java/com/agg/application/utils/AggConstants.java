package com.agg.application.utils;

public class AggConstants {
	
	public static final int ACTIVE = 1;
	
	public static final int PENDING = 2;
	
	public static final int TERMINATED = 0;
	
	public static final String DEALER_ADMIN = "Dealer Admin";
	
	public static final String ROLE_ADMIN = "admin";
	
	public static final String ACCOUNT_TYPE_DEALER = "dealer";
	
	public static final String ACCOUNT_TYPE_ADMIN = "admin";
	
	public static final String SEQUENCE_TYPE_DEALER = "dealer";
	
	public static final char[] CHARSET_AZ_09 = "ABCDFGHJKLMNPQRSTUVWXYZ123456789".toCharArray();
	
	public static final int QUOTE_ID_LENGTH = 5;
	
	public static final String QUOTE_STATUS_ESTIMATING_PRICE = "Estimating Price";
	
	public static final String QUOTE_STATUS_PURCHASE_REQUESTED = "Purchase Requested";
	
	public static final String QUOTE_STATUS_INVOICED = "Invoiced";
	
	public static final String QUOTE_STATUS_ACRHIVE = "Archive";
	
	public static final String QUOTE_STATUS_CLOSED = "Closed";
	
	public static final byte B_QUOTE_STATUS_ESTIMATING_PRICE = 1;
	
	public static final byte B_QUOTE_STATUS_PURCHASE_REQUESTED = 4;
	
	public static final byte B_QUOTE_STATUS_INVOICED = 5;
	
	public static final byte B_QUOTE_STATUS_ACRHIVE = 1;
	
	public static final byte B_QUOTE_STATUS_UNACRHIVE = 0;
	
	public static final byte B_QUOTE_STATUS_CLOSED = 6;
	
	public static final byte B_ACTIVE_CONTRACT = 1;
	
	public static final byte B_INACTIVE_CONTRACT = 2;
	
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
	
	public static final int CLAIM_STATUS_CANCEL = 10;
	
	public static final int CLAIM_STATUS_APPROVED_FOR_PAYMENT = 11;
	
	public static final String CLAIM_STATUS_PRE_AUTHORIZED_APPROVED_DESC = "Pre-authorization Approved";
	
	public static final String CLAIM_STATUS_PRE_AUTHORIZED_REJECTED_DESC = "Pre-authorization Rejected";
	
	public static final String CLAIM_STATUS_PRE_AUTHORIZED_APPROVED_WITH_ADJUSMENTS_DESC = "Pre-authorization Approved with Adjustments";
	
	public static final String CLAIM_STATUS_CLOSED_DESC = "Closed";
	
	public static final String CLAIM_STATUS_OPEN_DESC = "Open";
	
	public static final String CLAIM_STATUS_PRE_AUTHORIZED_REQUESTED_DESC = "Pre-authorization Requested";
	
	public static final String CLAIM_STATUS_SUBMITTED_DESC = "Submitted";
	
	public static final String CLAIM_STATUS_PENDING_DESC = "Pending";
	
	public static final String CLAIM_STATUS_DRAFT_DESC = "Draft";
	
	public static final String CLAIM_STATUS_CANCEL_DESC = "Rejected";
	
	public static final String CLAIM_STATUS_APPROVED_FOR_PAYMENT_DESC = "Approved for Payment";
	
	public static final String COVERAGE_TYPE_PT = "PT";
	
	public static final String COVERAGE_TYPE_PH = "PH";
	
	public static final String COVERAGE_TYPE_PL = "PL";
	
	public static final String COVERAGE_TYPE_PT_DESC = "Powertrain";
	
	public static final String COVERAGE_TYPE_PH_DESC = "Powertrain + Hydraulic";
	
	public static final String COVERAGE_TYPE_PL_DESC = "Powertrain + Hydraulic + Platform";
	
	public static final int COVERAGE_TYPE_PT_MONTHS = 24;
	
	public static final int COVERAGE_TYPE_PH_MONTHS = 24;
	
	public static final int COVERAGE_TYPE_PL_MONTHS = 24;
	
	public static final int COVERAGE_TYPE_PT_HOURS = 2000;
	
	public static final int COVERAGE_TYPE_PH_HOURS = 2000;
	
	public static final int COVERAGE_TYPE_PL_HOURS = 2000;
	
	public static final char EXTERNAL_CLAIM_NOTE = 'E';
	
	public static final char INTERNAL_CLAIM_NOTE = 'I';
	
	public static final String REPORT_TYPE_INVOICE = "invoice";
	
	public static final int QUOTE_EXPIRATION_DAYS = 90;
	
	public static final int SESSION_EXPIRATION_TIMEOUT = 14400; //in seconds - 4 hrs
	
	public static final int TOP_CLAIMS_LIMIT = 10;
	
	public static final int REPORT_PREVIOUS_YEARS_COUNT = 3;
	
	public static final int ZERO_HOUR_EXPIRATION_HOURS = 2000;
	
	public static final int MAX_EXPIRATION_HOURS = 5000;
	
	
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
	
	public static final String INVOICE_REPORT_OUT_STANDING_DESC = "Thank you for choosing AgGuard, we appreciate your business. This invoice reflects the information "
			+ "provided in your request for an extended service Contract based on the current terms and conditions provided on our website. "
			+ "It is extremely important for you to double-check the details reflected on this invoice before remitting payment. Inaccurate information may void the "
			+ "extended service Contract.";
	
	
	
	
}
