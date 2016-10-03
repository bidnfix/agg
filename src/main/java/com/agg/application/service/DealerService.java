package com.agg.application.service;

import java.util.List;

import com.agg.application.model.AccountDO;
import com.agg.application.model.DealerDO;
import com.agg.application.model.LocationDO;
import com.agg.application.model.RoleDO;
import com.agg.application.model.UserDO;

public interface DealerService {

	public List<DealerDO> getDealers();
	
	//public List<DealerDO> getAdminDealers();
	
	public List<DealerDO> getActiveDealers(AccountDO accountDO);
	
	public List<DealerDO> getParentDealers();

	public long saveDealer(DealerDO dealerDO, AccountDO accountDO, boolean dealerRegistration);

	public DealerDO getDealer(long dealerId);

	public long saveLocation(LocationDO locationDO);

	public List<RoleDO> getDealerAdminRoles();
	
	public List<RoleDO> getDealerRoles();

	public List<LocationDO> getDealerLocations(long dealerId);

	public long saveDealerUser(UserDO userDO, AccountDO accountDO);

	//public List<RoleDO> getDealerRoles(long id);

	public long editDealer(DealerDO dealerDO, AccountDO accountDO);

	public List<DealerDO> getPendingDealers();

	public List<RoleDO> getUserRoles(long id);
	
	public long getActiveDealerCount();
	
	public long getTerminatedDealerCount();
	
	public long getPendingDealerCount();

	public DealerDO getDealerInfo(long accountId);

	public boolean isUserNameExists(String userName);

}
