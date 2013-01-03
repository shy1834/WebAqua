package com.edsk.framework.dao;

import com.edsk.framework.Config;
import com.edsk.framework.ConfigKey;
import com.edsk.framework.ObjectFactory;

/**
 * DAOFactory<br>
 * DAO�� ������ �ش�. ��� DAO�� DAOFactory�� ���� �����ؾ� �Ѵ�.<br>
 * <br>
 * dao�� key�� Ŭ���� ���Ǵ� ssg.properties�� dao.defines�� ���ǵȴ�.
 * dao.defines=/dao.properties<br>
 * ������ �������� ������ CLASSPATH������ /dao.properties�� �˻��ϰ� �ȴ�.<br>
 * <br> 
 * 
 * @author mksong
 */
public class FactoryDAO extends ObjectFactory {
	final static String DEFAULT_DAO_DEFINE="/dao.properties";    
    final static FactoryDAO instance =  getDAOFactory();

    /**
     * DAOFactory �ν��Ͻ��� ��ȸ�Ѵ�.
     * @return DAOFactory �ν��Ͻ�
     */    
    public static FactoryDAO getInstance() {
        return instance;
    }
    
    /**
     * key�� �ش��ϴ� DAO�� instance�� ��ȸ�Ѵ�.
     * 
     * @param key ��ü�� key
     * @return DAO�� instance
     */
	public BaseDAO getDAO(String key) {
		return (BaseDAO)getObject(key);
    }
    
    protected String getPropertiesName() {
    	String propName=Config.getInstance().getProperty(ConfigKey.DAO_FACTORY_DEFINE);
    	return (propName!=null)?propName:DEFAULT_DAO_DEFINE;
    }
    
    protected FactoryDAO() {
        super();    	
    }
    
    /**
     * @return
     */
    private static FactoryDAO getDAOFactory() {
        String factoryName=Config.getInstance().getProperty(ConfigKey.DAO_FACTORY);
        FactoryDAO factory=null;
        if (factoryName==null) {
            factory = new FactoryDAO();
        } else {
            try {
                Class factoryClass=Class.forName(factoryName);
                factory=(FactoryDAO)factoryClass.newInstance();
            } catch (Exception e) {
                log.error("fail to create DAO factory "+factoryName,e);
                factory=new FactoryDAO();
            }
        }        
        return factory;
    }
}
