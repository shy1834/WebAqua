package com.edsk.framework.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * StringUtil
 * 
 * @author mksong
 */
public class StringUtil {
	
	/**
	 * String 개체가 Null인지를 체크한다.
	 * @param str
	 * @return
	 */
	public static boolean isNull(String str) {
        return (str==null || str.trim().length()==0 || "null".equals(str));
    }
    /**
     * 문자열내에서 지정된 char의 수를 조회한다.
     */
    public static int getCount(String str,char c) {
        int count=0;
        if (str!=null) {
            int strLength=str.length();
            for (int i=0;i<strLength;i++) {
                if (str.charAt(i)==c) {
                    count++;
                }
            }
            if (str.charAt(strLength-1)!=c) {
                count++;
            }
        }
        return count;
    }

    /**
     * sep로 구분된 문자열을 String[]로 변환한다.
     */
    public static String[] toMultiString(String str,char sep) {
        String[] array=null;
        if (str!=null) {
            str=str.trim();
            int strLength=str.length();
            int count=getCount(str,sep);
            int startIndex=0;
            if (str.charAt(strLength-1)==sep) {
                count++;
            }
            array=new String[count];

            for (int i=0;i<count;i++) {
                int endIndex=str.indexOf(sep,startIndex);
                if (endIndex<startIndex) {
                    endIndex=strLength;
                }
                array[i]=str.substring(startIndex,endIndex).trim();
                startIndex=endIndex+1;
            }
        }
        return array;
    }

    /**
     * 문자열 배열을 sep로 구분된 단일 문자열로 변환한다.
     */
    public static String toSingleString(String[] str,char sep) {
        if (str!=null) {
            StringBuffer sb=new StringBuffer();
            for (int i=0;i<str.length-1;i++) {
                sb.append(str[i]);
                sb.append(sep);
            }
            sb.append(str[str.length-1]);
            return sb.toString();
        }
        return null;
    }

    /**
     * Digest 알고리즘을 수행한다. 
     */
    public static String getDigest(String str,String digest,String charset) {
        try {
            MessageDigest md=MessageDigest.getInstance(digest);
            try {
                byte[] raw=str.getBytes(charset);
                byte[] digested=md.digest(raw);
                return getHexaEncoding(digested,charset);
            } catch (UnsupportedEncodingException e) {
            }            
        } catch (NoSuchAlgorithmException e) {
        }        
        return str;
    }

    private static byte[] hexaMap =
            {0x30,0x31,0x32,0x33,0x34,0x35,0x36,0x37,0x38,0x39,0x61,0x62,0x63,0x64,0x65,0x66};

    protected static byte[] getHexaEncodingBytes(byte[] src) {
        byte[] buffer=new byte[src.length*2];
        int index=0;
        for (int i=0;i<src.length;i++) {
            buffer[index++]=hexaMap[((src[i]&0xf0)>>4)];
            buffer[index++]=hexaMap[(src[i]&0x0f)];
        }
        return buffer;
    }

    protected static String getHexaEncoding(byte[] src,String charSet) 
            throws UnsupportedEncodingException {
        byte[] strBytes=getHexaEncodingBytes(src);
        return new String(strBytes,charSet);
    }

    protected static String getHexaEncoding(byte[] src) { 
        byte[] strBytes=getHexaEncodingBytes(src);
        return new String(strBytes);
    }
}
