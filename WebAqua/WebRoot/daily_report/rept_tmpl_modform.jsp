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

/*
var statisticsInfoCount = 1;
function addStatisticsInfo() {
	var table = document.all.statInfo;
	var rowIndex = table.rows.length;
	var tr = table.insertRow(rowIndex);
	if(Math.round(rowIndex/2) % 2  == 1)
		tr.bgColor = "#FFFFFF";
	else
		tr.bgColor = "#F6F6F6";
	//tr.id = "statInfo_" + whereCounter;

	var tr1 = table.insertRow(rowIndex+1);
	tr1.bgColor = tr.bgColor;

	var td = tr.insertCell();
	td.height = 40;
	td.width = 50;
	td.align = 'center';
	td.rowSpan = 2;
	td.innerHTML = ++statisticsInfoCount;

	td = tr.insertCell();
	td.height = 20;
	td.width = 100;
	td.rowSpan = 1;
	td.align = 'center';
	var element = document.createElement("<input type='text' name='sheet_no' size='13' value='' class='input'>");
	td.insertBefore(element);
	
	td = tr.insertCell();
	td.height = 20;
	td.width = 100;
	td.rowSpan = 1;
	td.align = 'center';
	var element = document.createElement("<input type='text' name='start_row' size='13' value='' class='input'>");
	td.insertBefore(element);
	
	td = tr.insertCell();
	td.height = 20;
	td.width = 100;
	td.rowSpan = 1;
	td.align = 'center';
	element = document.createElement("<input type='text' name='start_col' size='13' value='' class='input'>");
	td.insertBefore(element);

	td = tr.insertCell();
	td.height = 20;
	td.width = 100;
	td.rowSpan = 1;
	td.align = 'center';
	element = document.createElement("<input type='text' name='end_row' size='13' value='' class='input'>");
	td.insertBefore(element);

	td = tr.insertCell();
	td.height = 20;
	td.width = 100;
	td.rowSpan = 1;
	td.align = 'center';
	element = document.createElement("<input type='text' name='end_col' size='13' value='' class='input'>");
	td.insertBefore(element);

	td = tr.insertCell();
	td.height = 20;
	td.width = 190;
	td.rowSpan = 1;
	td.align = 'center';
	element = document.createElement("<input type='text' name='data_type' size='28' value='' class='input'>");
	td.insertBefore(element);
	
	td = tr.insertCell();
	td.height = 20;
	td.width = 70;
	td.rowSpan = 2;
	td.align = 'center';
	element = document.createElement("<img src='/img/btn_delete.gif' width='20' height='16' style='cursor:hand' onclick='deleteStatisticsInfo(\""+tr.uniqueID+"\", \""+tr1.uniqueID+"\")'>");
	td.insertBefore(element);

	td = tr1.insertCell();
	td.height = 20;
	td.colSpan = 6;
	td.rowSpan = 1;
	td.align = 'center';
	element = document.createElement("<input type='text' name='sql' size='112' value='' class='input'>");
	td.insertBefore(element);
}

function deleteStatisticsInfo(tr1, tr2) {
	var table = document.all.statInfo;
	for(var i = 0;  i < table.rows.length; i++) {
		if(table.rows[i].uniqueID == tr1 || table.rows[i].uniqueID == tr2) {
			table.deleteRow(i);
			i--;
		}
	}
}
*/

