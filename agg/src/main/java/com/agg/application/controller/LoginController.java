package com.agg.application.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.agg.application.model.LoginForm;
import com.agg.application.model.Result;
import com.agg.application.service.LoginService;

@RestController
@RequestMapping("/agg/api/v1")
public class LoginController {

	@Autowired
	private LoginService loginService;
	
	@RequestMapping(value = "/login", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Result login(@Valid @RequestBody LoginForm loginForm) {
		return new Result("success", null, "logged in");
	}
	
	@RequestMapping(method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Result test() {
		System.out.println("in test");
		return new Result("success", null, loginService.getUserDetails());
	}
}
