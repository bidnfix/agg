package com.agg.application.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.agg.application.entity.Quote;
import com.agg.application.model.QuoteDO;
import com.agg.application.model.Result;
import com.agg.application.service.ClaimsService;

@RestController
@RequestMapping("/agg")
public class ClaimsController extends BaseController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ClaimsService claimsService;

	@RequestMapping(value = "/claimsInfo", method = RequestMethod.GET, consumes = MediaType.ALL_VALUE)
	public @ResponseBody Result claimsInfo(ModelMap model, HttpServletResponse response) {
		logger.info("Inside claimsInfo()");
		List<QuoteDO> quoteInfoList = claimsService.getClaimsInfo();
		logger.info("quoteInfoList size: "+quoteInfoList.size());
		model.put("quoteInfoList", quoteInfoList);
		return new Result("success", null, model);	
	}
	
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
}
