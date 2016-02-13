package com.agg.application.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agg.application.dao.UserDAO;
import com.agg.application.entity.Account;
import com.agg.application.model.LoginForm;
import com.agg.application.service.LoginService;

@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	private UserDAO userDAO;

	@Override
	public Iterable<Account> getUserDetails() {
		System.out.println(userDAO.findAll());
		return userDAO.findAll();
	}

	@Override
	public Account authenticateUser(LoginForm loginForm) throws Exception {
		return userDAO.findByUserNameAndPassword(loginForm.getUsername(), loginForm.getPassword()).orElseThrow(() -> new Exception("User doesn't exist"));
	}

}
