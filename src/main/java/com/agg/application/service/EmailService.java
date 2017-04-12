package com.agg.application.service;

import com.agg.application.entity.Dealer;
import com.agg.application.entity.Quote;
import com.agg.application.model.AccountDO;
import com.agg.application.model.QuoteDO;

public interface EmailService {
	
	public void sendAsyncInvoiceEmail(QuoteDO quoteDO, Quote quote, AccountDO accountDO, Dealer dealer, String appUrl) throws Exception;
	
	public void sendAsyncPurchaseRequestEmail(QuoteDO quoteDO, Quote quote) throws Exception;
	
	public void sendAsyncContractEmail(QuoteDO quoteDO) throws Exception;

}