<bean:size name='STAT_LIST' id='sic'/>
var statisticsInfoCount = <%= sic %>;
function addStatisticsInfo() {
	var table = document.all.statInfo;
	var rowIndex = table.rows.length;

	var tr_bar1 = table.insertRow(rowIndex);

	var tr = table.insertRow(rowIndex+1);
	if(Math.round(rowIndex/4) % 2  == 1)
		tr.bgColor = "#FFFFFF";
	else
		tr.bgColor = "#F6F6F6";
	tr.align = 'center';
	
	var tr_bar2 = table.insertRow(rowIndex+2);

	var tr1 = table.insertRow(rowIndex+3);
	tr1.bgColor = tr.bgColor;
	tr1.align = 'center';

	var td = tr_bar1.insertCell();
	td.height = 1;
	td.colSpan = 15;
	td.bgColor = '#CCCCCC';
	
	td = tr.insertCell();
	td.height = 20;
	td.rowSpan = 3;
	td.innerHTML = ++statisticsInfoCount;
	addDelemiter(tr);
	
	td = tr.insertCell();
	td.rowSpan = 1;
	var element = document.createElement("<input type='text' name='sheet_no' size='13' value='' style='width:90px' class='input'>");
	td.insertBefore(element);
	addDelemiter(tr);
	
	td = tr.insertCell();
	td.rowSpan = 1;
	var element = document.createElement("<input type='text' name='start_row' size='13' value='' style='width:90px' class='input'>");
	td.insertBefore(element);
	addDelemiter(tr);
	
	td = tr.insertCell();
	td.rowSpan = 1;
	element = document.createElement("<input type='text' name='start_col' size='13' value='' style='width:90px' class='input'>");
	td.insertBefore(element);
	addDelemiter(tr);

	td = tr.insertCell();
	td.rowSpan = 1;
	element = document.createElement("<input type='text' name='end_row' size='13' value='' style='width:90px' class='input'>");
	td.insertBefore(element);
	addDelemiter(tr);

	td = tr.insertCell();
	td.rowSpan = 1;
	element = document.createElement("<input type='text' name='end_col' size='13' value='' style='width:90px' class='input'>");
	td.insertBefore(element);
	addDelemiter(tr);

	td = tr.insertCell();
	td.rowSpan = 1;
	td.align = 'center';
	element = document.createElement("<input type='text' name='data_type' size='28' value='' style='width:201px' class='input'>");
	td.insertBefore(element);
	addDelemiter(tr);
	
	td = tr.insertCell();
	td.rowSpan = 3;
	element = document.createElement("<img src='/WebAqua/img/del_01.gif' width='21' height='21' style='cursor:hand' onclick='deleteStatisticsInfo(\""+tr.uniqueID+"\", \""+tr1.uniqueID+"\", \""+tr_bar1.uniqueID+"\", \""+tr_bar2.uniqueID+"\")'>");
	td.insertBefore(element);

	td = tr_bar2.insertCell();
	td.height = 1;
	td.colSpan = 13;
	td.bgColor = '#CCCCCC';

	addDelemiter(tr1);
	td = tr1.insertCell();
	td.height = 20;
	td.colSpan = 11;
	td.rowSpan = 1;
	td.align = 'center';
	element = document.createElement("<input type='text' name='sql' size='112' value='' style='width:706px' class='input'>");
	td.insertBefore(element);
	addDelemiter(tr1);
}

function addDelemiter(tr) {
	var td = tr.insertCell();
	td.rowSpan = 1;
	td.bgColor = '#CCCCCC';
}

function deleteStatisticsInfo(tr1, tr2, tr3, tr4) {
	var table = document.all.statInfo;
	for(var i = 0;  i < table.rows.length; i++) {
		if(table.rows[i].uniqueID == tr1 || table.rows[i].uniqueID == tr2
			|| table.rows[i].uniqueID == tr3 || table.rows[i].uniqueID == tr4) {
			table.deleteRow(i);
			i--;
		}
	}
}

function click_submit() {
//	if(validateReportTemplateForm(document.ReportTemplateForm)) {
		document.ReportTemplateForm.submit();
//	}
}

function openTableInfo() {
	var sFeatures = "height=390,width=650,status=yes,toolbar=no,menubar=no,location=no";
	window.open("/WebAqua/daily_report/pop_table_inquiry.do", null, sFeatures);
}

</script>
<html:javascript formName="ReportTemplateForm"/>

</head>

<body bgcolor="#FFFFFF" text="#000000" leftmargin="0" topmargin="0">
<table width="857" height="50" border="0" cellpadding="0" cellspacing="0">
  <tr> 
    <td height="5"></td>
  </tr>
  <tr>
      <td> 
        <table width="820" border="0" align="center" cellpadding="0" cellspacing="0">
          <tr> 
            <td><table width="820" border="0" cellspacing="0" cellpadding="0">
                <tr> 
                  <td width="469"><img src="/WebAqua/img/title03.gif" width="137" height="29"></td>
                  <td width="351"><jsp:include page="rept_page_map.jsp" /> 보고서양식 수정</td>
                </tr>
              </table></td>
          </tr>
          <tr> 
            <td height="14"></td>
          </tr>
