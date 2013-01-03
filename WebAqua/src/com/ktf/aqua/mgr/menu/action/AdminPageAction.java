package com.ktf.aqua.mgr.menu.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ktf.aqua.common.CommonCheck;
import com.ktf.aqua.mgr.menu.MenuManager;
import com.ktf.aqua.user.UserManager;

public class AdminPageAction extends Action{
	
	protected Logger log = Logger.getLogger( this.getClass() );

	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response)
		throws Exception {
			
		//cookies check
		CommonCheck common = new CommonCheck();
		String[] resultStr = common.sessionCheck(request);
		
		if( resultStr[5].equals("true") == false){			
			request.setAttribute("errMsg", "로그인 후 이용가능합니다.");
			return mapping.findForward("sessionError");
		}
		
		//메뉴리스트
		MenuManager menuManager = MenuManager.instance();
		request.setAttribute("adminMenuList", menuManager.getAdminMenuList());
		
		//메뉴관리일경우 width를 크게 한다.
		String width = "900";
		String menu = request.getParameter("menu");
		if(menu.equals("1") || menu.equals("2") || menu.equals("3") || menu.equals("4")){
			width = "1100";
		}
		
		request.setAttribute("page_width", width);
		
		return mapping.findForward("success");
	}
}
