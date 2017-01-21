package com.agg.application.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agg.application.dao.UserDAO;
import com.agg.application.dao.UserRoleSubMenusDAO;
import com.agg.application.entity.Account;
import com.agg.application.entity.AccountType;
import com.agg.application.entity.Dealer;
import com.agg.application.entity.Role;
import com.agg.application.entity.UserMenus;
import com.agg.application.entity.UserRoleMenus;
import com.agg.application.entity.UserRoleSubMenus;
import com.agg.application.entity.UserSubMenus;
import com.agg.application.model.AccountDO;
import com.agg.application.model.LoginForm;
import com.agg.application.model.RoleDO;
import com.agg.application.model.UserMenuDO;
import com.agg.application.model.UserSubMenuDO;
import com.agg.application.service.LoginService;
import com.agg.application.utils.AggConstants;

@Service
public class LoginServiceImpl implements LoginService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private UserRoleSubMenusDAO userRoleSubMenusDAO;

	@Override
	public Iterable<Account> getUserDetails() {
		System.out.println(userDAO.findAll());
		return userDAO.findAll();
	}

	@Override
	public AccountDO authenticateUser(LoginForm loginForm) throws Exception {
		logger.debug("In authenticateUser");
		Account account = userDAO.findByUserNameAndPasswordAndStatus(loginForm.getUsername(), loginForm.getPassword(), AggConstants.ACTIVE)
				.orElseThrow(() -> new Exception("User doesn't exist"));
		
		Role role = account.getRole();
		AccountType accountType = role.getAccountType();
		
		AccountDO accountDO = new AccountDO();
		accountDO.setUsername(account.getUserName());
		accountDO.setStatus(account.getStatus());
		accountDO.setRoleName(role.getRTitle());
		accountDO.setFirstName(account.getFirstName());
		accountDO.setLastName(account.getLastName());
		accountDO.setId(account.getId());
		
		Dealer dealer = account.getDealer();
		if(dealer != null){
			accountDO.setDealerId(dealer.getId());
		}
		
		
		RoleDO roleDO = new RoleDO();
		roleDO.setId(role.getRId());
		roleDO.setAccountTypeId(accountType.getId());
		roleDO.setName(role.getRTitle());
		roleDO.setAccountType(accountType.getAccountType());
		
		accountDO.setRoleDO(roleDO);
		
		List<UserRoleMenus> userRoleMenuList = account.getRole().getUserRoleMenuses();
		
		UserMenuDO userMenuDO = null;
		UserSubMenuDO userSubMenuDO = null;
		List<UserMenuDO> userMenuDOList = new ArrayList<UserMenuDO>();
		List<UserSubMenuDO> userSubMenuDOList = null;
		UserMenus userMenus = null;
		List<UserRoleSubMenus> userRoleSubMenusList = null;
		UserSubMenus userSubMenus = null;
		for(UserRoleMenus userRoleMenus : userRoleMenuList){
			userMenus = userRoleMenus.getUserMenus();
			if(userMenus.getStatus() == AggConstants.ACTIVE){
				userMenuDO = new UserMenuDO();
				userMenuDO.setId(userMenus.getId());
				userMenuDO.setName(userMenus.getName());
				userMenuDO.setUrl(userMenus.getNavUrl());
				
				userSubMenuDOList = new ArrayList<UserSubMenuDO>();
				userRoleSubMenusList = userRoleSubMenusDAO.findByRoleRIdAndUserMenusId(userRoleMenus.getRole().getRId(),
						userMenus.getId());
				for(UserRoleSubMenus userRoleSubMenus : userRoleSubMenusList){
					userSubMenus = userRoleSubMenus.getUserSubMenus();
					if(userSubMenus.getStatus() == AggConstants.ACTIVE){
						userSubMenuDO = new UserSubMenuDO();
						userSubMenuDO.setId(userSubMenus.getId());
						userSubMenuDO.setName(userSubMenus.getName());
						userSubMenuDO.setUrl(userSubMenus.getNavUrl());
						
						userSubMenuDOList.add(userSubMenuDO);
					}
				}
				Collections.sort(userSubMenuDOList);
				userMenuDO.setUserSubMenuDOList(userSubMenuDOList);
				
				userMenuDOList.add(userMenuDO);
			}
		}
		
		Collections.sort(userMenuDOList);
		
		
		accountDO.setUserMenuDOList(userMenuDOList);
		
		return accountDO;
	}

}
