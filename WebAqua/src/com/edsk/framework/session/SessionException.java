package com.edsk.framework.session;


/**
 * SessionException
 * 
 * @author mksong
 */
public class SessionException extends Exception {

    public SessionException() {
        super();
    }

    public SessionException(String message) {
        super(message);
    }

    public SessionException(Throwable cause) {
        super(cause);
    }

    public SessionException(String message, Throwable cause) {
        super(message, cause);
    }

}
