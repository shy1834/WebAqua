package com.edsk.framework;
import java.util.Properties;
import java.io.*;

/**
 * Application의 설정 정보를 관리한다.<br>
 * CLASSPATH내에서 (보통 /WEB-INF/classes) aqua.properties 파일을 읽어 
 * 여기에 설정된 값을 보관한다.<br>
 * 설정 파일을 aqua.properties로 하지 않고 변경하고 싶을때는 Java 실행 시에
 * "-Dssg.config=파일명" 의 옵션을 줘 변경할 수 있다.
 * <br>
 * aqua.properties 파일은 "key=value"의 형태로 보관된 
 * java.util.Properties의 파일이다.<br> 
 * 
 * @author mksong
 */
public class Config {
	/**
	 * 설정 정보의 Instance를 생성하거나 조회한다.<br>
	 * 공유정보에 접근하지만 synchronized로는 처리하지는 않았다.<br>
	 * 만에 하나 instance가 두번 생성된들 무슨 상관이겠는가?<br>
	 * @return 설정 정보의 Instance
	 */
    public static Config getInstance() {
        return instance;
    }

	/**
	 * key에 해당하는 값을 조회.
	 * @param key 조회하고자 하는 정보의 key
	 * @return 설정 정보에 설정된 값
	 */
    public String getProperty(String key) {
        return prop.getProperty(key);
    }

	/**
	 * key에 해당하는 값을 조회.<br>
	 * className.key에 해당하는 값을 조회해 본 후 찾을 수 없을 경우 
	 * key로 다시 조회한다.<br>
	 *
	 * @param className 조회하고자 하는 정보의 class
	 * @param key 조회하고자 하는 정보의 key
	 * @return 설정 정보에 설정된 값
	 */
    public String getProperty(String className,String key) {
        String str=getProperty(className+"."+key);
        if (str==null) {
            str=getProperty(key);
        }
        return str;
    }

	/**
	 * key에 해당하는 값을 int로 변환하여 조회
	 * @param key 조회할 key
	 * @param defaultProp  key에 해당하는 값이 없을 경우 default 값
	 * @return key에 해당하는 값
	 */
    public int getPropertyInt(String key,int defaultProp) {
        String str=getProperty(key);
        int prop=defaultProp;
        if (str!=null) {
            try {
                prop=Integer.parseInt(str);
            } catch (NumberFormatException e) {}
        }
        return prop;
    }

	/**
	 * key에 해당하는 값을 int로 변환하여 조회
	 * 
	 * @param className 조회할 key의 class
	 * @param key 조회할 key
	 * @param defaultProp  key에 해당하는 값이 없을 경우 default 값
	 * @return key에 해당하는 값
	 */
    public int getPropertyInt(String className,String key,int defaultProp) {
        String str=getProperty(className,key);
        int prop=defaultProp;
        if (str!=null) {
            try {
                prop=Integer.parseInt(str);
            } catch (NumberFormatException e) {}
        }
        return prop;
    }

    public void reloadConfig() {
        String propName=System.getProperty(KEY_PROPERTIES,SYSTEM_PROPERTIES);
        prop=loadProperties(propName);
    }

    protected Properties loadProperties(String dataSource) {
        Properties prop=new Properties();
        InputStream is=getClass().getResourceAsStream("/"+dataSource);
        if (is!=null) {
            try {
                prop.load(is);
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        }
        return prop;
    }

    /**
     * 생성자.
     * Config는 외부에서 new를 통해 생성할 수 없고 getInstance()을 통해 생성한다.<br>
     *@see kr.co.kame.ssg.framework.Config#getInstance getInstance
     */
    private Config() {
        reloadConfig();
    }

    private final static String KEY_PROPERTIES="aqua.config";
    private final static String SYSTEM_PROPERTIES="aqua.properties";
    private Properties prop=null;

    /**
     * Config의 instance
     */
    private static Config instance=new Config();
}