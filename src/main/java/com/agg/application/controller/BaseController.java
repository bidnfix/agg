package com.agg.application.controller;

import javax.servlet.http.HttpServletRequest;

import com.agg.application.model.AccountDO;

public class BaseController {

	public boolean sessionExists(HttpServletRequest request) {
		AccountDO account = (AccountDO) request.getSession().getAttribute("user");
		return account != null;
	}
	
	public void removeSession(HttpServletRequest request) {
		request.getSession().invalidate();
	}
}
