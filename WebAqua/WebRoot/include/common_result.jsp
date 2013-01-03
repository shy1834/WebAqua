<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>:::: AQUA - Automatic Quality Analysis System�� ���Ű��� ȯ���մϴ�. ::::</title>
<link href="css/aqua.css" rel="stylesheet" type="text/css">
<link rel='stylesheet' href='/css/calendar.css' type='text/css' />
<link rel='stylesheet' href='/css/calendar_mon.css' type='text/css' />
<script src="/js/jquery-1.4.4.min.js"></script>
<script src="/js/jquery.mousewheel.min.js"></script>
<script src="/js/mybuilder_view.js"></script>
<script src="/js/calendar.js"></script>
<script src="/js/calendar_mon.js"></script>
<script src="/js/common_result.js"></script>
<script type="text/javascript">
<!--
	var authType = "${cookie.userLevel.value}";
	var userId	 = "${cookie.userId.value}";
//-->
</script>
</head>
<body onload="init()">
<form name="form1" action="menu1.do" method="post">
	<table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
	    	<td align="center" class="mainBg">
		
				<!-- header -->
				<c:import url="/topMenu.do" var="topMenu" /> 
				<c:out value="${topMenu}" escapeXml="false" />
				<!-- header -->
				
				<!--��ġ�� ���� --------------------------------->
	    		<table width="1024" border="0" cellpadding="0" cellspacing="0">
	      			<tr>
	        			<td align="right" style="padding:15px 10px 0px 0px;">
	        				<table border="0" cellspacing="0" cellpadding="0">
	          					<tr>
	            					<td width="20"><img src="images/common/blit01.gif" width="12" height="12"></td>
	            					<td class="add_bg" id="main_title">
	            						HOME &gt; ${mainMenu[param.menuIndex].text}
	            					<%-- HOME &gt; ME��� &gt; TCP &gt; <span class="add_sel">�ð迭</span> --%>
	            					</td>
	          					</tr>
	        				</table>
	        			</td>
	      			</tr>
	    		</table>
	     		<!--��ġ�� ���� --------------------------------->
     
	     		<!--�˻�â ���� --------------------------------->
	     		<table width="900" border="0" cellpadding="0" cellspacing="0">
	      			<tr>
	        			<td class="title" id="sub_title">
	        				${mainMenu[param.menuIndex].text}
	        			<%-- ME TCP <span style="color:#6CF;">�ð迭</span> --%>	        
	       			 	</td>
	      			</tr>
	      			
	      			<tr>
	       				<td class="search_guide">
							<!--�˻��׸���� <li> ������ �߰��Ͻø� �˴ϴ�. ��ĭ�� ũ�Ⱑ 285�ȼ��� �Ѿ�� ���� 3ĭ ����� ���� �� �ֽ��ϴ� -->
			    			<div id="we" class="fotCnt" style="width:100%;">
		          		        <%@ include file="/include/search_default.jsp" %>
		          		    </div>
		        			<!--//�˻��׸���� <li> ������ �߰��Ͻø� �˴ϴ�.  -->
		        		</td>
	      			</tr>
	      			
	      			<tr>
	        			<td class="search_guide01">
	        				<table width="978" border="0" cellspacing="0" cellpadding="0">
	          					<tr>
	            					<td align="right" width="50%">
	            						<div id="daily_report" style="display:none">
		            						<img src="/images/btn_newfile.gif" style="cursor:hand" onclick="createReport();">
		            						<img src="/images/btn_filelist.gif" style="cursor:hand" onclick="listReport();">
	            						</div>
	            						
	            						<div id="dateTypeSel" style="display: none">
											<span id="minType">
												<html:radio name="pForm" property="dayType" value="5" onclick="dateInit('Y',this.value)" /><span onclick="$(dayType)[0].click()" style="cursor:hand">5�� ��</span></span>
												<html:radio name="pForm" property="dayType" value="1" onclick="dateInit('Y',this.value)" /><span onclick="$(dayType)[1].click()" style="cursor:hand">�ð���</span>
												<html:radio name="pForm" property="dayType" value="2" onclick="dateInit('Y',this.value)" /><span onclick="$(dayType)[2].click()" style="cursor:hand">�Ϻ�</span>
												<html:radio name="pForm" property="dayType" value="3" onclick="dateInit('Y',this.value)" /><span onclick="$(dayType)[3].click()" style="cursor:hand">�ֺ�</span>
												<span id="monthType"><html:radio name="pForm" property="dayType" value="4" onclick="dateInit('Y',this.value)" /><span onclick="$(dayType)[4].click()" style="cursor:hand">����</span></span>
										</div>
	            					</td>
	            					
	            					<td align="right" width="40%">
	            						<input type="text" id="from_date"  class="tf_default" style="display: none">
	            						<select id="from_hour" style="display: none;width:40px" class="tf_default">
											<c:forEach var="item" items="${selHour }">
												<option value="${item.id }">${item.text }</option>
											</c:forEach>
										</select>
										
										<select id="from_min" style="display: none;width:40px" class="tf_default">
											<c:forEach var="item" begin="0"  items="${selMin}">
												<option value="${item.id }">${item.text }</option>
											</c:forEach>
										</select>
					
										<input type="text" id="from_month" class="tf_default" style="display: none">
										<span id="day-">-</span>
										<input type="text" id="to_date" class="tf_default" style="display: none">
										
										<select id="to_hour" style="display: none;width:40px" class="tf_default">
											<c:forEach var="item" items="${selHour }">
												<option value="${item.id }">${item.text }</option>
											</c:forEach>
										</select>
										<input type="text" id="to_month" class="tf_default" style="display: none">
					
										<script type="text/javascript">
											initCal("to_date");			
											initCal("from_date");
											initCalMon("to_month");			
											initCalMon("from_month");			
										</script>
										<span id="content5"></span>
	            					</td>
	            					
	            					<td width="10%" align="right">
	            						<img src="images/bt/search.gif" id="searchBtn" alt="�˻�" width="80" height="30" border="0" style="cursor:hand">
	            					</td>
	          					</tr>
	        				</table>
						</td>
					</tr>
				</table>
	    		<!--//�˻�â ���� --------------------------------->
	    		
	    		<!--mvf--------------------------------->
	     		<table width="1024" border="0" cellpadding="0" cellspacing="0">
	      			<tr>
	        			<td class="graph_guide" align="center">
	        				<!-- <span id="loading">loading</span> -->
	        				<span id="content6"></span>
	        			</td>
	      			</tr>
	    		</table>
	    		<!--mvf--------------------------------->
     		
				<!-- footer  -->
				<%@ include file="/include/footer.jsp" %>
				<!-- footer  -->
			</td>
		</tr>
	</table>
</form>

</body>
<input type="hidden" id="menuType" value="${param.menuType }"/>
<input type="hidden" id="userLevel" value="${cookie.userLevel.value}"/>
<input type="hidden" id="cpId" value="${cookie.cpId.value}"/>
</html>