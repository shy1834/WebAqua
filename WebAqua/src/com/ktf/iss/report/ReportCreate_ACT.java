package com.ktf.iss.report;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ReportCreate_ACT extends Action{
	
	private Log log = LogFactory.getLog(getClass());

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception{
	  
		request.setCharacterEncoding("euc-kr");

	    GregorianCalendar greCal = new GregorianCalendar();
	    greCal.add(5, -1);

	    Date yTime = greCal.getTime();
	    SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");

	    Report_BO bo = Report_BO.getInstance();
	    String rept_tmpl_id = request.getParameter("rept_tmpl_id");
	    String rept_nm_1 = request.getParameter("rept_tmpl_nm");
	    String date_fld = request.getParameter("date_fld");
	    String rept_nm = rept_nm_1.replaceAll("_¹èÆ÷¿ë", "");

	    String yDay = (rept_tmpl_id.equals("RPTP0050")) ? date_fld : formatter.format(yTime);

	    rept_nm = rept_nm + "(" + yDay + ")";

	    String[] in_params = request.getParameterValues("in_param");
	    bo.createReport(rept_tmpl_id, rept_nm, in_params, date_fld);

	    return mapping.findForward("ReptInquiry");
  	}
}
