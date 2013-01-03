package com.edsk.framework;

import javax.naming.*;

import org.apache.commons.collections.LRUMap;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * InitialContext를 통해 JNDI에서 객체를 검색한다.<br>
 * 직접 생성할 수 없고 getInstance() 메쏘드를 사용한다.<br>
 * 
 * 
 * @author mksong
 */
public class JNDILocator {
    /**
     *ServiceLocator의 instance. 전 System을 통해 하나의 instance만 존재한다.
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
     * 한번 조회한 JNDI 객체의 Cache
     */
	LRUMap cache;
	
    /**
     * ServiceLocator의 instance를 조회한다.
     */
    public static JNDILocator getInstance() {
        return instance;
    }
    
    /**
     * 생성자. cache, JNDI Context를 생성한다.
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
     * @param jndi 반납할 JNDI Context
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
     * JNDI Object를 조회한다.<br>
     * 지정된 이름으로 Cache에 등록되어 있는지 확인하고
     * 없을 경우 JNDI Context를 통해 지정된 이름의 객체를 조회한다.<br>
     * PortableRemoteObject.narrow를 수행하지 않는다.
     * 따라서 return된 Object는 PortableRemoteObject.narrow를 통해 형 변환해서 사용해야한다.<br>
     * 
     * @param jndi getJndiContext()를 통해 구한 InitialContext
     * @param name JNDI name
     * @param cache cache 사용여부
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
     * JNDI Object를 조회한다.<br>
     * 지정된 이름으로 Cache에 등록되어 있는지 확인하고
     * 없을 경우 JNDI Context를 통해 지정된 이름의 객체를 조회한다.<br>
     * PortableRemoteObject.narrow를 수행하지 않는다.
     * 따라서 return된 Object는 PortableRemoteObject.narrow를 통해 형 변환해서 사용해야한다.<br>
     * 
     * @param jndi getJndiContext()를 통해 구한 InitialContext
     * @param name JNDI name
     * @return JNDI Object
     * @throws NamingException
     */
    public static Object lookup(InitialContext jndi,String name) throws NamingException {
        return lookup(jndi,name,isCachingMode());
    }

    /**
     * JNDI Object를 조회한다.<br>
     * 지정된 이름으로 Cache에 등록되어 있는지 확인하고
     * 없을 경우 JNDI Context를 통해 지정된 이름의 객체를 조회한다.<br>
     * PortableRemoteObject.narrow를 수행하지 않는다.
     * 따라서 return된 Object는 PortableRemoteObject.narrow를 통해 형 변환해서 사용해야한다.<br>
     * 
     * @param name JNDI name
     * @param cache cache 사용여
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
     * JNDI Object를 조회한다.<br>
     * 지정된 이름으로 Cache에 등록되어 있는지 확인하고
     * 없을 경우 JNDI Context를 통해 지정된 이름의 객체를 조회한다.<br>
     * PortableRemoteObject.narrow를 수행하지 않는다.
     * 따라서 return된 Object는 PortableRemoteObject.narrow를 통해 형 변환해서 사용해야한다.<br>
     * 
     * @param name JNDI name
     * @return JNDI Object
     * @throws NamingException
     */
    public static Object lookup(String name) throws NamingException {
        return lookup(name,isCachingMode());
    }
    
    /**
     * JNDI Object를 조회한다.<br>
     * 지정된 이름으로 Cache에 등록되어 있는지 확인하고
     * 없을 경우 JNDI Context를 통해 지정된 이름의 객체를 조회한다.
     * 전달된 homeClass로 PortableRemoteObject.narrow를 수행한다.
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
     *  지정된 이름의 JNDI 객체를 조회한다.<br>
     * <b>한번 조회된 객체는 Cache된다. 따라서, EJBHome과 같이 공유 가능한 객체인 경우에만 사용한다.</b>
     * @param jndi getJndiContext()를 통해 구한 InitialContext
     * @param name 객체의 JNDI Name
     * @param homeClass 조회할 객체의 Class
     * @return 조회된 객체. 원하는 객체로 변환하여 사용한다.
     * @throws NamingException
     */
    public static Object lookup(InitialContext jndi,String name,Class homeClass) throws NamingException {
        return lookup(jndi,name,homeClass,true);
    }
    
    /**
     * JNDI Object를 조회한다.<br>
     * 지정된 이름으로 Cache에 등록되어 있는지 확인하고
     * 없을 경우 JNDI Context를 통해 지정된 이름의 객체를 조회한다.
     * 전달된 homeClass로 PortableRemoteObject.narrow를 수행한다.
     * @param name 객체의 JNDI Name
     * @param homeClass 조회할 객체의 Class
     * @param cache 조회된 객체를 Cache에 담을 것인지.
     * @return 조회된 객체. 원하는 객체로 변환하여 사용한다.
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
     *  지정된 이름의 JNDI 객체를 조회한다.<br>
     * <b>한번 조회된 객체는 Cache된다. 따라서, EJBHome과 같이 공유 가능한 객체인 경우에만 사용한다.</b>
     * @param name 객체의 JNDI Name
     * @param homeClass 조회할 객체의 Class
     * @return 조회된 객체. 원하는 객체로 변환하여 사용한다.
     * @throws NamingException
     */
    public static Object lookup(String name,Class homeClass) throws NamingException {
        return lookup(name,homeClass,true);
    }

    /**
     * @return Default로 Cache를 사용할 것인가 여부
     */
    protected static boolean isCachingMode() {
        return cachingMode;
    }
}