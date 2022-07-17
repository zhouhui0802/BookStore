package com.zh.bean;

/*
 * 购物项信息的封装类
 */

public class CartItem {

	private Book book;
	private int count;

	public CartItem(Book book, int count) {
		super();
		this.book = book;
		this.count = count;
	}

	public CartItem() {
		super();
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public float getItemPrice() {
		
		return book.getPrice()*count;
	}

	@Override
	public String toString() {
		return "CartItem [book=" + book + ", count=" + count + "]";
	}
}
