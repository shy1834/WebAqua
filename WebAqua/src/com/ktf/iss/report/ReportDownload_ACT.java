/*
 * Created on 2003. 10. 2.
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.ktf.iss.report;

import javax.servlet.http.*;
import javax.servlet.*;
import org.apache.struts.action.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import java.io.*;

/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class ReportDownload_ACT extends Action {

	private Log log = LogFactory.getLog(this.getClass());
	/**
	 *  
	 */
	public ReportDownload_ACT() {
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
		
		Report_BO bo = Report_BO.getInstance(); 
		String rept_id = request.getParameter("rept_id");
		ServletOutputStream stream = null;
		BufferedInputStream fif = null;
		try{
			ReportFileInfo_DTO dto = bo.getReportFileInfo(rept_id);
//			String filename	= dto.getRept_id()+".xls";
			String filename = dto.getRept_nm()+".xls";			
//			System.out.println(filename);
			java.io.File file = new java.io.File(dto.getRept_file_nm());
			long fileLen = file.length();
			fif = new BufferedInputStream(new FileInputStream(file)); 
			stream = response.getOutputStream(); 
	
			// CLIENT BROWSER TYPE NOTICE VARIABLE
			String browser_type = request.getHeader("User-Agent");
			response.setHeader("Content-Type", "application/octet-stream");
			response.setHeader("Content-Disposition", "attachment;filename="+filename+ ";");
			response.setHeader("Content-Transfer-Encoding", "binary;");
			response.setHeader("Content-Length", ""+(int)fileLen);
			response.setHeader("Pragma", "no-cache;");
			response.setHeader("Expires", "-1;");
			
			int blockSize = 65000;
			int readBytes = 0;
		    int totalRead = 0;
		    byte b[] = new byte[blockSize];
	
			while((long)totalRead < fileLen) {
				readBytes = fif.read(b, 0, blockSize);
				totalRead += readBytes;
				stream.write(b, 0, readBytes); 
			}
		}catch(Exception e){
			log.error(e);
		}finally{
			try{
				if (stream!=null)stream.close(); 
				if (fif!=null)fif.close(); 
			}catch(IOException e){
			}
		}

		return null;	
	}
}
