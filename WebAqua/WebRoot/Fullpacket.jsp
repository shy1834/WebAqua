<%@ page contentType="text/html; charset=EUC-KR" %>
<%@ page import="java.util.*, java.sql.*, javax.sql.*, javax.naming.*" %>
<%@ page import="javax.swing.JOptionPane, com.edsk.framework.util.StringUtil" %>
<%@ page import="javax.mail.*, javax.mail.internet.*, javax.activation.DataHandler" %>
<%
String id1=request.getParameter("id");
String ip1=request.getParameter("ip");
String file1=request.getParameter("file");
if(ip1 == null){
	  ip1 = "";
	}

int result = 0;

 Context initContext = null;
 Context envContext = null;
 DataSource ds = null;
 
 Connection conn = null;
 java.sql.Statement stmt = null;
 ResultSet rs = null;
 String sql = "";
 String userid = "";
 String usernm = "";
 String comp = "";
 String e_mail1 = "";
 String e_mail2 = "";
 String e_mail3 = "";
 String mailFrom = "";
 String mailTo1 = "";
 String mailTo2 = "";
 String title = "";
 String contents = "";
 
 try {	 
  initContext = new InitialContext();
  envContext = (Context) initContext.lookup("java:comp/env");
  ds = (DataSource) envContext.lookup("jdbc/AQUADataSource");
  conn = ds.getConnection();
  
  sql = "SELECT	USER_ID, USER_NAME, COMPANY,";
  sql += "EMAIL1, EMAIL2 ,EMAIL3 FROM AQUA2_USER_info_tbl where USER_ID = '"+id1+"' ";

  stmt = conn.createStatement(); 
  rs = stmt.executeQuery(sql);
  while(rs.next())
  {
  			userid = rs.getString("USER_ID");
  			usernm = rs.getString("USER_NAME");
  			comp = rs.getString("COMPANY");
  			e_mail1 = rs.getString("EMAIL1");
  			e_mail2 = rs.getString("EMAIL2");
  			e_mail3 = rs.getString("EMAIL3");
  }
  if(e_mail1 == null){
  	e_mail1 = "";
  }
  if(e_mail2 == null){
  	e_mail2 = "";  	
  }
  if(e_mail3 == null){
  	e_mail3 = "";  	
  }
  sql = "INSERT INTO AQUA3_FULL_PACKET_REQ_TBL";
  sql += "(DATE_FLD, USER_ID, USER_NAME, COMPANY, CLIENT_IP, EMAIL1, EMAIL2, FILE_NM, EMAIL3)";
  sql += "VALUES (SYSDATE,'"+userid+"','"+usernm+"','"+comp+"','"+ip1+"','"+e_mail1+"','"+e_mail2+"','"+file1+"','"+e_mail3+"')";
  
  stmt = conn.createStatement();
  result = stmt.executeUpdate(sql);
  
  rs.close();
  stmt.close();
  
  } catch( Exception e ) {
  out.println(e);
 } finally {
  try { if(stmt != null) stmt.close(); } catch (Exception e) {}
  try { if(conn != null) conn.close(); } catch (Exception e) {}
 }

mailFrom = "aqua@show.co.kr";


