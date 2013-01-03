<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib prefix="html" uri="/WEB-INF/struts-html.tld" %>
<%@ taglib prefix="logic" uri="/WEB-INF/struts-logic.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<logic:notEmpty name="subMenuList">
<span class="sub_blit">2차메뉴</span> 
<span class="sub_none">
	<html:select name="pForm" styleId="menu2" property="menu2" onchange="sel_menu2()" styleClass="tf_none"> 
		<html:options collection="subMenuList" property="id" labelProperty ="text"/>
	</html:select>
</span>
</logic:notEmpty>					
<span id="content2">
<logic:notEmpty name="detailMenuList">
	<span class="sub_blit">상세분류</span> 
	<span class="sub_none">
		<html:select name="pForm" styleId="menu_dev" property="menu_dev" onchange="sel_menu3()" styleClass="tf_none">
			<html:options collection="detailMenuList" property="id" labelProperty ="text"/>
		</html:select>
	</span>
</logic:notEmpty>
<logic:notEmpty name="minTypeList">
	<span class="sub_blit">검색화면선택</span> 
	<span class="sub_none">
		<html:select name="pForm" styleId="min_type" property="min_type" styleClass="tf_none">
			<html:options collection="minTypeList" property="id" labelProperty ="text"/>
		</html:select>
	</span>
</logic:notEmpty>
<span id="content3">
	<logic:notEmpty name="plfList">
		<span class="sub_blit">플랫폼구분</span> 
		<span class="sub_none">
			<html:select name="pForm" property="plf_name" styleId="plf_name" onchange="sel_menu4()" styleClass="tf_none">
				<html:options collection="plfList" property="id" labelProperty ="text"/>
			</html:select>
		</span>
	</logic:notEmpty>
	<span id="content4">
		<logic:notEmpty name="svcList1">	
			<span class="sub_blit">서비스L4</span> 
			<span class="sub_none">
				<html:select name="pForm" property="svc_dev" styleId="svc_dev" onchange="sel_menu_smart()" style="float:left" styleClass="tf_none">
					<html:options collection="svcList1" property="id" labelProperty ="text"/>
				</html:select>
			</span>
		</logic:notEmpty>
		<logic:notEmpty name="plfList"><span id="smart_l4"></span></logic:notEmpty>		
		<logic:notEmpty name="svcList2">
			<span class="sub_blit">서비스L7</span> 
			<span class="sub_none">
				<html:select name="pForm" property="svc_dev_1" styleId="svc_dev_1" styleClass="tf_none">
					<html:options collection="svcList2" property="id" labelProperty ="text" styleClass="tf_none"/>
				</html:select>
			</span>
		</logic:notEmpty>
	</span>
	<logic:notEmpty name="dataDevList">	
		<span class="sub_blit">데이터구분</span> 
		<span class="sub_none">
			<html:select name="pForm" property="data_dev" styleId="data_dev" styleClass="tf_none">
				<html:options collection="dataDevList" property="id" labelProperty ="text"/>
			</html:select>
		</span>
		<span class="sub_blit">데이터구분검색</span> 
		<span class="sub_none">
			<html:text name="pForm" property="data_dev_val" styleId="data_dev_val" styleClass="tf_none" />
		</span>
	</logic:notEmpty>	
	<logic:notEmpty name="detailSearch">
		<span class="sub_blit">세부검색항목</span> 
		<span class="sub_none">
			<html:select name="pForm" property="select_list1" styleId="select_list1" styleClass="tf_none">
				<html:options collection="detailSearch" property="id" labelProperty ="text"/>
			</html:select>
		</span>
		<span class="sub_blit">세부검색항목</span> 
		<span class="sub_none">
			<html:text name="pForm" property="input_val1" styleId="input_val1" styleClass="tf_none" />
		</span>			
	</logic:notEmpty>
	<logic:notEmpty name="sortOption">
		<span class="sub_blit">정렬기준</span> 
		<span class="sub_none">
			<html:select name="pForm" property="svc_sort" styleId="svc_sort" styleClass="tf_none">
				<html:options collection="sortOption" property="id" labelProperty ="text"/>
			</html:select>
		</span>
		<span class="sub_blit">정렬기준</span> 
		<span class="sub_none">		
			<html:radio name="pForm" property="sort_dev" styleId="sort_dev" value="1" />DESC
			<html:radio name="pForm" property="sort_dev" styleId="sort_dev" value="2" />ASC
		</span>
	</logic:notEmpty>			
	<logic:notEmpty name="urlShow">
		<span class="sub_blit">URL 검색</span> 
		<span class="sub_none">
			<html:text name="pForm" property="url_val" styleId="url_val" styleClass="tf_none" />
		</span>			
	</logic:notEmpty>
	
	<logic:notEmpty name="minShow">
		
	</logic:notEmpty>
	
	<logic:notEmpty name="minShow">
		
	</logic:notEmpty>
		
	<logic:notEmpty name="minShow">
		<span class="sub_blit">사용자번호</span> 
		<span class="sub_none"> 
			<html:text name="pForm" property="min_val" styleId="min_val" styleClass="tf_none"/>
		</span>
		<span class="sub_blit">CliendIP</span> 
		<span class="sub_none"> 
			<html:text name="pForm" property="min_val" styleId="client_val" styleClass="tf_none"/>
		</span>
		<span class="sub_blit">ServerIP</span> 
		<span class="sub_none"> 
			<html:text name="pForm" property="min_val" styleId="server_val" styleClass="tf_none"/>
		</span>
	</logic:notEmpty>	
	<logic:empty name="searchCount">
	<span id="spanCount">
		<span class="sub_blit">검색건수</span> 
		<span class="sub_none">
			<html:text name="pForm" property="srh_cnt" styleId="srh_cnt" styleClass="tf_none" />
		</span>
	</span>		
	</logic:empty>	 
	<html:hidden name="pForm" property="plf_dev" styleId="plf_dev" />
</span>
</span>
	