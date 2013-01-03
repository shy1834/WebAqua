<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!-- 파일 업로드 처리를 위한 MultipartRequest 객체를 임포트 -->
<%@ page import="com.oreilly.servlet.MultipartRequest" %> 
<!-- 파일 중복처리 객체 임포트 -->
<%@ page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy" %> 
<%@ page import="java.util.*" %>
<%@ page import="java.io.File,java.io.FileReader,java.io.FileWriter,java.io.IOException" %>
<%@ page import="au.com.bytecode.opencsv.CSVReader,java.sql.*" %>
<%@ page import="com.ktf.aqua.db.DBDataSource"%>
<%
	String uploadPath = "C:\\";
	int size = 10*1024*1024; 	//업로드 파일 최대 크기 지정
	
	String name="";
	String subject="";
	String filename="";
	
	int suc_cnt = 0;
	int err_cnt = 0;
	
	try{
	//파일 업로드. 폼에서 가져온 인자값을 얻기 위해request 객체 전달, 
	//업로드 경로, 파일 최대 크기, 한글처리, 파일 중복처리
	MultipartRequest multi = new MultipartRequest(request, uploadPath, size, "euc-kr", new DefaultFileRenamePolicy());
	
	//폼에서 입력한 값을 가져옴
	name = multi.getParameter("name");
	subject = multi.getParameter("subject");
	
	//업로드한 파일들을 Enumeration 타입으로 반환
	//Enumeration형은 데이터를 뽑아올때 유용한 인터페이스 Enumeration객체는 java.util 팩키지에 정의 되어있으므로
	//java.util.Enumeration을 import 시켜야 한다.
	Enumeration files = multi.getFileNames();
	
	//업로드한 파일들의 이름을 얻어옴
	String file = (String)files.nextElement();
	filename = multi.getFilesystemName(file);
	
	//DB입력처리
	CSVReader reader = new CSVReader(new FileReader(uploadPath+filename));     
	String [] nextLine;
	
	Connection con = DBDataSource.getCon(0);
	String query = "insert into WIFI_DATA_TBL values ( TO_DATE(sysdate) , ? , ? , ? , ? , ? , ? , ? )";
	int q =0;
	while((nextLine = reader.readNext()) != null) {
		
		//System.out.println(q++);
		//ip체크
		if(nextLine[0].length() != 0 && "123456789".indexOf(nextLine[0].charAt(0)) > -1 ){
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt = con.prepareStatement(query);
			
			//nextLine[] is an array of values from the line	
			for(int i=0;i<nextLine.length;i++){			
				nextLine[i] = nextLine[i].replace(",","");
				pstmt.setString(i+1, nextLine[i]);
			}
			
			pstmt.execute();
			pstmt.close();
			suc_cnt ++;
		}else{
			err_cnt ++;
		}
	}
		con.close();
	}catch(Exception e){
		//예외처리
		e.printStackTrace();
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
<script type="text/javascript">
<!--
	alert("업로드가 완료되었습니다.\n--------------------------\n성공:<%=suc_cnt%>건\n실패:<%=err_cnt%>\n--------------------------\nIP정보가 없는경우 입력되지 않습니다.");
	parent.init();
	location.href = "./file_form.jsp";
//-->
</script>
</body>
</html>