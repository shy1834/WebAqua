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
</head>

<script language='javascript'>

<jsp:useBean id="DATA_LIST" scope="request" class="java.util.ArrayList"/>
var DataList = new Array(<%= DATA_LIST.size() %>);
// DataList[0] = new Array('platform_code', 'table_id', 'table_name', 'hasServiceID');
<logic:iterate id="data" scope="request" name="DATA_LIST" indexId="index">
DataList[<bean:write name="index"/>] = new Array('<bean:write name="data" property="platform_code"/>',	'<bean:write name="data" property="table_id"/>', '<bean:write name="data" property="table_name"/>', '<bean:write name="data" property="hasServiceID"/>');
</logic:iterate>
function change_platform(target) {
	var platforms = document.getElementsByName("platform");
	var table_ids = document.getElementsByName("table_id");
	var platform_code = platforms[target].options[platforms[target].selectedIndex].value;
	var index = 0;
	table_ids[target].length = 0;
	for(var i = 0; i < DataList.length; i++) {
		if(DataList[i][0] == platform_code) {
			table_ids[target].options[index] = new Option(DataList[i][2], DataList[i][1]);
			index++;
		}
	}
}

function goPageNo(pageNo) {
	document.PageSearchForm.page.value = pageNo;
	//if(validatePageSearchForm(document.PageSearchForm))
		document.PageSearchForm.submit();
}

function initPage() {
	change_platform(0);
	var table_id = '<bean:write name="TABLE_ID"/>';
	if(table_id.length > 0) {
		var table_ids = document.getElementsByName("table_id");
		for(var i = 0; i < table_ids[0].length; i++) {
			if(table_ids[0].options[i].value == table_id) {
				table_ids[0].selectedIndex = i;
			}
		}
		
	}
}
</script>
<body bgcolor="#FFFFFF" text="#000000" leftmargin="0" topmargin="0" onload="initPage()">
<table width="650" height="50" border="0" cellpadding="0" cellspacing="0">
  <tr>
      <td> <table width="650" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr> 
          <td height="34" background="/WebAqua/img/pop_bg.gif"><img src="/WebAqua/img/pop_t_55.gif" width="125" height="34"></td>
        </tr>
        <tr> 
          <td><table width="650" height="3" border="0" cellpadding="0" cellspacing="0">
              <tr> 
                <td bgcolor="#E7E7E7"></td>
              </tr>
            </table></td>
        </tr>
        <tr> 
          <td height="14">&nbsp;</td>
        </tr>
        <tr> 
<html:form action="/daily_report/pop_table_inquiry.do" method="post" >
          <td height="20"> 
              <input type="hidden" name="page" value="">
              <input type="hidden" name="rows" value="10">
              <table width="630" border="0" align="center" cellpadding="0" cellspacing="0">
                <tr> 
                  <td width="174"></td>
                  <td width="61"><img src="/WebAqua/img/sear_06.gif" width="58" height="30"></td>
                  <td width="80">
                    <html:select property="platform" onchange="change_platform(0);">
                      <html:options collection="PLATFORM_LIST" property="platform_code" labelProperty="platform_name"/>
                    </html:select> 
                  </td>
                  <td width="61"><div align="right"><img src="/WebAqua/img/sear_28.gif" width="58" height="30"> 
                    </div></td>
                  <td width="250">
                    <html:select property="table_id" style="width:250px">
                    </html:select>
                  </td>
                  <td width="28" align="right"> 
                    <input type="image" name="" src="/WebAqua/img/search_03.gif" border="0" onfocus="this.blur()" style="border:0">
                  </td>
                </tr>
              </table>
            </td>
