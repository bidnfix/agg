package com.agg.application.service;

import java.util.List;

import com.agg.application.model.AccountDO;
import com.agg.application.model.DealerDO;
import com.agg.application.model.DealerDropDownDO;
import com.agg.application.model.LocationDO;
import com.agg.application.model.RoleDO;
import com.agg.application.model.UserDO;

public interface DealerService {

	public List<DealerDO> getDealers();
	
	//public List<DealerDO> getAdminDealers();
	
	public List<DealerDO> getActiveDealers(AccountDO accountDO);
	
	public List<DealerDropDownDO> getParentDealers();

	public long saveDealer(DealerDO dealerDO, AccountDO accountDO, boolean dealerRegistration, String appUrl) throws Exception;

	public DealerDO getDealer(long dealerId);

	public long saveLocation(LocationDO locationDO);

	public List<RoleDO> getDealerAdminRoles();
	
	public List<RoleDO> getDealerRoles();

	public List<LocationDO> getDealerLocations(long dealerId);

	public long saveDealerUser(UserDO userDO, AccountDO accountDO);

	//public List<RoleDO> getDealerRoles(long id);

	public long editDealer(DealerDO dealerDO, AccountDO accountDO, String appUrl);

	public List<DealerDO> getPendingDealers();

	public List<RoleDO> getUserRoles(long id);
	
	public long getActiveDealerCount();
	
	public long getTerminatedDealerCount();
	
	public long getPendingDealerCount();

	public DealerDO getDealerInfo(long accountId);

	public boolean isUserNameExists(String userName);

	public List<DealerDO> findDealers(String dealerType);
	
	public List<DealerDO> findAllDealers();
	
	public List<DealerDO> getServiceDealerActiveDealers();
	
	public List<UserDO> getDealerUsers(long dealerId);

}
