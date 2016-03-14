package com.agg.application.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.agg.application.model.ManufacturerDO;
import com.agg.application.model.Result;
import com.agg.application.service.MachineService;

@RestController
@RequestMapping("/agg")
public class MachineController extends BaseController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private MachineService machineService;

	@RequestMapping(value = "/machineInfo", method = RequestMethod.GET, consumes = MediaType.ALL_VALUE)
	public @ResponseBody Result machineInfo(ModelMap model, HttpServletResponse response) {
		logger.info("Inside machineIfo()");
		List<ManufacturerDO> manufacturers = machineService.getManufacturerDetails();
		model.put("manufacturerList", manufacturers);
		return new Result("success", null, model);	
	}
	
	@RequestMapping(value = "/machineType/{typeId}", method = RequestMethod.GET, consumes = MediaType.ALL_VALUE)
	public @ResponseBody Result machineType(ModelMap model, HttpServletResponse response, @PathVariable String typeId) {
		logger.info("Inside machineType() with typeId: "+typeId);
		if(typeId != null && !typeId.isEmpty()){
			List<ManufacturerDO> machineTypes = machineService.getMachineTypeById(Integer.valueOf(typeId));
			model.put("machineTypeList", machineTypes);
		}
		return new Result("success", null, model);	
	}
}
