package com.zh.dao.impl;

import java.util.List;

import com.zh.bean.CartItem;
import com.zh.bean.OrderItem;
import com.zh.dao.BaseDao;
import com.zh.dao.OrderItemDao;

public class OrderItemDaoImpl extends BaseDao<OrderItem> implements OrderItemDao{

	@Override
	public void batchInsert(Object[][] data) {
		String sql = "insert into orderitems values(?,?,?,?,?)";
		batch(sql, data);
	}

	@Override
	public List<OrderItem> getListByOrderId(String orderid) {
		String sql = "select * from orderitems where ordersid=?";
		return queryForList(sql, orderid);
	}

}
