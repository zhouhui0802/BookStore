package com.zh.bean;

/*
# orderitems 订单项 cartitem
CREATE TABLE orderitems
(
id VARCHAR(40) PRIMARY KEY,#主键
quantity INT,#数量
price FLOAT,#单价
ordersid VARCHAR(40),#对应订单的ID
booksid VARCHAR(40),#对应书的ID
CONSTRAINT ordersid_FK FOREIGN KEY(ordersid) REFERENCES orders(id),
CONSTRAINT booksid_FK FOREIGN KEY(booksid) REFERENCES books(id)
);
*/

public class OrderItem {

	private String id;
	private int quantity;
	private float price;
	private String ordersid;
	private String booksid;

	private Book book;

	public OrderItem(String id, int quantity, float price, String ordersid, String booksid) {
		super();
		this.id = id;
		this.quantity = quantity;
		this.price = price;
		this.ordersid = ordersid;
		this.booksid = booksid;
	}

	public OrderItem() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public String getOrdersid() {
		return ordersid;
	}

	public void setOrdersid(String ordersid) {
		this.ordersid = ordersid;
	}

	public String getBooksid() {
		return booksid;
	}

	public void setBooksid(String booksid) {
		this.booksid = booksid;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	@Override
	public String toString() {
		return "OrderItem [id=" + id + ", quantity=" + quantity + ", price=" + price + ", ordersid="
				+ ordersid + ", booksid=" + booksid + ", book=" + book + "]";
	}
	
}
