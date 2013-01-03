package com.edsk.framework.service;

import java.util.Locale;

/**
 * LocaleBaseService<br>
 * 로케일 정보를 유지하는 서비스를 정의한다.<br>
 * Service를 상속 받은 서비스 클래스는 상태를 유지하면 안되지만,
 * LocaleBaseService는 예외적으로 Locale을 상태로 가질 수 있여며, 
 * 인스턴스로 로케일별로 Caching된다.<br>
 * 즉 로케일당 하나의 인스턴스가 생성된다.
 * 
 * @author mksong
 */
public class LocaleBaseService extends Service {
	Locale locale=null;
	
    public LocaleBaseService() {
		setLocale(Locale.getDefault());
    }
	public LocaleBaseService(Locale locale) {
		setLocale(locale);
	}
    
	public void setLocale(Locale locale) {
        log.debug("set locale "+locale);
		this.locale=locale;
	}

	public Locale getLocale() {
		return locale;
	}	
	
    /**
     * @return 국가코드 (2자리)
     */
    public String getCountry() {
        return getLocale().getCountry();
    }

    /**
     * @return 언어코드 (2자리)
     */
    public String getLanguage() {
        return getLocale().getLanguage();
    }

    /**
     * @return 언어 확장 정보 (2자리)
     */
    public String getVariant() {
        return getLocale().getVariant();
    }

    /**
     * ServiceFactory로 부터 serviceName, locale로 지정된 인스턴스를 조회한다. 
     * @param serviceName 조회할 서비스의 이름
     * @param locale 조회할 서비스의 Locale
     * @return serviceName으로 지정된 서비스 객체
     */
    static protected LocaleBaseService lookupInstance(String serviceName,Locale locale) {
        LocaleBaseService service=null;
        log.debug("lookup service from ServiceFactory for "+serviceName+", "+locale);
        if ((service=factory.getService(serviceName,locale))==null) {
            log.warn("Can't create Service instance for "+serviceName+", "+locale);
        }
        return service;
    }
}
