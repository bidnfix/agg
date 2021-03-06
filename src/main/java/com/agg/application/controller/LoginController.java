package com.agg.application.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.agg.application.model.AccountDO;
import com.agg.application.model.DealerDO;
import com.agg.application.model.LoginForm;
import com.agg.application.model.Result;
import com.agg.application.service.DealerService;
import com.agg.application.service.LoginService;
import com.agg.application.utils.AggConstants;

@Controller
@RequestMapping("/agg")
public class LoginController extends BaseController {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private LoginService loginService;
	
	
	@Autowired
	private DealerService dealerService;
	

	@RequestMapping(value = "/login", method = RequestMethod.POST, consumes = MediaType.ALL_VALUE)
	public @ResponseBody Result login(@RequestBody LoginForm loginForm, BindingResult result, Model model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		logger.debug("LoginForm username: [{}]", loginForm.getUsername());
		AccountDO account = loginService.authenticateUser(loginForm);
		
		logger.info("UserMenu size: "+account.getUserMenuDOList().size());
		logger.debug("Account [{}]", account);
		request.getSession().setMaxInactiveInterval(AggConstants.SESSION_EXPIRATION_TIMEOUT);
		request.getSession().setAttribute("user", account);
		return new Result("success", null, account);
	}

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String home(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) {
		AccountDO account = getAccountDetails(request);
		if (account == null)
			return "login";
		return "basic";
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
	
	@RequestMapping(value = "/isLoggedIn", method = RequestMethod.GET, consumes = MediaType.ALL_VALUE)
	public @ResponseBody Result isLoggedIn(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return new Result("success", null, sessionExists(request));
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.GET, consumes = MediaType.ALL_VALUE)
	public String dealerRegister(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (sessionExists(request))
			return "redirect:/agg/home";
		return "registration";
	}
	
	@RequestMapping(value = "/aboutRisk", method = RequestMethod.GET, consumes = MediaType.ALL_VALUE)
	public String aboutRisk(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (sessionExists(request))
			return "redirect:/agg/home";
		return "aboutRisk";
	}
	
	@RequestMapping(value = "/whoWeAre", method = RequestMethod.GET, consumes = MediaType.ALL_VALUE)
	public String whoWeAre(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (sessionExists(request))
			return "redirect:/agg/home";
		return "whoWeAre";
	}
	
	@RequestMapping(value = "/momsPinkTractor", method = RequestMethod.GET, consumes = MediaType.ALL_VALUE)
	public String momsPinkTractor(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (sessionExists(request))
			return "redirect:/agg/home";
		return "momsPinkTractor";
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET, consumes = MediaType.ALL_VALUE)
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		removeSession(request);
		return "login";
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public @ResponseBody Result register(@RequestBody DealerDO dealerDO, BindingResult result, Model model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		logger.debug("Inside register method with userName: "+ dealerDO.getUserName());
		
		StringBuffer url = request.getRequestURL();
		String uri = request.getRequestURI();
		String appUrl = url.substring(0, url.length() - uri.length());
		logger.info("appUrl: "+appUrl);
		
		long dealerId = dealerService.saveDealer(dealerDO, null, true, appUrl);
		logger.debug("registered dealerId: "+dealerId);
		return new Result("success", null, "Dealer registered successfully");
	}
	
	@RequestMapping(value = "/isDealerUserNameExists", method = RequestMethod.POST)
	public @ResponseBody Result isUserNameExists(@RequestBody String userName, HttpServletRequest request, HttpServletResponse response, Model model) {
		logger.debug("In isDealerUserNameExists with userName: "+userName);
		Result opResult = new Result("success", "Dealer User Info", dealerService.isUserNameExists(userName));
		
		return opResult;
	}
	
	@RequestMapping(value = "/claimPaidQuickly", method = RequestMethod.GET, consumes = MediaType.ALL_VALUE)
	public String claimPaidQuickly(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (sessionExists(request))
			return "redirect:/agg/home";
		return "claimPaidQuickly";
	}
	
	@RequestMapping(value = "/agFocused", method = RequestMethod.GET, consumes = MediaType.ALL_VALUE)
	public String agFocused(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (sessionExists(request))
			return "redirect:/agg/home";
		return "agFocused";
	}
	
	@RequestMapping(value = "/expWithIntegrity", method = RequestMethod.GET, consumes = MediaType.ALL_VALUE)
	public String expWithIntegrity(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (sessionExists(request))
			return "redirect:/agg/home";
		return "expWithIntegrity";
	}
	
	@RequestMapping(value = "/fairAndFlexTerms", method = RequestMethod.GET, consumes = MediaType.ALL_VALUE)
	public String fairAndFlexTerms(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (sessionExists(request))
			return "redirect:/agg/home";
		return "fairAndFlexTerms";
	}
	
}
