package com.edsk.framework.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * DateUtil
 * 
 * @author mksong
 */
public class DateUtil {
    final public static String SEP="-";
    final public static int FORMAT_UNKNOWN=0;
    final public static int FORMAT_YYYYMMDD=123;
    final public static int FORMAT_MMDDYYYY=231;
    final public static int FORMAT_DDMMYYYY=321;

    public static long getLong(int year,int month,int date) {
        Date d=getDate(year,month,date);
        return d.getTime();
    }

    public static Date getDate(int year,int month,int date) {
        Calendar cal=getCalendar(year,month,date);
        return cal.getTime();
    }

    public static Calendar getCalendar(int year,int month,int date) {
        Calendar cal=Calendar.getInstance();
        cal.clear();
        cal.set(Calendar.YEAR,year);
        cal.set(Calendar.MONTH,month);
        cal.set(Calendar.DATE,date);
        return cal;
    }

    public static long getLong(String dateStr,int format) {        
        Date d=getDate(dateStr,format);
        return d.getTime();
    }

    public static Date getDate(String dateStr,int format) {
        Calendar cal=getCalendar(dateStr,format);
        return cal.getTime();
    }

    /**
     * @param dateStr
     * @param format
     * @return
     */
    public static Calendar getCalendar(String dateStr, int format) {
        Calendar cal=null;
        switch(format) {
            case FORMAT_YYYYMMDD :
                cal=getCalendarYYYYMMDD(dateStr);
            break;
            case FORMAT_MMDDYYYY :
                cal=getCalendarMMDDYYYY(dateStr);
            break;
            case FORMAT_DDMMYYYY :
                cal=getCalendarDDMMYYYY(dateStr);
            break;
        }
        return cal;
    }

    public static long getLong(String dateStr,Locale locale) {        
        return getLong(dateStr,getDateFormat(locale));
    }

    public static Date getDate(String dateStr,Locale locale) {
        return getDate(dateStr,getDateFormat(locale));
    }

    public static Calendar getCalendar(String dateStr, Locale locale) {
        return getCalendar(dateStr,getDateFormat(locale));
    }
    
    public static Calendar getCalendarYYYYMMDD(String YYYYMMDD) throws NumberFormatException {
        Calendar cal=Calendar.getInstance();
        int index=0;
        int l=SEP.length();
        String yyyyStr=YYYYMMDD.substring(index,index+4); 
        index+=(4+l);
        String mmStr=YYYYMMDD.substring(index,index+2);
        index+=(2+l);
        String ddStr=YYYYMMDD.substring(index,index+2);
        int yyyy=Integer.parseInt(yyyyStr);
        int mm=Integer.parseInt(mmStr);
        int dd=Integer.parseInt(ddStr);
        cal.clear();
        cal.set(yyyy,mm-1,dd);
        return cal;
    }

    public static Calendar getCalendarMMDDYYYY(String MMDDYYYY) throws NumberFormatException {
        Calendar cal=Calendar.getInstance();
        int index=0;
        int l=SEP.length();
        String mmStr=MMDDYYYY.substring(index,index+2);
        index+=(2+l);
        String ddStr=MMDDYYYY.substring(index,index+2);
        index+=(2+l);
        String yyyyStr=MMDDYYYY.substring(index,index+4);
        int yyyy=Integer.parseInt(yyyyStr);
        int mm=Integer.parseInt(mmStr);
        int dd=Integer.parseInt(ddStr);
        cal.clear();
        cal.set(yyyy,mm-1,dd);
        return cal;
    }

    public static Calendar getCalendarDDMMYYYY(String DDMMYYYY) throws NumberFormatException {
        Calendar cal=Calendar.getInstance();
        int index=0;
        int l=SEP.length();
        String ddStr=DDMMYYYY.substring(index,index+2);
        index+=(2+l);
        String mmStr=DDMMYYYY.substring(index,index+2);
        index+=(2+l);
        String yyyyStr=DDMMYYYY.substring(index,index+4);
        int yyyy=Integer.parseInt(yyyyStr);
        int mm=Integer.parseInt(mmStr);
        int dd=Integer.parseInt(ddStr);
        cal.clear();
        cal.set(yyyy,mm-1,dd);
        return cal;
    }

    public static int getDateFormat(Locale locale) {
        String datePattern=((SimpleDateFormat)DateFormat.getDateInstance(DateFormat.MEDIUM,locale)).toPattern();
        DatePatternTokenizer st=new DatePatternTokenizer(datePattern);
        int formatCode=0;
        while (st.hasMoreTokens()) {
            String token=st.nextToken();
            if ("yy".equals(token) || "yyyy".equals(token)) {
                formatCode=formatCode*10+1;
            } else if ("M".equals(token) || "MM".equals(token) || "MMM".equals(token) || "MMMM".equals(token)) {
                formatCode=formatCode*10+2;
            } else if ("d".equals(token) || "dd".equals(token)) {
                formatCode=formatCode*10+3;
            }
        }
        return formatCode;
    }
}
