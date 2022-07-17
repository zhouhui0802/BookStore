package com.zh.bean;


import java.util.HashMap;
import java.util.Map;

/*
 * ���ﳵ�ķ�װ��
 */

public class Cart {

	Map<String, CartItem> map = new HashMap<String, CartItem>();
	

	public Map<String, CartItem> getMap() {
		return map;
	}

	//������
	public int getTotalCount() {
		int count = 0;
		for(String id : map.keySet()) {
			CartItem cartItem = map.get(id);
			count += cartItem.getCount();
		}
		return count;
	}

	//�ܼ۸�
	public float getTotalPrice() {
		float totalPrice = 0;
		for(CartItem cartItem : map.values()) {
			totalPrice += cartItem.getItemPrice();;
		}
		return totalPrice;
	}
	
	/**
	 * �������id�õ���Ӧ��cartItem
	 * @param id
	 * @return
	 */
	public CartItem getCartItem(String id) {
		return map.get(id);
	}

	/**
	 * ��һ����cartItem����cart��
	 * @param id
	 * @param cartItem
	 */
	public void addCartItem(String id, CartItem cartItem) {
		map.put(id, cartItem);
	}

	@Override
	public String toString() {
		return "Cart [map=" + map + "]";
	}
}
