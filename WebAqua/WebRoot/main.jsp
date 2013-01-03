<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="java.net.URLDecoder" %>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>::::: AQUA - Automatic Quality Analysis System에 오신것을 환영합니다. ::::</title>
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
		
				<!--visual 영역 --------------------------------->
	    		<table width="100%" border="0" cellpadding="0" cellspacing="0">
	      			<tr>
	        			<td height="260" align="center" style="background-image:url(images/main/visualBg.gif); background-repeat:repeat-x;"><img src="images/main/visual.jpg" ></td>
	      			</tr>
	    		</table>
	    		<!--visual 영역 --------------------------------->
    
	     		<!--게시물 영역 --------------------------------->
	    		<table width="900" border="0" cellpadding="0" cellspacing="0">
	      			<tr>
	        			<td class="main_contBg">
	        				<table width="100%" border="0" cellspacing="0" cellpadding="0">
	          					<tr>
	            					<td width="100" class="Lbg_main">스마트서비스</td>
	            					<td class="Cbg_main">&nbsp;스마트앱서비스(날씨,마켓,네비)에 대한 사용자통계,프로토콜통계 제공</td>
	            				</tr>
	          					<tr>
	            					<td class="Lbg_main">피쳐폰서비스</td>
	            					<td class="Cbg_main">&nbsp;WCDMA,WIFI,WIFI-PDG에 대한 KUN,WIPI,VOD,IMS등 프로토콜통계 제공</td>
	          					</tr>
	          					<tr>
	            					<td class="Lbg_main">품질분석</td>
	            					<td class="Cbg_main">&nbsp;Call Log,오류로그,스마트앱추이,교통정보통계에 대한 데이터 제공</td>
	          					</tr>
	          					<tr>
	            					<td class="Lbg_main">집중관리서비스</td>
	            					<td class="Cbg_main">&nbsp;sDSC내부 통계데이터 입력 및 검색 기능 제공</td>
	          					</tr>
	          					<tr>
	            					<td class="Lbg_main">트래픽보고서</td>
	            					<td class="Cbg_main" bgcolor="white">&nbsp;일일상황일지등 보고서 검색 및 출력기능 제공</td>
	          					</tr>
	        				</table>
	        			</td>
	      			</tr>
	    		</table>
	    	<!--게시물 영역 --------------------------------->
	
			<!-- footer 영역 --> 
			<%@ include file="/include/footer.jsp" %>
			<!-- footer 영역 --> 
		</td>
	</tr>
</table>
</body>
</html>	
	
	