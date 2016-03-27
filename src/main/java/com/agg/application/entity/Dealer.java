package com.agg.application.entity;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Date;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the dealers database table.
 * 
 */
@Entity
@Table(name="dealers")
@NamedQuery(name="Dealer.findAll", query="SELECT d FROM Dealer d")
public class Dealer implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private long id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="active_date")
	private Date activeDate;

	private String address;

	private String city;

	private String contact;

	@Column(name="invoice_email")
	private String invoiceEmail;

	@Column(name="last_update")
	private Timestamp lastUpdate;

	private String location;

	@Column(name="market_email")
	private String marketEmail;

	private String name;

	private String phone;

	private String state;

	private int status;

	private String url;

	private String zip;

	@Lob
	private String notes;

	//bi-directional many-to-one association to Notification
	@OneToMany(mappedBy="dealer")
	private List<Notification> notifications;

	//bi-directional many-to-one association to Quote
	@OneToMany(mappedBy="dealer")
	private List<Quote> quotes;

	//bi-directional many-to-one association to Sprogram
	@OneToMany(mappedBy="dealer")
	private List<Sprogram> sprograms;
	
	@OneToOne(mappedBy="dealer", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	private Account account;

	public Dealer() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getActiveDate() {
		return this.activeDate;
	}

	public void setActiveDate(Date activeDate) {
		this.activeDate = activeDate;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getContact() {
		return this.contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getInvoiceEmail() {
		return this.invoiceEmail;
	}

	public void setInvoiceEmail(String invoiceEmail) {
		this.invoiceEmail = invoiceEmail;
	}

	public Timestamp getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public String getLocation() {
		return this.location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getMarketEmail() {
		return this.marketEmail;
	}

	public void setMarketEmail(String marketEmail) {
		this.marketEmail = marketEmail;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public int getStatus() {
		return this.status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getZip() {
		return this.zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}
	
	/**
	 * @return the notes
	 */
	public String getNotes() {
		return notes;
	}

	/**
	 * @param notes the notes to set
	 */
	public void setNotes(String notes) {
		this.notes = notes;
	}

	public List<Notification> getNotifications() {
		return this.notifications;
	}

	public void setNotifications(List<Notification> notifications) {
		this.notifications = notifications;
	}

	public Notification addNotification(Notification notification) {
		getNotifications().add(notification);
		notification.setDealer(this);

		return notification;
	}

	public Notification removeNotification(Notification notification) {
		getNotifications().remove(notification);
		notification.setDealer(null);

		return notification;
	}

	public List<Quote> getQuotes() {
		return this.quotes;
	}

	public void setQuotes(List<Quote> quotes) {
		this.quotes = quotes;
	}

	public Quote addQuote(Quote quote) {
		getQuotes().add(quote);
		quote.setDealer(this);

		return quote;
	}

	public Quote removeQuote(Quote quote) {
		getQuotes().remove(quote);
		quote.setDealer(null);

		return quote;
	}

	public List<Sprogram> getSprograms() {
		return this.sprograms;
	}

	public void setSprograms(List<Sprogram> sprograms) {
		this.sprograms = sprograms;
	}

	public Sprogram addSprogram(Sprogram sprogram) {
		getSprograms().add(sprogram);
		sprogram.setDealer(this);

		return sprogram;
	}

	public Sprogram removeSprogram(Sprogram sprogram) {
		getSprograms().remove(sprogram);
		sprogram.setDealer(null);

		return sprogram;
	}

	/**
	 * @return the account
	 */
	public Account getAccount() {
		return account;
	}

	/**
	 * @param account the account to set
	 */
	public void setAccount(Account account) {
		this.account = account;
	}
	
}