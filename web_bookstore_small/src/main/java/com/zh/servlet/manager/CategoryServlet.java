package com.zh.servlet.manager;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zh.bean.Category;
import com.zh.factory.BeanFactory;
import com.zh.service.CategoryService;
import com.zh.service.impl.CategoryServiceImpl;
import com.zh.servlet.BaseServlet;
import com.zh.util.TransacationProxyUtils;
import com.zh.util.WebUtils;

/**
 * Servlet implementation class CategoryServlet
 */

/**
 * 处理图书分类相关请求的Servlet
 * 1. add()  method=add
 * 2. list() method=list
 * 3. update() method=update
 * 4. delete() method=delete
 */



public class CategoryServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
    
	private CategoryService categoryService = TransacationProxyUtils.getProxy(BeanFactory.get(CategoryService.class));
	
	public void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		Category category = WebUtils.request2Bean(request, Category.class);
		
		boolean success = categoryService.addCategory(category);
		
		if(success) {
			WebUtils.forwordMessageUI(request, response, "添加分类成功!");
		} else {
			WebUtils.forword(request, response, "/manager/category/add.jsp", "message", "此分类已存在!");
		}
	}
	
	public void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Category> list = categoryService.getAllCategorys();
		
		WebUtils.forword(request, response, "/manager/category/list.jsp", "list", list);
	}
	
	public void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Category category = WebUtils.request2Bean(request, Category.class);
		categoryService.updateCategory(category);
		WebUtils.forwordMessageUI(request, response, "更新分类成功!");
	}
	
	public void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		categoryService.deleteCategoryById(id);
		WebUtils.forwordMessageUI(request, response, "删除分类成功!");
	}
}
