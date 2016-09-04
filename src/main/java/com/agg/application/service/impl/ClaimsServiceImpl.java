package com.agg.application.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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
import com.agg.application.model.ClaimLaborDO;
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

	/* (non-Javadoc)
	 * @see com.agg.application.service.ClaimsService#getClaimsByCStatus(byte)
	 */
	@Override
	public List<ClaimsDO> getClaimsByCStatus(byte cStatus) {
		List<Claims> claimsList = claimsDAO.findAllByCStatus(cStatus);
		List<ClaimLabor> claimsLaborList = null;
		List<ClaimPart> claimPartList = null;
		List<Integer> claimIdList = new ArrayList<>();
		for(Claims claims : claimsList){
			claimIdList.add(claims.getId());
		}
		if(!claimIdList.isEmpty()){
			claimsLaborList = claimLaborDAO.findAllByClaimID(claimIdList);
			claimPartList = claimPartDAO.findAllByClaimID(claimIdList);
		}
		return ConvertClaimToClaimsDO(claimsList, claimsLaborList, claimPartList);
	}
	
	@Override
	public List<ClaimsDO> getClaimsByCStatus(byte cStatus, int dealerId) {
		List<Claims> claimsList = claimsDAO.findAllByCStatus(cStatus, dealerId);
		List<ClaimLabor> claimsLaborList = null;
		List<ClaimPart> claimPartList = null;
		List<Integer> claimIdList = new ArrayList<>();
		for(Claims claims : claimsList){
			claimIdList.add(claims.getId());
		}
		if(!claimIdList.isEmpty()){
			claimsLaborList = claimLaborDAO.findAllByClaimID(claimIdList);
			claimPartList = claimPartDAO.findAllByClaimID(claimIdList);
		}
		return ConvertClaimToClaimsDO(claimsList, claimsLaborList, claimPartList);
	}
	
	private List<ClaimsDO> ConvertClaimToClaimsDO(List<Claims> claimsList, List<ClaimLabor> claimsLaborList, List<ClaimPart> claimPartList){
		List<ClaimsDO> claimsDOList = new ArrayList<>();
		Map<Integer, ClaimLabor> laborMap = new HashMap<>();
		Map<Integer, List<ClaimPart>> partMap = new HashMap<>();
		
		if(null != claimsLaborList){
			for(ClaimLabor claimLabor : claimsLaborList){
				laborMap.put(claimLabor.getClaimId(), claimLabor);
			}
		}
		if(null != claimPartList){
			for(ClaimPart claimPart : claimPartList){
				if(partMap.containsKey(claimPart.getClaimId())){
					List<ClaimPart> clList = partMap.get(claimPart.getClaimId());
					clList.add(claimPart);
				}else{
					List<ClaimPart> clList = new ArrayList<>();
					clList.add(claimPart);
					partMap.put(claimPart.getClaimId(), clList);
				}
			}
		}
		if(null != claimsList && !claimsList.isEmpty()){
			for(Claims claim : claimsList){
				ClaimsDO claimDO = new ClaimsDO();
				claimDO.setId(claim.getId());
				claimDO.setClaimId(claim.getClaimId());
				claimDO.setContractId(claim.getContractId());
				claimDO.setDealerId(claim.getDealerId());
				claimDO.setSerial(claim.getSerial());
				claimDO.setFailDate(claim.getFailDate());
				claimDO.setReportDate(claim.getReportDate());
				claimDO.setWorkOrder(claim.getWorkOrder());
				claimDO.setHoursBreakDown(claim.getHoursBreakDown());
				claimDO.setPreauthApprovedAmt(claim.getPreauthApprovedAmt());
				claimDO.setCustComplaint(claim.getCustComplaint());
				claimDO.setCauseFail(claim.getCauseFail());
				claimDO.setCorrectiveAction(claim.getCorrectiveAction());
				claimDO.setIsArchived(claim.getIsArchived());
				claimDO.setcStatus(claim.getcStatus());
				claimDO.setLastUpdate(claim.getLastUpdate());
				claimDO.setRequestedOtherCharges1(claim.getRequestedOtherCharges1());
				claimDO.setRequestedOtherCharges2(claim.getRequestedOtherCharges2());
				claimDO.setTotalAdjustedPartsCost(claim.getTotalAdjustedPartsCost());
				claimDO.setTotalAdjustedLaborCost(claim.getTotalAdjustedLaborCost());
				claimDO.setApprovedOtherCharges1(claim.getApprovedOtherCharges1());
				claimDO.setApprovedOtherCharges2(claim.getApprovedOtherCharges2());
				if(null != laborMap.get(claim.getId())){
					ClaimLabor cl = laborMap.get(claim.getId());
					ClaimLaborDO laborDO = new ClaimLaborDO();
					laborDO.setId(cl.getId());
					laborDO.setClaimId(cl.getClaimId());
					laborDO.setLaborDescr(cl.getLaborDescr());
					laborDO.setLaborNo(cl.getLaborNo());
					laborDO.setLaborHrs(cl.getLaborHrs());
					laborDO.setRate(cl.getRate());
					claimDO.setClaimLaborDO(laborDO);
				}
				
				if(null != partMap.get(claim.getId())){
					List<ClaimPartDO> cpl = new ArrayList<>();
					for(ClaimPart cp : partMap.get(claim.getId())){
						ClaimPartDO partDO = new ClaimPartDO();
						partDO.setId(cp.getId());
						partDO.setClaimId(cp.getClaimId());
						partDO.setPartDescr(cp.getPartDescr());
						partDO.setPartNo(cp.getPartNo());
						partDO.setQty(cp.getQty());
						partDO.setUnitPrice(cp.getQty());
						cpl.add(partDO);
					}
					claimDO.setClaimPartDO(cpl);
				}
				
				claimsDOList.add(claimDO);
			}
		}
		return claimsDOList;
	}

	/* (non-Javadoc)
	 * @see com.agg.application.service.ClaimsService#updateStatus(int, byte)
	 */
	@Override
	public void updateStatus(int id, byte status) {
		claimsDAO.updateStatus(id, status);
	}
	
}
