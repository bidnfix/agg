package com.agg.application.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agg.application.dao.UserDAO;
import com.agg.application.entity.User;
import com.agg.application.service.LoginService;

@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	private UserDAO userDAO;
	
	@Override
	public Iterable<User> getUserDetails() {
		System.out.println(userDAO.findAll());
		return userDAO.findAll();
	}

}
