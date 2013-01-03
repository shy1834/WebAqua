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
 * Properties 파일을 통해 클래스를 생성해주는 Factory 
 * 
 * @author mksong
 */
abstract public class ObjectFactory {
    static protected Log log=LogFactory.getLog(ObjectFactory.class);    
    final private Properties prop=loadProperties();

    /**
     * Properties 파일의 이름을 지정해주는 abstract method
     * 
     * @return Properties 파일의 이름
     */
    abstract protected String getPropertiesName();

    /**
     * key에 해당하는 클래스를 생성한다.<br>
     * Properties 파일에서 key에 해당하는 클래스의 이름이 정의되어 있지 않으면,
     * key 자체를 클래스 이름으로 사용한다.
     * 
     * @param key 클래스의 키
     * @return Key에 해당하는 Class 객체
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
     * key에 해당하는 객체를 생성한다.<br>
     * 
     * Properties 파일에서 key에 해당하는 클래스의 이름이 정의되어 있지 않으면,
     * key 자체를 클래스 이름으로 사용한다.<br>
     * 
     * key로 지정된 객체는 default contructor가 정의되어 있어야 한다.
     * 
     * @param key 객체의 키
     * @return key에 해당하는 Class 객체
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
     * key에 해당하는 객체를 생성한다.<br>
     * 
     * Properties 파일에서 key에 해당하는 클래스의 이름이 정의되어 있지 않으면,
     * key 자체를 클래스 이름으로 사용한다.<br>
     *
     * 해당 객체의 constructor가 parameter를 가질 경우 사용한다.
     *  
     * @param key 객체의 키
     * @param parameterTypes constructor의 parameter types
     * @param params constructor의 parameter values
     * @return key에 해당하는 객체
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
     * @param propName load할 property이름
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
     * Properties로부터 key에 해당하는 클래스 이름을 검색한다.
     */
    protected String getProperty(String key) {
        return prop.getProperty(key);
    }

    /**
     * Factory의 property가 지정되지 않았을 경우 
     * default로 넘겨줄 값을 담고 있는 Properties를 생성한다.<br>
     * 기본적으로는 빈 Properties만 리턴하도록 되어 있는데,
     * 이 메서드를 재정의 하여 default설정을 가지는 Factory를 생성할 수 있다.<br>
     * 
     * @return 빈 Properties
     */
    protected Properties getDefaultProperties() {
        return new Properties();
    }

    /**
     * Factory의 설정 파일을 읽어들인다.
     */
    private Properties loadProperties() {
        String propName=getPropertiesName();
        Properties prop=getDefaultProperties();
        prop.putAll(loadProperties(propName));
        return prop;
    }

}
