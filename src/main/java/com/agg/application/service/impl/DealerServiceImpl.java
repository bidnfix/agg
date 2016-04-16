package com.agg.application.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
import com.agg.application.utils.Util;

@Service
public class DealerServiceImpl implements DealerService {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private static final String DEALER_ADMIN = "Dealer Admin";
	
	private static final String ACCOUNT_TYPE_DEALER = "dealer";
	
	private static final String SEQUENCE_TYPE_DEALER = "dealer";
	
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
	
	@Override
	public List<DealerDO> getDealers() {
		logger.debug("In getPrograms");
		List<DealerDO> dealerDOList = null;
		List<Dealer> dealerList = Util.toList(dealerDAO.findAll());
		if(dealerList != null && !dealerList.isEmpty()){
			dealerDOList = new ArrayList<DealerDO>();
			DealerDO dealerDO = null;
			for(Dealer dealer : dealerList){
				dealerDO = new DealerDO();
				dealerDO.setId(dealer.getId());
				dealerDO.setUserName(dealer.getAccount().getUserName());
				dealerDO.setAddress1(dealer.getAddress());
				dealerDO.setCity(dealer.getCity());
				dealerDO.setInvoiceEmail(dealer.getInvoiceEmail());
				dealerDO.setLocation(dealer.getLocation());
				dealerDO.setMarketEmail(dealer.getMarketEmail());
				dealerDO.setPhone(dealer.getPhone());
				dealerDO.setState(dealer.getState());
				dealerDO.setZip(dealer.getZip());
				dealerDO.setStatus(dealer.getStatus());
				
				dealerDOList.add(dealerDO);
			}
		}
		return dealerDOList;
	}

	@Override
	@Transactional
	public long saveDealer(DealerDO dealerDO, AccountDO accountDO) {
		logger.debug("In saveDealer");
		Dealer dealer = new Dealer();
		Timestamp date = new Timestamp(new Date().getTime());
		dealer.setAddress(dealerDO.getAddress1());
		dealer.setCity(dealerDO.getCity());
		dealer.setContact(dealerDO.getContact());
		
		dealer.setInvoiceEmail(dealerDO.getInvoiceEmail());
		//TODO
		dealer.setLastUpdate(date);
		dealer.setLocation(dealerDO.getLocation());
		dealer.setMarketEmail(dealerDO.getMarketEmail());
		//TODO
		dealer.setName(dealerDO.getUserName());
		dealer.setNotes(dealerDO.getNotes());
		dealer.setPhone(dealerDO.getPhone());
		
		Sequence sequence = sequenceDAO.findBySeqType(SEQUENCE_TYPE_DEALER);
		logger.debug("sequenceId: "+sequence.getSeqValue());
		dealer.setCode(Long.valueOf(dealerDO.getZip().substring(0, 2)+sequence.getSeqValue()));
		dealer.setStatus(1);
		dealer.setState(dealerDO.getState());
		dealer.setUrl(dealerDO.getDealerUrl());
		dealer.setZip(dealerDO.getZip());
		dealer.setActiveDate(date);
		dealer.setLastUpdate(date);
		
		Role role = roleDAO.findOne(dealerDO.getRoleDO().getId());
		Account account = new Account();
		account.setUserName(dealerDO.getUserName());
		account.setPassword(dealerDO.getPassword());
		account.setAccountType(role.getAccountType());
		account.setRole(role);
		account.setCreatedDate(date);
		account.setCreatedBy(accountDO.getUsername());
		account.setUpdatedDate(date);
		account.setLastLoginDate(date);
		account.setStatus((byte)1);
		account.setUpdatedBy(accountDO.getUsername());
		
		account.setDealer(dealer);
		account.setDealerParent(dealer);
		
		sequence.setSeqValue(sequence.getSeqValue()+1);
		sequenceDAO.save(sequence);
		
		account = accountDAO.save(account);
		
		return dealer.getId();
	}

	@Override
	public DealerDO getDealer(long id) {
		Dealer dealer = dealerDAO.findOne((long)id);
		DealerDO dealerDO = null;
		if(dealer != null){
			dealerDO = new DealerDO();
			dealerDO.setId(dealer.getId());
			dealerDO.setUserName(dealer.getAccount().getUserName());
			dealerDO.setAddress1(dealer.getAddress());
			dealerDO.setCity(dealer.getCity());
			dealerDO.setInvoiceEmail(dealer.getInvoiceEmail());
			dealerDO.setLocation(dealer.getLocation());
			dealerDO.setMarketEmail(dealer.getMarketEmail());
			dealerDO.setPhone(dealer.getPhone());
			dealerDO.setState(dealer.getState());
			dealerDO.setZip(dealer.getZip());
			dealerDO.setStatus(dealer.getStatus());
			dealerDO.setContact(dealer.getContact());
			dealerDO.setDealerUrl(dealer.getUrl());
			dealerDO.setNotes(dealer.getNotes());
			dealerDO.setPassword(dealer.getAccount().getPassword());
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
		List<Role> roleList = roleDAO.findByRTitle(DEALER_ADMIN);
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
		List<Role> roleList = roleDAO.findByAccountTypeAccountType(ACCOUNT_TYPE_DEALER);
		List<RoleDO> roleDOList = null;
		if(roleList != null && !roleList.isEmpty()){
			RoleDO roleDO = null;
			roleDOList = new ArrayList<RoleDO>();
			for(Role role : roleList){
				if(!role.getRTitle().equalsIgnoreCase(DEALER_ADMIN)){
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
		
		Dealer dealer = new Dealer();
		Timestamp date = new Timestamp(new Date().getTime());
		dealer.setAddress(userDO.getAddress1());
		dealer.setCity(userDO.getCity());
		
		dealer.setInvoiceEmail(userDO.getEmail());
		dealer.setLastUpdate(date);
		//dealer.setLocation(userDO.getLocationDO().getId()+"");
		dealer.setMarketEmail(userDO.getEmail());
		dealer.setName(userDO.getFirstName()+" "+userDO.getLastName());
		dealer.setPhone(userDO.getPhone());
		
		Sequence sequence = sequenceDAO.findBySeqType(SEQUENCE_TYPE_DEALER);
		
		dealer.setCode(Long.valueOf(dealer.getZip().substring(0, 2)+sequence.getSeqValue()));
		dealer.setStatus(1);
		dealer.setState(userDO.getState());
		dealer.setUrl(userDO.getUrl());
		dealer.setZip(userDO.getZip());
		dealer.setActiveDate(date);
		dealer.setLastUpdate(date);
		
		Role role = roleDAO.findOne(userDO.getRoleDO().getId());
		//Location location = locationDAO.findOne(userDO.getLocationDO().getId());
		Account account = new Account();
		account.setUserName(userDO.getUserName());
		account.setPassword(userDO.getPassword());
		account.setAccountType(role.getAccountType());
		account.setRole(role);
		account.setCreatedDate(date);
		account.setCreatedBy(accountDO.getUsername());
		account.setUpdatedDate(date);
		account.setLastLoginDate(date);
		account.setStatus((byte)1);
		account.setUpdatedBy(accountDO.getUsername());
		account.setDealerParent(dealer);
		account.setDealer(dealer);
		
		sequence.setSeqValue(sequence.getSeqValue()+1);
		sequenceDAO.save(sequence);
		
		account = accountDAO.save(account);
		
		return dealer.getId();
	}

}

