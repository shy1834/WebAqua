/*
 * Created on 2006. 2. 8.
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.ktf.aqua.mgr.menu.action;

import java.util.*;

import javax.servlet.http.*;

import org.apache.struts.action.*;

import com.ktf.aqua.mgr.menu.*;
import com.ktf.aqua.user.*;
import com.ktf.aqua.user.common.*;

/**
 * @author redfox
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class MenuListAction3  extends Action{
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
		// �ѱ� ���� ����
		request.setCharacterEncoding("euc-kr");

		// ���� ���� ���� ����(ȭ�� UI  ���� �Ķ���� ����Ʈ)
		String cdma_dev = "";
		String menuList = "";
		String menuId = "";
		String plf_dev = "";
		String menu_dev = "";
		String svc_dev = "";
		String svc_dev_1 = "";
		String data_dev = "";
		String dayType = "";
		String from_date = "";
		String to_date = "";
		String from_time = "";
		String to_time = "";
		String data_dev_val = "";
		String url_val = "";
		String srh_cnt = "";
		String svc_sort = "";
		String sort_dev = "";
		String p_name = "";
		String svc_name = "";
		String type = "";
		String plf_name = "";
		String log_type = "";
		String path = "";
		String select_type = "";
		String error_code = "";
		String tcs_num_val = "";
		String min_type = "";
		String min_val = "";
		String input_val = "";
		String select_list = "";
		String input_val1 = "";
		String select_list1 = "";
		String from_low = "";
		String to_high = "";
		String sFlag = "";
		
		int plf_code = 0;
		int dev_code = 0;
		int upper_code = 0;
		
		cdma_dev = request.getParameter("cdma_dev");
		menuId = request.getParameter("menuId");		
		menu_dev = request.getParameter("menu_dev");
		svc_dev = request.getParameter("svc_dev");
		svc_dev_1 = request.getParameter("svc_dev_1");
		data_dev = request.getParameter("data_dev");
		dayType = request.getParameter("dayType");
		from_date = request.getParameter("from_date");
		to_date = request.getParameter("to_date");
		from_time = request.getParameter("from_time");
		to_time = request.getParameter("to_time");
		data_dev_val = request.getParameter("data_dev_val");
		url_val = request.getParameter("url_val");
		srh_cnt = request.getParameter("srh_cnt");
		svc_sort = request.getParameter("svc_sort");
		sort_dev = request.getParameter("sort_dev");
		p_name = request.getParameter("p_name");
		svc_name = request.getParameter("svc_name");
		type = request.getParameter("type");
		plf_name = request.getParameter("plf_name");
		log_type = request.getParameter("log_type");
		path = request.getParameter("path");
		select_type = request.getParameter("select_type");
		error_code = request.getParameter("error_code");
		tcs_num_val = request.getParameter("tcs_num_val");
		min_type = request.getParameter("min_type");
		min_val = request.getParameter("min_val");
		input_val = request.getParameter("input_val");
		select_list = request.getParameter("select_list");
		input_val1 = request.getParameter("input_val1");
		select_list1 = request.getParameter("select_list1");
		from_low = request.getParameter("from_low");
		to_high = request.getParameter("to_high");
		sFlag = request.getParameter("sFlag");

		menuList = path;

		//SSO ���� �� ��Ű�� ���� ��ü�� ��ȣȭ �� ������ Decode�ؼ� �Ѱ� �޴´�.
		SSOAuthorityManager managerAuthBySSO = SSOAuthorityManager.instance();		
		User userInfo = managerAuthBySSO.getCookiesUserInfo(request);		
		request.setAttribute("getCookiesUserInfo", userInfo);

		//����� ���� Ÿ�� �ڵ带 ������ �´�.
		UserManager userManager = UserManager.instance();
		String strUserAuthType = userManager.getUserAuthType(userInfo.getUserId());
		request.setAttribute("getUserAuthType", strUserAuthType);
		
		//����� Ÿ��(USER_TYPE)�� �����´�.
		String strUserType = userManager.getUserType(userInfo.getUserId());
		request.setAttribute("getUserType",strUserType );

		// �ܺλ������ ��� �ش� cp_id�� �����´�.
		if(strUserAuthType.equals("04")) {
			String strCpId = userManager.getCpId(userInfo.getUserId());
			request.setAttribute("getCpId",strCpId );
		}
		
		MenuManager manager = MenuManager.instance();
		
		String AuthType = strUserAuthType.substring(1,2);
		
		//���θ޴� ����Ʈ�� �������� �޼ҵ�
		if("2".equals(cdma_dev)){
		    List mainMenuList = manager.findMainaddtotList(AuthType);
            request.setAttribute("mainMenuList", mainMenuList);
		}else{
			List mainMenuList = manager.findMainMenuList(AuthType);
		    request.setAttribute("mainMenuList", mainMenuList);			  
		}
		
		//����޴� ����Ʈ�� �������� �޼ҵ�
		if("2".equals(cdma_dev)){
		    List subMenuList = manager.findSubaddtotList(AuthType);
		    request.setAttribute("subMenuList", subMenuList);
		}else{
		    List subMenuList = manager.findSubMenuList(AuthType);
		    request.setAttribute("subMenuList", subMenuList);
		   /* List subMenuList1 = manager.findSubLogMenuList(subMenuList);*/
		   
		}
		
		
		//�����޴� ����Ʈ�� �������� �޼ҵ�
		List detailMenuList = manager.findDetailMenuList(menuId, AuthType);
		request.setAttribute("detailMenuList", detailMenuList);
		
		if (menuId.substring(0,2).equals("51")){ // SMS
			plf_dev = menuId.substring(2,4);			
			if (menu_dev == null) { 	
			   if(plf_dev.equals("01")){
				   menu_dev = menuId.substring(0,4)+"01";
			   }else if(plf_dev.equals("02")){
				   menu_dev = menuId.substring(0,4)+"01";
			   }else if(plf_dev.equals("03")){
				   menu_dev = menuId.substring(0,4)+"01";
			   }else if(plf_dev.equals("04")){
				   menu_dev = menuId.substring(0,4)+"01";
			   }else if(plf_dev.equals("05")){
				   menu_dev = menuId.substring(0,4)+"01";
			   }
		    }
		}else if(menuId.substring(0,2).equals("52")){ // ARS
			plf_dev = menuId.substring(2,4);			
			if (menu_dev == null) { 	
			   if(plf_dev.equals("01")){
				   menu_dev = menuId.substring(0,4)+"01";
			   }
			}
		}else if (menuId.substring(0,2).equals("53")){ // ����/����/������
			plf_dev = menuId.substring(2,4);			
			if (menu_dev == null) { 	
			   if(plf_dev.equals("01")){
				   menu_dev = menuId.substring(0,4)+"01";
			   }else if(plf_dev.equals("02")){
				   menu_dev = menuId.substring(0,4)+"01";
			   }else if(plf_dev.equals("03")){
				   menu_dev = menuId.substring(0,4)+"01";
			   }
		    }
		}else if (menuId.substring(0,2).equals("10")){ // IMS
			if (menuId.substring(2,4).equals("01")) {
				plf_dev = "8000";
			}else if(menuId.substring(2,4).equals("02")) {
				plf_dev = "8000";
			}else if(menuId.substring(2,4).equals("03")) {
				plf_dev = "8000";
			}else if(menuId.substring(2,4).equals("04")) {
				plf_dev = "9000";
			}
			plf_code = Integer.parseInt(plf_dev);
			dev_code = Integer.parseInt(menuId.substring(2,4));
			if (menu_dev == null) {
				menu_dev = menuId.substring(0,4)+"01";
			}			
		}else if (menuId.substring(0,2).equals("01")) { // ��ǥ���
			
			plf_dev = menuId.substring(2,4);			

			if (menu_dev == null) {
				if(plf_dev.equals("03")) {
					menu_dev = menuId.substring(0,4)+"11";
				} else if(plf_dev.equals("04")) {
					menu_dev = menuId.substring(0,4)+"12";
				} else if(plf_dev.equals("05")) {
					menu_dev = menuId.substring(0,4)+"13";				
				} else {
					menu_dev = menuId.substring(0,4)+"01";					
				}
			}
			
			plf_code = Integer.parseInt(plf_dev);
			dev_code = Integer.parseInt(menuId.substring(2,4));
			
		} else if (menuId.substring(0,2).equals("09")) { // ���� ������
			
			plf_dev = menuId.substring(2,4);
			
			if (menu_dev == null) {
				menu_dev = menuId.substring(0,4)+"01";
			}		
			
			// NTAS�� ��� �÷���, ����, Ÿ�� ����Ʈ�� �޾ƿ;� �Ѵ�.
			//��������, �����Ǽ� : �÷���, ����, Ÿ��
			//����� : �÷���, Ÿ��
			if (plf_dev.equals("01")) { 

				if (menu_dev.substring(4,6).equals("01")||menu_dev.substring(4,6).equals("02")) { //��������, �����Ǽ�
					
					if (p_name == null) {
						p_name = "0";
					}
					
					upper_code = Integer.parseInt(p_name);
					
					List svcNameList = manager.findNtasSvcList(12, upper_code);
					List pNameList = manager.findNtasList(11);
					List typeList = manager.findNtasList(13);				
					
					request.setAttribute("svcNameList", svcNameList);
					request.setAttribute("pNameList", pNameList);
					request.setAttribute("typeList", typeList);

				} else if (menu_dev.substring(4,6).equals("03")) { //�����	
					List pNameList = manager.findNtasList(14);
					List typeList = manager.findNtasList(15);
					
					request.setAttribute("pNameList", pNameList);			
					request.setAttribute("typeList", typeList);
					
				}
			} else if (plf_dev.equals("04")) {
				
				if (p_name == null) {
					p_name = "1000"; // �ʱⰪ me
				}
				
				upper_code = Integer.parseInt(p_name);
				
				//�÷��� ���� �����͸� �����´�(CP����)
				List plfDevList = manager.findPlfNameList();
				request.setAttribute("plfDevList", plfDevList);	
				
				//���񽺱��� �����͸� �����´�
				List svcDevList = manager.findSvcList(upper_code,path);
				request.setAttribute("svcDevList", svcDevList);
				
				//���񽺱���1 �����͸� �����´�
				List svcDevList1 = manager.findSvcList1(upper_code,path);
				request.setAttribute("svcDevList1", svcDevList1);
				
				//������ ���� �����͸� �����´�
				List dataDevList = manager.findDataiList(menu_dev);
				request.setAttribute("dataDevList", dataDevList);
			}
			
			plf_code = Integer.parseInt(plf_dev);
			dev_code = Integer.parseInt(menuId.substring(2,4));
			
			
		} else {
			//menuId �Ķ���͸� �̿��ؼ� �÷��� ����(plf_dev)�� ���Ѵ�.
			if (menuId.substring(0,2).equals("02")) {
				plf_dev = "1000";
			} else if (menuId.substring(0,2).equals("03")) {
				plf_dev = "2000";
			} else if (menuId.substring(0,2).equals("04")) {
				plf_dev = "3000";
			} else if (menuId.substring(0,2).equals("05")) {
				plf_dev = "4000";
			} else if (menuId.substring(0,2).equals("06")) {
				plf_dev = "6000";
			} else if (menuId.substring(0,2).equals("07")) {
				plf_dev = "7000";
			} else if (menuId.substring(0,2).equals("11")) {
				plf_dev = "11000";
			} else if (menuId.substring(0,2).equals("08")) {
				plf_dev = "4";
			}	
			
			//	menu_dev �ʱⰪ ����
			if (menu_dev == null) {
				if(strUserAuthType.equals("04")) {
					menu_dev = menuId.substring(0,4)+"01";			
				} else {
					if (menuId.substring(2,4).equals("01")) {
						menu_dev = menuId.substring(0,4)+"01";
					} else if (menuId.substring(2,4).equals("02")) {
						menu_dev = menuId.substring(0,4)+"04";
					} else if (menuId.substring(2,4).equals("03")) {
						menu_dev = menuId.substring(0,4)+"04";
					} else if (menuId.substring(2,4).equals("04")) {
						menu_dev = menuId.substring(0,4)+"04";
					} else if (menuId.substring(2,4).equals("05")) {
						menu_dev = menuId.substring(0,4)+"01";
					} else if (menuId.substring(2,4).equals("06")) {
						menu_dev = menuId.substring(0,4)+"01";
					} else if (menuId.substring(2,4).equals("07")) {
						menu_dev = menuId.substring(0,4)+"01";
					} else if (menuId.substring(2,4).equals("08")) {
						menu_dev = menuId.substring(0,4)+"04";
					} else if (menuId.substring(2,4).equals("09")) {
						menu_dev = menuId.substring(0,4)+"04";
					}
				}
			}
			
			plf_code = Integer.parseInt(plf_dev);
			dev_code = Integer.parseInt(menuId.substring(2,4));
			
		} 

		//�ش� �����޴��� ���� �̹���, �̹���������, �޴��� �������� �޼ҵ�
		List menuDetail = manager.findMenuDetail(menu_dev);
		request.setAttribute("menuDetail", menuDetail);	
		
		request.setAttribute("cdma_dev", cdma_dev);
		request.setAttribute("menuId", menuId);
		request.setAttribute("plf_dev", plf_dev);
		request.setAttribute("menu_dev", menu_dev);
		request.setAttribute("svc_dev", svc_dev);
		request.setAttribute("svc_dev_1", svc_dev_1);
		request.setAttribute("data_dev", data_dev);
		request.setAttribute("dayType", dayType);
		request.setAttribute("from_date", from_date);
		request.setAttribute("to_date", to_date);
		request.setAttribute("from_time", from_time);
		request.setAttribute("to_time", to_time);
		request.setAttribute("data_dev_val", data_dev_val);
		request.setAttribute("url_val", url_val);
		request.setAttribute("srh_cnt", srh_cnt);
		request.setAttribute("svc_sort", svc_sort);
		request.setAttribute("sort_dev", sort_dev);
		request.setAttribute("p_name", p_name);
		request.setAttribute("svc_name", svc_name);
		request.setAttribute("type", type);
		request.setAttribute("plf_name", plf_name);
		request.setAttribute("log_type", log_type);
		request.setAttribute("path", path);
		request.setAttribute("select_type", select_type);
		request.setAttribute("error_code", error_code);
		request.setAttribute("tcs_num_val", tcs_num_val);
		request.setAttribute("min_type", min_type);
		request.setAttribute("min_val", min_val);
		request.setAttribute("input_val", input_val);
		request.setAttribute("select_list", select_list);
		request.setAttribute("input_val1", input_val1);
		request.setAttribute("select_list1", select_list1);
		request.setAttribute("from_low", from_low);
		request.setAttribute("to_high", to_high);
		request.setAttribute("sFlag", sFlag);
		
		List svcList = manager.findSvcList(plf_code,path);
		List svcList1 = manager.findSvcList1(plf_code,path);
		List dataList = manager.findDataiList(menu_dev);
		
		//�÷��� �� ���񽺱���, �����ͱ��� ������ ���ͼ� request�� �����Ͽ� �����ϰ� �ִ�.
		request.setAttribute("SvcList", svcList);	
		request.setAttribute("SvcList1", svcList1);	
		request.setAttribute("DataList", dataList);

		return mapping.findForward(menuList);
	}

}
