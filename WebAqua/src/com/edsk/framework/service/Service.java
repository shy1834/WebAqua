package com.edsk.framework.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Service<br>
 * 서비스의 객체의 root class<br>
 * ServiceFactory로부터 Service의 인스턴스를 생성하는 일을 한다.<br>
 * <br>
 * lookupInstance를 통해 생성된 서비스는 오직 하나의 인스턴스만 유지하게 된다.<br>
 * 따라서 서비스 클래스는 상태를 유지해서는 안된다.<br>
 * <br>
 *  * 
 * @author mksong
 */
public class Service {
    final static protected ServiceFactory factory=ServiceFactory.getInstance();
    static protected Log log=LogFactory.getLog(Service.class);    

    /**
     * ServiceFactory로 부터 serviceName으로 지정된 인스턴스를 조회한다. 
     * @param serviceName 조회할 서비스의 이름
     * @return serviceName으로 지정된 서비스 객체
     */
    static protected Service lookupInstance(String serviceName) {
        Service service=null;
        log.debug("lookup service from ServiceFactory for "+serviceName);
        if ((service=factory.getService(serviceName))==null) {
            log.warn("Can't create Service instance for "+serviceName);
        }
        return service;
    }

}
