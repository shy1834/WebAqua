<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%><%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><c:if test="${param.l4 == '11100'}"><span class="sub_blit">서비스L4</span> 
<span class="sub_none">
	<select name="svc_smart" id="svc_smart" class="tf_none">
		<option value="0">전체</option>
		<option value="11100">올레네비</option>
		<option value="11200">올레네비_DN</option>
	</select>
</span></c:if><c:if test="${param.l4 == '11300'}"><span class="sub_blit">서비스L4</span> 
<span class="sub_none">
	<select name="svc_smart" id="svc_smart" class="tf_none">
		<option value="0">전체</option>
		<option value="11300">올레마켓</option>
		<option value="11350">올레마켓_DN</option>
	</select>
</span></c:if>