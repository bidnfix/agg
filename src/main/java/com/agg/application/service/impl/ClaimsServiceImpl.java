package com.agg.application.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agg.application.dao.ClaimsDAO;
import com.agg.application.dao.ManufacturerDAO;
import com.agg.application.dao.QuoteDAO;
import com.agg.application.entity.Claims;
import com.agg.application.entity.Manufacturer;
import com.agg.application.entity.Quote;
import com.agg.application.model.ClaimsDO;
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
	
	@Autowired
	private ClaimsDAO claimsDAO;
	
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
				quoteDO.setId(quote.getId().getId());
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
	
	/**
	 * 
	 * @param serialNo
	 * @return
	 */
	public List<QuoteDO> getClaimInfoBySerialNumber(final String serialNo){
		logger.debug("Inside getClaimInfoBySerialNumber()");
		List<Quote> quoteList= Lists.newArrayList(quoteDAO.findByMachineSerial(serialNo));
		List<QuoteDO> quoteDOList = new ArrayList<QuoteDO>();
		QuoteDO quoteDO = null;
		ManufacturerDO manufacturerDO = null; 
		
		for(Quote quote : quoteList){
			quoteDO = new QuoteDO();
			manufacturerDO = new ManufacturerDO();
			quoteDO.setId(quote.getId().getId());
			quoteDO.setQuoteId(quote.getId().getQuoteId());
			Manufacturer manf = manufacturerDAO.findOne(Long.valueOf(quote.getManufacturer().getManfId()));
			
			manufacturerDO.setId(quote.getManufacturer().getManfId());
			manufacturerDO.setName(manf.getManfName());
			
			quoteDO.setManufacturerDO(manufacturerDO);
			
			
			quoteDO.setMachineModel(quote.getMachineModel());
			quoteDO.setMachineSerial(quote.getMachineSerial());
			quoteDO.setCoverageTerm(quote.getCoverageTerm());
			quoteDOList.add(quoteDO);
		}
		logger.debug("quoteDOList size: "+quoteDOList.size());
		return quoteDOList;
	}
	
	public QuoteDO getClaimInfo(String claimId)
	{
		logger.debug("Inside getClaimInfo()");
		Quote  quote = null;//quoteDAO.find(new QuotePK(claimId, 1));
		
		QuoteDO quoteDO = null;
		if(quote != null){
			logger.debug("QuoteList size:"+quote.getGroupId());
			//quoteDOList = new ArrayList<QuoteDO>();
			
			ManufacturerDO manufacturerDO = null; 
			//Quote quote = null;
			/*ManufacturerDO manufacturerDO = null;
			MachineTypeDO machineTypeDO = null;*/
			
			//Iterator<Quote> it = quoteList.iterator();
			//while(it.hasNext()){
				quoteDO = new QuoteDO();
				manufacturerDO = new ManufacturerDO();
				//quote = it.next();
				//logger.debug("machineInfo.getMachineType() " +machineInfo.getMachineType().getMachineType());
				quoteDO.setId(quote.getId().getId());
				quoteDO.setQuoteId(quote.getId().getQuoteId());
				
				//logger.debug("__quote id "+quote.getId().getQuoteId()+ "  "+quote.getId().getId());
				
				Manufacturer manf = manufacturerDAO.findOne(Long.valueOf(quote.getManufacturer().getManfId()));
				
				manufacturerDO.setId(quote.getManufacturer().getManfId());
				manufacturerDO.setName(manf.getManfName());
				
				quoteDO.setManufacturerDO(manufacturerDO);
				
				
				quoteDO.setMachineModel(quote.getMachineModel());
				quoteDO.setMachineSerial(quote.getMachineSerial());
				quoteDO.setCoverageTerm(quote.getCoverageTerm());
				//quoteDOList.add(quoteDO);
			//}
		}
		//logger.debug("quoteDOList size: "+quoteDOList.size());
		return quoteDO;
	}
	
	public Long saveClaim(ClaimsDO claimsDO)
	{
		Claims claim = new Claims();
		claim.setCauseFail(claimsDO.getCauseFail());
		claim.setCorrectiveAction(claimsDO.getCorrectiveAction());
		claim.setCustComplaint(claimsDO.getCustComplaint());
		claim.setDealerAddress1(claimsDO.getDealerAddress1());
		claim.setDealerAddress2(claimsDO.getDealerAddress2());
		claim.setDealerCity(claimsDO.getDealerCity());
		claim.setDealerContact(claimsDO.getDealerContact());
		claim.setDealerEmail(claimsDO.getDealerEmail());
		claim.setDealerId(claimsDO.getDealerId());
		claim.setDealerName(claimsDO.getDealerName());
		claim.setDealerPhone(claimsDO.getDealerPhone());
		claim.setDealerState(claimsDO.getDealerState());
		claim.setDealerZip(claimsDO.getDealerZip());
		claim.setFailDate(claimsDO.getFailDate());
		claim.setReportDate(claimsDO.getReportDate());
		claim.setHourlyRate(claimsDO.getHourlyRate());
		claim.setHoursBreakDown(claimsDO.getHoursBreakDown());
		claim.setIsArchived(claimsDO.getIsArchived());
		claim.setLabourHours(claimsDO.getLabourHours());
		claim.setLastUpdate(claimsDO.getLastUpdate());
		claim.setManf(claimsDO.getManf());
		claim.setModel(claimsDO.getModel());
		claim.setNewClaimId(claimsDO.getNewClaimId());
		claim.setOtherCharges1(claimsDO.getOtherCharges1());
		claim.setOtherCharges2(claimsDO.getOtherCharges2());
		claim.setOtherManf(claimsDO.getOtherManf());
		claim.setOtherModel(claimsDO.getOtherModel());
		claim.setPartsTotal(claimsDO.getPartsTotal());
		claim.setPreAuth(claimsDO.getPreAuth());
		claim.setContractId(claimsDO.getContractId());
		claim.setSerial(claimsDO.getSerial());
		claim.setWorkOrder(claimsDO.getWorkOrder());
		claim.setCLevel(claimsDO.getCLevel());
		Claims c = claimsDAO.save(claim);
		return (long)c.getClaimId();
	}
	
}
