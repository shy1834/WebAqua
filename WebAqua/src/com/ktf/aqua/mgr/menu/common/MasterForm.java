package com.ktf.aqua.mgr.menu.common;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class MasterForm extends ActionForm {
	private String dev_code;
	private String dev_desc;
	private String data_dev_code;
	private String menuId2;
	private String shortMenu2;
	private String imagePath2;
	private String imageSize2;
	private String path;
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getData_dev_code() {
		return data_dev_code;
	}
	public void setData_dev_code(String data_dev_code) {
		this.data_dev_code = data_dev_code;
	}
	public String getDev_code() {
		return dev_code;
	}
	public void setDev_code(String dev_code) {
		this.dev_code = dev_code;
	}
	public String getDev_desc() {
		return dev_desc;
	}
	public void setDev_desc(String dev_desc) {
		this.dev_desc = dev_desc;
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
	/**
	 * @return Returns the dev_code.
	 */
	public String getDevCode() {
		return dev_code;
	}
	/**
	 * @param dev_code The dev_code to set.
	 */
	public void setDevCode(String dev_code) {
		this.dev_code = dev_code;
	}
	/**
	 * @return Returns the dev_desc.
	 */
	public String getDevDesc() {
		return dev_desc;
	}
	/**
	 * @param dev_desc The dev_desc to set.
	 */
	public void setDevDesc(String dev_desc) {
		this.dev_desc = dev_desc;
	}
	/**
	 * @return Returns the data_dev_code.
	 */
	public String getDataDevCode() {
		return data_dev_code;
	}
	/**
	 * @param data_dev_code The data_dev_code to set.
	 */
	public void setDataDevCode(String data_dev_code) {
		this.data_dev_code = data_dev_code;
	}
}
