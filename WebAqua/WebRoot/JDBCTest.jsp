
<%@page import="com.ktf.aqua.db.DBDataSource"%><%@ page import="java.sql.*, javax.sql.*, javax.naming.*" %>
<%
 Context initContext = null;
 Context envContext = null;
 DataSource ds = null;
 
 Connection conn = null;
 java.sql.Statement stmt = null;
 ResultSet rs = null;
 String sql = "";
 
 try {
  System.out.println("--------------------db con-----------------------");
  conn = DBDataSource.getCon(0);
  stmt = conn.createStatement(); 
  stmt.executeQuery("SELECT count(*) num FROM dual");   
  stmt.close();
  conn.close();
 
  
  conn = DBDataSource.getCon(1);
  stmt = conn.createStatement(); 
  stmt.executeQuery("SELECT count(*) num FROM dual");   
  stmt.close();
  conn.close();
  
  conn = DBDataSource.getCon(2);
  stmt = conn.createStatement(); 
  stmt.executeQuery("SELECT count(*) num FROM dual");   
  stmt.close();
  conn.close();
  
 } catch( Exception e ) {
  out.println(e);
 } finally {
  //try { if(rs != null) rs.close(); } catch (Exception e) {}
 }
%>
