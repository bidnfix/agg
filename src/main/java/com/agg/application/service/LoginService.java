package com.agg.application.service;

import com.agg.application.entity.Account;
import com.agg.application.model.LoginForm;

public interface LoginService {

	Iterable<Account> getUserDetails();

	Account authenticateUser(LoginForm loginForm) throws Exception;

}
