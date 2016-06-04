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

	private long id;

	@Column(name="role_id", insertable=false, updatable=false)
	private long roleId;

	@Column(name="menu_id", insertable=false, updatable=false)
	private long menuId;

	public UserRoleMenusPK() {
	}
	public long getId() {
		return this.id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getRoleId() {
		return this.roleId;
	}
	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}
	public long getMenuId() {
		return this.menuId;
	}
	public void setMenuId(long menuId) {
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
		hash = hash * prime + Long.valueOf(this.id).intValue();
		hash = hash * prime + Long.valueOf(this.roleId).intValue();
		hash = hash * prime + Long.valueOf(this.menuId).intValue();
		
		return hash;
	}
}