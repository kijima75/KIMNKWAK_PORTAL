/*package com.sa.cloud.web.portal.admin.account;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Lists;
import com.sa.cloud.cmm.Constants;
import com.sa.cloud.db.service.portal.admin.membership.AccountService;
import com.sa.cloud.db.service.portal.common.partner.PartnerMgmtService;
import com.sa.cloud.db.service.portal.user.membership.MemberService;
import com.sa.cloud.web.portal.common.BaseController;

@Controller
public class SocAccountManageController extends BaseController {
	*//**
	 * �꺆�궛�눇�릤
	 * @see Log
	 *//*
	protected Log logger = LogFactory.getLog(SocAccountManageController.class);

	*//**
	 * �궋�궖�궑�꺍�깉鼇�若싥궢�꺖�깛�궧
	 * @see AccountService
	 *//*
	protected AccountService accountService = new AccountService();
	
	*//**
	 * �깺�꺖�궣鼇�若싥궢�꺖�깛�궧
	 * @see AccountService
	 *//*
	protected MemberService userService = new MemberService();
	*//**
	 * �깙�꺖�깉�깏�꺖�깄�젿鼇�若싥궢�꺖�깛�궧
	 * @see PartnerService
	 *//*
	protected PartnerMgmtService partnerService = new PartnerMgmtService();
	*//**
	 * �깮�꺖�궭�꺂�깺�꺖�궣�궋�궖�궑�꺍�깉訝�誤㎫뵽�씊
	 *//*
	@RequestMapping("/soc/account/accountList.do")
	public String portalUserAccountList(HttpServletRequest req, Model model) {
		model.addAttribute("accountType", "portal");
		
		model.addAttribute("main_menu_list", menuService.selectPartnerMenuList(null, 13, Constants.MENU_LEVEL_MAIN));
		
		model.addAttribute("rtn_tab_menu_page", "admin/account/accountList.jsp");
		return "forward:/jsp/portal/admin/layout.jsp";
	}
	*//**
	 * SOC�궋�궖�궑�꺍�깉訝�誤㎫뵽�씊
	 *//*
	@RequestMapping("/soc/account/socAccountList.do")
	public String socUserAccountList(HttpServletRequest req, Model model) {
		model.addAttribute("accountType", "soc");
		model.addAttribute("rtn_tab_menu_page", "admin/account/socAccountList.jsp");
		return "forward:/jsp/portal/admin/layout.jsp";
	}
	@RequestMapping("/soc/account/contactInfo.do")
	public String contactInfoPg(HttpServletRequest req, HttpServletResponse resp, Model model) {
		model.addAttribute("rtn_tab_menu_page", "admin/account/inc_contact_info.jsp");
		int maxCount = 3; // SoC�뵪�겗鼇�若싥깪�꺖�궦�겎�겘1�깺�꺖�궣�겓�겇�걤��鸚�3餓뜰겲�겎�쇉�뙯�룾�꺗
		model.addAttribute("contactInfoMaxCount", maxCount);
		return "forward:/jsp/portal/admin/layout.jsp";
	}
	*//**
	 *��窈㎩�㏂겗訝�誤㎩룚孃�
	 * - SOC�깺�꺖�궣�겗�깙�꺖�깉�깏�꺖�겓�굠�겂�겍�뀱鴉싧룾�꺗�겒窈㎩�㏂겗�댍�솏�걣�걗�굥�� 
	 *//*
	@RequestMapping("/soc/account/getCustomerList.do")
	public @ResponseBody Model getCustomerList(HttpServletRequest req, HttpServletResponse resp, Model model) {
		Integer partnerInfoId = Integer.parseInt((String) req.getSession().getAttribute(Constants.PARTNER_ID));
		List<Map<String, Object>> result = Lists.newArrayList();
		model.addAttribute("status", Constants.STATUS_FAILED);
		try {
			result = accountService.getCustomerList(partnerInfoId);
			model.addAttribute("status", Constants.STATUS_SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		model.addAttribute("customerList", result);
		return model;
	}
	*//**
	 * �궋�궖�궑�꺍�깉�깄�젿訝�誤㎩룚孃�
	 * @param req
	 * @param resp
	 * @param model
	 * @return
	 *//*
	@RequestMapping("soc/account/getAccountList.do")
	public @ResponseBody Model getPortalUserAccountList(HttpServletRequest req, HttpServletResponse resp, Model model) {
		Integer partnerInfoId = getPartnerInfoId(req);
		String accountType = this.getParamString(req, "accountType", "");
		boolean isSocAccount = false;
		if (accountType.equalsIgnoreCase("soc")) {
			isSocAccount = true;
		}
		return getCommonAccountList(partnerInfoId, isSocAccount, model);
		
	}
	public Model getCommonAccountList(Integer partnerInfoId, boolean isSocAccount, Model model) {	
		// 鸚됪빊若싩쑴
		List<Map<String, Object>> accountList = null;
		
		try {
			// �닜�쐿�뙑
			model.addAttribute("status", Constants.STATUS_FAILED);
			// �깺�꺖�궣�깄�젿訝�誤㎩룚孃�			
			accountList = accountService.selectAccountList(partnerInfoId, isSocAccount);
			// accountList �룚孃쀣겎�걤�굦�겙閭ｅ만�궧�깇�꺖�궭�궧
			if (accountList != null) {
				model.addAttribute("status", Constants.STATUS_SUCCESS);
			}
			// �ㅸ퓭�뜶
			model.addAttribute("accountList", accountList);

		}
		catch (Exception e) {
			e.printStackTrace();
			return model;
		}

		return model;
	}
	
	*//**
	 * �깙�꺖�깉�깏�꺖�깄�젿訝�誤㎩룚孃�
	 * @param req
	 * @param resp
	 * @param model
	 * @return
	 *//*
	@RequestMapping("soc/account/getPartnerList.do")
	public @ResponseBody Model getPartnerList(HttpServletRequest req, HttpServletResponse resp, Model model) {
		// 鸚됪빊若싩쑴
		List<Map<String, Object>> partnerList = null;
		
		try {

			// �닜�쐿�뙑
			model.addAttribute("status", Constants.STATUS_FAILED);

			// �깙�꺖�깉�깏�꺖�깄�젿訝�誤㎩룚孃�
			String partnerInfoId = (String) req.getSession().getAttribute(Constants.PARTNER_ID);
			partnerList = partnerService.selectPartnerList(partnerInfoId);

			// accountList �룚孃쀣겎�걤�굦�겙閭ｅ만�궧�깇�꺖�궭�궧
			if (partnerList != null) {
				model.addAttribute("status", Constants.STATUS_SUCCESS);
			}

			// �ㅸ퓭�뜶
			model.addAttribute("partnerList", partnerList);

		}
		catch (Exception e) {
			e.printStackTrace();
			return model;
		}

		return model;
	}
	
	*//**
	 * �궋�궖�궑�꺍�깉�깄�젿�룚孃�
	 * @param req
	 * @param resp
	 * @param model
	 * @return
	 *//*
	@RequestMapping("soc/account/getAccountInfo.do")
	public @ResponseBody Model getAccountInfo(HttpServletRequest req, HttpServletResponse resp, Model model) {
		// 鸚됪빊若싩쑴
		Map<String, Object> accountInfo = null;
		List<Map<String, Object>> menuInfoList = null;
		Map<String, Object> param = new HashMap<String, Object>();
		
		try {
			String userInfoId = getParamString(req, Constants.USER_ID);
			// �닜�쐿�뙑
			model.addAttribute("status", Constants.STATUS_FAILED);
			//���깙�꺀�깳�꺖�궭鼇�若�
			param.put("userInfoId", userInfoId);
			// �깺�꺖�궣�깄�젿�룚孃�
			accountInfo = accountService.getAccountInfo(param);
			menuInfoList = accountService.getMenuInfo(param);	//level one menu
			
			model.addAttribute("accountInfo", accountInfo);
			model.addAttribute("menuInfoList", menuInfoList);
			model.addAttribute("status", Constants.STATUS_SUCCESS);
		}
		catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("accountInfo", null);
			model.addAttribute("menuInfoList", null);
			return model;
		}
		return model;
	}
	
	*//**
	 * �궋�궖�궑�꺍�깉�깄�젿�쇉�뙯
	 * @param req
	 * @param resp
	 * @param model
	 * @return
	 *//*
	@RequestMapping("soc/account/registerAccountInfo.do")
	public @ResponseBody Model registerAccountInfo(HttpServletRequest req, HttpServletResponse resp, Model model) {
		// �궋�궖�궑�꺍�깉�깄�젿鼇�若�
		String partnerInfoId = req.getParameter("partner");
		String customerInfoId = (String) req.getSession().getAttribute(Constants.SITE_CODE);
		String issure = (String) req.getSession().getAttribute(Constants.USER_ID);
		String login_id = req.getParameter("login_id");
		String name = req.getParameter("name");
		String kana = getParamString(req, "kana", "");
		String tel = getParamString(req, "tel", "");
		String com_name = getParamString(req, "com_name", "");
		String com_kana = getParamString(req, "com_kana", "");
		String zip1 = getParamString(req, "zip1", "");
		String zip2 = getParamString(req, "zip2", "");
		String tzone = getParamString(req, "tzone", "JST");
		String companyFlg = req.getParameter("company_flg");
		String userLevel = req.getParameter("user_level");
		String userType = getParamString(req, "user_type", Constants.USER_TYPE_SOCMEMBER);
		// �깳�깑�깷�꺖�깄�젿鼇�若�
		String menu_noticeList = req.getParameter("menu_noticeList");
		String menu_moduleList = req.getParameter("menu_moduleList");
		String menu_monitoringSetting = req.getParameter("menu_monitoringSetting");
		String menu_deviceMaster = req.getParameter("menu_deviceMaster");
		String menu_accountList = req.getParameter("menu_accountList");
		String menu_license = req.getParameter("menu_license");
		String menu_lscSettingDetail = req.getParameter("menu_lscSettingDetail");
		String menu_lscList = req.getParameter("menu_lscList");
		String menu_apoloAllocManage = req.getParameter("menu_apoloAllocManage");
		
		String systemType = getParamString(req, "systemType", "");
		String menuCheckedListStr = getParamString(req, "menuCheckedList", null);
		String menuUncheckedListStr = getParamString(req, "menuUncheckedList", null);
		List<String> menuCheckedList = getMenuList(menuCheckedListStr);			
		List<String> menuUncheckedList = getMenuList(menuUncheckedListStr);
		
		Integer pref = 0;
		if ((req.getParameter("pref") != null) && (!(req.getParameter("pref")).equals(""))) {
			pref = Integer.parseInt(req.getParameter("pref"));
		}
		String addr1 = getParamString(req, "addr1", "");
		String addr2 = getParamString(req, "addr2", "");
		String password = req.getParameter("password");
		
		try {
			
			// �궋�궖�궑�꺍�깉�깄�젿�쇉�뙯
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("partner_info_id", Integer.parseInt(partnerInfoId));
			param.put("customer_info_id", customerInfoId);
			param.put("set_userid", issure);
			param.put("login_id", login_id);
			param.put("name", name);
			param.put("kana", kana);
			param.put("tel", tel);
			param.put("com_name", com_name);
			param.put("com_kana", com_kana);
			param.put("zip1", zip1);
			param.put("zip2", zip2);
			param.put("pref", pref);
			param.put("addr1", addr1);
			param.put("addr2", addr2);
			param.put("password", password);
			param.put("tzone", tzone);
			param.put("companyFlg", Integer.parseInt(companyFlg));
			param.put("userLevel", Integer.parseInt(userLevel));
			param.put("userType", Integer.parseInt(userType));

			if (accountService.insertAccount(param, menuCheckedList, menuUncheckedList)) {
				model.addAttribute("status", Constants.STATUS_SUCCESS);
			} else {
				model.addAttribute("status", Constants.STATUS_FAILED);
				model.addAttribute("message", "");
				return model;
			}
			
			// �깳�깑�깷�꺖�깄�젿�쎍�뼭
			param.put("userInfoId", param.get("userId"));
			updateMenu(param,menu_noticeList,menu_moduleList,menu_monitoringSetting,
					menu_deviceMaster,menu_accountList,menu_license,menu_lscSettingDetail,
					menu_lscList,menu_apoloAllocManage);
		}
		catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("status", Constants.STATUS_FAILED);
			model.addAttribute("message", e.getMessage());
			model.addAttribute("exception", super.getStackTrace(e));
		}

		return model;
	}
	
	*//**
	 * �궋�궖�궑�꺍�깉�깄�젿�뎷�솮
	 * @param req
	 * @param resp
	 * @param model
	 * @return
	 *//*
	@RequestMapping("soc/account/deleteAccountInfo.do")
	public @ResponseBody Model deleteAccountInfo(HttpServletRequest req, HttpServletResponse resp, Model model) {
		// �뎷�솮�눇�릤
		String userInfoId = req.getParameter("userInfoId");
		String customerInfoId = req.getParameter("customerInfoId");
		String loginId = req.getParameter("loginId");
		
		try {
			//���깺�꺖�궣�깄�젿�뎷�솮
			if (userService.delUserDetail(customerInfoId, userInfoId, loginId)) {
				
				*//***********************************
				 *                                 *
				 *                                 *
				 * �깺�꺖�궣驪롢겗鼇�若싩춬�굮�뎷�솮�걲�굥�졃�릦�곥걪�걪�겓鼇섋펹 *
				 *                                 *
				 *                                 *
				 ***********************************//*
				
				// �궖�궧�궭�깯�깄�젿�뎷�솮
				if (accountService.delCustomer(customerInfoId)) {
					
					//���궖�궧�궭�깯驪롢겗鼇�若싩춬�굮�뎷�솮�걲�굥�졃�릦�곥걪�걪�겓鼇섋펹
					*//************************************
					 *                                  *
					 *                                  *
					 * �궖�궧�궭�깯驪롢겗鼇�若싩춬�굮�뎷�솮�걲�굥�졃�릦�곥걪�걪�겓鼇섋펹 *
					 *                                  *
					 *                                  *
					 ************************************//*
					
					model.addAttribute("status", Constants.STATUS_SUCCESS);
				} else {
					model.addAttribute("status", Constants.STATUS_FAILED);
					model.addAttribute("message", "�궖�궧�궭�깯�뎷�솮�겓鸚길븮�걮�겲�걮�걼��");
				}
			} else {
				model.addAttribute("status", Constants.STATUS_FAILED);
				model.addAttribute("message", "�궋�궖�궑�꺍�깉�뎷�솮�겓鸚길븮�걮�겲�걮�걼��");
			}

		}
		catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("status", Constants.STATUS_FAILED);
			model.addAttribute("message", e.getMessage());
			model.addAttribute("exception", super.getStackTrace(e));
		}

		return model;
	}
	
	*//**
	 * �궋�궖�궑�꺍�깉�깄�젿�쎍�뼭
	 * @param req
	 * @param resp
	 * @param model
	 * @return
	 *//*
	@RequestMapping("soc/account/updateAccountInfo.do")
	public @ResponseBody Model updateAccountInfo(HttpServletRequest req, HttpServletResponse resp, Model model) {
		// �쎍�뼭�눇�릤�굮瓦썲뒥
		String partnerInfoId = req.getParameter("partner");
		String customerInfoId = (String) req.getSession().getAttribute(Constants.SITE_CODE);
		String issure = (String) req.getSession().getAttribute(Constants.USER_ID);
		String userInfoId = req.getParameter("userInfoId");
		String login_id = req.getParameter("login_id");
		String name = req.getParameter("name");
		String kana = getParamString(req, "kana", "");
		String tel = getParamString(req, "tel", "");
		String com_name = getParamString(req, "com_name", "");
		String com_kana = getParamString(req, "com_kana", "");
		String zip1 = getParamString(req, "zip1", "");
		String zip2 = getParamString(req, "zip2", "");
		String tzone = getParamString(req, "tzone", "JST");
		String companyFlg = req.getParameter("company_flg");
		String userLevel = req.getParameter("user_level");
		
		// �깳�깑�깷�꺖�깄�젿鼇�若�
		String menu_noticeList = req.getParameter("menu_noticeList");
		String menu_moduleList = req.getParameter("menu_moduleList");
		String menu_monitoringSetting = req.getParameter("menu_monitoringSetting");
		String menu_deviceMaster = req.getParameter("menu_deviceMaster");
		String menu_accountList = req.getParameter("menu_accountList");
		String menu_license = req.getParameter("menu_license");
		String menu_lscSettingDetail = req.getParameter("menu_lscSettingDetail");
		String menu_lscList = req.getParameter("menu_lscList");
		String menu_apoloAllocManage = req.getParameter("menu_apoloAllocManage");
		
		String systemType = getParamString(req, "systemType", "");
		String menuCheckedListStr = getParamString(req, "menuCheckedList", null);
		String menuUncheckedListStr = getParamString(req, "menuUncheckedList", null);
		List<String> menuCheckedList = getMenuList(menuCheckedListStr);			
		List<String> menuUncheckedList = getMenuList(menuUncheckedListStr);
		
		Integer pref = 0;
		if ((req.getParameter("pref") != null) && (!(req.getParameter("pref")).equals(""))) {
			pref = Integer.parseInt(req.getParameter("pref"));
		}
		String addr1 = getParamString(req, "addr1", "");
		String addr2 = getParamString(req, "addr2", "");
		String password = req.getParameter("password");
		if (password.isEmpty()) {
			password = null;
		}
		
		try {
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("partnerInfoId", Integer.parseInt(partnerInfoId));
			param.put("partner_info_id", Integer.parseInt(partnerInfoId));
			param.put("customer_info_id", customerInfoId);
			param.put("set_userid", issure);
			param.put("userInfoId", userInfoId);
			param.put("login_id", login_id);
			param.put("name", name);
			param.put("kana", kana);
			param.put("tel", tel);
			param.put("com_name", com_name);
			param.put("com_kana", com_kana);
			param.put("zip1", zip1);
			param.put("zip2", zip2);
			param.put("pref", pref);
			param.put("addr1", addr1);
			param.put("addr2", addr2);
			param.put("password", password);
			param.put("tzone", tzone);
			param.put("companyFlg", Integer.parseInt(companyFlg));
			param.put("userLevel", Integer.parseInt(userLevel));

			if (accountService.updAccount(param, menuCheckedList, menuUncheckedList)) {
				model.addAttribute("status", Constants.STATUS_SUCCESS);

				model.addAttribute("userid", param.get("userId"));
				model.addAttribute("mailaddr", login_id);
				model.addAttribute("username", name);
				model.addAttribute("ctmid", param.get("ctmId"));
			} else {
				model.addAttribute("status", Constants.STATUS_FAILED);
				model.addAttribute("message", "");
			}
			
			// �깳�깑�깷�꺖�깄�젿�쎍�뼭
			updateMenu(param,menu_noticeList,menu_moduleList,menu_monitoringSetting,
					menu_deviceMaster,menu_accountList,menu_license,menu_lscSettingDetail,
					menu_lscList,menu_apoloAllocManage);
		}
		catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("status", Constants.STATUS_FAILED);
			model.addAttribute("message", e.getMessage());
			model.addAttribute("exception", super.getStackTrace(e));
		}

		return model;
	}
	
	*//**
	 * �깳�깑�깷�꺖�깄�젿�쎍�뼭
	 * @return
	 *//*
	private void updateMenu(Map<String, Object> param,String menu_noticeList,String menu_moduleList,
			String menu_monitoringSetting,String menu_deviceMaster,String menu_accountList,String menu_license,
			String menu_lscSettingDetail,String menu_lscList,String menu_apoloAllocManage) {
		
		List<Integer> arrayUpdateMenuViewId = new ArrayList<Integer>();
		List<Integer> arrayDeleteMenuViewId = new ArrayList<Integer>();
		
		if ((Integer) param.get("userLevel") == Constants.USER_LEVEL_SOC) {
			if (menu_noticeList != null) {
				arrayUpdateMenuViewId.add(1000);
				arrayUpdateMenuViewId.add(1001);
			}
			else {
				arrayDeleteMenuViewId.add(1000);
				arrayDeleteMenuViewId.add(1001);
			}
			// 訝��닾�깺�꺖�궣�릲�걨�겗�걡�윥�굢�걵�뵽�씊�겘SoC�깺�꺖�궣�겓恙낁쫨�겒�걚
			arrayDeleteMenuViewId.add(1100);
			arrayDeleteMenuViewId.add(1101);
		} else {
			if (menu_noticeList != null) {
				arrayUpdateMenuViewId.add(1100);
				arrayUpdateMenuViewId.add(1101);
				
			}
			else {
				arrayDeleteMenuViewId.add(1100);
				arrayDeleteMenuViewId.add(1101);
			}
			// SoC�깺�꺖�궣�릲�걨�겗�걡�윥�굢�걵�뵽�씊�겘訝��닾�깺�꺖�궣�겓恙낁쫨�겒�걚
			arrayDeleteMenuViewId.add(1000);
			arrayDeleteMenuViewId.add(1001);
		}
		
		// �썵誤뽧깴�궦�깷�꺖�꺂嶸←릤
		if (menu_moduleList != null || menu_monitoringSetting != null || menu_deviceMaster != null) {
			arrayUpdateMenuViewId.add(1200);
		} else {
			arrayDeleteMenuViewId.add(1200);
		}
		if (menu_moduleList != null) {
			arrayUpdateMenuViewId.add(1201);
		} else {
			arrayDeleteMenuViewId.add(1201);
		}
		if (menu_monitoringSetting != null) {
			arrayUpdateMenuViewId.add(1202);
		} else {
			arrayDeleteMenuViewId.add(1202);
		}
		if (menu_deviceMaster != null) {
			arrayUpdateMenuViewId.add(1203);
		} else {
			arrayDeleteMenuViewId.add(1203);
		}

		// �궋�궖�궑�꺍�깉嶸←릤
		if (menu_accountList != null) {
			arrayUpdateMenuViewId.add(2010);
			arrayUpdateMenuViewId.add(2011);
		} else {
			arrayDeleteMenuViewId.add(2010);
			arrayDeleteMenuViewId.add(2011);
		}
		
		// LSC鼇�若싩�←릤
		if (menu_license != null || menu_lscSettingDetail != null || menu_lscList != null) {
			arrayUpdateMenuViewId.add(2100);
		} else {
			arrayDeleteMenuViewId.add(2100);
		}
		if (menu_license != null) {
			arrayUpdateMenuViewId.add(2101);
		} else {
			arrayDeleteMenuViewId.add(2101);
		}
		if (menu_lscSettingDetail != null) {
			arrayUpdateMenuViewId.add(2102);
		} else {
			arrayDeleteMenuViewId.add(2102);
		}
		if (menu_lscList != null) {
			arrayUpdateMenuViewId.add(2103);
		} else {
			arrayDeleteMenuViewId.add(2103);
		}
		
		// APOLO嶸←릤
		if (menu_apoloAllocManage != null) {
			arrayUpdateMenuViewId.add(2200);
			arrayUpdateMenuViewId.add(2201);
		} else {
			arrayDeleteMenuViewId.add(2200);
			arrayDeleteMenuViewId.add(2201);
		}
		
		if (arrayUpdateMenuViewId.size() != 0) {
			param.put("deleteFlg", 0);
			for (Integer updateMenuViewId : arrayUpdateMenuViewId) {
				param.put("menuViewId", updateMenuViewId);
				accountService.updSocMenu(param);
			}
		}
		if (arrayDeleteMenuViewId.size() != 0) {
			param.put("deleteFlg", 1);
			for (Integer deleteMenuViewId : arrayDeleteMenuViewId) {
				param.put("menuViewId", deleteMenuViewId);
				accountService.updSocMenu(param);
			}
		}
	}
	
	@RequestMapping("/soc/menu/selectMenuListOfPartnerWithUserMenu.do")
	public @ResponseBody Model selectMenuListOfPartnerWithUserMenu(HttpServletRequest req, HttpServletResponse resp, Model model) {
		String socUserPartnerInfoId = (String) req.getSession().getAttribute(Constants.PARTNER_ID);
		String partnerInfoId = getParamString(req, "partnerInfoId");
		String userId = getParamString(req, "userId");
		if(partnerInfoId.equals("")){
			model.addAttribute("status", Constants.STATUS_FAILED);
			model.addAttribute("message", "partnerInfoId is required.");
			return model;
		}
		try {
			*//**
			 * SOC�깳�꺍�깘�꺖�겗partnerInfoId(socUserPartnerInfoId)�걣�읃繹뽧겓�겒�겂�겍��
			 * socUserPartnerInfoId�겗�깳�깑�깷�꺖�겗訝��겎��
			 * partnerInfoId�걣鵝욍걟�굥�깳�깑�깷�꺖�꺁�궧�깉�굮瓦붵걲��
			 * �걼�걽�걅yPartnerInfoId�겗�깳�깑�깷�꺖::has_menu�걣partnerInfoId�걣鵝욍걟�굥�깳�깑�깷�꺖�겏�겒�굥��
			 *//*
			List<Map<String, Object>> menuList = accountService.selectMenuListOfPartnerWithUserMenu(userId, Integer.valueOf(partnerInfoId), Constants.MENU_SYTEM_TYPE_SOC);
			System.out.println("partnerInfoId:"+partnerInfoId + ",userId:"+userId + ",menuList count : "+menuList.size());
			model.addAttribute("menuList", menuList);
			model.addAttribute("status", Constants.STATUS_SUCCESS);
		}
		catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("status", Constants.STATUS_FAILED);
			model.addAttribute("message", e.getMessage());
			model.addAttribute("exception", super.getStackTrace(e));
			return model;
		}
		return model;
	}
	
	private List<String> getMenuList(String menuListStr) {
		List<String> menuList = Lists.newArrayList();			
		if (StringUtils.isEmpty(menuListStr) == false) {
			menuList = Arrays.asList(menuListStr.split(";"));
		}		
		return menuList;
	}
}
*/