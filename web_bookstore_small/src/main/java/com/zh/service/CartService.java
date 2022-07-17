package com.zh.service;

import com.zh.bean.Book;
import com.zh.bean.Cart;

public interface CartService {

	/**
	 * ���һ���鵽���ﳵ
	 * @param cart
	 * @param book
	 * @return
	 */
	String addBook(Cart cart, Book book);

	/**
	 * ɾ��һ��������
	 * @param cart
	 * @param bookid
	 */
	void deleteItem(Cart cart, String bookid);

	/**
	 * ����ĳ��������
	 * @param cart
	 * @param bookid
	 * @param count
	 * @return
	 */
	String updateItem(Cart cart, String bookid, int count);

	/**
	 * ��չ��ﳵ
	 * @param cart
	 */
	void clear(Cart cart);
	
}
