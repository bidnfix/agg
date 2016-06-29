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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.agg.application.model.DealerDO;
import com.agg.application.model.ManufacturerDO;
import com.agg.application.model.Result;
import com.agg.application.service.DealerService;
import com.agg.application.service.MachineService;
import com.agg.application.service.QuoteService;

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
			Map<String, List<Long>> deductCoverageMap = quoteService.getDeductableCoverageTermDetails(coverageExpired, machineId);
			model.addAttribute("deductibleAmtList", deductCoverageMap.get("deductibleAmtList"));
			model.addAttribute("coverageTermList", deductCoverageMap.get("coverageTermList"));
			
			opResult = new Result("success", "Quote Coverage and Deductible Info", model);
		}
		
		return opResult;
	}
	
}
