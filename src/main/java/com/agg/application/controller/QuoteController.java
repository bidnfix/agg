package com.agg.application.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.agg.application.model.AccountDO;
import com.agg.application.model.DealerDO;
import com.agg.application.model.MachineInfoDO;
import com.agg.application.model.ManufacturerDO;
import com.agg.application.model.PricingDO;
import com.agg.application.model.QuoteDO;
import com.agg.application.model.ReportDO;
import com.agg.application.model.Result;
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
	
	@RequestMapping(value = "/quote/report/{reportType}/{quoteId}", method = RequestMethod.GET)
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
			
			ReportDO reportDO = quoteService.getQuoteReportDetails(quoteId);
			JRDataSource jrDataSource = null;
			if(reportDO != null){
				List<ReportDO> reportDOList = new ArrayList<ReportDO>();
				reportDOList.add(reportDO);
				jrDataSource = new JRBeanCollectionDataSource(reportDOList);
			}else{
				jrDataSource = new JREmptyDataSource();
			}
			
			modelMap.put("datasource", jrDataSource);
			modelMap.put("format", "pdf");
			modelMap.put("imagePath", appUrl+"/assets/images/logo.png");
			if(reportType != null && reportType.equalsIgnoreCase("customer")){
				modelAndView = new ModelAndView("rpt_customerQuote", modelMap);
			}else if(reportType != null && reportType.equalsIgnoreCase("dealer")){
				modelAndView = new ModelAndView("rpt_dealerQuote", modelMap);
			}
			
		}
		
		return modelAndView;
	}
	
	@RequestMapping(value = "/quotesInfo", method = RequestMethod.GET)
	public Result getQuotes(Model model, HttpServletRequest request, HttpServletResponse response) {
		Result opResult = null;
		if (!sessionExists(request)){
			opResult = new Result("failure", "Invalid Login", null);
		}else{
			opResult = new Result("success", null, quoteService.getQuotes(getAccountDetails(request)));
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
				Map<String, List<Integer>> deductCoverageMap = quoteService.getDeductableCoverageTermDetails(quoteDO.isCoverageExpired(), 
						quoteDO.getMachineInfoDO().getMachineId());
				List<PricingDO> pricingDOList = quoteService.getCoveragePriceDetils(quoteDO.isCoverageExpired(), 
						quoteDO.getMachineInfoDO().getMachineId(), new Double(quoteDO.getDeductiblePrice()).intValue(), quoteDO.getCoverageTerm());
				List<MachineInfoDO> machineModels = machineService.getManfModel(Integer.valueOf(String.valueOf(quoteDO.getManufacturerDO().getId())));
				List<Integer> coverageLevelHoursList = null;
				if(pricingDOList != null && !pricingDOList.isEmpty()){
					coverageLevelHoursList = new ArrayList<Integer>();
					for(PricingDO pricingDO : pricingDOList){
						coverageLevelHoursList.add(pricingDO.getCoverageLevelHours());
					}
				}
				List<Integer> deductibleAmtList = deductCoverageMap.get("deductibleAmtList");
				List<Integer> coverageTermList = deductCoverageMap.get("coverageTermList");
				model.addAttribute("deductibleAmtList", deductibleAmtList);
				model.addAttribute("coverageTermList", coverageTermList);
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
	
	@RequestMapping(value = "/worklist", method = RequestMethod.GET)
	public @ResponseBody Result getWorklistInfo(HttpServletRequest request, HttpServletResponse response, Model model) {
		logger.debug("In getWorklistInfo");
		Result opResult = null;
		if (!sessionExists(request)){
			opResult = new Result("failure", "Invalid Login", null);
		}else{
			opResult = new Result("success", null, quoteService.getQuotes(getAccountDetails(request)));
		}
		
		return opResult;
	}
	
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
	
	@RequestMapping(value = "/quote/updateQuote", method = RequestMethod.POST)
	public @ResponseBody Result updateQuote(@RequestBody  QuoteDO quoteDO, HttpServletRequest request, HttpServletResponse response, Model model) throws Exception{
		logger.debug("In updateQuote");
		Result opResult = null;
		if (!sessionExists(request)){
			opResult = new Result("failure", "Invalid Login", null);
		}else{
			logger.info("quoteId: "+quoteDO.getQuoteId()+" and id: "+quoteDO.getId());
			
			boolean isQuoteUpdated = quoteService.updateQuote(quoteDO);
			
			logger.info("isQuoteUpdated: "+isQuoteUpdated);
			
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
			
			boolean isQuoteInvoiced = quoteService.invoiceQuote(quoteDO);
			
			logger.info("isQuoteInvoiced: "+isQuoteInvoiced);
			
			opResult = new Result("success", "Quote Info", quoteDO);
		}
		
		return opResult;
	}
	
	
}
