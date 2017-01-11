package com.agg.application.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.agg.application.entity.ClaimNote;

public interface ClaimNotesDAO extends CrudRepository<ClaimNote, Integer>{
	
	ClaimNote findByClaimIdAndDealerId(int claimId, int dealerId);
	
	List<ClaimNote> findByClaimId(int claimId);
}
