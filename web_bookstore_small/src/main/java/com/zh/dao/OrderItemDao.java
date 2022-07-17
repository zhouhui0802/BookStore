package com.zh.dao;

import java.util.List;

import com.zh.bean.OrderItem;

public interface OrderItemDao {

	/**
	 * ������������
	 * @param data
	 */
	public void batchInsert(Object[][] data);

	/**
	 * �õ�ĳ������id����Ӧ�����ж�����ļ���
	 * @param orderid
	 * @return
	 */
	public List<OrderItem> getListByOrderId(String orderid);
	
}
