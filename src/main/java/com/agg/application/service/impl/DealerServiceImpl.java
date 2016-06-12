package com.agg.application.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;

import com.agg.application.dao.AccountDAO;
import com.agg.application.dao.DealerDAO;
import com.agg.application.dao.LocationDAO;
import com.agg.application.dao.RoleDAO;
import com.agg.application.dao.SequenceDAO;
import com.agg.application.entity.Account;
import com.agg.application.entity.Dealer;
import com.agg.application.entity.Location;
import com.agg.application.entity.Role;
import com.agg.application.entity.Sequence;
import com.agg.application.model.AccountDO;
import com.agg.application.model.DealerDO;
import com.agg.application.model.LocationDO;
import com.agg.application.model.RoleDO;
import com.agg.application.model.UserDO;
import com.agg.application.service.DealerService;
import com.agg.application.utils.AggConstants;
import com.agg.application.utils.EmailSender;
import com.agg.application.utils.EmailStatus;
import com.agg.application.utils.Util;

@Service
public class DealerServiceImpl implements DealerService {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Value("${admin.email}")
	private String adminEmail;
	
	@Autowired
	DealerDAO dealerDAO;
	
	@Autowired
	LocationDAO locationDAO;
	
	@Autowired
	RoleDAO roleDAO;
	
	@Autowired
	AccountDAO accountDAO;
	
	@Autowired
	SequenceDAO sequenceDAO;
	
	@Autowired
	EmailSender emailSender;
	
	@Override
	public List<DealerDO> getDealers() {
		logger.debug("In getDealers");
		List<Dealer> dealerList = Util.toList(dealerDAO.findAll());
		
		return getDealerDOList(dealerList);
	}
	
	/*@Override
	public List<DealerDO> getAdminDealers() {
		logger.debug("In getPrograms");
		
		List<Dealer> dealerList = dealerDAO.findAll();
		
		return getDealerDOList(dealerList);
	}*/
	
	@Override
	public List<DealerDO> getActiveDealers(AccountDO accountDO) {
		logger.debug("In getActiveDealers");
		List<Dealer> dealerList = null;
		if(accountDO.getRoleDO().getAccountType().equalsIgnoreCase(AggConstants.ACCOUNT_TYPE_ADMIN)){
			dealerList = dealerDAO.findByStatus(AggConstants.ACTIVE);
		}else{
			Account account = accountDAO.findOne(accountDO.getId());
			if(account != null){
				dealerList = new ArrayList<Dealer>();
				dealerList.add(account.getDealer());
			}
		}
		
		return getDealerDOList(dealerList);
	}
	
	@Override
	public List<DealerDO> getPendingDealers() {
		logger.debug("In getPendingDealers");
		List<Dealer> dealerList = Util.toList(dealerDAO.findByStatus(AggConstants.PENDING));
		
		return getDealerDOList(dealerList);
	}
	
	@Override
	public List<DealerDO> getParentDealers() {
		logger.debug("In getParentDealers");
		List<Dealer> dealerList = Util.toList(dealerDAO.findParentDealers());
		
		return getDealerDOList(dealerList);
	}
	
