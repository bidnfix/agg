package com.agg.application.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * The primary key class for the user_role_menus database table.
 * 
 */
@Embeddable
public class UserRoleMenusPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	private int id;

	@Column(name="role_id", insertable=false, updatable=false)
	private int roleId;

	@Column(name="menu_id", insertable=false, updatable=false)
	private int menuId;

	public UserRoleMenusPK() {
	}
	public int getId() {
		return this.id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getRoleId() {
		return this.roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	public int getMenuId() {
		return this.menuId;
	}
	public void setMenuId(int menuId) {
		this.menuId = menuId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof UserRoleMenusPK)) {
			return false;
		}
		UserRoleMenusPK castOther = (UserRoleMenusPK)other;
		return 
			(this.id == castOther.id)
			&& (this.roleId == castOther.roleId)
			&& (this.menuId == castOther.menuId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.id;
		hash = hash * prime + this.roleId;
		hash = hash * prime + this.menuId;
		
		return hash;
	}
}