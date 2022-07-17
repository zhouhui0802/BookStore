package com.zh.mytab;

import java.io.IOException;
import java.io.StringWriter;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.JspFragment;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import com.zh.dao.BookDao;
import com.zh.dao.impl.BookDaoImpl;

public class CheckCategoryTag extends SimpleTagSupport {

	private BookDao bookDao = new BookDaoImpl();
	
	private String id;
	
	
	public void setId(String id) {
		this.id = id;
	}

	@Override
	public void doTag() throws JspException, IOException {
		boolean has = bookDao.hasBookByCategorysId(id);
		JspFragment fragment = getJspBody();
		StringWriter writer = new StringWriter();
		fragment.invoke(writer);
		String result = writer.toString();
		if(has) {
			result = "<button disabled='disabled'>É¾³ý</button>";
		}
		getJspContext().getOut().write(result);
	}
	
}
