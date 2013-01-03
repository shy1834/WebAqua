package com.edsk.framework.service;

import java.util.Locale;

/**
 * LocaleBaseService<br>
 * ������ ������ �����ϴ� ���񽺸� �����Ѵ�.<br>
 * Service�� ��� ���� ���� Ŭ������ ���¸� �����ϸ� �ȵ�����,
 * LocaleBaseService�� ���������� Locale�� ���·� ���� �� �ֿ���, 
 * �ν��Ͻ��� �����Ϻ��� Caching�ȴ�.<br>
 * �� �����ϴ� �ϳ��� �ν��Ͻ��� �����ȴ�.
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
     * @return �����ڵ� (2�ڸ�)
     */
    public String getCountry() {
        return getLocale().getCountry();
    }

    /**
     * @return ����ڵ� (2�ڸ�)
     */
    public String getLanguage() {
        return getLocale().getLanguage();
    }

    /**
     * @return ��� Ȯ�� ���� (2�ڸ�)
     */
    public String getVariant() {
        return getLocale().getVariant();
    }

    /**
     * ServiceFactory�� ���� serviceName, locale�� ������ �ν��Ͻ��� ��ȸ�Ѵ�. 
     * @param serviceName ��ȸ�� ������ �̸�
     * @param locale ��ȸ�� ������ Locale
     * @return serviceName���� ������ ���� ��ü
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
