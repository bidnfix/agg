package com.agg.application.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import com.agg.application.entity.Dealer;
import com.agg.application.model.DealerDO;
import com.agg.application.model.DealerDropDownDO;

@Component
public interface DealerDAO extends CrudRepository<Dealer, Long>{
	
	//public List<Dealer> findByAccountRoleRTitle(String dealerTitle);
	
	public List<Dealer> findByStatusOrderByNameAsc(int status);
	
	@Query("select new com.agg.application.model.DealerDropDownDO(dealer.id, dealer.name, dealer.code, dealer.city, dealer.parentCode) from Dealer dealer "
			+ "where dealer.code = dealer.parentCode order by dealer.name asc")
	public List<DealerDropDownDO> findParentDealers();
	
	public Dealer findByCode(long parentCode);
	
	public long countByStatus(int status);

	//public List<Dealer> findByIdAndStatus(long id, int active);
	
	@Query("select new com.agg.application.model.DealerDO(dealer.id, dealer.code, dealer.name, dealer.state, "
			+ "dealer.marketEmail, dealer.invoiceEmail, dealer.phone, dealer.parentCode, account.role.rTitle, dealer.status) "
			+ "from Dealer dealer, Account account "
			+ "where dealer.id = account.dealer.id and account.role.rTitle = :roleTitle")
	public List<DealerDO> findDealers(@Param("roleTitle")String roleTitle);
	
	@Query("select new com.agg.application.model.DealerDO(dealer.id, dealer.code, dealer.name, dealer.state, "
			+ "dealer.marketEmail, dealer.invoiceEmail, dealer.phone, dealer.parentCode, dealer.status) "
			+ "from Dealer dealer ")
	public List<DealerDO> findAllDealers();

}
