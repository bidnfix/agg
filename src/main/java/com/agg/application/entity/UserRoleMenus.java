package com.agg.application.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the user_role_menus database table.
 * 
 */
@Entity
@Table(name="user_role_menus")
@NamedQuery(name="UserRoleMenus.findAll", query="SELECT u FROM UserRoleMenus u")
public class UserRoleMenus implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private UserRoleMenusPK id;

	//bi-directional many-to-one association to UserMenus
	@ManyToOne
	@JoinColumn(name="menu_id", insertable=false, updatable=false)
	private UserMenus userMenus;

	//bi-directional many-to-one association to Role
	@ManyToOne
	@JoinColumn(name="role_id", insertable=false, updatable=false)
	private Role role;

	public UserRoleMenus() {
	}

	public UserRoleMenusPK getId() {
		return this.id;
	}

	public void setId(UserRoleMenusPK id) {
		this.id = id;
	}

	public UserMenus getUserMenus() {
		return this.userMenus;
	}

	public void setUserMenus(UserMenus userMenus) {
		this.userMenus = userMenus;
	}

	public Role getRole() {
		return this.role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

}