package com.zh.service;

import java.util.List;

import com.zh.bean.Book;
import com.zh.bean.ConditionBook;
import com.zh.bean.Page;
import com.zh.bean.PageCondition;

public interface BookService {

	/**
	 * ���һ����
	 * @param book
	 */
	public void addBook(Book book);
	/**
	 * �õ����е���
	 * @return
	 */
	public List<Book> getAllBooks();
	/**
	 * �õ���Ӧ��һ����
	 * @param id
	 * @return
	 */
	public Book getBookById(String id);
	/**
	 * ɾ����Ӧ��һ����
	 * @param id
	 */
	public void deleteBookById(String id);
	/**
	 * ���¶�Ӧ��һ����
	 * @param book
	 */
	public void updateBook(Book book);

	/**
	 * �õ���ҳ����
	 * @param conditionBook
	 * @return
	 */
	public Page<Book> getPage(ConditionBook conditionBook);
	
}
