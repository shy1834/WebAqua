<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="java.net.URLDecoder" %>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>::::: AQUA - Automatic Quality Analysis System�� ���Ű��� ȯ���մϴ�. ::::</title>
<link href="css/aqua.css" rel="stylesheet" type="text/css">
</head>
<body>
	<table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
	    	<td align="center" class="mainBg">
	
			<!-- header  -->
			<c:import url="/topMenu.do" var="topMenu" /> 
			<c:out value="${topMenu}" escapeXml="false" />
			<%-- 
			<jsp:include page="action.do" flush="true"/>
			<%@ include file="/topMenu.do" 
			--%> 
			<!-- header  -->
		
				<!--visual ���� --------------------------------->
	    		<table width="100%" border="0" cellpadding="0" cellspacing="0">
	      			<tr>
	        			<td height="260" align="center" style="background-image:url(images/main/visualBg.gif); background-repeat:repeat-x;"><img src="images/main/visual.jpg" ></td>
	      			</tr>
	    		</table>
	    		<!--visual ���� --------------------------------->
    
	     		<!--�Խù� ���� --------------------------------->
	    		<table width="900" border="0" cellpadding="0" cellspacing="0">
	      			<tr>
	        			<td class="main_contBg">
	        				<table width="100%" border="0" cellspacing="0" cellpadding="0">
	          					<tr>
	            					<td width="100" class="Lbg_main">����Ʈ����</td>
	            					<td class="Cbg_main">&nbsp;����Ʈ�ۼ���(����,����,�׺�)�� ���� ��������,����������� ����</td>
	            				</tr>
	          					<tr>
	            					<td class="Lbg_main">����������</td>
	            					<td class="Cbg_main">&nbsp;WCDMA,WIFI,WIFI-PDG�� ���� KUN,WIPI,VOD,IMS�� ����������� ����</td>
	          					</tr>
	          					<tr>
	            					<td class="Lbg_main">ǰ���м�</td>
	            					<td class="Cbg_main">&nbsp;Call Log,�����α�,����Ʈ������,����������迡 ���� ������ ����</td>
	          					</tr>
	          					<tr>
	            					<td class="Lbg_main">���߰�������</td>
	            					<td class="Cbg_main">&nbsp;sDSC���� ��赥���� �Է� �� �˻� ��� ����</td>
	          					</tr>
	          					<tr>
	            					<td class="Lbg_main">Ʈ���Ⱥ���</td>
	            					<td class="Cbg_main" bgcolor="white">&nbsp;���ϻ�Ȳ������ ���� �˻� �� ��±�� ����</td>
	          					</tr>
	        				</table>
	        			</td>
	      			</tr>
	    		</table>
	    	<!--�Խù� ���� --------------------------------->
	
			<!-- footer ���� --> 
			<%@ include file="/include/footer.jsp" %>
			<!-- footer ���� --> 
		</td>
	</tr>
</table>
</body>
</html>	
	
	