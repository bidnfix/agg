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
			List<UsageTierDO> usageTierLst = usageTierService.getUsageTier();
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
	
	/*
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
	*/
}