	/**
	 * @param dealerList
	 * @return List<DealerDO>
	 */
	private List<DealerDO> getDealerDOList(List<Dealer> dealerList){
		List<DealerDO> dealerDOList = null;
		if(dealerList != null && !dealerList.isEmpty()){
			dealerDOList = new ArrayList<DealerDO>();
			DealerDO dealerDO = null;
			RoleDO roleDO = null;
			Role role = null;
			List<Account> accounts = null;
			
			for(Dealer dealer : dealerList){
				dealerDO = new DealerDO();
				dealerDO.setId(dealer.getId());
				dealerDO.setCode(dealer.getCode());
				accounts = dealer.getAccounts();
				for(Account account : accounts){
					role = account.getRole();
					if(role.getRTitle().equalsIgnoreCase(AggConstants.DEALER_ADMIN)){
						dealerDO.setUserName(account.getUserName());
						dealerDO.setFirstName(account.getFirstName());
						dealerDO.setLastName(account.getLastName());
						
						roleDO = new RoleDO();
						roleDO.setId(role.getRId());
						roleDO.setName(role.getRTitle());
						dealerDO.setRoleDO(roleDO);
						dealerDO.setRoleName(role.getRTitle());
						
						break;
					}
				}
				
				dealerDO.setName(dealer.getName());
				dealerDO.setAddress1(dealer.getAddress());
				dealerDO.setAddress2(dealer.getAddress2());
				dealerDO.setCity(dealer.getCity());
				dealerDO.setInvoiceEmail(dealer.getInvoiceEmail());
				dealerDO.setMarketEmail(dealer.getMarketEmail());
				dealerDO.setPhone(dealer.getPhone());
				dealerDO.setState(dealer.getState());
				dealerDO.setZip(dealer.getZip());
				dealerDO.setStatus(dealer.getStatus());
				dealerDO.setParentCode(dealer.getParentCode());
				
				dealerDOList.add(dealerDO);
			}
		}
		
		return dealerDOList;
	}

	@Override
	@Transactional
	public long saveDealer(DealerDO dealerDO, AccountDO accountDO, boolean dealerRegistration) {
		logger.debug("In saveDealer");
		Dealer dealer = new Dealer();
		Timestamp date = new Timestamp(new Date().getTime());
		dealer.setAddress(dealerDO.getAddress1());
		dealer.setAddress2(dealerDO.getAddress2());
		dealer.setCity(dealerDO.getCity());
		dealer.setName(dealerDO.getName());
		
		dealer.setInvoiceEmail(dealerDO.getInvoiceEmail());
		//TODO
		dealer.setLastUpdate(date);
		dealer.setMarketEmail(dealerDO.getMarketEmail());
		dealer.setNotes(dealerDO.getNotes());
		dealer.setPhone(dealerDO.getPhone());
		
		Sequence sequence = sequenceDAO.findBySeqType(AggConstants.SEQUENCE_TYPE_DEALER);
		logger.debug("sequenceId: "+sequence.getSeqValue());
		long dealerCode = Long.valueOf(dealerDO.getZip().substring(0, 2)+sequence.getSeqValue());
		logger.debug("dealerCode: "+dealerCode);
		dealer.setCode(dealerCode);
		dealer.setParentCode(dealerCode);
		Role role = null;
		if(dealerRegistration){
			dealer.setStatus(2);
			List<Role> roleList = roleDAO.findByRTitle(AggConstants.DEALER_ADMIN);
			if(roleList != null){
				role = roleList.get(0);
			}
		}else{
			dealer.setStatus(1);
			role = roleDAO.findOne(dealerDO.getRoleDO().getId());
		}
		
		dealer.setState(dealerDO.getState());
		dealer.setUrl(dealerDO.getDealerUrl());
		dealer.setZip(dealerDO.getZip());
		dealer.setActiveDate(date);
		dealer.setLastUpdate(date);
		
		Account account = new Account();
		account.setUserName(dealerDO.getUserName());
		account.setPassword(dealerDO.getPassword());
		account.setFirstName(dealerDO.getFirstName());
		account.setLastName(dealerDO.getLastName());
		account.setAccountType(role.getAccountType());
		account.setRole(role);
		account.setCreatedDate(date);
		account.setUpdatedDate(date);
		account.setLastLoginDate(date);
		if(dealerRegistration){
			account.setStatus(AggConstants.TERMINATED);
		}else{
			account.setStatus(AggConstants.ACTIVE);
		}
		if(accountDO != null){
			account.setCreatedBy(accountDO.getUsername());
			account.setUpdatedBy(accountDO.getUsername());
		}else{
			account.setCreatedBy("");
			account.setUpdatedBy("");
		}
		
		account.setDealer(dealer);
		
		sequence.setSeqValue(sequence.getSeqValue()+1);
		sequenceDAO.save(sequence);
		
		account = accountDAO.save(account);
		
		logger.info("dealerId: "+dealer.getId());
		if(dealer.getId() > 0){
			Context context = new Context();
			context.setVariable("dealerName", dealerDO.getName());
			context.setVariable("dealerCode", dealerCode);
			context.setVariable("dealerFirstName", dealerDO.getFirstName());
			context.setVariable("dealerLastName", dealerDO.getLastName());
			context.setVariable("dealerPhone", dealerDO.getPhone());
			context.setVariable("dealerMarketEmail", dealerDO.getMarketEmail());
			
			logger.info("b4 Email sending to the dealer: "+dealer.getName());
			//sending email to dealer
			EmailStatus emailStatus = emailSender.sendMailAsHtml(dealer.getMarketEmail(), "Dealer Registration", "email/dealer-registration-template", context);
			if(emailStatus != null){
				logger.info("email status: "+emailStatus.isSuccess());
				if(emailStatus.isSuccess()){
					logger.info("Dealer Registration Email Send succssfully to dealer: "+emailStatus.getTo());
				}
			}
			
			//sending email to admin
			logger.info("Admin Email: "+adminEmail);
			emailStatus = emailSender.sendMailAsHtml(adminEmail, "Dealer Registration", "email/dealer-registration-admin-template", context);
			if(emailStatus != null){
				logger.info("email status: "+emailStatus.isSuccess());
				if(emailStatus.isSuccess()){
					logger.info("Dealer Registration Email Send succssfully to admin: "+emailStatus.getTo());
				}
			}
		}
		
		return dealer.getId();
	}
	
