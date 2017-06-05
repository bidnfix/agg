package com.agg.application.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
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

import com.agg.application.model.MachineDO;
import com.agg.application.model.Result;
import com.agg.application.service.ReportService;

@RestController
@RequestMapping("/agg")
public class ReportController extends BaseController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ReportService reportService;

	@RequestMapping(value = "/generateClaimReport", method = RequestMethod.GET, consumes = MediaType.ALL_VALUE)
	public @ResponseBody Result generateClaimReport(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
		logger.info("Inside generateClaimReport()");
		Result opResult = null;
		if (!sessionExists(request)){
			opResult = new Result("failure", "Invalid Login", null);
		}else{
			boolean isGenerate = reportService.generateClaimReport();
			model.put("isGenerate", isGenerate);
			opResult = new Result("success", null, model);
		}
		return opResult;	
	}
	
	
	
}