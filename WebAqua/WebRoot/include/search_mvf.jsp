<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="beans" uri="/WEB-INF/struts-bean.tld" %>
<%@ taglib prefix="c"	uri="http://java.sun.com/jsp/jstl/core" %>
<input type="hidden" id="file_name" name="file_name" value = "<beans:write name="mvfName"/>">
<object id="View1" name="View1" classid='clsid:5DCB714F-3509-11D6-9C7C-00E029904B7D' width="100%" height='500'></object>
<div id="main_title1" style="display: none">
HOME &gt; ${menuStr[0] } <span id="menu1_title">&gt; ${menuStr[1] }</span>
<c:if test="${menuStr[1] != menuStr[2] }">
 &gt; ${menuStr[2] }
</c:if>
&gt;
<span id="detail_title1" class="add_sel"></span>
</div>
<div id="sub_title1" style="display: none">
<span id="menu1_title1">${menuStr[1] }</span> 
<c:if test="${menuStr[1] != menuStr[2] }">
${menuStr[2] }
</c:if>
<span id="detail_title2" style="color:#6CF;"></span>
</div>
<input type="hidden" id="path" name="path" value="${path}" />