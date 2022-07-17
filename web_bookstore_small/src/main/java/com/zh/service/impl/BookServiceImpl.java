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
		
		//conditionBook�����ã��Բ�ѯ��Ϣ���з�װ
		String pageNumString = conditionBook.getPageNum();  //��ʾ�鱾�ĵڼ�ҳ
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
			categorysid = "%%";  //ģ����ѯ
		}
		
		//��ѯ�������������м�¼��         ��ȡ��ֵ�������䣬�ҷ�������Ϊcategorysid��������
		//���մ������Ĳ��������ǲ�ѯ����
		int totalRecord = (int) bookDao.getTotalRecord(categorysid, minprice, maxPrice);
		System.out.println("��ѯ�鱾��������totalRecord="+totalRecord);
		
		if(totalRecord==0) {
			return null;
		}
		
		//�������ǰ�ܵ�ҳ������   Ĭ��һҳ��ʾ3��
		int totalPageCount = (totalRecord+Page.PAGE_RECORD-1)/Page.PAGE_RECORD;
		System.out.println("������鱾����ҳ��totalPageCount="+totalPageCount);
		
		System.out.println("��ʱpageNum="+pageNum);
		
		if(pageNum>totalPageCount) {
			
			pageNum = totalPageCount;
			
		} else if(pageNum<1){
			
				pageNum = 1;
		}
		
		int start = (pageNum-1)*Page.PAGE_RECORD;
		
		//��ȡ��ҳ���ͼ����Ϣ
		List<Book> list = bookDao.getList(categorysid, minprice, maxPrice, start, Page.PAGE_RECORD);
		
		return new Page<Book>(pageNum, list, totalRecord);  //1  ��ҳ���ͼ����Ϣ   �ܼ�¼��
	}

}
