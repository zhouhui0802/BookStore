package com.zh.tag;

import java.io.IOException;

import javax.servlet.jsp.JspContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import com.zh.service.BookService;
import com.zh.service.impl.BookServiceImpl;

public class CButtonTag  extends SimpleTagSupport {

//	private String id;
//	private BookService bookService = new BookServiceImpl();
//
//	public void setId(String id) {
//		this.id = id;
//	}
//	
//	/*
//	 <button id='${c.id}' disabled='disabled'>ɾ��</button>
//	  <button id='${c.id}'>ɾ��</button>
//	 */
//	@Override
//	public void doTag() throws JspException, IOException {
//		System.out.println("CButtonTag doTag()");
//		
//		//����id�鿴��û�ж�Ӧ����
//		boolean hasBook = bookService.hasBook(id);
//		JspContext context = getJspContext();
//		//����, ɾ������������
//		if(hasBook) {
//			context.getOut().write("<button id='"+id+"' disabled='disabled'>ɾ��</button>");
//		} else {//û��, ɾ����������
//			context.getOut().write("<button id='"+id+"'>ɾ��</button>");
//		}
//	}
}
