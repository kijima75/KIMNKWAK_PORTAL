package com.myhome.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

//import com.secuavail.lsc.security.SecurityLoginInfo;

/**
 * LSCにサクセスするリクエストをインターセプトして
 * セッションが有るか、どうか判断し、
 * ない場合はログイン画面に回す処理を行うクラス。
 * 
 * @author choijh
 *
 */
public class LoginCheckInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		/* セッションチェック*/
		/*if(SecurityLoginInfo.instance().getUserId().isEmpty() == false) 
			return true; 
        else {
        	response.sendRedirect("/admin/login/logout.do"); 	 
        	return false;
        }*/
		/*if(request.getSession().getAttribute(Constants.USER_ID) != null) 
			return true; 
        else {
        	response.sendRedirect("/admin/login/logout.do"); 	 
        	return false;
        }*/
		return true;
	}
}
