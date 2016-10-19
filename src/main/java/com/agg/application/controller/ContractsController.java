/**
 * htamada
 */
package com.agg.application.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.agg.application.model.ContractDO;
import com.agg.application.model.Result;
import com.agg.application.service.ContractsService;
import com.agg.application.utils.Util;

/**
 * @author htamada
 *
 */

@RestController
@RequestMapping("/agg")
public class ContractsController extends BaseController{
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private ContractsService contractService ;
	
	@RequestMapping(value = "/contract", method = RequestMethod.POST)
	public @ResponseBody Result addContract(@RequestBody ContractDO contractDO, BindingResult result,
			HttpServletRequest request, HttpServletResponse response) {
		Result opResult = null;
		if (!sessionExists(request)){
			opResult = new Result("failure", "Invalid Login", null);
		}else{
			Calendar cal = Calendar.getInstance();
			cal.setTime(new Date());
			cal.set(Calendar.DAY_OF_MONTH, cal.get(Calendar.DAY_OF_MONTH)-6);
			Date inceptionDate = cal.getTime();
			
			cal.setTime(new Date());
			cal.set(Calendar.DAY_OF_MONTH, cal.get(Calendar.DAY_OF_MONTH)+6);
			Date expirationDate = cal.getTime();
			
			contractDO.setInceptionDate(inceptionDate);
			contractDO.setExpirationDate(expirationDate);
			contractDO.setLastUpdatedDate(new Date());
			
			long contractID = contractService.saveContract(contractDO);
			opResult = new Result("success", "", contractID);
		}
		return opResult;
	}
	
	@RequestMapping(value = "/contracts", method = RequestMethod.GET)
	public @ResponseBody Result getContracts(HttpServletRequest request, HttpServletResponse response, Model model) {
		Result opResult = null;
		if (!sessionExists(request)){
			opResult = new Result("failure", "Invalid Login", null);
		}else{
			opResult = new Result("success", "", model.addAttribute(contractService.getAllContracts(getAccountDetails(request))));
		}
		return opResult;
	}
	
	@RequestMapping(value = "/contracts/count/{contractId}", method = RequestMethod.GET, consumes = MediaType.ALL_VALUE)
	public @ResponseBody Result getContractsCount(@PathVariable String contractId, HttpServletRequest request, HttpServletResponse response, Model model) {
		logger.debug("Inside getContractsCount with id: "+contractId);
		Result opResult = null;
		if (!sessionExists(request)){
			opResult = new Result("failure", "Invalid Login", null);
		}else{
			int count = contractService.getContractsCount(contractId);
			opResult = new Result("success", "", model.addAttribute("count", count));
		}
		return opResult;
	}
	@RequestMapping(value = "/contractInfo/{id}/{contractId}", method = RequestMethod.GET)
	public @ResponseBody Result viewContract(@PathVariable long id, @PathVariable String contractId, HttpServletRequest request, HttpServletResponse response, Model model) {
		logger.debug("Inside viewContract with id: "+id+" and code: "+contractId);
		Result opResult = null;
		if (!sessionExists(request)){
			opResult = new Result("failure", "Invalid Login", null);
		}else{
			opResult = new Result("success", "", model.addAttribute(contractService.getContract(id, contractId)));
		}
		return opResult;
	}
	
	@RequestMapping(value = "/contracts/machineserialno/search/{term}", method = RequestMethod.GET, consumes = MediaType.ALL_VALUE)
	public @ResponseBody Result getContractsByMachineSerialNo(@PathVariable String term, HttpServletRequest request, HttpServletResponse response, Model model) {
		Result result = null;
		if (!sessionExists(request)){
			result = new Result("failure", "Invalid Login", null);
		}else{
			if(Util.isNotEmptyString(term)){
				List<ContractDO> responseDO = contractService.getAllContractsByMachineSerialNo(term);
				List<Map<String, Object>> contractDOList = formatGetContracts(responseDO);
				result = new Result("success", "", model.addAttribute("contractDOList",contractDOList));
			}else{
				result = new Result("failure", "invalid search term", null);
			}
		}
		return result;
	}
	
	@RequestMapping(value = "/updateContract", method = RequestMethod.POST)
	public @ResponseBody Result updateContract(@RequestBody ContractDO contractDO, HttpServletRequest request, HttpServletResponse response, Model model) {
		logger.debug("Inside updateContract with code: "+contractDO.getContractId());
		Result opResult = null;
		if (!sessionExists(request)){
			opResult = new Result("failure", "Invalid Login", null);
		}else{
			opResult = new Result("success", "", contractService.updateContract(contractDO));
		}
		return opResult;
	}
	
	private List<Map<String, Object>> formatGetContracts(final List<ContractDO> contractsList){
		List<Map<String, Object>> responseList = new ArrayList<>();
		for(ContractDO item : contractsList){
			Map<String, Object> itemMap = new HashMap<>();
			itemMap.put("contractID", item.getContractId());
			itemMap.put("machineSerialNo", item.getMachineSerialNo());
			itemMap.put("manfactureName", item.getManufacturerDO().getName());
			itemMap.put("machineModel", item.getMachineModel());
			itemMap.put("coverageType", item.getCoverageType());
			itemMap.put("expirationDate", item.getExpirationDate());
			itemMap.put("usageHoursCovered", item.getExpirationUsageHours());
			itemMap.put("lol", item.getLol());
			itemMap.put("availableLol", item.getAvailabeLol());
			itemMap.put("deductible", item.getDeductible());
			responseList.add(itemMap);
		}
		return responseList;
	}
}
