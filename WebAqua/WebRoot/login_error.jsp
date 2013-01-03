<%@ page contentType="text/html; charset=EUC-KR" %>
<script type="text/JavaScript">
	location.href = 'login.do?cmd=login';
	var pop = window.open("http://cass.magicn.comcass/registPopCp.do","","toolbar=0, status=0, scrollbars=yes, location=0, menubar=0, width=775, height=660");
		
	alert("본 시스템은 KTF 직원 및 CASS에 등록된 ID만 사용이 가능합니다.");
	pop.focus();	
</script>
