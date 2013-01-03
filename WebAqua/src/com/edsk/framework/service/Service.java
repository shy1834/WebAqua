package com.edsk.framework.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Service<br>
 * ������ ��ü�� root class<br>
 * ServiceFactory�κ��� Service�� �ν��Ͻ��� �����ϴ� ���� �Ѵ�.<br>
 * <br>
 * lookupInstance�� ���� ������ ���񽺴� ���� �ϳ��� �ν��Ͻ��� �����ϰ� �ȴ�.<br>
 * ���� ���� Ŭ������ ���¸� �����ؼ��� �ȵȴ�.<br>
 * <br>
 *  * 
 * @author mksong
 */
public class Service {
    final static protected ServiceFactory factory=ServiceFactory.getInstance();
    static protected Log log=LogFactory.getLog(Service.class);    

    /**
     * ServiceFactory�� ���� serviceName���� ������ �ν��Ͻ��� ��ȸ�Ѵ�. 
     * @param serviceName ��ȸ�� ������ �̸�
     * @return serviceName���� ������ ���� ��ü
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
