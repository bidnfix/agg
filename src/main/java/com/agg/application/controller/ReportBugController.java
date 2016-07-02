package com.agg.application.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.agg.application.model.BugDO;
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
		logger.debug("In addPrograms ");

		Long id = reportBugService.saveBug(bug);
		return new Result("success", null, id);
	}
	
}
