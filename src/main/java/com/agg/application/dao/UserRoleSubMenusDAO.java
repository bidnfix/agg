package com.agg.application.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import com.agg.application.entity.UserRoleSubMenus;

@Component
public interface UserRoleSubMenusDAO extends CrudRepository<UserRoleSubMenus, Long>{
	
	public List<UserRoleSubMenus> findByRoleRIdAndUserMenusId(long roleId, long menuId);
	
}
