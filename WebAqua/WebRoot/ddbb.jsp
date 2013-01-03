<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
<script src="/js/jquery-1.4.4.min.js"></script>
<script type="text/javascript">
<!--
function sel_menu3(){
	$.ajax({
		type:"POST",
		url: "/JDBCTest.jsp",		
		success:function(html){
			setTimeout("sel_menu3()",40000);			
		}
	});
}	

function init(){	 
	sel_menu3();
}
	
//-->
</script>
</head>
<body onload="init()">

</body>
</html>