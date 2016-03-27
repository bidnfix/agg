package com.agg.application.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import com.agg.application.entity.Role;

@Component
public interface RoleDAO extends CrudRepository<Role, Long>{
	
	public List<Role> findByRTitle(String title);
	
	public List<Role> findByAccountTypeAccountType(String accountType);
	
}
