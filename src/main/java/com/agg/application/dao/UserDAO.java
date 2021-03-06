package com.agg.application.dao;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import com.agg.application.entity.Account;

@Component
public interface UserDAO extends CrudRepository<Account, Long> {

	Account findByUserName(String username);

	Optional<Account> findByUserNameAndPassword(String username, String password);

	Optional<Account> findByUserNameAndPasswordAndStatus(String username, String password, int active);

}
