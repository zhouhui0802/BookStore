package com.zh.service;

import java.util.List;

import com.zh.bean.Book;
import com.zh.bean.ConditionBook;
import com.zh.bean.Page;
import com.zh.bean.PageCondition;

public interface BookService {

	/**
	 * 添加一本书
	 * @param book
	 */
	public void addBook(Book book);
	/**
	 * 得到所有的书
	 * @return
	 */
	public List<Book> getAllBooks();
	/**
	 * 得到对应的一本书
	 * @param id
	 * @return
	 */
	public Book getBookById(String id);
	/**
	 * 删除对应的一本书
	 * @param id
	 */
	public void deleteBookById(String id);
	/**
	 * 更新对应的一本书
	 * @param book
	 */
	public void updateBook(Book book);

	/**
	 * 得到分页对象
	 * @param conditionBook
	 * @return
	 */
	public Page<Book> getPage(ConditionBook conditionBook);
	
}
