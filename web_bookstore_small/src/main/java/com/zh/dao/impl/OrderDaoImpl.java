package com.zh.dao.impl;

/*
 * 操作orders表的dao实现类
 */

import java.util.List;

import com.zh.bean.Order;
import com.zh.dao.BaseDao;
import com.zh.dao.OrderDao;

public class OrderDaoImpl extends BaseDao<Order> implements OrderDao{

	@Override
	public void insert(Order order) {
		// TODO Auto-generated method stub
		String sql = "insert into orders (id, number, price, usersid, addressid) values(?,?,?,?,?)";
		update(sql, order.getId(), order.getNumber(), order.getPrice(),
				order.getUsersid(), order.getAddressid());
	}

	@Override
	public long getTodayCount() {
		// TODO Auto-generated method stub
		String sql = "SELECT COUNT(*) FROM orders WHERE ordertime>=CURRENT_DATE()";
		return QueryForSingleValue(sql);
	}

	@Override
	public List<Order> getListByUserId(String userId) {
		// TODO Auto-generated method stub
		String sql = "select * from orders where usersid=?";
		return queryForList(sql, userId);
	}

	@Override
	public List<Order> getListByStatus(boolean status) {
		// TODO Auto-generated method stub
		String sql = "select * from orders where status=?";
		return queryForList(sql, status);
	}

	@Override
	public void updateStatus(String orderid) {
		// TODO Auto-generated method stub
		String sql = "update orders set status=? where id=?";
		update(sql, true, orderid);
	}

}
