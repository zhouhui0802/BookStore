package com.zh.servlet.client;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zh.bean.User;
import com.zh.factory.BeanFactory;
import com.zh.service.UserService;
import com.zh.service.impl.UserServiceImpl;
import com.zh.servlet.BaseServlet;
import com.zh.util.TransacationProxyUtils;
import com.zh.util.WebUtils;

/**
 * Servlet implementation class UserServlet
 */

/*
 * �����û���ص����� 
 	1. regist()  ע�� 
 	2. login()  ��½
 	3. logout() �ǳ�
 */


public class UserServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	private UserService userService = TransacationProxyUtils.getProxy(BeanFactory.get(UserService.class));

	/*
	 * 1. �û�ע��: method=regist
	 */
	public void regist(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {
		System.out.println("UserServlet regist()");

		User user = WebUtils.request2Bean(request, User.class);

		boolean same = checkCode(request);
		if(!same) {
			WebUtils.forword(request, response, "/client/user/regist.jsp", "message", "��֤�벻��ȷ");
			return;
		}
		
		boolean success = userService.regist(user);

		if (success) {
			// request.getRequestDispatcher("/client/user/login.html").forward(request,
			// response);
			request.setAttribute("message", "ע��ɹ�, ���ڿ��Ե�½��!");
			request.getRequestDispatcher("/client/user/login.jsp").forward(request, response);
		} else {
			// response.sendRedirect(request.getContextPath()+"/client/user/regist_error.html");
			request.setAttribute("message", "ע��ʧ��, ���û����Ѵ���!");
			request.getRequestDispatcher("/client/user/regist.jsp").forward(request, response);
		}
	}

	private boolean checkCode(HttpServletRequest request) {
		String code = request.getParameter("code");
		
		String sessionCode = (String) request.getSession().getAttribute("CODE");
		
		return code!=null && code.equalsIgnoreCase(sessionCode);
	}

	/*
	 * 2. �û���½: method=login
	 */
	public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {
		System.out.println("UserServlet login()");

		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String auto = request.getParameter("auto");

		User user = userService.login(username, password);
		if (user != null) {
			// ��user���󱣴浽Session��
			WebUtils.login(request, response, user, "me".equals(auto));
			// response.sendRedirect(request.getContextPath()+"/client/user/login_success.html");
			response.sendRedirect(request.getContextPath() + "/client/user/login_success.jsp");
		} else {
			// response.sendRedirect(request.getContextPath()+"/client/user/login_error.html");
			request.setAttribute("message", "�û������������!");
			request.getRequestDispatcher("/client/user/login.jsp").forward(request, response);
		}
	}

	/*
	 * 3. �û��ǳ�
	 */
	public void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {
		System.out.println("UserServlet logout()");

		WebUtils.logout(request, response);

		response.sendRedirect(request.getContextPath() + "/client/user/login_success.jsp");
	}
	
	/*
	 * 4. Ajax�������û����Ƿ����
	 */
	public void checkNameExist(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("UserServlet checkNameExist()");
	
		String username = request.getParameter("username");
		boolean exist = userService.checkNameExist(username);
		
		if(exist) {
			response.getWriter().write("<font color='red'>���û����Ѵ���</font>");
		} else {
			response.getWriter().write("<font color='blue'>���û�����</font>");
		}
	}
}
