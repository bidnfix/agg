package com.agg.application.service.impl;

import java.sql.Timestamp;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.agg.application.dao.AccountDAO;
import com.agg.application.dao.CheckDAO;
import com.agg.application.dao.ClaimFileDAO;
import com.agg.application.dao.ClaimLaborDAO;
import com.agg.application.dao.ClaimNotesDAO;
import com.agg.application.dao.ClaimPartDAO;
import com.agg.application.dao.ClaimsDAO;
import com.agg.application.dao.ContractsDAO;
import com.agg.application.dao.CustomerInfoDAO;
import com.agg.application.dao.DealerDAO;
import com.agg.application.dao.MachineInfoDAO;
import com.agg.application.dao.ManufacturerDAO;
import com.agg.application.dao.QuoteDAO;
import com.agg.application.entity.Account;
import com.agg.application.entity.Check;
import com.agg.application.entity.ClaimFile;
import com.agg.application.entity.ClaimLabor;
import com.agg.application.entity.ClaimNote;
import com.agg.application.entity.ClaimPart;
import com.agg.application.entity.Claims;
import com.agg.application.entity.Contracts;
import com.agg.application.entity.Dealer;
import com.agg.application.entity.Manufacturer;
import com.agg.application.entity.Quote;
import com.agg.application.model.AccountDO;
import com.agg.application.model.CheckDO;
import com.agg.application.model.ClaimFileDO;
import com.agg.application.model.ClaimLaborDO;
import com.agg.application.model.ClaimNoteDO;
import com.agg.application.model.ClaimPartDO;
import com.agg.application.model.ClaimReportDO;
import com.agg.application.model.ClaimsDO;
import com.agg.application.model.ContractDO;
import com.agg.application.model.DealerDO;
import com.agg.application.model.ManufacturerDO;
import com.agg.application.model.QuoteDO;
import com.agg.application.service.ClaimsService;
import com.agg.application.utils.AggConstants;
import com.agg.application.utils.Util;
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
	
	@Autowired
	private DealerDAO dealerDAO;
	
	@Autowired
	private MachineInfoDAO machineInfoDAO;
	
	@Autowired
	private ContractsDAO contractsDAO;
	
	@Autowired
	private ClaimFileDAO claimFileDAO;
	
	@Autowired
	private ClaimNotesDAO claimNotesDAO;
	
	@Autowired
	private AccountDAO accountDAO;
	
	@Autowired
	private CustomerInfoDAO customerInfoDAO;
	
	@Autowired
	private CheckDAO checkDAO;
	
	
	public List<ClaimsDO> getClaimsInfo(AccountDO accountDO){
		logger.debug("Inside getApprovedClaims()");
		List<ClaimsDO> claimsDOList = null;
		if(accountDO.getRoleDO().getAccountType().equalsIgnoreCase(AggConstants.ACCOUNT_TYPE_ADMIN)){
			claimsDOList = claimsDAO.findAdminClaimsInfo((byte)AggConstants.CLAIM_STATUS_DRAFT);
		}else{
			logger.debug("Not an Admin :"+accountDO.getDealerId());
			claimsDOList = claimsDAO.findClaimsInfo(new Long(accountDO.getDealerId()).intValue());
		}
		if(claimsDOList != null){
			logger.debug("claimsDOList size: "+claimsDOList.size());
		}
		return claimsDOList;
	}
	
	public List<ClaimsDO> getAprvOrRejClaims(AccountDO accountDO, byte cStatus){
		logger.debug("Inside getApprovedClaims()");
		List<ClaimsDO> claimsDOList = null;
		if(accountDO.getRoleDO().getAccountType().equalsIgnoreCase(AggConstants.ACCOUNT_TYPE_ADMIN)){
			claimsDOList = claimsDAO.findApprovedClaims(cStatus);
		}else{
			logger.debug("Not an Admin :"+accountDO.getDealerId());
			claimsDOList = claimsDAO.findApprovedClaims(cStatus, accountDO.getDealerId());
		}
		if(claimsDOList != null){
			logger.debug("claimsDOList size: "+claimsDOList.size());
		}
		return claimsDOList;
	}
	
	public List<ClaimsDO> getAprvOrRejClaims(AccountDO accountDO, List<Byte> statusList){
		logger.debug("Inside getApprovedClaims()");
		List<ClaimsDO> claimsDOList = null;
		if(accountDO.getRoleDO().getAccountType().equalsIgnoreCase(AggConstants.ACCOUNT_TYPE_ADMIN)){
				
			claimsDOList = claimsDAO.findRejectedClaims(statusList);
		}else{
			logger.debug("Not an Admin :"+accountDO.getDealerId());
			claimsDOList = claimsDAO.findRejectedClaims(statusList, accountDO.getDealerId());
		}
		if(claimsDOList != null){
			logger.debug("claimsDOList size: "+claimsDOList.size());
		}
		return claimsDOList;
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
			
			
			quoteDO.setMachineModel(quote.getMachineInfo().getModel());
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
				
				
				quoteDO.setMachineModel(quote.getMachineInfo().getModel());
				quoteDO.setMachineSerial(quote.getMachineSerial());
				quoteDO.setCoverageTerm(quote.getCoverageTerm());
				//quoteDOList.add(quoteDO);
			//}
		}
		//logger.debug("quoteDOList size: "+quoteDOList.size());
		return quoteDO;
	}
	
	@Transactional
	public Claims saveClaim(ClaimsDO claimsDO, AccountDO accountDO)
	{
		Claims claim = null;
		if(claimsDO.getId() != 0){
			claim = claimsDAO.findOne(claimsDO.getId());
		}else{
			claim = new Claims();
		}
		claim.setId(claimsDO.getId());
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
		claim.setLastUpdate(new Date());
		if(claimsDO.getId() == 0){
			claim.setCrtaedBy(accountDO.getId());
			claim.setCreatedDate(new Date());
		}
		claim.setUpdatedBy(accountDO.getId());
		
		Claims newClaim = claimsDAO.save(claim);
		
		if(null != claimsDO.getClaimPartDO() && !claimsDO.getClaimPartDO().isEmpty()){
			List<ClaimPart> claimPartList = new ArrayList<>();
			for(ClaimPartDO claimPartDO : claimsDO.getClaimPartDO()){
				ClaimPart claimPart = new ClaimPart();
				claimPart.setId(claimPartDO.getId());
				claimPart.setClaimId(newClaim.getId());
				claimPart.setPartNo(claimPartDO.getPartNo());
				claimPart.setPartDescr(claimPartDO.getPartDescr());
				claimPart.setQty(claimPartDO.getQty());
				claimPart.setUnitPrice(claimPartDO.getUnitPrice());
				claimPart.setAdjQty(claimPartDO.getQty());
				claimPart.setAdjUnitPrice(claimPartDO.getUnitPrice());
				claimPartList.add(claimPart);
			}
			int records = claimPartDAO.deleteClaimPartsByClaimId(newClaim.getId());
			logger.info("b4 insert ClaimParts, "+records+" records deleted for claim: "+newClaim.getClaimId());
			claimPartDAO.save(claimPartList);
		}
		
		if(null != claimsDO.getClaimLaborDO() && !claimsDO.getClaimLaborDO().isEmpty()){
			List<ClaimLabor> claimLaborList = new ArrayList<>();
			for(ClaimLaborDO laborDO : claimsDO.getClaimLaborDO()){
				ClaimLabor claimLabor = new ClaimLabor();
				claimLabor.setId(laborDO.getId());
				claimLabor.setLaborNo(laborDO.getLaborNo());
				claimLabor.setLaborDescr(laborDO.getLaborDescr());
				claimLabor.setLaborHrs(laborDO.getLaborHrs());
				claimLabor.setRate(laborDO.getRate());
				claimLabor.setClaimId(newClaim.getId());
				claimLabor.setAdjLaborHrs(laborDO.getLaborHrs());
				claimLabor.setAdjRate(laborDO.getRate());
				claimLaborList.add(claimLabor);
			}
			int records = claimLaborDAO.deleteClaimLaborsByClaimId(newClaim.getId());
			logger.info("b4 insert ClaimLabors, "+records+" records deleted for claim: "+newClaim.getClaimId());
			claimLaborDAO.save(claimLaborList);
		}
		
		if(null != claimsDO.getClaimFileDO() && !claimsDO.getClaimFileDO().isEmpty()){
			List<ClaimFile> claimFiles = new ArrayList<>();
			for(ClaimFileDO fileDO : claimsDO.getClaimFileDO()){
				ClaimFile cFile = new ClaimFile();
				cFile.setFileName(fileDO.getFileName());
				cFile.setClaimId(newClaim.getId());
				claimFiles.add(cFile);
			}
			
			claimFileDAO.save(claimFiles);
		}
		
		//Added to save claim notes
		if(Util.isNotEmptyString(claimsDO.getComments())){
			logger.debug("External comments - Claim creation "+claimsDO.getComments());
				ClaimNote cNote = new ClaimNote();
				cNote.setNotes(claimsDO.getComments());
				cNote.setAccountId(accountDO.getId());
				cNote.setClaimId(newClaim.getId());
				cNote.setLastUpdate(new Date());
				cNote.setNoteType(AggConstants.EXTERNAL_CLAIM_NOTE);
			claimNotesDAO.save(cNote);
		}
		
		return newClaim;
	}

	/* (non-Javadoc)
	 * @see com.agg.application.service.ClaimsService#getClaimsByCStatus(byte)
	 */
	@Override
	public List<ClaimsDO> getClaimsByCStatus(byte cStatus, boolean contractInfo, int dealerId) {
		List<Claims> claimsList = claimsDAO.findAllByCStatus(cStatus);
		List<ClaimLabor> claimsLaborList = null;
		List<ClaimPart> claimPartList = null;
		List<Contracts> contractsList = null;
		List<String> contractIdList = new ArrayList<>();
		List<Integer> claimIdList = new ArrayList<>();
		List<ClaimFile> claimFileList = null;
		for(Claims claims : claimsList){
			claimIdList.add(claims.getId());
			if(contractInfo){
				contractIdList.add(claims.getContractId());
			}
		}
		if(!claimIdList.isEmpty()){
			claimsLaborList = claimLaborDAO.findAllByClaimID(claimIdList);
			claimPartList = claimPartDAO.findAllByClaimID(claimIdList);
			claimFileList = claimFileDAO.findAllByClaimID(claimIdList);
			if(contractInfo && !contractIdList.isEmpty()){
				contractsList = contractsDAO.findAllByContractID(contractIdList);
			}
		}
		return ConvertClaimToClaimsDO(claimsList, claimsLaborList, claimPartList, contractsList, claimFileList, dealerId);
	}
	
	@Override
	public ClaimsDO getClaim(int claimId, int dealerId){
		Claims claim = claimsDAO.findOne(claimId);
		List<Claims> claimsList = new ArrayList<Claims>();
		claimsList.add(claim);
		List<ClaimLabor> claimsLaborList = null;
		List<ClaimPart> claimPartList = null;
		List<Contracts> contractsList = null;
		List<String> contractIdList = new ArrayList<String>();
		List<Integer> claimIdList = new ArrayList<Integer>();
		List<ClaimFile> claimFileList = null;
		for(Claims claims : claimsList){
			claimIdList.add(claims.getId());
			contractIdList.add(claims.getContractId());
		}
		if(!claimIdList.isEmpty()){
			claimsLaborList = claimLaborDAO.findAllByClaimID(claimIdList);
			claimPartList = claimPartDAO.findAllByClaimID(claimIdList);
			if(!contractIdList.isEmpty()){
				contractsList = contractsDAO.findAllByContractID(contractIdList);
			}
		}
		List<ClaimsDO> list = ConvertClaimToClaimsDO(claimsList, claimsLaborList, claimPartList, contractsList, claimFileList, dealerId);
		return (list.isEmpty()) ? null : list.get(0);
	}
	
	@Override
	public ClaimsDO getClaim(String claimId, int dealerId){
		Claims claim = claimsDAO.findClaimsByClaimId(claimId);
		List<Claims> claimsList = new ArrayList<>();
		claimsList.add(claim);
		List<ClaimLabor> claimsLaborList = null;
		List<ClaimPart> claimPartList = null;
		List<Contracts> contractsList = null;
		List<String> contractIdList = new ArrayList<>();
		List<Integer> claimIdList = new ArrayList<>();
		List<ClaimFile> claimFileList = null;
		for(Claims claims : claimsList){
			claimIdList.add(claims.getId());
			contractIdList.add(claims.getContractId());
		}
		if(!claimIdList.isEmpty()){
			claimsLaborList = claimLaborDAO.findAllByClaimID(claimIdList);
			claimPartList = claimPartDAO.findAllByClaimID(claimIdList);
			if(!contractIdList.isEmpty()){
				contractsList = contractsDAO.findAllByContractID(contractIdList);
			}
			claimFileList = claimFileDAO.findAllByClaimID(claimIdList);
		}
		List<ClaimsDO> list = ConvertClaimToClaimsDO(claimsList, claimsLaborList, claimPartList, contractsList, claimFileList, dealerId);
		return (list.isEmpty()) ? null : list.get(0);
	}
	
	@Override
	public List<ClaimsDO> getClaimsByCStatus(byte cStatus, int dealerId, boolean contractInfo) {
		List<Claims> claimsList = claimsDAO.findAllByCStatus(cStatus, dealerId);
		List<ClaimLabor> claimsLaborList = null;
		List<ClaimPart> claimPartList = null;
		List<Contracts> contractsList = null;
		List<ClaimFile> claimFileList = null;
		List<Integer> claimIdList = new ArrayList<>();
		List<String> contractIdList = new ArrayList<>();
		for(Claims claims : claimsList){
			claimIdList.add(claims.getId());
			if(contractInfo){
				contractIdList.add(claims.getContractId());
			}
		}
		if(!claimIdList.isEmpty()){
			claimsLaborList = claimLaborDAO.findAllByClaimID(claimIdList);
			claimPartList = claimPartDAO.findAllByClaimID(claimIdList);
			claimFileList = claimFileDAO.findAllByClaimID(claimIdList);
			if(contractInfo && !contractIdList.isEmpty()){
				contractsList = contractsDAO.findAllByContractID(contractIdList);
			}
		}
		return ConvertClaimToClaimsDO(claimsList, claimsLaborList, claimPartList, contractsList, claimFileList, dealerId);
	}
	
	private List<ClaimsDO> ConvertClaimToClaimsDO(List<Claims> claimsList, List<ClaimLabor> claimsLaborList, List<ClaimPart> claimPartList, List<Contracts> contractsList, List<ClaimFile> claimFileList, int loginDealerId){
		List<ClaimsDO> claimsDOList = new ArrayList<>();
		Map<Integer, List<ClaimLabor>> laborMap = new HashMap<>();
		Map<Integer, List<ClaimFile>> fileMap = new HashMap<>();
		Map<Integer, List<ClaimPart>> partMap = new HashMap<>();
		Map<String, Contracts> contractsMap = new HashMap<>();
		if(null != claimsLaborList){
			for(ClaimLabor claimLabor : claimsLaborList){
				if(laborMap.containsKey(claimLabor.getClaimId())){
					List<ClaimLabor> cList = laborMap.get(claimLabor.getClaimId());
					cList.add(claimLabor);
				}else{
					List<ClaimLabor> cList = new ArrayList<>();
					cList.add(claimLabor);
					laborMap.put(claimLabor.getClaimId(), cList);
				}
			}
		}
		if(null != claimFileList){
			for(ClaimFile claimFile : claimFileList){
				if(fileMap.containsKey(claimFile.getClaimId())){
					List<ClaimFile> cList = fileMap.get(claimFile.getClaimId());
					cList.add(claimFile);
				}else{
					List<ClaimFile> cList = new ArrayList<>();
					cList.add(claimFile);
					fileMap.put(claimFile.getClaimId(), cList);
				}
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
		if(null != contractsList){
			for(Contracts contract :contractsList){
				contractsMap.put(contract.getContractId(), contract);
			}
		}
		if(null != claimsList && !claimsList.isEmpty()){
			Dealer dealer = null;
			DealerDO dealerDO = null;
			ClaimsDO claimDO = null;
			for(Claims claim : claimsList){
				claimDO = new ClaimsDO();
				claimDO.setId(claim.getId());
				claimDO.setClaimId(claim.getClaimId());
				claimDO.setContractId(claim.getContractId());
				claimDO.setDealerId(claim.getDealerId());
				if(claim.getDealerId() > 0){
					dealer = dealerDAO.findOne(Long.valueOf(claim.getDealerId()));
					if(dealer != null){
						dealerDO = new DealerDO();
						dealerDO.setInvoiceEmail(dealer.getInvoiceEmail());
						dealerDO.setName(dealer.getName());
						dealerDO.setId(dealer.getId());
						dealerDO.setMarketEmail(dealer.getMarketEmail());
						dealerDO.setAddress1(dealer.getAddress());
						dealerDO.setDealerUrl(dealer.getUrl());
						
						claimDO.setDealerDO(dealerDO);
					}
					
				}
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
				claimDO.setCreatedBy(claim.getCrtaedBy());
				claimDO.setUpdatedBy(claim.getUpdatedBy());
				claimDO.setCreatedDate(claim.getCreatedDate());
				claimDO.setCheqNo(claim.getCheqNo());
				claimDO.setPaidDate(claim.getPaidDate());
				
				Contracts contract = contractsDAO.findByContractId(claim.getContractId());
				Quote quote = quoteDAO.findOne((int)contract.getQuoteId());
				claimDO.setManufacturer(quote.getManufacturer().getManfName());
				claimDO.setMachineModel(quote.getMachineInfo().getModel());
				claimDO.setCoverageType(quote.getCoverageType());
				if(null != laborMap.get(claim.getId())){
					List<ClaimLaborDO> cpl = new ArrayList<>();
					for(ClaimLabor cp : laborMap.get(claim.getId())){
						ClaimLaborDO laborDO = new ClaimLaborDO();
						laborDO.setId(cp.getId());
						laborDO.setClaimId(cp.getClaimId());
						laborDO.setLaborDescr(cp.getLaborDescr());
						laborDO.setLaborNo(cp.getLaborNo());
						laborDO.setLaborHrs(cp.getLaborHrs());
						laborDO.setRate(cp.getRate());
						laborDO.setAdjLaborHrs(cp.getAdjLaborHrs());
						laborDO.setAdjRate(cp.getAdjRate());
						
						cpl.add(laborDO);
					}
					claimDO.setClaimLaborDO(cpl);
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
						partDO.setUnitPrice(cp.getUnitPrice());
						partDO.setAdjQty(cp.getAdjQty());
						partDO.setAdjUnitPrice(cp.getAdjUnitPrice());
						
						cpl.add(partDO);
					}
					claimDO.setClaimPartDO(cpl);
				}
				
				if(null != fileMap.get(claim.getId())){
					List<ClaimFileDO> cfl = new ArrayList<>();
					for(ClaimFile cf : fileMap.get(claim.getId())){
						ClaimFileDO fileDO = new ClaimFileDO();
						fileDO.setClaimId(cf.getClaimId());
						fileDO.setId(cf.getId());
						fileDO.setFileName(cf.getFileName());
						cfl.add(fileDO);
					}
					claimDO.setClaimFileDO(cfl);
				}
				
				if(null != contractsMap.get(claim.getContractId())){
					ContractDO contractDO = new ContractDO();
					Contracts con = contractsMap.get(claim.getContractId());
					contractDO.setContractId(con.getContractId());
					contractDO.setDeductible(con.getDeductible());
					contractDO.setLol(con.getLol());
					contractDO.setAvailabeLol(con.getAvailabeLol());
					contractDO.setCoverageTermMonths(con.getCoverageTermMonths());
					contractDO.setExpirationUsageHours(con.getExpirationUsageHours());
					contractDO.setCoverageLevelHours(con.getCoverageLevelHours());
					if(con.getExpirationDate() != null){
						contractDO.setExpirationDate(new java.sql.Timestamp(con.getExpirationDate().getTime()));
					}
					contractDO.setMachineModel(quote.getMachineInfo().getModel());
					contractDO.setMachineSerialNo(con.getMachineSerialNo());
					contractDO.setCoverageType(con.getCoverageType());
					claimDO.setContractDO(contractDO);
				}
				
				/*ClaimNote claimNote = claimNotesDAO.findByClaimIdAndDealerId(claim.getId(), loginDealerId);
				
				if(claimNote != null){
					claimDO.setComments(claimNote.getNotes());
				}*/
				
				List<ClaimNote> claimNoteList = claimNotesDAO.findByClaimId(claim.getId());
				List<ClaimNoteDO> claimNoteDOLst = null;
				
				if(claimNoteList !=null)
				{
					claimNoteDOLst = new ArrayList<ClaimNoteDO>();
					for(ClaimNote claimNote : claimNoteList){
						ClaimNoteDO claimNoteDO = convertClaimNoteDO(claimNote);
						claimNoteDOLst.add(claimNoteDO);
						logger.debug("Claim note: "+claimNote.getNotes());
					}
				}
				claimDO.setClaimsNoteList(claimNoteDOLst);
				
				
				if(claim.getCrtaedBy() > 0){
					Account account = accountDAO.findOne(claim.getCrtaedBy());
					if(account != null){
						claimDO.setCreatedUser(account.getFirstName()+" "+account.getLastName());
					}
				}
				
				if(claim.getUpdatedBy() > 0){
					Account account = accountDAO.findOne(claim.getUpdatedBy());
					if(account != null){
						claimDO.setUpdatedUser(account.getFirstName()+" "+account.getLastName());
					}
				}
				
				List<Check> checks = checkDAO.findByClaimId(claim.getId());
				if(checks != null && !checks.isEmpty()){
					CheckDO checkDO = null;
					List<CheckDO> checkDOList = new ArrayList<CheckDO>();
					for(Check check : checks){
						checkDO = new CheckDO();
						checkDO.setId(check.getId());
						checkDO.setAmount(check.getCheckAmount());
						checkDO.setCheckNo(check.getCheckNo());
						checkDO.setReceivedDate(check.getReceivedDate());
						
						checkDOList.add(checkDO);
					}
					claimDO.setCheckDOList(checkDOList);
				}
				
				claimsDOList.add(claimDO);
			}
		}
		return claimsDOList;
	}

	/* (non-Javadoc)
	 * @see com.agg.application.service.ClaimsService#updateStatus(int, byte)
	 */
	@Transactional
	@Override
	public void updateStatus(int id, byte status, final int dealerId, final long accountId, final String extComment) {
		claimsDAO.updateStatus(id, status);
		ClaimNote claimNote = null;
		if(Util.isNotEmptyString(extComment)){
			//ClaimNote claimNote = claimNotesDAO.findByClaimIdAndAccountId(id, accountId);
			claimNote = new ClaimNote();
			logger.debug("External comments: "+extComment);
			claimNote.setClaimId(id);
			claimNote.setAccountId(accountId);
			claimNote.setNotes(extComment);
			claimNote.setNoteType(AggConstants.EXTERNAL_CLAIM_NOTE);
			claimNote.setLastUpdate(new Timestamp(new Date().getTime()));
			claimNotesDAO.save(claimNote);
		}
	}

	@Transactional
	@Override
	public int updateClaimAdjudicate(ClaimsDO claimDO, AccountDO accountDO) {
		logger.debug("Inside updateClaimAdjudicate");
		Claims res = null;
		Claims claim = claimsDAO.findOne(claimDO.getId());
		if(null != claim){
			Date currDate = new Date();
			claim.setTotalAdjustedLaborCost(claimDO.getTotalAdjustedLaborCost());
			claim.setTotalAdjustedPartsCost(claimDO.getTotalAdjustedPartsCost());
			claim.setApprovedOtherCharges1(claimDO.getApprovedOtherCharges1());
			claim.setApprovedOtherCharges2(claimDO.getApprovedOtherCharges2());
			claim.setCheqNo(claimDO.getCheqNo());
			claim.setPaidDate(claimDO.getPaidDate());
			claim.setUpdatedBy(accountDO.getId());
			claim.setLastUpdate(currDate);
			if(claimDO.getCondValue() != null && claimDO.getCondValue().trim().equalsIgnoreCase(AggConstants.CLAIM_STATUS_APPROVED_FOR_PAYMENT_DESC)){
				claim.setcStatus((byte)AggConstants.CLAIM_STATUS_APPROVED_FOR_PAYMENT);
			}else{
				claim.setcStatus((byte)AggConstants.CLAIM_STATUS_CLOSED);
			}
			
			List<CheckDO> checkDOList = claimDO.getCheckDOList();
			double totalCheckAmount = 0.0;
			if(checkDOList != null && !checkDOList.isEmpty()){
				Set<Check> checks = new HashSet<Check>();
				Check check = null;
				for(CheckDO checkDO : checkDOList){
					if(checkDO.getId() > 0){
						check = checkDAO.findOne(checkDO.getId());
					}else{
						check = new Check();
						check.setCreatedBy(accountDO.getUsername());
						check.setCreatedDate(currDate);
					}
					totalCheckAmount += checkDO.getAmount();
					check.setClaim(claim);
					check.setCheckNo(checkDO.getCheckNo());
					check.setReceivedDate(checkDO.getReceivedDate());
					check.setCheckAmount(checkDO.getAmount());
					check.setUpdatedBy(accountDO.getUsername());
					check.setUpdatedDate(currDate);
					
					checks.add(check);
				}
				
				Set<Check> exstChecks = claim.getChecks();
				Set<Check> nonExstChecks = new HashSet<Check>();
				boolean nonExists = false;
				for(Check exstCheck : exstChecks){
					nonExists = true;
					for(Check checkk : checks){
						if(exstCheck.getId() == checkk.getId()){
							nonExists = false;
							break;
						}
					}
					
					if(nonExists){
						nonExstChecks.add(exstCheck);
					}
				}
				
				logger.debug("nonExstChecks size: "+nonExstChecks.size());
				if(nonExstChecks.size() > 0){
					checkDAO.delete(nonExstChecks);
				}
				
				claim.setChecks(checks);
			}
			
			res = claimsDAO.save(claim);
			
			if(null != claimDO.getClaimFileDO() && !claimDO.getClaimFileDO().isEmpty() && null != res ){
				List<ClaimFile> claimFiles = new ArrayList<>();
				for(ClaimFileDO fileDO : claimDO.getClaimFileDO()){
					ClaimFile cFile = new ClaimFile();
					cFile.setFileName(fileDO.getFileName());
					cFile.setClaimId(res.getId());
					claimFiles.add(cFile);
				}
				
				claimFileDAO.save(claimFiles);
			}
			
			Contracts contracts = contractsDAO.findByContractId(claim.getContractId());
			if(contracts != null){
				if(claimDO.getCondValue() == null){
					//contracts.setAvailabeLol(contracts.getAvailabeLol()-claimDO.getTra());
					contracts.setAvailabeLol(contracts.getAvailabeLol()-totalCheckAmount);
					contracts.setLastUpdatedDate(new Date());
					contractsDAO.save(contracts);
				}
			}
			ClaimNote claimNote = null;
			logger.debug("claimDO.getComments() "+claimDO.getComments());
			if(Util.isNotEmptyString(claimDO.getComments())){
				/*ClaimNote claimNote = claimNotesDAO.findByClaimIdAndAccountId(claimDO.getId(), new Long(accountDO.getDealerId()).intValue());
				if(claimNote == null){
					claimNote = new ClaimNote();
				}
				claimNote.setClaimId(claimDO.getId());
				claimNote.setAccountId(new Long(accountDO.getDealerId()).intValue());
				logger.debug("Adjudicate External comments: "+claimDO.getComments());
				claimNote.setNotes(claimDO.getComments());
				claimNote.setNoteType(AggConstants.EXTERNAL_CLAIM_NOTE);
				claimNote.setLastUpdate(new Timestamp(new Date().getTime()));
				claimNotesDAO.save(claimNote);*/
				
				claimNote = new ClaimNote();
				logger.debug("External comments: "+claimDO.getComments());
				claimNote.setClaimId(claimDO.getId());
				claimNote.setAccountId(accountDO.getId());
				claimNote.setNotes(claimDO.getComments());
				claimNote.setNoteType(AggConstants.EXTERNAL_CLAIM_NOTE);
				claimNote.setLastUpdate(new Timestamp(new Date().getTime()));
				claimNotesDAO.save(claimNote);
			}
			
			if(null != claimDO.getClaimPartDO() && !claimDO.getClaimPartDO().isEmpty()){
				List<ClaimPart> claimPartList = new ArrayList<>();
				ClaimPart claimPart = null;
				for(ClaimPartDO claimPartDO : claimDO.getClaimPartDO()){
					claimPart = new ClaimPart();
					claimPart.setId(claimPartDO.getId());
					claimPart.setClaimId(claim.getId());
					claimPart.setPartNo(claimPartDO.getPartNo());
					claimPart.setPartDescr(claimPartDO.getPartDescr());
					claimPart.setQty(claimPartDO.getQty());
					claimPart.setUnitPrice(claimPartDO.getUnitPrice());
					claimPart.setAdjQty(claimPartDO.getAdjQty());
					claimPart.setAdjUnitPrice(claimPartDO.getAdjUnitPrice());
					
					claimPartList.add(claimPart);
				}
				int records = claimPartDAO.deleteClaimPartsByClaimId(claim.getId());
				logger.info("b4 insert ClaimParts, "+records+" records deleted for claim: "+claim.getClaimId());
				claimPartDAO.save(claimPartList);
			}
			
			if(null != claimDO.getClaimLaborDO() && !claimDO.getClaimLaborDO().isEmpty()){
				List<ClaimLabor> claimLaborList = new ArrayList<>();
				ClaimLabor claimLabor = null;
				for(ClaimLaborDO laborDO : claimDO.getClaimLaborDO()){
					claimLabor = new ClaimLabor();
					claimLabor.setId(laborDO.getId());
					claimLabor.setLaborNo(laborDO.getLaborNo());
					claimLabor.setLaborDescr(laborDO.getLaborDescr());
					claimLabor.setLaborHrs(laborDO.getLaborHrs());
					claimLabor.setRate(laborDO.getRate());
					claimLabor.setClaimId(claim.getId());
					claimLabor.setAdjLaborHrs(laborDO.getAdjLaborHrs());
					claimLabor.setAdjRate(laborDO.getAdjRate());
					
					claimLaborList.add(claimLabor);
				}
				int records = claimLaborDAO.deleteClaimLaborsByClaimId(claim.getId());
				logger.info("b4 insert ClaimLabors, "+records+" records deleted for claim: "+claim.getClaimId());
				claimLaborDAO.save(claimLaborList);
			}
			
			
		}
		return (null == res) ? -1 : res.getId();
	}

	/* (non-Javadoc)
	 * @see com.agg.application.service.ClaimsService#getContractsCount(java.lang.String)
	 */
	@Override
	public int getContractsCount(String contractId) {
		return claimsDAO.getContractsCount(contractId);
	}

	@Override
	public ClaimReportDO getClaimReportDetails(String claimId, AccountDO accountDO) {
		logger.debug("Inside getClaim method with claimId: "+claimId);
		ClaimReportDO claimReportDO = null;
		SimpleDateFormat reportDateFormat = new SimpleDateFormat("MM/dd/yyyy");
		SimpleDateFormat noteDateFormat = new SimpleDateFormat("dd-MMM-yyyy");
		Locale locale = new Locale("en", "US");
		NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(locale);
		Claims claim = claimsDAO.findClaimsByClaimId(claimId);
		if(claim != null){
			claimReportDO = new ClaimReportDO();
			
			Dealer dealer = dealerDAO.findOne(new Long(claim.getDealerId()));
			if(dealer != null){
				claimReportDO.setAddress(dealer.getAddress());
				claimReportDO.setCity(dealer.getCity());
				claimReportDO.setEmail(dealer.getInvoiceEmail());
				claimReportDO.setDealerName(dealer.getName());
				claimReportDO.setPhone(dealer.getPhone());
				claimReportDO.setState(dealer.getState());
				claimReportDO.setZip(dealer.getZip());
			}
			
			Account account = accountDAO.findOne(claim.getCrtaedBy());
			if(account != null){
				claimReportDO.setContact(account.getFirstName()+" "+account.getLastName());
			}
			
			claimReportDO.setBreakdownHrs(claim.getHoursBreakDown());
			claimReportDO.setCauseOfFailure(claim.getCauseFail());
			claimReportDO.setClaimId(claim.getClaimId());
			claimReportDO.setClaimStatus(Util.getClaimStatusValue(claim.getcStatus()));
			claimReportDO.setCorrectiveAction(claim.getCorrectiveAction());
			claimReportDO.setCustomerComplaint(claim.getCustComplaint());
			claimReportDO.setFailureDate(reportDateFormat.format(claim.getFailDate()));
			//claimReportDO.setLaborCost(currencyFormat.format(claim.getTotalAdjustedLaborCost()));
			claimReportDO.setWorkOrderNumber(claim.getWorkOrder());
			claimReportDO.setReqOtherCharges1(currencyFormat.format(claim.getRequestedOtherCharges1()));
			claimReportDO.setReqOtherCharges2(currencyFormat.format(claim.getRequestedOtherCharges2()));
			claimReportDO.setAdjOtherCharges1(currencyFormat.format(claim.getApprovedOtherCharges1()));
			claimReportDO.setAdjOtherCharges2(currencyFormat.format(claim.getApprovedOtherCharges2()));
			claimReportDO.setChequeNo(claim.getCheqNo());
			claimReportDO.setPaidDate((claim.getPaidDate() != null)?reportDateFormat.format(claim.getPaidDate()):"");
			claimReportDO.setTotalAdjLaborCost(currencyFormat.format(claim.getTotalAdjustedLaborCost()));
			claimReportDO.setTotalAdjPartsCost(currencyFormat.format(claim.getTotalAdjustedPartsCost()));
			
			
			
			
			Contracts contract = contractsDAO.findByContractId(claim.getContractId());
			Quote quote = quoteDAO.findOne((int)contract.getQuoteId());
			claimReportDO.setManufacturer(quote.getManufacturer().getManfName());
			claimReportDO.setMachineModel(quote.getMachineInfo().getModel());
			claimReportDO.setContractId(claim.getContractId());
			claimReportDO.setContractExpirationDate((contract.getExpirationDate() != null)?reportDateFormat.format(contract.getExpirationDate()):"");
			claimReportDO.setUsageHoursCovered(contract.getExpirationUsageHours());
			claimReportDO.setLol(currencyFormat.format(contract.getLol()));
			claimReportDO.setAvailableLol(currencyFormat.format(contract.getAvailabeLol()));
			claimReportDO.setDeductibleAmount(currencyFormat.format(contract.getDeductible()));
			String coverageType = quote.getCoverageType();
			if(coverageType != null && !coverageType.isEmpty()){
				if(coverageType.equalsIgnoreCase("PT")){
					claimReportDO.setCoverageType("Powertrain");
				}else if(coverageType.equalsIgnoreCase("PH")){
					claimReportDO.setCoverageType("Powertrain + Hydraulic");
				}else if(coverageType.equalsIgnoreCase("PL")){
					claimReportDO.setCoverageType("Powertrain + Hydraulic + Platform");
				}
			}
			
			//claimReportDO.setOtherCharges1(currencyFormat.format(claim.getApprovedOtherCharges1()));
			//claimReportDO.setOtherCharges2(currencyFormat.format(claim.getApprovedOtherCharges2()));
			//claimReportDO.setPartsTotal(currencyFormat.format(claim.getTotalAdjustedPartsCost()));
			//claimReportDO.setQuoteId(quote.getId().getQuoteId());
			claimReportDO.setReportedOn(reportDateFormat.format(claim.getReportDate()));
			claimReportDO.setSerialNumber(claim.getSerial());
			double totalAdjClaimCost = (claim.getApprovedOtherCharges1()+claim.getApprovedOtherCharges2()+claim.getTotalAdjustedPartsCost()+claim.getTotalAdjustedLaborCost());
			claimReportDO.setTotalAdjClaimCost(currencyFormat.format(totalAdjClaimCost));
			
			List<Integer> claimIdList = new ArrayList<Integer>();
			claimIdList.add(claim.getId());
			List<ClaimLabor> claimsLaborList = claimLaborDAO.findAllByClaimID(claimIdList);
			List<ClaimPart> claimPartList = claimPartDAO.findAllByClaimID(claimIdList);
			List<ClaimFile> claimFileList = claimFileDAO.findAllByClaimID(claimIdList);
			List<ClaimNote> claimNoteList = claimNotesDAO.findByClaimId(claim.getId());
			
			double totalLaborCost = 0;
			if(null != claimsLaborList && !claimsLaborList.isEmpty()){
				List<ClaimLaborDO> claimLaborDOList = new ArrayList<ClaimLaborDO>();
				ClaimLaborDO laborDO = null;
				for(ClaimLabor claimLabor : claimsLaborList){
					laborDO = new ClaimLaborDO();
					laborDO.setId(claimLabor.getId());
					laborDO.setClaimId(claimLabor.getClaimId());
					laborDO.setLaborDescr(claimLabor.getLaborDescr());
					laborDO.setLaborNo(claimLabor.getLaborNo());
					laborDO.setLaborHrs(claimLabor.getAdjLaborHrs());
					laborDO.setRate(claimLabor.getAdjRate());
					laborDO.setLaborTotal(currencyFormat.format(claimLabor.getAdjLaborHrs() * claimLabor.getAdjRate()));
					totalLaborCost += (claimLabor.getAdjLaborHrs() * claimLabor.getAdjRate());
					claimLaborDOList.add(laborDO);
				}
				claimReportDO.setClaimLaborDOList(claimLaborDOList);
				claimReportDO.setTotalReqLaborCost(currencyFormat.format(totalLaborCost));
			}
			
			double totalPartCost = 0;
			if(null != claimPartList && !claimPartList.isEmpty()){
				List<ClaimPartDO> claimPartDOList = new ArrayList<ClaimPartDO>();
				ClaimPartDO partDO = null;
				for(ClaimPart claimPart : claimPartList){
					partDO = new ClaimPartDO();
					partDO.setId(claimPart.getId());
					partDO.setClaimId(claimPart.getClaimId());
					partDO.setPartDescr(claimPart.getPartDescr());
					partDO.setPartNo(claimPart.getPartNo());
					partDO.setQty(claimPart.getAdjQty());
					partDO.setUnitPrice(claimPart.getAdjUnitPrice());
					partDO.setPartTotal(currencyFormat.format((claimPart.getAdjQty() * claimPart.getAdjUnitPrice())));
					totalPartCost += (claimPart.getAdjQty() * claimPart.getAdjUnitPrice());
					claimPartDOList.add(partDO);
				}
				claimReportDO.setClaimPartDOList(claimPartDOList);
				claimReportDO.setTotalReqPartsCost(currencyFormat.format(totalPartCost));
			}
			
			claimReportDO.setTotalReqClaimCost(currencyFormat.format((totalLaborCost+totalPartCost+claim.getRequestedOtherCharges1()+claim.getRequestedOtherCharges2())));
			
			if(null != claimFileList && !claimFileList.isEmpty()){
				List<ClaimFileDO> claimFileDOList = new ArrayList<ClaimFileDO>();
				ClaimFileDO fileDO = null;
				for(ClaimFile claimFile : claimFileList){
					fileDO = new ClaimFileDO();
					fileDO.setClaimId(claimFile.getClaimId());
					fileDO.setId(claimFile.getId());
					fileDO.setFileName(claimFile.getFileName());
					claimFileDOList.add(fileDO);
				}
				claimReportDO.setClaimFileDOList(claimFileDOList);
			}
			
			
			if(null != claimNoteList && !claimNoteList.isEmpty())
			{
				List<ClaimNoteDO> claimNoteDOList = new ArrayList<ClaimNoteDO>();
				ClaimNoteDO claimNoteDO = null;
				String notes = null;
				for(ClaimNote claimNote : claimNoteList){
					claimNoteDO = convertClaimNoteDO(claimNote);
					//logger.debug("UpdatedBy: "+claimNoteDO.getUpdatedBy());
					//logger.debug("Notes: "+claimNoteDO.getNotes());
					notes = (claimNoteDO.getLastUpdate() != null)?noteDateFormat.format(claimNoteDO.getLastUpdate()):"";
					notes += " : "+claimNoteDO.getUpdatedBy()+" : "+claimNoteDO.getNotes();
					//logger.debug("notes: "+notes);
					claimNoteDO.setNotes(notes);
					claimNoteDOList.add(claimNoteDO);
				}
				claimReportDO.setClaimNoteDOList(claimNoteDOList);
			}
			
			double contractDeductible = totalAdjClaimCost - contract.getDeductible();
			if(contractDeductible < 0){
				contractDeductible = 0;
			}
			double deductibleTRA = contract.getAvailabeLol() - contractDeductible;
			//total Re-imbursed amount
			double tra = 0;
			if(deductibleTRA < 0){
				tra = contract.getAvailabeLol();
			}else{
				tra = contractDeductible;
			}
			
			double customerOwes = 0;
			if(tra == totalAdjClaimCost){
				customerOwes = contract.getDeductible();
			}else{
				customerOwes = totalAdjClaimCost - tra;
			}
			
			if(tra >= 0){
				claimReportDO.setTotalReimbursedAmount(currencyFormat.format(tra));
			}
			if(customerOwes >= 0){
				claimReportDO.setTotalAmtOwnedByCustomer(currencyFormat.format(customerOwes));
			}
			
		}
		
		return claimReportDO;
	}

	public ClaimNoteDO convertClaimNoteDO(ClaimNote claimNote)
	{
		ClaimNoteDO claimNoteDO = new ClaimNoteDO();
		claimNoteDO.setAccountId(claimNote.getAccountId());
		claimNoteDO.setClaimId(claimNote.getClaimId());
		claimNoteDO.setLastUpdate(claimNote.getLastUpdate());
		claimNoteDO.setNotes(claimNote.getNotes());
		claimNoteDO.setId(claimNote.getId());
		claimNoteDO.setNoteType(claimNote.getNoteType());
		
		Account account = accountDAO.findOne(claimNote.getAccountId());
		
		claimNoteDO.setUpdatedBy(account.getFirstName()+" "+account.getLastName());
		
		return claimNoteDO;
		
	}

	@Override
	public List<ClaimNoteDO> getClaimNotes(int claimId) {
		logger.info("Inside getClaimNotes method with claimId: "+claimId);
		List<ClaimNote> claimNoteList = claimNotesDAO.findByClaimId(claimId);
		List<ClaimNoteDO> claimNoteDOLst = null;
		if(claimNoteList !=null){
			claimNoteDOLst = new ArrayList<ClaimNoteDO>();
			for(ClaimNote claimNote : claimNoteList){
				ClaimNoteDO claimNoteDO = convertClaimNoteDO(claimNote);
				claimNoteDOLst.add(claimNoteDO);
			}
		}
		
		return claimNoteDOLst;
	}

	@Override
	public List<ClaimsDO> getClaimsByStatus(AccountDO accountDO, byte claimStatusApprovedForPayment) {
		List<ClaimsDO> claimsDOList = null;
		if(accountDO.getRoleDO().getAccountType().equalsIgnoreCase(AggConstants.ACCOUNT_TYPE_ADMIN)){
			claimsDOList = claimsDAO.findApprovedClaims(claimStatusApprovedForPayment);
		}
		return claimsDOList;
	}
	
	
}
