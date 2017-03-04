package com.agg.application.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import com.agg.application.entity.Quote;
import com.agg.application.entity.QuotePK;
import com.agg.application.model.QuoteDO;

@Component
public interface QuoteDAO extends CrudRepository<Quote, QuotePK> {
	
	@Query("SELECT q FROM Quote q WHERE q.id.id = :id")
	public Quote findOne(@Param("id")int id);
	
	@Query("SELECT q FROM Quote q WHERE q.machineSerial LIKE CONCAT('%', :machineSerial, '%')")
	List<Quote> findByMachineSerial(@Param("machineSerial") String serialNo);
	
	public Quote findByIdQuoteId(String quoteId);
	
	public List<Quote> findByDealerId(long dealerId);
	
	@Query("SELECT COUNT(*) FROM Quote q WHERE q.status=1")
	public int countByEstPrice();
	
	@Query("SELECT COUNT(*) FROM Quote q WHERE q.status=4")
	public int countByPurRequested();
	
	@Query("SELECT COUNT(*) FROM Quote q WHERE q.status=5")
	public int countByInvoiced();
	
	@Query("SELECT COUNT(*) FROM Quote q WHERE q.status=1 and q.dealer.id = :dealerId")
	public int countByEstPrice(@Param("dealerId")long dealerId);
	
	@Query("SELECT COUNT(*) FROM Quote q WHERE q.status=4 and q.dealer.id = :dealerId")
	public int countByPurRequested(@Param("dealerId")long dealerId);
	
	@Query("SELECT COUNT(*) FROM Quote q WHERE q.status=5 and q.dealer.id = :dealerId")
	public int countByInvoiced(@Param("dealerId")long dealerId);
	
	/*@Query("SELECT q FROM Quote q WHERE q.status=1")
	public List<Quote> findByEstPrice();*/
	
	/*@Query("SELECT q FROM Quote q WHERE q.status=1 and q.dealer.id = :dealerId")
	public List<Quote> findByEstPrice(@Param("dealerId")long dealerId);*/
	
	/*@Query("SELECT q FROM Quote q WHERE q.status=4")
	public List<Quote> findByPurRequested();*/
	
	/*@Query("SELECT q FROM Quote q WHERE q.status=4 and q.dealer.id = :dealerId")
	public List<Quote> findByPurRequested(@Param("status")byte status, @Param("dealerId")long dealerId);
	*/
	/*@Query("SELECT q FROM Quote q WHERE q.status=5")
	public List<Quote> findByInvoiced();*/
	
	public List<Quote> findByStatus(byte status);
	
/*	@Query("SELECT q FROM Quote q WHERE q.status= :status and q.dealer.id = :dealerId")
	public List<Quote> findByStatus(@Param("status")byte status, @Param("dealerId")long dealerId);
*/	
	public List<Quote> findByStatusAndDealerId(@Param("status")byte status, @Param("dealerId")long dealerId);
	
	@Query("SELECT new com.agg.application.model.QuoteDO(quote.id.id, quote.id.quoteId, quote.dealer.name, customerInfo.name, "
			+ "quote.machineInfo.model, quote.machineSaleDate, quote.status, quote.createDate)"
			+ " from Quote quote, CustomerInfo customerInfo"
			+ " where quote.id.quoteId=customerInfo.quoteId"
			+ " and quote.status = :status")
	public List<QuoteDO> findByDealerStatus(@Param("status")byte status);
	
	@Query("SELECT new com.agg.application.model.QuoteDO(quote.id.id, quote.id.quoteId, quote.dealer.name, customerInfo.name, "
			+ "quote.machineInfo.model, quote.machineSaleDate, quote.status, quote.createDate)"
			+ " from Quote quote, CustomerInfo customerInfo"
			+ " where quote.id.quoteId=customerInfo.quoteId"
			+ " and quote.dealer.id = :dealerId"
			+ " and quote.status = :status")
	public List<QuoteDO> findByDealerStatusAndDealerId(@Param("status")byte status, @Param("dealerId")long dealerId);
	
	/*@Query("SELECT q FROM Quote q WHERE q.status= :status and q.dealer.id = :dealerId")
	public List<Quote> findByInvoiced(@Param("status")byte status, @Param("dealerId")long dealerId);*/
	
	@Query("SELECT new com.agg.application.model.QuoteDO(quote.id.id, quote.id.quoteId, quote.dealer.name, customerInfo.name, "
			+ "quote.machineInfo.model, quote.machineSaleDate, quote.status, quote.createDate)"
			+ " from Quote quote, CustomerInfo customerInfo"
			+ " where quote.id.quoteId=customerInfo.quoteId")
	public List<QuoteDO> findAllQuotes();
	
	
	@Query("SELECT new com.agg.application.model.QuoteDO(quote.id.id, quote.id.quoteId, quote.dealer.name, customerInfo.name, "
			+ "quote.machineInfo.model, quote.machineSaleDate, quote.status, quote.createDate)"
			+ " from Quote quote, CustomerInfo customerInfo"
			+ " where quote.id.quoteId=customerInfo.quoteId"
			+ " and quote.dealer.id = :dealerId")
	public List<QuoteDO> findAllQuotesByDealer(@Param("dealerId")long dealerId);
	
	
}
