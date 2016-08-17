package com.agg.application.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.agg.application.dao.ClaimLaborDAO;
import com.agg.application.dao.ClaimPartDAO;
import com.agg.application.dao.ClaimsDAO;
import com.agg.application.dao.ManufacturerDAO;
import com.agg.application.dao.QuoteDAO;
import com.agg.application.entity.ClaimLabor;
import com.agg.application.entity.ClaimPart;
import com.agg.application.entity.Claims;
import com.agg.application.entity.Manufacturer;
import com.agg.application.entity.Quote;
import com.agg.application.model.ClaimPartDO;
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
	
	@Autowired
	private ClaimPartDAO claimPartDAO;
	
	@Autowired
	private ClaimLaborDAO claimLaborDAO;
	
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
	
	@Transactional
	public Long saveClaim(ClaimsDO claimsDO)
	{
		Claims claim = new Claims();
		claim.setClaimId(claimsDO.getClaimId());
		claim.setContractId(claimsDO.getContractId());
		claim.setDealerId(claimsDO.getDealerId());
		claim.setSerial(claimsDO.getSerial());
		claim.setFailDate(claimsDO.getFailDate());
		claim.setReportDate(claimsDO.getReportDate());
		claim.setWorkOrder(claimsDO.getWorkOrder());
		claim.setHoursBreakDown(claimsDO.getHoursBreakDown());
		claim.setPreauthApprovedAmt(claimsDO.getPreauthApprovedAmt());
		claim.setCustComplaint(claimsDO.getCustComplaint());
		claim.setCauseFail(claimsDO.getCauseFail());
		claim.setCorrectiveAction(claimsDO.getCorrectiveAction());
		claim.setIsArchived(claimsDO.getIsArchived());
		claim.setcStatus(claimsDO.getcStatus());
		claim.setRequestedOtherCharges1(claimsDO.getRequestedOtherCharges1());
		claim.setRequestedOtherCharges2(claimsDO.getRequestedOtherCharges2());
		claim.setTotalAdjustedPartsCost(claimsDO.getTotalAdjustedPartsCost());
		claim.setTotalAdjustedLaborCost(claimsDO.getTotalAdjustedLaborCost());
		claim.setApprovedOtherCharges1(claimsDO.getApprovedOtherCharges1());
		claim.setApprovedOtherCharges2(claimsDO.getApprovedOtherCharges2());
		
		Claims newClaim = claimsDAO.save(claim);
		
		if(null != claimsDO.getClaimPartDO() && !claimsDO.getClaimPartDO().isEmpty()){
			List<ClaimPart> claimPartList = new ArrayList<>();
			for(ClaimPartDO claimPartDO : claimsDO.getClaimPartDO()){
				ClaimPart claimPart = new ClaimPart();
				claimPart.setClaimId(newClaim.getId());
				claimPart.setPartNo(claimPartDO.getPartNo());
				claimPart.setPartDescr(claimPartDO.getPartDescr());
				claimPart.setQty(claimPartDO.getQty());
				claimPart.setUnitPrice(claimPartDO.getUnitPrice());
				claimPartList.add(claimPart);
			}
			claimPartDAO.save(claimPartList);
		}
		
		ClaimLabor claimLabor = new ClaimLabor();
		claimLabor.setClaimId(newClaim.getId());
		claimLabor.setLaborNo("LNO");
		claimLabor.setLaborDescr("L Desc");
		claimLabor.setLaborHrs(claimsDO.getClaimLaborDO().getLaborHrs());
		claimLabor.setRate(claimsDO.getClaimLaborDO().getRate());
		claimLaborDAO.save(claimLabor);
		return (long)newClaim.getId();
	}
	
}
