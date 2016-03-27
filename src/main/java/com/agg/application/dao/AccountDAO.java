package com.agg.application.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import com.agg.application.entity.Account;

@Component
public interface AccountDAO extends CrudRepository<Account, Long>{
	
	List<Account> findByAccountTypeAccountType(String accountType);
}
