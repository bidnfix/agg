package com.agg.application.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import com.agg.application.entity.Account;
import com.agg.application.model.UserDO;

@Component
public interface AccountDAO extends CrudRepository<Account, Long>{
	
	List<Account> findByAccountTypeAccountType(String accountType);
	
	List<Account> findByRoleRId(long roleId);
	
	List<Account> findByDealerId(long dealerId);
	
	Account findByUserNameIgnoreCase(String userName);
	
	List<Account> findByRoleRTitle(String roleTitle);
	
	@Query("select new com.agg.application.model.UserDO(account.id, account.userName, account.firstName, "
			+ "account.lastName, account.accountType.accountType, account.role.rTitle, account.dealer.name, account.status) "
			+ "from Account account")
	List<UserDO> findAllDealerUsers();
	
	@Query("select new com.agg.application.model.UserDO(account.id, account.userName, account.firstName, "
			+ "account.lastName, account.accountType.accountType, account.role.rTitle, account.status) "
			+ "from Account account where account.role.rTitle = :rTitle")
	List<UserDO> findAllAdminUsers(@Param("rTitle") String rTitle);
	
	@Query("select new com.agg.application.model.UserDO(account.id, account.userName, account.firstName, "
			+ "account.lastName, account.accountType.accountType, account.role.rTitle, account.dealer.name, account.status) "
			+ "from Account account where account.dealer.id = :dealerId")
	List<UserDO> findAllDealerUsers(@Param("dealerId") long dealerId);
}
