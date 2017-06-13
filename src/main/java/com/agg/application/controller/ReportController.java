package com.agg.application.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.agg.application.model.Result;
import com.agg.application.service.ReportService;
import com.agg.application.utils.ClaimExcelView;
import com.agg.application.utils.QuoteExcelView;

@RestController
@RequestMapping("/agg")
public class ReportController extends BaseController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ReportService reportService;

	@RequestMapping(value = "/generateClaimReport", method = RequestMethod.GET)
	public ModelAndView generateClaimReport(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
		logger.info("Inside generateClaimReport()");
		Result opResult = null;
		//List workbook = null;
		if (!sessionExists(request)){
			opResult = new Result("failure", "Invalid Login", null);
		}else{
			
			model.put("workbook", reportService.generateClaimReport());
			response.setContentType( "application/ms-excel" );
	        response.setHeader( "Content-disposition", "attachment; filename=ClaimReport.xls" );         
	        return new ModelAndView(new ClaimExcelView(), model);
			
			//model.put("claimWorkbook", workbook);
			//opResult = new Result("success", null, model);
		}
		return null;	
	}
	
	@RequestMapping(value = "/generateQuoteReport", method = RequestMethod.GET)
	public ModelAndView generateQuoteReport(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
		logger.info("Inside generateQuoteReport()");
		Result opResult = null;
		//List workbook = null;
		if (!sessionExists(request)){
			opResult = new Result("failure", "Invalid Login", null);
		}else{
			
			model.put("workbook", reportService.generateQuoteReport());
			response.setContentType( "application/ms-excel" );
	        response.setHeader( "Content-disposition", "attachment; filename=QuoteReport.xls" );         
	        return new ModelAndView(new QuoteExcelView(), model);
			
			//model.put("claimWorkbook", workbook);
			//opResult = new Result("success", null, model);
		}
		return null;	
	}
	
	
	
}
