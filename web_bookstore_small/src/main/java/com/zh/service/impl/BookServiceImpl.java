package com.zh.service.impl;

import java.util.List;

import com.zh.bean.Book;
import com.zh.bean.ConditionBook;
import com.zh.bean.Page;
import com.zh.bean.PageCondition;
import com.zh.dao.BookDao;
import com.zh.dao.impl.BookDaoImpl;
import com.zh.factory.BeanFactory;
import com.zh.service.BookService;

public class BookServiceImpl implements BookService{

	private BookDao bookDao = BeanFactory.get(BookDao.class);

	@Override
	public void addBook(Book book) {
		bookDao.insert(book);
	}

	@Override
	public List<Book> getAllBooks() {
		return bookDao.getAll();
	}

	@Override
	public Book getBookById(String id) {
		return bookDao.getById(id);
	}

	@Override
	public void deleteBookById(String id) {
		bookDao.deleteById(id);
	}

	@Override
	public void updateBook(Book book) {
		bookDao.update(book);
	}

	@Override
	public Page<Book> getPage(ConditionBook conditionBook) {
		
		//conditionBook的作用，对查询信息进行封装
		String pageNumString = conditionBook.getPageNum();  //显示书本的第几页
		String categorysid = conditionBook.getCategorysid();
		String minpriceString = conditionBook.getMinprice();
		String maxpriceString = conditionBook.getMaxprice();
		
		System.out.println("pageNumString="+pageNumString+":"+"categorysid="+categorysid+":minpriceString="+
				minpriceString+":maxpriceString="+maxpriceString);
		
		int pageNum = 1;
		int minprice = 0;
		int maxPrice = Integer.MAX_VALUE;
		
		
		try {
			pageNum = Integer.parseInt(pageNumString);
			
		} catch (Exception e) {
		}
		
		try {
			minprice = Integer.parseInt(minpriceString);
		} catch (Exception e) {
			
		}
		
		try {
			maxPrice = Integer.parseInt(maxpriceString);
		} catch (Exception e) {
			
		}
		
		
		if(categorysid==null || "".equals(categorysid.trim())) {
			categorysid = "%%";  //模糊查询
		}
		
		//查询满足条件的所有记录数         获取价值在这区间，且分类种类为categorysid的总数量
		//按照传过来的参数，就是查询所有
		int totalRecord = (int) bookDao.getTotalRecord(categorysid, minprice, maxPrice);
		System.out.println("查询书本的总量是totalRecord="+totalRecord);
		
		if(totalRecord==0) {
			return null;
		}
		
		//计算出当前总的页码数量   默认一页显示3本
		int totalPageCount = (totalRecord+Page.PAGE_RECORD-1)/Page.PAGE_RECORD;
		System.out.println("计算出书本的总页数totalPageCount="+totalPageCount);
		
		System.out.println("此时pageNum="+pageNum);
		
		if(pageNum>totalPageCount) {
			
			pageNum = totalPageCount;
			
		} else if(pageNum<1){
			
				pageNum = 1;
		}
		
		int start = (pageNum-1)*Page.PAGE_RECORD;
		
		//获取分页后的图书信息
		List<Book> list = bookDao.getList(categorysid, minprice, maxPrice, start, Page.PAGE_RECORD);
		
		return new Page<Book>(pageNum, list, totalRecord);  //1  分页后的图书信息   总记录数
	}

}
