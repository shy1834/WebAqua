<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%
	/* 로컬용 상용에서는 주석처리 setMaxAge()안에 음수가 올경우 브라우저가 종료될 때 쿠키도 함께 삭제됨. */
	Cookie cookie  = new Cookie("userName", "AHM");
	cookie.setMaxAge(-1);			
	response.addCookie(cookie);
	
	Cookie cookie1 = new Cookie("userId", "ahmax");
	cookie1.setMaxAge(-1);
	response.addCookie(cookie1);
	
	Cookie cookie2 = new Cookie("userLevel", "00");
	cookie2.setMaxAge(-1);
	response.addCookie(cookie2);
	
	Cookie cookie3 = new Cookie("cpId", "00389");
	cookie3.setMaxAge(-1);
	response.addCookie(cookie3);
	
	Cookie cookie4 = new Cookie("userType", "01");
	cookie4.setMaxAge(-1);
	response.addCookie(cookie4);
	
	
%>      
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>::::: AQUA - Automatic Quality Analysis System에 오신것을 환영합니다. ::::</title>
<link href="css/aqua.css" rel="stylesheet" type="text/css">
<script src="/js/jquery-1.4.4.min.js"></script>
<script src="/js/login.js"></script>
<script type="text/javascript" src="/js/mybuilder_view.js"></script>
<script type="text/javascript">
<!--
	var doamain_url = "http://<%=request.getServerName()%>:<%=request.getServerPort()%>";
//-->
</script>
</head>
<body>
<form id="loginForm" name="loginForm" method="post">
	<table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td align="center" class="loginBg">
		    	<table width="1000" border="0" cellpadding="0" cellspacing="0">
	      			<tr>
	        			<td class="loginGuide" align="left">
	        				<table border="0" cellspacing="0" cellpadding="0">
	          					<tr>
	            					<td width="48" height="40"><img src="images/login/id.gif" alt="아이디"></td>
	            					<td width="138" align="center"><input name="ID" type="text" class="tf_default" id="ID" style="width:130px;"></td>
	            					<td><img src="images/login/pw.gif" alt="패스워드" ></td>
	            					<td width="138" align="center"><input name="PASSWORD" type="password" class="tf_default" id="PASSWORD" style="width:130px;"></td>
	            					<td width="120" align="right"><img id="loginBtn" src="images/login/bt_login.gif" alt="login" border="0"></td>
	          					</tr>
	        				</table>
	        			</td>
	      			</tr>
	    		</table>
	    	</td>
	  	</tr>
	</table>
</form>
<%@ include file="/include/mybuilder_Init.jsp"%>
</body>
</html>
