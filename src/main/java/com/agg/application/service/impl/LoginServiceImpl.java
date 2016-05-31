package com.agg.application.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agg.application.dao.UserDAO;
import com.agg.application.entity.Account;
import com.agg.application.entity.UserMenus;
import com.agg.application.entity.UserRoleMenus;
import com.agg.application.entity.UserSubMenus;
import com.agg.application.model.AccountDO;
import com.agg.application.model.LoginForm;
import com.agg.application.model.UserMenuDO;
import com.agg.application.model.UserSubMenuDO;
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
		accountDO.setRoleName(account.getRole().getRTitle());
		accountDO.setFirstName(account.getFirstName());
		accountDO.setLastName(account.getLastName());
		
		List<UserRoleMenus> userRoleMenuList = account.getRole().getUserRoleMenuses();
		
		UserMenuDO userMenuDO = null;
		UserSubMenuDO userSubMenuDO = null;
		Set<UserMenuDO> userMenuDOSet = new HashSet<UserMenuDO>();
		Set<UserSubMenuDO> userSubMenuDOSet = null;
		UserMenus userMenus = null;
		List<UserSubMenus> userSubMenusList = null;
		for(UserRoleMenus userRoleMenus : userRoleMenuList){
			userMenus = userRoleMenus.getUserMenus();
			userMenuDO = new UserMenuDO();
			userMenuDO.setId(userMenus.getId());
			userMenuDO.setName(userMenus.getName());
			userMenuDO.setUrl(userMenus.getNavUrl());
			
			userSubMenuDOSet = new HashSet<UserSubMenuDO>();
			userSubMenusList = userMenus.getUserSubMenuses();
			for(UserSubMenus userSubMenus : userSubMenusList){
				userSubMenuDO = new UserSubMenuDO();
				userSubMenuDO.setId(userSubMenus.getId());
				userSubMenuDO.setName(userSubMenus.getName());
				userSubMenuDO.setUrl(userSubMenus.getNavUrl());
				
				userSubMenuDOSet.add(userSubMenuDO);
			}
			userMenuDO.setUserSubMenuDOSet(userSubMenuDOSet);
			
			userMenuDOSet.add(userMenuDO);
		}
		
		accountDO.setUserMenuDOSet(userMenuDOSet);
		
		return accountDO;
	}

}
