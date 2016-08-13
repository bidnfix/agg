package com.agg.application.controller;

import java.util.List;

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

import com.agg.application.model.ClaimsDO;
import com.agg.application.model.DealerDO;
import com.agg.application.model.QuoteDO;
import com.agg.application.model.Result;
import com.agg.application.service.ClaimsService;
import com.agg.application.service.ContractsService;
import com.agg.application.service.DealerService;
import com.agg.application.service.QuoteService;
import com.agg.application.utils.Util;
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
		List<QuoteDO> quoteInfoList = claimsService.getClaimInfoBySerialNumber(id);
		logger.info("quoteInfoList size: "+quoteInfoList.size());
		model.put("quoteInfoList", quoteInfoList);
		return new Result("success", null, model);	
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
		ClaimsDO claimsDO = new ClaimsDO();
		claimsDO.setCauseFail(Util.setDefaultStringValue(claimsVO.getCauseFail()));
		claimsDO.setCorrectiveAction(Util.setDefaultStringValue(claimsVO.getCorrectiveAction()));
		claimsDO.setCustComplaint(Util.setDefaultStringValue(claimsVO.getCustComplaint()));
		
		DealerDO dealerDO = contractsService.getDealer(claimsVO.getContractId());
		if(null != dealerDO){
			claimsDO.setDealerAddress1(Util.setDefaultStringValue(dealerDO.getAddress1()));
			claimsDO.setDealerAddress2(Util.setDefaultStringValue(dealerDO.getAddress2()));
			claimsDO.setDealerCity(Util.setDefaultStringValue(dealerDO.getCity()));
			claimsDO.setDealerEmail(Util.setDefaultStringValue(dealerDO.getInvoiceEmail()));
			claimsDO.setDealerId((int)dealerDO.getId());
			claimsDO.setDealerName(Util.setDefaultStringValue(dealerDO.getName()));
			claimsDO.setDealerPhone(Util.setDefaultStringValue(dealerDO.getPhone()));
			claimsDO.setDealerState(Util.setDefaultStringValue(dealerDO.getState()));
			claimsDO.setDealerZip(Util.setDefaultStringValue(dealerDO.getZip()));
			claimsDO.setDealerContact(Util.setDefaultStringValue(null));
		}
		
		claimsDO.setFailDate(claimsVO.getFailDate());
		claimsDO.setHourlyRate(claimsVO.getHourlyRate());
		claimsDO.setHoursBreakDown(claimsVO.getHoursBreakDown());
		claimsDO.setIsArchived(claimsVO.getIsArchived());
		claimsDO.setLabourHours(claimsVO.getLabourHours());
		claimsDO.setManf(Util.setDefaultStringValue(claimsVO.getManf()));
		claimsDO.setModel(Util.setDefaultStringValue(claimsVO.getModel()));
		claimsDO.setNewClaimId(claimsVO.getClaimId());
		claimsDO.setOtherCharges1(claimsVO.getOtherCharges1());
		claimsDO.setOtherCharges2(claimsVO.getOtherCharges2());
		claimsDO.setOtherManf(Util.setDefaultStringValue(claimsVO.getOtherManf()));
		claimsDO.setOtherModel(Util.setDefaultStringValue(claimsVO.getOtherModel()));
		claimsDO.setPartsTotal(claimsVO.getPartsTotal());
		claimsDO.setPreAuth(Util.setDefaultStringValue(claimsVO.getPreAuth()));
		claimsDO.setContractId(claimsVO.getContractId());
		claimsDO.setReportDate(claimsVO.getReportDate());
		claimsDO.setSerial(claimsVO.getSerial());
		claimsDO.setWorkOrder(Util.setDefaultStringValue(claimsVO.getWorkOrder()));
		claimsDO.setContractId(Util.setDefaultStringValue(null));
		claimsDO.setCLevel(Util.setDefaultStringValue(null));
		
		Long id = claimsService.saveClaim(claimsDO);
		return new Result("success", null, id);
	}
}
