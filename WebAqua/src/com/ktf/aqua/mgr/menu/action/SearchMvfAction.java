package com.ktf.aqua.mgr.menu.action;

import javax.servlet.http.*;

import org.apache.struts.action.*;

import com.ktf.aqua.common.CommonCheck;
import com.ktf.aqua.mgr.menu.*;

public class SearchMvfAction extends Action {
	
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) 
		throws Exception {
		
		String menuDev = request.getParameter("menu_dev");
		String menu1   = request.getParameter("menu1");
		String menu2   = request.getParameter("menu2");
		String menuType= request.getParameter("menuType");
		
		MenuManager manager = MenuManager.instance();		
		
		String mvfName = manager.getMvfName(menuDev); 
				
		if(mvfName == null || mvfName.equals("")){
			mvfName = "tstat/iniTialView.mvf";
		}
		
		/* ����� ���� �ҷ����� 
		session logic
		HttpSession s = request.getSession();
		String authType = (String)s.getAttribute("userLevel");
		*/
		
		//cookie logic
		CommonCheck common = new CommonCheck();
		String[] resultStr = common.sessionCheck(request); 
		String authType = resultStr[0];
		
		//CP�ϰ��
		if(authType.equals("4") && mvfName.equals("tstat/iniTialView.mvf") == false){	
			mvfName = mvfName.replace("tstat", "tstat_cp");
			mvfName = mvfName.replace(".", "_cp.");			
		}
		
		//�˻��̷�
		String[] menuStr = manager.searchLog("test", menuType, menu1, menu2);
		
		String path = "";
		//�ٿ�ε�&ȭ���̵� ���Ҽ��ֵ���
		if(menuStr != null && menuStr.length > 2 && menuStr[2] != null){
			if("ȭ���̵�".equals(menuStr[2].trim())){
				path = "page";
			}else if("�ٿ�ε�".equals(menuStr[2].trim())){
				path = "down";
			}else if("��Ʈ����".equals(menuStr[2].trim())){
				path = "streaming";
			}else if("ù�޴�����".equals(menuStr[2].trim())){
				path = "maiden";
			}else if("HTTP".equals(menuStr[2].trim())){
				path = "http";
			}
		}
		
		request.setAttribute("mvfName", mvfName);		 		
		request.setAttribute("path", path);
		request.setAttribute("menuStr", menuStr);
		
		return mapping.findForward("success");
	}
}
