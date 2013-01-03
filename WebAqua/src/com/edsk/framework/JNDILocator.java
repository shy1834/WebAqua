package com.edsk.framework;

import javax.naming.*;

import org.apache.commons.collections.LRUMap;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * InitialContext�� ���� JNDI���� ��ü�� �˻��Ѵ�.<br>
 * ���� ������ �� ���� getInstance() �޽�带 ����Ѵ�.<br>
 * 
 * 
 * @author mksong
 */
public class JNDILocator {
    /**
     *ServiceLocator�� instance. �� System�� ���� �ϳ��� instance�� �����Ѵ�.
     */
    static JNDILocator instance=null;
    static Log log=null;
	static boolean cachingMode=true;
    
	static {
        log=LogFactory.getLog(JNDILocator.class);
		Config config=Config.getInstance();
		cachingMode=!"false".equals(config.getProperty(
				JNDILocator.class.getName(),
				ConfigKey.SERVICE_LOCATER_CACHE_MODE));
        instance=new JNDILocator();
	}
    /**
     * �ѹ� ��ȸ�� JNDI ��ü�� Cache
     */
	LRUMap cache;
	
    /**
     * ServiceLocator�� instance�� ��ȸ�Ѵ�.
     */
    public static JNDILocator getInstance() {
        return instance;
    }
    
    /**
     * ������. cache, JNDI Context�� �����Ѵ�.
     */
    private JNDILocator() {
        log.info("Create the ServiceLocator instance");
        Config config=Config.getInstance();
        if (isCachingMode()) {
            log.info("cache mode on");
            int maxSize=config.getPropertyInt(
                    JNDILocator.class.getName(),
                    ConfigKey.SERVICE_LOCATER_CACHE_SIZE,100);
            log.info("cache size = "+maxSize);
            cache = new LRUMap(maxSize);
        } else {
            log.info("cache mode off");
        }
    }

    /**
     * Get InitialContext<br>
     *@return JNDI Context
     */
    public static InitialContext getJndiContext() throws NamingException {
        log.debug("create jndi context");
        return new InitialContext();
    }

    /**
     * Release InitialContext
     * @param jndi �ݳ��� JNDI Context
     */
    public static void releaseJndiContext(InitialContext jndi) {
        try {
            log.debug("close jndi context");
            jndi.close();
        } catch (NamingException e) {
            log.error("NamingException",e);
        }
    }

    /**
     * JNDI Object�� ��ȸ�Ѵ�.<br>
     * ������ �̸����� Cache�� ��ϵǾ� �ִ��� Ȯ���ϰ�
     * ���� ��� JNDI Context�� ���� ������ �̸��� ��ü�� ��ȸ�Ѵ�.<br>
     * PortableRemoteObject.narrow�� �������� �ʴ´�.
     * ���� return�� Object�� PortableRemoteObject.narrow�� ���� �� ��ȯ�ؼ� ����ؾ��Ѵ�.<br>
     * 
     * @param jndi getJndiContext()�� ���� ���� InitialContext
     * @param name JNDI name
     * @param cache cache ��뿩��
     * @return JNDI Object
     * @throws NamingException
     */    
    public static Object lookup(InitialContext jndi,String name,boolean cache) throws NamingException {
        JNDILocator instance=JNDILocator.getInstance();
        Object obj=null;
        if (isCachingMode() && cache) {
            log.debug("finding "+name+" from the cache");
            obj=instance.cache.get(name);
        }
        if (obj==null) {
            log.debug("no object found for "+name+" from the cache");
            log.debug("looking for a new object from the JNDI. caching="+cache);
            if ((obj=jndi.lookup(name))!=null && isCachingMode() && cache) {
                log.debug("A new object is stored into the cache");                
                instance.cache.put(name,obj);
            }
        }
        return obj;
    }

    /**
     * JNDI Object�� ��ȸ�Ѵ�.<br>
     * ������ �̸����� Cache�� ��ϵǾ� �ִ��� Ȯ���ϰ�
     * ���� ��� JNDI Context�� ���� ������ �̸��� ��ü�� ��ȸ�Ѵ�.<br>
     * PortableRemoteObject.narrow�� �������� �ʴ´�.
     * ���� return�� Object�� PortableRemoteObject.narrow�� ���� �� ��ȯ�ؼ� ����ؾ��Ѵ�.<br>
     * 
     * @param jndi getJndiContext()�� ���� ���� InitialContext
     * @param name JNDI name
     * @return JNDI Object
     * @throws NamingException
     */
    public static Object lookup(InitialContext jndi,String name) throws NamingException {
        return lookup(jndi,name,isCachingMode());
    }

