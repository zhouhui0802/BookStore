package com.zh.service.impl;

import com.zh.bean.User;
import com.zh.dao.UserDao;
import com.zh.dao.impl.UserDaoImpl;
import com.zh.factory.BeanFactory;
import com.zh.service.UserService;

public class UserServiceImpl implements UserService{

	private UserDao userDao = BeanFactory.get(UserDao.class);

	@Override
	public boolean regist(User user) {
		User dbUser = userDao.getByName(user.getUsername());
		if (dbUser != null) {
			return false;
		}
		userDao.insert(user);
		return true;
	}

	public User login(String username, String password) {

		return userDao.getByNameAndPwd(username, password);
	}

	@Override
	public User getUserById(String usersid) {
		return userDao.getById(usersid);
	}

	@Override
	public boolean checkNameExist(String username) {
		User user = userDao.getByName(username);
		
		return user!=null;
	}

}