<html:form action="/daily_report/rept_tmpl_modify.do" method="POST" enctype="multipart/form-data" onsubmit="return validateReportTemplateForm(this);">
<html:hidden name="TMPL_INFO" property="rept_tmpl_id" />
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
                        <td width="660" height="25" bgcolor="#FFFFFF"><img src="/WebAqua/img/blink.gif" width="10" height="5"><html:text name="TMPL_INFO" property="rept_tmpl_nm" size="20" styleClass="input" /></td>
                      </tr>
                      <tr> 
                        <td width="150" height="25" background="/WebAqua/img/table_bg1.gif"><div align="left"><font color="#FFFFFF"><strong><img src="/WebAqua/img/blink.gif" width="20" height="5">보고서 양식 파일</strong></font></div></td>
                        <td height="25" bgcolor="#FFFFFF"><img src="/WebAqua/img/blink.gif" width="10" height="5"><html:file name="TMPL_INFO" property="rept_tmpl_file" value="" styleClass="input"/></td>
                      </tr>
                      <tr> 
                        <td width="150" height="25" background="/WebAqua/img/table_bg1.gif"><div align="left"><font color="#FFFFFF"><strong><img src="/WebAqua/img/blink.gif" width="20" height="5">보고서 생성 주기</strong></font></div></td>
                        <td height="25" bgcolor="#FFFFFF"><img src="/WebAqua/img/blink.gif" width="10" height="5">
                          <html:radio name="TMPL_INFO" property="rept_prd" value="D" onfocus="this.blur();" style="border:0"/> 일간
                          <html:radio name="TMPL_INFO" property="rept_prd" value="W" onfocus="this.blur();" style="border:0"/> 주간 
                          <html:radio name="TMPL_INFO" property="rept_prd" value="M" onfocus="this.blur();" style="border:0"/> 월간 
                          <html:radio name="TMPL_INFO" property="rept_prd" value="R" onfocus="this.blur();" style="border:0"/> 임의 
                        </td>
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
            <td height="20"></td>
          </tr>
          <tr> 
            <td height="20"><table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td height="20"><img src="/WebAqua/img/st_32.gif"  width="125" height="30"></td>
                <td width="74%" align="right"><img src="/WebAqua/img/tableinfo.gif" width="108" height="23" alt="통계테이블정보" style="cursor:hand" onclick="openTableInfo();" align="absmiddle">&nbsp;</td>
                <td width="9%" align="right" style="padding-right:10px"><img src="/WebAqua/img/btn_addition.gif" width="62" height="23" style="cursor:hand" onclick="addStatisticsInfo();"></td>
              </tr>
            </table></td>
          </tr>
          <tr> 
            <td> </td>
          </tr>
          <tr> 
            <td bgcolor="#F6F6F6"><table width="810" border="0" cellspacing="0" cellpadding="0">
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
			            <td width="50" rowspan="3"><b><font color="#FFFFFF">번호</font></b></td>
			            <td width="1" height="25" background="/img/table_line1.gif"></td>
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
			            <td width="211" ><b><font color="#FFFFFF">테이터 타입</font></b></td>
			            <td width="1" background="/WebAqua/img/table_line1.gif"></td>
			            <td width="40" rowspan="3"><b><font color="#FFFFFF">삭제</font></b></td>
			          </tr>
			          <tr>
			            <td height="1" colspan="13" background="/WebAqua/img/table_line1.gif"></td>
			          </tr>
					  <tr>
			            <td width="1" height="24" background="/WebAqua/img/table_line1.gif"></td>
			            <td colspan="11" align="center"><b><font color="#FFFFFF">SQL</font></b></td>
			            <td width="1" background="/WebAqua/img/table_line1.gif"></td>
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
			            <td><html:text name="statInfo" property="sheet_no" style="width:90px" styleClass="input"/></td>
			            <td width="1" bgcolor="#CCCCCC"></td>
			            <td><html:text name="statInfo" property="start_row" style="width:90px" styleClass="input"/></td>
			            <td width="1" bgcolor="#CCCCCC"></td>
			            <td><html:text name="statInfo" property="start_col" style="width:90px" styleClass="input"/></td>
			            <td width="1" bgcolor="#CCCCCC"></td>
			            <td><html:text name="statInfo" property="end_row" style="width:90px" styleClass="input"/></td>
			            <td width="1" bgcolor="#CCCCCC"></td>
			            <td><html:text name="statInfo" property="end_col" style="width:90px" styleClass="input"/></td>
			            <td width="1" bgcolor="#CCCCCC"></td>
			            <td><html:text name="statInfo" property="data_type" style="width:201px" styleClass="input"/></td>
			            <td width="1" bgcolor="#CCCCCC"></td>
			            <td rowspan="3"><img src="/img/del_01.gif" width="21" height="21" style='cursor:hand' onclick='deleteStatisticsInfo(this.parentNode.parentNode.uniqueID, this.parentNode.parentNode.nextSibling.nextSibling.uniqueID, this.parentNode.parentNode.previousSibling.uniqueID, this.parentNode.parentNode.nextSibling.uniqueID)'></td>
			          </tr>
			          <tr>
			            <td height="1" colspan="13" bgcolor="#CCCCCC"></td>
			          </tr>
			          <tr bgcolor="<%= bgcolor %>">
			            <td width="1" height="20" bgcolor="#CCCCCC"></td>
			            <td colspan="11" align="center"><html:text name="statInfo" property="sql"  style="width:706px" styleClass="input"/></td>
			            <td width="1" bgcolor="#CCCCCC"></td>
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
                  </table></td>
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
                  <td align="center"><img width="62" height="23" src="/WebAqua/img/btn_modify02.gif" onclick='click_submit();' style='cursor:hand'>&nbsp;&nbsp;
                    <img src="/WebAqua/img/btn_cancel.gif" width="62" height="23" onclick='history.back();' style='cursor:hand'> </td>
                </tr>
              </table></td>
          </tr>
</html:form>
          <tr> 
            <td height="20"></td>
          </tr>
        </table>
      </td>
  </tr>
</table>
</body>
</html>
