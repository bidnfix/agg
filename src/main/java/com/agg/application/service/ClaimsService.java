package com.agg.application.service;

import java.util.List;

import com.agg.application.model.AccountDO;
import com.agg.application.model.ClaimsDO;
import com.agg.application.model.QuoteDO;

public interface ClaimsService {

	List<ClaimsDO> getClaimsInfo(AccountDO accountDO);
	List<QuoteDO> getClaimInfoBySerialNumber(final String serialNo);
	QuoteDO getClaimInfo(String ClaimId);
	Long saveClaim(ClaimsDO claimsDO);
	List<ClaimsDO> getClaimsByCStatus(byte cStatus);
	List<ClaimsDO> getClaimsByCStatus(byte cStatus, int dealerId);
	void updateStatus(final int id, final byte status);
}
