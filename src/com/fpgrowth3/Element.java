package com.fpgrowth3;

public class Element {
	
	String type = null;
	String value = null;
	
	public Element(String type, String value) {
		super();
		this.type = type;
		this.value = value;
	}
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
}
