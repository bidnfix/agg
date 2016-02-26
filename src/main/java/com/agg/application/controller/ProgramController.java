package com.agg.application.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.agg.application.model.Program;
import com.agg.application.service.ProgramService;

@Controller
public class ProgramController extends BaseController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ProgramService programService;

	@RequestMapping(value = "/programs", method = RequestMethod.GET)
	public String listPrograms(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) {

		if (!sessionExists(request))
			return "login";
		model.put("programs", programService.getPrograms());
		return "programs";
	}

	@RequestMapping(value = "/addPrograms", method = RequestMethod.GET)
	public String addPrograms(Model model, HttpServletRequest request, HttpServletResponse response) {
		logger.debug("In addPrograms ");
		if (!sessionExists(request))
			return "login";

		model.addAttribute("programForm", new Program());
		return "addPrograms";
	}

	@RequestMapping(value = "/postPrograms", method = RequestMethod.GET)
	public String saveOrEditPrograms(@ModelAttribute("programForm") @Validated Program program, BindingResult result,
			HttpServletRequest request, HttpServletResponse response) {
		logger.debug("In addPrograms ");
		if (!sessionExists(request))
			return "login";

		if (result.hasErrors())
			return "redirect:/addPrograms";

		int id = programService.saveProgram(program);
		return "redirect:/programs/" + id;
	}
	
	@RequestMapping(value = "/programs/{id}", method = RequestMethod.GET)
	public String getOneProgram(@PathVariable int id, Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) {
		logger.debug("In getOneProgram ");
		if (!sessionExists(request))
			return "login";

		model.put("program", programService.getProgram(id));
		return "/programs";
	}
}
