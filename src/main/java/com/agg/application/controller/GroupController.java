package com.agg.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.agg.application.model.Result;
import com.agg.application.service.GroupService;

@RestController
@RequestMapping("/agg")
public class GroupController extends BaseController {

	@Autowired
	private GroupService groupService;
	
	@RequestMapping(value = "/groups", method = RequestMethod.GET)
	public Result getGroups() {
		return new Result("success", null, groupService.getGroups());
	}
}
