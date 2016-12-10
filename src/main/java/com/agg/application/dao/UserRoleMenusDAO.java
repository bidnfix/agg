package com.agg.application.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import com.agg.application.entity.UserRoleMenus;

@Component
public interface UserRoleMenusDAO extends CrudRepository<UserRoleMenus, Long>{
	
}
