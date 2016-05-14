package com.agg.application.service;

import java.util.List;

import com.agg.application.model.AccountDO;
import com.agg.application.model.UserDO;

public interface UserService {

	public List<UserDO> getUsers();

	public Object getUser(long id);

	public long editUser(UserDO userDO, AccountDO accountDetails);

	//public long saveDealerUser(UserDO userDO, AccountDO accountDO);

}
