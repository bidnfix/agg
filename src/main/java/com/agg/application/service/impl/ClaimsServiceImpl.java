package com.agg.application.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.agg.application.dao.ClaimFileDAO;
import com.agg.application.dao.ClaimLaborDAO;
import com.agg.application.dao.ClaimNotesDAO;
import com.agg.application.dao.ClaimPartDAO;
import com.agg.application.dao.ClaimsDAO;
import com.agg.application.dao.ContractsDAO;
import com.agg.application.dao.DealerDAO;
import com.agg.application.dao.MachineInfoDAO;
import com.agg.application.dao.ManufacturerDAO;
import com.agg.application.dao.QuoteDAO;
import com.agg.application.entity.ClaimFile;
import com.agg.application.entity.ClaimLabor;
import com.agg.application.entity.ClaimNote;
import com.agg.application.entity.ClaimPart;
import com.agg.application.entity.Claims;
import com.agg.application.entity.Contracts;
import com.agg.application.entity.Manufacturer;
import com.agg.application.entity.Quote;
import com.agg.application.model.AccountDO;
import com.agg.application.model.ClaimFileDO;
import com.agg.application.model.ClaimLaborDO;
import com.agg.application.model.ClaimPartDO;
import com.agg.application.model.ClaimsDO;
import com.agg.application.model.ContractDO;
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
	
	
	public List<ClaimsDO> getClaimsInfo(AccountDO accountDO){
		logger.debug("Inside getClaimsInfo()");
		List<ClaimsDO> claimsDOList = null;
		if(accountDO.getRoleDO().getAccountType().equalsIgnoreCase(AggConstants.ACCOUNT_TYPE_ADMIN)){
			claimsDOList = claimsDAO.findClaimsInfo();
		}else{
			claimsDOList = claimsDAO.findClaimsInfo(new Long(accountDO.getDealerId()).intValue());
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
	public Claims saveClaim(ClaimsDO claimsDO)
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
		claim.setLastUpdate(new Date()); 
		
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
		
		if(null != claimsDO.getClaimLaborDO() && !claimsDO.getClaimLaborDO().isEmpty()){
			List<ClaimLabor> claimLaborList = new ArrayList<>();
			for(ClaimLaborDO laborDO : claimsDO.getClaimLaborDO()){
				ClaimLabor claimLabor = new ClaimLabor();
				claimLabor.setLaborNo(laborDO.getLaborNo());
				claimLabor.setLaborDescr(laborDO.getLaborDescr());
				claimLabor.setLaborHrs(laborDO.getLaborHrs());
				claimLabor.setRate(laborDO.getRate());
				claimLabor.setClaimId(newClaim.getId());
				claimLaborList.add(claimLabor);
			}
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
		
		return newClaim;
	}

	/* (non-Javadoc)
	 * @see com.agg.application.service.ClaimsService#getClaimsByCStatus(byte)
	 */
	@Override
	public List<ClaimsDO> getClaimsByCStatus(byte cStatus, boolean contractInfo) {
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
		return ConvertClaimToClaimsDO(claimsList, claimsLaborList, claimPartList, contractsList, claimFileList);
	}
	
	@Override
	public ClaimsDO getClaim(int claimId){
		Claims claim = claimsDAO.findOne(claimId);
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
		}
		List<ClaimsDO> list = ConvertClaimToClaimsDO(claimsList, claimsLaborList, claimPartList, contractsList, claimFileList);
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
		return ConvertClaimToClaimsDO(claimsList, claimsLaborList, claimPartList, contractsList, claimFileList);
	}
	
	private List<ClaimsDO> ConvertClaimToClaimsDO(List<Claims> claimsList, List<ClaimLabor> claimsLaborList, List<ClaimPart> claimPartList, List<Contracts> contractsList, List<ClaimFile> claimFileList){
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
				Contracts contract = contractsDAO.findByContractId(claim.getContractId());
				Quote quote = quoteDAO.findOne((int)contract.getQuoteId());
				claimDO.setManufacturer(quote.getManfName());
				claimDO.setMachineModel(quote.getMachineModel());
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
						partDO.setUnitPrice(cp.getQty());
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
					claimDO.setContractDO(contractDO);
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
	public void updateStatus(int id, byte status, final int dealerId, final String extComment) {
		claimsDAO.updateStatus(id, status);
		if(Util.isNotEmptyString(extComment)){
			ClaimNote claimNote = new ClaimNote();
			claimNote.setClaimId(id);
			claimNote.setDealerId(dealerId);
			claimNote.setNotes(extComment);
			claimNote.setLastUpdate(new Timestamp(new Date().getTime()));
			claimNotesDAO.save(claimNote);
		}
	}

	@Transactional
	@Override
	public int updateClaimAdjudicate(ClaimsDO claimDO) {
		Claims res = null;
		Claims claim = claimsDAO.findOne(claimDO.getId());
		if(null != claim){
			claim.setTotalAdjustedLaborCost(claimDO.getTotalAdjustedLaborCost());
			claim.setTotalAdjustedPartsCost(claimDO.getTotalAdjustedPartsCost());
			claim.setApprovedOtherCharges1(claimDO.getApprovedOtherCharges1());
			claim.setApprovedOtherCharges2(claimDO.getApprovedOtherCharges2());
			claim.setcStatus((byte)AggConstants.CLAIM_STATUS_CLOSED);
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

	
	
	
}
