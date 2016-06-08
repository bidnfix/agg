package com.agg.application.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the user_role_sub_menus database table.
 * 
 */
@Entity
@Table(name="user_role_sub_menus")
@NamedQuery(name="UserRoleSubMenus.findAll", query="SELECT u FROM UserRoleSubMenus u")
public class UserRoleSubMenus implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private UserRoleSubMenusPK id;

	//bi-directional many-to-one association to Role
	@ManyToOne
	@JoinColumn(name="role_id", insertable=false, updatable=false)
	private Role role;

	//bi-directional many-to-one association to UserSubMenus
	@ManyToOne
	@JoinColumn(name="sub_menu_id", insertable=false, updatable=false)
	private UserSubMenus userSubMenus;

	//bi-directional many-to-one association to UserMenus
	@ManyToOne
	@JoinColumn(name="menu_id", insertable=false, updatable=false)
	private UserMenus userMenus;

	public UserRoleSubMenus() {
	}

	public UserRoleSubMenusPK getId() {
		return this.id;
	}

	public void setId(UserRoleSubMenusPK id) {
		this.id = id;
	}

	public Role getRole() {
		return this.role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public UserSubMenus getUserSubMenus() {
		return this.userSubMenus;
	}

	public void setUserSubMenus(UserSubMenus userSubMenus) {
		this.userSubMenus = userSubMenus;
	}

	public UserMenus getUserMenus() {
		return this.userMenus;
	}

	public void setUserMenus(UserMenus userMenus) {
		this.userMenus = userMenus;
	}

}