    /**
     * JNDI Object�� ��ȸ�Ѵ�.<br>
     * ������ �̸����� Cache�� ��ϵǾ� �ִ��� Ȯ���ϰ�
     * ���� ��� JNDI Context�� ���� ������ �̸��� ��ü�� ��ȸ�Ѵ�.<br>
     * PortableRemoteObject.narrow�� �������� �ʴ´�.
     * ���� return�� Object�� PortableRemoteObject.narrow�� ���� �� ��ȯ�ؼ� ����ؾ��Ѵ�.<br>
     * 
     * @param name JNDI name
     * @param cache cache ��뿩
     * @return JNDI Object
     * @throws NamingException
     */
    public static Object lookup(String name,boolean cache) throws NamingException {
        JNDILocator instance=JNDILocator.getInstance();
        Object obj=null;
        if (isCachingMode() && cache) {
            log.debug("finding "+name+" from the cache");
            obj=instance.cache.get(name);
        }        
        if (obj==null) {
            log.debug("get a JNDI Context");
            InitialContext jndi=getJndiContext();
            try {
                log.debug("no object found for "+name+" from the cache");
                log.debug("looking for a new object from the JNDI. caching="+cache);
                if ((obj=jndi.lookup(name))!=null && isCachingMode() && cache) {
                    log.debug("A new object is stored into the cache");                
                    instance.cache.put(name,obj);
                }
            } finally {
                log.debug("release a JNDI Context");
                releaseJndiContext(jndi);
            }
        }
        return obj;
    }

    /**
     * JNDI Object�� ��ȸ�Ѵ�.<br>
     * ������ �̸����� Cache�� ��ϵǾ� �ִ��� Ȯ���ϰ�
     * ���� ��� JNDI Context�� ���� ������ �̸��� ��ü�� ��ȸ�Ѵ�.<br>
     * PortableRemoteObject.narrow�� �������� �ʴ´�.
     * ���� return�� Object�� PortableRemoteObject.narrow�� ���� �� ��ȯ�ؼ� ����ؾ��Ѵ�.<br>
     * 
     * @param name JNDI name
     * @return JNDI Object
     * @throws NamingException
     */
    public static Object lookup(String name) throws NamingException {
        return lookup(name,isCachingMode());
    }
    
    /**
     * JNDI Object�� ��ȸ�Ѵ�.<br>
     * ������ �̸����� Cache�� ��ϵǾ� �ִ��� Ȯ���ϰ�
     * ���� ��� JNDI Context�� ���� ������ �̸��� ��ü�� ��ȸ�Ѵ�.
     * ���޵� homeClass�� PortableRemoteObject.narrow�� �����Ѵ�.
     * @param name JNDI name
     * @return JNDI Object
     * @throws NamingException
     */
    public static Object lookup(InitialContext jndi,String name,Class homeClass,boolean cache) throws NamingException {
        Object obj=null;
        if ((obj=lookup(jndi,name,cache))!=null) {
            obj=javax.rmi.PortableRemoteObject.narrow(obj,homeClass);
        }
        return obj;
    }

    /**
     *  ������ �̸��� JNDI ��ü�� ��ȸ�Ѵ�.<br>
     * <b>�ѹ� ��ȸ�� ��ü�� Cache�ȴ�. ����, EJBHome�� ���� ���� ������ ��ü�� ��쿡�� ����Ѵ�.</b>
     * @param jndi getJndiContext()�� ���� ���� InitialContext
     * @param name ��ü�� JNDI Name
     * @param homeClass ��ȸ�� ��ü�� Class
     * @return ��ȸ�� ��ü. ���ϴ� ��ü�� ��ȯ�Ͽ� ����Ѵ�.
     * @throws NamingException
     */
    public static Object lookup(InitialContext jndi,String name,Class homeClass) throws NamingException {
        return lookup(jndi,name,homeClass,true);
    }
    
    /**
     * JNDI Object�� ��ȸ�Ѵ�.<br>
     * ������ �̸����� Cache�� ��ϵǾ� �ִ��� Ȯ���ϰ�
     * ���� ��� JNDI Context�� ���� ������ �̸��� ��ü�� ��ȸ�Ѵ�.
     * ���޵� homeClass�� PortableRemoteObject.narrow�� �����Ѵ�.
     * @param name ��ü�� JNDI Name
     * @param homeClass ��ȸ�� ��ü�� Class
     * @param cache ��ȸ�� ��ü�� Cache�� ���� ������.
     * @return ��ȸ�� ��ü. ���ϴ� ��ü�� ��ȯ�Ͽ� ����Ѵ�.
     * @throws NamingException
     */
    public static Object lookup(String name,Class homeClass,boolean cache) throws NamingException {
        Object obj=null;
        if ((obj=lookup(name,cache))!=null) {
            obj=javax.rmi.PortableRemoteObject.narrow(obj,homeClass);
        }
        return obj;
    }

    /**
     *  ������ �̸��� JNDI ��ü�� ��ȸ�Ѵ�.<br>
     * <b>�ѹ� ��ȸ�� ��ü�� Cache�ȴ�. ����, EJBHome�� ���� ���� ������ ��ü�� ��쿡�� ����Ѵ�.</b>
     * @param name ��ü�� JNDI Name
     * @param homeClass ��ȸ�� ��ü�� Class
     * @return ��ȸ�� ��ü. ���ϴ� ��ü�� ��ȯ�Ͽ� ����Ѵ�.
     * @throws NamingException
     */
    public static Object lookup(String name,Class homeClass) throws NamingException {
        return lookup(name,homeClass,true);
    }

    /**
     * @return Default�� Cache�� ����� ���ΰ� ����
     */
    protected static boolean isCachingMode() {
        return cachingMode;
    }
}