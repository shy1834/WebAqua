/*
 * Created on 2003. 10. 2.
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.ktf.iss.report;

import java.util.ArrayList;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ktf.iss.statistics.StatisticsPage_BO;

/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class TableInquiry_ACT extends Action {

	private Log log = LogFactory.getLog(this.getClass());
	/**
	 * 
	 */
	public TableInquiry_ACT() {
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
	public ActionForward execute(ActionMapping mapping,
									ActionForm form,
									HttpServletRequest request,
									HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("euc-kr");
		
		StatisticsPage_BO bo = new StatisticsPage_BO();
		request.setAttribute("PLATFORM_LIST", bo.getPlatformList());
		request.setAttribute("DATA_LIST", bo.getDataList());

		String table_org_id = request.getParameter("table_id");
		if(table_org_id != null) {
			Report_BO reptBO = Report_BO.getInstance();
			ArrayList tableList = reptBO.getTableList(table_org_id);
			request.setAttribute("TABLE_LIST", tableList);
			if(tableList.size() > 0) {
				TableInfo_DTO dto = (TableInfo_DTO)tableList.get(0);
				String table_id = dto.getTable_id();
				ArrayList columnList = reptBO.getColumnList(table_id);
				request.setAttribute("COLUMN_LIST", columnList);
			}
			request.setAttribute("TABLE_ID", table_org_id);
		} else {
			request.setAttribute("TABLE_ID", "");
		}

		return mapping.findForward("TableInquiry");	
	}
}
