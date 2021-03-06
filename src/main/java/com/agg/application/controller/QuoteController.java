package com.agg.application.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.agg.application.model.DealerDO;
import com.agg.application.model.MachineInfoDO;
import com.agg.application.model.ManufacturerDO;
import com.agg.application.model.PricingDO;
import com.agg.application.model.QuoteDO;
import com.agg.application.model.ReportDO;
import com.agg.application.model.Result;
import com.agg.application.model.UseOfEquipmentDO;
import com.agg.application.service.DealerService;
import com.agg.application.service.MachineService;
import com.agg.application.service.QuoteService;
import com.agg.application.utils.AggConstants;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

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
	
	@Value("${file.banner.image.path}")
	private String reportImagePath;

	@RequestMapping(value = "/quoteInfo", method = RequestMethod.GET)
	public @ResponseBody Result getQuoteInfo(HttpServletRequest request, HttpServletResponse response, Model model) {
		logger.debug("In getQuoteInfo");
		Result opResult = null;
		if (!sessionExists(request)){
			opResult = new Result("failure", "Invalid Login", null);
		}else{
			List<DealerDO> dealerDOList = dealerService.getActiveDealers(getAccountDetails(request));
			List<ManufacturerDO> manufacturerDOList = machineService.getManufacturerDetails();
			model.addAttribute("dealerDOList", dealerDOList);
			model.addAttribute("manufacturerDOList", manufacturerDOList);
			model.addAttribute("useOfEquipmentDOList", quoteService.getUseOfEquipmentDetails());
			model.addAttribute("machineTypeDOList", machineService.getMachineTypes());
			
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
				List<PricingDO> pricingDOList = quoteService.getCoveragePriceDetils(coverageExpired, machineId, deductibleAmtList.get(0).intValue(), coverageTermList.get(0).intValue(), -1);
				List<Integer> coverageLevelHoursList = null;
				if(pricingDOList != null && !pricingDOList.isEmpty()){
					coverageLevelHoursList = new ArrayList<Integer>();
					for(PricingDO pricingDO : pricingDOList){
						coverageLevelHoursList.add(pricingDO.getCoverageLevelHours());
					}
				}
				model.addAttribute("pricingDOList", pricingDOList);
				model.addAttribute("coverageLevelHoursList", coverageLevelHoursList);
			}
			
			opResult = new Result("success", "Quote Coverage and Deductible Info", model);
		}
		
		return opResult;
	}
	
	@RequestMapping(value = "/quote/coverageLevelPrice/{coverageExpired}/{machineId}/{deductiblePrice}/{coverageTerm}/{coverageHours}", method = RequestMethod.GET)
	public @ResponseBody Result getQuoteCoverageLevelPrice(HttpServletRequest request, HttpServletResponse response,
			Model model, @PathVariable boolean coverageExpired, @PathVariable long machineId, @PathVariable int deductiblePrice, @PathVariable int coverageTerm, 
			@PathVariable int coverageHours) {
		logger.debug("In getQuoteCoverageLevelPrice with coverageExpired: "+coverageExpired+" machineId: "+machineId+" deductiblePrice: "+deductiblePrice
				+" coverageTerm: "+coverageTerm+" coverageHours: "+coverageHours);
		Result opResult = null;
		if (!sessionExists(request)){
			opResult = new Result("failure", "Invalid Login", null);
		}else{
			List<PricingDO> pricingDOList = quoteService.getCoveragePriceDetils(coverageExpired, machineId, deductiblePrice, coverageTerm, coverageHours);
			List<Integer> coverageLevelHoursList = null;
			if(pricingDOList != null && !pricingDOList.isEmpty()){
				coverageLevelHoursList = new ArrayList<Integer>();
				for(PricingDO pricingDO : pricingDOList){
					coverageLevelHoursList.add(pricingDO.getCoverageLevelHours());
				}
			}
			model.addAttribute("pricingDOList", pricingDOList);
			model.addAttribute("coverageLevelHoursList", coverageLevelHoursList);
			
			opResult = new Result("success", "Quote Coverage Level Price Info", model);
		}
		
		return opResult;
	}
	
	@RequestMapping(value = "/quote/userInfo/{dealerId}", method = RequestMethod.GET)
	public @ResponseBody Result getUserInfo(HttpServletRequest request, HttpServletResponse response, Model model, @PathVariable("dealerId") long dealerId) {
		logger.debug("In getUserInfo with dealerId: "+dealerId);
		Result opResult = null;
		if (!sessionExists(request)){
			opResult = new Result("failure", "Invalid Login", null);
		}else{
			
			DealerDO dealerDO = dealerService.getDealer(dealerId);
			
			opResult = new Result("success", "Quote Info", dealerDO);
		}
		
		return opResult;
	}
	
	@RequestMapping(value = "/quote/addQuote/warrantyInfo", method = RequestMethod.POST)
	public @ResponseBody Result saveQuoteWarrantyInfo(@RequestBody  QuoteDO quoteDO, HttpServletRequest request, HttpServletResponse response, Model model) {
		logger.debug("In saveQuoteWarrantyInfo");
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
	
	@RequestMapping(value = "/quote/addQuote/machineInfo", method = RequestMethod.POST)
	public @ResponseBody Result saveQuoteMachineInfo(@RequestBody  QuoteDO quoteDO, HttpServletRequest request, HttpServletResponse response, Model model) {
		logger.debug("In saveQuoteMachineInfo");
		Result opResult = null;
		if (!sessionExists(request)){
			opResult = new Result("failure", "Invalid Login", null);
		}else{
			quoteService.saveMachineInfo(quoteDO);
			
			logger.info("quoteId: "+quoteDO.getQuoteId()+" and id: "+quoteDO.getId());
			
			opResult = new Result("success", "Quote Info", quoteDO);
		}
		
		return opResult;
	}
	
	@RequestMapping(value = "/quote/addQuote/coverageInfo", method = RequestMethod.POST)
	public @ResponseBody Result saveQuoteCoverageInfo(@RequestBody  QuoteDO quoteDO, HttpServletRequest request, HttpServletResponse response, Model model) {
		logger.debug("In saveQuoteCoverageInfo");
		Result opResult = null;
		if (!sessionExists(request)){
			opResult = new Result("failure", "Invalid Login", null);
		}else{
			quoteService.saveCoverageInfo(quoteDO);
			
			logger.info("quoteId: "+quoteDO.getQuoteId()+" and id: "+quoteDO.getId());
			
			opResult = new Result("success", "Quote Info", quoteDO);
		}
		
		return opResult;
	}
	
	@RequestMapping(value = "/quote/addQuote/summaryInfo", method = RequestMethod.POST)
	public @ResponseBody Result saveQuoteSummaryInfo(@RequestBody  QuoteDO quoteDO, HttpServletRequest request, HttpServletResponse response, Model model) {
		logger.debug("In saveQuoteSummaryInfo");
		Result opResult = null;
		if (!sessionExists(request)){
			opResult = new Result("failure", "Invalid Login", null);
		}else{
			quoteService.saveCoverageInfo(quoteDO);
			
			logger.info("quoteId: "+quoteDO.getQuoteId()+" and id: "+quoteDO.getId());
			
			opResult = new Result("success", "Quote Info", quoteDO);
		}
		
		return opResult;
	}
	
	@RequestMapping(value = "/quote/addQuote/purchaseInfo", method = RequestMethod.POST)
	public @ResponseBody Result saveQuotePurchaseInfo(@RequestBody  QuoteDO quoteDO, HttpServletRequest request, HttpServletResponse response, Model model) throws Exception{
		logger.debug("In saveQuotePurchaseInfo");
		Result opResult = null;
		if (!sessionExists(request)){
			opResult = new Result("failure", "Invalid Login", null);
		}else{
			StringBuffer url = request.getRequestURL();
			String uri = request.getRequestURI();
			String appUrl = url.substring(0, url.length() - uri.length());
			logger.info("appUrl: "+appUrl);
			
			quoteService.savePurchaseInfo(quoteDO, appUrl);
			
			logger.info("quoteId: "+quoteDO.getQuoteId()+" and id: "+quoteDO.getId());
			
			opResult = new Result("success", "Quote Info", quoteDO);
		}
		
		return opResult;
	}
	
	@RequestMapping(value = "/quote/report/{reportType}/{quoteId}", method = RequestMethod.GET, produces="application/pdf;charset=UTF-8")
	public ModelAndView showQuoteReport(@PathVariable String reportType, @PathVariable String quoteId, ModelAndView modelAndView, HttpServletRequest request, 
			HttpServletResponse response, ModelMap modelMap) throws Exception{
		logger.debug("In showQuoteReport with reportType: "+reportType+" and quoteId: "+quoteId);
		if (!sessionExists(request)){
			modelAndView = new ModelAndView("login");
		}else{
			StringBuffer url = request.getRequestURL();
			String uri = request.getRequestURI();
			String appUrl = url.substring(0, url.length() - uri.length());
			logger.info("appUrl: "+appUrl);
			
			ReportDO reportDO = quoteService.getQuoteReportDetails(quoteId, reportType);
			JRDataSource jrDataSource = null;
			if(reportDO != null){
				List<ReportDO> reportDOList = new ArrayList<ReportDO>();
				reportDOList.add(reportDO);
				jrDataSource = new JRBeanCollectionDataSource(reportDOList);
			}else{
				jrDataSource = new JREmptyDataSource();
			}
			
			//logger.debug("image path: "+request.getServletContext().getRealPath("/assets/images/"));
			//logger.debug("resource image path: "+request.getServletContext().getResource("/assets/images/").getPath());
			logger.debug("system user.dir path: "+System.getProperty("user.dir"));
			modelMap.put("datasource", jrDataSource);
			modelMap.put("format", "pdf");
			//modelMap.put("imagePath", appUrl+"/assets/images/report_banner.png");
			modelMap.put("imagePath", System.getProperty("user.dir")+reportImagePath);
			if(reportType != null && reportType.equalsIgnoreCase("customer")){
				modelAndView = new ModelAndView("rpt_customerQuote", modelMap);
			}else if(reportType != null && reportType.equalsIgnoreCase("dealer")){
				modelAndView = new ModelAndView("rpt_dealerQuote", modelMap);
			}else if(reportType != null && reportType.equalsIgnoreCase("invoice")){
				modelAndView = new ModelAndView("rpt_dealerInvoice", modelMap);
			}
			
		}
		
		return modelAndView;
	}
	
	@RequestMapping(value = "/quotesInfo", method = RequestMethod.GET)
	public Result getQuotes(Model model, HttpServletRequest request, HttpServletResponse response) {
		Result opResult = null;
		if (!sessionExists(request)) {
			opResult = new Result("failure", "Invalid Login", null);
		} else {
			//opResult = new Result("success", null, quoteService.getQuotes(getAccountDetails(request)));
			String draw = request.getParameter("draw");
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
			
			Direction direction = Direction.DESC;
			if ("asc".equals(orderDirection)) {
				 direction = Direction.ASC;
			}
			
			String properties = "";
			
			Pageable pageable;// = new PageRequest(page, pageLength, direction, properties);
			
			long totalRecords = quoteService.getQuotesCount(getAccountDetails(request));
			
			List<QuoteDO> quoteDos; 
			
			if (!StringUtils.isEmpty(searchText)) {
				
				String statusSearch = getSearchTextForStatus(searchText);
				
				searchText = searchText.replaceAll("\\s+", "|");
				
				long filteredCount = quoteService.getQuotesSearchCount(getAccountDetails(request), searchText, statusSearch);
				
				switch (Integer.parseInt(orderColumn)) {
				case 0:
					properties = "quote_id";
					break;
				case 1:
					properties = "dealer.name";
					break;
				case 2:
					properties = "customerInfo.name";
					break;
				case 3:
					properties = "machine_model";
					break;
				case 4:
					properties = "machine_sale_date";
					break;
				case 5:
					properties = "status";
					break;
				case 6:
					properties = "create_date";
					break;

				default:
					properties = "create_date";
					break;
				}
				
				if (pageLength == -1) {
					pageLength = (int) filteredCount;
					pageable = new PageRequest(page, pageLength, direction, properties);
				} else {
					pageable = new PageRequest(page, pageLength, direction, properties);
				}
				
				//quoteDos = quoteService.getAllQuotesForSearch(getAccountDetails(request), searchText, pageable);
				quoteDos = quoteService.getAllQuotesForSearch(getAccountDetails(request), searchText, Integer.parseInt(start), pageLength, properties, orderDirection, statusSearch, pageable);
				opResult = new Result("success", null, quoteDos);
				
				opResult.setDraw(Integer.parseInt(draw));
				opResult.setRecordsTotal(totalRecords);
				opResult.setRecordsFiltered(filteredCount);
				
			} else {
				
				switch (Integer.parseInt(orderColumn)) {
				case 0:
					properties = "id.quoteId";
					break;
				case 1:
					properties = "dealer.name";
					break;
				case 2:
					properties = "customerInfo.name";
					break;
				case 3:
					properties = "machineInfo.model";
					break;
				case 4:
					properties = "machineSaleDate";
					break;
				case 5:
					properties = "status";
					break;
				case 6:
					properties = "createDate";
					break;

				default:
					properties = "id.quoteId";
					break;
				}
				
				if (pageLength == -1) {
					pageLength = (int) totalRecords;
					pageable = new PageRequest(page, pageLength, direction, properties);
				} else {
					pageable = new PageRequest(page, pageLength, direction, properties);
				}
				quoteDos = quoteService.getAllQuotes(getAccountDetails(request), pageable);
				opResult = new Result("success", null, quoteDos);
				
				opResult.setDraw(Integer.parseInt(draw));
				opResult.setRecordsTotal(totalRecords);
				opResult.setRecordsFiltered(totalRecords);
			}
		}
		
		return opResult;
	}
	
	private String getSearchTextForStatus(String searchText) {
		
		String[] search = searchText.split("\\s+");
		
		String status = "99";
		for (String string :search) {
			if (AggConstants.QUOTE_STATUS_ACRHIVE.toLowerCase().contains(string.toLowerCase())) {
				status = status+"|"+AggConstants.B_QUOTE_STATUS_ACRHIVE;
			} else if (AggConstants.QUOTE_STATUS_ESTIMATING_PRICE.toLowerCase().contains(string.toLowerCase())) {
				status = status+"|"+AggConstants.B_QUOTE_STATUS_ESTIMATING_PRICE;
			} else if (AggConstants.QUOTE_STATUS_PURCHASE_REQUESTED.toLowerCase().contains(string.toLowerCase())) {
				status = status+"|"+AggConstants.B_QUOTE_STATUS_PURCHASE_REQUESTED;
			} else if (AggConstants.QUOTE_STATUS_INVOICED.toLowerCase().contains(string.toLowerCase())) {
				status = status+"|"+AggConstants.B_QUOTE_STATUS_INVOICED;
			} else if (AggConstants.QUOTE_STATUS_CLOSED.toLowerCase().contains(string.toLowerCase())) {
				status = status+"|"+AggConstants.B_QUOTE_STATUS_CLOSED;
			}
		}
		return status;
	}

	@RequestMapping(value = "/archivedQuotesInfo", method = RequestMethod.GET)
	public Result getArchivedQuotes(Model model, HttpServletRequest request, HttpServletResponse response) {
		Result opResult = null;
		if (!sessionExists(request)){
			opResult = new Result("failure", "Invalid Login", null);
		}else{
			opResult = new Result("success", null, quoteService.getArchivedQuotes(getAccountDetails(request)));
		}
		
		return opResult;
	}
	
	@RequestMapping(value = "/quoteInfo/{id}/{quoteId}", method = RequestMethod.GET)
	public Result getQuote(@PathVariable int id, @PathVariable String quoteId, Model model, HttpServletRequest request, HttpServletResponse response) {
		logger.debug("Inside getQuote with id: "+id+" & quoteId: "+quoteId);
		Result opResult = null;
		if (!sessionExists(request)){
			opResult = new Result("failure", "Invalid Login", null);
		}else{
			QuoteDO quoteDO = quoteService.getQuote(getAccountDetails(request), id, quoteId);
			if(quoteDO != null){
				List<DealerDO> dealerDOList = dealerService.getActiveDealers(getAccountDetails(request));
				List<ManufacturerDO> manufacturerDOList = machineService.getManufacturerDetails();
				//boolean coverageExpired = true;
				boolean coverageExpired = false;
				if(quoteDO.isCoverageExpired()){
					coverageExpired = true;
		    	}/*else if(quoteDO.getCoverageEndDate() != null && quoteDO.getCoverageEndDate().after(new Date())){
		    		coverageExpired = false;
		    	}*/
				Map<String, List<Integer>> deductCoverageMap = null;
				List<PricingDO> pricingDOList = null;
				List<MachineInfoDO> machineModels = null;
				if(quoteDO.getMachineInfoDO() != null){
					deductCoverageMap = quoteService.getDeductableCoverageTermDetails(coverageExpired, 
							quoteDO.getMachineInfoDO().getMachineId());
					
					pricingDOList = quoteService.getCoveragePriceDetils(coverageExpired, 
							quoteDO.getMachineInfoDO().getMachineId(), new Double(quoteDO.getDeductiblePrice()).intValue(), quoteDO.getCoverageTerm(), -1);
				}
				if(quoteDO.getManufacturerDO() != null){
					machineModels = machineService.getManfModel(Integer.valueOf(String.valueOf(quoteDO.getManufacturerDO().getId())));
				}
				List<Integer> coverageLevelHoursList = null;
				if(pricingDOList != null && !pricingDOList.isEmpty()){
					coverageLevelHoursList = new ArrayList<Integer>();
					for(PricingDO pricingDO : pricingDOList){
						coverageLevelHoursList.add(pricingDO.getCoverageLevelHours());
					}
				}
				if(deductCoverageMap != null){
					List<Integer> deductibleAmtList = deductCoverageMap.get("deductibleAmtList");
					List<Integer> coverageTermList = deductCoverageMap.get("coverageTermList");
					model.addAttribute("deductibleAmtList", deductibleAmtList);
					model.addAttribute("coverageTermList", coverageTermList);
				}
				
				model.addAttribute("pricingDOList", pricingDOList);
				model.addAttribute("dealerDOList", dealerDOList);
				model.addAttribute("manufacturerDOList", manufacturerDOList);
				model.addAttribute("useOfEquipmentDOList", quoteService.getUseOfEquipmentDetails());
				model.addAttribute("machineModelList", machineModels);
				model.addAttribute("coverageLevelHoursList", coverageLevelHoursList);
				model.addAttribute("quote", quoteDO);
			}
			
			opResult = new Result("success", null, model);
		}
		
		return opResult;
	}
	
	/*@RequestMapping(value = "/worklist", method = RequestMethod.GET)
	public @ResponseBody Result getWorklistInfo(HttpServletRequest request, HttpServletResponse response, Model model) {
		logger.debug("In getWorklistInfo");
		Result opResult = null;
		if (!sessionExists(request)){
			opResult = new Result("failure", "Invalid Login", null);
		}else{
			
			WorklistDO worklistDO = quoteService.getWorklistCount();
			model.addAttribute("worklistDO", worklistDO);
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
			
			List<QuoteDO> quoteDOList = quoteService.getEstPriceQuotes(getAccountDetails(request));
			model.addAttribute("quoteDOList",quoteDOList);
			opResult = new Result("success", null, model);
		}
		
		return opResult;
	}
	
	@RequestMapping(value = "/purchaseRequestedQuotes", method = RequestMethod.GET)
	public Result getpurchaseReqQuotes(Model model, HttpServletRequest request, HttpServletResponse response) {
		Result opResult = null;
		if (!sessionExists(request)){
			opResult = new Result("failure", "Invalid Login", null);
		}else{
			opResult = new Result("success", null, quoteService.getEstPriceQuotes(getAccountDetails(request)));
			
			List<QuoteDO> quoteDOList = quoteService.getEstPriceQuotes(getAccountDetails(request));
			model.addAttribute("quoteDOList",quoteDOList);
			opResult = new Result("success", null, model);
		}
		
		return opResult;
	}*/
	
	@RequestMapping(value = "/quote/archiveQuote", method = RequestMethod.POST)
	public @ResponseBody Result archiveQuote(@RequestBody  QuoteDO quoteDO, HttpServletRequest request, HttpServletResponse response, Model model) throws Exception{
		logger.debug("In archiveQuote");
		Result opResult = null;
		if (!sessionExists(request)){
			opResult = new Result("failure", "Invalid Login", null);
		}else{
			logger.info("quoteId: "+quoteDO.getQuoteId()+" and id: "+quoteDO.getId());
			
			boolean isArchivedQuote = quoteService.archiveQuote(quoteDO);
			
			logger.info("isArchivedQuote: "+isArchivedQuote);
			
			opResult = new Result("success", "Quote Info", quoteDO);
		}
		
		return opResult;
	}
	
	@RequestMapping(value = "/quote/unArchiveQuote", method = RequestMethod.POST)
	public @ResponseBody Result unArchiveQuote(@RequestBody  QuoteDO quoteDO, HttpServletRequest request, HttpServletResponse response, Model model) throws Exception{
		logger.debug("In unArchiveQuote");
		Result opResult = null;
		if (!sessionExists(request)){
			opResult = new Result("failure", "Invalid Login", null);
		}else{
			logger.info("quoteId: "+quoteDO.getQuoteId()+" and id: "+quoteDO.getId());
			
			boolean isArchivedQuote = quoteService.unArchiveQuote(quoteDO);
			
			logger.info("isArchivedQuote: "+isArchivedQuote);
			
			opResult = new Result("success", "Quote Info", quoteDO);
		}
		
		return opResult;
	}
	
	@RequestMapping(value = "/quote/updateQuote", method = RequestMethod.POST)
	public @ResponseBody Result updateQuote(@RequestBody  QuoteDO quoteDO, HttpServletRequest request, HttpServletResponse response, Model model) throws Exception{
		logger.debug("In updateQuote");
		Result opResult = null;
		if (!sessionExists(request)){
			opResult = new Result("failure", "Invalid Login", null);
		}else{
			logger.info("quoteId: "+quoteDO.getQuoteId()+" and id: "+quoteDO.getId());
			
			StringBuffer url = request.getRequestURL();
			String uri = request.getRequestURI();
			String appUrl = url.substring(0, url.length() - uri.length());
			logger.info("appUrl: "+appUrl);
			
			boolean isQuoteUpdated = quoteService.updateQuote(quoteDO, getAccountDetails(request), appUrl);
			logger.info("isQuoteUpdated: "+isQuoteUpdated);
			
			if(isQuoteUpdated){
				quoteDO = quoteService.getQuote(getAccountDetails(request), quoteDO.getId(), quoteDO.getQuoteId());
			}
			
			opResult = new Result("success", "Quote Info", quoteDO);
		}
		
		return opResult;
	}
	
	@RequestMapping(value = "/quote/invoiceQuote", method = RequestMethod.POST)
	public @ResponseBody Result invoiceQuote(@RequestBody  QuoteDO quoteDO, HttpServletRequest request, HttpServletResponse response, Model model) throws Exception{
		logger.debug("In invoiceQuote");
		Result opResult = null;
		if (!sessionExists(request)){
			opResult = new Result("failure", "Invalid Login", null);
		}else{
			logger.info("quoteId: "+quoteDO.getQuoteId()+" and id: "+quoteDO.getId());
			
			StringBuffer url = request.getRequestURL();
			String uri = request.getRequestURI();
			String appUrl = url.substring(0, url.length() - uri.length());
			logger.info("appUrl: "+appUrl);
			
			boolean isQuoteInvoiced = quoteService.invoiceQuote(quoteDO, getAccountDetails(request), appUrl);
			logger.info("isQuoteInvoiced: "+isQuoteInvoiced);
			
			if(isQuoteInvoiced){
				quoteDO = quoteService.getQuote(getAccountDetails(request), quoteDO.getId(), quoteDO.getQuoteId());
			}
			
			opResult = new Result("success", "Quote Info", quoteDO);
		}
		
		return opResult;
	}
	
	@RequestMapping(value = "/quote/createContract", method = RequestMethod.POST)
	public @ResponseBody Result createContract(@RequestBody  QuoteDO quoteDO, HttpServletRequest request, HttpServletResponse response, Model model) throws Exception{
		logger.debug("In createContract");
		Result opResult = null;
		if (!sessionExists(request)){
			opResult = new Result("failure", "Invalid Login", null);
		}else{
			logger.info("quoteId: "+quoteDO.getQuoteId()+" and id: "+quoteDO.getId());
			
			StringBuffer url = request.getRequestURL();
			String uri = request.getRequestURI();
			String appUrl = url.substring(0, url.length() - uri.length());
			logger.info("appUrl: "+appUrl);
			
			boolean isContractCreated = quoteService.createContract(quoteDO, getAccountDetails(request), appUrl);
			
			logger.info("isContractCreated: "+isContractCreated);
			
			opResult = new Result("success", "Quote Info", quoteDO);
		}
		
		return opResult;
	}
	
	@RequestMapping(value = "/useOfEquipInfo", method = RequestMethod.GET, consumes = MediaType.ALL_VALUE)
	public @ResponseBody Result useOfEquipInfo(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
		logger.info("Inside useOfEquipInfo()");
		Result opResult = null;
		if (!sessionExists(request)){
			opResult = new Result("failure", "Invalid Login", null);
		}else{
			List<UseOfEquipmentDO> useOfEquipLst = quoteService.getUseOfEquipmentDetails();
			model.put("useOfEquipLst", useOfEquipLst);
			opResult = new Result("success", null, model);
		}
		return opResult;	
	}
	
	@RequestMapping(value = "/saveEquipment", method = RequestMethod.POST)
	public @ResponseBody Result saveEquipment(@RequestBody UseOfEquipmentDO useOfEquipmentDO, BindingResult result,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		logger.debug("In saveEquipment "+useOfEquipmentDO);
		Result opResult = null;
		if (!sessionExists(request)){
			opResult = new Result("failure", "Invalid Login", null);
		}else{
	
		long id = 0;
			try
			{
				id = quoteService.saveEquipment(useOfEquipmentDO);
			}catch (Exception e) {
		    	if (e instanceof DataIntegrityViolationException) {
		    		logger.error("Equipment already exist");
		    		throw new Exception("Equipment already exists");
		        } else {
		        	throw e;
		        }
		    }
			//if(id > 0){
				opResult = new Result("success", "Equipment created successfully", null);
			//}
			
		}
		
		return opResult;
	}
	
	@RequestMapping(value = "/useOfEquip/{id}", method = RequestMethod.GET, consumes = MediaType.ALL_VALUE)
	public @ResponseBody Result useOfEquip(ModelMap model, HttpServletRequest request, HttpServletResponse response, @PathVariable String id) {
		logger.info("Inside machineModel() with Id: "+id);
		Result opResult = null;
		if (!sessionExists(request)){
			opResult = new Result("failure", "Invalid Login", null);
		}else{
			if(id != null && !id.isEmpty()){
				UseOfEquipmentDO useOfEquipmentDO = quoteService.getUseOfEquip(Integer.valueOf(id));
				model.put("useOfEquip", useOfEquipmentDO);
			}
			opResult = new Result("success", null, model);
		}
		return opResult;	
	}
	
	@RequestMapping(value = "/editEquipment", method = RequestMethod.POST)
	public @ResponseBody Result editEquipment(@RequestBody UseOfEquipmentDO useOfEquipmentDO, BindingResult result,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		logger.debug("In editEquipment "+useOfEquipmentDO);
		Result opResult = null;
		if (!sessionExists(request)){
			opResult = new Result("failure", "Invalid Login", null);
		}else{
			if (result.hasErrors()){
				opResult = new Result("failure", "Invalid dealer form field values", null);
			}
			long id = 0;
	
			try
			{
			id = quoteService.editEquipment(useOfEquipmentDO);
			}catch (Exception e) {
		    	if (e instanceof DataIntegrityViolationException) {
		    		logger.error("Equipment already exist");
		    		throw new Exception("Equipment already exists");
		        } else {
		        	throw e;
		        }
		    }
			if(id > 0){
				opResult = new Result("success", "Invalid Equipment form field values", null);
			}
			
		}
		
		return opResult;
	}
	
}
