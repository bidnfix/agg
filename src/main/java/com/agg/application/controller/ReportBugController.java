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

import com.agg.application.model.BugDO;
import com.agg.application.model.GroupDO;
import com.agg.application.model.MachineDO;
import com.agg.application.model.Result;
import com.agg.application.service.ReportBugService;

@RestController
@RequestMapping("/agg")
public class ReportBugController extends BaseController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ReportBugService reportBugService;

	@RequestMapping(value = "/postBug", method = RequestMethod.POST)
	public @ResponseBody Result saveOrEditBug(@RequestBody BugDO bug, BindingResult result,
			HttpServletRequest request, HttpServletResponse response) {
		logger.debug("In saveOrEditBug ");

		Long id = reportBugService.saveBug(bug);
		return new Result("success", null, id);
	}
	
	@RequestMapping(value = "/bugId", method = RequestMethod.GET, consumes = MediaType.ALL_VALUE)
	public @ResponseBody Result bugId(ModelMap model, HttpServletResponse response) {
		logger.info("Inside bugId()");
		Integer bugId = reportBugService.getBugId();
		model.put("bugId", bugId+1);
		return new Result("success", null, model);	
	}
	
	@RequestMapping(value = "/getBugInfo", method = RequestMethod.GET, consumes = MediaType.ALL_VALUE)
	public @ResponseBody Result bugInfo(ModelMap model, HttpServletResponse response) {
		logger.debug("In getBugInfo ");

		List<BugDO> bugLists = reportBugService.getBugs();
		model.put("bugDOList", bugLists);
		return new Result("success", null, bugLists);
	}
	
	@RequestMapping(value = "/bug/{id}", method = RequestMethod.GET)
	public @ResponseBody Result getBug(ModelMap model, HttpServletRequest request, HttpServletResponse response, @PathVariable long id) {
		logger.debug("In getMachine");
		if (!sessionExists(request)){
			return new Result("failure", "Invalid Login", null);
		}else{
			
			BugDO bugDO = reportBugService.getBug(id);
			model.put("bug", bugDO);
			
		}
		return new Result("success", null, model);
	}
	
}
