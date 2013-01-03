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
	 * ���� ȭ���� ������ ����Ʈ
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
			
			//������������ �̵�
			if(resultStr[5].equals("true")){
				return mapping.findForward("success");
			}	
		}
		return mapping.findForward("login");
	}

	/**
	 * �α� �ƿ�
    */
	public ActionForward logout(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		logger = Logger.getLogger(this.getClass());
		HttpSession session = request.getSession();
		session.invalidate();
		
		// ��Ű����.
		Cookie cookie[] = request.getCookies();
		for(int i=0 ; i<cookie.length; i++)
		{
		   cookie[i].setMaxAge(0);		  
		   response.addCookie(cookie[i]);
		}
		
		return mapping.findForward("login");
	}

	/**
	 * �α���
	 */
	public ActionForward authority(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {	
		
		logger = Logger.getLogger(this.getClass());

		SSOAuthorityManager ssoManager = SSOAuthorityManager.instance();

		//Error Code -99�� ��� �� ����
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
				
				/* session => cookie�� ����
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
	 * ckErrCode = -1 (ID ����) ckErrCode = -2 (Password Ʋ��) ckErrCode = -3
	 * (System ���) ckErrCode = -11 (�α׿� �Ұ� : n-drama ��������) ���� = 0
	 */
	public String errorCode(HttpServletRequest request) {

		String chkSSOType = request.getParameter("SSO");
		String ckErrCode = "";
		// S.S.O. ���� ���� or �������� ó�� ��û�� ���
	
		if ("f".equals(chkSSOType)) {
			ckErrCode = request.getParameter("ErrCode");
		} else {
			ckErrCode = "0";
		}
		return ckErrCode;
	}

	/**
	 * ��Ű���� ����������� �о�´�.
	 * 
	 * @param request
	 * @return Stirng[0] - �����ID, Stirng[1] - ����ڸ�
	 */
	public Map getUserInfoInCookie(HttpServletRequest request) throws Exception {
		
		String name = "";
		String value = "";

		Map userInfo = new Hashtable();

		// ��Ű���� ��������� Ȯ��
		Cookie[] cookies = request.getCookies();

		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				name  = URLDecoder.decode(cookies[i].getName(), "EUC-KR");
				value = URLDecoder.decode(cookies[i].getValue(), "EUC-KR");
				userInfo.put(name, value);
				//System.out.println("��Ű:"+ name + "=" + value);
			}
			logger.info("��Ű-	 :  " + userInfo);			
		}
		return userInfo;
	}
}
