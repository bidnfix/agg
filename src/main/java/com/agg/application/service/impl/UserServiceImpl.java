package com.agg.application.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agg.application.dao.AccountDAO;
import com.agg.application.dao.DealerDAO;
import com.agg.application.dao.RoleDAO;
import com.agg.application.entity.Account;
import com.agg.application.entity.Dealer;
import com.agg.application.entity.Role;
import com.agg.application.model.AccountDO;
import com.agg.application.model.DealerDO;
import com.agg.application.model.RoleDO;
import com.agg.application.model.UserDO;
import com.agg.application.service.UserService;
import com.agg.application.utils.AggConstants;
import com.agg.application.utils.Util;

@Service
public class UserServiceImpl implements UserService {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	DealerDAO dealerDAO;
	
	@Autowired
	RoleDAO roleDAO;
	
	@Autowired
	AccountDAO accountDAO;
	
	@Override
	public List<UserDO> getUsers(AccountDO accountDetails) {
		logger.debug("In getUsers"); ;
		List<Account> accountList = null;
		if(accountDetails.getRoleDO().getAccountType().equalsIgnoreCase(AggConstants.ACCOUNT_TYPE_ADMIN)){
			accountList = Util.toList(accountDAO.findAll());
		}else{
			Account account = accountDAO.findOne(accountDetails.getId());
			if(account != null){
				accountList = Util.toList(accountDAO.findByDealerId(account.getDealer().getId()));
			}
		}
		
		
		return getUserDOList(accountList);
	}
	
	public List<AccountDO> getAdminDetails(){
		List<AccountDO> accountDOList = null;
		List<Account> accounts = accountDAO.findByRoleRTitle(AggConstants.ROLE_ADMIN);
		if(accounts != null){
			accountDOList = new ArrayList<AccountDO>();
			AccountDO accountDO = null;
			for(Account account : accounts){
				accountDO = new AccountDO();
				accountDO.setFirstName(account.getFirstName());
				accountDO.setLastName(account.getLastName());
				accountDO.setId(account.getId());
				accountDO.setRoleName(account.getRole().getRTitle());
				accountDO.setStatus(account.getStatus());
				accountDO.setUsername(account.getUserName());
				
				accountDOList.add(accountDO);
			}
			
		}
			
		return accountDOList;
	}
	
	@Override
	public UserDO getUser(long userId) {
		logger.debug("In getUser");
		Account account = accountDAO.findOne(userId);
		UserDO userDO = null;
		Role role = null;
		RoleDO roleDO = null;
		if(account != null){
			userDO = new UserDO();
			userDO.setId(account.getId());
			userDO.setUserName(account.getUserName());
			userDO.setFirstName(account.getFirstName());
			userDO.setLastName(account.getLastName());
			
			role = account.getRole();
			roleDO = new RoleDO();
			roleDO.setId(role.getRId());
			roleDO.setName(role.getRTitle());
			userDO.setRoleDO(roleDO);
			
			userDO.setStatus(account.getStatus());
			userDO.setUserType(account.getAccountType().getAccountType());
			
			Dealer dealer = account.getDealer();
			if(dealer != null){
				DealerDO dealerDO = new DealerDO();
				dealerDO.setName(dealer.getName());
				dealerDO.setId(dealer.getId());
				dealerDO.setCode(dealer.getCode());
				userDO.setDealerDO(dealerDO);
			}
		}
		
		return userDO;
	}
	
	/**
	 * @param accountList
	 * @return List<DealerDO>
	 */
	private List<UserDO> getUserDOList(List<Account> accountList){
		List<UserDO> userDOList = null;
		if(accountList != null && !accountList.isEmpty()){
			userDOList = new ArrayList<UserDO>();
			UserDO userDO = null;
			RoleDO roleDO = null;
			Role role = null;
			for(Account account : accountList){
				userDO = new UserDO();
				userDO.setId(account.getId());
				userDO.setUserName(account.getUserName());
				userDO.setFirstName(account.getFirstName());
				userDO.setLastName(account.getLastName());
				//userDO.setPassword(account.getPassword());
				
				role = account.getRole();
				roleDO = new RoleDO();
				roleDO.setId(role.getRId());
				roleDO.setName(role.getRTitle());
				userDO.setRoleDO(roleDO);
				
				userDO.setStatus(account.getStatus());
				userDO.setUserType(account.getAccountType().getAccountType());
				
				Dealer dealer = account.getDealer();
				if(dealer != null){
					DealerDO dealerDO = new DealerDO();
					dealerDO.setName(dealer.getName());
					dealerDO.setId(dealer.getId());
					dealerDO.setCode(dealer.getCode());
					userDO.setDealerDO(dealerDO);
				}
				
				userDOList.add(userDO);
			}
		}
		
		return userDOList;
	}

	@Override
	public long editUser(UserDO userDO, AccountDO accountDetails) {
		logger.debug("Inside editUser");
		Account account = accountDAO.findOne(userDO.getId());
		account.setStatus(userDO.getStatus());
		account.setFirstName(userDO.getFirstName());
		account.setLastName(userDO.getLastName());
		account.setUpdatedDate(new Date());
		account.setUpdatedBy(accountDetails.getUsername());
		Role role = roleDAO.findOne(userDO.getRoleDO().getId());
		account.setRole(role);
		
		account = accountDAO.save(account);
		
		return account.getId();
	}

	/*@Override
	public long saveDealerUser(UserDO userDO, AccountDO accountDO) {
		logger.debug("In saveDealerUser method");
		
		Timestamp date = new Timestamp(new Date().getTime());
		
		Role role = roleDAO.findOne(userDO.getRoleDO().getId());
		Account account = new Account();
		account.setUserName(userDO.getUserName());
		account.setPassword(userDO.getPassword());
		account.setFirstName(userDO.getFirstName());
		account.setLastName(userDO.getLastName());
		account.setAccountType(role.getAccountType());
		account.setRole(role);
		account.setCreatedDate(date);
		account.setCreatedBy(accountDO.getUsername());
		account.setUpdatedDate(date);
		account.setLastLoginDate(date);
		account.setStatus(1);
		account.setUpdatedBy(accountDO.getUsername());
		account.setDealer(dealerDAO.findOne(userDO.getDealerDO().getId()));
		
		account = accountDAO.save(account);
		
		return account.getId();
	}*/

}

