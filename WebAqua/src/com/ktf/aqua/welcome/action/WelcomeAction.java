/*
 * Created on 2006. 1. 26.
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.ktf.aqua.welcome.action;

import javax.servlet.http.*;

import org.apache.log4j.*;
import org.apache.struts.action.*;

import com.edsk.framework.common.*;

/**
 * @author redfox
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class WelcomeAction extends AbstractAction{
	
   //로그를 생성하기 위한 메소드 선언
	protected Logger log = Logger.getLogger( this.getClass() );
	
	/**
	 * 메인 페이지로 이동
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward goMain(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	throws Exception {	
		return mapping.findForward( "goMain" );
	}

}
