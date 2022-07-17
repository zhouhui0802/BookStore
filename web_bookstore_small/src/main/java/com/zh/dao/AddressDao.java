package com.zh.dao;

import java.util.List;

import com.zh.bean.Address;

public interface AddressDao {
	
	
	/**
	 * 插入一条数据
	 * @param address
	 */
	public void insert(Address address);
	
	/**
	 * 根据用户id得到对应的所有地址的列表
	 * @param userid
	 * @return
	 */
	public List<Address> getListByUserId(String userId);


	
	/**
	 * 根据id得到对应的记录对象
	 * @param addressid
	 * @return
	 */
	public Address getById(String addressid);
	
}
