package com.agg.application.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.agg.application.model.AccountDO;
import com.agg.application.model.LoginForm;
import com.agg.application.model.ManufacturerDO;
import com.agg.application.model.Result;
import com.agg.application.service.LoginService;
import com.agg.application.service.MachineService;

@Controller
@RequestMapping("/agg")
public class LoginController extends BaseController {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private LoginService loginService;
	
	@Autowired
	private MachineService machineService;
	

	@RequestMapping(value = "/login", method = RequestMethod.POST, consumes = MediaType.ALL_VALUE)
	public @ResponseBody Result login(@RequestBody LoginForm loginForm, BindingResult result, Model model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		logger.debug("LoginForm username: [{}]", loginForm.getUsername());
		AccountDO account = loginService.authenticateUser(loginForm);
		logger.debug("Account [{}]", account);
		request.getSession().setAttribute("user", account);
		return new Result("success", null, account);
	}

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String home(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) {
		AccountDO account = (AccountDO) request.getSession().getAttribute("user");
		if (account == null)
			return "login";
		return "home";
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String login(Map<String, Object> model) {
		return "redirect:/agg/login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET, consumes = MediaType.ALL_VALUE)
	public String getLogin(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (sessionExists(request))
			return "redirect:/agg/home";
		return "login";
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET, consumes = MediaType.ALL_VALUE)
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		removeSession(request);
		return "login";
	}
	
	@RequestMapping(value = "/machineInfo", method = RequestMethod.GET, consumes = MediaType.ALL_VALUE)
	public String machineIfo(ModelMap model, HttpServletResponse response) {
		
		List<ManufacturerDO> manufacturers = machineService.getManufacturerDetails();
		model.put("manufacturerList", manufacturers);
		
		return "machineInfo";
	}
	
	
}
