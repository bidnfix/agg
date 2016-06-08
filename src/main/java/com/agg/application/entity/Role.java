package com.agg.application.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 * The persistent class for the roles database table.
 * 
 */
@Entity
@Table(name="roles")
@NamedQuery(name="Role.findAll", query="SELECT r FROM Role r")
public class Role implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="r_id")
	private long rId;

	@Column(name="last_update")
	private Timestamp lastUpdate;

	@Lob
	@Column(name="r_permissions")
	private String rPermissions;

	@Column(name="r_slug")
	private String rSlug;

	@Column(name="r_title")
	private String rTitle;

	//bi-directional many-to-one association to Account
	@OneToMany(mappedBy="role")
	private List<Account> accounts;
	
	@ManyToOne
	@JoinColumn(name="account_type_id")
	private AccountType accountType;
	
	//bi-directional many-to-one association to UserRoleMenus
	@OneToMany(mappedBy="role")
	private List<UserRoleMenus> userRoleMenuses;
	
	//bi-directional many-to-one association to UserRoleSubMenus
	@OneToMany(mappedBy="role")
	private List<UserRoleSubMenus> userRoleSubMenuses;

	public Role() {
	}

	public long getRId() {
		return this.rId;
	}

	public void setRId(long rId) {
		this.rId = rId;
	}

	public Timestamp getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public String getRPermissions() {
		return this.rPermissions;
	}

	public void setRPermissions(String rPermissions) {
		this.rPermissions = rPermissions;
	}

	public String getRSlug() {
		return this.rSlug;
	}

	public void setRSlug(String rSlug) {
		this.rSlug = rSlug;
	}

	public String getRTitle() {
		return this.rTitle;
	}

	public void setRTitle(String rTitle) {
		this.rTitle = rTitle;
	}

	public List<Account> getAccounts() {
		return this.accounts;
	}

	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}

	public Account addAccount(Account account) {
		getAccounts().add(account);
		account.setRole(this);

		return account;
	}

	public Account removeAccount(Account account) {
		getAccounts().remove(account);
		account.setRole(null);

		return account;
	}

	/**
	 * @return the accountType
	 */
	public AccountType getAccountType() {
		return accountType;
	}

	/**
	 * @param accountType the accountType to set
	 */
	public void setAccountType(AccountType accountType) {
		this.accountType = accountType;
	}
	
	public List<UserRoleMenus> getUserRoleMenuses() {
		return this.userRoleMenuses;
	}

	public void setUserRoleMenuses(List<UserRoleMenus> userRoleMenuses) {
		this.userRoleMenuses = userRoleMenuses;
	}

	public UserRoleMenus addUserRoleMenus(UserRoleMenus userRoleMenus) {
		getUserRoleMenuses().add(userRoleMenus);
		userRoleMenus.setRole(this);

		return userRoleMenus;
	}

	public UserRoleMenus removeUserRoleMenus(UserRoleMenus userRoleMenus) {
		getUserRoleMenuses().remove(userRoleMenus);
		userRoleMenus.setRole(null);

		return userRoleMenus;
	}
	
	public List<UserRoleSubMenus> getUserRoleSubMenuses() {
		return this.userRoleSubMenuses;
	}

	public void setUserRoleSubMenuses(List<UserRoleSubMenus> userRoleSubMenuses) {
		this.userRoleSubMenuses = userRoleSubMenuses;
	}

	public UserRoleSubMenus addUserRoleSubMenus(UserRoleSubMenus userRoleSubMenus) {
		getUserRoleSubMenuses().add(userRoleSubMenus);
		userRoleSubMenus.setRole(this);

		return userRoleSubMenus;
	}

	public UserRoleSubMenus removeUserRoleSubMenus(UserRoleSubMenus userRoleSubMenus) {
		getUserRoleSubMenuses().remove(userRoleSubMenus);
		userRoleSubMenus.setRole(null);

		return userRoleSubMenus;
	}
	
}