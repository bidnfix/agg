package com.agg.application.service;

import com.agg.application.entity.Account;
import com.agg.application.model.AccountDO;
import com.agg.application.model.LoginForm;

public interface LoginService {

	Iterable<Account> getUserDetails();

	AccountDO authenticateUser(LoginForm loginForm) throws Exception;

}
