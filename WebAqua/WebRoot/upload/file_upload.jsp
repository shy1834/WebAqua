<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!-- ���� ���ε� ó���� ���� MultipartRequest ��ü�� ����Ʈ -->
<%@ page import="com.oreilly.servlet.MultipartRequest" %> 
<!-- ���� �ߺ�ó�� ��ü ����Ʈ -->
<%@ page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy" %> 
<%@ page import="java.util.*" %>
<%@ page import="java.io.File,java.io.FileReader,java.io.FileWriter,java.io.IOException" %>
<%@ page import="au.com.bytecode.opencsv.CSVReader,java.sql.*" %>
<%@ page import="com.ktf.aqua.db.DBDataSource"%>
<%
	String uploadPath = "C:\\";
	int size = 10*1024*1024; 	//���ε� ���� �ִ� ũ�� ����
	
	String name="";
	String subject="";
	String filename="";
	
	int suc_cnt = 0;
	int err_cnt = 0;
	
	try{
	//���� ���ε�. ������ ������ ���ڰ��� ��� ����request ��ü ����, 
	//���ε� ���, ���� �ִ� ũ��, �ѱ�ó��, ���� �ߺ�ó��
	MultipartRequest multi = new MultipartRequest(request, uploadPath, size, "euc-kr", new DefaultFileRenamePolicy());
	
	//������ �Է��� ���� ������
	name = multi.getParameter("name");
	subject = multi.getParameter("subject");
	
	//���ε��� ���ϵ��� Enumeration Ÿ������ ��ȯ
	//Enumeration���� �����͸� �̾ƿö� ������ �������̽� Enumeration��ü�� java.util ��Ű���� ���� �Ǿ������Ƿ�
	//java.util.Enumeration�� import ���Ѿ� �Ѵ�.
	Enumeration files = multi.getFileNames();
	
	//���ε��� ���ϵ��� �̸��� ����
	String file = (String)files.nextElement();
	filename = multi.getFilesystemName(file);
	
	//DB�Է�ó��
	CSVReader reader = new CSVReader(new FileReader(uploadPath+filename));     
	String [] nextLine;
	
	Connection con = DBDataSource.getCon(0);
	String query = "insert into WIFI_DATA_TBL values ( TO_DATE(sysdate) , ? , ? , ? , ? , ? , ? , ? )";
	int q =0;
	while((nextLine = reader.readNext()) != null) {
		
		//System.out.println(q++);
		//ipüũ
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
		//����ó��
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
	alert("���ε尡 �Ϸ�Ǿ����ϴ�.\n--------------------------\n����:<%=suc_cnt%>��\n����:<%=err_cnt%>\n--------------------------\nIP������ ���°�� �Էµ��� �ʽ��ϴ�.");
	parent.init();
	location.href = "./file_form.jsp";
//-->
</script>
</body>
</html>