package com.zh.dao;

import com.zh.bean.User;

public interface UserDao {

	/**
	 * �����û�������users���ж�Ӧ�ļ�¼
	 * @param name
	 * @return
	 */
	public User getByName(String name);
	
	/**
	 * ��user�������Ϣ���浽users����
	 * @param user
	 */
	public void insert(User user);
	
	/**
	 * �����û�����������users���в��Ҷ�Ӧ�ļ�¼, ����װ�ɶ���
	 * @param name
	 * @param password
	 * @return
	 */
	public User getByNameAndPwd(String name, String password);
	
	/**
	 * ����id�õ���Ӧ�ļ�¼����
	 * @param usersid
	 * @return
	 */
	public User getById(String usersid);
}
