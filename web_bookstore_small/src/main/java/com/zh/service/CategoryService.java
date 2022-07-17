package com.zh.service;

import java.util.List;

import com.zh.bean.Category;

/**
 * ͼ�������ز�����ҵ��ӿ�
 * 
 * @author zhouhui
 * 
 */

public interface CategoryService {

	/**
	 * ���һ������
	 * @param category
	 * @return
	 */
	boolean addCategory(Category category);

	/**
	 * �õ����еķ��� 
	 * @return
	 */
	List<Category> getAllCategorys();

	/**
	 * ����ĳ������ 
	 * @param category
	 */
	void updateCategory(Category category);

	/**
	 * ɾ��һ������
	 * @param id
	 */
	void deleteCategoryById(String id);

	/**
	 * �õ�ĳ������ 
	 * @param id
	 * @return
	 */
	Category getCategory(String id);
}
