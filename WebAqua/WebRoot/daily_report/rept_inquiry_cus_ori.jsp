<%@ page contentType="text/html;charset=euc-kr" %>
<%@ taglib uri="/iss-struts-bean" prefix="bean" %>
<%@ taglib uri="/iss-struts-html" prefix="html" %>
<%@ taglib uri="/iss-struts-logic" prefix="logic" %>
<%@ taglib uri="/ISSTags" prefix="sttdto" %>
<html> 
<head>
<title>:::::::: KTF 아쿠아 레포트 생성 시스템 ::::::::</title>
<meta http-equiv="Content-Type" content="text/html; charset=euc-kr">
<link rel="stylesheet" href="/WebAqua/css/txt.css" type="text/css">
<script language="javascript">

function autoBlur(){ 
	if(event.srcElement.tagName=="A"||event.srcElement.tagName=="IMG") 
		document.body.focus(); 
} 
document.onfocusin=autoBlur; 

function click_pageno(pageno) {
	document.ReptInquiryForm.cur_page.value=pageno;
	var eventTimeFrom = document.ReptInquiryForm.prev_eventTimeFrom.value;
	var eventTimeTo = document.ReptInquiryForm.prev_eventTimeTo.value;
	while(eventTimeFrom.indexOf('/') > -1) {
		var i = eventTimeFrom.indexOf('/');
		eventTimeFrom = eventTimeFrom.substring(0, i) + eventTimeFrom.substring(i+1);
	}
	while(eventTimeTo.indexOf('/') > -1) {
		var j = eventTimeTo.indexOf('/');
		eventTimeTo = eventTimeTo.substring(0, j) + eventTimeTo.substring(j+1);
	}
	document.ReptInquiryForm.eventTimeFrom.value = eventTimeFrom;
	document.ReptInquiryForm.eventTimeTo.value = eventTimeTo;
	document.ReptInquiryForm.rept_tmpl_nm.value = document.ReptInquiryForm.prev_rept_tmpl_nm.value;
	document.ReptInquiryForm.rept_nm.value = document.ReptInquiryForm.prev_rept_nm.value;
	document.ReptInquiryForm.eventTimeFrom.disabled = false;
	document.ReptInquiryForm.eventTimeTo.disabled = false;
	document.ReptInquiryForm.submit();
}

function click_delete(rept_id) {
	document.ReptActionForm.action="/WebAqua/daily_report/rept_delete.do";
	document.ReptActionForm.rept_id.value = rept_id;
	document.ReptActionForm.submit();
}

function click_search() {
	document.ReptInquiryForm.cur_page.value=1;
	var eventTimeFrom = document.ReptInquiryForm.eventTimeFrom.value;
	var eventTimeTo = document.ReptInquiryForm.eventTimeTo.value;

	while(eventTimeFrom.indexOf('/') > -1) {
		var i = eventTimeFrom.indexOf('/');
		eventTimeFrom = eventTimeFrom.substring(0, i) + eventTimeFrom.substring(i+1);
	}
	while(eventTimeTo.indexOf('/') > -1) {
		var j = eventTimeTo.indexOf('/');
		eventTimeTo = eventTimeTo.substring(0, j) + eventTimeTo.substring(j+1);
	}

	document.ReptInquiryForm.eventTimeFrom.value = eventTimeFrom;
	document.ReptInquiryForm.eventTimeTo.value = eventTimeTo;
	document.ReptInquiryForm.eventTimeFrom.disabled = false;
	document.ReptInquiryForm.eventTimeTo.disabled = false;

	return true;
}

</script>
<SCRIPT LANGUAGE="JavaScript" src="/js/calendar_iss.js"></SCRIPT>
</head>

<body bgcolor="#FFFFFF" text="#000000" leftmargin="0" topmargin="0">
<table width="857" height="50" border="0" cellpadding="0" cellspacing="0">
  <tr> 
    <td height="5"></td>
  </tr><tr>
      <td valign="top"> 
        <table width="820" border="0" align="center" cellpadding="0" cellspacing="0">
          <tr> 
            <td><table width="820" border="0" cellspacing="0" cellpadding="0">
                <tr> 
                  <td width="490" height="32"><img src="/WebAqua/img/title05.gif"></td>
                  <td width="330"><jsp:include page="rept_page_map_cus.jsp" /> 보고서 조회</td>
                </tr>
              </table></td>
          </tr>
          <tr> 
            <td height="14"></td>
          </tr>
