package com.ktf.aqua.mgr.menu.form;

public class SelectForm {
	private String id = null;
	private String text = null;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	
	public SelectForm(String id,String text){
		this.id = id;
		this.text = text;
	}
	
	public SelectForm(){
		
	}
	
	
}
