package com.edsk.framework.service;

import java.util.Locale;

import org.apache.commons.collections.LRUMap;

import com.edsk.framework.Config;
import com.edsk.framework.ConfigKey;
import com.edsk.framework.ObjectFactory;

/**
 * ServiceFactory<br>
 * 
 * 서비스 객체를 생성하고, 관리한다.<br>
 * 한번 생성된 객체는 LRUMap에 담겨 Caching된다.<br>
 * 
 * 지정된 이름의 서비스를 Cache에서 검색해 보고, Cache에 없을 경우
 * 새로운 서비스를 생성한다.<br>
 * 
 * 서비스를 생성할 때는 CLASSPATH내에 있는 "/service.properties"
 * 를 조회하여 해당 이름의 클래스 이름을 조회한다.<br>
 * 
 * "/service.properties"가 없거나 지정된 이름의 클래스 명을 
 * 조회할 수 없을 경우 서비스명을  클래스 명으로 사용하여 클래스 
 * 생성을 시도한다.<br>
 * 
 * ssf.properties 에 
 * com.ktf.aqua.framework.service.ServiceFactory.cache=off 또는
 * cache=off 로 지정되면 서비스 인스턴스의 Cache가 동작하지 않는다.
 * com.ktf.aqua.framework.service.ServiceFactory.cache의 설정이
 * 우선시 된다.<br>
 * ssf.properties 의
 * com.ktf.aqua.framework.service.ServiceFactory.cache.size 또는
 * cache.size속성으로 Cache의 크기를 지정한다. 
 * <br>
 * @author mksong
 */
public class ServiceFactory extends ObjectFactory {
    final static int DEFAULT_CACHE_SIZE=50;
    final static String DEFAULT_SERVICE_DEFINE="/service.properties";
    final static Class[] PARAMETER_TYPE={Locale.class};
    final static Config config=Config.getInstance();

    final static ServiceFactory instance=getServiceFactory();
    LRUMap serviceCache=null;

    /**
     * 서비스 팩토리의 인스턴스를 조회한다.
     * @return 서비스 팩토리의 인스턴스
     */
    static public ServiceFactory getInstance() {
        return instance;
    }    
    
    /**
     * 서비스 인스턴스를 조회한다.<br>
     * 지정된 이름의 서비스 인스턴스를 Cache에서 조회해 보고,
     * 존재하지 않을 경우 ObjectFactory.getObject()를 통해 
     * 새로운 서비스 인스턴스를 생성한다.<br>
     * 
     * 새로 생성된 인스턴스는 Cache에 보관된다.
     * 
     * @param serviceName 조회하고자 하는 서비스명
     * @return 서비스 인스턴스
     */
    protected Object getObject(String serviceName) {
        Object obj=null;
        if (serviceCache!=null) {
            obj=serviceCache.get(serviceName);
            if (obj==null) {
                obj=super.getObject(serviceName);
                if (obj!=null && serviceCache!=null) {
                    serviceCache.put(serviceName,obj);
                }
            }
        } else {
            obj=super.getObject(serviceName);
        }
        return obj;
    }

    /**
     * 서비스 인스턴스를 조회한다.
     * 지정된 이름, 로케일의 서비스 인스턴스를 Cache에서 조회해 보고,
     * 존재하지 않을 경우 ObjectFactory.getObject()를 통해 
     * 새로운 서비스 인스턴스를 생성한다.<br>
     * 
     * 새로 생성된 인스턴스는 Cache에 보관된다.
     * 
     * @param serviceName 조회하고자 하는 서비스명
     * @param locale 로케일
     * @return 서비스 인스턴스
     */
    protected Object getObject(String serviceName, Locale locale) {
        Object obj=null;
        Object[] param=new Object[1];
        param[0]=locale;

        if (serviceCache!=null) {
            String cacheKey=serviceName+locale.toString();
            obj=serviceCache.get(cacheKey);
            if (obj==null) {
                obj=super.getObject(serviceName,PARAMETER_TYPE,param);
                if (obj!=null && serviceCache!=null) {
                    serviceCache.put(serviceName,obj);
                }
            }
        } else {
            obj=super.getObject(serviceName,PARAMETER_TYPE,param);
        }
        return obj;
    }

    /**
     * 지정된 이름의 서비스 인스턴스를 조회한다.
     * @param serviceName 서비스명
     * @return 서비스 인스턴스
     */
    public Service getService(String serviceName) {
        return (Service)getObject(serviceName);
    }

    /**
     * 지정된 이름,로케일의 서비스 인스턴스를 조회한다.
     * @param serviceName 서비스명
     * @param locale 로케일
     * @return 서비스 인스턴스
     */
    public LocaleBaseService getService(String serviceName,Locale locale) {
        return (LocaleBaseService)getObject(serviceName,locale);
    }

    /**
     * 서비스 명별 서비스 클래스가 정의되어 있는 properties파일의 이름을 조회한다.
     */    
    protected String getPropertiesName() {
        String propName=Config.getInstance().getProperty(ConfigKey.SERVICE_FACTORY_DEFINE);
        return (propName!=null)?propName:DEFAULT_SERVICE_DEFINE;
    }

    /**
     * 서비스 팩토리의 생성자
     */
    protected ServiceFactory() {
        super();
        String propertyGroup=getClass().getName();
        if (!"off".equals(config.getProperty(propertyGroup,ConfigKey.CACHE_KEY))) {
            int serviceCacheSize=config.getPropertyInt(propertyGroup,
                    ConfigKey.CACHE_SIZE_KEY,DEFAULT_CACHE_SIZE);        
            log.info("service cache on cache size ="+serviceCacheSize);
            serviceCache=new LRUMap(serviceCacheSize);
        } else {
            log.info("service cache off");
        }
    }

    private static ServiceFactory getServiceFactory() {
        String factoryName=Config.getInstance().getProperty(ConfigKey.SERVICE_FACTORY);
        ServiceFactory factory=null;
        if (factoryName==null) {
            factory = new ServiceFactory();
        } else {
            try {
                Class factoryClass=Class.forName(factoryName);
                factory=(ServiceFactory)factoryClass.newInstance();
            } catch (Exception e) {
                log.error("fail to create factory "+factoryName,e);
                factory=new ServiceFactory();
            }
        }
        
        return factory;
    }
}