	@Override
	@Transactional
	public long editDealer(DealerDO dealerDO, AccountDO accountDO) {
		logger.debug("In editDealer with dealerId: "+dealerDO.getId());
		Dealer dealer = dealerDAO.findOne(dealerDO.getId());
		Timestamp date = new Timestamp(new Date().getTime());
		dealer.setAddress(dealerDO.getAddress1());
		dealer.setAddress2(dealerDO.getAddress2());
		dealer.setCity(dealerDO.getCity());
		dealer.setName(dealerDO.getName());
		
		dealer.setInvoiceEmail(dealerDO.getInvoiceEmail());
		//TODO
		dealer.setLastUpdate(date);
		dealer.setMarketEmail(dealerDO.getMarketEmail());
		dealer.setNotes(dealerDO.getNotes());
		dealer.setPhone(dealerDO.getPhone());
		
		
		dealer.setCode(dealerDO.getCode());
		dealer.setParentCode((dealerDO.getParentDealerDO() != null)?dealerDO.getParentDealerDO().getCode():dealerDO.getParentCode());
		dealer.setState(dealerDO.getState());
		dealer.setUrl(dealerDO.getDealerUrl());
		dealer.setZip(dealerDO.getZip());
		//dealer.setActiveDate(date);
		//dealer.setLastUpdate(date);
		
		//updating pending and termination accounts
		if(dealerDO.getStatus() == 0 || dealer.getStatus() == 2){
			List<Account> accounts = dealer.getAccounts();
			for(Account account : accounts){
				account.setStatus(dealerDO.getStatus());
			}
		//updating only dealer account
		}else if(dealerDO.getStatus() == 1){
			List<Account> accounts = dealer.getAccounts();
			for(Account account : accounts){
				if(account.getRole().getRTitle().equalsIgnoreCase(AggConstants.DEALER_ADMIN)){
					account.setStatus(dealerDO.getStatus());
				}
			}
		}
		dealer.setStatus(dealerDO.getStatus());
		
		/*Role role = roleDAO.findOne(dealerDO.getRoleDO().getId());
		Account account = dealer.getAccount();
		account.setUserName(dealerDO.getUserName());
		account.setPassword(dealerDO.getPassword());
		account.setFirstName(dealerDO.getFirstName());
		account.setLastName(dealerDO.getLastName());
		account.setAccountType(role.getAccountType());
		account.setRole(role);
		account.setCreatedDate(date);
		account.setCreatedBy(accountDO.getUsername());
		account.setUpdatedDate(date);
		account.setLastLoginDate(date);
		account.setStatus(1);
		account.setUpdatedBy(accountDO.getUsername());
		
		account.setDealer(dealer);
		
		account = accountDAO.save(account);
		*/
		dealer = dealerDAO.save(dealer);
		
		return dealer.getId();
	}

