package com.agg.application.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import com.agg.application.entity.Role;

@Component
public interface RoleDAO extends CrudRepository<Role, Long>{
	
	public List<Role> findByRTitle(String title);
	
	/*@Query("SELECT r FROM Role r WHERE r.r_title IN : Title")
	public List<Role> findByRTitles(@Param("Title") List<String> titles);*/
	
	public List<Role> findByAccountTypeAccountType(String accountType);
	
}
