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
		
		String portNo = "";
		
		portNo = request.getParameter("portNo");
		request.setAttribute("portNo", portNo);
		
		String mainPage = "";
		String strUserType = "";
		//SSO ���� �� ��Ű�� ���� ��ü�� ��ȣȭ �� ������ Decode�ؼ� �Ѱ� �޴´�.
		SSOAuthorityManager managerAuthBySSO = SSOAuthorityManager.instance();		
		User userInfo = managerAuthBySSO.getCookiesUserInfo(request);
		
		request.setAttribute("getCookiesUserInfo", userInfo);
		
		UserManager manager = UserManager.instance();	
		
		//user id�� ���ڷ� �޾� ����ڰ� aqua_user_info_tbl�� ���� �ϴ��� Ȯ�� �ϴ� �޼ҵ� 			
		boolean existedUser = manager.existedUser(userInfo.getUserId());	
		
		//userInfo.getMobile()�� ���ڷ� �޾� aqua_freenet_tbl�� �����ϴ��� Ȯ���ϴ� �޼ҵ�
		boolean existedKTFUse = manager.existedKTFUser(userInfo.getMobile());
		
		//user id�� ���ڷ� �޾� ����ڰ� aqua2_cass_user_info_tbl�� ���� �ϴ��� Ȯ�� �ϴ� �޼ҵ�
		boolean existedCassUse = manager.existedCassUser(userInfo.getUserId());
		
		//cdma_dev �Ķ���͸� �޾Ƽ� �Ѱ���
		String cdma_dev = "";
		cdma_dev	= request.getParameter("cdma_dev");
		request.setAttribute("cdma_dev", cdma_dev);
		
		if( existedUser == true) {
			mainPage= "default";
		} else {
			String authType = "";
		
			if(existedKTFUse == true){ //���� �����
				//KTF������ ��� �μ�(���ͳݿ����)�� ���� �ٸ� ������ �ο��ϱ� ���ؼ� �μ��� �����ϴ���
				//Ȯ���ϴ� �޼ҵ�
				boolean existedTeamUse = manager.existedTeamUser(userInfo.getMobile());
				
				if(existedTeamUse == true){
					authType = "02"; // �����
				} else {
					authType = "03"; // �����
				}
				
				//KTF������ ��� ����� �Ǵ� ����� �������� ����� ���̺� ��Ͻ�Ų��.
				int result = manager.createKTF(userInfo.getUserId(), userInfo.getMobile(), authType);
				request.setAttribute("result", String.valueOf(result));

				mainPage= "default";
				 
			} else { //�ܺλ����	
				
				if(existedCassUse == true) {
					//aqua2_cass_user_info_tbl�� ���� �� ��� �ܺλ���ڷ� ����� ���̺� ��Ͻ�Ų��.
					int result = manager.createCass(userInfo.getUserId(), userInfo.getRegRgstNum());
					request.setAttribute("result", String.valueOf(result));	
					
					mainPage= "default";
				} else {
					//�������� �ʴ� ��� �α����������� �̵� ��Ų��.(cass ������ ���� ��� ȸ����� �Ұ�)
					mainPage= "notAllowed";
				}
			}	  
		}		
		
		if ((existedUser == true) ||(existedKTFUse == true)||(existedCassUse == true)) {
			//��������, ����� �Խ��� ������ ���ͼ� request�� �����Ͽ� �����ϰ� �ִ�.
			List gongjiList = manager.findGongjiList();
			List userBoardList = manager.findUserBoardList();						
			
			request.setAttribute("gongjiList", gongjiList);			
			request.setAttribute("userBoardList", userBoardList);
			
			//����� ���� Ÿ�� �ڵ带 ������ �´�.
			String strUserAuthType = manager.getUserAuthType(userInfo.getUserId());
			request.setAttribute("getUserAuthType", strUserAuthType);
			
			// ����� Ÿ��(USER_TYPE)�� �����´�.
			strUserType = manager.getUserType(userInfo.getUserId());
			request.setAttribute("getUserType",strUserType );
			
			// �ܺλ������ ��� �ش� cp_id�� �����´�.
			if(strUserAuthType.equals("04")) {
				String strCpId = manager.getCpId(userInfo.getUserId());
				request.setAttribute("getCpId",strCpId );
			}
			
			String AuthType = strUserAuthType.substring(1,2);
			MenuManager menuManager = MenuManager.instance();
			
			//���θ޴� ����Ʈ�� �������� �޼ҵ�(depth1)
			// cdma_dev 2 	  = �ΰ���� 
			//			0,1,3 = ����
			//			4 	  = ��ü
			if("2".equals(cdma_dev)){
			    List mainMenuList = menuManager.findMainaddtotList(AuthType);
	            request.setAttribute("mainMenuList", mainMenuList);
			}else{
				List mainMenuList = menuManager.findMainMenuList(AuthType);
			    request.setAttribute("mainMenuList", mainMenuList);			  
			}
			//����޴� ����Ʈ�� �������� �޼ҵ�(depth2)
			if("2".equals(cdma_dev)){
			    List subMenuList = menuManager.findSubaddtotList(AuthType);
			    request.setAttribute("subMenuList", subMenuList);
			}else{
			    List subMenuList = menuManager.findSubMenuList(AuthType);
			    request.setAttribute("subMenuList", subMenuList);			   
			}
			
			// ����� Ÿ��(USER_TYPE)�� ���� �α������� ����
			if(strUserType.equals("01")) { // ���λ���� �α���
				int result = manager.loginKTF(userInfo.getMobile());
				request.setAttribute("result", String.valueOf(result));
			} else if(strUserType.equals("02")) { // �ܺλ���� �α���
				int result = manager.loginCass(userInfo.getUserId());
				request.setAttribute("result", String.valueOf(result));	
			}
		}
		
		log.info("portNo===>"+portNo);
		log.info("mainPage==>"+mainPage);
		
		return mapping.findForward(mainPage);
	}
}


