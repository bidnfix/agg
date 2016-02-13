package com.agg.application.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.agg.application.model.LoginForm;
import com.agg.application.model.Result;
import com.agg.application.service.LoginService;

@Controller
@RequestMapping("/agg/api/v1")
public class LoginController {

	@Autowired
	private LoginService loginService;
	
	@RequestMapping(value = "/login", method = RequestMethod.POST, consumes = MediaType.ALL_VALUE)
	public @ResponseBody Result login(@RequestBody LoginForm loginForm,
            BindingResult result, Model model) throws Exception {
		return new Result("success", null, loginService.authenticateUser(loginForm));
	}
	
	@RequestMapping(value = "/welcome", method = RequestMethod.GET)
	public String welcome(Map<String, Object> model) {
		return "index";
	}
	
	@RequestMapping(method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Result test() {
		System.out.println("in test");
		return new Result("success", null, loginService.getUserDetails());
	}
}
