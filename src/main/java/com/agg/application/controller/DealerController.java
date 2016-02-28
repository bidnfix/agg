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

import com.agg.application.model.DealerDO;
import com.agg.application.service.DealerService;

@Controller
@RequestMapping("/agg")
public class DealerController extends BaseController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private DealerService dealerService;

	@RequestMapping(value = "/dealers", method = RequestMethod.GET)
	public String getDealers(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) {

		if (!sessionExists(request))
			return "login";
		model.put("dealers", dealerService.getDealers());
		return "dealers";
	}

	@RequestMapping(value = "/addDealer", method = RequestMethod.GET)
	public String addPrograms(Model model, HttpServletRequest request, HttpServletResponse response) {
		logger.debug("In addDealer");
		if (!sessionExists(request))
			return "login";

		return "addDealer";
	}

	@RequestMapping(value = "/addDealer", method = RequestMethod.POST)
	public String saveOrEditDealer(@Validated @ModelAttribute DealerDO dealerDO, BindingResult result,
			HttpServletRequest request, HttpServletResponse response) {
		logger.debug("In addPrograms ");
		if (!sessionExists(request))
			return "login";

		if (result.hasErrors())
			return "redirect:/addDealer";

		long id = dealerService.saveDealer(dealerDO);
		return "redirect:/dealer/" + id;
	}
	
	@RequestMapping(value = "/dealer/{id}", method = RequestMethod.GET)
	public String getOneProgram(@PathVariable long id, Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) {
		logger.debug("In getOneProgram ");
		if (!sessionExists(request))
			return "login";

		model.put("dealer", dealerService.getDealer(id));
		return "redirect:/dealers";
	}
}
