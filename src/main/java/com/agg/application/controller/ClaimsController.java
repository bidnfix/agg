package com.agg.application.controller;

import java.io.File;
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
import org.thymeleaf.context.Context;

import com.agg.application.entity.Claims;
import com.agg.application.model.AccountDO;
import com.agg.application.model.ClaimFileDO;
import com.agg.application.model.ClaimLaborDO;
import com.agg.application.model.ClaimPartDO;
import com.agg.application.model.ClaimsDO;
import com.agg.application.model.DealerDO;
import com.agg.application.model.QuoteDO;
import com.agg.application.model.Result;
import com.agg.application.service.ClaimsService;
import com.agg.application.service.ContractsService;
import com.agg.application.service.DealerService;
import com.agg.application.service.QuoteService;
import com.agg.application.service.UserService;
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

@RestController
@RequestMapping("/agg")
public class ClaimsController extends BaseController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private static final String UPLOAD_FOLDER_NAME = "/uploads/"; 
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
		//uploadingdir = request.getServletContext().getRealPath("/uploads/");
		//uploadingdir = request.getServletContext().getResource("/uploads/").getFile();
		//logger.debug("paths: "+request.getServletContext().getResource("/uploads/"));
		
		logger.debug("Directory for image upload: "+uploadingdir);
		
		ClaimsVO claimsVO = new GsonBuilder().setDateFormat("yyyy-MM-dd").create().fromJson(data.toString(), ClaimsVO.class);
		logger.debug("In saveClaim ");
		if(!sessionExists(request)){
			return new Result("failure", "Session Expired", null);
		}else{
			ClaimsDO claimsDO = new ClaimsDO();
			claimsDO.setClaimId(claimsVO.getClaimId());
			claimsDO.setContractId(claimsVO.getContractId());
			DealerDO dealerDO = null;
			if(null != claimsVO.getContractId()){
				dealerDO= contractsService.getDealer(claimsVO.getContractId());
				if(null != dealerDO){
					claimsDO.setDealerId((int)dealerDO.getId());
				}
			}
			
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
			
			if(null != claimsVO.getClaimPartVOList() && !claimsVO.getClaimPartVOList().isEmpty()){
				List<ClaimPartDO> partDO = new ArrayList<>();
				for(ClaimPartVO partVO : claimsVO.getClaimPartVOList()){
					ClaimPartDO claimPartDO = new ClaimPartDO();
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
	            	fileDO.setFileName(Util.getBaseURL(request) + UPLOAD_FOLDER_NAME + fName);
	            	claimFileDO.add(fileDO);
					uploadedFile.transferTo(file);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        }
			claimsDO.setClaimFileDO(claimFileDO);
			Claims claim = claimsService.saveClaim(claimsDO);
			Long id = (null != claim) ? (long)claim.getId() : -1; 
			if(id != -1 ){
				ClaimMail mail = new ClaimMail();
				int partsCost = calcTotalPartsCost(claimsDO.getClaimPartDO());
				int laborsCost = calcTotalLaborsCost(claimsDO.getClaimLaborDO());
				int otherCost = claimsDO.getRequestedOtherCharges1() + claimsDO.getRequestedOtherCharges2();
				String dealerFirstName = dealerService.getDealer(claim.getDealerId()).getFirstName();
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
				context.setVariable("availableLol", claimsVO.getAvailableLol());
				context.setVariable("externalComments", "");
				mail.setContext(context);
				mail.setEmailSender(emailSender);
				mail.setUserService(userService);
				new Thread(mail).start();
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
				cliamsList = claimsService.getClaimsByCStatus(Util.getClaimStatusCode("pre_authorized_requested"), false);
			}else{
				cliamsList = claimsService.getClaimsByCStatus(Util.getClaimStatusCode("pre_authorized_requested"), (int)account.getDealerId(), false);
			}
			
			logger.info("preAuthClaims size: "+cliamsList.size());
			map.put("preAuthClaimList", cliamsList);
			return new Result("success", null, map);
		}
	}
	
	@RequestMapping(value = "/preAuthClaimReq", method = RequestMethod.PUT, consumes = MediaType.ALL_VALUE)
	public @ResponseBody Result updatePreAuthClaimRequestUpdate(@RequestBody ClaimPreAuthVO claimPreAuthVO, HttpServletRequest request, HttpServletResponse response) {
		logger.info("Inside UpdatePreAuthClaimRequestUpdate()");
		if(!sessionExists(request)){
			return new Result("failure", "Session Expired", null);
		}else{
			int dealerId = (int)getAccountDetails(request).getDealerId();
			claimsService.updateStatus(claimPreAuthVO.getId(), Util.getClaimStatusCode(claimPreAuthVO.getcStatus()), dealerId, claimPreAuthVO.getExtComment());
			PreAuthMail mail = new PreAuthMail();
			Context context = new Context();
			context.setVariable("claimNo", claimPreAuthVO.getId());
			context.setVariable("externalComments", claimPreAuthVO.getExtComment());
			mail.setContext(context);
			mail.setEmailSender(emailSender);
			mail.setUserService(userService);
			
			new Thread(mail).start();
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
	
	@RequestMapping(value = "/adjudicateClaim", method = RequestMethod.GET, consumes = MediaType.ALL_VALUE)
	public @ResponseBody Result getAdjudicateClaim(HttpServletRequest request, HttpServletResponse response) {
		logger.info("Inside getAdjudicateClaim()");
		if(!sessionExists(request)){
			return new Result("failure", "Session Expired", null);
		}else{
			Map<String, Object> map = new HashMap<>();
			List<ClaimsDO> cliamsList = getClaimByStatus(Util.getClaimStatusCode("pre_authorized_approved_with_adjustments"), request, true);
			
			logger.info("preAuthClaims size: "+cliamsList.size());
			map.put("preAuthClaimList", cliamsList);
			return new Result("success", null, map);
		}
	}
	
	@RequestMapping(value = "/adjudicateClaim", method = RequestMethod.POST)
	public @ResponseBody Result adjudicateClaim(@ModelAttribute("data") Object data,  @RequestParam("files") List<MultipartFile> fileList, BindingResult result,
			HttpServletRequest request, HttpServletResponse response) {
		uploadingdir = request.getServletContext().getRealPath("/uploads/");
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
			claimsDO.setApprovedOtherCharges1(vo.getRequestedOtherCharges1());
			claimsDO.setApprovedOtherCharges2(vo.getRequestedOtherCharges2());
			List<ClaimFileDO> claimFileDO = new ArrayList<>();
			for(MultipartFile uploadedFile : fileList) {
				String fName = String.format("%s_%s", System.currentTimeMillis(), uploadedFile.getOriginalFilename());
				ClaimFileDO fileDO = new ClaimFileDO();
	            File file = new File(String.format("%s%s%s", uploadingdir, File.separator, fName));
	            try {
	            	fileDO.setFileName(Util.getBaseURL(request) + UPLOAD_FOLDER_NAME + fName);
	            	claimFileDO.add(fileDO);
					uploadedFile.transferTo(file);
				} catch (Exception e) {
					e.printStackTrace();
				}
	        }
			if(!claimFileDO.isEmpty()){
				claimsDO.setClaimFileDO(claimFileDO);
			}
			id = claimsService.updateClaimAdjudicate(claimsDO);
			
			/*if(id != -1 ){
				ClaimMail mail = new ClaimMail();
				mail.setUserService(userService);
				ClaimMailDO claimMailDO = new ClaimMailDO();
				claimMailDO.setClaimID(String.valueOf(id));
				claimMailDO.setDealerName(dealerDO.getFirstName());
				claimMailDO.setContractId(claimsDO.getContractId());
				claimMailDO.setTotalPartsCost(claimsDO.getClaimPartDO());
				claimMailDO.setTotalOtherCosts(String.valueOf(claimsDO.getRequestedOtherCharges1() + claimsDO.getRequestedOtherCharges2()));
				claimMailDO.setDeductible(String.valueOf(claimsVO.getDeductible()));
				claimMailDO.setLol(String.valueOf(claimsVO.getLol()));
				claimMailDO.setAvailableLol(String.valueOf(claimsVO.getAvailableLol()));
				mail.setClaimMailDO(claimMailDO);
				new Thread(mail).start();
			}*/
		}
		return (-1 == id) ? new Result("failure", null, "") : new Result("success", null, id);
	}
	
	private List<ClaimsDO> getClaimByStatus(final byte statusCode, HttpServletRequest request, boolean contractInfo){
		List<ClaimsDO> cliamsList = null;
		AccountDO account = getAccountDetails(request);
		if("admin".equals(account.getRoleName())){
			cliamsList = claimsService.getClaimsByCStatus(statusCode, contractInfo);
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
}


