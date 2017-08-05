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

import com.agg.application.model.OEMWarrantyTierDO;
import com.agg.application.model.Result;
import com.agg.application.service.OEMWarrantyTierService;

@RestController
@RequestMapping("/agg")
public class OEMWarrantyTierController extends BaseController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private OEMWarrantyTierService oemWarrantyTierService;

	@RequestMapping(value = "/getOEMWarrantyTiers", method = RequestMethod.GET, consumes = MediaType.ALL_VALUE)
	public @ResponseBody Result getOEMWarrantyTiers(ModelMap model, HttpServletRequest request, 
			HttpServletResponse response) {
		logger.info("Inside getOEMWarrantyTiers()");
		Result opResult = null;
		if (!sessionExists(request)){
			opResult = new Result("failure", "Invalid Login", null);
		}else{
			List<OEMWarrantyTierDO> oemWarrantyTierLst = oemWarrantyTierService.getOEMWarrantyTiers();
			model.put("oemWarrantyTierLst", oemWarrantyTierLst);
			opResult = new Result("success", null, model);
		}
		return opResult;	
	}
	
	
	@RequestMapping(value = "/saveOEMWarrantyTier", method = RequestMethod.POST)
	public @ResponseBody Result saveOEMWarrantyTier(@RequestBody OEMWarrantyTierDO oemWarrantyTierDO, BindingResult result,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		logger.debug("In saveOEMWarrantyTier ");
		Result opResult = null;
		if (!sessionExists(request)){
			opResult = new Result("failure", "Invalid Login", null);
		}else{
	
		long id = 0;
			try
			{
				id = oemWarrantyTierService.saveOEMWarrantyTier(oemWarrantyTierDO);
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
	
	@RequestMapping(value = "/getOEMWarrantyTier/{id}", method = RequestMethod.GET, consumes = MediaType.ALL_VALUE)
	public @ResponseBody Result getOEMWarrantyTierDOTier(ModelMap model, HttpServletRequest request, HttpServletResponse response, @PathVariable String id) {
		logger.info("Inside getOEMWarrantyTier() with Id: "+id);
		Result opResult = null;
		if (!sessionExists(request)){
			opResult = new Result("failure", "Invalid Login", null);
		}else{
			if(id != null && !id.isEmpty()){
				OEMWarrantyTierDO oemWarrantyTierDO = oemWarrantyTierService.getOEMWarrantyTier(Long.valueOf(id));
				model.put("oemWarrantyTierDO", oemWarrantyTierDO);
			}
			opResult = new Result("success", null, model);
		}
		return opResult;	
	}
	
	@RequestMapping(value = "/editOEMWarrantyTier", method = RequestMethod.POST)
	public @ResponseBody Result editOEMWarrantyTier(@RequestBody OEMWarrantyTierDO oemWarrantyTierDO, BindingResult result,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		logger.debug("In editOEMWarrantyTier "+oemWarrantyTierDO.getAdjFactor());
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
				id = oemWarrantyTierService.editOEMWarrantyTier(oemWarrantyTierDO);
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
}
