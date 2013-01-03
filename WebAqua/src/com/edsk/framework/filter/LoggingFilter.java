package com.edsk.framework.filter;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.edsk.framework.util.StringUtil;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * LoggingFilter
 * LoggingøÎ « ≈Õ<br>
 * 
 * @author mksong
 */
public class LoggingFilter implements Filter {
    private static Log log = LogFactory.getLog(LoggingFilter.class);
    String prefix="\n>A new request begin<";
    String suffix="\n";
    
    FilterConfig config=null;
    String[] sessionParams=null;
    String[] headerParams=null;
    boolean uri=true;
    boolean method=false;
    boolean secure=false;
    boolean remoteUser=false;
    boolean remoteAddr=false;
    boolean remoteHost=false;
    boolean protocol=false;
    boolean parameters=false;
    boolean sessions=false;
    
    boolean logging=true;

    public void init(FilterConfig config) throws ServletException {
        setFilterConfig(config);
    }

    public FilterConfig getFilterConfig() {
        return config;
    }


    public void setFilterConfig(FilterConfig config) {
        this.config=config;
        uri=!"false".equals(config.getInitParameter("uri"));
        method="true".equals(config.getInitParameter("method"));
        secure="true".equals(config.getInitParameter("secure"));
        remoteUser="true".equals(config.getInitParameter("remoteUser"));
        remoteAddr="true".equals(config.getInitParameter("remoteAddr"));
        remoteHost="true".equals(config.getInitParameter("remoteHost"));
        protocol="true".equals(config.getInitParameter("protocol"));
        parameters="true".equals(config.getInitParameter("parameters"));
        sessions="true".equals(config.getInitParameter("sessions"));
        sessionParams=StringUtil.toMultiString(config.getInitParameter("sessionParams"),',');
        headerParams=StringUtil.toMultiString(config.getInitParameter("headerParams"),',');
        String param=config.getInitParameter("prefix");
        if (param!=null) {
            prefix=param;
        }
        param=config.getInitParameter("suffix");
        if (param!=null) {
            suffix=param;
        }
        logging=uri || method || secure|| remoteUser || remoteAddr || remoteHost || 
                protocol || parameters || sessions || (sessionParams!=null) || (headerParams!=null);
    }
    
    public void doFilter(ServletRequest request, ServletResponse response, 
            FilterChain chain) throws IOException, ServletException {
        if (logging) {
            log.info(getLogMessage((HttpServletRequest)request)); 
        }       
        chain.doFilter(request,response); 
    }

    protected String getLogMessage(HttpServletRequest request) {
        StringBuffer sb=new StringBuffer(prefix);
        if (uri) {
            sb.append("\nRequestURI="+request.getRequestURI());
        }
        if (method) {
            sb.append("\nmethod="+request.getMethod());
        }
        if (secure) {
            sb.append("\nsecure="+request.isSecure());
        }
        if (remoteUser) {
            sb.append("\nremoteUser="+request.getRemoteUser());
        }
        if (remoteAddr) {
            sb.append("\nremoteAddr="+request.getRemoteAddr());
        }                
        
        if (remoteHost) {
            sb.append("\nremoteHost="+request.getRemoteHost());
        }
        if (protocol) {
            sb.append("\nprotocol="+request.getProtocol());
            
        }
        if (parameters && !isMultipart(request)) {
            appendParameters(sb,request);
        }
        if (sessions) {
            appendSession(sb,request.getSession());
        }
        if (sessionParams!=null) {
            appendSessions(sb,request.getSession(),sessionParams);
        }
        if (headerParams!=null) {
            appendHeaders(sb,request,headerParams);
        }
        sb.append(suffix);
        return sb.toString();
    }

    /**
     * @param sb
     * @param session
     */
    protected void appendSession(StringBuffer sb, HttpSession session) {
        java.util.Enumeration names=session.getAttributeNames();
        while (names.hasMoreElements()) {
            String sessionName=(String)names.nextElement();
            sb.append("\n - "+sessionName+"="+session.getAttribute(sessionName));
        }
    }

    /**
     * @param request
     * @return
     */
    protected boolean isMultipart(HttpServletRequest request) {
        String type = null;
        String type1 = request.getHeader("Content-Type");
        String type2 = request.getContentType();
        if (type1 == null && type2 != null) {
            type = type2;
        } else if (type2 == null && type1 != null) {
            type = type1;
        } else if (type1 != null && type2 != null) {
            type = (type1.length() > type2.length() ? type1 : type2);
        }

        return (type != null && type.toLowerCase().startsWith("multipart/form-data"));
    }

    /**
     * @param sb
     * @param request
     * @param headerParams
     */
    protected void appendHeaders(StringBuffer sb, HttpServletRequest request, String[] headerParams) {
        for (int i=0;i<headerParams.length;i++) {
            sb.append("\n - "+headerParams[i]+"="+request.getHeader(headerParams[i]));            
        }
    }

    /**
     * @param sb
     * @param session
     * @param sessionParams
     */
    protected void appendSessions(StringBuffer sb, HttpSession session, String[] sessionParams) {
        for (int i=0;i<sessionParams.length;i++) {
            sb.append("\n - "+sessionParams[i]+"="+session.getAttribute(sessionParams[i]));            
        }
    }

    /**
     * @param sb
     * @param request
     */
    protected void appendParameters(StringBuffer sb, HttpServletRequest request) {
        java.util.Enumeration parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String parameterName=(String)parameterNames.nextElement();
            String[] parameterValues=request.getParameterValues(parameterName);
            for (int i=0;i<parameterValues.length;i++) {
                sb.append("\n - "+parameterName+"="+parameterValues[i]);
            }
        }
    }
    
    public void destroy() {
    }

}
