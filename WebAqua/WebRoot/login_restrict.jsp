<%@ page contentType="text/html; charset=EUC-KR" %>
<%
	//쿠키삭제.
	Cookie cookie[] = request.getCookies();
	for(int i=0 ; i<cookie.length; i++)
	{
	   cookie[i].setMaxAge(0);		  
	   response.addCookie(cookie[i]);
	}
%>
<script type="text/JavaScript">
	alert("내부직원은 8080 포트로 접속하시기 바랍니다.");	
	location.href = "http://kt68aqua.magicn.com:8080/login.do?cmd=logout";
</script>
