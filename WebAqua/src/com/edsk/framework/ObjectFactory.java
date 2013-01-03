package com.edsk.framework;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * ObjectFactory<br>
 * 
 * Properties ������ ���� Ŭ������ �������ִ� Factory 
 * 
 * @author mksong
 */
abstract public class ObjectFactory {
    static protected Log log=LogFactory.getLog(ObjectFactory.class);    
    final private Properties prop=loadProperties();

    /**
     * Properties ������ �̸��� �������ִ� abstract method
     * 
     * @return Properties ������ �̸�
     */
    abstract protected String getPropertiesName();

    /**
     * key�� �ش��ϴ� Ŭ������ �����Ѵ�.<br>
     * Properties ���Ͽ��� key�� �ش��ϴ� Ŭ������ �̸��� ���ǵǾ� ���� ������,
     * key ��ü�� Ŭ���� �̸����� ����Ѵ�.
     * 
     * @param key Ŭ������ Ű
     * @return Key�� �ش��ϴ� Class ��ü
     */
    protected Class getClass(String key) {
        log.debug("key="+key);
        String className=getProperty(key);
        if (className==null) {
            log.debug("no other classes are defined for the key "+className);
            className=key;
        }
        log.debug("className="+className);
        Class classObject=null;
        try {
            classObject=Class.forName(className);
        } catch (ClassNotFoundException e) {
            log.error("Can't find the class "+className,e);
        }
        return classObject;
    }
    
    /**
     * key�� �ش��ϴ� ��ü�� �����Ѵ�.<br>
     * 
     * Properties ���Ͽ��� key�� �ش��ϴ� Ŭ������ �̸��� ���ǵǾ� ���� ������,
     * key ��ü�� Ŭ���� �̸����� ����Ѵ�.<br>
     * 
     * key�� ������ ��ü�� default contructor�� ���ǵǾ� �־�� �Ѵ�.
     * 
     * @param key ��ü�� Ű
     * @return key�� �ش��ϴ� Class ��ü
     */
    protected Object getObject(String key) {
        Class classObject=getClass(key);
        Object instance=null;
        if (classObject!=null) {
            try {
                instance=classObject.newInstance();
            } catch (Throwable e) {
                log.error("Can't create class",e);
            }
        }
        return instance;
    }

    /**
     * key�� �ش��ϴ� ��ü�� �����Ѵ�.<br>
     * 
     * Properties ���Ͽ��� key�� �ش��ϴ� Ŭ������ �̸��� ���ǵǾ� ���� ������,
     * key ��ü�� Ŭ���� �̸����� ����Ѵ�.<br>
     *
     * �ش� ��ü�� constructor�� parameter�� ���� ��� ����Ѵ�.
     *  
     * @param key ��ü�� Ű
     * @param parameterTypes constructor�� parameter types
     * @param params constructor�� parameter values
     * @return key�� �ش��ϴ� ��ü
     */
    protected Object getObject(String key, Class[] parameterTypes,Object[] params) {
        Class classObject=getClass(key);
        Object instance=null;
        if (classObject!=null) {
            try {
                Constructor constructor = classObject.getConstructor(parameterTypes);
                instance = constructor.newInstance(params);
            } catch (Throwable e) {
                log.error("Can't create class",e);
            }
        }
        return instance;
    }

    /**
     * @param propName load�� property�̸�
     * @return Properties
     */
    private static Properties loadProperties(String propName) {
        log.info("load properties name="+propName);
        Properties prop=new Properties();
        if (propName!=null) {
            InputStream is=ObjectFactory.class.getResourceAsStream(propName);
            if (is!=null) {
                try {
                    prop.load(is);
                } catch (IOException e) {
                    log.error("IOException",e);
                }
            } else {
                log.warn("Can't find properties file");
            }
        } else {
            log.warn("The properties file name is null");
        }
        return prop;
    }

    /**
     * Properties�κ��� key�� �ش��ϴ� Ŭ���� �̸��� �˻��Ѵ�.
     */
    protected String getProperty(String key) {
        return prop.getProperty(key);
    }

    /**
     * Factory�� property�� �������� �ʾ��� ��� 
     * default�� �Ѱ��� ���� ��� �ִ� Properties�� �����Ѵ�.<br>
     * �⺻�����δ� �� Properties�� �����ϵ��� �Ǿ� �ִµ�,
     * �� �޼��带 ������ �Ͽ� default������ ������ Factory�� ������ �� �ִ�.<br>
     * 
     * @return �� Properties
     */
    protected Properties getDefaultProperties() {
        return new Properties();
    }

    /**
     * Factory�� ���� ������ �о���δ�.
     */
    private Properties loadProperties() {
        String propName=getPropertiesName();
        Properties prop=getDefaultProperties();
        prop.putAll(loadProperties(propName));
        return prop;
    }

}
