package com.agg.application.utils;

import java.util.ArrayList;
import java.util.List;

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
}
