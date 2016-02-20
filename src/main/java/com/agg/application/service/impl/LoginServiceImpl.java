package com.agg.application.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agg.application.dao.UserDAO;
import com.agg.application.entity.Account;
import com.agg.application.model.AccountDO;
import com.agg.application.model.LoginForm;
import com.agg.application.service.LoginService;

@Service
public class LoginServiceImpl implements LoginService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private UserDAO userDAO;

	@Override
	public Iterable<Account> getUserDetails() {
		System.out.println(userDAO.findAll());
		return userDAO.findAll();
	}

	@Override
	public AccountDO authenticateUser(LoginForm loginForm) throws Exception {
		logger.debug("In authenticateUser");
		Account account = userDAO.findByUserNameAndPassword(loginForm.getUsername(), loginForm.getPassword())
				.orElseThrow(() -> new Exception("User doesn't exist"));
		AccountDO accountDO = new AccountDO();
		accountDO.setUsername(account.getUserName());
		accountDO.setStatus(account.getStatus());
		//accountDO.setRoleName(account.getRole().getRTitle());
		return accountDO;
	}

}
