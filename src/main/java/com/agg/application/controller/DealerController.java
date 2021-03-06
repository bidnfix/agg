package com.agg.application.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.agg.application.model.DealerDO;
import com.agg.application.model.LocationDO;
import com.agg.application.model.Result;
import com.agg.application.model.RoleDO;
import com.agg.application.model.UserDO;
import com.agg.application.service.DealerService;

@RestController
@RequestMapping("/agg")
public class DealerController extends BaseController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private DealerService dealerService;

	@RequestMapping(value = "/dealers", method = RequestMethod.GET)
	public Result getDealers(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) {

		if (!sessionExists(request))
			return null;
		//model.put("dealers", dealerService.getDealers());
		return new Result("success", null, dealerService.getDealers());
	}

	@RequestMapping(value = "/addDealer", method = RequestMethod.GET)
	public String addPrograms(Model model, HttpServletRequest request, HttpServletResponse response) {
		logger.debug("In addDealer");
		if (!sessionExists(request))
			return "login";

		return "addDealer";
	}
	
	@RequestMapping(value = "/dealerRoleInfo", method = RequestMethod.GET)
	public @ResponseBody Result getDealerAdminRoles(HttpServletRequest request, HttpServletResponse response) {
		logger.debug("In getDealerAdminRoles");
		Result opResult = null;
		if (!sessionExists(request)){
			opResult = new Result("failure", "Invalid Login", null);
		}else{
			List<RoleDO> roleDOList = dealerService.getDealerAdminRoles();
			opResult = new Result("success", "Dealer Role Info", roleDOList);
		}
		
		return opResult;
	}
	

	@RequestMapping(value = "/addDealer", method = RequestMethod.POST)
	public @ResponseBody Result saveOrEditDealer(@RequestBody DealerDO dealerDO, BindingResult result,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		logger.debug("In saveOrEditDealer user: "+dealerDO.getUserName());
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
				StringBuffer url = request.getRequestURL();
				String uri = request.getRequestURI();
				String appUrl = url.substring(0, url.length() - uri.length());
				logger.info("appUrl: "+appUrl);
				
				id = dealerService.saveDealer(dealerDO, getAccountDetails(request), false, appUrl);
			}catch (Exception e) {
		    	if (e instanceof DataIntegrityViolationException) {
		    		logger.error("Dealer already exists");
		    		throw new Exception("Dealer already exists");
		        } else {
		        	throw e;
		        }
		    }
			if(id > 0){
				opResult = new Result("success", "Dealer added successfully", id);
			}
			
		}
		
		return opResult;
	}
	
	
	@RequestMapping(value = "/editDealer", method = RequestMethod.POST)
	public @ResponseBody Result editDealer(@RequestBody DealerDO dealerDO, BindingResult result,
			HttpServletRequest request, HttpServletResponse response) {
		logger.debug("In editDealer user: "+dealerDO.getUserName());
		Result opResult = null;
		if (!sessionExists(request)){
			opResult = new Result("failure", "Invalid Login", null);
		}else{
			if (result.hasErrors()){
				opResult = new Result("failure", "Invalid dealer form field values", null);
			}
			
			StringBuffer url = request.getRequestURL();
			String uri = request.getRequestURI();
			String appUrl = url.substring(0, url.length() - uri.length());
			logger.info("appUrl: "+appUrl);
	
			long id = dealerService.editDealer(dealerDO, getAccountDetails(request), appUrl);
			if(id > 0){
				opResult = new Result("success", "Dealer edited successfully", null);
			}
			
		}
		
		return opResult;
	}
	
	@RequestMapping(value = "/dealer/{id}", method = RequestMethod.GET)
	public @ResponseBody Result getDealer(@PathVariable long id, HttpServletRequest request, HttpServletResponse response, Model model) {
		logger.debug("In getDealer");
		Result opResult = null;
		if (!sessionExists(request)){
			opResult = new Result("failure", "Invalid Login", null);
		}else{
			model.addAttribute("roleList", dealerService.getDealerAdminRoles());
			model.addAttribute("parentDealers", dealerService.getParentDealers());
			model.addAttribute("dealer", dealerService.getDealer(id));
			model.addAttribute("dealerUsers", dealerService.getDealerUsers(id));
			opResult = new Result("success", "Dealer Info", model);
		}
		
		return opResult;
	}
	
	@RequestMapping(value = "/dealerInfo", method = RequestMethod.GET)
	public @ResponseBody Result getDealers(HttpServletRequest request, HttpServletResponse response, Model model) {
		logger.debug("In getDealers");
		Result opResult = null;
		if (!sessionExists(request)){
			opResult = new Result("failure", "Invalid Login", null);
		}else{
			//List<DealerDO> dealerDOList = dealerService.findDealers(AggConstants.DEALER_ADMIN);
			List<DealerDO> dealerDOList = dealerService.findAllDealers();
			if(dealerDOList!=null)
				logger.debug("dealerDOList "+dealerDOList.size());
			model.addAttribute("dealerDOList", dealerDOList);
			opResult = new Result("success", "Dealer Info", model);
		}
		
		return opResult;
	}
	
	@RequestMapping(value = "/dealerInfo/{dealerId}", method = RequestMethod.GET)
	public @ResponseBody Result getDealers(@PathVariable long dealerId, HttpServletRequest request, HttpServletResponse response, Model model) {
		logger.debug("In getDealers");
		Result opResult = null;
		if (!sessionExists(request)){
			opResult = new Result("failure", "Invalid Login", null);
		}else{
			if(dealerId != 0){
				DealerDO dealerDO = dealerService.getDealer(dealerId);
				if(dealerDO != null){
					model.addAttribute("dealerName", dealerDO.getName());
				}
			}
			List<DealerDO> dealerDOList = dealerService.getDealers();
			model.addAttribute("dealerDOList", dealerDOList);
			opResult = new Result("success", "Dealer Info", model);
		}
		
		return opResult;
	}
	
	@RequestMapping(value = "/pendingDealerInfo", method = RequestMethod.GET)
	public @ResponseBody Result getPendingDealers(HttpServletRequest request, HttpServletResponse response) {
		logger.debug("In getPendingDealers");
		Result opResult = null;
		if (!sessionExists(request)){
			opResult = new Result("failure", "Invalid Login", null);
		}else{
			List<DealerDO> dealerDOList = dealerService.getPendingDealers();
			opResult = new Result("success", "Dealer Info", dealerDOList);
		}
		
		return opResult;
	}
	
	@RequestMapping(value = "/addLocation", method = RequestMethod.POST)
	public @ResponseBody Result saveOrEditLocation(@RequestBody LocationDO locationDO, BindingResult result,
			HttpServletRequest request, HttpServletResponse response) {
		logger.debug("In saveOrEditLocation title: "+locationDO.getTitle());
		Result opResult = null;
		if (!sessionExists(request)){
			opResult = new Result("failure", "Invalid Login", null);
		}else{
			if (result.hasErrors()){
				opResult = new Result("failure", "Invalid Location form field values", null);
			}
	
			long id = dealerService.saveLocation(locationDO);
			logger.info("locationId: "+id);
			if(id > 0){
				opResult = new Result("success", "Invalid Location form field values", null);
			}
			
		}
		
		return opResult;
	}
	
	@RequestMapping(value = "/dealerAndRoleInfo", method = RequestMethod.GET)
	public @ResponseBody Result getDealersAndRoles(HttpServletRequest request, HttpServletResponse response, Model model) {
		logger.debug("In getDealersAndRoles");
		Result opResult = null;
		if (!sessionExists(request)){
			opResult = new Result("failure", "Invalid Login", null);
		}else{
			model.addAttribute("dealerList", dealerService.getActiveDealers(getAccountDetails(request)));
			model.addAttribute("roleList", dealerService.getDealerRoles());
			opResult = new Result("success", "Dealer and Role Info", model);
		}
		
		return opResult;
	}
	
	@RequestMapping(value = "/locationInfo/{dealerId}", method = RequestMethod.GET)
	public @ResponseBody Result getDealerLocations(HttpServletRequest request, HttpServletResponse response, @PathVariable long dealerId) {
		logger.debug("In getDealerLocations with dealerId: "+dealerId);
		Result opResult = null;
		if (!sessionExists(request)){
			opResult = new Result("failure", "Invalid Login", null);
		}else{
			List<LocationDO> locationDOList = dealerService.getDealerLocations(dealerId);
			opResult = new Result("success", "Dealer Role Info", locationDOList);
		}
		
		return opResult;
	}
	
	@RequestMapping(value = "/addUser", method = RequestMethod.POST)
	public @ResponseBody Result saveOrEditDealerUser(@RequestBody UserDO userDO, BindingResult result,
			HttpServletRequest request, HttpServletResponse response) {
		logger.debug("In saveOrEditDealerUser userName: "+userDO.getUserName());
		Result opResult = null;
		if (!sessionExists(request)){
			opResult = new Result("failure", "Invalid Login", null);
		}else{
			if (result.hasErrors()){
				opResult = new Result("failure", "Invalid User form field values", null);
			}
	
			long id = dealerService.saveDealerUser(userDO, getAccountDetails(request));
			logger.info("accountId: "+id);
			if(id > 0){
				opResult = new Result("success", "Dealer User created successfully", null);
			}
			
		}
		
		return opResult;
	}
	
	@RequestMapping(value = "/dealerCountDetails", method = RequestMethod.GET)
	public @ResponseBody Result getDealerCountDetails(Model model, HttpServletRequest request, HttpServletResponse response) {
		logger.debug("In getDealerCountDetails method");
		Result opResult = null;
		if (!sessionExists(request)){
			opResult = new Result("failure", "Invalid Login", null);
		}else{
			long activeDealers = dealerService.getActiveDealerCount();
			long pendingDealers = dealerService.getPendingDealerCount();
			long terminatedDealers = dealerService.getTerminatedDealerCount();
			
			logger.info("activeDealers, pendingDealers & terminatedDealers: "+activeDealers+", "+pendingDealers+" & "+terminatedDealers);
			model.addAttribute("activeDealers", activeDealers);
			model.addAttribute("pendingDealers", pendingDealers);
			model.addAttribute("terminatedDealers", terminatedDealers);
			opResult = new Result("success", "Dealer information fetched successfully", model);
			
		}
		
		return opResult;
	}
	
	@RequestMapping(value = "/isUserNameExists", method = RequestMethod.POST)
	public @ResponseBody Result isUserNameExists(@RequestBody String userName, HttpServletRequest request, HttpServletResponse response, Model model) {
		logger.debug("In isUserNameExists with userName: "+userName);
		Result opResult = null;
		if (!sessionExists(request)){
			opResult = new Result("failure", "Invalid Login", null);
		}else{
			opResult = new Result("success", "Dealer User Info", dealerService.isUserNameExists(userName));
		}
		
		return opResult;
	}
}
