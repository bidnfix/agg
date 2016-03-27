package com.agg.application.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import com.agg.application.entity.AccountType;

@Component
public interface AccountTypeDAO extends CrudRepository<AccountType, Long>{
	
	AccountType findByAccountType(String accountType);
}
