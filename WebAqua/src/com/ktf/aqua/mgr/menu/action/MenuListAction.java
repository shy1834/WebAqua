/*
 * Created on 2006. 2. 8.
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.ktf.aqua.mgr.menu.action;

import javax.servlet.http.*;

import org.apache.struts.action.*;

import com.ktf.aqua.common.*;
import com.ktf.aqua.mgr.menu.*;
import com.ktf.aqua.mgr.menu.data.*;
import com.ktf.aqua.mgr.menu.form.*;

public class MenuListAction extends Action{
	
	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response)
		throws Exception {

		//cookies check
		CommonCheck common = new CommonCheck();
		String[] resultStr = common.sessionCheck(request); 

		if(resultStr[5].equals("true") == false){
			request.setAttribute("errMsg", "�α��� �� �̿밡���մϴ�.");
			return mapping.findForward("sessionError");			
		}
		
		//�ѱ� ���� ����
 		request.setCharacterEncoding("euc-kr");
		ParamForm pForm = (ParamForm)form;
		
		/* session logic = > cookies logic
		HttpSession s = request.getSession();
		String AuthType = (String)s.getAttribute("userLevel");
		*/
		
		//cookies logic
		String AuthType = resultStr[0];	
		
		MenuManager manager = MenuManager.instance();

//����
System.out.println("MenuListAction : pForm.getMenuType() = " + pForm.getMenuType());

		request.setAttribute("searchTypeList", manager.getSearchType("1", "", pForm.getMenuType(), "", "", ""));		//menuLevel 1 : �˻�Ÿ��
		request.setAttribute("mainMenuList", manager.getSearchType("2", pForm.getMenuType(), "", "", "", AuthType));	//menuLevel 2 : 1���޴�
		request.setAttribute("pForm", pForm);

		request.setAttribute("selHour", DefaultData.getTimehour());
		request.setAttribute("selMin",  DefaultData.getTimemin());
				
		return mapping.findForward("success");
	}
}
