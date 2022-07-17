package com.zh.servlet.client;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zh.bean.User;
import com.zh.service.UserService;
import com.zh.service.impl.UserServiceImpl;

/**
 * Servlet implementation class RegistServlet
 */
//@WebServlet("/RegistServlet")
public class RegistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private UserService userService=new UserServiceImpl();
    /**
     * @see HttpServlet#HttpServlet()
     */


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		String password2=request.getParameter("password2");
		String email=request.getParameter("email");
		
		String id=UUID.randomUUID().toString();
		
		User user=new User(id,username,password,email);
		
		boolean success=userService.regist(user);
		
		if(success)
		{
			request.setAttribute("message", "注册成功，可以登录");
			request.getRequestDispatcher("/client/user/login.jsp").forward(request, response);
		}else
		{
			request.setAttribute("message", "注册失败，次用户已经存在");
			response.sendRedirect(request.getContextPath()+"/client/user/regist.jsp");
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
