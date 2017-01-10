/**
 * htamada
 */
package com.agg.application.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Lob;

/**
 * @author htamada
 *
 */
public class ClaimNoteDO {
	private int id;
	private int claimId;
	private int dealerId;
	private Timestamp lastUpdate;
	private String notes;

	
}
