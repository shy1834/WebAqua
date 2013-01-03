/*
 * Created on 2003-09-18
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.ktf.iss.taglib;

import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;

import java.lang.reflect.*;
import com.ktf.iss.statistics.*;

/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class GetCodeValue extends TagSupport{
	
	
	private String name;			// 데이터를 가지고 있는 객체명
	private String property;		// 테이터 칼럼명
	private String columnName;		// 칼럼 네임을 가지고 있는 빈의 객체 변수명
	private String columnProperty;	// 칼럼 네임을 가지고 있는 빈의 속성명
	private String codeCategory;
	private String categoryName;
	private String categoryProperty;
	
	public GetCodeValue(){
	}
	
	public void setName(String name){
		this.name = name;		
	}//end of setName();
	
	public void setProperty(String property){
		this.property = property;
	}//end of setId
	
	public String getName(){
		return name;
	}
	
	public String getProperty(){
		return property;
	}
	
	public String getResult(){

		String result = null;
		Object dto = null;
		String fieldName = "";
		String category = "";
		
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

			if(codeCategory != null)
				category = codeCategory;
			else if(categoryName != null && categoryProperty != null){
				Object colObject = pageContext.findAttribute(categoryName);
				String methodName = "get" + categoryProperty.substring(0, 1).toUpperCase()
								 + categoryProperty.substring(1);
				Method method = colObject.getClass().getMethod(methodName, new Class[0]);
				category = (String)method.invoke(colObject, new Object[0]);				
			}

			dto = pageContext.findAttribute(name);
			if(dto != null) {
				Class[] paramType = {String.class};
				Method method = dto.getClass().getMethod("getValue", paramType);
				Object[] param = {fieldName};
				result = (String)method.invoke(dto, param);
				CodeCacheManager ccManager = CodeCacheManager.getInstance();
				String tmp = ccManager.getCodeName(category, result);
				if(tmp != null)
					result = tmp;
			}
		} catch(Throwable t) {
			//t.printStackTrace();
		}
		return result;
	}//end fo getResult
	
	public int doStartTag() throws JspException{
		try{
			pageContext.getOut().print(getResult());
		}catch(Exception e){
			//e.printStackTrace();	
		}
		return SKIP_BODY;
	}//end of doStartTag
	

	public int doEndTag() throws JspException{
			return SKIP_BODY;
	}//end of doEndTag;
	
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
	 * @return
	 */
	public String getCodeCategory() {
		return codeCategory;
	}

	/**
	 * @param string
	 */
	public void setCodeCategory(String string) {
		codeCategory = string;
	}

	/**
	 * @return
	 */
	public String getCategoryName() {
		return categoryName;
	}

	/**
	 * @return
	 */
	public String getCategoryProperty() {
		return categoryProperty;
	}

	/**
	 * @param string
	 */
	public void setCategoryName(String string) {
		categoryName = string;
	}

	/**
	 * @param string
	 */
	public void setCategoryProperty(String string) {
		categoryProperty = string;
	}

}//end of class