package com.zh.service;

import java.util.List;

import com.zh.bean.Address;
import com.zh.bean.Cart;
import com.zh.bean.Order;
import com.zh.bean.OrderItem;
import com.zh.bean.Transacation;

public interface OrderService {

	/**
	 * ��ӵ�ַ
	 * @param address
	 */
	void addAddress(Address address);
	/**
	 * ����һ������
	 * @param userId
	 * @param addressId
	 * @param cart
	 * @return
	 */
	@Transacation
	public String makeOrder(String userId, String addressId, Cart cart);

	/**
	 * �õ�ĳ���û������е�ַ��Ϣ
	 * @param userid
	 * @return
	 */
	List<Address> getAdressesByUserId(String userid);

	/**
	 * �õ�ĳ���û������ж�����Ϣ
	 * @param id
	 * @return
	 */
	List<Order> getOrdersByUserId(String id);

	/**
	 * �õ�ĳ�����������ж�������Ϣ
	 * @param orderid
	 * @return
	 */
	List<OrderItem> getOrderItemsByOrderId(String orderid);

	/**
	 * �õ���ַ����
	 * @param addressid
	 * @return
	 */
	Address getAdressByAddresId(String addressid);

	/**
	 * �õ�ĳ������״̬�����ж���
	 * @param status
	 * @return
	 */
	List<Order> getOrdersByStatus(boolean status);

	/**
	 * ��ĳ����������
	 * @param orderid
	 */
	void send(String orderid);
	
}
