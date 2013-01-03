<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jstl/sql" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<% pageContext.setAttribute("crlf","\r\n"); %>
<sql:query var="row" dataSource="jdbc/AQUADataSource">
		select bbs_title,contents,to_char(reg_date,'YYYY-MM-DD') reg_date,reg_id from AQUA2_NOTICE_BOARD_TBL where sequence = '${param.num}'		 
</sql:query>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=euc-kr">
<title>::::: AQUA - Automatic Quality Analysis System�� ���Ű��� ȯ���մϴ�.. ::::</title>
<link href="css/aqua.css" rel="stylesheet" type="text/css">
</head>
<body>
<!--�⺻ ���̺� ������� ���δ� 100%�̴� ���Ƿ� ���� �ֽø� �Ǳ���
�ּ� 300�ȼ��̻��� �ּž� �մϴ�. ���δ� �⺻ ����� 386�ȼ��Դϴ�. ���� ���Ƽ� Ŀ���� �ڵ����� �þ�� �صξ����ϴ�. -->
<c:forEach var="res" items="${row.rows}">
<table width="500px" border="0" cellspacing="0" cellpadding="0" class="popupBox">
  <tr>
    <td valign="top">
    
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td class="popTbg"><img src="images/popup/pt01.gif"></td>
        <td class="popRbg"><img src="images/popup/bt_closed.gif" alt="�ݱ�" width="51" height="15" border="0" onclick="window.close()"></td>
      </tr>
    </table>
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td class="popTitle">${res.bbs_title }</td>
      </tr>
    </table>
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="70" class="popLbg">�ۼ���</td>
        <td class="Cbg">${res.reg_id }</td>
        <td width="70" class="popLbg">�ۼ���</td> 
        <td class="Cbg">${res.reg_date }</td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td class="popRead">
    	<div style="overflow:auto;width:100%;height:200px">
    	${fn:replace(res.contents,crlf,"<br/>")}
    	</div>
   	</td>
  </tr>
</table>
</c:forEach>
</body>
</html>
