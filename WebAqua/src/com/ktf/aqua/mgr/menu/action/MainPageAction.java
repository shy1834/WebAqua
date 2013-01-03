package com.ktf.aqua.mgr.menu.action;

import javax.servlet.http.*;

import org.apache.log4j.*;
import org.apache.struts.action.*;

import com.ktf.aqua.common.*;

public class MainPageAction extends Action{
	
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
			
			if(resultStr[5].equals("true") == false){			
				request.setAttribute("errMsg", "로그인 후 이용가능합니다.");
				return mapping.findForward("sessionError");
			}
			
			/* 공지사항, 사용자 게시판 
			UserManager manager = UserManager.instance();	
			List gongjiList = manager.findGongjiList();
			List userBoardList = manager.findUserBoardList();

			request.setAttribute("gongjiList", gongjiList);			
			request.setAttribute("userBoardList", userBoardList);
			*/
			
			return mapping.findForward("default");
	}
}
