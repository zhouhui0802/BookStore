package com.zh.service;

import java.util.List;

import com.zh.bean.Address;
import com.zh.bean.Cart;
import com.zh.bean.Order;
import com.zh.bean.OrderItem;
import com.zh.bean.Transacation;

public interface OrderService {

	/**
	 * 添加地址
	 * @param address
	 */
	void addAddress(Address address);
	/**
	 * 生成一个订单
	 * @param userId
	 * @param addressId
	 * @param cart
	 * @return
	 */
	@Transacation
	public String makeOrder(String userId, String addressId, Cart cart);

	/**
	 * 得到某个用户的所有地址信息
	 * @param userid
	 * @return
	 */
	List<Address> getAdressesByUserId(String userid);

	/**
	 * 得到某个用户的所有订单信息
	 * @param id
	 * @return
	 */
	List<Order> getOrdersByUserId(String id);

	/**
	 * 得到某个订单的所有订单项信息
	 * @param orderid
	 * @return
	 */
	List<OrderItem> getOrderItemsByOrderId(String orderid);

	/**
	 * 得到地址对象
	 * @param addressid
	 * @return
	 */
	Address getAdressByAddresId(String addressid);

	/**
	 * 得到某个订单状态的所有订单
	 * @param status
	 * @return
	 */
	List<Order> getOrdersByStatus(boolean status);

	/**
	 * 给某个订单发货
	 * @param orderid
	 */
	void send(String orderid);
	
}
