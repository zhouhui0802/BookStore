package com.zh.dao;

import java.util.List;

import com.zh.bean.Address;

public interface AddressDao {
	
	
	/**
	 * ����һ������
	 * @param address
	 */
	public void insert(Address address);
	
	/**
	 * �����û�id�õ���Ӧ�����е�ַ���б�
	 * @param userid
	 * @return
	 */
	public List<Address> getListByUserId(String userId);


	
	/**
	 * ����id�õ���Ӧ�ļ�¼����
	 * @param addressid
	 * @return
	 */
	public Address getById(String addressid);
	
}
