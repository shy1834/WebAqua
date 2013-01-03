package com.edsk.framework.filter;

import java.io.IOException;
import javax.servlet.*;

public class CharacterSetEncodingFilter implements Filter {

    public CharacterSetEncodingFilter() {
        encoding = null;
    }

    public void destroy() {
        encoding = null;
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        if(request.getCharacterEncoding() == null) {
            String encoding = selectEncoding();
            if(encoding != null)
                request.setCharacterEncoding(encoding);
        }
        chain.doFilter(request, response);
    }

    public void init(FilterConfig filterConfig) throws ServletException {
        encoding = filterConfig.getInitParameter("encoding");
    }

    protected String selectEncoding() {
        return encoding;
    }

    protected String encoding;
}