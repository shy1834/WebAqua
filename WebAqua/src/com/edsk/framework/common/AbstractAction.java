/*
 * Created on 2006. 1. 23.
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.edsk.framework.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public abstract class AbstractAction extends DispatchAction
{
	protected Logger logger = null;
	/* (non-Javadoc)
	 * @see org.apache.struts.action.Action#execute(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		logger = Logger.getLogger( this.getClass() );
		
		// session에 Locale 정보가 없으면 config.properties의 LANG.default 값으로 Locale을 설정한다.
		// config.properties에 Locale 정보가 설정되어 있지 않으면 request의 Locale로 설정한다.
		//HttpSession session = request.getSession();
		/*
		if( session.getAttribute( "a2b.i18n.locale" ) == null ){
			java.util.Locale defaultLocale = null;
			if( Config.getValue("LANG.default") == null || Config.getValue("LANG.default").equals("") ){
				defaultLocale = request.getLocale();
			}
			else{
				defaultLocale = new java.util.Locale(Config.getValue("LANG.default"));
			}
			session.setAttribute("a2b.i18n.locale", defaultLocale);
		}*/

		return super.execute( mapping, form, request, response );
	}	
}
