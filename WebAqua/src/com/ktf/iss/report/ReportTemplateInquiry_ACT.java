/*
 * Created on 2003. 10. 2.
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.ktf.iss.report;

import javax.servlet.http.*;
import org.apache.struts.action.*;
import java.util.ArrayList;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class ReportTemplateInquiry_ACT extends Action {

	private Log log = LogFactory.getLog(this.getClass());

	public ReportTemplateInquiry_ACT() {
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
		String dateFld = request.getParameter("date_fld");
		
		if(reptTmplName != null && reptTmplName.length() == 0){
			reptTmplName = null;
		}
		
		String cur_page = request.getParameter("cur_page") == null ? "1": request.getParameter("cur_page");
		String max_line = request.getParameter("max_line") == null ? "10": request.getParameter("max_line");

		ArrayList alist = bo.getReportTemplateList(reptTmplName, Integer.parseInt(cur_page), Integer.parseInt(max_line));
		int total = bo.getReportTemplateTotal(reptTmplName);
		
		request.setAttribute("CUR_PAGE", cur_page);
		request.setAttribute("MAX_LINE", max_line);
		request.setAttribute("TMPL_LIST", alist);
		request.setAttribute("TOTAL_LINE", String.valueOf(total));
		request.setAttribute("DATE_FLD", dateFld);
		
		if(reptTmplName == null){
			request.setAttribute("REPT_TMPL_NM", "");
		}else{
			request.setAttribute("REPT_TMPL_NM", reptTmplName);
		}
		
		return mapping.findForward("ReptTmplInquiry");	
	}
}
