package com.agg.application.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
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
	private int id;

	private String name;

	@Column(name="nav_url")
	private String navUrl;

	//bi-directional one-to-one association to UserMenus
	@ManyToOne
	@JoinColumn(name="menu_id")
	private UserMenus userMenus;

	public UserSubMenus() {
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
	
}