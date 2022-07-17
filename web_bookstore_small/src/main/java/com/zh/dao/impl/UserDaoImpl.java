package com.zh.dao.impl;

import com.zh.bean.User;
import com.zh.dao.BaseDao;
import com.zh.dao.UserDao;

public class UserDaoImpl extends BaseDao<User> implements UserDao{

	

	
	@Override
	public User getByName(String name) {
		// TODO Auto-generated method stub
		String sql="select * from users where username=?";
		return query(sql,name);
	}

	@Override
	public void insert(User user) {
		// TODO Auto-generated method stub
		String sql = "insert into users(id,username,password, email) values(?,?,?,?)";
		update(sql, user.getId(), user.getUsername(), user.getPassword(),user.getEmail());
	}

	@Override
	public User getByNameAndPwd(String name, String password) {
		// TODO Auto-generated method stub
		String sql="select * from users where username=? and password=?";
		return query(sql,name,password);
	}

	@Override
	public User getById(String usersid) {
		// TODO Auto-generated method stub
		String sql = "select * from users where id=?";
		return query(sql, usersid);
	}

}
