<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="html" uri="/WEB-INF/struts-html.tld" %>
<%@ taglib prefix="logic" uri="/WEB-INF/struts-logic.tld" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	<div class="fotMainMn" align="left">		
		<c:if test="${fn:length(searchTypeList) != 0}">
			<span id="searchType">
				<span class="sub_blit">검색타입</span> 
				<span class="sub_none">
					<html:select styleId="cdma_dev" name="pForm" property="cdma_dev" styleClass="tf_none">
						<html:options collection="searchTypeList" property="id" labelProperty ="text"/>
					</html:select>
				</span>
			</span>
		</c:if>
		
		<span class="sub_blit">1차메뉴</span> 
		<span class="sub_none">
			<html:select styleId="menu1" name="pForm" property="menu1" onchange="sel_menu1()" styleClass="tf_none">
				<html:options collection="mainMenuList" property="id" labelProperty ="text"/>
			</html:select>
		</span>
		
		<span id="content">
		</span>
	</div>
<html:hidden name="pForm" property="menuType" styleId="menuType" />
<iframe id="sMenu" class="sMenu" src="/include/selectbox.jsp" frameborder="0"></iframe>
<iframe id="sMenu1" class="sMenu" src="/include/selectbox1.jsp" frameborder="0"></iframe>

