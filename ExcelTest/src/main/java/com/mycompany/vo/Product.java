package com.mycompany.vo;

public class Product {
	int no;
	String name;
	String code;
	String content;
	
	public Product(int no, String name, String code, String content) {
		super();
		this.no = no;
		this.name = name;
		this.code = code;
		this.content = content;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	@Override
	public String toString() {
		return "Product [no=" + no + ", name=" + name + ", code=" + code + ", content=" + content + "]";
	}
	
	
}
