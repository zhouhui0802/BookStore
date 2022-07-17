package com.zh.bean;

/*
#address ��ַ��
CREATE TABLE address
(
id VARCHAR(40) PRIMARY KEY,#����
NAME VARCHAR(20),#�ջ�������
location VARCHAR(100),#��ַ
cellphone VARCHAR(20),#�绰
usersid VARCHAR(40),#����Ӧ�û���id

CONSTRAINT usersid_FK FOREIGN KEY(usersid) REFERENCES users(id)
);
*/

public class Address {

	private String id;
	private String name;
	private String location;
	private String cellphone;
	private String usersid;

	public Address(String id, String name, String location, String cellphone,
			String usersid) {
		super();
		this.id = id;
		this.name = name;
		this.location = location;
		this.cellphone = cellphone;
		this.usersid = usersid;
	}

	public Address() {
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

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getCellphone() {
		return cellphone;
	}

	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}

	public String getUsersid() {
		return usersid;
	}

	public void setUsersid(String usersid) {
		this.usersid = usersid;
	}

	@Override
	public String toString() {
		return "Address [id=" + id + ", name=" + name + ", location="
				+ location + ", cellphone=" + cellphone + ", usersid="
				+ usersid + "]";
	}
}
