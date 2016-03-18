package com.agg.application.service;

import java.util.List;

import com.agg.application.model.DealerDO;
import com.agg.application.model.LocationDO;

public interface DealerService {

	public List<DealerDO> getDealers();

	public long saveDealer(DealerDO dealerDO);

	public DealerDO getDealer(long dealerId);

	public long saveLocation(LocationDO locationDO);

}
