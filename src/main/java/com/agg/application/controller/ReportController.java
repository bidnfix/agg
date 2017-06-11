package com.agg.application.controller;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.agg.application.model.Result;
import com.agg.application.service.ReportService;

@RestController
@RequestMapping("/agg")
public class ReportController extends BaseController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ReportService reportService;

	@RequestMapping(value = "/generateClaimReport", method = RequestMethod.POST, consumes = MediaType.ALL_VALUE)
	public @ResponseBody Result generateClaimReport(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
		logger.info("Inside generateClaimReport()");
		Result opResult = null;
		XSSFWorkbook workbook = null;
		if (!sessionExists(request)){
			opResult = new Result("failure", "Invalid Login", null);
		}else{
			workbook = (XSSFWorkbook)reportService.generateClaimReport();
			
			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Content-Disposition", "attachment; filename=filename.xlsx");
			// ...
			// Now populate workbook the usual way.
			// ...
			try{
				
				workbook.write(response.getOutputStream()); // Write workbook to response.
				workbook.close();
				/*FileOutputStream outputStream = new FileOutputStream("D:/ClaimReport.xlsx");
				workbook.write(outputStream);
				workbook.close();*/
				
				
			}catch(FileNotFoundException e)
			{
				e.printStackTrace();
			}catch(IOException e)
			{
				e.printStackTrace();
			}
			
			model.put("claimWorkbook", workbook);
			opResult = new Result("success", null, model);
		}
		
		
		
		
		return opResult;	
	}
	
	@RequestMapping(value = "/generateQuoteReport", method = RequestMethod.POST, consumes = MediaType.ALL_VALUE)
	public @ResponseBody Result generateQuoteReport(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
		logger.info("Inside generateQuoteReport()");
		Result opResult = null;
		XSSFWorkbook workbook = null;
		if (!sessionExists(request)){
			opResult = new Result("failure", "Invalid Login", null);
		}else{
			workbook = (XSSFWorkbook)reportService.generateQuoteReport();
			
			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Content-Disposition", "attachment; filename=filename.xlsx");
			// ...
			// Now populate workbook the usual way.
			// ...
			try{
				
				workbook.write(response.getOutputStream()); // Write workbook to response.
				workbook.close();
				/*FileOutputStream outputStream = new FileOutputStream("D:/ClaimReport.xlsx");
				workbook.write(outputStream);
				workbook.close();*/
				
				
			}catch(FileNotFoundException e)
			{
				e.printStackTrace();
			}catch(IOException e)
			{
				e.printStackTrace();
			}
			
			model.put("quoteWorkbook", workbook);
			opResult = new Result("success", null, model);
		}
		
		
		
		
		return opResult;	
	}
	
	
	
}
