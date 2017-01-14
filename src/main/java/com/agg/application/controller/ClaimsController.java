package com.agg.application.controller;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.thymeleaf.context.Context;

import com.agg.application.entity.Claims;
import com.agg.application.model.AccountDO;
import com.agg.application.model.ClaimFileDO;
import com.agg.application.model.ClaimLaborDO;
import com.agg.application.model.ClaimPartDO;
import com.agg.application.model.ClaimReportDO;
import com.agg.application.model.ClaimsDO;
import com.agg.application.model.DealerDO;
import com.agg.application.model.QuoteDO;
import com.agg.application.model.Result;
import com.agg.application.service.ClaimsService;
import com.agg.application.service.ContractsService;
import com.agg.application.service.DealerService;
import com.agg.application.service.QuoteService;
import com.agg.application.service.UserService;
import com.agg.application.utils.AdjudicateMail;
import com.agg.application.utils.AggConstants;
import com.agg.application.utils.ClaimMail;
import com.agg.application.utils.EmailSender;
import com.agg.application.utils.PreAuthMail;
import com.agg.application.utils.Util;
import com.agg.application.vo.AdjudicateClaimFormVO;
import com.agg.application.vo.ClaimLabourVO;
import com.agg.application.vo.ClaimPartVO;
import com.agg.application.vo.ClaimPreAuthVO;
import com.agg.application.vo.ClaimsVO;
import com.google.gson.GsonBuilder;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@RestController
@RequestMapping("/agg")
public class ClaimsController extends BaseController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	//private static final String UPLOAD_FOLDER_NAME = "/uploads/";
	//private static final String UPLOAD_FOLDER_PATH = "/src/main/webapp/uploads/";
	//public static String uploadingdir = System.getProperty("user.dir") + UPLOAD_FOLDER_NAME;

	@Autowired
	private ClaimsService claimsService;
	
	@Autowired
	private DealerService dealerService;
	
	@Autowired
	private QuoteService quoteService;
	
	@Autowired
	private ContractsService contractsService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	EmailSender emailSender;
	
	@Value("${admin.email}")
	private String adminEmail;
	
	@Value("${file.upload.dir}")
	private String uploadingdir;

	@RequestMapping(value = "/editClaim", method = RequestMethod.GET, consumes = MediaType.ALL_VALUE)
	public @ResponseBody Result machineModel(ModelMap model, HttpServletResponse response/*, @PathVariable String claimId*/) {
		String claimId = "GoR1858";
		logger.info("Inside machineModel() with typeId: "+claimId);
		if(claimId != null && !claimId.isEmpty()){
			QuoteDO quoteDO = claimsService.getClaimInfo(claimId);
			model.put("quoteDO", quoteDO);
		}
		return new Result("success", null, model);	
	}
	
	@RequestMapping(value = "/searchClaim/{id}", method = RequestMethod.GET, consumes = MediaType.ALL_VALUE)
	public @ResponseBody Result searchClaim(@PathVariable String id, HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		logger.info("Inside claimsInfo()");
		if(!sessionExists(request)){
			return new Result("failure", "Session Expired", null);
		}else{
			List<QuoteDO> quoteInfoList = claimsService.getClaimInfoBySerialNumber(id);
			logger.info("quoteInfoList size: "+quoteInfoList.size());
			model.put("quoteInfoList", quoteInfoList);
			return new Result("success", null, model);
		}
	}

	@RequestMapping(value = "/saveClaim", method = RequestMethod.POST)
	public @ResponseBody Result saveClaim(@ModelAttribute("data") Object data,  @RequestParam("files") List<MultipartFile> fileList, BindingResult result,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		logger.debug("In saveClaim ");
		
		//uploadingdir = request.getServletContext().getRealPath("/uploads/");
		//uploadingdir = request.getServletContext().getResource("/uploads/").getFile();
		//logger.debug("paths: "+request.getServletContext().getResource("/uploads/"));
		//logger.debug("Session Realpath: "+request.getSession().getServletContext().getRealPath("/"));
		//logger.debug("Request Realpath: "+request.getServletContext().getRealPath("/"));
		//logger.debug("system user.dir path: "+System.getProperty("user.dir"));
		
		
		//uploadingdir = System.getProperty("user.dir")+ UPLOAD_FOLDER_PATH;
		new File(uploadingdir).mkdirs();
		
		logger.debug("Directory for image upload: "+uploadingdir);
		
		ClaimsVO claimsVO = new GsonBuilder().setDateFormat("yyyy-MM-dd").create().fromJson(data.toString(), ClaimsVO.class);
		
		if(!sessionExists(request)){
			return new Result("failure", "Session Expired", null);
		}else{
			ClaimsDO claimsDO = new ClaimsDO();
			claimsDO.setClaimId(claimsVO.getClaimId());
			claimsDO.setContractId(claimsVO.getContractId());
			/*DealerDO dealerDO = null;
			if(null != claimsVO.getContractId()){
				dealerDO= contractsService.getDealer(claimsVO.getContractId());
				if(null != dealerDO){
					claimsDO.setDealerId((int)dealerDO.getId());
				}
			}*/
			
			//updating claim created user as delaerId.
			AccountDO accountDO = getAccountDetails(request);
			if(accountDO != null){
				claimsDO.setDealerId(new Long(accountDO.getDealerId()).intValue());
			}
			
			claimsDO.setId(claimsVO.getId());
			claimsDO.setSerial(claimsVO.getSerial());
			claimsDO.setFailDate(claimsVO.getFailDate());
			claimsDO.setReportDate(claimsVO.getReportDate());
			claimsDO.setWorkOrder(Util.setDefaultStringValue(claimsVO.getWorkOrder()));
			claimsDO.setHoursBreakDown(claimsVO.getHoursBreakDown());
			claimsDO.setPreauthApprovedAmt(claimsVO.getPreauthApprovedAmt());
			claimsDO.setCustComplaint(Util.setDefaultStringValue(claimsVO.getCustComplaint()));
			claimsDO.setCauseFail(Util.setDefaultStringValue(claimsVO.getCauseFail()));
			claimsDO.setCorrectiveAction(Util.setDefaultStringValue(claimsVO.getCorrectiveAction()));
			claimsDO.setIsArchived(claimsVO.getIsArchived());
			claimsDO.setcStatus(claimsVO.getcStatusValue());
			claimsDO.setRequestedOtherCharges1(claimsVO.getRequestedOtherCharges1());
			claimsDO.setRequestedOtherCharges2(claimsVO.getRequestedOtherCharges2());
			claimsDO.setTotalAdjustedPartsCost(claimsVO.getTotalAdjustedPartsCost());
			claimsDO.setTotalAdjustedLaborCost(claimsVO.getTotalAdjustedLaborCost());
			claimsDO.setApprovedOtherCharges1(claimsVO.getApprovedOtherCharges1());
			claimsDO.setApprovedOtherCharges2(claimsVO.getApprovedOtherCharges2());
			logger.debug("claimsVO.getExtComments() "+claimsVO.getExtComments());
			claimsDO.setComments(claimsVO.getExtComments());
			
			if(null != claimsVO.getClaimPartVOList() && !claimsVO.getClaimPartVOList().isEmpty()){
				List<ClaimPartDO> partDO = new ArrayList<>();
				for(ClaimPartVO partVO : claimsVO.getClaimPartVOList()){
					ClaimPartDO claimPartDO = new ClaimPartDO();
					if(partVO.getId() > 0){
						claimPartDO.setId(partVO.getId());
					}
					claimPartDO.setPartNo(partVO.getPartNo());
					claimPartDO.setPartDescr(partVO.getPartDescr());
					claimPartDO.setQty(partVO.getQty());
					claimPartDO.setUnitPrice(partVO.getUnitPrice());
					partDO.add(claimPartDO);
				}
				claimsDO.setClaimPartDO(partDO);
			}
			
			if(null != claimsVO.getClaimLabourVOList() && !claimsVO.getClaimLabourVOList().isEmpty()){
				List<ClaimLaborDO> labourDO = new ArrayList<>();
				for(ClaimLabourVO labourVO : claimsVO.getClaimLabourVOList()){
					ClaimLaborDO claimLabourDO = new ClaimLaborDO();
					if(labourVO.getId() > 0){
						claimLabourDO.setId(labourVO.getId());
					}
					claimLabourDO.setLaborNo(labourVO.getLaborNo());
					claimLabourDO.setLaborDescr(labourVO.getLaborDescr());
					claimLabourDO.setLaborHrs(labourVO.getLaborHrs());
					claimLabourDO.setRate(labourVO.getLaborHourlyRate());
					labourDO.add(claimLabourDO);
				}
				claimsDO.setClaimLaborDO(labourDO);
			}
			List<ClaimFileDO> claimFileDO = new ArrayList<>();
			for(MultipartFile uploadedFile : fileList) {
				String fName = String.format("%s_%s", System.currentTimeMillis(), uploadedFile.getOriginalFilename());
				ClaimFileDO fileDO = new ClaimFileDO();
	            File file = new File(String.format("%s%s%s", uploadingdir, File.separator, fName));
	            try {
	            	//fileDO.setFileName(Util.getBaseURL(request) + UPLOAD_FOLDER_NAME + fName);
	            	fileDO.setFileName(fName);
	            	claimFileDO.add(fileDO);
					uploadedFile.transferTo(file);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        }
			claimsDO.setClaimFileDO(claimFileDO);
			Claims claim = claimsService.saveClaim(claimsDO, accountDO);
			Long id = (null != claim) ? (long)claim.getId() : -1; 
			if(id != -1 ){
				ClaimMail mail = new ClaimMail();
				int partsCost = calcTotalPartsCost(claimsDO.getClaimPartDO());
				int laborsCost = calcTotalLaborsCost(claimsDO.getClaimLaborDO());
				int otherCost = claimsDO.getRequestedOtherCharges1() + claimsDO.getRequestedOtherCharges2();
				String dealerFirstName = "";
				DealerDO dealerDO = dealerService.getDealer(claim.getDealerId());
				if(dealerDO != null){
					dealerFirstName = dealerDO.getFirstName();
				}
				Context context = new Context();
				context.setVariable("claimNo", claimsDO.getClaimId());
				context.setVariable("dealerName", dealerFirstName);
				context.setVariable("contractNo", claimsDO.getContractId());
				context.setVariable("totalLaborCost", laborsCost);
				context.setVariable("totalPartsCost", partsCost);
				context.setVariable("totalOtherCost", otherCost);
				context.setVariable("totalClaimCost", (partsCost + laborsCost + otherCost));
				context.setVariable("deductible", claimsVO.getDeductible());
				context.setVariable("lol", claimsVO.getLol());
				context.setVariable("availableLol", claimsVO.getAvailabeLol());
				context.setVariable("externalComments", "");
				mail.setContext(context);
				mail.setEmailSender(emailSender);
				mail.setUserService(userService);
				mail.setToEmail(adminEmail);
				new Thread(mail).start();
			}
			return new Result("success", null, id);
		}
	}
	
	@RequestMapping(value = "/updateClaim", method = RequestMethod.POST)
	public @ResponseBody Result updateClaim(@ModelAttribute("data") Object data,  @RequestParam("files") List<MultipartFile> fileList, BindingResult result,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		//logger.debug("Session Realpath: "+request.getSession().getServletContext().getRealPath("/"));
		//logger.debug("Request Realpath: "+request.getServletContext().getRealPath("/"));
		//logger.debug("system user.dir path: "+System.getProperty("user.dir"));
		
		logger.debug("Directory for image upload: "+uploadingdir);
		
		ClaimsVO claimsVO = new GsonBuilder().setDateFormat("yyyy-MM-dd").create().fromJson(data.toString(), ClaimsVO.class);
		logger.debug("In saveClaim ");
		if(!sessionExists(request)){
			return new Result("failure", "Session Expired", null);
		}else{
			ClaimsDO claimsDO = new ClaimsDO();
			claimsDO.setClaimId(claimsVO.getClaimId());
			claimsDO.setContractId(claimsVO.getContractId());
			/*DealerDO dealerDO = null;
			if(null != claimsVO.getContractId()){
				dealerDO= contractsService.getDealer(claimsVO.getContractId());
				if(null != dealerDO){
					claimsDO.setDealerId((int)dealerDO.getId());
				}
			}*/
			//updating claim created user as delaerId.
			AccountDO accountDO = getAccountDetails(request);
			if(accountDO != null){
				claimsDO.setDealerId(new Long(accountDO.getDealerId()).intValue());
			}
			claimsDO.setId(claimsVO.getId());
			claimsDO.setSerial(claimsVO.getSerial());
			claimsDO.setFailDate(claimsVO.getFailDate());
			claimsDO.setReportDate(claimsVO.getReportDate());
			claimsDO.setWorkOrder(Util.setDefaultStringValue(claimsVO.getWorkOrder()));
			claimsDO.setHoursBreakDown(claimsVO.getHoursBreakDown());
			claimsDO.setPreauthApprovedAmt(claimsVO.getPreauthApprovedAmt());
			claimsDO.setCustComplaint(Util.setDefaultStringValue(claimsVO.getCustComplaint()));
			claimsDO.setCauseFail(Util.setDefaultStringValue(claimsVO.getCauseFail()));
			claimsDO.setCorrectiveAction(Util.setDefaultStringValue(claimsVO.getCorrectiveAction()));
			claimsDO.setIsArchived(claimsVO.getIsArchived());
			//claimsDO.setcStatus(claimsVO.getcStatusValue());
			claimsDO.setcStatus(claimsVO.getcStatusValue());
			claimsDO.setRequestedOtherCharges1(claimsVO.getRequestedOtherCharges1());
			claimsDO.setRequestedOtherCharges2(claimsVO.getRequestedOtherCharges2());
			claimsDO.setTotalAdjustedPartsCost(claimsVO.getTotalAdjustedPartsCost());
			claimsDO.setTotalAdjustedLaborCost(claimsVO.getTotalAdjustedLaborCost());
			claimsDO.setApprovedOtherCharges1(claimsVO.getApprovedOtherCharges1());
			claimsDO.setApprovedOtherCharges2(claimsVO.getApprovedOtherCharges2());
			
			if(null != claimsVO.getClaimPartVOList() && !claimsVO.getClaimPartVOList().isEmpty()){
				List<ClaimPartDO> partDO = new ArrayList<>();
				for(ClaimPartVO partVO : claimsVO.getClaimPartVOList()){
					ClaimPartDO claimPartDO = new ClaimPartDO();
					if(partVO.getId() > 0){
						claimPartDO.setId(partVO.getId());
					}
					claimPartDO.setPartNo(partVO.getPartNo());
					claimPartDO.setPartDescr(partVO.getPartDescr());
					claimPartDO.setQty(partVO.getQty());
					claimPartDO.setUnitPrice(partVO.getUnitPrice());
					partDO.add(claimPartDO);
				}
				claimsDO.setClaimPartDO(partDO);
			}
			
			if(null != claimsVO.getClaimLabourVOList() && !claimsVO.getClaimLabourVOList().isEmpty()){
				List<ClaimLaborDO> labourDO = new ArrayList<>();
				for(ClaimLabourVO labourVO : claimsVO.getClaimLabourVOList()){
					ClaimLaborDO claimLabourDO = new ClaimLaborDO();
					if(labourVO.getId() > 0){
						claimLabourDO.setId(labourVO.getId());
					}
					claimLabourDO.setLaborNo(labourVO.getLaborNo());
					claimLabourDO.setLaborDescr(labourVO.getLaborDescr());
					claimLabourDO.setLaborHrs(labourVO.getLaborHrs());
					claimLabourDO.setRate(labourVO.getLaborHourlyRate());
					labourDO.add(claimLabourDO);
				}
				claimsDO.setClaimLaborDO(labourDO);
			}
			List<ClaimFileDO> claimFileDO = new ArrayList<>();
			for(MultipartFile uploadedFile : fileList) {
				String fName = String.format("%s_%s", System.currentTimeMillis(), uploadedFile.getOriginalFilename());
				ClaimFileDO fileDO = new ClaimFileDO();
	            File file = new File(String.format("%s%s%s", uploadingdir, File.separator, fName));
	            try {
	            	//fileDO.setFileName(Util.getBaseURL(request) + UPLOAD_FOLDER_NAME + fName);
	            	fileDO.setFileName(fName);
	            	claimFileDO.add(fileDO);
					uploadedFile.transferTo(file);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        }
			claimsDO.setClaimFileDO(claimFileDO);
			Claims claim = claimsService.saveClaim(claimsDO, accountDO);
			Long id = (null != claim) ? (long)claim.getId() : -1;
			if(id == -1){
				return new Result("error", null, "");
			}
			return new Result("success", null, id);
		}
	}
	
	@RequestMapping(value = "/preAuthClaimReq", method = RequestMethod.GET, consumes = MediaType.ALL_VALUE)
	public @ResponseBody Result getPreAuthClaimRequest(HttpServletRequest request, HttpServletResponse response) {
		logger.info("Inside getPreAuthClaimRequest()");
		if(!sessionExists(request)){
			return new Result("failure", "Session Expired", null);
		}else{
			Map<String, Object> map = new HashMap<>();
			List<ClaimsDO> cliamsList = null;
			AccountDO account = getAccountDetails(request);
			if("admin".equals(account.getRoleName())){
				cliamsList = claimsService.getClaimsByCStatus(Util.getClaimStatusCode("pre_authorized_requested"), true, (int)account.getDealerId());
			}else{
				cliamsList = claimsService.getClaimsByCStatus(Util.getClaimStatusCode("pre_authorized_requested"), (int)account.getDealerId(), true);
			}
			
			logger.info("preAuthClaims size: "+cliamsList.size());
			map.put("preAuthClaimList", cliamsList);
			return new Result("success", null, map);
		}
	}
	
	@RequestMapping(value = "/draftClaim", method = RequestMethod.GET, consumes = MediaType.ALL_VALUE)
	public @ResponseBody Result getDraftClaimRequest(HttpServletRequest request, HttpServletResponse response) {
		logger.info("Inside getDraftClaimRequest()");
		if(!sessionExists(request)){
			return new Result("failure", "Session Expired", null);
		}else{
			Map<String, Object> map = new HashMap<>();
			List<ClaimsDO> cliamsList = null;
			AccountDO account = getAccountDetails(request);
			if("admin".equals(account.getRoleName())){
				cliamsList = claimsService.getClaimsByCStatus(Util.getClaimStatusCode("draft"), false, (int)account.getDealerId());
			}else{
				cliamsList = claimsService.getClaimsByCStatus(Util.getClaimStatusCode("draft"), (int)account.getDealerId(), false);
			}
			
			logger.info("draft claims size: "+cliamsList.size());
			map.put("draftClaimList", cliamsList);
			return new Result("success", null, map);
		}
	}
	
	@RequestMapping(value = "/preAuthClaimReq", method = RequestMethod.PUT, consumes = MediaType.ALL_VALUE)
	public @ResponseBody Result updatePreAuthClaimRequestUpdate(@RequestBody ClaimPreAuthVO claimPreAuthVO, HttpServletRequest request, HttpServletResponse response) {
		logger.info("Inside UpdatePreAuthClaimRequestUpdate()");
		if(!sessionExists(request)){
			return new Result("failure", "Session Expired", null);
		}else{
			AccountDO account = getAccountDetails(request);
			int dealerId = (int)account.getDealerId();
			long accountId = account.getId();
			claimsService.updateStatus(claimPreAuthVO.getId(), Util.getClaimStatusCode(claimPreAuthVO.getcStatus()), dealerId, accountId, claimPreAuthVO.getExtComment());
			PreAuthMail mail = new PreAuthMail();
			Context context = new Context();
			context.setVariable("claimNo", claimPreAuthVO.getId());
			context.setVariable("dealerId", dealerId);
			context.setVariable("externalComments", claimPreAuthVO.getExtComment());
			mail.setContext(context);
			mail.setEmailSender(emailSender);
			mail.setUserService(userService);
			mail.setClaimsService(claimsService);
			
			new Thread(mail).start();
			return new Result("success", null, "status updated");
		}
	}
	
	@RequestMapping(value = "/updateClaimComment", method = RequestMethod.PUT, consumes = MediaType.ALL_VALUE)
	public @ResponseBody Result updateClaimComment(@RequestBody ClaimPreAuthVO claimPreAuthVO, HttpServletRequest request, HttpServletResponse response) {
		logger.info("Inside UpdatePreAuthClaimRequestUpdate()");
		if(!sessionExists(request)){
			return new Result("failure", "Session Expired", null);
		}else{
			AccountDO account = getAccountDetails(request);
			int dealerId = (int)account.getDealerId();
			long accountId = account.getId();
			claimsService.updateStatus(claimPreAuthVO.getId(), claimPreAuthVO.getcStatusValue(), dealerId, accountId, claimPreAuthVO.getExtComment());
			return new Result("success", null, "status updated");
		}
	}
	
	@RequestMapping(value = "/getClaimsInfo", method = RequestMethod.GET, consumes = MediaType.ALL_VALUE)
	public @ResponseBody Result getClaimsInfo(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) {
		logger.info("Inside getClaimsInfo()");
		if(!sessionExists(request)){
			return new Result("failure", "Session Expired", null);
		}else{
			List<ClaimsDO> claimsInfoList = claimsService.getClaimsInfo(getAccountDetails(request));
			if(claimsInfoList != null){
				logger.info("claimsInfoList size: "+claimsInfoList.size());
			}
			model.put("claimDOList", claimsInfoList);
			return new Result("success", null, model);
		}
	}
	
	@RequestMapping(value = "/approvedClaims", method = RequestMethod.GET, consumes = MediaType.ALL_VALUE)
	public @ResponseBody Result getApprovedClaims(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) {
		logger.info("Inside getApprovedClaims()");
		if(!sessionExists(request)){
			return new Result("failure", "Session Expired", null);
		}else{
			List<ClaimsDO> claimsInfoList = claimsService.getAprvOrRejClaims(getAccountDetails(request), (byte)AggConstants.CLAIM_STATUS_CLOSED);
			if(claimsInfoList != null){
				logger.info("claimsInfoList size: "+claimsInfoList.size());
			}
			model.put("claimDOList", claimsInfoList);
			return new Result("success", null, model);
		}
	}
	
	@RequestMapping(value = "/rejectedClaims", method = RequestMethod.GET, consumes = MediaType.ALL_VALUE)
	public @ResponseBody Result getRejectedClaims(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) {
		logger.info("Inside getRejectedClaims()");
		if(!sessionExists(request)){
			return new Result("failure", "Session Expired", null);
		}else{
			List<Byte> statusList = new ArrayList<Byte>();
			statusList.add((byte)AggConstants.CLAIM_STATUS_PRE_AUTHORIZED_REJECTED);
			statusList.add((byte)AggConstants.CLAIM_STATUS_CANCEL);
			List<ClaimsDO> claimsInfoList = claimsService.getAprvOrRejClaims(getAccountDetails(request), statusList);
			if(claimsInfoList != null){
				logger.info("claimsInfoList size: "+claimsInfoList.size());
			}
			model.put("claimDOList", claimsInfoList);
			return new Result("success", null, model);
		}
	}
	
	@RequestMapping(value = "/draftClaims", method = RequestMethod.GET, consumes = MediaType.ALL_VALUE)
	public @ResponseBody Result getDraftClaims(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) {
		logger.info("Inside getDraftClaims()");
		if(!sessionExists(request)){
			return new Result("failure", "Session Expired", null);
		}else{
			List<Byte> statusList = new ArrayList<Byte>();
			statusList.add((byte)AggConstants.CLAIM_STATUS_DRAFT);
			statusList.add((byte)AggConstants.CLAIM_STATUS_PRE_AUTHORIZED_APPROVED);
			statusList.add((byte)AggConstants.CLAIM_STATUS_PRE_AUTHORIZED_APPROVED_WITH_ADJUSMENTS);
			List<ClaimsDO> claimsInfoList = claimsService.getAprvOrRejClaims(getAccountDetails(request), statusList);
			if(claimsInfoList != null){
				logger.info("claimsInfoList size: "+claimsInfoList.size());
			}
			model.put("claimDOList", claimsInfoList);
			return new Result("success", null, model);
		}
	}
	
	@RequestMapping(value = "/adjudicateClaim", method = RequestMethod.GET, consumes = MediaType.ALL_VALUE)
	public @ResponseBody Result getAdjudicateClaim(HttpServletRequest request, HttpServletResponse response) {
		logger.info("Inside getAdjudicateClaim()");
		if(!sessionExists(request)){
			return new Result("failure", "Session Expired", null);
		}else{
			Map<String, Object> map = new HashMap<>();
			List<ClaimsDO> cliamsList = getClaimByStatus(Util.getClaimStatusCode("pending"), request, true);
			
			logger.info("preAuthClaims size: "+cliamsList.size());
			map.put("preAuthClaimList", cliamsList);
			return new Result("success", null, map);
		}
	}
	
	@RequestMapping(value = "/adjudicateClaim", method = RequestMethod.POST)
	public @ResponseBody Result adjudicateClaim(@ModelAttribute("data") Object data,  @RequestParam("files") List<MultipartFile> fileList, BindingResult result,
			HttpServletRequest request, HttpServletResponse response) {
		//uploadingdir = request.getServletContext().getRealPath("/uploads/");
		
		//uploadingdir = System.getProperty("user.dir")+ UPLOAD_FOLDER_PATH;
		new File(uploadingdir).mkdirs();
		AdjudicateClaimFormVO vo = new GsonBuilder().setDateFormat("yyyy-MM-dd").create().fromJson(data.toString(), AdjudicateClaimFormVO.class);
		logger.debug("In adjudicateClaim ");
		int id = -1;
		if(!sessionExists(request)){
			return new Result("failure", "Session Expired", null);
		}else{
			ClaimsDO claimsDO = new ClaimsDO();
			claimsDO.setId(vo.getId());
			claimsDO.setTotalAdjustedPartsCost(vo.getTotalAdjustmentPartsCost());
			claimsDO.setTotalAdjustedLaborCost(vo.getTotalAdjustmentLaborsCost());
			claimsDO.setApprovedOtherCharges1(vo.getApprovedOtherCharges1());
			claimsDO.setApprovedOtherCharges2(vo.getApprovedOtherCharges2());
			claimsDO.setTra(vo.getTra());
			claimsDO.setCustomerOwesAmount(vo.getCustomerOwes());
			claimsDO.setComments(vo.getExtComment());
			claimsDO.setCheqNo(vo.getCheqNo());
			claimsDO.setPaidDate(vo.getPaidDate());
			claimsDO.setComments(vo.getExtComment());
			logger.debug("vo.getExtComment() "+vo.getExtComment());
			
			List<ClaimFileDO> claimFileDO = new ArrayList<>();
			for(MultipartFile uploadedFile : fileList) {
				String fName = String.format("%s_%s", System.currentTimeMillis(), uploadedFile.getOriginalFilename());
				ClaimFileDO fileDO = new ClaimFileDO();
	            File file = new File(String.format("%s%s%s", uploadingdir, File.separator, fName));
	            try {
	            	//fileDO.setFileName(Util.getBaseURL(request) + UPLOAD_FOLDER_NAME + fName);
	            	fileDO.setFileName(fName);
	            	claimFileDO.add(fileDO);
					uploadedFile.transferTo(file);
				} catch (Exception e) {
					e.printStackTrace();
				}
	        }
			if(!claimFileDO.isEmpty()){
				claimsDO.setClaimFileDO(claimFileDO);
			}
			id = claimsService.updateClaimAdjudicate(claimsDO, getAccountDetails(request));
			
			if(id != -1){
				StringBuffer url = request.getRequestURL();
				String uri = request.getRequestURI();
				String appUrl = url.substring(0, url.length() - uri.length());
				logger.info("appUrl: "+appUrl);
				
				AccountDO accountDO = getAccountDetails(request);
				
				AdjudicateMail mail = new AdjudicateMail();
				mail.setClaimsService(claimsService);
				mail.setAppUrl(appUrl);
				mail.setEmailSender(emailSender);
				mail.setId(vo.getId());
				mail.setTra(vo.getTra());
				mail.setCustomerOwes(vo.getCustomerOwes());
				mail.setDealerId(new Long(accountDO.getDealerId()).intValue());
				new Thread(mail).start();
			}
		}
		return (-1 == id) ? new Result("failure", null, "") : new Result("success", null, id);
	}
	
	private List<ClaimsDO> getClaimByStatus(final byte statusCode, HttpServletRequest request, boolean contractInfo){
		List<ClaimsDO> cliamsList = null;
		AccountDO account = getAccountDetails(request);
		if("admin".equals(account.getRoleName())){
			cliamsList = claimsService.getClaimsByCStatus(statusCode, contractInfo, (int)account.getDealerId());
		}else{
			cliamsList = claimsService.getClaimsByCStatus(statusCode, (int)account.getDealerId(), contractInfo);
		}
		return cliamsList;
	}
	
	public int calcTotalPartsCost(List<ClaimPartDO> parts){
		int sum = 0;
		if(null != parts){
			for(ClaimPartDO part : parts){
				sum += (part.getQty() * part.getUnitPrice());
			}
		}
		return sum;
	}
	
	public int calcTotalLaborsCost(List<ClaimLaborDO> labors){
		int sum = 0;
		if(null != labors){
			for(ClaimLaborDO labor : labors){
				sum += (labor.getLaborHrs() * labor.getRate());
			}
		}
		return sum;
	}
	
	@RequestMapping(value = "/claims/count/{contractId}", method = RequestMethod.GET, consumes = MediaType.ALL_VALUE)
	public @ResponseBody Result getContractsCount(@PathVariable String contractId, HttpServletRequest request, HttpServletResponse response, Model model) {
		logger.debug("Inside getContractsCount with id: "+contractId);
		Result opResult = null;
		if (!sessionExists(request)){
			opResult = new Result("failure", "Invalid Login", null);
		}else{
			int count = claimsService.getContractsCount(contractId);
			opResult = new Result("success", "", model.addAttribute("count", count));
		}
		return opResult;
	}
	
	@RequestMapping(value = "/claims/{claimId}", method = RequestMethod.GET, consumes = MediaType.ALL_VALUE)
	public @ResponseBody Result getClaimById(@PathVariable String claimId, HttpServletRequest request, HttpServletResponse response, Model model) {
		logger.debug("Inside getClaimById with id: "+claimId);
		Result opResult = null;
		if (!sessionExists(request)){
			opResult = new Result("failure", "Invalid Login", null);
		}else{
			AccountDO account = getAccountDetails(request);
			ClaimsDO claimDO = claimsService.getClaim(claimId, (int)account.getDealerId());
			opResult = new Result("success", "", model.addAttribute("claim", claimDO));
		}
		return opResult;
	}
	
	@RequestMapping(value = "/claim/file/{claimId}", method = RequestMethod.GET, consumes = MediaType.ALL_VALUE)
	public void getClaimFile(@PathVariable String claimId, @RequestParam("filename") String fileName, HttpServletRequest request, HttpServletResponse response, Model model) throws Exception{
		logger.debug("Inside getClaimFile with id: "+claimId+" and fileName: "+fileName);
		//Result opResult = null;
		if (!sessionExists(request)){
			//opResult = new Result("failure", "Invalid Login", null);
			logger.info("Invalid Login");
		}else{
			//uploadingdir = System.getProperty("user.dir")+ UPLOAD_FOLDER_PATH;
			String path = uploadingdir +File.separator+ fileName;
			File file = new File(path);
	        FileInputStream inputStream = new FileInputStream(file);

	        response.setContentType("application/"+fileName.substring(fileName.lastIndexOf(".") + 1));
	        response.setContentLength((int) file.length());
	        response.setHeader("Content-Disposition", "inline;filename=\"" + fileName + "\"");

	        FileCopyUtils.copy(inputStream, response.getOutputStream());
	        
	        inputStream.close();
		}
	}
	
	/*@RequestMapping(value = "/claim/files/{claimId}", method = RequestMethod.GET, consumes = MediaType.ALL_VALUE)
	@ResponseBody
	public FileSystemResource  getClaimFiles(@PathVariable String claimId, @RequestParam("filename") String fileName, 
			HttpServletRequest request, HttpServletResponse response, Model model) throws Exception{
		logger.debug("Inside getClaimFiles with id: "+claimId+" and fileName: "+fileName);
		Result opResult = null;
		FileSystemResource fileSystemResource = null;
		if (!sessionExists(request)){
			opResult = new Result("failure", "Invalid Login", null);
		}else{
			response.setContentType("application/"+fileName.substring(fileName.lastIndexOf(".") + 1));
			uploadingdir = System.getProperty("user.dir")+ UPLOAD_FOLDER_PATH;
			String path = uploadingdir + fileName;
			File file = new File(path);
			fileSystemResource = new FileSystemResource(file); 
		}
		
		return fileSystemResource;
	}*/
	
	
	@RequestMapping(value = "/claim/report/{claimId}", method = RequestMethod.GET, produces="application/pdf;charset=UTF-8")
	public ModelAndView showClaimDetailsReport(@PathVariable String claimId, ModelAndView modelAndView, HttpServletRequest request, 
			HttpServletResponse response, ModelMap modelMap) throws Exception{
		logger.debug("In showClaimDetailsReport with claimId: "+claimId);
		if (!sessionExists(request)){
			modelAndView = new ModelAndView("login");
		}else{
			StringBuffer url = request.getRequestURL();
			String uri = request.getRequestURI();
			String appUrl = url.substring(0, url.length() - uri.length());
			logger.info("appUrl: "+appUrl);
			
			AccountDO account = getAccountDetails(request);
			ClaimReportDO reportDO = claimsService.getClaimReportDetails(claimId, account);
			JRDataSource jrDataSource = null;
			JRDataSource claimFileSubReportDataSource = null;
			JRDataSource claimPartSubReportDataSource = null;
			JRDataSource claimLaborSubReportDataSource = null;
			JRDataSource claimNotesSubReportDataSource = null;
			if(reportDO != null){
				List<ClaimReportDO> reportDOList = new ArrayList<ClaimReportDO>();
				reportDOList.add(reportDO);
				jrDataSource = new JRBeanCollectionDataSource(reportDOList);
				
				if(reportDO.getClaimFileDOList() != null && !reportDO.getClaimFileDOList().isEmpty()){
					claimFileSubReportDataSource = new JRBeanCollectionDataSource(reportDO.getClaimFileDOList());
				}else{
					claimFileSubReportDataSource = new JREmptyDataSource();
				}
				
				if(reportDO.getClaimPartDOList() != null && !reportDO.getClaimPartDOList().isEmpty()){
					claimPartSubReportDataSource = new JRBeanCollectionDataSource(reportDO.getClaimPartDOList());
				}else{
					claimPartSubReportDataSource = new JREmptyDataSource();
				}
				
				if(reportDO.getClaimLaborDOList() != null && !reportDO.getClaimLaborDOList().isEmpty()){
					claimLaborSubReportDataSource = new JRBeanCollectionDataSource(reportDO.getClaimLaborDOList());
				}else{
					claimLaborSubReportDataSource = new JREmptyDataSource();
				}
				
				if(reportDO.getClaimNoteDOList() != null && !reportDO.getClaimNoteDOList().isEmpty()){
					claimNotesSubReportDataSource = new JRBeanCollectionDataSource(reportDO.getClaimNoteDOList());
				}else{
					claimNotesSubReportDataSource = new JREmptyDataSource();
				}
			}else{
				jrDataSource = new JREmptyDataSource();
				claimFileSubReportDataSource = new JREmptyDataSource();
				claimPartSubReportDataSource = new JREmptyDataSource();
				claimLaborSubReportDataSource = new JREmptyDataSource();
				claimNotesSubReportDataSource = new JREmptyDataSource();
			}
			
			modelMap.put("datasource", jrDataSource);
			modelMap.put("claimFileList", claimFileSubReportDataSource);
			modelMap.put("claimPartList", claimPartSubReportDataSource);
			modelMap.put("claimLaborList", claimLaborSubReportDataSource);
			modelMap.put("claimExtCommentList", claimNotesSubReportDataSource);
			modelMap.put("format", "pdf");
			modelMap.put("SUBREPORT_DIR", System.getProperty("user.dir")+"/src/main/resources/jrxml/");
			modelMap.put("imagePath", appUrl+"/assets/images/logo.png");
			modelMap.put("totalReqPartsCost", reportDO.getTotalReqPartsCost());
			modelMap.put("totalAdjPartsCost", reportDO.getTotalAdjPartsCost());
			modelMap.put("totalReqLaborCost", reportDO.getTotalReqLaborCost());
			modelMap.put("totalAdjLaborCost", reportDO.getTotalAdjLaborCost());
			modelMap.put("totalReqClaimCost", reportDO.getTotalReqClaimCost());
			modelMap.put("totalAdjClaimCost", reportDO.getTotalAdjClaimCost());
			modelMap.put("totalReimbursedAmount", reportDO.getTotalReimbursedAmount());
			modelMap.put("totalAmtOwnedByCustomer", reportDO.getTotalAmtOwnedByCustomer());
			
			modelAndView = new ModelAndView("rpt_claimDetails", modelMap);
		}
		
		return modelAndView;
	}
	
}


