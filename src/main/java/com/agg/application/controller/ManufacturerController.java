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

import com.agg.application.model.MachineInfoDO;
import com.agg.application.model.ManufacturerDO;
import com.agg.application.model.Result;
import com.agg.application.service.ManufacturerService;

@RestController
@RequestMapping("/agg")
public class ManufacturerController extends BaseController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ManufacturerService manufacturerService;

	@RequestMapping(value = "/getManufacturers", method = RequestMethod.GET, consumes = MediaType.ALL_VALUE)
	public @ResponseBody Result getOEMWarrantyTiers(ModelMap model, HttpServletRequest request, 
			HttpServletResponse response) {
		logger.info("Inside getManufacturers()");
		Result opResult = null;
		if (!sessionExists(request)){
			opResult = new Result("failure", "Invalid Login", null);
		}else{
			List<ManufacturerDO> manfLst = manufacturerService.getManufacturers();
			model.put("manfLst", manfLst);
			opResult = new Result("success", null, model);
		}
		return opResult;	
	}
	

	@RequestMapping(value = "/saveManufacturer", method = RequestMethod.POST)
	public @ResponseBody Result saveManufacturer(@RequestBody ManufacturerDO manufacturerDO, BindingResult result,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		logger.debug("In saveManufacturer ");
		Result opResult = null;
		if (!sessionExists(request)){
			opResult = new Result("failure", "Invalid Login", null);
		}else{
	
		long id = 0;
			try
			{
				id = manufacturerService.saveManufacturer(manufacturerDO);
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
		
	@RequestMapping(value = "/getManufacturer/{id}", method = RequestMethod.GET, consumes = MediaType.ALL_VALUE)
	public @ResponseBody Result getManufacturer(ModelMap model, HttpServletRequest request, HttpServletResponse response, @PathVariable String id) {
		logger.info("Inside getManufacturer() with Id: "+id);
		Result opResult = null;
		if (!sessionExists(request)){
			opResult = new Result("failure", "Invalid Login", null);
		}else{
			if(id != null && !id.isEmpty()){
				ManufacturerDO manfDO = manufacturerService.getManufacturer(Long.valueOf(id));
				List<MachineInfoDO> machineInfoDOLst = manfDO.getMachineInfoDO();
				model.put("manfDO", manfDO);
				model.put("machineInfoDOLst", machineInfoDOLst);
			}
			opResult = new Result("success", null, model);
		}
		return opResult;	
	}
	/*
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
	}*/
}
