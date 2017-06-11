package com.agg.application.dao;

import java.util.List;

import javax.persistence.SqlResultSetMapping;

import org.hibernate.annotations.NamedQuery;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;
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
	
/*	@Query("SELECT COUNT(*) FROM Quote q WHERE q.status=1")
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
	public int countByInvoiced(@Param("dealerId")long dealerId);*/
	
	@Query("SELECT COUNT(*) FROM Quote quote WHERE quote.status = :status and quote.isArchive = :isArchive")
	public int countByStatus(@Param("status")byte status, @Param("isArchive")short isArchive);
	
	@Query("SELECT COUNT(*) FROM Quote quote WHERE quote.status = :status and quote.isArchive = :isArchive and quote.dealer.id = :dealerId")
	public int countByStatus(@Param("status")byte status, @Param("isArchive")short isArchive, @Param("dealerId")long dealerId);
	
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
			+ "quote.machineInfo.model, quote.machineSaleDate, quote.status, quote.createDate, quote.isArchive)"
			+ " from Quote quote, CustomerInfo customerInfo"
			+ " where quote.id.quoteId=customerInfo.quoteId"
			+ " and quote.status = :status"
			+ " and quote.isArchive = :isArchive")
	public List<QuoteDO> findByQuoteStatus(@Param("status")byte status, @Param("isArchive")short isArchive);
	
	@Query("SELECT new com.agg.application.model.QuoteDO(quote.id.id, quote.id.quoteId, quote.dealer.name, customerInfo.name, "
			+ "quote.machineInfo.model, quote.machineSaleDate, quote.status, quote.createDate, quote.isArchive)"
			+ " from Quote quote, CustomerInfo customerInfo"
			+ " where quote.id.quoteId=customerInfo.quoteId"
			+ " and quote.dealer.id = :dealerId"
			+ " and quote.status = :status"
			+ " and quote.isArchive = :isArchive")
	public List<QuoteDO> findByDealerStatusAndDealerId(@Param("status")byte status, @Param("dealerId")long dealerId, @Param("isArchive")short isArchive);
	
	/*@Query("SELECT q FROM Quote q WHERE q.status= :status and q.dealer.id = :dealerId")
	public List<Quote> findByInvoiced(@Param("status")byte status, @Param("dealerId")long dealerId);*/
	
	@Query("SELECT new com.agg.application.model.QuoteDO(quote.id.id, quote.id.quoteId, quote.dealer.name, customerInfo.name, "
			+ "quote.machineInfo.model, quote.machineSaleDate, quote.status, quote.createDate, quote.lastUpdate, quote.isArchive)"
			+ " from Quote quote, CustomerInfo customerInfo"
			+ " where quote.id.quoteId=customerInfo.quoteId"
			+ " and quote.isArchive = :isArchive"
			+ " ORDER BY quote.lastUpdate DESC")
	public List<QuoteDO> findAllQuotes(@Param("isArchive")short isArchive);
	
	
	@Query("SELECT new com.agg.application.model.QuoteDO(quote.id.id, quote.id.quoteId, quote.dealer.name, customerInfo.name, "
			+ "quote.machineInfo.model, quote.machineSaleDate, quote.status, quote.createDate, quote.lastUpdate, quote.isArchive)"
			+ " from Quote quote, CustomerInfo customerInfo"
			+ " where quote.id.quoteId=customerInfo.quoteId"
			+ " and quote.dealer.id = :dealerId"
			+ " and quote.isArchive = :isArchive"
			+ " ORDER BY quote.lastUpdate DESC")
	public List<QuoteDO> findAllQuotesByDealer(@Param("dealerId")long dealerId, @Param("isArchive")short isArchive);
	
	
	@Query("SELECT new com.agg.application.model.QuoteDO(quote.id.id, quote.id.quoteId, quote.dealer.name, "
			+ "quote.machineSaleDate, quote.status, quote.createDate, quote.isArchive)"
			+ " from Quote quote"
			+ " where quote.status = :status"
			+ " and quote.isArchive = :isArchive")
	public List<QuoteDO> findEstPriceQuotesByStatus(@Param("status")byte status, @Param("isArchive")short isArchive);
	
	@Query("SELECT new com.agg.application.model.QuoteDO(quote.id.id, quote.id.quoteId, quote.dealer.name, "
			+ "quote.machineSaleDate, quote.status, quote.createDate, quote.isArchive)"
			+ " from Quote quote"
			+ " where quote.dealer.id = :dealerId"
			+ " and quote.status = :status"
			+ " and quote.isArchive = :isArchive")
	public List<QuoteDO> findEstPriceQuotesByStatusAndDealerId(@Param("status")byte status, @Param("dealerId")long dealerId, @Param("isArchive")short isArchive);

	@Query("SELECT COUNT(*)"
			+ " from Quote quote, CustomerInfo customerInfo"
			+ " where quote.id.quoteId=customerInfo.quoteId"
			+ " and quote.isArchive = :isArchive")
	public long findQuotesCount(@Param("isArchive")short isArchive);

	@Query("SELECT new com.agg.application.model.QuoteDO(quote.id.id, quote.id.quoteId, quote.dealer.name, customerInfo.name, "
			+ "quote.machineInfo.model, quote.machineSaleDate, quote.status, quote.createDate, quote.isArchive)"
			+ " from Quote quote, CustomerInfo customerInfo"
			+ " where quote.id.quoteId=customerInfo.quoteId"
			+ " and quote.isArchive = :isArchive")
	public List<QuoteDO> findQuotesByStatus(@Param("isArchive")short isArchive, Pageable pageable);

	/*@Query("SELECT COUNT(*)"
			+ " from Quote quote, CustomerInfo customerInfo"
			+ " where quote.id.quoteId=customerInfo.quoteId"
			+ " and quote.isArchive = :isArchive and (quote.id.quoteId like %:searchText% or quote.dealer.name like %:searchText% or"
			+ " customerInfo.name like %:searchText% or quote.machineInfo.model like %:searchText% or quote.machineSaleDate like %:searchText% or"
			+ " quote.status like %:searchText% or quote.createDate like %:searchText%)")*/
	@Query(value = "SELECT COUNT(*)"
			+ " from quotes quote LEFT JOIN customer_info customerInfo ON quote.quote_id=customerInfo.quote_id LEFT JOIN dealers dealer ON quote.dealer_id=dealer.id"
			+ " where"
			+ " quote.is_archive = :isArchive and (quote.quote_id regexp :searchText or dealer.name regexp :searchText or"
			+ " customerInfo.name regexp :searchText or quote.machine_model regexp :searchText or quote.machine_sale_date regexp :searchText or"
			+ " quote.status regexp :statusSearch or quote.create_date regexp :searchText)", nativeQuery = true)
	public long findQuotesCountForSearch(@Param("isArchive")short isArchive, @Param("searchText")String searchText, @Param("statusSearch")String statusSearch);
	
	@Query("SELECT new com.agg.application.model.QuoteDO(quote.id.id, quote.id.quoteId, quote.dealer.name, customerInfo.name, "
			+ "quote.machineInfo.model, quote.machineSaleDate, quote.status, quote.createDate, quote.isArchive)"
			+ " from Quote quote, CustomerInfo customerInfo"
			+ " where quote.id.quoteId=customerInfo.quoteId"
			+ " and quote.isArchive = :isArchive and (quote.id.quoteId like %:searchText% or quote.dealer.name like %:searchText% or"
			+ " customerInfo.name like %:searchText% or quote.machineInfo.model like %:searchText% or quote.machineSaleDate like %:searchText% or"
			+ " quote.status like %:searchText% or quote.createDate like %:searchText%)")
	public List<QuoteDO> findQuotesForSearchByStatus(@Param("isArchive")short isArchive, @Param("searchText")String searchText, Pageable pageable);
	
	@Query("SELECT new com.agg.application.model.QuoteDO(quote.id.id, quote.id.quoteId, quote.dealer.name, customerInfo.name, "
			+ "quote.machineInfo.model, quote.machineSaleDate, quote.status, quote.createDate, quote.isArchive)"
			+ " from Quote quote, CustomerInfo customerInfo"
			+ " where quote.id.quoteId=customerInfo.quoteId"
			+ " and quote.dealer.id = :dealerId"
			+ " and quote.isArchive = :isArchive")
	public List<QuoteDO> findAllQuotesByDealer(@Param("dealerId")long dealerId, @Param("isArchive")short isArchive, Pageable pageable);
	
	@Query("SELECT new com.agg.application.model.QuoteDO(quote.id.id, quote.id.quoteId, quote.dealer.name, customerInfo.name, "
			+ "quote.machineInfo.model, quote.machineSaleDate, quote.status, quote.createDate, quote.isArchive)"
			+ " from Quote quote, CustomerInfo customerInfo"
			+ " where quote.id.quoteId=customerInfo.quoteId"
			+ " and quote.dealer.id = :dealerId"
			+ " and quote.isArchive = :isArchive and (quote.id.quoteId like %:searchText% or quote.dealer.name like %:searchText% or"
			+ " customerInfo.name like %:searchText% or quote.machineInfo.model like %:searchText% or quote.machineSaleDate like %:searchText% or"
			+ " quote.status like %:searchText% or quote.createDate like %:searchText%)")
	public List<QuoteDO> findAllQuotesByDealerForSearch(@Param("dealerId")long dealerId, @Param("isArchive")short isArchive, @Param("searchText")String searchText, Pageable pageable);
	
	/*@Query("SELECT new com.agg.application.model.QuoteDO(quote.id.id, quote.id.quoteId, quote.dealer.name, customerInfo.name, "
			+ "quote.machineInfo.model, quote.machineSaleDate, quote.status, quote.createDate, quote.isArchive)"
			+ " from Quote quote, CustomerInfo customerInfo"
			+ " where quote.id.quoteId=customerInfo.quoteId"
			+ " and quote.dealer.id = :dealerId"
			+ " and quote.isArchive = :isArchive and (quote.id.quoteId like %:searchText% or quote.dealer.name like %:searchText% or"
			+ " customerInfo.name like %:searchText% or quote.machineInfo.model like %:searchText% or quote.machineSaleDate like %:searchText% or"
			+ " quote.status like %:searchText% or quote.createDate like %:searchText%)")*/
	/*@Query(value = "SELECT quote.id as id, quote.quote_id as quoteId, dealer.name as dealerName, customerInfo.name as customerName, quote.machine_model as model,"
			+ " quote.machine_sale_date as saleDate, cast(quote.status as  unsigned) as status, quote.create_date as createDate, quote.is_archive as archive"
			+ " from quotes quote LEFT JOIN customer_info customerInfo ON quote.quote_id=customerInfo.quote_id LEFT JOIN dealers dealer ON quote.dealer_id=dealer.id"
			+ " where quote.dealer_id = :dealerId"
			+ " and quote.is_archive = :isArchive and (quote.quote_id regexp :searchText or dealer.name regexp :searchText or"
			+ " customerInfo.name regexp :searchText or quote.machine_model regexp :searchText or quote.machine_sale_date regexp :searchText or"
			+ " quote.status regexp :statusSearch or quote.create_date regexp :searchText) ORDER BY :orderBy :orderDirection LIMIT :noOfRows, :pageLength", nativeQuery=true)*/
	@Query(value = "SELECT quote.id as id, quote.quote_id as quoteId, dealer.name as dealerName, customerInfo.name as customerName, quote.machine_model as model,"
			+ " quote.machine_sale_date as saleDate, cast(quote.status as  unsigned) as status, quote.create_date as createDate, quote.is_archive as archive"
			+ " from quotes quote LEFT JOIN customer_info customerInfo ON quote.quote_id=customerInfo.quote_id LEFT JOIN dealers dealer ON quote.dealer_id=dealer.id"
			+ " where quote.dealer_id = :dealerId"
			+ " and quote.is_archive = :isArchive and (quote.quote_id regexp :searchText or dealer.name regexp :searchText or"
			+ " customerInfo.name regexp :searchText or quote.machine_model regexp :searchText or quote.machine_sale_date regexp :searchText or"
			+ " quote.status regexp :statusSearch or quote.create_date regexp :searchText) \n-- #pageable\n", nativeQuery=true)
	public List<Object[]> findAllQuotesByDealerForSearch(@Param("dealerId")long dealerId, @Param("isArchive")short isArchive, @Param("searchText")String searchText, 
			/*@Param("noOfRows")int noOfRows, @Param("pageLength")int pageLength, @Param("orderBy")String orderBy, @Param("orderDirection")String orderDirection,*/
			@Param("statusSearch")String statusSearch, Pageable pageable);
	
	/*@Query("SELECT COUNT(*)"
			+ " from Quote quote, CustomerInfo customerInfo"
			+ " where quote.id.quoteId=customerInfo.quoteId"
			+ " and quote.dealer.id = :dealerId"
			+ " and quote.isArchive = :isArchive and (quote.id.quoteId like %:searchText% or quote.dealer.name like %:searchText% or"
			+ " customerInfo.name like %:searchText% or quote.machineInfo.model like %:searchText% or quote.machineSaleDate like %:searchText% or"
			+ " quote.status like %:searchText% or quote.createDate like %:searchText%)")*/
	@Query(value = "SELECT COUNT(*)"
			+ " from quotes quote LEFT JOIN customer_info customerInfo ON quote.quote_id=customerInfo.quote_id LEFT JOIN dealers dealer ON quote.dealer_id=dealer.id"
			+ " where quote.dealer_id = :dealerId"
			+ " and quote.is_archive = :isArchive and (quote.quote_id regexp :searchText or dealer.name regexp :searchText or"
			+ " customerInfo.name regexp :searchText or quote.machine_model regexp :searchText or quote.machine_sale_date regexp :searchText or"
			+ " quote.status regexp :statusSearch or quote.create_date regexp :searchText)", nativeQuery = true)
	public long findAllQuotesCountByDealerForSearch(@Param("dealerId")long dealerId, @Param("isArchive")short isArchive, @Param("searchText")String searchText, @Param("statusSearch")String statusSearch);
	
	@Query("SELECT COUNT(*)"
			+ " from Quote quote, CustomerInfo customerInfo"
			+ " where quote.id.quoteId=customerInfo.quoteId"
			+ " and quote.dealer.id = :dealerId"
			+ " and quote.isArchive = :isArchive")
	public long findAllQuotesCountByDealer(@Param("dealerId")long dealerId, @Param("isArchive")short isArchive);
	
	@Query("SELECT new com.agg.application.model.QuoteDO(quote.id.id, quote.id.quoteId, quote.dealer.name, customerInfo.name, "
			+ "quote.machineInfo.model, adjustment.basePrice, adjustment.invoiceDate, quote.machineSerial, quote.status, quote.lastUpdate, quote.isArchive)"
			+ " from Quote quote, CustomerInfo customerInfo, AdminAdjustment adjustment"
			+ " where quote.id.quoteId = customerInfo.quoteId"
			+ " and quote.id.quoteId = adjustment.quoteId"
			+ " and quote.status = :status"
			+ " and quote.isArchive = :isArchive")
	public List<QuoteDO> findInvoiceQuotes(@Param("status")byte status, @Param("isArchive")short isArchive);
	
	@Query("SELECT new com.agg.application.model.QuoteDO(quote.id.id, quote.id.quoteId, quote.dealer.name, customerInfo.name, "
			+ "quote.machineInfo.model, adjustment.basePrice, adjustment.invoiceDate, quote.machineSerial, quote.status, quote.lastUpdate, quote.isArchive)"
			+ " from Quote quote, CustomerInfo customerInfo, AdminAdjustment adjustment"
			+ " where quote.id.quoteId = customerInfo.quoteId"
			+ " and quote.id.quoteId = adjustment.quoteId"
			+ " and quote.status = :status"
			+ " and quote.isArchive = :isArchive"
			+ " and quote.dealer.id = :dealerId")
	public List<QuoteDO> findInvoiceQuotesByDealer(@Param("status")byte status, @Param("isArchive")short isArchive, @Param("dealerId")long dealerId);
	
	/*@Query(value = "SELECT new com.agg.application.model.QuoteDO(quote.id, quote.quote_id, quote.dealer_id, customerInfo.name, "
	+ "quote.machine_model, quote.machine_sale_date, quote.status, quote.create_date, quote.is_archive)"
	+ " from quotes quote, customer_info customerInfo"
	+ " where quote.quote_id=customerInfo.quote_id"
	+ " and quote.is_archive = :isArchive and (quote.quote_id regexp :searchText or quote.dealer_id regexp :searchText or"
	+ " customerInfo.name regexp :searchText or quote.machine_model regexp :searchText or quote.machine_sale_date regexp :searchText or"
	+ " quote.status regexp :searchText or quote.create_date regexp :searchText) order by :orderBy :orderDirection LIMIT :noOfRows, :pageLength", nativeQuery = true)*/
	@Query(value = "SELECT quote.id, quote.quote_id, dealer.name, customerInfo.name, quote.machine_model,"
	+ " quote.machine_sale_date, cast(quote.status as  unsigned), quote.create_date, quote.is_archive"
	+ " from quotes quote, customer_info customerInfo"
	+ " where quote.quote_id=customerInfo.quote_id"
	+ " and quote.is_archive = :isArchive and (quote.quote_id regexp :searchText or quote.dealer_id regexp :searchText or"
	+ " customerInfo.name regexp :searchText or quote.machine_model regexp :searchText or quote.machine_sale_date regexp :searchText or"
	+ " quote.status regexp :statusSearch or quote.create_date regexp :searchText) ORDER BY :orderBy :orderDirection LIMIT :noOfRows, :pageLength", nativeQuery = true)
	public List<Object[]> findQuotesForSearchByStatus1(@Param("isArchive")short isArchive, @Param("searchText")String searchText, 
			@Param("noOfRows")int noOfRows, @Param("pageLength")int pageLength, @Param("orderBy")String orderBy, @Param("orderDirection")String orderDirection,
			@Param("statusSearch")byte statusSearch);
	
	@Query(value = "SELECT quote.id as id, quote.quote_id as quoteId, dealer.name as dealerName, customerInfo.name as customerName, quote.machine_model as model,"
			+ " quote.machine_sale_date as saleDate, cast(quote.status as  unsigned) as status, quote.create_date as createDate, quote.is_archive as archive"
			+ " from quotes quote LEFT JOIN customer_info customerInfo ON quote.quote_id=customerInfo.quote_id LEFT JOIN dealers dealer ON quote.dealer_id=dealer.id"
			+ " where"
			+ " quote.is_archive = :isArchive and (quote.quote_id regexp :searchText or dealer.name regexp :searchText or"
			+ " customerInfo.name regexp :searchText or quote.machine_model regexp :searchText or quote.machine_sale_date regexp :searchText or"
			+ " quote.status regexp :statusSearch or quote.create_date regexp :searchText) ORDER BY :orderBy  :orderDirection LIMIT :noOfRows, :pageLength", nativeQuery=true)
	public List<Object[]> findQuotesForSearchByStatus(@Param("isArchive")short isArchive, @Param("searchText")String searchText, 
			@Param("noOfRows")int noOfRows, @Param("pageLength")int pageLength, @Param("orderBy")String orderBy, @Param("orderDirection")String orderDirection,
			@Param("statusSearch")byte statusSearch);

	@Query(value = "SELECT quote.id as id, quote.quote_id as quoteId, dealer.name as dealerName, customerInfo.name as customerName, quote.machine_model as model,"
			+ " quote.machine_sale_date as saleDate, cast(quote.status as  unsigned) as status, quote.create_date as createDate, quote.is_archive as archive"
			+ " from quotes quote LEFT JOIN customer_info customerInfo ON quote.quote_id=customerInfo.quote_id LEFT JOIN dealers dealer ON quote.dealer_id=dealer.id"
			+ " where"
			+ " quote.is_archive = :isArchive and (quote.quote_id regexp :searchText or dealer.name regexp :searchText or"
			+ " customerInfo.name regexp :searchText or quote.machine_model regexp :searchText or quote.machine_sale_date regexp :searchText or"
			+ " quote.status regexp :statusSearch or quote.create_date regexp :searchText) \n-- #pageable\n", nativeQuery=true)
	public List<Object[]> findQuotesForSearchByStatus(@Param("isArchive")short isArchive, @Param("searchText")String searchText, 
			/*@Param("noOfRows")int noOfRows, @Param("pageLength")int pageLength, @Param("orderCluase")String orderCluase,*/
			@Param("statusSearch")String statusSearch, Pageable pageable);
			
	//public List<QuoteDO> findQuotsForSearch(Specification<T> spec, Pageable pageable);
	
	@Query(value="select * from Invoice_Quotes", nativeQuery=true)
	List<Object[]> findAllQuotesForReport();
	
}