	@Override
	public DealerDO getDealer(long id) {
		Dealer dealer = dealerDAO.findOne((long)id);
		DealerDO dealerDO = null;
		List<Account> accounts = null;
		Role role =null;
		RoleDO roleDO = null;
		if(dealer != null){
			dealerDO = new DealerDO();
			dealerDO.setId(dealer.getId());
			accounts = dealer.getAccounts();
			for(Account account : accounts){
				role = account.getRole();
				if(role.getRTitle().equalsIgnoreCase(AggConstants.DEALER_ADMIN)){
					dealerDO.setUserName(account.getUserName());
					dealerDO.setFirstName(account.getFirstName());
					dealerDO.setLastName(account.getLastName());
					dealerDO.setPassword(account.getPassword());
					
					roleDO = new RoleDO();
					roleDO.setId(role.getRId());
					roleDO.setName(role.getRTitle());
					dealerDO.setRoleDO(roleDO);
					dealerDO.setRoleName(role.getRTitle());
					
					break;
				}
			}

			dealerDO.setAddress1(dealer.getAddress());
			dealerDO.setAddress2(dealer.getAddress2());
			dealerDO.setCity(dealer.getCity());
			dealerDO.setCode(dealer.getCode());
			dealerDO.setInvoiceEmail(dealer.getInvoiceEmail());
			dealerDO.setMarketEmail(dealer.getMarketEmail());
			dealerDO.setPhone(dealer.getPhone());
			dealerDO.setParentCode(dealer.getParentCode());
			dealerDO.setState(dealer.getState());
			dealerDO.setZip(dealer.getZip());
			dealerDO.setStatus(dealer.getStatus());
			dealerDO.setName(dealer.getName());
			dealerDO.setDealerUrl(dealer.getUrl());
			dealerDO.setNotes(dealer.getNotes());
			
			Dealer parentDealer = dealerDAO.findByCode(dealer.getParentCode());
			DealerDO parentDealerDO = new DealerDO();
			parentDealerDO.setCode(parentDealer.getCode());
			parentDealerDO.setName(parentDealer.getName());
			parentDealerDO.setParentCode(parentDealer.getParentCode());
			
			dealerDO.setParentDealerDO(parentDealerDO);
			
		}
		
		return dealerDO;
	}

	@Override
	public long saveLocation(LocationDO locationDO) {
		logger.debug("In saveLocation");
		Location location = new Location();
		location.setCreatedBy(locationDO.getDealerDO().getUserName());
		location.setIsHead((locationDO.isHeadQuarter())?(byte)1:(byte)0);
		location.setlAddress1(locationDO.getAddress1());
		location.setlAddress2(locationDO.getAddress1());
		location.setLCity(locationDO.getCity());
		location.setLCountry("USA");
		location.setLEmail(locationDO.getEmail());
		location.setLIsArchived((byte)0);
		location.setLLastUpdate(new Timestamp(new Date().getTime()));
		location.setDealer(dealerDAO.findOne(locationDO.getDealerDO().getId()));
		location.setLPhone(locationDO.getPhone());
		location.setLState(locationDO.getState());
		location.setLTitle(locationDO.getTitle());
		location.setLUrl(locationDO.getLocationUrl());
		location.setLZip(locationDO.getZip());
		
		location = locationDAO.save(location);
		
		logger.info("locationId: "+location.getLId());
		
		return location.getLId();
	}

	@Override
	public List<RoleDO> getDealerAdminRoles() {
		logger.debug("Inside getDealerAdminRoles");
		List<Role> roleList = roleDAO.findByRTitle(AggConstants.DEALER_ADMIN);
		List<RoleDO> roleDOList = null;
		if(roleList != null && !roleList.isEmpty()){
			RoleDO roleDO = null;
			roleDOList = new ArrayList<RoleDO>();
			for(Role role : roleList){
				roleDO = new RoleDO();
				roleDO.setAccountTypeId(role.getAccountType().getId());
				roleDO.setId(role.getRId());
				roleDO.setName(role.getRTitle());
				
				roleDOList.add(roleDO);
			}
			
			logger.info("roleDOList size: "+roleDOList.size());
		}
		return roleDOList;
	}

