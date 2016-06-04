package com.agg.application.utils;

import java.util.Comparator;

import com.agg.application.model.UserMenuDO;

public class UserMenuComparator implements Comparator<UserMenuDO> {

	@Override
	public int compare(UserMenuDO userMenuDO1, UserMenuDO userMenuDO2) {
		return userMenuDO1.compareTo(userMenuDO2);
	}

}
