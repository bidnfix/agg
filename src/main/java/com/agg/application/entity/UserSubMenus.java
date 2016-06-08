package com.agg.application.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 * The persistent class for the user_sub_menus database table.
 * 
 */
@Entity
@Table(name="user_sub_menus")
@NamedQuery(name="UserSubMenus.findAll", query="SELECT u FROM UserSubMenus u")
public class UserSubMenus implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private long id;

	private String name;

	@Column(name="nav_url")
	private String navUrl;

	//bi-directional one-to-one association to UserMenus
	@ManyToOne
	@JoinColumn(name="menu_id")
	private UserMenus userMenus;
	
	//bi-directional many-to-one association to UserRoleSubMenus
	@OneToMany(mappedBy="userSubMenus")
	private List<UserRoleSubMenus> userRoleSubMenuses;

	public UserSubMenus() {
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

	public UserMenus getUserMenus() {
		return this.userMenus;
	}

	public void setUserMenus(UserMenus userMenus) {
		this.userMenus = userMenus;
	}

	/**
	 * @return the navUrl
	 */
	public String getNavUrl() {
		return navUrl;
	}

	/**
	 * @param navUrl the navUrl to set
	 */
	public void setNavUrl(String navUrl) {
		this.navUrl = navUrl;
	}
	
	public List<UserRoleSubMenus> getUserRoleSubMenuses() {
		return this.userRoleSubMenuses;
	}

	public void setUserRoleSubMenuses(List<UserRoleSubMenus> userRoleSubMenuses) {
		this.userRoleSubMenuses = userRoleSubMenuses;
	}

	public UserRoleSubMenus addUserRoleSubMenus(UserRoleSubMenus userRoleSubMenus) {
		getUserRoleSubMenuses().add(userRoleSubMenus);
		userRoleSubMenus.setUserSubMenus(this);

		return userRoleSubMenus;
	}

	public UserRoleSubMenus removeUserRoleSubMenus(UserRoleSubMenus userRoleSubMenus) {
		getUserRoleSubMenuses().remove(userRoleSubMenus);
		userRoleSubMenus.setUserSubMenus(null);

		return userRoleSubMenus;
	}
	
}