	@Override
	public List<RoleDO> getDealerRoles() {
		logger.debug("Inside getDealerAdminRoles");
		List<Role> roleList = roleDAO.findByAccountTypeAccountType(AggConstants.ACCOUNT_TYPE_DEALER);
		List<RoleDO> roleDOList = null;
		if(roleList != null && !roleList.isEmpty()){
			RoleDO roleDO = null;
			roleDOList = new ArrayList<RoleDO>();
			for(Role role : roleList){
				if(!role.getRTitle().equalsIgnoreCase(AggConstants.DEALER_ADMIN)){
					roleDO = new RoleDO();
					roleDO.setAccountTypeId(role.getAccountType().getId());
					roleDO.setId(role.getRId());
					roleDO.setName(role.getRTitle());
					
					roleDOList.add(roleDO);
				}
			}
			
			logger.info("roleDOList size: "+roleDOList.size());
		}
		return roleDOList;
	}
	
	/*@Override
	public List<RoleDO> getDealerRoles(long dealerId) {
		logger.debug("Inside getDealerAdminRoles");
		Dealer dealer = dealerDAO.findOne(dealerId);
		List<Role> roleList = roleDAO.findByAccountTypeAccountType(ACCOUNT_TYPE_DEALER);
		List<RoleDO> roleDOList = null;
		if(roleList != null && !roleList.isEmpty()){
			RoleDO roleDO = null;
			roleDOList = new ArrayList<RoleDO>();
			for(Role role : roleList){
				if(dealer.getAccount().getRole().getRTitle().equalsIgnoreCase(DEALER_ADMIN)){
					if(role.getRTitle().equalsIgnoreCase(DEALER_ADMIN)){
						roleDO = new RoleDO();
						roleDO.setAccountTypeId(role.getAccountType().getId());
						roleDO.setId(role.getRId());
						roleDO.setName(role.getRTitle());
						
						roleDOList.add(roleDO);
						break;
					}
				}else{
					if(!role.getRTitle().equalsIgnoreCase(DEALER_ADMIN)){
						roleDO = new RoleDO();
						roleDO.setAccountTypeId(role.getAccountType().getId());
						roleDO.setId(role.getRId());
						roleDO.setName(role.getRTitle());
						
						roleDOList.add(roleDO);
					}
				}
			}
			
			logger.info("roleDOList size: "+roleDOList.size());
		}
		return roleDOList;
	}*/

	@Override
	public List<LocationDO> getDealerLocations(long dealerId) {
		logger.debug("In getDealerLocations with dealerId: "+dealerId);
		List<Location> locationList = locationDAO.findByDealerId(dealerId);
		List<LocationDO> locationDOList = null;
		if(locationList != null && !locationList.isEmpty()){
			locationDOList = new ArrayList<LocationDO>();
			LocationDO locationDO = null;
			for(Location location : locationList){
				locationDO = new LocationDO();
				locationDO.setId(location.getLId());
				locationDO.setTitle(location.getLTitle());
				
				locationDOList.add(locationDO);
			}
		}
		
		return locationDOList;
	}

