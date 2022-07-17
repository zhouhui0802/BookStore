package com.zh.dao;

import java.util.List;

import com.zh.bean.Order;

public interface OrderDao {

	

	/**
	 * 得到今天添加的记录数
	 * @return
	 */
	public long getTodayCount();
	
	
	/**
	 * 插入一条记录
	 * @param order
	 */
	public void insert(Order order);
	
	/**
	 * 根据用户id得到对应的所有记录的集合
	 * @param userId
	 * @return
	 */
	List<Order> getListByUserId(String userId);

	/**
	 * 根据订单状态得到所有对应的订单的集合
	 * @param status
	 * @return
	 */
	List<Order> getListByStatus(boolean status);

	/**
	 * 更新某个订单的状态
	 * @param orderid
	 */
	void updateStatus(String orderid);
}
