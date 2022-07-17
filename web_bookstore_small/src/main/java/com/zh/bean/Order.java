package com.zh.bean;

import java.util.Date;

/*
#orders 订单表  cart
CREATE TABLE orders 
(
id VARCHAR(40) PRIMARY KEY, #主键
number VARCHAR(50),#订单号
STATUS BOOLEAN DEFAULT FALSE,#状态, 标识是否发货
price FLOAT,#价格
ordertime TIMESTAMP,#下单时间
usersid VARCHAR(40),#对应用户的ID
addressid VARCHAR(40),#对应的地址ID

CONSTRAINT usersid_FK1 FOREIGN KEY(usersid) REFERENCES users(id),
CONSTRAINT addressid_FK FOREIGN KEY(addressid) REFERENCES address(id)
);
*/

public class Order {

	private String id;
	private String number;
	private boolean status;
	private float price;
	private Date ordertime;
	private String usersid;
	private String addressid;

	
	
	
	public Order(String id, String number, float price, String usersid,
			String addressid) {
		super();
		this.id = id;
		this.number = number;
		this.price = price;
		this.usersid = usersid;
		this.addressid = addressid;
	}

	public Order(String id, String number, boolean status, float price,
			Date ordertime, String usersid, String addressid) {
		super();
		this.id = id;
		this.number = number;
		this.status = status;
		this.price = price;
		this.ordertime = ordertime;
		this.usersid = usersid;
		this.addressid = addressid;
	}

	public Order() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean sTATUS) {
		status = sTATUS;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public Date getOrdertime() {
		return ordertime;
	}

	public void setOrdertime(Date ordertime) {
		this.ordertime = ordertime;
	}

	public String getUsersid() {
		return usersid;
	}

	public void setUsersid(String usersid) {
		this.usersid = usersid;
	}

	public String getAddressid() {
		return addressid;
	}

	public void setAddressid(String addressid) {
		this.addressid = addressid;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", number=" + number + ", STATUS=" + status
				+ ", price=" + price + ", ordertime=" + ordertime
				+ ", usersid=" + usersid + ", addressid=" + addressid + "]";
	}

}
