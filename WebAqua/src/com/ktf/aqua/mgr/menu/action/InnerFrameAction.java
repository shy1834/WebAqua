/*
 * Created on 2006. 2. 20.
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.ktf.aqua.mgr.menu.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ktf.aqua.user.SSOAuthorityManager;
import com.ktf.aqua.user.common.User;

/**
 * @author redfox
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class InnerFrameAction  extends Action{
	/**
	 * MenuManager의 list메써드를 호출하여 
	 * List를 response에 저장하는 소스코드가 들어간다. 
	 * list.jsp에서 response에 저장된 List객체를 이용한다.
	 */
	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response)
		throws Exception {

//		SSO 인증 후 쿠키에 담은 개체를 암호화 된 값들을 Decode해서 넘겨 받는다.
		SSOAuthorityManager manager = SSOAuthorityManager.instance();		
		User userInfo = manager.getCookiesUserInfo(request);		
		request.setAttribute("getCookiesUserInfo", userInfo);	
		
		return mapping.findForward("innerFrame");
	}

}
