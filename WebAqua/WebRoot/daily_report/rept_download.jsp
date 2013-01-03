<%@page import="com.ktf.iss.report.*, javax.servlet.http.*, javax.servlet.*, org.apache.struts.action.*, org.apache.commons.logging.Log, org.apache.commons.logging.LogFactory, java.io.*"%>
<%@ page contentType = "text/html;charset=euc-kr" %>
<%
	Report_BO bo = Report_BO.getInstance();
	String rept_id = request.getParameter("rept_id");
    ReportFileInfo_DTO dto = bo.getReportFileInfo(rept_id);
    String filename = dto.getRept_nm()+".xls";

    //테스트
    dto.setRept_file_nm("e:\\" + dto.getRept_file_nm().substring(3));
    java.io.File file = new java.io.File(dto.getRept_file_nm());

    String userAgent = request.getHeader("User-Agent");
    if (userAgent.indexOf("MSIE") > -1) {
        filename = java.net.URLEncoder.encode(filename, "UTF-8");
    }else{
        filename = new String(filename.getBytes("utf-8"),"8859_1");
    }

    if(file.exists()) {
    	out.clear();
    	out = pageContext.pushBody();
        response.setContentType("file/unknown");
        response.setHeader("Content-Disposition", "attachment;filename="+ filename+ ";");
        response.setHeader("Content-Transfer-Encoding", "binary;");
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Content-Length", String.valueOf(file.length()));
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Cache-Control", "private");
        response.setDateHeader( "Expires", 0);
        
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
        BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream());
        
        byte[] buffer = new byte[1024];
        int read = 0;
        while ((read = bis.read(buffer)) != -1){
            bos.write(buffer,0,read);
        }

        bos.close();
        bis.close();
        
    }else{
        response.setContentType("text/html; charset=euc-kr");
        out.println(filename + "이 존재하지 않습니다.");
    }
%>