<form name="ReptInquiryForm" action="/WebAqua/daily_report/rept_inquiry_cus.do" method="post" onsubmit="return click_search();">
          <tr> 
            <td height="20"><table width="810" border="0" align="center" cellpadding="0" cellspacing="0">
                <tr> 
                  <td width="129" height="25"></td>
				  <td width="81"><img src="/WebAqua/img/05_2.gif" width="81" height="17"></td>
				  <td width="105"><input type="text" name="rept_nm" size="15" value="<bean:write name="REPT_NM"/>" class="input"></td>
				  <td width="70" height="25"><div align="right"><img src="/WebAqua/img/img_form_name.gif" width="69" height="30"></div></td>
                  <td width="105" height="25"><input type="text" name="rept_tmpl_nm" size="15" value="<bean:write name="REPT_TMPL_NM"/>" class="input"></td>
                  <td width="50" height="25"><img src="/WebAqua/img/sear_29.gif" width="50" height="30"></td>
                  <td width="240" height="25">
                  <!--
                    <input name="eventTimeFrom" type="text" class="input" value="<bean:write name="eventTimeFrom"/>" size="12" disabled> 
                    <img src="/WebAqua/img/cal.gif" width="21" height="21" border="0" style="cursor:hand" onclick="showCalendar(document.all['eventTimeFrom'])" align="absmiddle"> 
                    ~ <input name="eventTimeTo" type="text" class="input" value="<bean:write name="eventTimeTo"/>" size="12" disabled>
                    <img src="/WebAqua/img/cal.gif" width="21" height="21" border="0" style="cursor:hand" onclick="showLeftCalendar(document.all['eventTimeTo'])" align="absmiddle">
                  -->
                    <input name="eventTimeFrom" type="text" class="input" value="<bean:write name="eventTimeFrom"/>" size="15"> 
                    ~ <input name="eventTimeTo" type="text" class="input" value="<bean:write name="eventTimeTo"/>" size="15">                                      
                  </td>
                  <td width="30" height="25"><div align="right"><input type="image" name="" src="/WebAqua/img/search_03.gif" border="0" onfocus="this.blur();" style="border:0"></div></td>
                </tr>
              </table></td>
          </tr>
<input type='hidden' name='cur_page' value='<bean:write name="CUR_PAGE"/>'>
<input type='hidden' name='prev_eventTimeFrom' value='<bean:write name="eventTimeFrom"/>'>
<input type='hidden' name='prev_eventTimeTo' value='<bean:write name="eventTimeTo"/>'>
<input type='hidden' name='prev_rept_nm' value='<bean:write name="REPT_NM"/>'>
<input type='hidden' name='prev_rept_tmpl_nm' value='<bean:write name="REPT_TMPL_NM"/>'>
</form>
          <tr> 
            <td> </td>
          </tr>
          <tr> 
            <td valign="top" bgcolor="#F6F6F6"><table width="810" border="0" cellspacing="0" cellpadding="0">
                <tr> 
                  <td height="5"></td>
                </tr>
              </table>
              <!--여기서부터게시판 시작입니다.-->
              <table width="810" border="0" align="center" cellpadding="0" cellspacing="0">
                <tr> 
                  <td bgcolor="#CCCCCC">
                    <table width="810" border="0" cellpadding="0" cellspacing="1">
                      <tr> 
                        <td height="25" colspan="6" background="/img/table_bg.gif">
                          <table border="0" cellpadding="0" cellspacing="0" class="title">
                            <tr>
                              <td width="70"><div align="center"><font color="#FFFFFF"><strong>번호</strong></font></div></td>
                              <td width="1"><img src="/WebAqua/img/table_line.gif" width="1" height="25"></td>
                              <td width="130"><div align="center"><font color="#FFFFFF"><strong>생성일자</strong></font></div></td>
                              <td width="1"><img src="/WebAqua/img/table_line.gif" width="1" height="25"></td>
                              <td width="220"><div align="center"><font color="#FFFFFF"><strong>양식  이름</strong></font></div></td>
                              <td width="1"><img src="/WebAqua/img/table_line.gif" width="1" height="25"></td>
                              <td width="220"><div align="center"><font color="#FFFFFF"><strong>보고서 이름</strong></font></div></td>
                              <td width="1"><img src="/WebAqua/img/table_line.gif" width="1" height="25"></td>
                              <td width="100"><div align="center"><font color="#FFFFFF"><strong>다운로드</strong></font></div></td>
                            </tr>
                          </table>
                        </td>
                      </tr>
					<logic:notEmpty name="REPT_LIST" >
					<logic:iterate name="REPT_LIST" id="rept" indexId="index">
					<%
                      	String bgcolor = "#F6F6F6";
                      	if(index.intValue()%2 == 0) { 
                      		bgcolor="#FFFFFF";
                      	}
                     %>
                      <tr bgcolor="<%= bgcolor %>" borderColorLight="#" onmouseover="this.className='menuover'-1" onmouseout="this.className='menuNo-1'">   
                        <td width="63"  height="20" align="center"><bean:write name="rept" property="rownum"/></td> 
                        <td width="117"  height="20" align="center"><bean:write name="rept" property="reg_dt"/></td>
                        <td width="200"   height="20" align="center"><bean:write name="rept" property="rept_tmpl_nm"/></td>
						<td width="201"  height="20" align="center"><bean:write name="rept" property="rept_nm"/></td>
						<td width="149"  height="20" align="center"><a href="/WebAqua/daily_report/rept_download.do?rept_id=<bean:write name="rept" property="rept_id"/>" target="top"><img src="/WebAqua/img/btn_receive.gif" border="0"></a></td>
                      </tr>
					</logic:iterate>
					</logic:notEmpty>
					<logic:empty name="REPT_LIST">
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
            </table></td>
          </tr>
          <tr> 
            <td> </td>
          </tr>
          <tr> 
            <td> </td>
          </tr>
          <tr> 
            <td height="10"><br> <table width="810" border="0" align="center" cellpadding="0" cellspacing="0">
                <tr> 
                  <td align="center"><sttdto:pageNavigator total_rec_cnt="TOTAL_LINE" cur_pageno="CUR_PAGE" MAX_LINE="MAX_LINE" func_nm="click_pageno" /></td>
                </tr>
              </table></td>
          </tr>
          <tr> 
            <td height="20"></td>
          </tr>
      </table></td>
  </tr>
</table>
<form name="ReptActionForm" action="/WebAqua/daily_report/rept_delete.do" method="post">
<input type="hidden" name="rept_id" value="">
</form>
</body>
</html>
