package com.agg.application.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the prog_manf_model_xref database table.
 * 
 */
@Entity
@Table(name="prog_manf_model_xref")
@NamedQuery(name="ProgManfModelXref.findAll", query="SELECT p FROM ProgManfModelXref p")
public class ProgManfModelXref implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ProgManfModelXrefPK id;

	public ProgManfModelXref() {
	}

	public ProgManfModelXrefPK getId() {
		return this.id;
	}

	public void setId(ProgManfModelXrefPK id) {
		this.id = id;
	}

}