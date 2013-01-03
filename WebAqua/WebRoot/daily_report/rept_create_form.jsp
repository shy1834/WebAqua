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
<script language="JavaScript"> 
function autoBlur(){ 
  if(event.srcElement.tagName=="A"||event.srcElement.tagName=="IMG") 
  document.body.focus(); 
} 
  document.onfocusin=autoBlur; 

function click_create() {
//	if(document.all.rept_nm.value == null || document.all.rept_nm.value == "") {
//		alert("보고서 이름을 입력하세요!");
//		return;
//	}
	
	document.ReptCreateForm.submit();
}
	
</script>
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
                  <td width="585"><img src="/WebAqua/img/title04.gif"></td>
                  <td width="235"><jsp:include page="rept_page_map.jsp" /> 보고서 생성</td>
                </tr>
              </table></td>
          </tr>
          <tr> 
            <td height="14"></td>
          </tr>
<form name="ReptCreateForm" action="/daily_report/rept_create.do" method="post">
<input type='hidden' name='rept_tmpl_id' value='<bean:write name="TMPL_INFO" property="rept_tmpl_id"/>'>
<input type='hidden' name='rept_tmpl_nm' value='<bean:write name="TMPL_INFO" property="rept_tmpl_nm"/>'>
          <tr> 
            <td bgcolor="#F6F6F6"><table width="810" border="0" cellspacing="0" cellpadding="0">
                <tr> 
                  <td height="5"></td>
                </tr>
              </table>
              <!--여기서부터게시판 시작입니다.-->              
              <table width="810" border="0" align="center" cellpadding="0" cellspacing="0">
                <tr> 
                  <td bgcolor="#CCCCCC"><table width="810" border="0" align="center" cellpadding="0" cellspacing="1">
                      <tr> 
                      	<td width="150" height="25" background="/WebAqua/img/table_bg1.gif"><div align="left"><font color="#FFFFFF"><strong><img src="/WebAqua/img/blink.gif" width="20" height="5">보고서 양식 이름</strong></font></div></td>
                        <td width="660" height="25" bgcolor="#FFFFFF"><img src="/img/blink.gif" width="10" height="5"><bean:write name="TMPL_INFO" property="rept_tmpl_nm"/></td>
                      </tr>
                    </table></td>
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
            <td valign="top" bgcolor="#F6F6F6"><table width="810" border="0" cellspacing="0" cellpadding="0">
                <tr> 
                  <td height="5"></td>
                </tr>
              </table>
              <!--여기서부터게시판 시작입니다.-->
              <table width="810" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#CCCCCC">
                <tr> 
                  <td>
					<table width="808" border="0" cellpadding="0" cellspacing="0" background="/WebAqua/img/table_bg_rept.gif" id="statInfo">
			          <tr align="center">
			            <td width="49" rowspan="3"><b><font color="#FFFFFF">번호</font></b></td>
			            <td width="1" height="25" background="/WebAqua/img/table_line1.gif"></td>
			            <td width="100"><b><font color="#FFFFFF">쉬트번호</font></b></td>
			            <td width="1"><img src="/WebAqua/img/table_line.gif"></td>
			            <td width="100"><b><font color="#FFFFFF">시작행</font></b></td>
			            <td width="1"><img src="/WebAqua/img/table_line.gif"></td>
			            <td width="100"><b><font color="#FFFFFF">시작열</font></b></td>
			            <td width="1"><img src="/WebAqua/img/table_line.gif"></td>
			            <td width="100" ><b><font color="#FFFFFF">마지막행</font></b></td>
			            <td width="1"><img src="/WebAqua/img/table_line.gif"></td>
			            <td width="100" ><b><font color="#FFFFFF">마지막열</font></b></td>
			            <td width="1"><img src="/WebAqua/img/table_line.gif"></td>
			            <td width="100" ><b><font color="#FFFFFF">테이터 타입</font></b></td>
			            <td width="1"><img src="/WebAqua/img/table_line.gif"></td>
			            <td width="151" ><b><font color="#FFFFFF">입력 파라미터</font></b></td>
			          </tr>
			          <tr>
			            <td height="1" colspan="14" background="/WebAqua/img/table_line1.gif"></td>
			          </tr>
					  <tr>
			            <td width="1" height="24" background="/WebAqua/img/table_line1.gif"></td>
			            <td colspan="13" align="center"><b><font color="#FFFFFF">SQL</font></b></td>
			          </tr>
					<logic:notEmpty name="STAT_LIST" >
					<logic:iterate name="STAT_LIST" id="statInfo" indexId="index">
					<%
                      	String bgcolor = "#F6F6F6";
                      	if(index.intValue()%2 == 0) { 
                      		bgcolor="#FFFFFF";
                      	}
                    %>
			          <tr>
			            <td height="1" colspan="15" bgcolor="#CCCCCC"></td>
			          </tr>
					  <tr align="center" bgcolor="<%= bgcolor %>">
			            <td rowspan="3"><%= index.intValue()+1 %></td>
			            <td width="1" height="20" bgcolor="#CCCCCC"></td>
			            <td><bean:write  name="statInfo" property="sheet_no" /></td>
			            <td width="1" bgcolor="#CCCCCC"></td>
			            <td><bean:write  name="statInfo" property="start_row" /></td>
			            <td width="1" bgcolor="#CCCCCC"></td>
			            <td><bean:write  name="statInfo" property="start_col" /></td>
			            <td width="1" bgcolor="#CCCCCC"></td>
			            <td><bean:write  name="statInfo" property="end_row" /></td>
			            <td width="1" bgcolor="#CCCCCC"></td>
			            <td><bean:write  name="statInfo" property="end_col" /></td>
			            <td width="1" bgcolor="#CCCCCC"></td>
			            <td><bean:write  name="statInfo" property="data_type" /></td>
			            <td width="1" bgcolor="#CCCCCC"></td>
			            <td><input type="text" name="in_param" size="10" value="" class="input"></td>
			          </tr>
			          <tr>
			            <td height="1" colspan="14" bgcolor="#CCCCCC"></td>
			          </tr>
			          <tr bgcolor="<%= bgcolor %>">
			            <td width="1" height="20" bgcolor="#CCCCCC"></td>
			            <td colspan="13" align="center"><bean:write name="statInfo" property="sql" /></td>
			          </tr>
					</logic:iterate>
					</logic:notEmpty>
					<logic:empty name="STAT_LIST">
                      <tr colspan="15" bgcolor="#F6F6F6"> 
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
                  <td height="2"></td>
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
            <td height="10"><br> 
			<table width="810" border="0" align="center" cellpadding="0" cellspacing="0">
                <tr> 
                  <td height="20"></td>
                </tr>
                <tr> 
                  <td align="center"><img src="/WebAqua/img/btn_creation.gif" style="cursor:hand" onclick="click_create();">&nbsp;&nbsp;
                    <img src="/WebAqua/img/btn_cancel.gif" style="cursor:hand" onclick="history.back();"> </td>
                </tr>
              </table></td>
          </tr>
</form>
          <tr> 
            <td height="20"></td>
          </tr>
      </table></td>
  </tr>
</table>
</body>
</html>
