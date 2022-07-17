package com.zh.service;

import java.util.List;

import com.zh.bean.Category;

/**
 * 图书分类相关操作的业务接口
 * 
 * @author zhouhui
 * 
 */

public interface CategoryService {

	/**
	 * 添加一个分类
	 * @param category
	 * @return
	 */
	boolean addCategory(Category category);

	/**
	 * 得到所有的分类 
	 * @return
	 */
	List<Category> getAllCategorys();

	/**
	 * 更新某个分类 
	 * @param category
	 */
	void updateCategory(Category category);

	/**
	 * 删除一个分类
	 * @param id
	 */
	void deleteCategoryById(String id);

	/**
	 * 得到某个分类 
	 * @param id
	 * @return
	 */
	Category getCategory(String id);
}
