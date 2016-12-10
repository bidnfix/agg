package com.agg.application.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import com.agg.application.entity.Dealer;

@Component
public interface DealerDAO extends CrudRepository<Dealer, Long>{
	
	//public List<Dealer> findByAccountRoleRTitle(String dealerTitle);
	
	public List<Dealer> findByStatusOrderByNameAsc(int status);
	
	@Query("select dealer from Dealer dealer where dealer.code = dealer.parentCode order by dealer.name asc")
	public List<Dealer> findParentDealers();
	
	public Dealer findByCode(long parentCode);
	
	public long countByStatus(int status);

	//public List<Dealer> findByIdAndStatus(long id, int active);

}
