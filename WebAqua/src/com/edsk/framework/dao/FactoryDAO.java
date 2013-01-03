package com.edsk.framework.dao;

import com.edsk.framework.Config;
import com.edsk.framework.ConfigKey;
import com.edsk.framework.ObjectFactory;

/**
 * DAOFactory<br>
 * DAO를 생성해 준다. 모든 DAO는 DAOFactory를 통해 생성해야 한다.<br>
 * <br>
 * dao의 key별 클래스 정의는 ssg.properties에 dao.defines로 정의된다.
 * dao.defines=/dao.properties<br>
 * 별도로 지정하지 않으면 CLASSPATH내에서 /dao.properties를 검색하게 된다.<br>
 * <br> 
 * 
 * @author mksong
 */
public class FactoryDAO extends ObjectFactory {
	final static String DEFAULT_DAO_DEFINE="/dao.properties";    
    final static FactoryDAO instance =  getDAOFactory();

    /**
     * DAOFactory 인스턴스를 조회한다.
     * @return DAOFactory 인스턴스
     */    
    public static FactoryDAO getInstance() {
        return instance;
    }
    
    /**
     * key에 해당하는 DAO의 instance를 조회한다.
     * 
     * @param key 객체의 key
     * @return DAO의 instance
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
