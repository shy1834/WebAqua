<%@ page contentType="text/html; charset=EUC-KR" %>
<%
	//��Ű����.
	Cookie cookie[] = request.getCookies();
	for(int i=0 ; i<cookie.length; i++)
	{
	   cookie[i].setMaxAge(0);		  
	   response.addCookie(cookie[i]);
	}
%>
<script type="text/JavaScript">
	alert("���������� 8080 ��Ʈ�� �����Ͻñ� �ٶ��ϴ�.");	
	location.href = "http://kt68aqua.magicn.com:8080/login.do?cmd=logout";
</script>
