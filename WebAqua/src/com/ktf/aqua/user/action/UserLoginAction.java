/*
 * Created on 2006. 1. 24.
 */
package com.ktf.aqua.user.action;

import java.net.*;
import java.util.*;

import javax.servlet.http.*;

import org.apache.log4j.*;
import org.apache.struts.action.*;

import com.edsk.framework.common.*;
import com.ktf.aqua.common.*;
import com.ktf.aqua.user.*;
import com.ktf.aqua.user.common.*;
import com.ktf.aqua.user.dao.*;

public class UserLoginAction extends AbstractAction {
	
	protected Logger logger = null;

	/**
	 * 메인 화면의 컨텐츠 리스트
	 */
	public ActionForward login(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		Cookie[] cookie = request.getCookies();
		
		if(cookie == null){
			Cookie ck  = new Cookie("tempcookie", "");
			response.addCookie(ck);
		}else{		
			//session check
			CommonCheck common = new CommonCheck();
			String[] resultStr = common.sessionCheck(request); 
			
			//메인페이지로 이동
			if(resultStr[5].equals("true")){
				return mapping.findForward("success");
			}	
		}
		return mapping.findForward("login");
	}

	/**
	 * 로그 아웃
    */
	public ActionForward logout(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		logger = Logger.getLogger(this.getClass());
		HttpSession session = request.getSession();
		session.invalidate();
		
		// 쿠키삭제.
		Cookie cookie[] = request.getCookies();
		for(int i=0 ; i<cookie.length; i++)
		{
		   cookie[i].setMaxAge(0);		  
		   response.addCookie(cookie[i]);
		}
		
		return mapping.findForward("login");
	}

	/**
	 * 로그인
	 */
	public ActionForward authority(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {	
		
		logger = Logger.getLogger(this.getClass());

		SSOAuthorityManager ssoManager = SSOAuthorityManager.instance();

		//Error Code -99인 경우 는 정상
		String errCode = errorCode(request);
		request.setAttribute("ErrCode", String.valueOf(errCode));
		
		String path = "";
		String id 	= "";

		if(errCode.equals("0")){
			User userInfo = ssoManager.getCookiesUserInfo(request);
			UserDAO u = new UserDAO();
			id = userInfo.getUserId();
			String[] resultStr = u.loginProcess(userInfo.getUserId(), userInfo.getMobile(), userInfo.getRegRgstNum());
			
			if(resultStr[1].equals("notAllowed") == false){
				
				/* session => cookie로 변경
				HttpSession session = request.getSession();			
				session.setAttribute("userName", userInfo.getName());
				session.setAttribute("userId", userInfo.getUserId());
				session.setAttribute("userLevel", resultStr[0]);
				session.setAttribute("cpId", resultStr[2]);
				session.setAttribute("userType", resultStr[3]);
				*/

				Cookie cookie  = new Cookie("userName", URLEncoder.encode(userInfo.getName(), "euc-kr"));
				response.addCookie(cookie);
				Cookie cookie1 = new Cookie("userId", userInfo.getUserId());
				response.addCookie(cookie1);
				Cookie cookie2 = new Cookie("userLevel", resultStr[0]);
				response.addCookie(cookie2);
				Cookie cookie3 = new Cookie("cpId", resultStr[2]);
				response.addCookie(cookie3);
				Cookie cookie4 = new Cookie("userType", resultStr[3]);
				response.addCookie(cookie4);
			}
			
			path = resultStr[1];			
			
			if(request.getServerPort() == 80 && id.equals("sbk1126") == false 
				&& id.equals("kymj016") == false && id.equals("mybang74") == false
				&& id.equals("ahmax") == false  && resultStr[0].equals("4") == false){
				path = "restrict";
			}
		}else{			
			String errMsg  = ssoManager.getSSOErrorMessage(errCode);
			request.setAttribute("errMsg", errMsg);
			path = "authority";
		}
		
		return mapping.findForward(path); 
	}

	/**
	 * ckErrCode = -1 (ID 없음) ckErrCode = -2 (Password 틀림) ckErrCode = -3
	 * (System 장애) ckErrCode = -11 (로그온 불가 : n-drama 여성전용) 정상 = 0
	 */
	public String errorCode(HttpServletRequest request) {

		String chkSSOType = request.getParameter("SSO");
		String ckErrCode = "";
		// S.S.O. 인증 성공 or 페이지를 처음 요청한 경우
	
		if ("f".equals(chkSSOType)) {
			ckErrCode = request.getParameter("ErrCode");
		} else {
			ckErrCode = "0";
		}
		return ckErrCode;
	}

	/**
	 * 쿠키에서 사용자정보를 읽어온다.
	 * 
	 * @param request
	 * @return Stirng[0] - 사용자ID, Stirng[1] - 사용자명
	 */
	public Map getUserInfoInCookie(HttpServletRequest request) throws Exception {
		
		String name = "";
		String value = "";

		Map userInfo = new Hashtable();

		// 쿠키에서 사용자정보 확인
		Cookie[] cookies = request.getCookies();

		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				name  = URLDecoder.decode(cookies[i].getName(), "EUC-KR");
				value = URLDecoder.decode(cookies[i].getValue(), "EUC-KR");
				userInfo.put(name, value);
				//System.out.println("쿠키:"+ name + "=" + value);
			}
			logger.info("쿠키-	 :  " + userInfo);			
		}
		return userInfo;
	}
}
