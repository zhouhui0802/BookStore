package com.zh.dao;

import java.util.List;

import com.zh.bean.Book;

public interface BookDao {

	/**
	 * 向books表中插入一条记录
	 * @param b
	 */
	void insert(Book b);
	
	/**
	 * 查询books表得到所有的记录, 返回一个List
	 * @return
	 */
	public List<Book> getAll();
	
	/**
	 * 根据id查询books表中对应记录, 返回book对象
	 * @param id
	 * @return
	 */
	public Book getById(String id);
	
	/**
	 * 更新book对象在books表中对应的一条记录
	 * @param b
	 */
	public void update(Book b);
	
	/**
	 * 根据id删除books表中的一条记录
	 * @param id
	 */
	public void deleteById(String id);
	
	/**
	 * 根据查询条件得到匹配的总记录数
	 * @param cid
	 * @param minPrice
	 * @param maxPrice
	 * @return
	 */
	public long getTotalRecord(String cid, int minPrice, int maxPrice);
	
	
	/**
	 * 根据条件得到满足条件的记录的集合
	 * @param categorysid
	 * @param minprice
	 * @param maxPrice
	 * @param start
	 * @param count
	 * @return
	 */
	public List<Book> getList(String categorysid, int minprice, int maxPrice, int start, int count);

	
	/**
	 * 根据categorysid判断有没有对应的记录
	 * @param categorysid
	 * @return
	 */
	public boolean hasBookByCategorysId(String categorysid);

	/**
	 * 批量更新书的数据
	 * @param data
	 */
	public void batchUpdateStoreAndSale(Object[][] data);
	

	

}
