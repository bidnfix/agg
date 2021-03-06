package com.agg.application.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.util.StringUtils;

import com.agg.application.model.ContractDO;
import com.agg.application.model.QuoteDO;
import com.agg.application.model.Result;
import com.agg.application.model.WorklistDO;
import com.agg.application.service.ContractsService;
import com.agg.application.service.QuoteService;

@RestController
@RequestMapping("/agg")
public class WorklistController extends BaseController{
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private QuoteService quoteService;

	@Autowired
	private ContractsService contractsService;	
	
	@RequestMapping(value = "/worklist", method = RequestMethod.GET)
	public @ResponseBody Result getWorklistInfo(HttpServletRequest request, HttpServletResponse response, Model model) {
		logger.debug("In getWorklistInfo");
		Result opResult = null;
		if (!sessionExists(request)){
			opResult = new Result("failure", "Invalid Login", null);
		}else{
			
			WorklistDO worklistDO = quoteService.getWorklistCount(getAccountDetails(request));
			model.addAttribute("worklistDO", worklistDO);
			List<QuoteDO> quoteList = quoteService.getPurchaseReqQuotes(getAccountDetails(request));
			model.addAttribute("quoteList", quoteList);
			opResult = new Result("success", null, model);
		}
		
		return opResult;
	}
	
	@RequestMapping(value = "/estimatedPriceQuotes", method = RequestMethod.GET)
	public Result getestPriceQuotes(Model model, HttpServletRequest request, HttpServletResponse response) {
		Result opResult = null;
		if (!sessionExists(request)){
			opResult = new Result("failure", "Invalid Login", null);
		}else{
			opResult = new Result("success", null, quoteService.getEstPriceQuotes(getAccountDetails(request)));
			
			/*List<QuoteDO> quoteDOList = quoteService.getEstPriceQuotes(getAccountDetails(request));
			model.addAttribute("quoteDOList",quoteDOList);
			opResult = new Result("success", null, model);*/
		}
		
		return opResult;
	}
	
	@RequestMapping(value = "/purchaseRequestedQuotes", method = RequestMethod.GET)
	public Result getpurchaseReqQuotes(Model model, HttpServletRequest request, HttpServletResponse response) {
		Result opResult = null;
		if (!sessionExists(request)){
			opResult = new Result("failure", "Invalid Login", null);
		}else{
			opResult = new Result("success", null, quoteService.getPurchaseReqQuotes(getAccountDetails(request)));
			
			/*List<QuoteDO> quoteDOList = quoteService.getEstPriceQuotes(getAccountDetails(request));
			model.addAttribute("quoteDOList",quoteDOList);
			opResult = new Result("success", null, model);*/
		}
		
		return opResult;
	}
	
	@RequestMapping(value = "/invoicedQuotes", method = RequestMethod.GET)
	public Result getInvoicedQuotes(Model model, HttpServletRequest request, HttpServletResponse response) {
		Result opResult = null;
		if (!sessionExists(request)){
			opResult = new Result("failure", "Invalid Login", null);
		}else{
			opResult = new Result("success", null, quoteService.getInvoicedQuotes(getAccountDetails(request)));
			
			/*List<QuoteDO> quoteDOList = quoteService.getEstPriceQuotes(getAccountDetails(request));
			model.addAttribute("quoteDOList",quoteDOList);
			opResult = new Result("success", null, model);*/
		}
		
		return opResult;
	}
	
	@RequestMapping(value = "/activeContracts", method = RequestMethod.GET)
	public Result getActiveContracts(Model model, HttpServletRequest request, HttpServletResponse response) {
		Result opResult = null;
		if (!sessionExists(request)){
			opResult = new Result("failure", "Invalid Login", null);
		}else{
			opResult = new Result("success", "", model.addAttribute(contractsService.getActiveContracts(getAccountDetails(request))));
			/*String draw = request.getParameter("draw");
			String start = request.getParameter("start");
			String length = request.getParameter("length");
			String searchText = request.getParameter("search[value]");
			String orderColumn = request.getParameter("order[0][column]");
			String orderDirection = request.getParameter("order[0][dir]");
			
			int page = Integer.parseInt(start);
			int pageLength = Integer.parseInt(length);
			
			if (page > 0) {
				 page = page / pageLength;
			} 
			
			Direction direction = "asc".equals(orderDirection) ? Direction.ASC :Direction.DESC;
			
			String properties = "";
			
			switch (Integer.parseInt(orderColumn)) {
			case 0:
				properties = "contractId";
				break;
			case 1:
				properties = "machineSerialNo";
				break;
			case 2:
				properties = "lol";
				break;
			case 3:
				properties = "inceptionDate";
				break;
			case 4:
				properties = "expirationDate";
				break;
			case 5:
				properties = "expirationUsageHours";
				break;
			case 6:
				properties = "status";
				break;
			case 7:
				properties = "lastUpdatedDate";
				break;

			default:
				properties = "id.quoteId";
				break;
			}
			
			Pageable pageable;// = new PageRequest(page, pageLength, direction, properties);
			
			long totalRecords = contractsService.getActiveContractsCount(getAccountDetails(request));
			
			List<ContractDO> contractDos;
			
			if (!StringUtils.isEmpty(searchText)) {
				long filteredCount = contractsService.getActiveContractsSearchCount(getAccountDetails(request), searchText);
				
				if (pageLength == -1) {
					pageLength = (int) filteredCount;
					pageable = new PageRequest(page, pageLength, direction, properties);
				} else {
					pageable = new PageRequest(page, pageLength, direction, properties);
				}
				
				contractDos= contractsService.getActiveContractsForSearch(getAccountDetails(request), searchText, pageable);
				
				opResult = new Result("success", null, contractDos);
				
				opResult.setDraw(Integer.parseInt(draw));
				opResult.setRecordsTotal(totalRecords);
				opResult.setRecordsFiltered(filteredCount);
				
			} else {
				if (pageLength == -1) {
					pageLength = (int) totalRecords;
					pageable = new PageRequest(page, pageLength, direction, properties);
				} else {
					pageable = new PageRequest(page, pageLength, direction, properties);
				}
				
				contractDos= contractsService.getActiveContracts(getAccountDetails(request), pageable);
				opResult = new Result("success", null, contractDos);
				
				opResult.setDraw(Integer.parseInt(draw));
				opResult.setRecordsTotal(totalRecords);
				opResult.setRecordsFiltered(totalRecords);
			}*/
		}
		return opResult;
	}
	
	@RequestMapping(value = "/inactiveContracts", method = RequestMethod.GET)
	public Result getInactiveContracts(Model model, HttpServletRequest request, HttpServletResponse response) {
		Result opResult = null;
		if (!sessionExists(request)){
			opResult = new Result("failure", "Invalid Login", null);
		}else{
			opResult = new Result("success", "", model.addAttribute(contractsService.getInactiveContracts(getAccountDetails(request))));
		}
		return opResult;
	}
	

}
