package com.agg.application.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

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

import com.agg.application.model.MachineInfoDO;
import com.agg.application.model.ProgramDO;
import com.agg.application.model.QuoteDO;
import com.agg.application.model.Result;
import com.agg.application.service.DealerService;
import com.agg.application.service.MachineService;
import com.agg.application.service.ProgramService;
import com.agg.application.service.QuoteService;

@RestController
@RequestMapping("/agg")
public class ProgramController extends BaseController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ProgramService programService;
	
	@Autowired
	private MachineService machineService;
	
	@Autowired
	private DealerService dealerService;
	
	@Autowired
	private QuoteService quoteService;

	@RequestMapping(value = "/programs", method = RequestMethod.GET)
	public @ResponseBody Result listPrograms(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) {

/*		if (!sessionExists(request))
			return "login";*/
		Result opResult = null;
		if (!sessionExists(request)){
			opResult = new Result("failure", "Invalid Login", null);
		}else{
			model.put("programs", programService.getPrograms(getAccountDetails(request)));
			opResult = new Result("success", null, model);
		}
		return opResult;
	}
	
	
	@RequestMapping(value = "/programAsDealer", method = RequestMethod.GET)
	public @ResponseBody Result listProgramsForDealer(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) {
		logger.info("Inside listProgramsForDealer()");
		Result opResult = null;
		if (!sessionExists(request)){
			opResult = new Result("failure", "Invalid Login", null);
		}else{
			model.put("programList", programService.getPrograms(getAccountDetails(request)));
			model.put("useOfEquipmentDOList", quoteService.getUseOfEquipmentDetails());
			opResult = new Result("success", null, model);
		}
		return opResult;
	}

	/*@RequestMapping(value = "/addPrograms", method = RequestMethod.GET)
	public String addPrograms(Model model, HttpServletRequest request, HttpServletResponse response) {
		logger.debug("In addPrograms ");
		if (!sessionExists(request))
			return "login";

		model.addAttribute("programForm", new ProgramDO());
		return "addPrograms";
	}*/
	
	@RequestMapping(value = "/addPrograms", method = RequestMethod.GET, consumes = MediaType.ALL_VALUE)
	public @ResponseBody Result addPrograms(ModelMap model, HttpServletResponse response, HttpServletRequest request) {
		logger.info("Inside addPrograms()");
		Result opResult = null;
		if (!sessionExists(request)){
			opResult = new Result("failure", "Invalid Login", null);
		}else{
			//List<GroupDO> groupList = machineService.getGroups();
			model.addAttribute("manufacturerList", machineService.getManufacturerDetails());
			model.addAttribute("dealerList", dealerService.getActiveDealers(getAccountDetails(request)));
			//model.put("groupList", groupList);
			opResult = new Result("success", null, model);
		}
		return opResult;	
	}

	@RequestMapping(value = "/postPrograms", method = RequestMethod.POST)
	public @ResponseBody Result saveOrEditPrograms(@RequestBody ProgramDO program, BindingResult result,
			HttpServletRequest request, HttpServletResponse response) {
		logger.debug("In addPrograms ");
		
		Result opResult = null;
		if (!sessionExists(request)){
			opResult = new Result("failure", "Invalid Login", null);
		}else{
			Long id = programService.saveProgram(program);
			opResult = new Result("success", null, id);
		}
		return opResult;
	}
	
	@RequestMapping(value = "/saveProgramsAsDealr", method = RequestMethod.POST)
	public @ResponseBody Result saveProgramsAsDealr(@RequestBody QuoteDO quoteDO, BindingResult result,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		logger.debug("In saveProgramsAsDealr");
		
		Result opResult = null;
		if (!sessionExists(request)){
			opResult = new Result("failure", "Invalid Login", null);
		}else{
			StringBuffer url = request.getRequestURL();
			String uri = request.getRequestURI();
			String appUrl = url.substring(0, url.length() - uri.length());
	
			QuoteDO newQuoteDO = programService.saveProgramsAsDealr(quoteDO, getAccountDetails(request), appUrl);
			logger.debug("Quote id :"+newQuoteDO.getQuoteId());
			
			opResult = new Result("success", null, newQuoteDO);
		}
		return opResult;
	}
	
	@RequestMapping(value = "/programs/{id}", method = RequestMethod.GET)
	public @ResponseBody Result getOneProgram(@PathVariable Long id, Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) {
		logger.debug("In getOneProgram ");
		Result opResult = null;
		if (!sessionExists(request)){
			opResult = new Result("failure", "Invalid Login", null);
		}else{
			ProgramDO programDO = programService.getProgram(id, getAccountDetails(request));
			model.put("program", programDO);
			model.put("manufacturerList", machineService.getManufacturerDetails());
			model.put("dealerList", dealerService.getActiveDealers(getAccountDetails(request)));
			if(programDO != null && programDO.getManufacturerDO() != null){
				model.put("machineInfoList", machineService.getManfModel(programDO.getManufacturerDO().getId()));
			}
			
			opResult = new Result("success", null, model);
		}
		return opResult;
	}
	
	@RequestMapping(value = "/programs/{id}", method = RequestMethod.DELETE)
	public @ResponseBody Result deleteProgram(@PathVariable Long id, Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) {
		logger.debug("In getOneProgram ");
		Result opResult = null;
		if (!sessionExists(request)){
			opResult = new Result("failure", "Invalid Login", null);
		}else{
			programService.deleteProgram(id);
			opResult = new Result("success", null, true);
		}
		return opResult;
	}
	
	@RequestMapping(value = "/editProgram", method = RequestMethod.POST)
	public @ResponseBody Result editProgram(@RequestBody ProgramDO programDO, BindingResult result,
			HttpServletRequest request, HttpServletResponse response) {
		logger.debug("In saveOrEditMachine with groupId: "+programDO.getPrId());
		Result opResult = null;
		if (!sessionExists(request)){
			opResult = new Result("failure", "Invalid Login", null);
		}else{
			/*if (result.hasErrors()){
				opResult = new Result("failure", "Invalid dealer form field values", null);
			}*/
	
			long id = programService.editProgram(programDO);
			if(id > 0){
				opResult = new Result("success", "Invalid Machine form field values", null);
			}
			
		}
		
		return opResult;
	}
	
	@RequestMapping(value = "/modelByCode/{modelId}", method = RequestMethod.GET, consumes = MediaType.ALL_VALUE)
	public @ResponseBody Result getModelByCode(ModelMap model, HttpServletRequest request, HttpServletResponse response, @PathVariable String modelId) {
		logger.info("Inside getModelByCode()");
		
		List<String> machineIdLst = null;
		List<Long> machineIdLstLng = null;
		Result opResult = null;
		if (!sessionExists(request)){
			opResult = new Result("failure", "Invalid Login", null);
		}else{
			
			if(modelId != null && !modelId.isEmpty() && modelId.matches("[0-9, /,]+")){
				
				machineIdLst = Arrays.asList(modelId.split(","));
				
				logger.debug("Model Ids "+machineIdLst);
				
				if(machineIdLst !=null)
				{
					machineIdLstLng = new ArrayList<Long>();
					for(String modelcode:machineIdLst)
					{
						machineIdLstLng.add(Long.parseLong(modelcode));
					}
								
					List<MachineInfoDO> machineModels = programService.getModelByCodes(machineIdLstLng);
					
					if(machineModels == null)
					{
						opResult = new Result("failure", "Model code does not belong to same Manufacturer", null);
					}
					else
					{
						model.put("machineModelList", machineModels);
						opResult = new Result("success", null, model);
					}
				}
				
				else
				{
					opResult = new Result("failure", "Model code empty", null);
				}
			}
			else{
				opResult = new Result("failure", "Enter only number", null);
			}
			
		}
		return opResult;	
	}
}
