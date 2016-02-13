package com.agg.application.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the quotes database table.
 * 
 */
@Embeddable
public class QuotePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	private int id;

	@Column(name="quote_id")
	private String quoteId;

	public QuotePK() {
	}
	public int getId() {
		return this.id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getQuoteId() {
		return this.quoteId;
	}
	public void setQuoteId(String quoteId) {
		this.quoteId = quoteId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof QuotePK)) {
			return false;
		}
		QuotePK castOther = (QuotePK)other;
		return 
			(this.id == castOther.id)
			&& this.quoteId.equals(castOther.quoteId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.id;
		hash = hash * prime + this.quoteId.hashCode();
		
		return hash;
	}
}