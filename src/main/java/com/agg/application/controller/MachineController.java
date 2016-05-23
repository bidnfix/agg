package com.agg.application.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.agg.application.model.DealerDO;
import com.agg.application.model.GroupDO;
import com.agg.application.model.MachineDO;
import com.agg.application.model.MachineInfoDO;
import com.agg.application.model.MachineModelDO;
import com.agg.application.model.MachineTypeDO;
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
		logger.info("Inside machineInfo()");
		List<MachineDO> machineInfoList = machineService.getmachineInfo();
		model.put("machineInfoList", machineInfoList);
		return new Result("success", null, model);	
	}
	
	@RequestMapping(value = "/addMachine", method = RequestMethod.GET, consumes = MediaType.ALL_VALUE)
	public @ResponseBody Result addMachine(ModelMap model, HttpServletResponse response) {
		logger.info("Inside addMachine()");
		List<ManufacturerDO> manufacturers = machineService.getManufacturerDetails();
		List<MachineTypeDO> machineTypes = machineService.getMachineTypes();
		List<GroupDO> groupList = machineService.getGroups();
		model.put("manufacturerList", manufacturers);
		model.put("machineTypeList", machineTypes);
		model.put("groupList", groupList);
		return new Result("success", null, model);	
	}
	
	/*@RequestMapping(value = "/machineType/{typeId}", method = RequestMethod.GET, consumes = MediaType.ALL_VALUE)
	public @ResponseBody Result machineType(ModelMap model, HttpServletResponse response, @PathVariable String typeId) {
		logger.info("Inside machineType() with typeId: "+typeId);
		if(typeId != null && !typeId.isEmpty()){
			List<ManufacturerDO> machineTypes = machineService.getMachineTypeById(Integer.valueOf(typeId));
			model.put("machineTypeList", machineTypes);
		}
		return new Result("success", null, model);	
	}*/
	
	@RequestMapping(value = "/machineModel/{typeId}", method = RequestMethod.GET, consumes = MediaType.ALL_VALUE)
	public @ResponseBody Result machineModel(ModelMap model, HttpServletResponse response, @PathVariable String typeId) {
		logger.info("Inside machineModel() with typeId: "+typeId);
		if(typeId != null && !typeId.isEmpty()){
			List<MachineModelDO> machineModels = machineService.getMachineModel(Integer.valueOf(typeId));
			model.put("machineModelList", machineModels);
		}
		return new Result("success", null, model);	
	}
	
	@RequestMapping(value = "/manfModel/{manfId}", method = RequestMethod.GET, consumes = MediaType.ALL_VALUE)
	public @ResponseBody Result getManfModel(ModelMap model, HttpServletResponse response, @PathVariable String manfId) {
		logger.info("Inside manfModel() with manfId: "+manfId);
		if(manfId != null && !manfId.isEmpty()){
			List<MachineInfoDO> machineModels = machineService.getManfModel(Integer.valueOf(manfId));
			model.put("machineModelList", machineModels);
		}
		return new Result("success", null, model);	
	}
	
	@RequestMapping(value = "/editMachine", method = RequestMethod.GET, consumes = MediaType.ALL_VALUE)
	public @ResponseBody Result editMachine(ModelMap model, HttpServletResponse response) {
		logger.info("Inside editMachine()");
		List<ManufacturerDO> manufacturers = machineService.getManufacturerDetails();
		model.put("manufacturerList", manufacturers);
		return new Result("success", null, model);
	}
	
	@RequestMapping(value = "/saveMachine", method = RequestMethod.POST)
	public @ResponseBody Result saveMachine(@RequestBody MachineDO machineDO, BindingResult result,
			HttpServletRequest request, HttpServletResponse response) {
		logger.debug("In saveOrEditMachine with groupId: "+machineDO.getGroupDO().getGroupId());
		Result opResult = null;
		/*if (!sessionExists(request)){
			opResult = new Result("failure", "Invalid Login", null);
		}else{
			if (result.hasErrors()){
				opResult = new Result("failure", "Invalid dealer form field values", null);
			}*/
	
			long id = machineService.saveMachineInfo(machineDO);
			if(id > 0){
				opResult = new Result("success", "Machine created successfully", null);
			}
			
		//}
		
		return opResult;
	}
	
	@RequestMapping(value = "/editMachine", method = RequestMethod.POST)
	public @ResponseBody Result editMachine(@RequestBody MachineDO machineDO, BindingResult result,
			HttpServletRequest request, HttpServletResponse response) {
		logger.debug("In saveOrEditMachine with groupId: "+machineDO.getGroupDO().getGroupId());
		Result opResult = null;
		/*if (!sessionExists(request)){
			opResult = new Result("failure", "Invalid Login", null);
		}else{
			if (result.hasErrors()){
				opResult = new Result("failure", "Invalid dealer form field values", null);
			}*/
	
			long id = machineService.editMachineInfo(machineDO);
			if(id > 0){
				opResult = new Result("success", "Invalid Machine form field values", null);
			}
			
		//}
		
		return opResult;
	}
	
	
	/*@RequestMapping(value = "/machine/{id}", method = RequestMethod.GET)
	public @ResponseBody Result getMachine(@PathVariable long id, HttpServletRequest request, HttpServletResponse response) {
		logger.debug("In getMachine");
		Result opResult = null;
		if (!sessionExists(request)){
			opResult = new Result("failure", "Invalid Login", null);
		}else{
			
			opResult = new Result("success", "Machine Info", machineService.getMachine(id));
		}
		
		return opResult;
	}*/
	
	@RequestMapping(value = "/machine/{id}", method = RequestMethod.GET)
	public @ResponseBody Result getMachine(ModelMap model, HttpServletRequest request, HttpServletResponse response, @PathVariable long id) {
		logger.debug("In getMachine");
		if (!sessionExists(request)){
			return new Result("failure", "Invalid Login", null);
		}else{
			
			MachineDO machine = machineService.getMachine(id);
			model.put("machine", machine);
			
			List<GroupDO> groupList = machineService.getGroups();
			model.put("groupList", groupList);
		}
		
		return new Result("success", null, model);
	}
	
}
