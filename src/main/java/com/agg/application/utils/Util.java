package com.agg.application.utils;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
	
	public static boolean isNotEmptyString(final String value){
		return (null != value && !value.trim().equals(EMPTY));
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
}
