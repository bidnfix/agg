package com.agg.application.entity;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "user")
public class User implements Serializable {

	private static final long serialVersionUID = -2893268173533036668L;

	@Id
	@GeneratedValue
	private long id;

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + Arrays.toString(password) + ", status="
				+ status + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", createdDate="
				+ createdDate + ", updatedDate=" + updatedDate + ", invalidAttempts=" + invalidAttempts
				+ ", isPwdExpired=" + isPwdExpired + ", forcePwdChange=" + forcePwdChange + ", lastLoginDate="
				+ lastLoginDate + ", lastPwdChange=" + lastPwdChange + "]";
	}

	@Column(name = "user_name", nullable = false)
	private String username;

	@Column(name = "password", nullable = false)
	private char[] password;

	@Column(name = "status", nullable = false)
	private int status;

	@Column(name = "first_name", nullable = false)
	private String firstName;

	@Column(name = "last_name", nullable = false)
	private String lastName;

	@Column(name = "email", nullable = false)
	private String email;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_date", nullable = false)
	private Date createdDate;

	@Column(name = "updated_date")
	private Date updatedDate;

	@Column(name = "invalid_attempts")
	private int invalidAttempts;

	@Column(name = "pwd_expired")
	private boolean isPwdExpired;

	@Column(name = "forced_pwd_change")
	private boolean forcePwdChange = false;

	@Column(name = "last_login_date")
	private Date lastLoginDate;

	@Column(name = "last_pwd_date")
	private Date lastPwdChange;

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username
	 *            the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the password
	 */
	public char[] getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(char[] password) {
		this.password = password;
	}

	/**
	 * @return the status
	 */
	public int getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(int status) {
		this.status = status;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName
	 *            the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName
	 *            the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the createdDate
	 */
	public Date getCreatedDate() {
		return createdDate;
	}

	/**
	 * @param createdDate
	 *            the createdDate to set
	 */
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	/**
	 * @return the updatedDate
	 */
	public Date getUpdatedDate() {
		return updatedDate;
	}

	/**
	 * @param updatedDate
	 *            the updatedDate to set
	 */
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	/**
	 * @return the invalidAttempts
	 */
	public int getInvalidAttempts() {
		return invalidAttempts;
	}

	/**
	 * @param invalidAttempts
	 *            the invalidAttempts to set
	 */
	public void setInvalidAttempts(int invalidAttempts) {
		this.invalidAttempts = invalidAttempts;
	}

	/**
	 * @return the isPwdExpired
	 */
	public boolean isPwdExpired() {
		return isPwdExpired;
	}

	/**
	 * @param isPwdExpired
	 *            the isPwdExpired to set
	 */
	public void setPwdExpired(boolean isPwdExpired) {
		this.isPwdExpired = isPwdExpired;
	}

	/**
	 * @return the forcePwdChange
	 */
	public boolean isForcePwdChange() {
		return forcePwdChange;
	}

	/**
	 * @param forcePwdChange
	 *            the forcePwdChange to set
	 */
	public void setForcePwdChange(boolean forcePwdChange) {
		this.forcePwdChange = forcePwdChange;
	}

	/**
	 * @return the lastLoginDate
	 */
	public Date getLastLoginDate() {
		return lastLoginDate;
	}

	/**
	 * @param lastLoginDate
	 *            the lastLoginDate to set
	 */
	public void setLastLoginDate(Date lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}

	/**
	 * @return the lastPwdChange
	 */
	public Date getLastPwdChange() {
		return lastPwdChange;
	}

	/**
	 * @param lastPwdChange
	 *            the lastPwdChange to set
	 */
	public void setLastPwdChange(Date lastPwdChange) {
		this.lastPwdChange = lastPwdChange;
	}

}
