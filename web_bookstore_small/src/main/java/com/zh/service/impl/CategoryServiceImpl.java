package com.zh.service.impl;

import java.util.List;

import com.zh.bean.Category;
import com.zh.dao.CategoryDao;
import com.zh.dao.impl.CategoryDaoImpl;
import com.zh.factory.BeanFactory;
import com.zh.service.CategoryService;

public class CategoryServiceImpl implements CategoryService {

	private CategoryDao categoryDao = BeanFactory.get(CategoryDao.class);
	
	@Override
	public boolean addCategory(Category category) {
		boolean success = false;
		Category dbCategory = categoryDao.getByName(category.getName());
		if(dbCategory==null) {
			categoryDao.insert(category);
			success = true;
		}
		return success;
	}

	@Override
	public List<Category> getAllCategorys() {
		
		return categoryDao.getAll();
	}

	@Override
	public void updateCategory(Category category) {
		categoryDao.update(category);
	}

	@Override
	public void deleteCategoryById(String id) {
		categoryDao.deleteById(id);
	}

	@Override
	public Category getCategory(String id) {
		return categoryDao.getById(id);
	}

}
