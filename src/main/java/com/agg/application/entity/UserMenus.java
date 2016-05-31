package com.agg.application.entity;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the user_menus database table.
 * 
 */
@Entity
@Table(name="user_menus")
@NamedQuery(name="UserMenus.findAll", query="SELECT u FROM UserMenus u")
public class UserMenus implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private int id;

	private String name;

	@Column(name="nav_url")
	private String navUrl;

	//bi-directional many-to-one association to UserRoleMenus
	@OneToMany(mappedBy="userMenus")
	private List<UserRoleMenus> userRoleMenuses;

	//bi-directional one-to-one association to UserSubMenus
	@OneToMany(mappedBy="userMenus")
	private List<UserSubMenus> userSubMenuses;

	public UserMenus() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNavUrl() {
		return this.navUrl;
	}

	public void setNavUrl(String navUrl) {
		this.navUrl = navUrl;
	}

	public List<UserRoleMenus> getUserRoleMenuses() {
		return this.userRoleMenuses;
	}

	public void setUserRoleMenuses(List<UserRoleMenus> userRoleMenuses) {
		this.userRoleMenuses = userRoleMenuses;
	}

	public UserRoleMenus addUserRoleMenus(UserRoleMenus userRoleMenus) {
		getUserRoleMenuses().add(userRoleMenus);
		userRoleMenus.setUserMenus(this);

		return userRoleMenus;
	}

	public UserRoleMenus removeUserRoleMenus(UserRoleMenus userRoleMenus) {
		getUserRoleMenuses().remove(userRoleMenus);
		userRoleMenus.setUserMenus(null);

		return userRoleMenus;
	}

	public List<UserSubMenus> getUserSubMenuses() {
		return userSubMenuses;
	}

	public void setUserSubMenuses(List<UserSubMenus> userSubMenuses) {
		this.userSubMenuses = userSubMenuses;
	}
	
	public UserSubMenus addUserSubMenus(UserSubMenus userSubMenus) {
		getUserSubMenuses().add(userSubMenus);
		userSubMenus.setUserMenus(this);

		return userSubMenus;
	}

	public UserSubMenus removeUserSubMenus(UserSubMenus userSubMenus) {
		getUserSubMenuses().remove(userSubMenus);
		userSubMenus.setUserMenus(null);

		return userSubMenus;
	}

}