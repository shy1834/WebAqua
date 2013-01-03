<%@ page contentType="text/html;charset=euc-kr"
import="java.util.*, javax.mail.*, javax.mail.internet.*"
%> 
<%! // 함수 선언할 때는 ! 붙여줍니다.
public String kr(String s) {
try {
s = (s == null) ? "" : new String(s.getBytes("8859_1"),"KSC5601");
} catch (java.io.UnsupportedEncodingException uee) {}
return s;
}
%>
<html><head><title>메일전송결과</title>

<%
// 사용자가 입력한 메일 전송 자료를 저장
String mailFrom = null;
String mailTo = null;
String title = null;
String contents = null;
String htmltag = null;

// Resin 일 경우 kr을 뺍니다. 한글변환을 하지 않습니다.
// 톰캣일 경우 소스 그대로 사용합니다. 한글변환 필요합니다.
mailFrom = kr(request.getParameter("from"));
mailTo = kr(request.getParameter("to"));
title = kr(request.getParameter("title"));
contents = kr(request.getParameter("content"));

htmltag = "<font color=BLUE size=2>";

contents = htmltag + contents;

// Session을 생성하기 위해 java.util.Properties 클래스를
// 생성하고 자신이 해당하는 SMTP 호스트 주소를 할당합니다.
Properties props = new Properties();
props.put("mail.smtp.host", "220.73.145.133");

// 기본 Session을 생성하고 할당합니다.
Session msgSession = Session.getDefaultInstance(props, null);
%>
</head>
<body bgcolor="#D0E0FF">
<center>
<%
try {

// Message 클래스의 객체를 Session을 이용해 생성합니다.
MimeMessage msg = new MimeMessage(msgSession);
InternetAddress from = new InternetAddress(mailFrom);
msg.setFrom(from);

InternetAddress to = new InternetAddress(mailTo);
msg.setRecipient(Message.RecipientType.TO, to);

msg.setSubject(title);

msg.setContent(contents, "text/html; charset=EUC-KR");

Transport.send(msg);

%>
축하합니다. 요청하신 메일 전송이 완료되었습니다.<br>
좋은 하루 되세요.<br>
<a href="http://jspstudy.zoa.to">to jspstudy.zoa.to</a>
<%
}
catch (MessagingException e) {
e.printStackTrace();
%>
<center>죄송합니다. 메일 전송이 실패하였습니다.<br>
관리자에게 문의하세요.<br>
<a href="#" onClick="history.back()">돌아가기</a>
<% } %>
</center></body></html>
