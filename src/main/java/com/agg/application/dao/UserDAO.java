package com.agg.application.dao;

import org.springframework.data.repository.CrudRepository;

import com.agg.application.entity.User;

public interface UserDAO extends CrudRepository<User, Long> {

}
