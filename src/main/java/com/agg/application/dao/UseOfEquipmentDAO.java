package com.agg.application.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import com.agg.application.entity.UseOfEquip;
import com.agg.application.model.MachineDO;
import com.agg.application.model.UseOfEquipmentDO;

@Component
public interface UseOfEquipmentDAO extends CrudRepository<UseOfEquip, Long>{
	
	@Query("select new com.agg.application.model.UseOfEquipmentDO(useOfEquip.id, useOfEquip.equipName, useOfEquip.discount) "
			+ "from UseOfEquip useOfEquip")
	List<UseOfEquipmentDO> findAllUseOfEquip();

}