	@Override
	public long saveDealerUser(UserDO userDO, AccountDO accountDO) {
		logger.debug("In saveDealerUser method");
		
		Timestamp date = new Timestamp(new Date().getTime());
		
		/*Dealer dealer = new Dealer();
		dealer.setAddress(userDO.getAddress1());
		dealer.setAddress2(userDO.getAddress2());
		dealer.setCity(userDO.getCity());
		dealer.setInvoiceEmail(userDO.getEmail());
		dealer.setLastUpdate(date);
		dealer.setMarketEmail(userDO.getEmail());
		dealer.setPhone(userDO.getPhone());
		
		Sequence sequence = sequenceDAO.findBySeqType(SEQUENCE_TYPE_DEALER);
		
		dealer.setCode(Long.valueOf(userDO.getZip().substring(0, 2)+sequence.getSeqValue()));
		dealer.setStatus(1);
		dealer.setState(userDO.getState());
		dealer.setUrl(userDO.getUrl());
		dealer.setZip(userDO.getZip());
		dealer.setActiveDate(date);
		dealer.setLastUpdate(date);*/
		
		Role role = roleDAO.findOne(userDO.getRoleDO().getId());
		//Location location = locationDAO.findOne(userDO.getLocationDO().getId());
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
		account.setStatus(AggConstants.ACTIVE);
		account.setUpdatedBy(accountDO.getUsername());
		account.setDealer(dealerDAO.findOne(userDO.getDealerDO().getId()));
		
		/*sequence.setSeqValue(sequence.getSeqValue()+1);
		sequenceDAO.save(sequence);*/
		
		account = accountDAO.save(account);
		
		return account.getId();
	}

	@Override
	public List<RoleDO> getUserRoles(long id) {
		logger.debug("Inside getUserRoles");
		Account account = accountDAO.findOne(id);
		List<RoleDO> roleDOList = null;
		if(account != null){
			String roleName = account.getRole().getRTitle();
			String accountType = account.getAccountType().getAccountType();
			List<Role> roleList = null;
			if(accountType.equalsIgnoreCase(AggConstants.ACCOUNT_TYPE_DEALER)){
				roleList = roleDAO.findByAccountTypeAccountType(AggConstants.ACCOUNT_TYPE_DEALER);
				roleDOList = getUserRoles(roleList, roleName, accountType);
			}else{
				roleList = roleDAO.findByAccountTypeAccountType(AggConstants.ACCOUNT_TYPE_ADMIN);
				roleDOList = getUserRoles(roleList, roleName, accountType);
			}
		}
		return roleDOList;
	}
	
	public List<RoleDO> getUserRoles(List<Role> roleList, String roleName, String accountType) {
		logger.debug("Inside getUserRoles");
		List<RoleDO> roleDOList = null;
		if(roleList != null && !roleList.isEmpty()){
			RoleDO roleDO = null;
			roleDOList = new ArrayList<RoleDO>();
			if(accountType.equalsIgnoreCase(AggConstants.ACCOUNT_TYPE_DEALER)){
				if(roleName.equalsIgnoreCase(AggConstants.DEALER_ADMIN)){
					for(Role role : roleList){
						if(role.getRTitle().equalsIgnoreCase(roleName)){
							roleDO = new RoleDO();
							roleDO.setAccountTypeId(role.getAccountType().getId());
							roleDO.setId(role.getRId());
							roleDO.setName(role.getRTitle());
							
							roleDOList.add(roleDO);
							break;
						}
					}
				}else {
					for(Role role : roleList){
						if(!role.getRTitle().equalsIgnoreCase(AggConstants.DEALER_ADMIN)){
							roleDO = new RoleDO();
							roleDO.setAccountTypeId(role.getAccountType().getId());
							roleDO.setId(role.getRId());
							roleDO.setName(role.getRTitle());
							
							roleDOList.add(roleDO);
						}
					}
				}
			}else{
				for(Role role : roleList){
					roleDO = new RoleDO();
					roleDO.setAccountTypeId(role.getAccountType().getId());
					roleDO.setId(role.getRId());
					roleDO.setName(role.getRTitle());
					
					roleDOList.add(roleDO);
				}
			}
		}
			
		logger.info("roleDOList size: "+roleDOList.size());
			
		return roleDOList;
	}

	@Override
	public long getActiveDealerCount() {
		logger.info("inside getActiveDealerCount method");
		return dealerDAO.countByStatus(AggConstants.ACTIVE);
	}

	@Override
	public long getTerminatedDealerCount() {
		logger.info("inside getTerminatedDealerCount method");
		return dealerDAO.countByStatus(AggConstants.TERMINATED);
	}

	@Override
	public long getPendingDealerCount() {
		logger.info("inside getPendingDealerCount method");
		return dealerDAO.countByStatus(AggConstants.PENDING);
	}

}

