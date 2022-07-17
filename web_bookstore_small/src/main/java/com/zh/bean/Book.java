package com.zh.bean;

/*
CREATE TABLE books 
(
id VARCHAR(40) PRIMARY KEY , #����
NAME VARCHAR(50), #����
author VARCHAR(20), #����
price FLOAT, #�۸�
salesamount INT(11) NOT NULL DEFAULT 0,#��������
storenumber INT(11) NOT NULL, #�������
imagepath VARCHAR(100), #ͼ����Ƭ�洢·��
categorysid VARCHAR(40), #��ͼ���Ӧ�ķ���ID(��һ�����)
CONSTRAINT categorysid_FK FOREIGN KEY(categorysid) REFERENCES categorys(id)
);
*/

public class Book {

	private String id;
	private String name;
	private String author;
	private float price;
	private int salesamount;
	private int storenumber;
	private String imagepath;
	private String categorysid;

	public Book(String id, String name, String author, float price,
			int salesamount, int storenumber, String imagepath,
			String categorysid) {
		super();
		this.id = id;
		this.name = name;
		this.author = author;
		this.price = price;
		this.salesamount = salesamount;
		this.storenumber = storenumber;
		this.imagepath = imagepath;
		this.categorysid = categorysid;
	}

	public Book() {
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

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public int getSalesamount() {
		return salesamount;
	}

	public void setSalesamount(int salesamount) {
		this.salesamount = salesamount;
	}

	public int getStorenumber() {
		return storenumber;
	}

	public void setStorenumber(int storenumber) {
		this.storenumber = storenumber;
	}

	public String getImagepath() {
		return imagepath;
	}

	public void setImagepath(String imagepath) {
		this.imagepath = imagepath;
	}

	public String getCategorysid() {
		return categorysid;
	}

	public void setCategorysid(String categorysid) {
		this.categorysid = categorysid;
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", name=" + name + ", author=" + author
				+ ", price=" + price + ", salesamount=" + salesamount
				+ ", storenumber=" + storenumber + ", imagepath=" + imagepath
				+ ", categorysid=" + categorysid + "]";
	}
}
