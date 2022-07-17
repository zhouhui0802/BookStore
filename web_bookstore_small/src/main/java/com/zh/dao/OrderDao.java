package com.zh.dao;

import java.util.List;

import com.zh.bean.Order;

public interface OrderDao {

	

	/**
	 * �õ�������ӵļ�¼��
	 * @return
	 */
	public long getTodayCount();
	
	
	/**
	 * ����һ����¼
	 * @param order
	 */
	public void insert(Order order);
	
	/**
	 * �����û�id�õ���Ӧ�����м�¼�ļ���
	 * @param userId
	 * @return
	 */
	List<Order> getListByUserId(String userId);

	/**
	 * ���ݶ���״̬�õ����ж�Ӧ�Ķ����ļ���
	 * @param status
	 * @return
	 */
	List<Order> getListByStatus(boolean status);

	/**
	 * ����ĳ��������״̬
	 * @param orderid
	 */
	void updateStatus(String orderid);
}
