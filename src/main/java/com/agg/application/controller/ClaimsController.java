package com.agg.application.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.agg.application.model.AccountDO;
import com.agg.application.model.ClaimLaborDO;
import com.agg.application.model.ClaimMailDO;
import com.agg.application.model.ClaimPartDO;
import com.agg.application.model.ClaimsDO;
import com.agg.application.model.ContractDO;
import com.agg.application.model.DealerDO;
import com.agg.application.model.QuoteDO;
import com.agg.application.model.Result;
import com.agg.application.service.ClaimsService;
import com.agg.application.service.ContractsService;
import com.agg.application.service.DealerService;
import com.agg.application.service.QuoteService;
import com.agg.application.service.UserService;
import com.agg.application.utils.ClaimMail;
import com.agg.application.utils.Util;
import com.agg.application.vo.ClaimPartVO;
import com.agg.application.vo.ClaimPreAuthVO;
import com.agg.application.vo.ClaimsVO;

@RestController
@RequestMapping("/agg")
public class ClaimsController extends BaseController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ClaimsService claimsService;
	
	@Autowired
	private DealerService dealerService;
	
	@Autowired
	private QuoteService quoteService;
	
	@Autowired
	private ContractsService contractsService;
	
	@Autowired
	private UserService userService;

	/*@RequestMapping(value = "/claimsInfo", method = RequestMethod.GET, consumes = MediaType.ALL_VALUE)
	public @ResponseBody Result claimsInfo(ModelMap model, HttpServletResponse response) {
		logger.info("Inside claimsInfo()");
		List<QuoteDO> quoteInfoList = claimsService.getClaimsInfo();
		logger.info("quoteInfoList size: "+quoteInfoList.size());
		model.put("quoteInfoList", quoteInfoList);
		return new Result("success", null, model);	
	}*/
	
	@RequestMapping(value = "/editClaim", method = RequestMethod.GET, consumes = MediaType.ALL_VALUE)
	public @ResponseBody Result machineModel(ModelMap model, HttpServletResponse response/*, @PathVariable String claimId*/) {
		String claimId = "GoR1858";
		logger.info("Inside machineModel() with typeId: "+claimId);
		if(claimId != null && !claimId.isEmpty()){
			QuoteDO quoteDO = claimsService.getClaimInfo(claimId);
			model.put("quoteDO", quoteDO);
		}
		return new Result("success", null, model);	
	}
	
	@RequestMapping(value = "/searchClaim/{id}", method = RequestMethod.GET, consumes = MediaType.ALL_VALUE)
	public @ResponseBody Result searchClaim(@PathVariable String id, HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		logger.info("Inside claimsInfo()");
		if(!sessionExists(request)){
			return new Result("failure", "Session Expired", null);
		}else{
			List<QuoteDO> quoteInfoList = claimsService.getClaimInfoBySerialNumber(id);
			logger.info("quoteInfoList size: "+quoteInfoList.size());
			model.put("quoteInfoList", quoteInfoList);
			return new Result("success", null, model);
		}
	}
	
	/*@RequestMapping(value = "/searchClaim/{id}", method = RequestMethod.GET, consumes = MediaType.ALL_VALUE)
	public @ResponseBody Result searchClaim(@PathVariable String id, HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		logger.info("Inside claimsInfo()");
		List<QuoteDO> quoteInfoList = claimsService.getClaimInfoBySerialNumber(id);
		logger.info("quoteInfoList size: "+quoteInfoList.size());
		model.put("quoteInfoList", quoteInfoList);
		return new Result("success", null, model);	
	}*/
	
	@RequestMapping(value = "/saveClaim", method = RequestMethod.POST)
	public @ResponseBody Result saveClaim(@RequestBody ClaimsVO claimsVO, BindingResult result,
			HttpServletRequest request, HttpServletResponse response) {
		logger.debug("In saveClaim ");
		if(!sessionExists(request)){
			return new Result("failure", "Session Expired", null);
		}else{
			ClaimsDO claimsDO = new ClaimsDO();
			claimsDO.setClaimId(claimsVO.getClaimId());
			claimsDO.setContractId(claimsVO.getContractId());
			DealerDO dealerDO = null;
			if(null != claimsVO.getContractId()){
				dealerDO= contractsService.getDealer(claimsVO.getContractId());
				if(null != dealerDO){
					claimsDO.setDealerId((int)dealerDO.getId());
				}
			}
			
			claimsDO.setSerial(claimsVO.getSerial());
			claimsDO.setFailDate(claimsVO.getFailDate());
			claimsDO.setReportDate(claimsVO.getReportDate());
			claimsDO.setWorkOrder(Util.setDefaultStringValue(claimsVO.getWorkOrder()));
			claimsDO.setHoursBreakDown(claimsVO.getHoursBreakDown());
			claimsDO.setPreauthApprovedAmt(claimsVO.getPreauthApprovedAmt());
			claimsDO.setCustComplaint(Util.setDefaultStringValue(claimsVO.getCustComplaint()));
			claimsDO.setCauseFail(Util.setDefaultStringValue(claimsVO.getCauseFail()));
			claimsDO.setCorrectiveAction(Util.setDefaultStringValue(claimsVO.getCorrectiveAction()));
			claimsDO.setIsArchived(claimsVO.getIsArchived());
			claimsDO.setcStatus(claimsVO.getcStatusValue());
			claimsDO.setRequestedOtherCharges1(claimsVO.getRequestedOtherCharges1());
			claimsDO.setRequestedOtherCharges2(claimsVO.getRequestedOtherCharges2());
			claimsDO.setTotalAdjustedPartsCost(claimsVO.getTotalAdjustedPartsCost());
			claimsDO.setTotalAdjustedLaborCost(claimsVO.getTotalAdjustedLaborCost());
			claimsDO.setApprovedOtherCharges1(claimsVO.getApprovedOtherCharges1());
			claimsDO.setApprovedOtherCharges2(claimsVO.getApprovedOtherCharges2());
			
			if(null != claimsVO.getClaimPartVOList() && !claimsVO.getClaimPartVOList().isEmpty()){
				List<ClaimPartDO> partDO = new ArrayList<>();
				for(ClaimPartVO partVO : claimsVO.getClaimPartVOList()){
					ClaimPartDO claimPartDO = new ClaimPartDO();
					claimPartDO.setPartNo(partVO.getPartNo());
					claimPartDO.setPartDescr(partVO.getPartDescr());
					claimPartDO.setQty(partVO.getQty());
					claimPartDO.setUnitPrice(partVO.getUnitPrice());
					partDO.add(claimPartDO);
				}
				claimsDO.setClaimPartDO(partDO);
			}
			
			ClaimLaborDO claimLaborDO = new ClaimLaborDO();
			claimLaborDO.setLaborNo(claimsVO.getLaborNo());
			claimLaborDO.setLaborDescr(claimsVO.getLaborDescr());
			claimLaborDO.setLaborHrs(claimsVO.getLaborHrs());
			claimLaborDO.setRate(claimsVO.getLaborHourlyRate());
			claimsDO.setClaimLaborDO(claimLaborDO);
			Long id = claimsService.saveClaim(claimsDO);
			if(id != 0 ){
				ClaimMail mail = new ClaimMail();
				mail.setUserService(userService);
				ClaimMailDO claimMailDO = new ClaimMailDO();
				claimMailDO.setClaimID(String.valueOf(id));
				claimMailDO.setDealerName(dealerDO.getFirstName());
				claimMailDO.setContractId(claimsDO.getContractId());
				claimMailDO.setTotalLaborCost(String.valueOf(claimLaborDO.getLaborHrs() * claimLaborDO.getRate()));
				claimMailDO.setTotalPartsCost(claimsDO.getClaimPartDO());
				claimMailDO.setTotalOtherCosts(String.valueOf(claimsDO.getRequestedOtherCharges1() + claimsDO.getRequestedOtherCharges2()));
				claimMailDO.setDeductible(String.valueOf(claimsVO.getDeductible()));
				claimMailDO.setLol(String.valueOf(claimsVO.getLol()));
				claimMailDO.setAvailableLol(String.valueOf(claimsVO.getAvailableLol()));
				mail.setClaimMailDO(claimMailDO);
				new Thread(mail).start();
			}
			return new Result("success", null, id);
		}
	}
	
	@RequestMapping(value = "/preAuthClaimReq", method = RequestMethod.GET, consumes = MediaType.ALL_VALUE)
	public @ResponseBody Result getPreAuthClaimRequest(HttpServletRequest request, HttpServletResponse response) {
		logger.info("Inside getPreAuthClaimRequest()");
		if(!sessionExists(request)){
			return new Result("failure", "Session Expired", null);
		}else{
			Map<String, Object> map = new HashMap<>();
			List<ClaimsDO> cliamsList = null;
			AccountDO account = getAccountDetails(request);
			if("admin".equals(account.getRoleName())){
				cliamsList = claimsService.getClaimsByCStatus(Util.getClaimStatusCode("pre_authorized_requested"));
			}else{
				cliamsList = claimsService.getClaimsByCStatus(Util.getClaimStatusCode("pre_authorized_requested"), (int)account.getDealerId());
			}
			
			logger.info("preAuthClaims size: "+cliamsList.size());
			map.put("preAuthClaimList", cliamsList);
			return new Result("success", null, map);
		}
	}
	
	@RequestMapping(value = "/preAuthClaimReq", method = RequestMethod.PUT, consumes = MediaType.ALL_VALUE)
	public @ResponseBody Result UpdatePreAuthClaimRequestUpdate(@RequestBody ClaimPreAuthVO claimPreAuthVO, HttpServletRequest request, HttpServletResponse response) {
		logger.info("Inside UpdatePreAuthClaimRequestUpdate()");
		if(!sessionExists(request)){
			return new Result("failure", "Session Expired", null);
		}else{
			claimsService.updateStatus(claimPreAuthVO.getId(), Util.getClaimStatusCode(claimPreAuthVO.getcStatus()));
			return new Result("success", null, "status updated");
		}
	}
	
	@RequestMapping(value = "/getClaimsInfo", method = RequestMethod.GET, consumes = MediaType.ALL_VALUE)
	public @ResponseBody Result getClaimsInfo(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) {
		logger.info("Inside getClaimsInfo()");
		if(!sessionExists(request)){
			return new Result("failure", "Session Expired", null);
		}else{
			List<QuoteDO> quoteInfoList = claimsService.getClaimsInfo();
			logger.info("quoteInfoList size: "+quoteInfoList.size());
			model.put("quoteDOList", quoteInfoList);
			return new Result("success", null, model);
		}
	}
	
}


