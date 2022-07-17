package com.zh.dao;

import java.util.List;

import com.zh.bean.Category;

public interface CategoryDao {

	/**
	 * �������Ƶõ���Ӧ�ķ������
	 * @param name
	 * @return
	 */
	Category getByName(String name);
	
	//��categorys���в���һ������
	void insert(Category category);
	
	//��ѯcategorys�������еļ�¼
	List<Category> getAll();
	
	//�޸ı��е�һ����¼
	void update(Category category);
	
	//����ID��ѯ���еļ�¼
	void deleteById(String id);
	
	/**
	 * ����id�õ���Ӧ�ļ�¼����
	 * @param categorysid
	 * @return
	 */
	Category getById(String categorysid);
}
