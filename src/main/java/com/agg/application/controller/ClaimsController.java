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
import com.agg.application.model.ManufacturerDO;
import com.agg.application.model.QuoteDO;
import com.agg.application.model.Result;
import com.agg.application.service.ClaimsService;
import com.agg.application.service.MachineService;

@RestController
@RequestMapping("/agg")
public class ClaimsController extends BaseController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ClaimsService claimsService;

	@RequestMapping(value = "/claimsInfo", method = RequestMethod.GET, consumes = MediaType.ALL_VALUE)
	public @ResponseBody Result claimsInfo(ModelMap model, HttpServletResponse response) {
		logger.info("Inside claimsInfo()");
		List<QuoteDO> quoteInfoList = claimsService.getClaimsInfo();
		logger.info("quoteInfoList size: "+quoteInfoList.size());
		model.put("quoteInfoList", quoteInfoList);
		return new Result("success", null, model);	
	}
	
	@RequestMapping(value = "/editClaim/{claimId}", method = RequestMethod.GET, consumes = MediaType.ALL_VALUE)
	public @ResponseBody Result machineModel(ModelMap model, HttpServletResponse response, @PathVariable String claimId) {
		logger.info("Inside machineModel() with typeId: "+claimId);
		if(claimId != null && !claimId.isEmpty()){
			//List<MachineModelDO> machineModels = machineService.getMachineModel(Integer.valueOf(typeId));
			//model.put("machineModelList", machineModels);
		}
		return new Result("success", null, model);	
	}
}
