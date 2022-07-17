package com.zh.bean;

public class Category {

	private String id;
	private String name;

	public Category(String id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
	public Category(String name) {
		this.name = name;
	}

	public Category() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Category [id=" + id + ", name=" + name + "]";
	}
}
