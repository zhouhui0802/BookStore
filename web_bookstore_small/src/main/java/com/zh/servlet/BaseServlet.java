package com.zh.servlet;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zh.exception.DBException;

/**
 * Servlet implementation class BaseServlet
 */

/*
 * 所有实现servlet的父类
 * 将请求交给对应的处理方法去处理
 */
public abstract class BaseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		//设置请求的编码
		request.setCharacterEncoding("utf-8");
		
		String methodName=request.getParameter("method");
		//System.out.println("BaseServlet中的方法名字是"+methodName);
		
		//反射，得到要调用方法所对应的method对象
		try {
			//根据methodName反射得到对应的Method对象
			Method method=getClass().getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
			//调用对应的处理请求方法
			method.invoke(this, request,response);
		}catch(Exception e)
		{
			e.printStackTrace();
			
			Throwable cause = e.getCause();
			if(cause !=null && cause instanceof DBException) {
				throw (DBException)cause; 
			}
		}
	}

}
