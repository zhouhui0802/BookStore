package com.zh.dao.impl;

import java.util.List;

import com.zh.bean.Address;
import com.zh.dao.AddressDao;
import com.zh.dao.BaseDao;

public class AddressDaoImpl extends BaseDao<Address> implements AddressDao{

	@Override
	public List<Address> getListByUserId(String userId) {
		// TODO Auto-generated method stub
		String sql = "select * from address where usersid=?";
		return queryForList(sql, userId);
	}

	@Override
	public void insert(Address address) {
		// TODO Auto-generated method stub
		String sql = "insert into address values(?,?,?,?,?)";
		update(sql, address.getId(), address.getName(), address.getLocation(), address.getCellphone(), address.getUsersid());
	}

	@Override
	public Address getById(String addressid) {
		// TODO Auto-generated method stub
		String sql = "select * from address where id=?";
		return query(sql, addressid);
	}

}
