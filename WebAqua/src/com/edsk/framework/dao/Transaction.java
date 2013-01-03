package com.edsk.framework.dao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.edsk.framework.Config;
import com.edsk.framework.ConfigKey;
import com.edsk.framework.JNDILocator;

/**
 * @author mksong
 */
public class Transaction {
	final static String UTX_NAME_DEFAULT="java:comp/env/UserTransaction";
    static String jndiName=UTX_NAME_DEFAULT;
    static boolean enabled=false;
    protected static Log log=null;
    
    static {
        log = LogFactory.getLog(Transaction.class);
        
        Config config=Config.getInstance();
        if ((enabled=!"false".equals(config.getProperty(ConfigKey.JTS_ENABLED)))) {
            log.info("Transaction enabled");
            jndiName=config.getProperty(ConfigKey.JTS_JNDI_NAME);
            if (jndiName==null) {
                jndiName=UTX_NAME_DEFAULT;
            }
        } else {
        	log.info("JNDI name for UserTransaction = "+jndiName);
            log.info("Transaction disabled");
        }
    }
    
	UserTransaction utx = null;
	
	public Transaction() {
        if (enabled) { 
            try {
                log.debug("look up = "+jndiName);
                utx=(UserTransaction)JNDILocator.lookup(jndiName, UserTransaction.class);
                if (utx!=null) {
                    log.debug("transaction begin = "+jndiName);
                    utx.begin();
                } else {
                    log.debug("The UserTransaction is not ready. The Transaction is disabled");
                    enabled=false;                    
                }
            } catch (Exception e) {
                log.error("The UserTransaction is not ready. The Transaction is disabled",e);
                enabled=false;
            }
        }
	}
	
	public void commit() {
        if (utx!=null) {
            try {
                log.debug("transaction commit");
                utx.commit();
            } catch (Exception e) {
                log.error("Fail to commit the transaction ",e);
            }
        }
	}

	public void rollback() {
        if (utx!=null) {
            try {
                log.debug("transaction rollback");
                utx.rollback();
            } catch (Exception e) {
                log.error("Fail to rollback the transaction ",e);
            }
        }
	}
}
