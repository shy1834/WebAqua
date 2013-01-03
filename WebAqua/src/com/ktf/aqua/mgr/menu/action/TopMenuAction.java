/*
 * Created on 2006. 2. 10.
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

import com.ktf.aqua.common.CommonCheck;
import com.ktf.aqua.mgr.menu.MenuManager;
import com.ktf.aqua.mgr.menu.form.ParamForm;


public class TopMenuAction  extends Action{

	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response)
		throws Exception {
	
		/* 사용자 권한 불러오기 (session) = > (cookie)
		HttpSession session = request.getSession();
		String authType = (String)session.getAttribute("userLevel");
		*/
		
		//사용자 권한 불러오기 (cookie)
		CommonCheck common = new CommonCheck();
		String[] resultStr = common.sessionCheck(request); 
		
		String username = resultStr[2];
	    String authType = resultStr[0];

//삭제	    
System.out.println("TopMenuAction : username = " + username);	//AHM
System.out.println("TopMenuAction : authType = " + authType);	//00

		request.setAttribute("userName", username);
		
		//메뉴리스트
		MenuManager menuManager = MenuManager.instance();
		request.setAttribute("mainMenu", menuManager.getSearchType("0", "", "", "", "", authType));

		return mapping.findForward("success");
	}
}
