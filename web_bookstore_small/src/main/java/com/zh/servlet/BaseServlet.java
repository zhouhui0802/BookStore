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
 * ����ʵ��servlet�ĸ���
 * �����󽻸���Ӧ�Ĵ�����ȥ����
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
		
		//��������ı���
		request.setCharacterEncoding("utf-8");
		
		String methodName=request.getParameter("method");
		//System.out.println("BaseServlet�еķ���������"+methodName);
		
		//���䣬�õ�Ҫ���÷�������Ӧ��method����
		try {
			//����methodName����õ���Ӧ��Method����
			Method method=getClass().getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
			//���ö�Ӧ�Ĵ������󷽷�
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
