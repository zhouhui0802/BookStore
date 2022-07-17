package com.zh.dao;

import java.util.List;

import com.zh.bean.Category;

public interface CategoryDao {

	/**
	 * 根据名称得到对应的分类对象
	 * @param name
	 * @return
	 */
	Category getByName(String name);
	
	//向categorys表中插入一条数据
	void insert(Category category);
	
	//查询categorys表中所有的记录
	List<Category> getAll();
	
	//修改表中的一条记录
	void update(Category category);
	
	//根据ID查询表中的记录
	void deleteById(String id);
	
	/**
	 * 根据id得到对应的记录对象
	 * @param categorysid
	 * @return
	 */
	Category getById(String categorysid);
}
