package com.agg.application.utils;

import java.net.MalformedURLException;
import java.net.URL;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

public class Util {
	private static final String EMPTY = ""; 
	
	public static <T> List<T> toList(Iterable<T> iterable) {
		if (iterable instanceof List) {
			return (List<T>) iterable;
		}
		ArrayList<T> list = new ArrayList<T>();
		if (iterable != null) {
			for (T e : iterable) {
				list.add(e);
			}
		}
		return list;
	}
	
	/**
	 * 
	 * @param value
	 * @param string
	 * @return
	 */
	public static boolean isNotEmptyString(final String value){
		return (null != value && !value.trim().equals(EMPTY));
	}
	
	/**
	 * sets empty string as default value
	 */
	public static String setDefaultStringValue(final String string){
		return setDefaultStringValue(string, EMPTY);
	}
	
	/**
	 * sets given value string as default value to string
	 * @param string
	 * @param value
	 */
	public static String setDefaultStringValue(final String string, final String value){
		return isNotEmptyString(string)?string:value;
	}
	
	/**
	 * @param characterSet
	 * @param length
	 * @return String
	 */
	public static String getQuoteId(char[] characterSet, int length) {
        Random random = new SecureRandom();
        char[] result = new char[length];
        for (int i = 0; i < result.length; i++) {
            // picks a random index out of character set > random character
            int randomCharIndex = random.nextInt(characterSet.length);
            result[i] = characterSet[randomCharIndex];
        }
        return new String(result);
    }
	
	/**
	 * 
	 */
	public static byte getClaimStatusCode(final String cStatus){
		byte cStatusValue = 0;
		switch(cStatus.toLowerCase()){
			case "open": 
				cStatusValue = AggConstants.CLAIM_STATUS_OPEN;
				break;
			case "pre_authorized_requested":
				cStatusValue = AggConstants.CLAIM_STATUS_PRE_AUTHORIZED_REQUESTED;
				break;
			case "submitted":
				cStatusValue = AggConstants.CLAIM_STATUS_SUBMITTED;
				break;
			case "closed":
				cStatusValue = AggConstants.CLAIM_STATUS_CLOSED;
				break;
			case "pre_authorized_approved":
				cStatusValue = AggConstants.CLAIM_STATUS_PRE_AUTHORIZED_APPROVED;
				break;
			case "pre_authorized_rejected":
				cStatusValue = AggConstants.CLAIM_STATUS_PRE_AUTHORIZED_REJECTED;
				break;
			case "pre_authorized_approved_with_adjustments":
				cStatusValue = AggConstants.CLAIM_STATUS_PRE_AUTHORIZED_APPROVED_WITH_ADJUSMENTS;
				break;
			case "pending":
				cStatusValue = AggConstants.CLAIM_STATUS_PENDING;
				break;
			case "draft":
				cStatusValue = AggConstants.CLAIM_STATUS_DRAFT;
				break;
			case "cancel":
				cStatusValue = AggConstants.CLAIM_STATUS_CANCEL;
				break;
			case "approved_for_payment":
				cStatusValue = AggConstants.CLAIM_STATUS_APPROVED_FOR_PAYMENT;
				break;
			default:
				cStatusValue = 0;
		}
		return cStatusValue;
	}
	
	public static String getClaimStatusValue(final int cStatusCode){
		String cStatusValue = null;
		switch(cStatusCode){
			case AggConstants.CLAIM_STATUS_OPEN: 
				cStatusValue = AggConstants.CLAIM_STATUS_OPEN_DESC;
				break;
			case AggConstants.CLAIM_STATUS_PRE_AUTHORIZED_REQUESTED:
				cStatusValue = AggConstants.CLAIM_STATUS_PRE_AUTHORIZED_REQUESTED_DESC;
				break;
			case AggConstants.CLAIM_STATUS_SUBMITTED:
				cStatusValue = AggConstants.CLAIM_STATUS_SUBMITTED_DESC;
				break;
			case AggConstants.CLAIM_STATUS_CLOSED:
				cStatusValue = AggConstants.CLAIM_STATUS_CLOSED_DESC;
				break;
			case AggConstants.CLAIM_STATUS_PRE_AUTHORIZED_APPROVED:
				cStatusValue = AggConstants.CLAIM_STATUS_PRE_AUTHORIZED_APPROVED_DESC;
				break;
			case AggConstants.CLAIM_STATUS_PRE_AUTHORIZED_REJECTED:
				cStatusValue = AggConstants.CLAIM_STATUS_PRE_AUTHORIZED_REJECTED_DESC;
				break;
			case AggConstants.CLAIM_STATUS_PRE_AUTHORIZED_APPROVED_WITH_ADJUSMENTS:
				cStatusValue = AggConstants.CLAIM_STATUS_PRE_AUTHORIZED_APPROVED_WITH_ADJUSMENTS_DESC;
				break;
			case AggConstants.CLAIM_STATUS_PENDING:
				cStatusValue = AggConstants.CLAIM_STATUS_PENDING_DESC;
				break;
			case AggConstants.CLAIM_STATUS_DRAFT:
				cStatusValue = AggConstants.CLAIM_STATUS_DRAFT_DESC;
				break;
			case AggConstants.CLAIM_STATUS_CANCEL:
				cStatusValue = AggConstants.CLAIM_STATUS_CANCEL_DESC;
				break;
			case AggConstants.CLAIM_STATUS_APPROVED_FOR_PAYMENT:
				cStatusValue = AggConstants.CLAIM_STATUS_APPROVED_FOR_PAYMENT_DESC;
				break;
			default:
				cStatusValue = null;
		}
		return cStatusValue;
	}
	
	/**
	 * @param request
	 * @return
	 */
	public static String getBaseURL(HttpServletRequest request) {
		String url = "";
		try {
			url = new URL(request.getScheme(), 
			        request.getServerName(), 
			        request.getServerPort(), 
			        request.getContextPath()).toString();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return url;
	}
	
	/**
	 * @param startCal
	 * @param endCal
	 * @return int
	 */
	public static int getMonthDifference(Calendar startCal, Calendar endCal){
		int diffYear = endCal.get(Calendar.YEAR) - startCal.get(Calendar.YEAR);
		int diffMonth = (diffYear * 12) + (endCal.get(Calendar.MONTH) - startCal.get(Calendar.MONTH));
		
		return diffMonth;
	}
}
