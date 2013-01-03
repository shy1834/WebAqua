<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>:::: AQUA - Automatic Quality Analysis System에 오신것을 환영합니다. ::::</title>
<link href="/css/aqua1.css" rel="stylesheet" type="text/css">
<script src="/js/mybuilder_view.js"></script>
<script type="text/javascript">
<!--
	function init(){
		mySet(document.form1);
		document.form1.View1.style.height = "400px";		
		document.form1.View1.Param("userId")          = "${cookie.userId.value}";
		document.form1.View1.Param("userLevel")          = "${cookie.userLevel.value}";
		document.form1.View1.Run();
	}
//-->
</script>
</head>
<body onload="init()">
<form name="form1">
<table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0">
	  <tr>
	    <td align="center" class="mainBg">
		
		<!-- header  -->
		<c:import url="/topMenu.do" var="topMenu" /> 
		<c:out value="${topMenu}" escapeXml="false" />
		
		<!--위치값 영역 --------------------------------->
	    <table width="900" border="0" cellpadding="0" cellspacing="0">
	      <tr>
	        <td align="right" style="padding:15px 10px 0px 0px;">
	        
	        <table border="0" cellspacing="0" cellpadding="0">
	          <tr>
	            <td width="20"><img src="images/common/blit01.gif" width="12" height="12"></td>
	            <td class="add_bg">HOME &gt; <span class="add_sel">공지사항</span></td>
	          </tr>
	        </table>
	        
	        </td>
	      </tr>
	    </table>
	     <!--//위치값 영역 --------------------------------->
	     
	     
	     <!--타이틀 영역 --------------------------------->
	     <table width="900" border="0" cellpadding="0" cellspacing="0">
	      <tr>
	        <td class="title01">공지사항</td>
	      </tr>
	      </table>
	     <br>
	     <!--//타이틀 영역 --------------------------------->
	    
	    <!--게시물 영역 --------------------------------->
		<table width="900" border="0" cellspacing="0" cellpadding="0">
			<tr>
		    	<td>
			    	<input type="hidden" id="file_name" name="file_name" value="mgr/bbs/bbs_gongji_list.mvf">
					<script src="/js/tstat.js"></script>
		    	</td>
		    </tr>
		</table>
	  
	  <!--버튼영역 ----------------------->
	    <table width="900" border="0" cellspacing="0" cellpadding="0">
	      <tr>
	        <td align="right" style="padding-right:38px;">	        	
	        </td>
	      </tr>
	  </table>
	  <br/><br/>
	  <!--//버튼영역 ----------------------->
	 <!--//게시물 영역 --------------------------------->
     		
	<!-- footer  -->
	<%@ include file="/include/footer.jsp" %>
	
	</td>
  </tr>
</table>
</form>