package com.agg.application.utils;

import java.util.ArrayList;
import java.util.List;

public class Util {
	
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
}
