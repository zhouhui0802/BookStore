package com.zh.service;

import com.zh.bean.User;

//处理用户相关业务的接口

public interface UserService {

	/**
	 * 注册
	 * @param user
	 * @return
	 */
	boolean regist(User user);
	/**
	 * 登陆
	 * @param username
	 * @param password
	 * @return
	 */
	User login(String username, String password);

	/**
	 * 根据id得到用户对象
	 * @param usersid
	 * @return
	 */
	User getUserById(String usersid);

	/**
	 * 检查用户名是否已存在
	 * @param username
	 * @return
	 */
	boolean checkNameExist(String username);
}
