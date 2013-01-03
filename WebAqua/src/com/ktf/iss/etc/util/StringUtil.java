/*
 * Created on 2003. 10. 23.
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.ktf.iss.etc.util;

/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class StringUtil {

	/**
	 * 
	 */
	public StringUtil() {
		super();
	}
	
	public static String replace(String source, String toReplace, String replacement) {
		if (source.indexOf(toReplace) > -1) {
			StringBuffer sb = new StringBuffer();
			int ix = -1;
			while (( ix = source.indexOf(toReplace)) >= 0) {
				sb.append(source.substring(0,ix)).append(replacement);
				source = source.substring(ix + toReplace.length());
			}
			if (source.length() >= 1) {
				sb.append(source);
			}
			return sb.toString();
		} else {
			return source;
		}
	}
	
	public static int compareString(String str1, String str2) {

		int result = 0; 
		for(int i = 0; true;i++) {
			if(str1.length() <= i && str2.length() > i) {
				result = -1;
				break;				
			}
			if(str2.length() <= i && str1.length() > i) {
				result = 1;
				break;				
			}
			if(str2.length() == (i-1) && str1.length() == (i-1) 
				&& str1.charAt(i) == str2.charAt(i)) {
				result = 0;
				break;				
			}
			if(str1.charAt(i) > str2.charAt(i)) {
				result = 1;
				break;
			} else if(str1.charAt(i) < str2.charAt(i)) {
				result = -1;
				break;
			}
		}
		
		return result;
	}
}
