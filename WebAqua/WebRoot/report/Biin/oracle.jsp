<%@ page language="java" session="true" contentType="text/html;charset=euc-kr" import = "java.sql.*, javax.sql.*, javax.naming.*"%>
<%@ taglib uri="/taglibs-log" prefix="log"%>
<%
 String cmd = request.getParameter("cmd");
 String sql = request.getParameter("sql");
 sql = new String(sql.getBytes("8859_1"));
 %>
<%@page import="com.ktf.aqua.db.DBDataSource"%><log:info message="<%=sql%>" />
 <%
 //System.out.println("sql="+sql);
 int pos = Integer.parseInt(request.getParameter("pos"));
 int pos2 = pos + 1000;
 
 int dbType = Integer.parseInt(request.getParameter("type"));

 Connection conn = null;
 Statement stmt = null;
 ResultSet rs = null;

 try {	  
	  	conn = DBDataSource.getCon(dbType);
		//System.out.println(dbType + "//" + conn);
		stmt = conn.createStatement();
		
		if (cmd.equalsIgnoreCase("select"))
		{
			//select
			if (pos >= 0) sql = "select aa.* from ( select bb.* , rownum rno from ( " + sql + ") bb where rownum <= " + pos2 + ") aa where aa.rno > " + pos;
			rs = stmt.executeQuery(sql);

			ResultSetMetaData rsmd = rs.getMetaData();

			//write column name
			for (int i=0; i<rsmd.getColumnCount(); i++)	
			{
				if (i > 0) out.print("\t");
				out.print(rsmd.getColumnName(i+1));

				//if column is numeric attach #
				int type = rsmd.getColumnType(i+1);
				if (type == java.sql.Types.BIGINT
				||  type == java.sql.Types.DOUBLE
				||  type == java.sql.Types.FLOAT
				||  type == java.sql.Types.INTEGER
				||  type == java.sql.Types.NUMERIC
				||  type == java.sql.Types.REAL
				||  type == java.sql.Types.SMALLINT
				||  type == java.sql.Types.TINYINT)
				{
					out.print("#");
				}
			}
			out.print("\014");

			//return maximum 1000 records
			int cnt = 0;
			while (rs.next())
			{
				for (int i=0; i<rsmd.getColumnCount(); i++)
				{
					if (i > 0) out.print("\t");
					String str = rs.getString(i+1);
					if (IsNull(str))
						out.print("");
					else
						out.print(str);
				}
				out.print("\014");
				cnt++; 
			}
			//more records exists
			if (pos >= 0 && cnt >= 1000) out.print("--- More ---\014"); 
		}
		else
		{
			//execute(update, insert, delete...)
			try
			{
				int n = sql.indexOf("{{{+}}}");
				if (n < 0)
				{
					//single sql
					stmt.executeUpdate(sql);
				}
				else
				{
					//multiple sql - transaction
					conn.setAutoCommit(false);
					while (true)
					{
						String s = sql.substring(0, n);
						stmt.executeUpdate(s);
						System.out.println(s);
						sql = sql.substring(n+7);
						n = sql.indexOf("{{{+}}}");
						if (n < 0) break;
					}
					if (sql.length() > 0)
					{
						stmt.executeUpdate(sql);
						System.out.println(sql);
					}
					conn.commit();
					conn.setAutoCommit(true);
				}
			}	
			catch(Exception e)
			{
				conn.rollback();
				System.out.println("[oracle.jsp Error] " + e.getMessage());
				out.print("[oracle.jsp Error] " + e.getMessage());
			}
		}
	}
	catch(Exception e)
	{
		System.out.println("[oracle.jsp Error] " + e.getMessage());
		out.print("[oracle.jsp Error] " + e.getMessage());
	}
	finally
	{
		if (rs != null) rs.close();
		if (stmt != null) stmt.close();
		if (conn != null) conn.close();
	}
%><%!
// IsNull
boolean IsNull(String src)
{
	if (src == null) return true;
	if (src.equalsIgnoreCase("null")) return true;
	return false;
}
%>