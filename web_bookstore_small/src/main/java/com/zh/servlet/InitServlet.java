package com.zh.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zh.factory.BeanFactory;

/**
 * Servlet implementation class InitServlet
 */

/**
 * ������ȡ����Դ�ĳ�ʼ��Servlet
 */
public class InitServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    @Override
    public void init() throws ServletException {
    	BeanFactory.init();
    }
}