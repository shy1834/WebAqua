package com.edsk.framework;
import java.util.Properties;
import java.io.*;

/**
 * Application�� ���� ������ �����Ѵ�.<br>
 * CLASSPATH������ (���� /WEB-INF/classes) aqua.properties ������ �о� 
 * ���⿡ ������ ���� �����Ѵ�.<br>
 * ���� ������ aqua.properties�� ���� �ʰ� �����ϰ� �������� Java ���� �ÿ�
 * "-Dssg.config=���ϸ�" �� �ɼ��� �� ������ �� �ִ�.
 * <br>
 * aqua.properties ������ "key=value"�� ���·� ������ 
 * java.util.Properties�� �����̴�.<br> 
 * 
 * @author mksong
 */
public class Config {
	/**
	 * ���� ������ Instance�� �����ϰų� ��ȸ�Ѵ�.<br>
	 * ���������� ���������� synchronized�δ� ó�������� �ʾҴ�.<br>
	 * ���� �ϳ� instance�� �ι� �����ȵ� ���� ����̰ڴ°�?<br>
	 * @return ���� ������ Instance
	 */
    public static Config getInstance() {
        return instance;
    }

	/**
	 * key�� �ش��ϴ� ���� ��ȸ.
	 * @param key ��ȸ�ϰ��� �ϴ� ������ key
	 * @return ���� ������ ������ ��
	 */
    public String getProperty(String key) {
        return prop.getProperty(key);
    }

	/**
	 * key�� �ش��ϴ� ���� ��ȸ.<br>
	 * className.key�� �ش��ϴ� ���� ��ȸ�� �� �� ã�� �� ���� ��� 
	 * key�� �ٽ� ��ȸ�Ѵ�.<br>
	 *
	 * @param className ��ȸ�ϰ��� �ϴ� ������ class
	 * @param key ��ȸ�ϰ��� �ϴ� ������ key
	 * @return ���� ������ ������ ��
	 */
    public String getProperty(String className,String key) {
        String str=getProperty(className+"."+key);
        if (str==null) {
            str=getProperty(key);
        }
        return str;
    }

	/**
	 * key�� �ش��ϴ� ���� int�� ��ȯ�Ͽ� ��ȸ
	 * @param key ��ȸ�� key
	 * @param defaultProp  key�� �ش��ϴ� ���� ���� ��� default ��
	 * @return key�� �ش��ϴ� ��
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
	 * key�� �ش��ϴ� ���� int�� ��ȯ�Ͽ� ��ȸ
	 * 
	 * @param className ��ȸ�� key�� class
	 * @param key ��ȸ�� key
	 * @param defaultProp  key�� �ش��ϴ� ���� ���� ��� default ��
	 * @return key�� �ش��ϴ� ��
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
     * ������.
     * Config�� �ܺο��� new�� ���� ������ �� ���� getInstance()�� ���� �����Ѵ�.<br>
     *@see kr.co.kame.ssg.framework.Config#getInstance getInstance
     */
    private Config() {
        reloadConfig();
    }

    private final static String KEY_PROPERTIES="aqua.config";
    private final static String SYSTEM_PROPERTIES="aqua.properties";
    private Properties prop=null;

    /**
     * Config�� instance
     */
    private static Config instance=new Config();
}