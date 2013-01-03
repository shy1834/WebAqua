package com.edsk.framework.listener;

import java.text.DateFormat;
import java.util.Date;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * SessionEventListener
 * 
 * @author mksong
 */
public class SessionEventListener 
        implements HttpSessionAttributeListener, HttpSessionListener {
    private static Log log = LogFactory.getLog(SessionEventListener.class);
    private static DateFormat df = DateFormat.getDateTimeInstance(DateFormat.LONG,DateFormat.LONG);


    public void attributeAdded(HttpSessionBindingEvent event) {
        log.info("\nA session attribute is added.("+event.getName()+" = "+event.getValue()+")");
    }

    public void attributeRemoved(HttpSessionBindingEvent event) {
        log.info("\nA session attribute is removed.("+event.getName()+" = "+event.getValue()+")");
    }

    public void attributeReplaced(HttpSessionBindingEvent event) {
        log.info("\nA session attribute is replaced.("+event.getName()+" = "+event.getValue()+")");
    }

    public void sessionCreated(HttpSessionEvent event) {
        HttpSession session=event.getSession();
        log.info("\nA new session is created. id="+session.getId());
    }

    public void sessionDestroyed(HttpSessionEvent event) {
    }

    /**
     * @param l
     * @return
     */
    private String getDate(long l) {
        return df.format(new Date(l));
    }

}
