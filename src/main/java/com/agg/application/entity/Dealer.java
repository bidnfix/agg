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
	private int id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="active_date")
	private Date activeDate;

	private String address;

	private String city;

	private String contact;

	@Column(name="dealer_id")
	private int dealerId;

	@Column(name="invoice_email")
	private String invoiceEmail;

	@Column(name="l_id")
	private int lId;

	@Column(name="last_update")
	private Timestamp lastUpdate;

	private String location;

	@Column(name="market_email")
	private String marketEmail;

	private String name;

	private String password;

	private String phone;

	@Column(name="r_id")
	private int rId;

	private String role;

	private String state;

	private int status;

	private String url;

	private String username;

	private String zip;

	//bi-directional one-to-one association to DealerNote
	@OneToOne(mappedBy="dealer")
	private DealerNote dealerNote;

	//bi-directional many-to-one association to Notification
	@OneToMany(mappedBy="dealer")
	private List<Notification> notifications;

	//bi-directional many-to-one association to Quote
	@OneToMany(mappedBy="dealer")
	private List<Quote> quotes;

	//bi-directional many-to-one association to Sprogram
	@OneToMany(mappedBy="dealer")
	private List<Sprogram> sprograms;

	public Dealer() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
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

	public int getDealerId() {
		return this.dealerId;
	}

	public void setDealerId(int dealerId) {
		this.dealerId = dealerId;
	}

	public String getInvoiceEmail() {
		return this.invoiceEmail;
	}

	public void setInvoiceEmail(String invoiceEmail) {
		this.invoiceEmail = invoiceEmail;
	}

	public int getLId() {
		return this.lId;
	}

	public void setLId(int lId) {
		this.lId = lId;
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

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int getRId() {
		return this.rId;
	}

	public void setRId(int rId) {
		this.rId = rId;
	}

	public String getRole() {
		return this.role;
	}

	public void setRole(String role) {
		this.role = role;
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

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getZip() {
		return this.zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public DealerNote getDealerNote() {
		return this.dealerNote;
	}

	public void setDealerNote(DealerNote dealerNote) {
		this.dealerNote = dealerNote;
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

}