</html:form>
        </tr>
        <tr>
          <td>
            <div style="overflow-y: auto; height:300px">
            <table width="630" border="0" align="center" cellpadding="0" cellspacing="0">
             <tr>
          <td bgcolor="#F6F6F6">
            <table width="630" border="0" cellspacing="0" cellpadding="0">
              <tr> 
                <td height="5"></td>
              </tr>
            </table>
            <!--여기서부터게시판 시작입니다.-->
            <table width="620" border="0" align="center" cellpadding="0" cellspacing="0">
              <tr> 
                <td bgcolor="#CCCCCC">
                  <table width="620" border="0" align="center" cellpadding="0" cellspacing="1">
                    <tr> 
                      <td height="25" colspan="3" background="/WebAqua/img/table_bg.gif"><table width="620" border="0" cellpadding="0" cellspacing="0" class="title">
                          <tr> 
                            <td width="250"><div align="center"><strong><font color="#FFFFFF">테이블명</font><br>
                                </strong></div></td>
                            <td width="1"><div align="center"><strong><font color="#FFFFFF"><img src="/WebAqua/img/table_line.gif" width="1" height="25"></font></strong></div></td>
                            <td width="250"><div align="center"><strong><font color="#FFFFFF">ALIAS</font><br>
                                </strong></div></td>
                            <td width="1"><div align="center"><strong><font color="#FFFFFF"><img src="/WebAqua/img/table_line.gif" width="1" height="25"></font></strong></div></td>
                            <td width="120"><div align="center"><strong><font color="#FFFFFF">통계타입<br>
                                </font></strong></div></td>
                          </tr>
                        </table></td>
                    </tr>
					<logic:notEmpty name="TABLE_LIST" >
					<logic:iterate name="TABLE_LIST" id="tableInfo" indexId="index">
					<%
                      	String bgcolor = "#F6F6F6";
                      	if(index.intValue()%2 == 0) { 
                      		bgcolor="#FFFFFF";
                      	}
                     %>
                    <tr bgcolor="<%= bgcolor %>"  borderColorLight=gray onmouseover="this.className='menuover-1'" onmouseout="this.className='menuNo-1'"> 
                      <td width="250" height="20"><img src="/WebAqua/img/blink.gif" width="8" height="5"><bean:write name="tableInfo" property="table_name"/></td>
                      <td width="250" height="20"><img src="/WebAqua/img/blink.gif" width="8" height="5"><bean:write name="tableInfo" property="alias"/></td>
                      <td width="120" height="20"><img src="/WebAqua/img/blink.gif" width="8" height="5"><bean:write name="tableInfo" property="aggr_result_type"/></td>
                    </tr>
					</logic:iterate>
					</logic:notEmpty>
					<logic:empty name="TABLE_LIST">
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
            <table width="630" border="0" cellspacing="0" cellpadding="0">
              <tr> 
                <td height="5"></td>
              </tr>
            </table></td>
        </tr>
        <tr> 
          <td height="10"></td>
        </tr>
        <tr> 
          <td bgcolor="#F6F6F6"><table width="630" border="0" cellspacing="0" cellpadding="0">
              <tr> 
                <td height="5"></td>
              </tr>
            </table>
            <table width="620" border="0" align="center" cellpadding="0" cellspacing="0">
              <tr> 
                <td bgcolor="#CCCCCC">
                  <table width="620" border="0" align="center" cellpadding="0" cellspacing="1">
                    <tr> 
                      <td height="25" colspan="5" background="/WebAqua/img/table_bg.gif"><table width="620" border="0" cellpadding="0" cellspacing="0" class="title">
                          <tr> 
                            <td width="200"><div align="center"><strong><font color="#FFFFFF">컬럼명</font><br>
                                </strong></div></td>
                            <td width="1"><div align="center"><strong><font color="#FFFFFF"><img src="/WebAqua/img/table_line.gif" width="1" height="25"></font></strong></div></td>
                            <td width="140"><div align="center"><strong><font color="#FFFFFF">ALIAS</font><br>
                                </strong></div></td>
                            <td width="1"><div align="center"><strong><font color="#FFFFFF"><img src="/WebAqua/img/table_line.gif" width="1" height="25"></font></strong></div></td>
                            <td width="50"><div align="center"><strong><font color="#FFFFFF">유형<br>
                            <td width="1"><div align="center"><strong><font color="#FFFFFF"><img src="/WebAqua/img/table_line.gif" width="1" height="25"></font></strong></div></td>
                            <td width="50"><div align="center"><strong><font color="#FFFFFF">크기<br>
                            <td width="1"><div align="center"><strong><font color="#FFFFFF"><img src="/WebAqua/img/table_line.gif" width="1" height="25"></font></strong></div></td>
                            <td width="180"><div align="center"><strong><font color="#FFFFFF">설명<br>
                                </font></strong></div></td>
                          </tr>
                        </table></td>
                    </tr>
					<logic:notEmpty name="COLUMN_LIST" >
					<logic:iterate name="COLUMN_LIST" id="columnInfo" indexId="index">
					<%
                      	String bgcolor = "#F6F6F6";
                      	if(index.intValue()%2 == 0) { 
                      		bgcolor="#FFFFFF";
                      	}
                     %>
                    <tr bgcolor="<%= bgcolor %>"  borderColorLight=gray onmouseover="this.className='menuover-1'" onmouseout="this.className='menuNo-1'"> 
                      <td width="200" height="20"><img src="/WebAqua/img/blink.gif" width="8" height="5"><bean:write name="columnInfo" property="col_name"/></td>
                      <td width="140" height="20"><img src="/WebAqua/img/blink.gif" width="8" height="5"><bean:write name="columnInfo" property="alias"/></td>
                      <td width="50" height="20"><img src="/WebAqua/img/blink.gif" width="8" height="5"><bean:write name="columnInfo" property="data_type"/></td>
                      <td width="50" height="20"><img src="/WebAqua/img/blink.gif" width="8" height="5"><bean:write name="columnInfo" property="len"/></td>
                      <td width="180" height="20"><img src="/WebAqua/img/blink.gif" width="8" height="5"><bean:write name="columnInfo" property="description"/></td>
                    </tr>
					</logic:iterate>
					</logic:notEmpty>
					<logic:empty name="COLUMN_LIST">
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
            <table width="630" border="0" cellspacing="0" cellpadding="0">
              <tr> 
                <td height="5"></td>
              </tr>
            </table>
          </td>
        </tr>
            </table>
            </div>
          </td>
        </tr>
      </table>
    </td>
  </tr>
</table>
</body>
</html>
