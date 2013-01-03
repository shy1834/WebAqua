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


title = "[AQUA] "+usernm+"님의 고객 무선인터넷 사용정보 열람 내역";
contents =
	"<center>"
	+"<table align=\"center\"  style=\"font-size:9pt;\">"
	+"<tr>"
	+"<td style=\"font-size:12pt;\"><font color=\"blue\"><알림><알림></font></td>"
	+"</tr>"
	+"<tr>"
	+"<td>=====================================================================================</td>"
	+"</tr>"
	+"<tr>"
	+"<td>&nbsp;&nbsp;고객 무선인터넷 사용 패킷 정보 열람 직원분께서는 메일 수신 후 정보 열람사유를 추가하여</td>"
	+"</tr>"
	+"<tr>"
	+"<td><font color=\"#0000FF\">KTF직원</font>의 경우 소속 팀장님 및 AQUA 담당자(김민수 과장)에게 메일을 발송하여 주시기 바라며,</td>"
	+"</tr>"
	+"<tr>"
	+"<td><font color=\"#0000FF\">협력사직원</font>인 경우 업무담당 KTF 직원 및 AQUA 담당자(김민수 과장)에게 메일을 발송하여</td>"
	+"</tr>"
	+"<tr>"
	+"<td>주시기 바랍니다.</td>"
	+"</tr>"
	+"<tr>"
	+"<td><font color=\"#FF0000\">&nbsp;&nbsp;--24시간 이내 메일이 오지 않을 경우 해당 계정은 삭제 조치하고 무단 데이터 유출로 간주.</td>"
	+"</tr>"
	+"<tr>"
	+"<td>=====================================================================================</td>"
	+"</tr>"
	+"</table>"
	+"</center>"
	+"<table align=\"center\" width=\"400\" border=\"0\" cellspacing=\"1\" cellpadding=\"2\" style=\"font-size:9pt;\" bgcolor=\"#2F302F\">" 
	+ "<tr>"
	+ "<td align=\"center\" width=\"100\" bgcolor=\"#E5F6ED\" height=\"25\">고객 패킷 정보 열람자명</td>" 
	+ "<td width=\"300\" bgcolor=\"#FFFFFF\" height=\"25\">&nbsp;&nbsp;" + usernm + "</td>"
	+ "</tr>" 
	+ "<tr>" + "<td align=\"center\" width=\"100\" bgcolor=\"#E5F6ED\" height=\"25\">AQUA ID</td>" 
	+ "<td width=\"300\" bgcolor=\"#FFFFFF\" height=\"25\">&nbsp;&nbsp;" + userid + "</td>" 
	+ "</tr>" 
	+ "<tr>" 
	+ "<td align=\"center\" width=\"100\" bgcolor=\"#E5F6ED\" height=\"25\">열람자 IP</td>"
	+ "<td width=\"300\" bgcolor=\"#FFFFFF\" height=\"25\">&nbsp;&nbsp;" + ip1 + "</td>"
	+ "</tr>" 
	+ "<tr>"
	+ "<td align=\"center\" width=\"100\" bgcolor=\"#E5F6ED\" height=\"25\">다운로드 파일명</td>" + "<td width=\"300\" bgcolor=\"#FFFFFF\" height=\"25\">&nbsp;&nbsp;" + file1 + "</td>"
	+ "</tr>" 
	+ "</table>";


// Session을 생성하기 위해 java.util.Properties 클래스를
// 생성하고 자신이 해당하는 SMTP 호스트 주소를 할당합니다.
Properties props = new Properties();
//props.put("mail.smtp.host", "220.73.145.133");
props.put("mail.smtp.host", "128.134.98.49");

// 기본 Session을 생성하고 할당합니다.
Session msgSession = Session.getDefaultInstance(props, null);

try {

// Message 클래스의 객체를 Session을 이용해 생성합니다.
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

// Message 클래스의 객체를 Session을 이용해 생성합니다.
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

// Message 클래스의 객체를 Session을 이용해 생성합니다.
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