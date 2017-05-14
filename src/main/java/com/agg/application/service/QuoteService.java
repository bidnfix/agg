package com.agg.application.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Pageable;

import com.agg.application.model.AccountDO;
import com.agg.application.model.PricingDO;
import com.agg.application.model.QuoteDO;
import com.agg.application.model.ReportDO;
import com.agg.application.model.UseOfEquipmentDO;
import com.agg.application.model.WorklistDO;

public interface QuoteService {

	public List<UseOfEquipmentDO> getUseOfEquipmentDetails();

	public Map<String, List<Integer>> getDeductableCoverageTermDetails(boolean coverageExpired, long machineId);

	public List<PricingDO> getCoveragePriceDetils(boolean coverageExpired, long machineId, int deductibleAmt, int coverageTerm, int coverageHours);
	
	public void saveWarrantyInfo(QuoteDO quoteDO);
	
	public void saveMachineInfo(QuoteDO quoteDO);

	public void saveCoverageInfo(QuoteDO quoteDO);
	
	public void savePurchaseInfo(QuoteDO quoteDO, String appUrl) throws Exception;

	public ReportDO getQuoteReportDetails(String quoteId, String reportType);

	public List<QuoteDO> getQuotes(AccountDO accountDO);

	public QuoteDO getQuote(AccountDO accountDetails, int id, String quoteId);

	public boolean archiveQuote(QuoteDO quoteDO);

	public boolean updateQuote(QuoteDO quoteDO, AccountDO accountDO, String appUrl) throws Exception;

	public boolean invoiceQuote(QuoteDO quoteDO, AccountDO accountDO, String appUrl) throws Exception;
	
	public WorklistDO getWorklistCount(AccountDO accountDO);
	
	public List<QuoteDO> getEstPriceQuotes(AccountDO accountDO);
	
	public List<QuoteDO> getPurchaseReqQuotes(AccountDO accountDO);
	
	public List<QuoteDO> getInvoicedQuotes(AccountDO accountDO);

	public boolean createContract(QuoteDO quoteDO, AccountDO accountDO, String appUrl) throws Exception;

	public Object getArchivedQuotes(AccountDO accountDO);

	public long getQuotesCount(AccountDO accountDo);

	public long getQuotesSearchCount(AccountDO accountDo, String searchText, byte statusSearch);

	public List<QuoteDO> getAllQuotesForSearch(AccountDO accountDo, String searchText, Pageable pageable);

	public List<QuoteDO> getAllQuotes(AccountDO accountDo, Pageable pageable);
	
	public List<QuoteDO> getAllQuotesForSearch(AccountDO accountDetails, String searchText, int parseInt,
						int pageLength, String properties, String orderDirection, byte statusSearch);
}
