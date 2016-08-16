/**
 * htamada
 */
package com.agg.application.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import com.agg.application.entity.ClaimPart;

/**
 * @author htamada
 *
 */
@Component
public interface ClaimPartDAO extends CrudRepository<ClaimPart, Integer>{
	
}
