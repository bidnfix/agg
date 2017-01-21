package com.agg.application.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;


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
	private long id;

	private String name;

	@Column(name="nav_url")
	private String navUrl;
	
	@Column(name="status")
	private int status;

	//bi-directional many-to-one association to UserRoleMenus
	@OneToMany(mappedBy="userMenus")
	private List<UserRoleMenus> userRoleMenuses;

	//bi-directional one-to-one association to UserSubMenus
	@OneToMany(mappedBy="userMenus")
	private List<UserSubMenus> userSubMenuses;
	
	//bi-directional many-to-one association to UserRoleSubMenus
	@OneToMany(mappedBy="userMenus")
	private List<UserRoleSubMenus> userRoleSubMenuses;

	public UserMenus() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
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
	
	/**
	 * @return the status
	 */
	public int getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(int status) {
		this.status = status;
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
	
	public List<UserRoleSubMenus> getUserRoleSubMenuses() {
		return this.userRoleSubMenuses;
	}

	public void setUserRoleSubMenuses(List<UserRoleSubMenus> userRoleSubMenuses) {
		this.userRoleSubMenuses = userRoleSubMenuses;
	}

	public UserRoleSubMenus addUserRoleSubMenus(UserRoleSubMenus userRoleSubMenus) {
		getUserRoleSubMenuses().add(userRoleSubMenus);
		userRoleSubMenus.setUserMenus(this);

		return userRoleSubMenus;
	}

	public UserRoleSubMenus removeUserRoleSubMenus(UserRoleSubMenus userRoleSubMenus) {
		getUserRoleSubMenuses().remove(userRoleSubMenus);
		userRoleSubMenus.setUserMenus(null);

		return userRoleSubMenus;
	}

}