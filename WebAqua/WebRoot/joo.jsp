<%@ page contentType="text/html;charset=euc-kr"
import="java.util.*, javax.mail.*, javax.mail.internet.*"
%> 
<%! // �Լ� ������ ���� ! �ٿ��ݴϴ�.
public String kr(String s) {
try {
s = (s == null) ? "" : new String(s.getBytes("8859_1"),"KSC5601");
} catch (java.io.UnsupportedEncodingException uee) {}
return s;
}
%>
<html><head><title>�������۰��</title>

<%
// ����ڰ� �Է��� ���� ���� �ڷḦ ����
String mailFrom = null;
String mailTo = null;
String title = null;
String contents = null;
String htmltag = null;

// Resin �� ��� kr�� ���ϴ�. �ѱۺ�ȯ�� ���� �ʽ��ϴ�.
// ��Ĺ�� ��� �ҽ� �״�� ����մϴ�. �ѱۺ�ȯ �ʿ��մϴ�.
mailFrom = kr(request.getParameter("from"));
mailTo = kr(request.getParameter("to"));
title = kr(request.getParameter("title"));
contents = kr(request.getParameter("content"));

htmltag = "<font color=BLUE size=2>";

contents = htmltag + contents;

// Session�� �����ϱ� ���� java.util.Properties Ŭ������
// �����ϰ� �ڽ��� �ش��ϴ� SMTP ȣ��Ʈ �ּҸ� �Ҵ��մϴ�.
Properties props = new Properties();
props.put("mail.smtp.host", "220.73.145.133");

// �⺻ Session�� �����ϰ� �Ҵ��մϴ�.
Session msgSession = Session.getDefaultInstance(props, null);
%>
</head>
<body bgcolor="#D0E0FF">
<center>
<%
try {

// Message Ŭ������ ��ü�� Session�� �̿��� �����մϴ�.
MimeMessage msg = new MimeMessage(msgSession);
InternetAddress from = new InternetAddress(mailFrom);
msg.setFrom(from);

InternetAddress to = new InternetAddress(mailTo);
msg.setRecipient(Message.RecipientType.TO, to);

msg.setSubject(title);

msg.setContent(contents, "text/html; charset=EUC-KR");

Transport.send(msg);

%>
�����մϴ�. ��û�Ͻ� ���� ������ �Ϸ�Ǿ����ϴ�.<br>
���� �Ϸ� �Ǽ���.<br>
<a href="http://jspstudy.zoa.to">to jspstudy.zoa.to</a>
<%
}
catch (MessagingException e) {
e.printStackTrace();
%>
<center>�˼��մϴ�. ���� ������ �����Ͽ����ϴ�.<br>
�����ڿ��� �����ϼ���.<br>
<a href="#" onClick="history.back()">���ư���</a>
<% } %>
</center></body></html>
