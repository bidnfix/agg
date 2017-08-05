package com.agg.application.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import com.agg.application.entity.OEMWarrantyTier;
import com.agg.application.model.OEMWarrantyTierDO;

@Component
public interface OEMWarrantyTierDAO extends CrudRepository<OEMWarrantyTier, Long>{
	
	@Query("select new com.agg.application.model.OEMWarrantyTierDO(oemWarrantyTier.id, oemWarrantyTier.warrantyFrom, oemWarrantyTier.warrantyTo, "
			+ "oemWarrantyTier.adjFactor) from OEMWarrantyTier oemWarrantyTier")
	List<OEMWarrantyTierDO> findAllOEMWarrantyTier();

}
