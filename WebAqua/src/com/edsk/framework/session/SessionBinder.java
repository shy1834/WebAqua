package com.edsk.framework.session;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

/**
 * SessionBinder
 * 
 * @author mksong
 */
public interface SessionBinder {    
    public Object getSessionObject(HttpServletRequest request,Locale locale);

}
