package com.agg.application.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agg.application.dao.ManufacturerDAO;
import com.agg.application.dao.QuoteDAO;
import com.agg.application.entity.MachineInfo;
import com.agg.application.entity.MachineType;
import com.agg.application.entity.Manufacturer;
import com.agg.application.entity.Quote;
import com.agg.application.model.MachineDO;
import com.agg.application.model.MachineTypeDO;
import com.agg.application.model.ManufacturerDO;
import com.agg.application.model.QuoteDO;
import com.agg.application.service.ClaimsService;
import com.google.common.collect.Lists;

@Service
public class ClaimsServiceImpl implements ClaimsService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private QuoteDAO quoteDAO;
	
	@Autowired
	private ManufacturerDAO manufacturerDAO;
	
	public List<QuoteDO> getClaimsInfo()
	{
		logger.debug("Inside getClaimsInfo()");
		List<Quote>  quoteList =  Lists.newArrayList(quoteDAO.findAll());
		
		List<QuoteDO> quoteDOList = null;
		if(!quoteList.isEmpty()){
			logger.debug("QuoteList size:"+quoteList.size());
			quoteDOList = new ArrayList<QuoteDO>();
			QuoteDO quoteDO = null;
			ManufacturerDO manufacturerDO = null; 
			Quote quote = null;
			/*ManufacturerDO manufacturerDO = null;
			MachineTypeDO machineTypeDO = null;*/
			
			Iterator<Quote> it = quoteList.iterator();
			while(it.hasNext()){
				quoteDO = new QuoteDO();
				manufacturerDO = new ManufacturerDO();
				quote = it.next();
				//logger.debug("machineInfo.getMachineType() " +machineInfo.getMachineType().getMachineType());
				quoteDO.setId(quote.getId());
				quoteDO.setQuoteId(quote.getId().getQuoteId());
				
				//logger.debug("__quote id "+quote.getId().getQuoteId()+ "  "+quote.getId().getId());
				
				Manufacturer manf = manufacturerDAO.findOne(Long.valueOf(quote.getManufacturer().getManfId()));
				
				manufacturerDO.setId(quote.getManufacturer().getManfId());
				manufacturerDO.setName(manf.getManfName());
				
				quoteDO.setManufacturerDO(manufacturerDO);
				
				
				quoteDO.setMachineModel(quote.getMachineModel());
				quoteDO.setMachineSerial(quote.getMachineSerial());
				quoteDO.setCoverageTerm(quote.getCoverageTerm());
				quoteDOList.add(quoteDO);
			}
		}
		logger.debug("quoteDOList size: "+quoteDOList.size());
		return quoteDOList;
	}
	
	}
