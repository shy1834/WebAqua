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
	 * MenuManager�� list�޽�带 ȣ���Ͽ� 
	 * List�� response�� �����ϴ� �ҽ��ڵ尡 ����. 
	 * list.jsp���� response�� ����� List��ü�� �̿��Ѵ�.
	 */
	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response)
		throws Exception {

//		SSO ���� �� ��Ű�� ���� ��ü�� ��ȣȭ �� ������ Decode�ؼ� �Ѱ� �޴´�.
		SSOAuthorityManager manager = SSOAuthorityManager.instance();		
		User userInfo = manager.getCookiesUserInfo(request);		
		request.setAttribute("getCookiesUserInfo", userInfo);	
		
		return mapping.findForward("innerFrame");
	}

}
