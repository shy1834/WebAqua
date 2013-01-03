/*
 * Created on 2006. 2. 17.
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.ktf.aqua.mgr.menu.action;

import java.util.*;

import javax.servlet.http.*;

import org.apache.log4j.*;
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
public class MainPageAction2 extends Action{
	
	protected Logger log = Logger.getLogger( this.getClass() );
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
		
		String portNo = "";
		
		portNo = request.getParameter("portNo");
		request.setAttribute("portNo", portNo);
		
		String mainPage = "";
		String strUserType = "";
		//SSO 인증 후 쿠키에 담은 개체를 암호화 된 값들을 Decode해서 넘겨 받는다.
		SSOAuthorityManager managerAuthBySSO = SSOAuthorityManager.instance();		
		User userInfo = managerAuthBySSO.getCookiesUserInfo(request);
		
		request.setAttribute("getCookiesUserInfo", userInfo);
		
		UserManager manager = UserManager.instance();	
		
		//user id를 인자로 받아 사용자가 aqua_user_info_tbl에 존재 하는지 확인 하는 메소드 			
		boolean existedUser = manager.existedUser(userInfo.getUserId());	
		
		//userInfo.getMobile()를 인자로 받아 aqua_freenet_tbl에 존재하는지 확인하는 메소드
		boolean existedKTFUse = manager.existedKTFUser(userInfo.getMobile());
		
		//user id를 인자로 받아 사용자가 aqua2_cass_user_info_tbl에 존재 하는지 확인 하는 메소드
		boolean existedCassUse = manager.existedCassUser(userInfo.getUserId());
		
		//cdma_dev 파라미터를 받아서 넘겨줌
		String cdma_dev = "";
		cdma_dev	= request.getParameter("cdma_dev");
		request.setAttribute("cdma_dev", cdma_dev);
		
		if( existedUser == true) {
			mainPage= "default";
		} else {
			String authType = "";
		
			if(existedKTFUse == true){ //내부 사용자
				//KTF직원인 경우 부서(인터넷운용팀)에 따라 다른 권한을 부여하기 위해서 부서에 존재하는지
				//확인하는 메소드
				boolean existedTeamUse = manager.existedTeamUser(userInfo.getMobile());
				
				if(existedTeamUse == true){
					authType = "02"; // 운용자
				} else {
					authType = "03"; // 사용자
				}
				
				//KTF직원인 경우 운용자 또는 사용자 권한으로 사용자 테이블에 등록시킨다.
				int result = manager.createKTF(userInfo.getUserId(), userInfo.getMobile(), authType);
				request.setAttribute("result", String.valueOf(result));

				mainPage= "default";
				 
			} else { //외부사용자	
				
				if(existedCassUse == true) {
					//aqua2_cass_user_info_tbl에 존재 할 경우 외부사용자로 사용자 테이블에 등록시킨다.
					int result = manager.createCass(userInfo.getUserId(), userInfo.getRegRgstNum());
					request.setAttribute("result", String.valueOf(result));	
					
					mainPage= "default";
				} else {
					//존재하지 않는 경우 로그인페이지로 이동 시킨다.(cass 정보가 없는 경우 회원등록 불가)
					mainPage= "notAllowed";
				}
			}	  
		}		
		
		if ((existedUser == true) ||(existedKTFUse == true)||(existedCassUse == true)) {
			//공지사항, 사용자 게시판 정보를 얻어와서 request에 저장하여 전달하고 있다.
			List gongjiList = manager.findGongjiList();
			List userBoardList = manager.findUserBoardList();						
			
			request.setAttribute("gongjiList", gongjiList);			
			request.setAttribute("userBoardList", userBoardList);
			
			//사용자 권한 타입 코드를 가지고 온다.
			String strUserAuthType = manager.getUserAuthType(userInfo.getUserId());
			request.setAttribute("getUserAuthType", strUserAuthType);
			
			// 사용자 타입(USER_TYPE)을 가져온다.
			strUserType = manager.getUserType(userInfo.getUserId());
			request.setAttribute("getUserType",strUserType );
			
			// 외부사용자인 경우 해당 cp_id를 가져온다.
			if(strUserAuthType.equals("04")) {
				String strCpId = manager.getCpId(userInfo.getUserId());
				request.setAttribute("getCpId",strCpId );
			}
			
			String AuthType = strUserAuthType.substring(1,2);
			MenuManager menuManager = MenuManager.instance();
			
			//메인메뉴 리스트를 가져오는 메소드(depth1)
			// cdma_dev 2 	  = 부가통계 
			//			0,1,3 = 동일
			//			4 	  = 전체
			if("2".equals(cdma_dev)){
			    List mainMenuList = menuManager.findMainaddtotList(AuthType);
	            request.setAttribute("mainMenuList", mainMenuList);
			}else{
				List mainMenuList = menuManager.findMainMenuList(AuthType);
			    request.setAttribute("mainMenuList", mainMenuList);			  
			}
			//서브메뉴 리스트를 가져오는 메소드(depth2)
			if("2".equals(cdma_dev)){
			    List subMenuList = menuManager.findSubaddtotList(AuthType);
			    request.setAttribute("subMenuList", subMenuList);
			}else{
			    List subMenuList = menuManager.findSubMenuList(AuthType);
			    request.setAttribute("subMenuList", subMenuList);			   
			}
			
			// 사용자 타입(USER_TYPE)에 따른 로그인정보 저장
			if(strUserType.equals("01")) { // 내부사용자 로그인
				int result = manager.loginKTF(userInfo.getMobile());
				request.setAttribute("result", String.valueOf(result));
			} else if(strUserType.equals("02")) { // 외부사용자 로그인
				int result = manager.loginCass(userInfo.getUserId());
				request.setAttribute("result", String.valueOf(result));	
			}
		}
		
		log.info("portNo===>"+portNo);
		log.info("mainPage==>"+mainPage);
		
		return mapping.findForward(mainPage);
	}
}


