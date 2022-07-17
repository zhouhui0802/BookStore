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
//	 <button id='${c.id}' disabled='disabled'>删除</button>
//	  <button id='${c.id}'>删除</button>
//	 */
//	@Override
//	public void doTag() throws JspException, IOException {
//		System.out.println("CButtonTag doTag()");
//		
//		//根据id查看有没有对应的书
//		boolean hasBook = bookService.hasBook(id);
//		JspContext context = getJspContext();
//		//有书, 删除按键不可用
//		if(hasBook) {
//			context.getOut().write("<button id='"+id+"' disabled='disabled'>删除</button>");
//		} else {//没书, 删除按键可用
//			context.getOut().write("<button id='"+id+"'>删除</button>");
//		}
//	}
}
