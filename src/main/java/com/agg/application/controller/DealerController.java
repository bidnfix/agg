package com.agg.application.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
	public String getDealers(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) {

		if (!sessionExists(request))
			return "login";
		model.put("dealers", dealerService.getDealers());
		return "dealers";
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
			HttpServletRequest request, HttpServletResponse response) {
		logger.debug("In saveOrEditDealer user: "+dealerDO.getUserName());
		Result opResult = null;
		if (!sessionExists(request)){
			opResult = new Result("failure", "Invalid Login", null);
		}else{
			if (result.hasErrors()){
				opResult = new Result("failure", "Invalid dealer form field values", null);
			}
	
			long id = dealerService.saveDealer(dealerDO, getAccountDetails(request));
			if(id > 0){
				opResult = new Result("success", "Invalid dealer form field values", null);
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
	
			long id = dealerService.editDealer(dealerDO, getAccountDetails(request));
			if(id > 0){
				opResult = new Result("success", "Invalid dealer form field values", null);
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
			model.addAttribute("roleList", dealerService.getDealerRoles(id));
			model.addAttribute("dealer", dealerService.getDealer(id));
			opResult = new Result("success", "Dealer Info", model);
		}
		
		return opResult;
	}
	
	@RequestMapping(value = "/dealerInfo", method = RequestMethod.GET)
	public @ResponseBody Result getDealers(HttpServletRequest request, HttpServletResponse response) {
		logger.debug("In getDealers");
		Result opResult = null;
		if (!sessionExists(request)){
			opResult = new Result("failure", "Invalid Login", null);
		}else{
			List<DealerDO> dealerDOList = dealerService.getDealers();
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
			model.addAttribute("dealerList", dealerService.getAdminDealers());
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
			logger.info("locationId: "+id);
			if(id > 0){
				opResult = new Result("success", "Dealer User created successfully", null);
			}
			
		}
		
		return opResult;
	}
}
