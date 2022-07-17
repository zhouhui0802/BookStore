package com.zh.servlet.client;

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
import com.zh.servlet.BaseServlet;
import com.zh.util.TransacationProxyUtils;
import com.zh.util.WebUtils;

/**
 * Servlet implementation class ClientServlet
 */

public class ClientServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	//通过反射，动态代理的方法调用
	private CategoryService categoryService = TransacationProxyUtils.getProxy(BeanFactory.get(CategoryService.class));
	
	public void toClientUI(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//检查用户是否在线
		WebUtils.checkUser(request, response);
		
		//无论是否登录，获取左边图书的类型 图书的分类
		List<Category> allCategorys = categoryService.getAllCategorys(); //String sql="select * from categorys";调用invoke函数
		WebUtils.forword(request, response, "/client/client.jsp", "allCategorys", allCategorys);
	}
}
