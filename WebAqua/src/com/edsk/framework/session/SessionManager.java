package com.edsk.framework.session;

import java.util.HashMap;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.edsk.framework.Config;
import com.edsk.framework.ConfigKey;

/**
 * SessionManager
 * 
 * @author mksong
 */
public class SessionManager {
    private static Log log = LogFactory.getLog(UserInfoSessionBinder.class);
    final public static String SESSION_KEY_PREFIX="kr.co.kame.ssg.session.";
    final public static String SESSION_KEY_USER_INFO="USER_INFO" ;
    final static HashMap binderMap=new HashMap();
    static HashMap defaultBinders=null;
    
    static {
        defaultBinders=new HashMap();
        defaultBinders.put(SESSION_KEY_USER_INFO,UserInfoSessionBinder.class.getName());
    }
    
    public static Object getSessionObject(String sessionKey,
            HttpServletRequest request, Locale locale) {
        log.info("lookup session sessionKey="+sessionKey);
        String key=SESSION_KEY_PREFIX+sessionKey;
        log.debug("key="+key);
        HttpSession session=request.getSession();
        Object sessionObject=session.getAttribute(key);
        if (sessionObject==null) {
            log.debug("session empty.");
            try {
                SessionBinder binder=(SessionBinder)getSessionBinder(sessionKey);
                sessionObject=binder.getSessionObject(request,locale);
                if (sessionObject!=null) {
                    log.debug("create new session.");
                    session.setAttribute(key,sessionObject);
                }
            } catch (SessionException e) {
                log.error("fail to bind session sessionKey="+sessionKey,e);
            }            
        }
        return sessionObject;
    }

    private static SessionBinder getSessionBinder(String sessionKey) 
            throws SessionException {
        SessionBinder binder=(SessionBinder)binderMap.get(sessionKey);
        if (binder==null) {
            binder=createBinder(sessionKey);
            if (binder!=null) {
                binderMap.put(sessionKey,binder);
            }
        }
        return binder;
    }

    private static SessionBinder createBinder(String sessionKey) 
            throws SessionException {
        Config config=Config.getInstance();
        String className=config.getProperty(
                ConfigKey.SESSION_BINDER_KEY+"."+sessionKey);
        if (className==null) {
            className=(String)defaultBinders.get(sessionKey);
        }
        if (className!=null) {
            try {
                Class c = Class.forName(className);
                Object o=c.newInstance();
                if (o instanceof SessionBinder) {
                    return (SessionBinder)o;
                } else {
                    throw new SessionException("SessionBinder is not instance of SessionBinder");
                }
            } catch (Exception e) {
                throw new SessionException("Fail to create a SessionBinder",e);
            }
        } else {
            throw new SessionException("SessionBinder is not defined");
        }
    }
}
