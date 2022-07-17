package com.zh.dao;

import java.util.List;

import com.zh.bean.Book;

public interface BookDao {

	/**
	 * ��books���в���һ����¼
	 * @param b
	 */
	void insert(Book b);
	
	/**
	 * ��ѯbooks��õ����еļ�¼, ����һ��List
	 * @return
	 */
	public List<Book> getAll();
	
	/**
	 * ����id��ѯbooks���ж�Ӧ��¼, ����book����
	 * @param id
	 * @return
	 */
	public Book getById(String id);
	
	/**
	 * ����book������books���ж�Ӧ��һ����¼
	 * @param b
	 */
	public void update(Book b);
	
	/**
	 * ����idɾ��books���е�һ����¼
	 * @param id
	 */
	public void deleteById(String id);
	
	/**
	 * ���ݲ�ѯ�����õ�ƥ����ܼ�¼��
	 * @param cid
	 * @param minPrice
	 * @param maxPrice
	 * @return
	 */
	public long getTotalRecord(String cid, int minPrice, int maxPrice);
	
	
	/**
	 * ���������õ����������ļ�¼�ļ���
	 * @param categorysid
	 * @param minprice
	 * @param maxPrice
	 * @param start
	 * @param count
	 * @return
	 */
	public List<Book> getList(String categorysid, int minprice, int maxPrice, int start, int count);

	
	/**
	 * ����categorysid�ж���û�ж�Ӧ�ļ�¼
	 * @param categorysid
	 * @return
	 */
	public boolean hasBookByCategorysId(String categorysid);

	/**
	 * ���������������
	 * @param data
	 */
	public void batchUpdateStoreAndSale(Object[][] data);
	

	

}
