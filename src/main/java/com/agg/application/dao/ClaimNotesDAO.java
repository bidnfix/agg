package com.agg.application.dao;

import org.springframework.data.repository.CrudRepository;

import com.agg.application.entity.ClaimNote;

public interface ClaimNotesDAO extends CrudRepository<ClaimNote, Integer>{
	
	ClaimNote findByClaimIdAndDealerId(int claimId, int dealerId);
}
