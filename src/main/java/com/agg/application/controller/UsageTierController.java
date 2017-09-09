package com.agg.application.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.MediaType;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.agg.application.model.Result;
import com.agg.application.model.UsageTierDO;
import com.agg.application.service.UsageTierService;

@RestController
@RequestMapping("/agg")
public class UsageTierController extends BaseController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private UsageTierService usageTierService;

	@RequestMapping(value = "/getUsageTiers", method = RequestMethod.GET, consumes = MediaType.ALL_VALUE)
	public @ResponseBody Result getUsageTiers(ModelMap model, HttpServletRequest request, 
			HttpServletResponse response) {
		logger.info("Inside getUsageTiers()");
		Result opResult = null;
		if (!sessionExists(request)){
			opResult = new Result("failure", "Invalid Login", null);
		}else{
			List<UsageTierDO> usageTierLst = usageTierService.getUsageTiers();
			model.put("usageTierLst", usageTierLst);
			opResult = new Result("success", null, model);
		}
		return opResult;	
	}
	
	
	@RequestMapping(value = "/saveUsageTier", method = RequestMethod.POST)
	public @ResponseBody Result saveUsageTier(@RequestBody UsageTierDO usageTierDO, BindingResult result,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		logger.debug("In saveUsageTier ");
		Result opResult = null;
		if (!sessionExists(request)){
			opResult = new Result("failure", "Invalid Login", null);
		}else{
	
		long id = 0;
			try
			{
				id = usageTierService.saveUsageTier(usageTierDO);
			}catch (Exception e) {
		    	if (e instanceof DataIntegrityViolationException) {
		    		logger.error("Usage Tier already exists");
		    		throw new Exception("Usage Tier already exists");
		        } else {
		        	throw e;
		        }
		    }
			//if(id > 0){
				opResult = new Result("success", "Usage Tier created successfully", null);
			//}
			
		}
		
		return opResult;
	}
	
	@RequestMapping(value = "/getUsageTier/{id}", method = RequestMethod.GET, consumes = MediaType.ALL_VALUE)
	public @ResponseBody Result getUsageTier(ModelMap model, HttpServletRequest request, HttpServletResponse response, @PathVariable String id) {
		logger.info("Inside getUsageTier() with Id: "+id);
		Result opResult = null;
		if (!sessionExists(request)){
			opResult = new Result("failure", "Invalid Login", null);
		}else{
			if(id != null && !id.isEmpty()){
				UsageTierDO usageTierDO = usageTierService.getUsageTier(Long.valueOf(id));
				model.put("usageTierDO", usageTierDO);
			}
			opResult = new Result("success", null, model);
		}
		return opResult;	
	}
	
	@RequestMapping(value = "/editUsageTier", method = RequestMethod.POST)
	public @ResponseBody Result editUsageTier(@RequestBody UsageTierDO usageTierDO, BindingResult result,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		logger.debug("In editUsageTier "+usageTierDO.getAdjFactor());
		Result opResult = null;
		if (!sessionExists(request)){
			opResult = new Result("failure", "Invalid Login", null);
		}else{
			if (result.hasErrors()){
				opResult = new Result("failure", "Invalid UsageTier form field values", null);
			}
			long id = 0;
	
			try
			{
				id = usageTierService.editUsageTier(usageTierDO);
			}catch (Exception e) {
		    	if (e instanceof DataIntegrityViolationException) {
		    		logger.error("UsageTier already exist");
		    		throw new Exception("UsageTier already exists");
		        } else {
		        	throw e;
		        }
		    }
			if(id > 0){
				opResult = new Result("success", "Invalid UsageTier form field values", null);
			}
			
		}
		
		return opResult;
	}
	
	@RequestMapping(value = "/deleteUsageTier", method = RequestMethod.POST)
	public @ResponseBody Result deleteUsageTier(@RequestBody Long id, BindingResult result,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		logger.debug("In deleteUsageTier "+id);
		Result opResult = null;
		if (!sessionExists(request)){
			opResult = new Result("failure", "Invalid Login", null);
		}else{
			if (result.hasErrors()){
				opResult = new Result("failure", "Invalid UsageTier form field values", null);
			}
			//long id = 0;
	
			try
			{
				id = usageTierService.deleteUsageTier(id);
			}catch (Exception e) {
		    	if (e instanceof DataIntegrityViolationException) {
		    		logger.error("Exception occured, contact system admin");
		    		throw new Exception("Exception occured, contact system admin");
		        } else {
		        	throw e;
		        }
		    }
			if(id > 0){
				opResult = new Result("success", "Invalid UsageTier form field values", null);
			}
			
		}
		
		return opResult;
	}
	
	@RequestMapping(value = "/usagetier/adjfactor/{meterHours}", method = RequestMethod.GET, consumes = MediaType.ALL_VALUE)
	public @ResponseBody Result getUsageTierAdjFactor(ModelMap model, HttpServletRequest request, HttpServletResponse response, 
			@PathVariable long meterHours) {
		logger.info("Inside getUsageTierAdjFactor() with meterHours: "+meterHours);
		Result opResult = null;
		if (!sessionExists(request)){
			opResult = new Result("failure", "Invalid Login", null);
		}else{
			double usageTierAdjFactor = usageTierService.getUsageTierAdjFactor(meterHours);
			model.put("usageTierAdjFactor", usageTierAdjFactor);
			opResult = new Result("success", null, model);
		}
		return opResult;	
	}
}
