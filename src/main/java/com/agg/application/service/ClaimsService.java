package com.agg.application.service;

import java.util.List;

import com.agg.application.entity.Claims;
import com.agg.application.model.AccountDO;
import com.agg.application.model.ClaimsDO;
import com.agg.application.model.QuoteDO;

public interface ClaimsService {

	List<ClaimsDO> getClaimsInfo(AccountDO accountDO);
	List<ClaimsDO> getAprvOrRejClaims(AccountDO accountDO, byte cStatus);
	List<QuoteDO> getClaimInfoBySerialNumber(final String serialNo);
	QuoteDO getClaimInfo(String ClaimId);
	Claims saveClaim(ClaimsDO claimsDO, AccountDO accountDO);
	List<ClaimsDO> getClaimsByCStatus(byte cStatus, boolean contractInfo, int dealerId);
	List<ClaimsDO> getClaimsByCStatus(byte cStatus, int dealerId, boolean contractInfo);
	void updateStatus(final int id, final byte status, int dealerId, String extComment);
	int updateClaimAdjudicate(ClaimsDO claim, AccountDO accountDO);
	ClaimsDO getClaim(int claimId, int dealerId);
	int getContractsCount(String contractId);
	ClaimsDO getClaim(String claimId, int dealerId);
}
