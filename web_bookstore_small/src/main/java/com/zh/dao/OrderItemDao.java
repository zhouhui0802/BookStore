package com.zh.dao;

import java.util.List;

import com.zh.bean.OrderItem;

public interface OrderItemDao {

	/**
	 * 批量插入数据
	 * @param data
	 */
	public void batchInsert(Object[][] data);

	/**
	 * 得到某个订单id所对应的所有订单项的集合
	 * @param orderid
	 * @return
	 */
	public List<OrderItem> getListByOrderId(String orderid);
	
}
