<%@ page contentType="text/html;charset=euc-kr" %>
<%@ page import="org.apache.struts.*" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<% 
	Exception ex  = (Exception)request.getAttribute(Globals.EXCEPTION_KEY);
	StackTraceElement[] ste = ex.getStackTrace();
%>
<html>
<head>
<title><bean:message key="index.title"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=euc-kr">
<link rel="stylesheet" href="/WebAqua/css/txt.css" type="text/css">
<script language="javascript"> 
function goMain() {
	document.redirect.submit();
	if(opener) {
		self.close();
	}
}
</script>
</head>

<body bgcolor="#FFFFFF" text="#000000" leftmargin="0" topmargin="0">
<table width="857" border="0" cellspacing="0" cellpadding="0">
  <tr> 
    <td><br> <br> <br> <br> <br> <br> <br> <br> <br> 
      <TABLE WIDTH=605 BORDER=0 align="center" CELLPADDING=0 CELLSPACING=0>
        <TR> 
          <TD> <IMG SRC="/WebAqua/img/wt_01.gif" WIDTH=43 HEIGHT=41 ALT=""></TD>
          <TD width="50" rowspan="4"><img src="/WebAqua/img/error.gif" width="181" height="171"> 
          </TD>
          <TD> <IMG SRC="/WebAqua/img/wt_04.gif" WIDTH=386 HEIGHT=41 ALT=""></TD>
          <TD> <IMG SRC="/WebAqua/img/wt_05.gif" WIDTH=34 HEIGHT=41 ALT=""></TD>
        </TR>
        <TR> 
          <TD>&nbsp; </TD>
          <TD> <IMG SRC="/WebAqua/img/wt_23.gif" WIDTH=386 HEIGHT=42 ALT=""></TD>
          <TD>&nbsp; </TD>
        </TR>
        <TR> 
          <TD>&nbsp; </TD>
          <TD valign="middle"><table width="386" border="0" cellspacing="0" cellpadding="0">
              <tr> 
                <td width="62">&nbsp;</td>
                <td width="322"><table width="330" border="0" cellspacing="5" cellpadding="0">
                    <tr> 
                      <td height="25">
                        <!--  <b><%= ex.getMessage() %></b>-->
                        <br>
                        <b>첨부파일의 원시데이터를 삭제 후 보고서 양식의 첨부파일을 수정해 주시기 바랍니다</b>
                        <br><br>
                        <!--%
							
							for(int i = 0; i < ste.length; i++) {
								out.println(ste[i].toString() + "<br>");
							}
                        %-->
                      </td>
                    </tr>
                    <tr>
                      <td height="15"><img src="/WebAqua/img/dot_01.gif" width="9" height="9"> 자세한 
                        사항은 관리자에게 문의해 주십시오. </td>
                    </tr>
                    <tr>
                    </tr>
                  </table></td>
              </tr>
            </table></TD>
          <TD>&nbsp;</TD>
        </TR>
        <TR> 
          <TD> <IMG SRC="/WebAqua/img/wt_16.gif" WIDTH=43 HEIGHT=37 ALT=""></TD>
          <TD> <IMG SRC="/WebAqua/img/wt_19.gif" WIDTH=386 HEIGHT=37 ALT=""></TD>
          <TD> <IMG SRC="/WebAqua/img/wt_20.gif" WIDTH=34 HEIGHT=37 ALT=""></TD>
        </TR>
        <TR> 
          <TD height="50" colspan="4"><div align="center"><a href="/daily_report/rept_tmpl_inquiry.do" onfocus="this.blur();" style="border:0"><img src="/WebAqua/img/sure.gif" width="54" height="23" border="0"></a></div></TD>
        </TR>
      </TABLE></td>
  </tr>
</table>
<p>&nbsp;</p>
<form name="redirect" action="/iss/main.iss" target="top">
</body>
</html>
