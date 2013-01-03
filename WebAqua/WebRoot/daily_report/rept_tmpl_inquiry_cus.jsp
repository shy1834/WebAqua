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
<script language="javascript">
	
	function autoBlur(){ 
		
		if(event.srcElement.tagName=="A"||event.srcElement.tagName=="IMG"){
			document.body.focus();  
		} 
	} 
		document.onfocusin=autoBlur; 
	
	function click_pageno(pageno) {
		
		document.ReptTmpeInquiryForm.cur_page.value=pageno;
		document.ReptTmpeInquiryForm.submit();
	}
	
	function click_modify(rept_tmpl_id) {
		
		document.TemplateActionForm.action="/daily_report/rept_tmpl_modform.do";
		document.TemplateActionForm.rept_tmpl_id.value=rept_tmpl_id;
		document.TemplateActionForm.submit();
	}
	
	function click_create(rept_tmpl_id, date_fld) {
		
		document.TemplateActionForm.action="/daily_report/rept_create_form_cus.do?date_fld="+date_fld;
		document.TemplateActionForm.rept_tmpl_id.value=rept_tmpl_id;
		//alert(document.TemplateActionForm.rept_tmpl_id.value);
		document.TemplateActionForm.submit();
	}
	
	function click_delete(rept_tmpl_id) {
		
		if(confirm("보고서 양식을 정말 삭제 하시겠습니까?")) {
			document.TemplateActionForm.action="/daily_report/rept_tmpl_delete.do";
			document.TemplateActionForm.rept_tmpl_id.value=rept_tmpl_id;
			document.TemplateActionForm.submit();
		}
	}
	
	function search_tmpl() {
		document.ReptTmpeInquiryForm.cur_page.value=1;
		return true;
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
	                  				<td width="572"><img src="/img/title.gif" width="141" height="28"></td>
	                  				<td width="250"><jsp:include page="rept_page_map_cus.jsp" /> 보고서 양식 관리</td>
	                			</tr>
	              			</table>
	              		</td>
	          		</tr>
	          		
	          		<tr> 
	            		<td height="14"></td>
	          		</tr>
					
<form name="ReptTmpeInquiryForm" action="/daily_report/rept_tmpl_inquiry_cus.do?date_fld=${DATE_FLD }" method="post" onsubmit="return search_tmpl();">
	          		<tr> 
	            		<td height="20">
	            			<table width="810" border="0" align="center" cellpadding="0" cellspacing="0">
				                <tr>
				                  	<td width="532" height="25"></td>
								  	<td width="44" height="25">
								  		<div align="right"><img src="/img/img_form_name.gif" width="69" height="30"></div>
								  	</td>
				                  	<td width="90" height="25"><input type="text" name="rept_tmpl_nm" size="15" value="<bean:write name="REPT_TMPL_NM"/>" class="input"></td>
				                  	<td width="28" height="25">
				                  		<div align="right"><input type="image" name="" src="/img/search_03.gif" border="0" onfocus="this.blur();" style="border:0"></div>
				                  	</td>
				                </tr>
							</table>
						</td>
					</tr>
<input type='hidden' name='cur_page' value='<bean:write name="CUR_PAGE"/>'>
</form>
	    
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
							<table width="810" border="0" align="center" cellpadding="0" cellspacing="0">
								<tr> 
									<td valign="top" bgcolor="#CCCCCC"><table width="810" border="0" cellpadding="0" cellspacing="1">
				                      	<tr> 
				                        	<td height="25" colspan="7" background="/img/table_bg.gif">
				                        		<table width="810" border="0" cellpadding="0" cellspacing="0" class="title">
				                          			<tr>
				                            			<td width="50">
				                            				<div align="center"><strong><font color="#FFFFFF">번호</font></strong></div>
				                            			</td>
				                            			<td><img src="/img/table_line.gif" width="1" height="25"></td>
				                            			<td width="210">
				                            				<div align="center"><strong><font color="#FFFFFF">양식 이름</font></strong></div>
				                            			</td>
				                            			<td><img src="/img/table_line.gif" width="1" height="25"></td>
				                            			<td width="210">
				                            				<div align="center"><strong><font color="#FFFFFF">양식 파일 이름</font></strong></div>
				                            			</td>
				                            			<td><img src="/img/table_line.gif" width="1" height="25"></td>
				                            			<td width="130">
				                            				<div align="center"><strong><font color="#FFFFFF">보고서 생성</font></strong></div>
				                            			</td>
				                          			</tr>
				                        		</table>
											</td>
				                      	</tr>
									
											<logic:notEmpty name="TMPL_LIST" >
												<logic:iterate name="TMPL_LIST" id="tmpl" indexId="index">
													<%
								                      	String bgcolor = "#F6F6F6";
								                      	if(index.intValue()%2 == 0){ 
								                      		bgcolor="#FFFFFF";
								                      	}
								                     %>
						                      		<tr bgcolor="<%= bgcolor %>" borderColorLight="#" onmouseover="this.className='menuover-1'" onmouseout="this.className='menuNo-1'">
						                        		<td width="44" height="20" align="center"><bean:write name="tmpl" property="rownum"/></td> 
						                        		<td width="259" height="20" align="center"><bean:write name="tmpl" property="rept_tmpl_nm"/></td>
						                        		<td width="205" height="20" align="center"><bean:write name="tmpl" property="rept_tmpl_file_nm"/></td>
														<td width="125" height="20" align="center"><img src="/img/btn_report_creation.gif" style="cursor:hand" onclick="click_create('<bean:write name="tmpl" property="rept_tmpl_id"/>', '<bean:write name="DATE_FLD"/>');"></td>
						                      		</tr>
												</logic:iterate>
											</logic:notEmpty>
										
											<logic:empty name="TMPL_LIST">
						                      	<tr bgcolor="#F6F6F6"> 
						                        	<td  height="30" align="center"> 
														검색된 데이터가 없습니다.
						                        	</td>
						                      	</tr>
											</logic:empty>
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
						<td></td>
					<tr> 
						<td> </td>
					</tr>
			   					
			   		<tr> 
			      		<td height="10"><br> 
			      			<table width="810" border="0" align="center" cellpadding="0" cellspacing="0">
								<tr> 
									<td>
										<td align="center"><sttdto:pageNavigator total_rec_cnt="TOTAL_LINE" cur_pageno="CUR_PAGE" MAX_LINE="MAX_LINE" func_nm="click_pageno" /></td>
			          			</tr>
			          			<tr>
			            			<td align="left"><font color="red">*.레포트생성은 오전 09시 00분 이후에 실행하여 주십시오.</font></td>
			          			</tr>
			        		</table>
			        	</td>
					</tr>
						        		
					<tr> 
						<td height="20"></td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
<form name='TemplateActionForm' action='' method='post'>
<input type='hidden' name='rept_tmpl_id' value=''>
<input type='hidden' name='cur_page' value='<bean:write name="CUR_PAGE"/>'>
</form>
</body>
</html>
