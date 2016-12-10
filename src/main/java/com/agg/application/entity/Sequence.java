package com.agg.application.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the sequence database table.
 * 
 */
@Entity
@Table(name="sequence")
@NamedQuery(name="Sequence.findAll", query="SELECT seq FROM Sequence seq")
public class Sequence implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;

	@Column(name="seq_type")
	private String seqType;

	@Column(name="seq_value")
	private long seqValue;

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the seqType
	 */
	public String getSeqType() {
		return seqType;
	}

	/**
	 * @param seqType the seqType to set
	 */
	public void setSeqType(String seqType) {
		this.seqType = seqType;
	}

	/**
	 * @return the seqValue
	 */
	public long getSeqValue() {
		return seqValue;
	}

	/**
	 * @param seqValue the seqValue to set
	 */
	public void setSeqValue(long seqValue) {
		this.seqValue = seqValue;
	}

}