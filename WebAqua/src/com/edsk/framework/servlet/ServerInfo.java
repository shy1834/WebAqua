/*
 * Created on 2006. 4. 11.
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.edsk.framework.servlet;

/**
 * @author Administrator
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
import java.io.*; 
import java.util.*; 
import javax.servlet.*; 

public class ServerInfo extends GenericServlet { 
	
  public void service (ServletRequest req, ServletResponse res) throws ServletException, IOException { 
    res.setContentType("text/plain;charset=euc-kr"); 
    PrintWriter out = res.getWriter(); 
    //�����̸� ��� 
    out.println("�����̸� ---> " + req.getServerName()); 
    //������Ʈ 
    out.println("������Ʈ ---> " + req.getServerPort()); 
    //Protocol 
    out.println("�������� ---> " + req.getProtocol()); 
   //Client IP 
    out.println("Client IP ---> " + req.getRemoteAddr()); 
   //Client HostName 
    out.println("Client HostName ---> " + req.getRemoteHost()); 
   //Document Root(Servlet API2.1���� Deprecated��) 
    //out.println("Document Root(req.getRealPath) ---> " + req.getRealPath("/")); 
    out.println("Document Root(getServletContext().getRealPath) ---> " +  getServletContext().getRealPath("/"));      
   //�������� Document Root 
   out.println("���� ��Ʈ ���丮 ---> " + System.getProperty("server.root")); 
  } 
} 

