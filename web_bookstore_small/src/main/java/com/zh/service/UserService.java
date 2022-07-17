package com.zh.service;

import com.zh.bean.User;

//�����û����ҵ��Ľӿ�

public interface UserService {

	/**
	 * ע��
	 * @param user
	 * @return
	 */
	boolean regist(User user);
	/**
	 * ��½
	 * @param username
	 * @param password
	 * @return
	 */
	User login(String username, String password);

	/**
	 * ����id�õ��û�����
	 * @param usersid
	 * @return
	 */
	User getUserById(String usersid);

	/**
	 * ����û����Ƿ��Ѵ���
	 * @param username
	 * @return
	 */
	boolean checkNameExist(String username);
}
