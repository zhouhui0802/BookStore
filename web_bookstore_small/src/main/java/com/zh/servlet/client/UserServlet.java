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
 * 处理用户相关的请求 
 	1. regist()  注册 
 	2. login()  登陆
 	3. logout() 登出
 */


public class UserServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	private UserService userService = TransacationProxyUtils.getProxy(BeanFactory.get(UserService.class));

	/*
	 * 1. 用户注册: method=regist
	 */
	public void regist(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {
		System.out.println("UserServlet regist()");

		User user = WebUtils.request2Bean(request, User.class);

		boolean same = checkCode(request);
		if(!same) {
			WebUtils.forword(request, response, "/client/user/regist.jsp", "message", "验证码不正确");
			return;
		}
		
		boolean success = userService.regist(user);

		if (success) {
			// request.getRequestDispatcher("/client/user/login.html").forward(request,
			// response);
			request.setAttribute("message", "注册成功, 现在可以登陆了!");
			request.getRequestDispatcher("/client/user/login.jsp").forward(request, response);
		} else {
			// response.sendRedirect(request.getContextPath()+"/client/user/regist_error.html");
			request.setAttribute("message", "注册失败, 此用户名已存在!");
			request.getRequestDispatcher("/client/user/regist.jsp").forward(request, response);
		}
	}

	private boolean checkCode(HttpServletRequest request) {
		String code = request.getParameter("code");
		
		String sessionCode = (String) request.getSession().getAttribute("CODE");
		
		return code!=null && code.equalsIgnoreCase(sessionCode);
	}

	/*
	 * 2. 用户登陆: method=login
	 */
	public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {
		System.out.println("UserServlet login()");

		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String auto = request.getParameter("auto");

		User user = userService.login(username, password);
		if (user != null) {
			// 将user对象保存到Session中
			WebUtils.login(request, response, user, "me".equals(auto));
			// response.sendRedirect(request.getContextPath()+"/client/user/login_success.html");
			response.sendRedirect(request.getContextPath() + "/client/user/login_success.jsp");
		} else {
			// response.sendRedirect(request.getContextPath()+"/client/user/login_error.html");
			request.setAttribute("message", "用户名或密码错误!");
			request.getRequestDispatcher("/client/user/login.jsp").forward(request, response);
		}
	}

	/*
	 * 3. 用户登出
	 */
	public void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {
		System.out.println("UserServlet logout()");

		WebUtils.logout(request, response);

		response.sendRedirect(request.getContextPath() + "/client/user/login_success.jsp");
	}
	
	/*
	 * 4. Ajax请求检查用户名是否可用
	 */
	public void checkNameExist(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("UserServlet checkNameExist()");
	
		String username = request.getParameter("username");
		boolean exist = userService.checkNameExist(username);
		
		if(exist) {
			response.getWriter().write("<font color='red'>此用户名已存在</font>");
		} else {
			response.getWriter().write("<font color='blue'>此用户可用</font>");
		}
	}
}
