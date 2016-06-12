package com.agg.application.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.agg.application.model.Result;
import com.agg.application.model.UserDO;
import com.agg.application.service.DealerService;
import com.agg.application.service.UserService;

@RestController
@RequestMapping("/agg")
public class UserController extends BaseController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private UserService userService;
	
	@Autowired
	private DealerService dealerService;

	@RequestMapping(value = "/userInfo", method = RequestMethod.GET)
	public Result getDealers(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) {
		Result opResult = null;
		if (!sessionExists(request)){
			opResult = new Result("failure", "Invalid Login", null);
		}else{
			opResult = new Result("success", null, userService.getUsers(getAccountDetails(request)));
		}
		
		return opResult;
	}

	/*@RequestMapping(value = "/addUser", method = RequestMethod.POST)
	public @ResponseBody Result saveOrEditDealerUser(@RequestBody UserDO userDO, BindingResult result,
			HttpServletRequest request, HttpServletResponse response) {
		logger.debug("In saveOrEditDealerUser userName: "+userDO.getUserName());
		Result opResult = null;
		if (!sessionExists(request)){
			opResult = new Result("failure", "Invalid Login", null);
		}else{
			if (result.hasErrors()){
				opResult = new Result("failure", "Invalid User form field values", null);
			}
	
			long id = userService.saveDealerUser(userDO, getAccountDetails(request));
			logger.info("accountId: "+id);
			if(id > 0){
				opResult = new Result("success", "Dealer User created successfully", null);
			}
			
		}
		
		return opResult;
	}*/
	
	@RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
	public @ResponseBody Result getUser(@PathVariable long id, HttpServletRequest request, HttpServletResponse response, Model model) {
		logger.debug("In getUser");
		Result opResult = null;
		if (!sessionExists(request)){
			opResult = new Result("failure", "Invalid Login", null);
		}else{
			model.addAttribute("dealerList", dealerService.getDealers());
			model.addAttribute("roleList", dealerService.getUserRoles(id));
			model.addAttribute("user", userService.getUser(id));
			opResult = new Result("success", "User Info", model);
		}
		
		return opResult;
	}
	
	@RequestMapping(value = "/editUser", method = RequestMethod.POST)
	public @ResponseBody Result editUser(@RequestBody UserDO userDO, BindingResult result,
			HttpServletRequest request, HttpServletResponse response) {
		logger.debug("In editUser userName: "+userDO.getUserName());
		Result opResult = null;
		if (!sessionExists(request)){
			opResult = new Result("failure", "Invalid Login", null);
		}else{
			if (result.hasErrors()){
				opResult = new Result("failure", "Invalid user form field values", null);
			}
	
			long id = userService.editUser(userDO, getAccountDetails(request));
			if(id > 0){
				opResult = new Result("success", "User edited successfully", null);
			}
			
		}
		
		return opResult;
	}
}
