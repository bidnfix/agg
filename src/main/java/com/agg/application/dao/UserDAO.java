package com.agg.application.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import com.agg.application.entity.Account;

@Component
public interface UserDAO extends CrudRepository<Account, Long> {

	Account findByUserName(String username);

	Account findByUserNameAndPassword(String username, char[] password);

}
