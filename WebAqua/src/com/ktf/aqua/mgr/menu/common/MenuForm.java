/*
 * Created on 2006. 2. 8.
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.ktf.aqua.mgr.menu.common;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 * @author redfox
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class MenuForm extends ActionForm{
	
	private String levelDepth;
	private String limitLevelDepth;
	private String menuId;    
	private String menuName;    
	private String shortMenu;
	private String path;     
	private String viewSort;   
	private String useyn;     
	private String remark;    
	private String regDate;  
	private String regId;    
	private String updateDate; 
	private String updateId; 
	private String imagePath;
	private String imagePath1;
	private String devName;
	private String imageSize;
		
	private String first_Name;
	private String first_Image;
	private String first_Size;
	private String second_Name;
	private String second_Image;
	private String second_Size;
	private String third_Name;
	private String third_Image;
	private String third_Size;
	private String menuId2;
	private String shortMenu2;
	private String imagePath2;
	private String imageSize2;
	/*
	 * 사용자로부터 전달되는 값들의 유효성을 서버측에 전달한다.
	 * @see org.apache.struts.action.ActionForm#validate(org.apache.struts.action.ActionMapping, javax.servlet.http.HttpServletRequest)
	 */
	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request){
		return null;
	}
	
	public MenuForm(){
		this.levelDepth ="";
		this.limitLevelDepth = "";
		this.menuId ="";    
		this.menuName ="";  
		this.shortMenu = "";
		this.path ="";     
		this.viewSort ="";   
		this.useyn ="";     
		this.remark ="";    
		this.regDate ="";  
		this.regId ="";    
		this.updateDate =""; 
		this.updateId ="";
		this.imagePath = "";
		this.imagePath1 = "";
		this.devName = "";
		this.imageSize = "";
				
		this.first_Name = "";
		this.first_Image = "";
		this.first_Size = "";
		this.second_Name = "";
		this.second_Image = "";
		this.second_Size = "";
		this.third_Name = "";
		this.third_Image = "";
		this.third_Size = "";
	}
	
	public void reset(ActionMapping mapping, HttpServletRequest request){
		this.levelDepth ="";
		this.limitLevelDepth = "";
		this.menuId ="";    
		this.menuName ="";    
		this.shortMenu ="";    
		this.path ="";     
		this.viewSort ="";   
		this.useyn ="";     
		this.remark ="";    
		this.regDate ="";  
		this.regId ="";    
		this.updateDate =""; 
		this.updateId ="";
		this.imagePath = "";
		this.imagePath1 = "";
		this.devName = "";
		this.imageSize = "";
				
		this.first_Name = "";
		this.first_Image = "";
		this.first_Size = "";
		this.second_Name = "";
		this.second_Image = "";
		this.second_Size = "";
		this.third_Name = "";
		this.third_Image = "";
		this.third_Size = "";
	}

	/**
	 * @return Returns the levelDepth.
	 */
	public String getLevelDepth() {
		return levelDepth;
	}
	/**
	 * @param levelDepth The levelDepth to set.
	 */
	public void setLevelDepth(String levelDepth) {
		this.levelDepth = levelDepth;
	}
	/**
	 * @return Returns the menuId.
	 */
	public String getMenuId() {
		return menuId;
	}
	/**
	 * @param menuId The menuId to set.
	 */
	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}
	/**
	 * @return Returns the menuName.
	 */
	public String getMenuName() {
		return menuName;
	}
	/**
	 * @param menuName The menuName to set.
	 */
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	/**
	 * @return Returns the shortMenu.
	 */
	public String getShortMenu() {
		return shortMenu;
	}
	/**
	 * @param shortMenu The shortMenu to set.
	 */
	public void setShortMenu(String shortMenu) {
		this.shortMenu = shortMenu;
	}
	/**
	 * @return Returns the path.
	 */
	public String getPath() {
		return path;
	}
	/**
	 * @param path The path to set.
	 */
	public void setPath(String path) {
		this.path = path;
	}
	/**
	 * @return Returns the regDate.
	 */
	public String getRegDate() {
		return regDate;
	}
	/**
	 * @param regDate The regDate to set.
	 */
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	/**
	 * @return Returns the regId.
	 */
	public String getRegId() {
		return regId;
	}
	/**
	 * @param regId The regId to set.
	 */
	public void setRegId(String regId) {
		this.regId = regId;
	}
	/**
	 * @return Returns the remark.
	 */
	public String getRemark() {
		return remark;
	}
	/**
	 * @param remark The remark to set.
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	/**
	 * @return Returns the updateDate.
	 */
	public String getUpdateDate() {
		return updateDate;
	}
	/**
	 * @param updateDate The updateDate to set.
	 */
	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}
	/**
	 * @return Returns the updateId.
	 */
	public String getUpdateId() {
		return updateId;
	}
	/**
	 * @param updateId The updateId to set.
	 */
	public void setUpdateId(String updateId) {
		this.updateId = updateId;
	}
	/**
	 * @return Returns the useyn.
	 */
	public String getUseyn() {
		return useyn;
	}
	/**
	 * @param useyn The useyn to set.
	 */
	public void setUseyn(String useyn) {
		this.useyn = useyn;
	}
	/**
	 * @return Returns the viewSort.
	 */
	public String getViewSort() {
		return viewSort;
	}
	/**
	 * @param viewSort The viewSort to set.
	 */
	public void setViewSort(String viewSort) {
		this.viewSort = viewSort;
	}
	/**
	 * @return Returns the imagePath.
	 */
	public String getImagePath() {
		return imagePath;
	}
	/**
	 * @param imagePath The imagePath to set.
	 */
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	/**
	 * @return Returns the imagePath1.
	 */
	public String getImagePath1() {
		return imagePath1;
	}
	/**
	 * @param imagePath1 The imagePath1 to set.
	 */
	public void setImagePath1(String imagePath1) {
		this.imagePath1 = imagePath1;
	}
	/**
	 * @return Returns the devName.
	 */
	public String getDevName() {
		return devName;
	}
	/**
	 * @param devName The devName to set.
	 */
	public void setDevName(String devName) {
		this.devName = devName;
	}
	/**
	 * @return Returns the imageSize.
	 */
	public String getImageSize() {
		return imageSize;
	}
	/**
	 * @param imageSize The imageSize to set.
	 */
	public void setImageSize(String imageSize) {
		this.imageSize = imageSize;
	}
	
	/**
	 * @return Returns the limitLevelDepth.
	 */
	public String getLimitLevelDepth() {
		return limitLevelDepth;
	}
	/**
	 * @param limitLevelDepth The limitLevelDepth to set.
	 */
	public void setLimitLevelDepth(String limitLevelDepth) {
		this.limitLevelDepth = limitLevelDepth;
	}
	
	/**
	 * @return Returns the first_Name.
	 */
	public String getFirst_Name() {
		return first_Name;
	}
	/**
	 * @param first_Name The first_Name to set.
	 */
	public void setFirst_Name(String first_Name) {
		this.first_Name = first_Name;
	}

	/**
	 * @return Returns the first_Image.
	 */
	public String getFirst_Image() {
		return first_Image;
	}
	/**
	 * @param first_Image The first_Image to set.
	 */
	public void setFirst_Image(String first_Image) {
		this.first_Image = first_Image;
	}

	/**
	 * @return Returns the first_Size.
	 */
	public String getFirst_Size() {
		return first_Size;
	}
	/**
	 * @param first_Size The first_Size to set.
	 */
	public void setFirst_Size(String first_Size) {
		this.first_Size = first_Size;
	}
	
	/**
	 * @return Returns the second_Name.
	 */
	public String getSecond_Name() {
		return second_Name;
	}
	/**
	 * @param second_Name The second_Name to set.
	 */
	public void setSecond_Name(String second_Name) {
		this.second_Name = second_Name;
	}

	/**
	 * @return Returns the second_Image.
	 */
	public String getSecond_Image() {
		return second_Image;
	}
	/**
	 * @param second_Image The second_Image to set.
	 */
	public void setSecond_Image(String second_Image) {
		this.second_Image = second_Image;
	}

	/**
	 * @return Returns the second_Size.
	 */
	public String getSecond_Size() {
		return second_Size;
	}
	/**
	 * @param second_Size The second_Size to set.
	 */
	public void setSecond_Size(String second_Size) {
		this.second_Size = second_Size;
	}
	
	/**
	 * @return Returns the third_Name.
	 */
	public String getThird_Name() {
		return third_Name;
	}
	/**
	 * @param third_Name The third_Name to set.
	 */
	public void setThird_Name(String third_Name) {
		this.third_Name = third_Name;
	}

	/**
	 * @return Returns the third_Image.
	 */
	public String getThird_Image() {
		return third_Image;
	}
	/**
	 * @param third_Image The third_Image to set.
	 */
	public void setThird_Image(String third_Image) {
		this.third_Image = third_Image;
	}

	/**
	 * @return Returns the third_Size.
	 */
	public String getThird_Size() {
		return third_Size;
	}
	/**
	 * @param second_Size The second_Size to set.
	 */
	public void setThird_Size(String third_Size) {
		this.third_Size = third_Size;
	}

	public String getImagePath2() {
		return imagePath2;
	}

	public void setImagePath2(String imagePath2) {
		this.imagePath2 = imagePath2;
	}

	public String getImageSize2() {
		return imageSize2;
	}

	public void setImageSize2(String imageSize2) {
		this.imageSize2 = imageSize2;
	}

	public String getMenuId2() {
		return menuId2;
	}

	public void setMenuId2(String menuId2) {
		this.menuId2 = menuId2;
	}

	public String getShortMenu2() {
		return shortMenu2;
	}

	public void setShortMenu2(String shortMenu2) {
		this.shortMenu2 = shortMenu2;
	}
}
