package com.agg.application.service;

import java.util.List;

import com.agg.application.model.QuoteDO;
import com.agg.application.vo.ClaimsVO;

public interface ClaimsService {

	List<QuoteDO> getClaimsInfo();
	List<QuoteDO> getClaimInfoBySerialNumber(final String serialNo);
	QuoteDO getClaimInfo(String ClaimId);
	Long saveClaim(ClaimsVO claimsVO);

}
