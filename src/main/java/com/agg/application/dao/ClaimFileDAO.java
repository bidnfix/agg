/**
 * htamada
 */
package com.agg.application.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import com.agg.application.entity.ClaimFile;

/**
 * @author htamada
 *
 */
@Component
public interface ClaimFileDAO extends CrudRepository<ClaimFile, Integer> {

}
