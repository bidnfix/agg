package com.agg.application.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
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
	private int id;

	@Column(name="menu_id")
	private int menuId;

	private String name;

	@Column(name="navi_url")
	private String naviUrl;

	//bi-directional one-to-one association to UserMenus
	@ManyToOne
	@JoinColumn(name="id")
	private UserMenus userMenus;

	public UserSubMenus() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getMenuId() {
		return this.menuId;
	}

	public void setMenuId(int menuId) {
		this.menuId = menuId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNaviUrl() {
		return this.naviUrl;
	}

	public void setNaviUrl(String naviUrl) {
		this.naviUrl = naviUrl;
	}

	public UserMenus getUserMenus() {
		return this.userMenus;
	}

	public void setUserMenus(UserMenus userMenus) {
		this.userMenus = userMenus;
	}

}