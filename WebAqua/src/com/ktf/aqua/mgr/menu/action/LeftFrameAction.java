/*
 * Created on 2006. 2. 16.
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
import com.ktf.aqua.user.UserManager;
import com.ktf.aqua.user.common.User;

/**
 * @author redfox
 *
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class LeftFrameAction extends Action{
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
		SSOAuthorityManager managerAuthBySSO = SSOAuthorityManager.instance();		
		User userInfo = managerAuthBySSO.getCookiesUserInfo(request);		
		request.setAttribute("getCookiesUserInfo", userInfo);

		
		UserManager manager = UserManager.instance();
		boolean existedUser = manager.existedUser(userInfo.getUserId());	
		
		//user id�� ���ڷ� �޾� ����ڰ� aqua_user_info_tbl�� ���� �ϴ��� Ȯ�� �ϴ� �޼ҵ� 		
		request.setAttribute("existedUser", String.valueOf(existedUser));
		
		return mapping.findForward("left");
	}
}

	
