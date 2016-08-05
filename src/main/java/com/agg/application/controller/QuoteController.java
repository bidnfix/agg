package com.agg.application.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.agg.application.model.AccountDO;
import com.agg.application.model.DealerDO;
import com.agg.application.model.ManufacturerDO;
import com.agg.application.model.PricingDO;
import com.agg.application.model.QuoteDO;
import com.agg.application.model.Result;
import com.agg.application.service.DealerService;
import com.agg.application.service.MachineService;
import com.agg.application.service.QuoteService;
import com.agg.application.utils.AggConstants;

@RestController
@RequestMapping("/agg")
public class QuoteController extends BaseController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private DealerService dealerService;
	
	@Autowired
	private MachineService machineService;
	
	@Autowired
	private QuoteService quoteService;

	@RequestMapping(value = "/quoteInfo", method = RequestMethod.GET)
	public @ResponseBody Result getQuoteInfo(HttpServletRequest request, HttpServletResponse response, Model model) {
		logger.debug("In getQuoteInfo");
		Result opResult = null;
		if (!sessionExists(request)){
			opResult = new Result("failure", "Invalid Login", null);
		}else{
			List<DealerDO> dealerDOList = dealerService.getDealers();
			List<ManufacturerDO> manufacturerDOList = machineService.getManufacturerDetails();
			model.addAttribute("dealerDOList", dealerDOList);
			model.addAttribute("manufacturerDOList", manufacturerDOList);
			model.addAttribute("useOfEquipmentDOList", quoteService.getUseOfEquipmentDetails());
			
			opResult = new Result("success", "Quote Info", model);
		}
		
		return opResult;
	}
	
	@RequestMapping(value = "/quote/coverageDeductInfo/{coverageExpired}/{machineId}", method = RequestMethod.GET)
	public @ResponseBody Result getQuoteDeductableCoverageInfo(HttpServletRequest request, HttpServletResponse response, Model model, @PathVariable boolean coverageExpired, @PathVariable long machineId) {
		logger.debug("In getQuoteDeductableCoverageInfo with coverageExpired: "+coverageExpired+" machineId: "+machineId);
		Result opResult = null;
		if (!sessionExists(request)){
			opResult = new Result("failure", "Invalid Login", null);
		}else{
			Map<String, List<Integer>> deductCoverageMap = quoteService.getDeductableCoverageTermDetails(coverageExpired, machineId);
			List<Integer> deductibleAmtList = deductCoverageMap.get("deductibleAmtList");
			List<Integer> coverageTermList = deductCoverageMap.get("coverageTermList");
			model.addAttribute("deductibleAmtList", deductibleAmtList);
			model.addAttribute("coverageTermList", coverageTermList);
			if(deductibleAmtList.size() > 0 && coverageTermList.size() > 0){
				List<PricingDO> pricingDOList = quoteService.getCoveragePriceDetils(coverageExpired, machineId, deductibleAmtList.get(0).intValue(), coverageTermList.get(0).intValue());
				model.addAttribute("pricingDOList", pricingDOList);
			}
			
			opResult = new Result("success", "Quote Coverage and Deductible Info", model);
		}
		
		return opResult;
	}
	
	@RequestMapping(value = "/quote/coverageLevelPrice/{coverageExpired}/{machineId}/{deductiblePrice}/{coverageTerm}", method = RequestMethod.GET)
	public @ResponseBody Result getQuoteCoverageLevelPrice(HttpServletRequest request, HttpServletResponse response,
			Model model, @PathVariable boolean coverageExpired, @PathVariable long machineId, @PathVariable int deductiblePrice, @PathVariable int coverageTerm) {
		logger.debug("In getQuoteCoverageLevelPrice with coverageExpired: "+coverageExpired+" machineId: "+machineId+" ");
		Result opResult = null;
		if (!sessionExists(request)){
			opResult = new Result("failure", "Invalid Login", null);
		}else{
			List<PricingDO> pricingDOList = quoteService.getCoveragePriceDetils(coverageExpired, machineId, deductiblePrice, coverageTerm);
			
			opResult = new Result("success", "Quote Coverage Level Price Info", pricingDOList);
		}
		
		return opResult;
	}
	
	@RequestMapping(value = "/quote/userInfo", method = RequestMethod.GET)
	public @ResponseBody Result getUserInfo(HttpServletRequest request, HttpServletResponse response, Model model) {
		logger.debug("In getUserInfo");
		Result opResult = null;
		if (!sessionExists(request)){
			opResult = new Result("failure", "Invalid Login", null);
		}else{
			AccountDO accountDO = getAccountDetails(request);
			DealerDO  dealerDO = null;
			if(accountDO.getRoleName().equalsIgnoreCase(AggConstants.ACCOUNT_TYPE_ADMIN)){
				dealerDO = new DealerDO();
				dealerDO.setName(accountDO.getFirstName()+" "+accountDO.getLastName());
			}else{
				dealerDO = dealerService.getDealerInfo(accountDO.getId());
			}
			
			opResult = new Result("success", "Quote Info", dealerDO);
		}
		
		return opResult;
	}
	
	@RequestMapping(value = "/quote/addQuote/warrantyInfo", method = RequestMethod.POST)
	public @ResponseBody Result saveQuoteWarrantyInfo(@RequestBody  QuoteDO quoteDO, HttpServletRequest request, HttpServletResponse response, Model model) {
		logger.debug("In getUserInfo");
		Result opResult = null;
		if (!sessionExists(request)){
			opResult = new Result("failure", "Invalid Login", null);
		}else{
			quoteService.saveWarrantyInfo(quoteDO);
			
			logger.info("quoteId: "+quoteDO.getQuoteId()+" and id: "+quoteDO.getId());
			
			opResult = new Result("success", "Quote Info", quoteDO);
		}
		
		return opResult;
	}
	
}
