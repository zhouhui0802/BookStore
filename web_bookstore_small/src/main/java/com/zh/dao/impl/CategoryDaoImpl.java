package com.zh.dao.impl;

import java.util.List;

import com.zh.bean.Category;
import com.zh.dao.BaseDao;
import com.zh.dao.CategoryDao;

public class CategoryDaoImpl extends BaseDao<Category> implements CategoryDao{

	@Override
	public void insert(Category category) {
		// TODO Auto-generated method stub
		String sql="insert into categorys values(?,?)";
		update(sql,category.getId(),category.getName());
	}

	@Override
	public List<Category> getAll() {
		// TODO Auto-generated method stub
		String sql="select * from categorys";
		return queryForList(sql);
	}

	@Override
	public void update(Category category) {
		// TODO Auto-generated method stub
		String sql="update categorys set name=? where id=?";
		update(sql,category.getName(),category.getId());
	}

	@Override
	public void deleteById(String id) {
		// TODO Auto-generated method stub
		String sql="delete from categorys where id=?";
		update(sql,id);
	}
	
	@Override
	public Category getById(String categorysid) {
		String sql = "select * from categorys where id=?";
		return query(sql, categorysid);
	}

	@Override
	public Category getByName(String name) {
		// TODO Auto-generated method stub
		String sql = "select * from categorys where name=?";
		return query(sql, name);
	}

}
