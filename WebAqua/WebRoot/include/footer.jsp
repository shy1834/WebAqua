<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
	<!--footer ���� --------------------------------->
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
        	<td align="center" style="border-top:solid 1px #f2f2f2;">
        		<table width="1024" border="0" cellspacing="0" cellpadding="0">
          			<tr>
            			<td height="30" bgcolor="#cdcdcd" style="padding-left:25px; padding-top:2px;">
            				<table border="0" cellspacing="0" cellpadding="0">
              					<tr>
              						<%-- ������ ���Ѹ� ���ٰ��� --%>
									<c:if test="${cookie.userLevel.value == 0 || cookie.userLevel.value == 1}">              	              
	                					<td><a href="/adminPage.do?menu=0" class="Fmenu">�����ڸ��</a></td>
	                					<td width="15" align="center"></td>	                
                					</c:if>
                					
                					<td><%-- <a href="/noticeListView.do" class="Fmenu">��������</a> --%></td>
	                				<td width="15" align="center"></td>
	                				<td></td>
              					</tr>
            				</table>
            			</td>
          			</tr>
          		
          			<tr>
            			<td height="70" valign="top"><img src="images/main/footlogo.gif" ></td>
					</tr>
				</table>
        	</td>
		</tr>
	</table>
	<!--//footer ���� --------------------------------->
	