<%@ page contentType="text/html;charset=euc-kr" %>
<%@ taglib uri="/iss-struts-bean" prefix="bean" %>
<%@ taglib uri="/iss-struts-html" prefix="html" %>
<%@ taglib uri="/iss-struts-logic" prefix="logic" %>
<%@ taglib uri="/ISSTags" prefix="sttdto" %>
<html> 
<head>
<title>:::::::: KTF 아쿠아 레포트 생성 시스템 ::::::::</title>
<meta http-equiv="Content-Type" content="text/html; charset=euc-kr">
<link rel="stylesheet" href="/css/txt.css" type="text/css">
<script language="JavaScript"> 
	
	function autoBlur(){ 
		
		if(event.srcElement.tagName=="A"||event.srcElement.tagName=="IMG"){
			document.body.focus();	
		} 
	} 
		document.onfocusin=autoBlur; 
	
	function click_create(){
		
		/*
		if(document.all.rept_nm.value == null || document.all.rept_nm.value == "") {
			alert("보고서 이름을 입력하세요!");
			return;
		}
		*/
		
		document.ReptCreateForm.submit();
	}
		
</script>
</head>

<body bgcolor="#FFFFFF" text="#000000" leftmargin="0" topmargin="0">
	<table width="857" height="50" border="0" cellpadding="0" cellspacing="0">
		<tr> 
			<td height="5"></td>
	  	</tr>
	  	
	  	<tr>
			<td valign="top"> 
				<table width="820" border="0" align="center" cellpadding="0" cellspacing="0">
					<tr> 
	            		<td>
	            			<table width="820" border="0" cellspacing="0" cellpadding="0">
	                			<tr> 
	                  				<td width="585"><img src="/img/title04.gif"></td>
	                  				<td width="235"><jsp:include page="rept_page_map_cus.jsp" /> 보고서 생성</td>
	                			</tr>
	              			</table>
	              		</td>
	          		</tr>
	          		
	          		<tr> 
	            		<td height="14"></td>
	          		</tr>
<form name="ReptCreateForm" action="/daily_report/rept_create_cus.do" method="post">
<input type='hidden' name='rept_tmpl_id' value='<bean:write name="TMPL_INFO" property="rept_tmpl_id"/>'>
<input type='hidden' name='rept_tmpl_nm' value='<bean:write name="TMPL_INFO" property="rept_tmpl_nm"/>'>
<input type='hidden' name='date_fld' value='<bean:write name="DATE_FLD"/>'>
<input type="hidden" name="in_param" size="10" value="" class="input" >
	          		
	          		<tr> 
			            <td bgcolor="#F6F6F6">
			            	<table width="810" border="0" cellspacing="0" cellpadding="0">
	                			<tr> 
	                  				<td height="5"></td>
	                			</tr>
							</table>
	              
	              			<!--여기서부터게시판 시작입니다.-->              
	              			<table width="810" border="0" align="center" cellpadding="0" cellspacing="0">
	                			<tr> 
	                  				<td bgcolor="#CCCCCC">
	                  					<table width="810" border="0" align="center" cellpadding="0" cellspacing="1">
	                      					<tr> 
	                        					<td width="150" height="25" background="/img/table_bg1.gif"><div align="left"><font color="#FFFFFF"><strong><img src="/img/blink.gif" width="20" height="5">보고서 양식 이름</strong></font></div></td>
	                        					<td width="660" height="25" bgcolor="#FFFFFF"><img src="/img/blink.gif" width="10" height="5"><bean:write name="TMPL_INFO" property="rept_tmpl_nm"/></td>
	                      					</tr>
	                    				</table>
	                    			</td>
	                			</tr>
	              			</table>
	              			
	              			<table width="810" border="0" cellspacing="0" cellpadding="0">
	                			<tr> 
	                  				<td height="5"></td>
	                			</tr>
	              			</table>
	              		</td>
	          		</tr>
	          	
		          	<tr> 
		            	<td></td>
		          	</tr>
	          		
	          		<tr> 
	            		<td valign="top" bgcolor="#F6F6F6">
	            			<table width="810" border="0" cellspacing="0" cellpadding="0">
	                			<tr>
	                				<td height="5"></td>
	                			</tr>
							</table>
	              			
	              			<!--여기서부터게시판 시작입니다.-->
	                		<table width="810" border="0" cellspacing="0" cellpadding="0">	<!-- table tag 추가 shy -->
	                			<tr> 
	                  				<td>
				        				<logic:notEmpty name="STAT_LIST" >
											<logic:iterate name="STAT_LIST" id="statInfo" indexId="index">
												<%
							                      	String bgcolor = "#F6F6F6";
							                      	if(index.intValue()%2 == 0) { 
							                      		bgcolor="#FFFFFF";
							                      	}
							                    %>
<input type="hidden" value=<%= index.intValue()+1 %>'>
<input type="hidden" value=<bean:write  name="statInfo" property="sheet_no" />'>
<input type="hidden" value=<bean:write  name="statInfo" property="start_row" />'>
<input type="hidden" value=<bean:write  name="statInfo" property="start_col" />'>
<input type="hidden" value=<bean:write  name="statInfo" property="end_row" />'>
<input type="hidden" value=<bean:write  name="statInfo" property="end_col" />'>
<input type="hidden" value=<bean:write  name="statInfo" property="data_type" />'>
<input type="hidden" name="in_param" size="10" value="" class="input">
<%--<input type="hidden" value=<bean:write name="statInfo" property="sql" />'> --%>
											</logic:iterate>
										</logic:notEmpty>
										
										<logic:empty name="STAT_LIST">
	                        				<td  height="30" align="center"> 
												검색된 데이터가 없습니다.
	                        				</td>
										</logic:empty>
	                  				</td>
	                			</tr>
	              			</table>
	              			
	              			<table width="810" border="0" cellspacing="0" cellpadding="0">
	                			<tr> 
	                  				<td height="2"></td>
	                			</tr>
	            			</table>
	            		</td>
	          		</tr>
	          		
	          		<tr> 
	            		<td></td>
	          		</tr>
	          		<tr> 
	            		<td></td>
	          		</tr>
	          		
	          		<tr> 
	            		<td height="10"><br> 
							<table width="810" border="0" align="center" cellpadding="0" cellspacing="0">
	                			<tr> 
	                  				<td height="20"></td>
	                			</tr>
	                			
	                			<tr> 
	                  				<td align="center">
	                  					<img src="/img/btn_creation.gif" style="cursor:hand" onclick="click_create();">&nbsp;&nbsp;
	                    				<img src="/img/btn_cancel.gif" style="cursor:hand" onclick="history.back();">
	                    			</td>
	                			</tr>
	              			</table>
	              		</td>
	          		</tr>
</form>
		          	<tr> 
		          		<td height="20"></td>
		          	</tr>
	      		</table>
	      	</td>
	  	</tr>
	</table>
</body>
</html>
