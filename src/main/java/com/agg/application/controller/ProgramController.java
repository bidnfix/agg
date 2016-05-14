package com.agg.application.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.agg.application.model.ProgramDO;
import com.agg.application.model.Result;
import com.agg.application.service.ProgramService;

@RestController
@RequestMapping("/agg")
public class ProgramController extends BaseController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ProgramService programService;

	@RequestMapping(value = "/programs", method = RequestMethod.GET)
	public @ResponseBody Result listPrograms(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) {

/*		if (!sessionExists(request))
			return "login";*/
		model.put("programs", programService.getPrograms());
		return new Result("success", null, model);	
	}

	@RequestMapping(value = "/addPrograms", method = RequestMethod.GET)
	public String addPrograms(Model model, HttpServletRequest request, HttpServletResponse response) {
		logger.debug("In addPrograms ");
		if (!sessionExists(request))
			return "login";

		model.addAttribute("programForm", new ProgramDO());
		return "addPrograms";
	}

	@RequestMapping(value = "/postPrograms", method = RequestMethod.POST)
	public @ResponseBody Result saveOrEditPrograms(@RequestBody ProgramDO program, BindingResult result,
			HttpServletRequest request, HttpServletResponse response) {
		logger.debug("In addPrograms ");

		Long id = programService.saveProgram(program);
		return new Result("success", null, id);
	}
	
	@RequestMapping(value = "/programs/{id}", method = RequestMethod.GET)
	public @ResponseBody Result getOneProgram(@PathVariable Long id, Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) {
		logger.debug("In getOneProgram ");
		model.put("program", programService.getProgram(id));
		return new Result("success", null, model);	
	}
	
	@RequestMapping(value = "/programs/{id}", method = RequestMethod.DELETE)
	public @ResponseBody Result deleteProgram(@PathVariable Long id, Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) {
		logger.debug("In getOneProgram ");
		programService.deleteProgram(id);
		return new Result("success", null, true);	
	}
}
