<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
<script type="text/javascript">

	function file_check(){
		
		var file_name = fileForm.filename.value;
		if(file_name == ""){
			alert("파일을 선택하십시오");
			return false;
		}
		
		var file_type = file_name.substring(file_name.length-3,file_name.length); 
		if(file_type != "csv" && file_type != "CSV"){
			alert("csv파일만 업로드 가능합니다.");
			return false;
		}
		
		return true;
	}
</script>
</head>
<body>
<form name="fileForm" method="post" enctype="multipart/form-data" action="./file_upload.jsp" onsubmit="return file_check()">
<table width="550">
	<tr>
		<td width="50" style="font-size: 9pt" align="center">파일 : </td>
		<td>
			<input type="file" name="filename" style="width:400px">
		</td>
		<td align="left">
			<input type="submit" value="파일올리기"><br>
		</td>
	</tr>
</table> 
<hr>
</form> 
</body>
</html>