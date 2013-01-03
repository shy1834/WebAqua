/*
 * Created on 2003. 10. 2.
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.ktf.iss.report;

import java.util.ArrayList;

import javax.servlet.http.*;
import org.apache.struts.action.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class ReportInquiry_ACT extends Action {

	private Log log = LogFactory.getLog(this.getClass());

	public ReportInquiry_ACT() {
		super();
	}

	/** 
	 * Method execute
	 * @param ActionMapping mapping
	 * @param ActionForm form
	 * @param HttpServletRequest request
	 * @param HttpServletResponse response
	 * @return ActionForward
	 * @throws Exception
	 */
	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response)
		throws Exception {

		request.setCharacterEncoding("euc-kr");

		Report_BO bo = Report_BO.getInstance(); 
		String reptTmplName = request.getParameter("rept_tmpl_nm");		//양식이름
		if(reptTmplName != null && reptTmplName.length() == 0){
			reptTmplName = null;
		}
		
		String reptName = request.getParameter("rept_nm");				//보고서이름
		if(reptName != null && reptName.length() == 0){
			reptName = null;
		}
		
		String cur_page = request.getParameter("cur_page") == null ? "1": request.getParameter("cur_page");
		String max_line = request.getParameter("max_line") == null ? "10": request.getParameter("max_line");
		String eventTimeFrom = request.getParameter("eventTimeFrom");
		String eventTimeTo = request.getParameter("eventTimeTo");
		
		if(eventTimeFrom == null || eventTimeFrom.length() == 0) {
			eventTimeFrom = null;
			request.setAttribute("eventTimeFrom", "");
		}else{
			String eventTime = eventTimeFrom.substring(0, 4) + "/"
								+ eventTimeFrom.substring(4, 6) + "/"
								+ eventTimeFrom.substring(6);
			request.setAttribute("eventTimeFrom", eventTime);			
		}
		
		if(eventTimeTo == null || eventTimeTo.length() == 0) {
			eventTimeTo = null;
			request.setAttribute("eventTimeTo", "");
		} else {
			String eventTime = eventTimeTo.substring(0, 4) + "/"
								+ eventTimeTo.substring(4, 6) + "/"
								+ eventTimeTo.substring(6);
			request.setAttribute("eventTimeTo", eventTime);			
		}

		ArrayList alist = bo.getReportFileList(reptTmplName, reptName, eventTimeFrom, eventTimeTo, Integer.parseInt(cur_page),Integer.parseInt(max_line));
		int total = bo.getReportFileTotal(reptTmplName, reptName, eventTimeFrom, eventTimeTo);
		
		request.setAttribute("CUR_PAGE", cur_page);
		request.setAttribute("MAX_LINE", max_line);
		request.setAttribute("REPT_LIST", alist);
		request.setAttribute("TOTAL_LINE", String.valueOf(total));
		
		if(reptTmplName == null){
			request.setAttribute("REPT_TMPL_NM", "");
		}else{
			request.setAttribute("REPT_TMPL_NM", reptTmplName);
		}
			
		if(reptName == null){
			request.setAttribute("REPT_NM", "");
		}else{
			request.setAttribute("REPT_NM", reptName);
		}
		
		return mapping.findForward("ReptInquiry");	
	}
}
