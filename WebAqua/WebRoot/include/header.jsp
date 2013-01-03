<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<body>
	<table width="900" border="0" cellpadding="0" cellspacing="0">
		
		<!--상단로고라인 --------------------------------->
		<tr>
        	<td height="57" valign="top">
          		<table width="100%" border="0" cellspacing="0" cellpadding="0">
            		<tr>
              			<td width="50%"><a href="mainPage.do"><img src="images/main/logo.gif" border="0" ></a></td>
              			<td width="50%" align="right" valign="bottom" style="padding-right:10px;">
     						
                			<table border="0" cellspacing="0" cellpadding="0">
                  				<tr>
                    				<td style="padding-right:15px;"><span class="txt_name">${userName }</span>님께서 로그인 하셨습니다.</td>
                    				<td><a href="login.do?cmd=logout"><img src="images/main/bt_logout.gif" alt="Logout" border="0"></a></td>
                    				<td><a href="/userInfoView.do"><img src="images/main/bt_pinfo.gif" alt="개인정보" border="0"></a></td>
                    			</tr>
                  			</table>
                			
                		</td>
              		</tr>
            	</table>
          	</td>
      	</tr>
      	<!--상단로고라인 --------------------------------->
    	
    	<!--상단 메뉴 --------------------------------->  	
      	<tr>
        	<td class="menu_guide" align="center">
        		<table border="0" cellspacing="0" cellpadding="0">
          			<tr>
            			<c:forEach var="result" items="${mainMenu }" varStatus="resStat">
							<td width="15"></td>
							<td><a href="/menu.do?menuType=${result.id }&menuIndex=${resStat.index}" class="menu">${result.text }</a></td>
							<td width="80" align="center">	
								<c:if test="${resStat.count != fn:length(mainMenu)}">
									<img src="images/main/mn_bar.gif" width="25" height="20">
								</c:if> 
							</td>
						</c:forEach>
          			</tr>
        		</table>
        	</td>
      	</tr>
		<!--상단 메뉴 --------------------------------->
      	
    </table>
</body>    