package com.edsk.framework.service;

import java.util.Locale;

import org.apache.commons.collections.LRUMap;

import com.edsk.framework.Config;
import com.edsk.framework.ConfigKey;
import com.edsk.framework.ObjectFactory;

/**
 * ServiceFactory<br>
 * 
 * ���� ��ü�� �����ϰ�, �����Ѵ�.<br>
 * �ѹ� ������ ��ü�� LRUMap�� ��� Caching�ȴ�.<br>
 * 
 * ������ �̸��� ���񽺸� Cache���� �˻��� ����, Cache�� ���� ���
 * ���ο� ���񽺸� �����Ѵ�.<br>
 * 
 * ���񽺸� ������ ���� CLASSPATH���� �ִ� "/service.properties"
 * �� ��ȸ�Ͽ� �ش� �̸��� Ŭ���� �̸��� ��ȸ�Ѵ�.<br>
 * 
 * "/service.properties"�� ���ų� ������ �̸��� Ŭ���� ���� 
 * ��ȸ�� �� ���� ��� ���񽺸���  Ŭ���� ������ ����Ͽ� Ŭ���� 
 * ������ �õ��Ѵ�.<br>
 * 
 * ssf.properties �� 
 * com.ktf.aqua.framework.service.ServiceFactory.cache=off �Ǵ�
 * cache=off �� �����Ǹ� ���� �ν��Ͻ��� Cache�� �������� �ʴ´�.
 * com.ktf.aqua.framework.service.ServiceFactory.cache�� ������
 * �켱�� �ȴ�.<br>
 * ssf.properties ��
 * com.ktf.aqua.framework.service.ServiceFactory.cache.size �Ǵ�
 * cache.size�Ӽ����� Cache�� ũ�⸦ �����Ѵ�. 
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
     * ���� ���丮�� �ν��Ͻ��� ��ȸ�Ѵ�.
     * @return ���� ���丮�� �ν��Ͻ�
     */
    static public ServiceFactory getInstance() {
        return instance;
    }    
    
    /**
     * ���� �ν��Ͻ��� ��ȸ�Ѵ�.<br>
     * ������ �̸��� ���� �ν��Ͻ��� Cache���� ��ȸ�� ����,
     * �������� ���� ��� ObjectFactory.getObject()�� ���� 
     * ���ο� ���� �ν��Ͻ��� �����Ѵ�.<br>
     * 
     * ���� ������ �ν��Ͻ��� Cache�� �����ȴ�.
     * 
     * @param serviceName ��ȸ�ϰ��� �ϴ� ���񽺸�
     * @return ���� �ν��Ͻ�
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
     * ���� �ν��Ͻ��� ��ȸ�Ѵ�.
     * ������ �̸�, �������� ���� �ν��Ͻ��� Cache���� ��ȸ�� ����,
     * �������� ���� ��� ObjectFactory.getObject()�� ���� 
     * ���ο� ���� �ν��Ͻ��� �����Ѵ�.<br>
     * 
     * ���� ������ �ν��Ͻ��� Cache�� �����ȴ�.
     * 
     * @param serviceName ��ȸ�ϰ��� �ϴ� ���񽺸�
     * @param locale ������
     * @return ���� �ν��Ͻ�
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
     * ������ �̸��� ���� �ν��Ͻ��� ��ȸ�Ѵ�.
     * @param serviceName ���񽺸�
     * @return ���� �ν��Ͻ�
     */
    public Service getService(String serviceName) {
        return (Service)getObject(serviceName);
    }

    /**
     * ������ �̸�,�������� ���� �ν��Ͻ��� ��ȸ�Ѵ�.
     * @param serviceName ���񽺸�
     * @param locale ������
     * @return ���� �ν��Ͻ�
     */
    public LocaleBaseService getService(String serviceName,Locale locale) {
        return (LocaleBaseService)getObject(serviceName,locale);
    }

    /**
     * ���� �� ���� Ŭ������ ���ǵǾ� �ִ� properties������ �̸��� ��ȸ�Ѵ�.
     */    
    protected String getPropertiesName() {
        String propName=Config.getInstance().getProperty(ConfigKey.SERVICE_FACTORY_DEFINE);
        return (propName!=null)?propName:DEFAULT_SERVICE_DEFINE;
    }

    /**
     * ���� ���丮�� ������
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
