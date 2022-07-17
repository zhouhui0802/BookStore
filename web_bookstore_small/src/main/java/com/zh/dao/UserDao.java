package com.zh.dao;

import com.zh.bean.User;

public interface UserDao {

	/**
	 * 根据用户名查找users表中对应的记录
	 * @param name
	 * @return
	 */
	public User getByName(String name);
	
	/**
	 * 将user对象的信息保存到users表中
	 * @param user
	 */
	public void insert(User user);
	
	/**
	 * 根据用户名和密码在users表中查找对应的记录, 并封装成对象
	 * @param name
	 * @param password
	 * @return
	 */
	public User getByNameAndPwd(String name, String password);
	
	/**
	 * 根据id得到对应的记录对象
	 * @param usersid
	 * @return
	 */
	public User getById(String usersid);
}
