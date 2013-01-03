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

/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class GetPreviousCompareValue extends TagSupport {

	private String name;			// 데이터를 가지고 있는 객체명
	private String property;		// 테이터 칼럼명
	private String columnName;		// 칼럼 네임을 가지고 있는 빈의 객체 변수명
	private String columnProperty;	// 칼럼 네임을 가지고 있는 빈의 속성명
	/**
	 * 
	 */
	public GetPreviousCompareValue() {
		super();
	}

	public int doStartTag() throws JspException{
		
		String result = null;
		Object dto = null;
		String fieldName = "";
		
		try {
			if(property != null)
				fieldName = property;
			else if(columnName != null && columnProperty != null){
				Object colObject = pageContext.findAttribute(columnName);
				String methodName = "get" + columnProperty.substring(0, 1).toUpperCase()
								 + columnProperty.substring(1);
				Method method = colObject.getClass().getMethod(methodName, new Class[0]);
				fieldName = (String)method.invoke(colObject, new Object[0]);				
			}

			dto = pageContext.findAttribute(name);
			if(dto != null) {
				Class[] paramType = {String.class};
				Method method = dto.getClass().getMethod("getNumberValue", paramType);
				Object[] param = {fieldName};
				Double curNum = (Double)method.invoke(dto, param);

				Object[] param1 = {"PC_"+fieldName};
				Double prevNum = (Double)method.invoke(dto, param1);
				
				result = String.valueOf(curNum.doubleValue() - prevNum.doubleValue());
			}

			pageContext.getOut().print(result);
		}catch(Exception e){
			e.printStackTrace();	
		}
		return SKIP_BODY;
	}//end of doStartTag

	/**
	 * @return
	 */
	public String getColumnName() {
		return columnName;
	}

	/**
	 * @return
	 */
	public String getColumnProperty() {
		return columnProperty;
	}

	/**
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return
	 */
	public String getProperty() {
		return property;
	}

	/**
	 * @param string
	 */
	public void setColumnName(String string) {
		columnName = string;
	}

	/**
	 * @param string
	 */
	public void setColumnProperty(String string) {
		columnProperty = string;
	}

	/**
	 * @param string
	 */
	public void setName(String string) {
		name = string;
	}

	/**
	 * @param string
	 */
	public void setProperty(String string) {
		property = string;
	}

}
