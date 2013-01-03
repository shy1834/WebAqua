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
	 * MenuManager의 list메써드를 호출하여 List를 response에 저장하는 소스코드가 들어간다. list.jsp에서
	 * response에 저장된 List객체를 이용한다.
	 */
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		//한글 깨짐 방지
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

		//SSO 인증 후 쿠키에 담은 개체를 암호화 된 값들을 Decode해서 넘겨 받는다.
		SSOAuthorityManager managerAuthBySSO = SSOAuthorityManager.instance();
		User userInfo = managerAuthBySSO.getCookiesUserInfo(request);
		request.setAttribute("getCookiesUserInfo", userInfo);

		//cdma_dev 파라미터를 받아서 넘겨줌
		String cdma_dev = "";
		cdma_dev = request.getParameter("cdma_dev");
		request.setAttribute("cdma_dev", cdma_dev);

		//사용자 권한 타입 코드를 가지고 온다.
		UserManager userManager = UserManager.instance();
		String strUserAuthType = userManager.getUserAuthType(userInfo.getUserId());
		request.setAttribute("getUserAuthType", strUserAuthType);

		//사용자 타입(USER_TYPE)을 가져온다.
		String strUserType = userManager.getUserType(userInfo.getUserId());
		request.setAttribute("getUserType", strUserType);

		//외부사용자인 경우 해당 cp_id를 가져온다.
		if (strUserAuthType.equals("04")) {
			String strCpId = userManager.getCpId(userInfo.getUserId());
			request.setAttribute("getCpId", strCpId);
		}

		MenuManager menuManager = MenuManager.instance();

		String AuthType = strUserAuthType.substring(1, 2);

		//메인메뉴 리스트를 가져오는 메소드
		List mainMenuList = menuManager.findMainMenuList(AuthType);
		request.setAttribute("mainMenuList", mainMenuList);

		//서브메뉴 리스트를 가져오는 메소드
		List subMenuList = menuManager.findSubMenuList(AuthType);
		request.setAttribute("subMenuList", subMenuList);

		//관리자 서브메뉴 리스트를 가져오는 메소드
		List subAdMenuList = menuManager.findSubAdMenuList();
		request.setAttribute("subAdMenuList", subAdMenuList);

		//관리자 메뉴상세리스트를 가져오는 메소드
		List AdMenuDetail = menuManager.findAdMenuDetail(menuId_ad);
		request.setAttribute("AdMenuDetail", AdMenuDetail);

		//개인정보
		if(path_ad.equals("userInfo")) {
			userType = request.getParameter("userType");
			
			if(userType == null){
				HttpSession h = request.getSession();
				userType = (String)h.getAttribute("userLevel");
			}

			if(userType.equals("01")) { 							//내부사용자
				UserInfoList = userManager.findInUserInfo(userInfo.getMobile());
			} else { 												//외부사용자
				UserInfoList = userManager.findOutUserInfo(userInfo.getUserId());
			}
			request.setAttribute("UserInfoList", UserInfoList);
		}

		//사용자 리스트 게시판
		if(path_ad.equals("userList")) {
			
			int total_cnt = userManager.getUserTotCnt();
			request.setAttribute("str_total_cnt", String.valueOf(total_cnt));

			//시작 페이지 번호 c_page 설정
			String str_c_page = request.getParameter("str_c_page");
			if(str_c_page == null){
				str_c_page = "1";
			}
				
			request.setAttribute("str_c_page", str_c_page);

			// 검색 조건 설정
			String srhItem = request.getParameter("srhItem");
			if(srhItem == null){
				srhItem = "1";
			}
				
			request.setAttribute("srhItem", srhItem);

			// 한 화면에 보여줄 리스트 갯수
			String str_list_num = "10";
			request.setAttribute("str_list_num", str_list_num);

			int c_page = Integer.parseInt(str_c_page);
			int list_num = Integer.parseInt(str_list_num);

			List UserList = userManager.findUserList(c_page, list_num);
			request.setAttribute("UserList", UserList);
		}

		//사용자 정보 수정 화면, 사용자 정보 보기 화면
		if(path_ad.equals("userEdit") || path_ad.equals("userView")) {
			
			seq = Integer.parseInt(request.getParameter("seq"));
			userType = request.getParameter("userType");

			if (userType.equals("01")) { 				//내부사용자
				UserEditList = userManager.findInUser(seq);
			} else { 									//외부사용자
				UserEditList = userManager.findOutUser(seq);
			}
			request.setAttribute("UserEditList", UserEditList);
		}

		//사용자 정보 수정(버튼클릭)
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

		//사용자 정보 삭제
		if(path_ad.equals("userDelete")) {
			
			seq = Integer.parseInt(request.getParameter("seq"));
			int result = userManager.Remove(seq);
			request.setAttribute("result", String.valueOf(result));
		}

		//사용자 검색
		if(path_ad.equals("userSearch")) {
			//검색 조건 설정
			String srhItem = request.getParameter("srhItem");
			if(srhItem == null){
				srhItem = "1";
			}
				
			request.setAttribute("srhItem", srhItem);

			//검색어 조건 설정
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

			//시작 페이지 번호 c_page 설정
			String str_c_page = request.getParameter("str_c_page");
			if (str_c_page == null){
				str_c_page = "1";
			}
				
			request.setAttribute("str_c_page", str_c_page);

			//한 화면에 보여줄 리스트 갯수
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
