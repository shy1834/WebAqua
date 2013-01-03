/*
 * Created on 2003. 10. 2.
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.ktf.iss.report;

import javax.servlet.http.*;
import org.apache.struts.action.*;
import org.apache.struts.upload.FormFile;
import org.apache.struts.validator.DynaValidatorForm;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class ReportTemplateModify_ACT extends Action {

	private Log log = LogFactory.getLog(this.getClass());
	/**
	 * 
	 */
	public ReportTemplateModify_ACT() {
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

		DynaValidatorForm dynaform = (DynaValidatorForm)form;
		String rept_tmpl_id = (String)dynaform.get("rept_tmpl_id");
		String rept_tmpl_nm = (String)dynaform.get("rept_tmpl_nm");
		String rept_prd = (String)dynaform.get("rept_prd");
		FormFile rept_tmpl_file = (FormFile)dynaform.get("rept_tmpl_file");
		String[] start_rows = (String[])dynaform.get("start_row");
		String[] start_cols = (String[])dynaform.get("start_col");
		String[] end_rows = (String[])dynaform.get("end_row");
		String[] end_cols = (String[])dynaform.get("end_col");
		String[] sqls = (String[])dynaform.get("sql");
		String[] data_types = (String[])dynaform.get("data_type");
		String[] sheet_nos = (String[])dynaform.get("sheet_no");
		
		try {
			ReportTemplate_DTO rtDTO = new ReportTemplate_DTO();
			rtDTO.setRept_tmpl_id(rept_tmpl_id);
			rtDTO.setRept_tmpl_nm(rept_tmpl_nm);
			rtDTO.setRept_prd(rept_prd);
			rtDTO.setRept_tmpl_file_nm(rept_tmpl_file.getFileName());
			rtDTO.setRept_tmpl_file(rept_tmpl_file);
	
			ReportTemplateStatistics_DTO[] rtsDTO = new ReportTemplateStatistics_DTO[start_rows.length];
			for(int i = 0; i < rtsDTO.length; i++) {
				rtsDTO[i] = new ReportTemplateStatistics_DTO();
				rtsDTO[i].setStart_row(start_rows[i]);
				rtsDTO[i].setStart_col(start_cols[i]);
				rtsDTO[i].setEnd_row(end_rows[i]);
				rtsDTO[i].setEnd_col(end_cols[i]);
				rtsDTO[i].setSql(sqls[i]);
				rtsDTO[i].setData_type(data_types[i]);
				rtsDTO[i].setSheet_no(sheet_nos[i]);
			}
			
			Report_BO bo = Report_BO.getInstance();
			bo.modifyReportTemplate(rtDTO, rtsDTO);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		

		return mapping.findForward("ReptTmplInquiry");	
	}
}
