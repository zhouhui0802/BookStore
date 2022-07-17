package com.zh.servlet.manager;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zh.bean.Book;
import com.zh.bean.Category;
import com.zh.factory.BeanFactory;
import com.zh.service.BookService;
import com.zh.service.CategoryService;
import com.zh.service.impl.BookServiceImpl;
import com.zh.service.impl.CategoryServiceImpl;
import com.zh.servlet.BaseServlet;
import com.zh.util.TransacationProxyUtils;
import com.zh.util.WebUtils;

/*
�����̨ͼ�������ص����� 
1. toAddUI()  ����ͼ�����ҳ��
2. add()  ���ͼ��
3. list() չʾͼ����б�
4. toUpdateUI() �������ҳ��
5. update()  ����ͼ����Ϣ
6. delete() ɾ��ͼ��
*/

public class BookServlet extends BaseServlet{
	private static final long serialVersionUID = 1L;

	private BookService bookService = TransacationProxyUtils.getProxy(BeanFactory.get(BookService.class));
	private CategoryService categoryService = TransacationProxyUtils.getProxy(BeanFactory.get(CategoryService.class));

	public void toAddUI(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
			IOException {
		System.out.println("BookServlet toAddUI()");

		List<Category> allCategorys = categoryService.getAllCategorys();
		WebUtils.forword(req, resp, "/manager/book/add.jsp", "allCategorys", allCategorys);
	}

	public void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("BookServlet add()");

		Book book = WebUtils.uploadBook(req);
	
		bookService.addBook(book);
		
		WebUtils.forwordMessageUI(req, resp, "���ͼ��ɹ�!");
	}

	public void listAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
			IOException {
		System.out.println("BookServlet listAll()");

		List<Book> list = bookService.getAllBooks();
		WebUtils.forword(req, resp, "/manager/book/list.jsp", "list", list);
	}

	public void showBook(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
			IOException {
		System.out.println("BookServlet showBook()");

		String id = req.getParameter("bookid");
		Book book = bookService.getBookById(id);
		WebUtils.forword(req, resp, "/manager/book/book.jsp", "book", book);
	}

	public void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("BookServlet delete()");

		String id = req.getParameter("bookid");
		bookService.deleteBookById(id);
		WebUtils.forwordMessageUI(req, resp, "ɾ��ͼ��ɹ�!");
	}

	public void toUpdateUI(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
			IOException {
		System.out.println("BookServlet toUpdateUI()");

		String bookid = req.getParameter("bookid");

		List<Category> allCategorys = categoryService.getAllCategorys();
		Book book = bookService.getBookById(bookid);

		req.setAttribute("allCategorys", allCategorys);
		req.setAttribute("book", book);
		req.getRequestDispatcher("/manager/book/update.jsp").forward(req, resp);
		;

	}

	public void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("BookServlet update()");

		Book book = WebUtils.request2Bean(req, Book.class);
		bookService.updateBook(book);
		WebUtils.forwordMessageUI(req, resp, "����ͼ��ɹ�!");
	}
}
