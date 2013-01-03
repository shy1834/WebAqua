/*
 * Created on 2006. 2. 21.
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.ktf.aqua.user.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ktf.aqua.mgr.menu.MenuManager;
import com.ktf.aqua.user.SSOAuthorityManager;
import com.ktf.aqua.user.UserManager;
import com.ktf.aqua.user.common.User;

/**
 * @author redfox
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class UserInfoAction extends Action {

	/**
	 * MenuManager�� list�޽�带 ȣ���Ͽ� List�� response�� �����ϴ� �ҽ��ڵ尡 ����. list.jsp����
	 * response�� ����� List��ü�� �̿��Ѵ�.
	 */
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		//�ѱ� ���� ����
		request.setCharacterEncoding("euc-kr");

		String userInfo_ = "";
		String menuId_ad = "";
		String path_ad = "";

		//update item
		String auth_type = "";
		String apply_stat_cd = "";
		String prog_stat_cd = "";
		//String User_Email1 = "";
		String User_Email2 = "";
		String User_Email3 = "";
		String User_Mobile = "";
		String Company_Dept = "";

		int seq = 0;
		String userType = "";
		List UserEditList;
		List UserInfoList;

		menuId_ad = request.getParameter("menuId_ad");
		path_ad = request.getParameter("path_ad");
		userInfo_ = path_ad;

		//SSO ���� �� ��Ű�� ���� ��ü�� ��ȣȭ �� ������ Decode�ؼ� �Ѱ� �޴´�.
		SSOAuthorityManager managerAuthBySSO = SSOAuthorityManager.instance();
		User userInfo = managerAuthBySSO.getCookiesUserInfo(request);
		request.setAttribute("getCookiesUserInfo", userInfo);

		//cdma_dev �Ķ���͸� �޾Ƽ� �Ѱ���
		String cdma_dev = "";
		cdma_dev = request.getParameter("cdma_dev");
		request.setAttribute("cdma_dev", cdma_dev);

		//����� ���� Ÿ�� �ڵ带 ������ �´�.
		UserManager userManager = UserManager.instance();
		String strUserAuthType = userManager.getUserAuthType(userInfo.getUserId());
		request.setAttribute("getUserAuthType", strUserAuthType);

		//����� Ÿ��(USER_TYPE)�� �����´�.
		String strUserType = userManager.getUserType(userInfo.getUserId());
		request.setAttribute("getUserType", strUserType);

		//�ܺλ������ ��� �ش� cp_id�� �����´�.
		if (strUserAuthType.equals("04")) {
			String strCpId = userManager.getCpId(userInfo.getUserId());
			request.setAttribute("getCpId", strCpId);
		}

		MenuManager menuManager = MenuManager.instance();

		String AuthType = strUserAuthType.substring(1, 2);

		//���θ޴� ����Ʈ�� �������� �޼ҵ�
		List mainMenuList = menuManager.findMainMenuList(AuthType);
		request.setAttribute("mainMenuList", mainMenuList);

		//����޴� ����Ʈ�� �������� �޼ҵ�
		List subMenuList = menuManager.findSubMenuList(AuthType);
		request.setAttribute("subMenuList", subMenuList);

		//������ ����޴� ����Ʈ�� �������� �޼ҵ�
		List subAdMenuList = menuManager.findSubAdMenuList();
		request.setAttribute("subAdMenuList", subAdMenuList);

		//������ �޴��󼼸���Ʈ�� �������� �޼ҵ�
		List AdMenuDetail = menuManager.findAdMenuDetail(menuId_ad);
		request.setAttribute("AdMenuDetail", AdMenuDetail);

		//��������
		if(path_ad.equals("userInfo")) {
			userType = request.getParameter("userType");
			
			if(userType == null){
				HttpSession h = request.getSession();
				userType = (String)h.getAttribute("userLevel");
			}

			if(userType.equals("01")) { 							//���λ����
				UserInfoList = userManager.findInUserInfo(userInfo.getMobile());
			} else { 												//�ܺλ����
				UserInfoList = userManager.findOutUserInfo(userInfo.getUserId());
			}
			request.setAttribute("UserInfoList", UserInfoList);
		}

		//����� ����Ʈ �Խ���
		if(path_ad.equals("userList")) {
			
			int total_cnt = userManager.getUserTotCnt();
			request.setAttribute("str_total_cnt", String.valueOf(total_cnt));

			//���� ������ ��ȣ c_page ����
			String str_c_page = request.getParameter("str_c_page");
			if(str_c_page == null){
				str_c_page = "1";
			}
				
			request.setAttribute("str_c_page", str_c_page);

			// �˻� ���� ����
			String srhItem = request.getParameter("srhItem");
			if(srhItem == null){
				srhItem = "1";
			}
				
			request.setAttribute("srhItem", srhItem);

			// �� ȭ�鿡 ������ ����Ʈ ����
			String str_list_num = "10";
			request.setAttribute("str_list_num", str_list_num);

			int c_page = Integer.parseInt(str_c_page);
			int list_num = Integer.parseInt(str_list_num);

			List UserList = userManager.findUserList(c_page, list_num);
			request.setAttribute("UserList", UserList);
		}

		//����� ���� ���� ȭ��, ����� ���� ���� ȭ��
		if(path_ad.equals("userEdit") || path_ad.equals("userView")) {
			
			seq = Integer.parseInt(request.getParameter("seq"));
			userType = request.getParameter("userType");

			if (userType.equals("01")) { 				//���λ����
				UserEditList = userManager.findInUser(seq);
			} else { 									//�ܺλ����
				UserEditList = userManager.findOutUser(seq);
			}
			request.setAttribute("UserEditList", UserEditList);
		}

		//����� ���� ����(��ưŬ��)
		if(path_ad.equals("userEdit1")) {
			
			seq = Integer.parseInt(request.getParameter("seq"));
			userType = request.getParameter("User_Type");

			auth_type = request.getParameter("auth_type");
			//User_Email2 = request.getParameter("User_Email1");
			User_Email2 = request.getParameter("User_Email2");
			User_Email3 = request.getParameter("User_Email3");
			User_Mobile = request.getParameter("User_Mobile");
			Company_Dept = request.getParameter("Company_Dept");

			int result = userManager.Update(seq, auth_type, User_Email2,User_Email3, User_Mobile, Company_Dept, userInfo.getUserId());
			request.setAttribute("result", String.valueOf(result));

			if (userType.equals("02")) {
				UserEditList = userManager.findOutUser(seq);
			} else {
				UserEditList = userManager.findInUser(seq);
			}
			request.setAttribute("UserEditList", UserEditList);
		}

		//����� ���� ����
		if(path_ad.equals("userDelete")) {
			
			seq = Integer.parseInt(request.getParameter("seq"));
			int result = userManager.Remove(seq);
			request.setAttribute("result", String.valueOf(result));
		}

		//����� �˻�
		if(path_ad.equals("userSearch")) {
			//�˻� ���� ����
			String srhItem = request.getParameter("srhItem");
			if(srhItem == null){
				srhItem = "1";
			}
				
			request.setAttribute("srhItem", srhItem);

			//�˻��� ���� ����
			String srhName = request.getParameter("srhName");
			if(srhName == null){
				srhName = "";
			}
				
			request.setAttribute("srhName", srhName);

			String str = "";
			if(srhName.equals("")){
				str = "";
			}else if(srhItem.equals("1")){
				str = "WHERE USER_ID LIKE  '%" + srhName + "%'  ";
			}else if(srhItem.equals("2")){
				str = "WHERE USER_NAME LIKE  '%" + srhName + "%'  ";
			}else if(srhItem.equals("3")){
				str = "WHERE COMPANY LIKE  '%" + srhName + "%'  ";
			}else if(srhItem.equals("4")){
				str = "WHERE AUTH_TYPE = '" + srhName + "'  ";
			}

			int total_cnt = userManager.getUserSrhCnt(str);
			request.setAttribute("str_total_cnt", String.valueOf(total_cnt));

			//���� ������ ��ȣ c_page ����
			String str_c_page = request.getParameter("str_c_page");
			if (str_c_page == null){
				str_c_page = "1";
			}
				
			request.setAttribute("str_c_page", str_c_page);

			//�� ȭ�鿡 ������ ����Ʈ ����
			String str_list_num = "10";
			request.setAttribute("str_list_num", str_list_num);

			int c_page = Integer.parseInt(str_c_page);
			int list_num = Integer.parseInt(str_list_num);

			List SearchUserList = userManager.findSearchList(str, c_page, list_num);
			request.setAttribute("SearchUserList", SearchUserList);
		}
		
		request.setAttribute("menuId_ad", menuId_ad);
		request.setAttribute("path_ad", path_ad);
		return mapping.findForward(userInfo_);
	}
}
