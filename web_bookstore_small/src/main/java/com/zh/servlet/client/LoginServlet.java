package com.zh.servlet.client;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zh.service.UserService;
import com.zh.service.impl.UserServiceImpl;

/**
 * Servlet implementation class LoginServlet
 */

//�����¼������
public class LoginServlet extends HttpServlet {
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
		
		//System.out.println("");
//		boolean success=userService.login(username, password);
//		
//		if(success)
//		{
//			//�ض���login_success.html
//			response.sendRedirect(request.getContextPath()+"/client/user/login_success.jsp");
//		}else
//		{
//			request.setAttribute("message", "��¼ʧ�ܣ��û������������");
//			request.getRequestDispatcher("/client/user/login.jsp").forward(request, response);
//		}
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
