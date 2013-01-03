/*
 * Created on 2003. 10. 23.
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.ktf.iss.taglib;

import javax.servlet.jsp.tagext.TagSupport;
import javax.servlet.jsp.JspException;
import java.lang.reflect.*;
import com.ktf.iss.etc.util.Calculator;

/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class GetUserDefinedField extends TagSupport {

	/**
	 * 데이터를 가지고 있는 객체명
	 */
	private String name;

	/**
	 * 표현식 문자열
	 */ 
	private String expression;

	/**
	 * 표현식 문자열을 가지고 있는 객체명
	 */  
	private String expressionName;

	/**
	 * 표현식 문자열을 가지고 있는 객체의 변수명
	 */
	private String expressionProperty;

	/**
	 * 결과 포맷
	 */
	private String format;

	/**
	 * 포맷 문자열을 가지고 있는 객체명
	 */  
	private String formatName;

	/**
	 * 포맷 문자열을 가지고 있는 객체의 변수명
	 */
	private String formatProperty;
	
	/**
	 * 포맷 문자열을 가지고 있는 객체의 변수명
	 */
	private String postfix;

	/**
	 * 
	 */
	public GetUserDefinedField() {
		super();
	}

	public int doStartTag() throws JspException{
		
		String result = "0";
		Object dto = null;
		String expr = "";

		try {
			if(expression != null)
				expr = expression;
			else if(expressionName != null && expressionProperty != null){
				expr = getProperty(expressionName, expressionProperty);
			}

			dto = pageContext.findAttribute(name);
			if(dto != null) {
				expr = parseExpression(dto, expr);
				result = (new Calculator(expr)).evaluate();
				if(formatName != null && formatProperty != null) {
					format = getProperty(formatName, formatProperty);
				}
			
				if(format != null) {
					java.text.DecimalFormat df = new java.text.DecimalFormat(format);
					double doubleValue = Double.parseDouble(result);
					result = df.format(doubleValue);
				}
			}

			pageContext.getOut().print(result);
		}catch(Exception e){
			try {
				pageContext.getOut().print("0");
			} catch (Exception e1) {}
			e.printStackTrace();	
		}
		return SKIP_BODY;
	}//end of doStartTag

	private String parseExpression(Object dto, String expr) throws Exception {
		int start = expr.indexOf("${");
		while(start > -1) {
			int end = expr.indexOf('}', start);
			if(end > start) {
				String fieldName = expr.substring(start+2, end).trim();
				String value = getValue(dto, fieldName);
				String expTmp1 = "";
				String expTmp2 = "";
				if(start>0)
					expTmp1 = expr.substring(0, start);
				if(end < expr.length())
					expTmp2 = expr.substring(end+1);
				expr = expTmp1 + value + expTmp2;
			}
			start = expr.indexOf("${", start+2);
		}

		return expr;
	}
	
	private String getValue(Object dto, String name) throws Exception {
		Class[] paramType = {String.class};
		Method method = dto.getClass().getMethod("getNumberValue", paramType);
		if(postfix != null && postfix.length() > 0)
			name = name+postfix;
		Object[] param = {name};
		return ((Double)method.invoke(dto, param)).toString();
	}
	
	private String getProperty(String name, String property) throws Exception {
		Object obj = pageContext.findAttribute(name);
		String methodName = "get" + property.substring(0, 1).toUpperCase()
						 + property.substring(1);
		Method method = obj.getClass().getMethod(methodName, new Class[0]);
		return (String)method.invoke(obj, new Object[0]);						
	}
	
	/**
	 * @return
	 */
	public String getExpressionProperty() {
		return expressionProperty;
	}

	/**
	 * @return
	 */
	public String getExpression() {
		return expression;
	}

	/**
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param string
	 */
	public void setExpressionName(String string) {
		expressionName = string;
	}

	/**
	 * @param string
	 */
	public void setExpressionProperty(String string) {
		expressionProperty = string;
	}

	/**
	 * @param string
	 */
	public void setExpression(String string) {
		expression = string;
	}

	/**
	 * @param string
	 */
	public void setName(String string) {
		name = string;
	}

	/**
	 * @return
	 */
	public String getExpressionName() {
		return expressionName;
	}

	/**
	 * @return
	 */
	public String getFormat() {
		return format;
	}

	/**
	 * @param string
	 */
	public void setFormat(String string) {
		format = string;
	}

	/**
	 * @return
	 */
	public String getFormatName() {
		return formatName;
	}

	/**
	 * @return
	 */
	public String getFormatProperty() {
		return formatProperty;
	}

	/**
	 * @param string
	 */
	public void setFormatName(String string) {
		formatName = string;
	}

	/**
	 * @param string
	 */
	public void setFormatProperty(String string) {
		formatProperty = string;
	}

	/**
	 * @return
	 */
	public String getPostfix() {
		return postfix;
	}

	/**
	 * @param string
	 */
	public void setPostfix(String string) {
		postfix = string;
	}

}
