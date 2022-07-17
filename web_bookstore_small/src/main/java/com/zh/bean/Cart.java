package com.zh.bean;


import java.util.HashMap;
import java.util.Map;

/*
 * 购物车的封装类
 */

public class Cart {

	Map<String, CartItem> map = new HashMap<String, CartItem>();
	

	public Map<String, CartItem> getMap() {
		return map;
	}

	//总数量
	public int getTotalCount() {
		int count = 0;
		for(String id : map.keySet()) {
			CartItem cartItem = map.get(id);
			count += cartItem.getCount();
		}
		return count;
	}

	//总价格
	public float getTotalPrice() {
		float totalPrice = 0;
		for(CartItem cartItem : map.values()) {
			totalPrice += cartItem.getItemPrice();;
		}
		return totalPrice;
	}
	
	/**
	 * 根据书的id得到对应的cartItem
	 * @param id
	 * @return
	 */
	public CartItem getCartItem(String id) {
		return map.get(id);
	}

	/**
	 * 将一个新cartItem放入cart中
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
