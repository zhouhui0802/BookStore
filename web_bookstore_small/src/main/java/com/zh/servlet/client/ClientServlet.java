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

	//ͨ�����䣬��̬����ķ�������
	private CategoryService categoryService = TransacationProxyUtils.getProxy(BeanFactory.get(CategoryService.class));
	
	public void toClientUI(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//����û��Ƿ�����
		WebUtils.checkUser(request, response);
		
		//�����Ƿ��¼����ȡ���ͼ������� ͼ��ķ���
		List<Category> allCategorys = categoryService.getAllCategorys(); //String sql="select * from categorys";����invoke����
		WebUtils.forword(request, response, "/client/client.jsp", "allCategorys", allCategorys);
	}
}
