<%@page import="java.util.*,java.lang.*, com.ktf.aqua.mgr.menu.* , com.ktf.aqua.mgr.menu.common.MenuForm, com.ktf.aqua.user.common.User, com.ktf.aqua.mgr.menu.common.MenuAuth, com.ktf.aqua.mgr.menu.MenuManager"%>
<%@ page contentType="text/html;charset=euc-kr" %>

<!-- ����  -->
<%
	String sType = "7";
%>

<!-- ���  -->
<%@ include file="/include/header.jsp" %>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td align="center"><table width="1000" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td width="175" valign="top">
<!-- Ÿ��Ʋ -->
<%@ include file="/include/admin_title.jsp" %>
					</td>
				</tr>
			</table>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td align="center"><table width="1000" border="0" cellspacing="0" cellpadding="0">
        <tr>

<!-- �����޴� -->
<%@ include file="/include/admin_left.jsp" %>

    <td width="815" height="450" valign="top">
<!-- �����޴� -->
<%@ include file="/mgr/menu/pm_mst_right.jsp" %>
    </td>
    <td>&nbsp;</td>
  </tr>
</table>
					</td>
				</tr>
			</table>
<!-- Ǫ��  -->
<%@ include file="/include/footer.jsp" %>