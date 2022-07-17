package com.zh.dao.impl;

import java.util.List;

import com.zh.bean.Book;
import com.zh.dao.BaseDao;
import com.zh.dao.BookDao;

public class BookDaoImpl extends BaseDao<Book> implements BookDao{

	@Override
	public void insert(Book b) {
		// TODO Auto-generated method stub
		String sql = "insert into books values(?,?,?,?,?,?,?,?)";
		update(sql, b.getId(),b.getName(), b.getAuthor(), 
				b.getPrice(),b.getSalesamount(),b.getStorenumber(),b.getImagepath(), b.getCategorysid());
	}

	@Override
	public List<Book> getAll() {
		// TODO Auto-generated method stub
		String sql = "select * from books";
		return queryForList(sql);
	}

	@Override
	public Book getById(String id) {
		// TODO Auto-generated method stub
		String sql = "select * from books where id=?";
		return query(sql, id);
	}

	@Override
	public void update(Book b) {
		// TODO Auto-generated method stub
		String sql = "update books set name=?,author=?,price=?,categorysid=? where id=?";
		update(sql, b.getName(),b.getAuthor(),b.getPrice(), b.getCategorysid(), b.getId());
	}

	@Override
	public void deleteById(String id) {
		// TODO Auto-generated method stub
		String sql = "delete from books where id=?";
		update(sql, id);
	}

	@Override
	public long getTotalRecord(String cid, int minPrice, int maxPrice) {
		// TODO Auto-generated method stub
		String sql = "select count(*) from books where price>=? and price<=? and categorysid like ?";

		return QueryForSingleValue(sql, minPrice, maxPrice, cid);
	}
	
	/**
	 
	  int count  = 2;
	  String id = "dfd";
	  Object[][] params = [[2,2,'dfd'],[1,1,'xxx'],[2,2,'dfd']];

	 */
	@Override
	public void batchUpdateStoreAndSale(Object[][] data) {
		String sql = "update books set storenumber=storenumber-?, salesamount=salesamount+? where id=?";
		batch(sql, data);
	}

	@Override
	public boolean hasBookByCategorysId(String categorysid) {
		// TODO Auto-generated method stub
		String sql = "select count(*) from books where categorysid=?";
		return (Long)QueryForSingleValue(sql, categorysid)>0;
	}

	@Override
	public List<Book> getList(String categorysid, int minprice, int maxPrice, int start, int count) {
		// TODO Auto-generated method stub
		String sql = "select * from books where price>=? and price<=? and categorysid like ? limit ?,?";
		return queryForList(sql, minprice, maxPrice, categorysid, start, count);
	}

}
