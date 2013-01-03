/*
 * Created on 2003. 10. 2.
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.ktf.iss.report;

import javax.servlet.http.*;
import org.apache.struts.action.*;
import java.util.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class ReportTemplateModifyForm_ACT extends Action {

	private Log log = LogFactory.getLog(this.getClass());
	/**
	 * 
	 */
	public ReportTemplateModifyForm_ACT() {
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
		String rept_tmpl_id = request.getParameter("rept_tmpl_id");
		String cur_page = request.getParameter("cur_page");
		String rept_tmpl_nm = request.getParameter("rept_tmpl_nm");
		if(rept_tmpl_id != null && rept_tmpl_id.length() == 0){
			rept_tmpl_id = null;
		}

		if(rept_tmpl_id == null) {
			
		}
		
		ReportTemplate_DTO dto = bo.getReportTemplateInfo(rept_tmpl_id);
		ArrayList statInfoList = bo.getReportStatisticsInfo(rept_tmpl_id);
		request.setAttribute("TMPL_INFO", dto);
		request.setAttribute("STAT_LIST", statInfoList);
		request.setAttribute("CUR_PAGE", cur_page);
		request.setAttribute("REPT_TMPL_NM", rept_tmpl_nm);
		return mapping.findForward("ReptTmplModify");	
	}
}
