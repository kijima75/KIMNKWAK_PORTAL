package com.myhome.portal.web.account;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.myhome.portal.db.account.service.AccountManageService;

/**
 * account
 */
@Controller
public class AccountManageController {
	
	private AccountManageService accountManageService = new AccountManageService();
	
	private static final Logger logger = LoggerFactory.getLogger(AccountManageController.class);
	
	@RequestMapping(value = "/account/month_account.do", method = RequestMethod.GET)
	public String monthAccountManage(HttpServletRequest req, HttpServletResponse resp, Model model) {
		String rtnMenuPage = "/account/account_month_list.jsp"; 
		
		
		model.addAttribute("rtnMenuPage", rtnMenuPage);
		

		//src\main\webapp\WEB-INF\views\common\form\layout\layout.jsp
		return "forward:/WEB-INF/views/common/form/layout/layout.jsp";
	}
	
	/**
	 *
	 * 2019-04-23
	 * view map(kakao)
	 * @param req
	 * @param resp
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/location/locationView.do", method = RequestMethod.GET)
	public String locationView(HttpServletRequest req, HttpServletResponse resp, Model model) {
		String rtnMenuPage = "/location/location_view.jsp"; 
		
		
		model.addAttribute("rtnMenuPage", rtnMenuPage);
		

		//src\main\webapp\WEB-INF\views\common\form\layout\layout.jsp
		return "forward:/WEB-INF/views/common/form/layout/layout.jsp";
	}
	
	
	@RequestMapping("/account/getMonthAccount.do")
	public @ResponseBody Map<String,Object> dsvExport(HttpServletRequest req, HttpServletResponse resp) {
		Map<String,Object> model = new HashMap<String,Object>();
		String status  = "f";//Constants.STATUS_FAILED;
		String message = "";
		
		String targetMonth = req.getParameter("target_month");
		
		//return (value == null || value.equalsIgnoreCase("undefined") == true ? "" : value);
		//String regDate = DateUtil.getCurrentDate(DateUtil.FORMAT_YYYYMMDD_HHMMSS);
		//String dsvTargetId = getParamString(req, "dsv_target_id", "");
		//String dsvDestinyId  = getParamString(req, "dsv_destiny_id", "");
		
		try{
			
			
			accountManageService.selectAccountMonthList();
			
			//DsvMgmtService dsvMgmtService = new DsvMgmtService();
			/*int rtnValue = dsvMgmtService.insertDsvSelectedByUserId(dsvTargetId, dsvDestinyId);
			if(rtnValue>0){
				status  = Constants.STATUS_SUCCESS;
			} else if(rtnValue<0){
				//target user_id dsv dsat is not exist
				status  = Constants.STATUS_FAILED;
				message= messageSourceAccessor.getMessage("lsc.dsv.export.dsv.no_dsv_data");
			} else if(rtnValue==0){
				//save fail!!
				status  = Constants.STATUS_FAILED;
				message= messageSourceAccessor.getMessage("lsc.common.msg.fail_process_msg");
			}*/
		}catch ( Exception e) {
			e.printStackTrace();
			message=e.getMessage();
		}
		
		model.put("message", message);
		model.put("status", status);
		return model;
	}
	
	/*@RequestMapping("/dash/dsvExport.do")
	public @ResponseBody Map<String,Object> dsvExport(HttpServletRequest req, HttpServletResponse resp) {
		Map<String,Object> model = new HashMap<String,Object>();
		String status  = Constants.STATUS_FAILED;
		String message = "";
		
		String regDate = DateUtil.getCurrentDate(DateUtil.FORMAT_YYYYMMDD_HHMMSS);
		String dsvTargetId = getParamString(req, "dsv_target_id", "");
		String dsvDestinyId  = getParamString(req, "dsv_destiny_id", "");
		
		try{
			DsvMgmtService dsvMgmtService = new DsvMgmtService();
			int rtnValue = dsvMgmtService.insertDsvSelectedByUserId(dsvTargetId, dsvDestinyId);
			if(rtnValue>0){
				status  = Constants.STATUS_SUCCESS;
			} else if(rtnValue<0){
				//target user_id dsv dsat is not exist
				status  = Constants.STATUS_FAILED;
				message= messageSourceAccessor.getMessage("lsc.dsv.export.dsv.no_dsv_data");
			} else if(rtnValue==0){
				//save fail!!
				status  = Constants.STATUS_FAILED;
				message= messageSourceAccessor.getMessage("lsc.common.msg.fail_process_msg");
			}
		}catch ( Exception e) {
			e.printStackTrace();
			message=e.getMessage();
		}
		
		model.put("message", message);
		model.put("status", status);
		return model;
	}*/
	
	
}