title = "[AQUA] "+usernm+"���� �� �������ͳ� ������� ���� ����";
contents =
	"<center>"
	+"<table align=\"center\"  style=\"font-size:9pt;\">"
	+"<tr>"
	+"<td style=\"font-size:12pt;\"><font color=\"blue\"><�˸�><�˸�></font></td>"
	+"</tr>"
	+"<tr>"
	+"<td>=====================================================================================</td>"
	+"</tr>"
	+"<tr>"
	+"<td>&nbsp;&nbsp;�� �������ͳ� ��� ��Ŷ ���� ���� �����в����� ���� ���� �� ���� ���������� �߰��Ͽ�</td>"
	+"</tr>"
	+"<tr>"
	+"<td><font color=\"#0000FF\">KTF����</font>�� ��� �Ҽ� ����� �� AQUA �����(��μ� ����)���� ������ �߼��Ͽ� �ֽñ� �ٶ��,</td>"
	+"</tr>"
	+"<tr>"
	+"<td><font color=\"#0000FF\">���»�����</font>�� ��� ������� KTF ���� �� AQUA �����(��μ� ����)���� ������ �߼��Ͽ�</td>"
	+"</tr>"
	+"<tr>"
	+"<td>�ֽñ� �ٶ��ϴ�.</td>"
	+"</tr>"
	+"<tr>"
	+"<td><font color=\"#FF0000\">&nbsp;&nbsp;--24�ð� �̳� ������ ���� ���� ��� �ش� ������ ���� ��ġ�ϰ� ���� ������ ����� ����.</td>"
	+"</tr>"
	+"<tr>"
	+"<td>=====================================================================================</td>"
	+"</tr>"
	+"</table>"
	+"</center>"
	+"<table align=\"center\" width=\"400\" border=\"0\" cellspacing=\"1\" cellpadding=\"2\" style=\"font-size:9pt;\" bgcolor=\"#2F302F\">" 
	+ "<tr>"
	+ "<td align=\"center\" width=\"100\" bgcolor=\"#E5F6ED\" height=\"25\">�� ��Ŷ ���� �����ڸ�</td>" 
	+ "<td width=\"300\" bgcolor=\"#FFFFFF\" height=\"25\">&nbsp;&nbsp;" + usernm + "</td>"
	+ "</tr>" 
	+ "<tr>" + "<td align=\"center\" width=\"100\" bgcolor=\"#E5F6ED\" height=\"25\">AQUA ID</td>" 
	+ "<td width=\"300\" bgcolor=\"#FFFFFF\" height=\"25\">&nbsp;&nbsp;" + userid + "</td>" 
	+ "</tr>" 
	+ "<tr>" 
	+ "<td align=\"center\" width=\"100\" bgcolor=\"#E5F6ED\" height=\"25\">������ IP</td>"
	+ "<td width=\"300\" bgcolor=\"#FFFFFF\" height=\"25\">&nbsp;&nbsp;" + ip1 + "</td>"
	+ "</tr>" 
	+ "<tr>"
	+ "<td align=\"center\" width=\"100\" bgcolor=\"#E5F6ED\" height=\"25\">�ٿ�ε� ���ϸ�</td>" + "<td width=\"300\" bgcolor=\"#FFFFFF\" height=\"25\">&nbsp;&nbsp;" + file1 + "</td>"
	+ "</tr>" 
	+ "</table>";


// Session�� �����ϱ� ���� java.util.Properties Ŭ������
// �����ϰ� �ڽ��� �ش��ϴ� SMTP ȣ��Ʈ �ּҸ� �Ҵ��մϴ�.
Properties props = new Properties();
//props.put("mail.smtp.host", "220.73.145.133");
props.put("mail.smtp.host", "128.134.98.49");

// �⺻ Session�� �����ϰ� �Ҵ��մϴ�.
Session msgSession = Session.getDefaultInstance(props, null);

try {

// Message Ŭ������ ��ü�� Session�� �̿��� �����մϴ�.
MimeMessage msg = new MimeMessage(msgSession);
InternetAddress from = new InternetAddress(mailFrom);
msg.setFrom(from);

InternetAddress to = new InternetAddress(e_mail1);
msg.setRecipient(Message.RecipientType.TO, to);

msg.setSubject(title);

msg.setContent(contents, "text/html; charset=EUC-KR");

Transport.send(msg);

}catch (MessagingException e) {
e.printStackTrace();
}

try {

// Message Ŭ������ ��ü�� Session�� �̿��� �����մϴ�.
MimeMessage msg = new MimeMessage(msgSession);
InternetAddress from = new InternetAddress(mailFrom);
msg.setFrom(from);

InternetAddress to = new InternetAddress(e_mail2);
msg.setRecipient(Message.RecipientType.TO, to);

msg.setSubject(title);

msg.setContent(contents, "text/html; charset=EUC-KR");

Transport.send(msg);

}catch (MessagingException e) {
e.printStackTrace();
}

try {

// Message Ŭ������ ��ü�� Session�� �̿��� �����մϴ�.
MimeMessage msg = new MimeMessage(msgSession);
InternetAddress from = new InternetAddress(mailFrom);
msg.setFrom(from);

InternetAddress to = new InternetAddress(e_mail3);
msg.setRecipient(Message.RecipientType.TO, to);

msg.setSubject(title);

msg.setContent(contents, "text/html; charset=EUC-KR");

Transport.send(msg);

}catch (MessagingException e) {
e.printStackTrace();
}%>

<script language = JavaScript>
 	function packet(result, mailFrom) {
	 	if(result == 1 && mailFrom != null){
	  	if (/MSIE/.test(navigator.userAgent)) { 
		    if(navigator.appVersion.indexOf("MSIE 7.0")>=0) {
		        window.open('about:blank','_self').close();
		    } else { 
		       window.opener = self; 
		       self.close(); 
		    }                       
			}
	  }
	}
 	</script>
 <body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" onload="packet(<%=result%>,'<%=mailFrom%>')">
 </body>