<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%	
	session.invalidate(); 
	String code = (String)request.getParameter("code");
	String errMsg = "";
	//System.out.println(">>"+code);
	if( code.equals("404")){
		errMsg = "�߸��� ��û�Դϴ�.\\n�α����������� �̵��մϴ�.";
	}else {
		errMsg = "�Ͻ����� ������ �α����������� �̵��մϴ�.\\n ��� ������ �߻��� �� �����ڿ��� �����Ͻñ� �ٶ��ϴ�.";
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
		