<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>:::: AQUA - Automatic Quality Analysis System�� ���Ű��� ȯ���մϴ�. ::::</title>
<link href="/css/aqua.css" rel="stylesheet" type="text/css">
<script src="/js/mybuilder_view.js"></script>
<script type="text/javascript">
<!--
	function init(){
		mySet(document.form1);
		document.form1.View1.style.height = "500px";		
		document.form1.View1.Param("userId")          = "${cookie.userId.value}";
		document.form1.View1.Param("userLevel")       = "${cookie.userLevel.value}";
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
				<!-- header  -->
			
	     		<!-- ��ġ�� ���� --------------------------------->
    			<table width="${page_width }" border="0" cellpadding="0" cellspacing="0">
      				<tr>
        				<td align="right" style="padding:15px 10px 0px 0px;">
        					<table border="0" cellspacing="0" cellpadding="0">
          						<tr>
            						<td width="20"><img src="images/common/blit01.gif" width="12" height="12"></td>
            						<td class="add_bg">HOME &gt; <span class="add_sel">${adminMenuList[param.menu].code_desc }</span></td>
          						</tr>
        					</table>
        
        				</td>
      				</tr>
    			</table>
     			<!-- ��ġ�� ���� --------------------------------->     
     
     			<!-- Ÿ��Ʋ ���� --------------------------------->
     			<table width="${page_width }" border="0" cellpadding="0" cellspacing="0">
       				<tr>
         				<td class="Left_area"><!-- �������� ----------------->
           					<table border="0" cellspacing="0" cellpadding="0">
             					<tr>
               						<td class="Left_top">�����ڸ��</td>
             					</tr>
             					
             					<tr>
               						<td class="Left_MNbox"><!-- ���� �����޴� ----------------->
                 						<table width="100%" border="0" cellspacing="0" cellpadding="0">
                   							<c:forEach var="list" items="${adminMenuList}" varStatus="cnt">
	                   							<tr>
	                   								<td class="Left_blit"><a href="/adminPage.do?menu=${cnt.index}" class="mainboard">${list.code_desc }</a></td>
	                   							</tr>
                   							</c:forEach>
                 						</table>
                 					</td><!-- ���� �����޴� ----------------->
             					</tr>
             					
             					<tr>
               						<td><img src="images/common/Limg01.gif" ></td>
             					</tr>             
           					</table>
           				</td><!-- �������� ----------------->
           				
         				<td align="left" valign="top" class="Cont_area">
         				<!--�߾� ������ ���� --------------------------------------------------------->
           				<!--������ġ�� ----------------->
           				<!--//������ġ�� ----------------->
           				<!--���ù�ư ----------------->
           				<!-- <table width="100%" border="0" cellspacing="0" cellpadding="0">
            					<tr>
              						<td class="bt_Tguide"><a href="#"><img src="images/bt/select_all.gif" alt="��ü����" border="0"></a> 
              											  <a href="#"><img src="images/bt/select_clear.gif" alt="����" border="0"></a>
              						</td>
            					</tr>
          					</table> -->
           				<!--//���ù�ư ----------------->
           				<!--�Խù�����Ʈ ----------------->
           				<!--//�Խù�����Ʈ ----------------->
           				<!--����¡ ----------------->
           
           					<table width="100%" border="0" cellpadding="0" cellspacing="0">
             					<tr>
               						<td class="title01">${adminMenuList[param.menu].code_desc }</td>
             					</tr>
           					</table>
           					<br>
           		
           					<%-- wifi�����Ͱ��� ���Ͼ��ε� --%>
           					<c:if test="${param.menu == 7 }">
           						<iframe src="/upload/file_form.jsp" frameborder="0" width="700" height="52" scrolling="no"></iframe>            	
           					</c:if>
           					<input type="hidden" id="file_name" name="file_name" value="${adminMenuList[param.menu].mvf_file }">
		   					<script src="/js/tstat.js"></script>
           
           				<!--//����¡ ----------------->
           				</td>
					</tr>
				</table>
    			<br>
    			<!-- Ÿ��Ʋ ���� --------------------------------->
				<!-- �Խù� ���� --------------------------------->
     			
				<!-- footer  -->
				<%@ include file="/include/footer.jsp" %>
				<!-- footer  -->
			</td>
		</tr>
	</table>
</form>