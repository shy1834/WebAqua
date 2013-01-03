package com.ktf.aqua.mgr.menu.form;

public class SearchForm {
	private String menuLevel  = null;
	private String menuType   = null;
	private String upperCode  = null;
	private String detailCode = null;
	private String plfDevCode = null;
	
	public String getMenuLevel() {
		return menuLevel;
	}
	public void setMenuLevel(String menuLevel) {
		this.menuLevel = menuLevel;
	}
	public String getMenuType() {
		return menuType;
	}
	public void setMenuType(String menuType) {
		this.menuType = menuType;
	}
	public String getUpperCode() {
		return upperCode;
	}
	public void setUpperCode(String upperCode) {
		this.upperCode = upperCode;
	}
	public String getDetailCode() {
		return detailCode;
	}
	public void setDetailCode(String detailCode) {
		this.detailCode = detailCode;
	}
	public String getPlfDevCode() {
		return plfDevCode;
	}
	public void setPlfDevCode(String plfDevCode) {
		this.plfDevCode = plfDevCode;
	}
	
	public void clear(){
		this.menuLevel  = null;
		this.menuType   = null;
		this.upperCode  = null;
		this.detailCode = null;
		this.plfDevCode = null;
	}
}
