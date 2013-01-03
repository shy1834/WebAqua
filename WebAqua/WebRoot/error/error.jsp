<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%	
	session.invalidate(); 
	String code = (String)request.getParameter("code");
	String errMsg = "";
	//System.out.println(">>"+code);
	if( code.equals("404")){
		errMsg = "잘못된 요청입니다.\\n로그인페이지로 이동합니다.";
	}else {
		errMsg = "일시적인 오류로 로그인페이지로 이동합니다.\\n 계속 문제가 발생할 시 관리자에게 문의하시기 바랍니다.";
	}
%>    		
<script type="text/javascript">
<!--
	alert("<%=errMsg%>");
	location.href= "/login.do?cmd=login";	
//-->
</script>
<%for(int i=0;i<200;i++){ %>
&nbsp;
<%} %>
		