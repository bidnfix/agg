package com.agg.application.service;

import java.util.List;

import com.agg.application.model.QuoteDO;

public interface ClaimsService {

	List<QuoteDO> getClaimsInfo();
	
	QuoteDO getClaimInfo(String ClaimId);

}
