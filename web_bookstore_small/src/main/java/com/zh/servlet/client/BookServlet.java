package com.zh.servlet.client;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zh.bean.Book;
import com.zh.bean.Category;
import com.zh.bean.ConditionBook;
import com.zh.bean.Page;
import com.zh.bean.PageCondition;
import com.zh.factory.BeanFactory;
import com.zh.service.BookService;
import com.zh.service.CategoryService;
import com.zh.service.impl.BookServiceImpl;
import com.zh.service.impl.CategoryServiceImpl;
import com.zh.servlet.BaseServlet;
import com.zh.util.TransacationProxyUtils;
import com.zh.util.WebUtils;

/**
����ǰ̨ͼ���������
toClientUI()
getPage()
getBook()
getHistory()
clearHistory()
*/

/**
 * Servlet implementation class BookServlet
 */

public class BookServlet extends BaseServlet {
	
	private static final long serialVersionUID = 1L;

	private BookService bookService = TransacationProxyUtils.getProxy(BeanFactory.get(BookService.class));
	private CategoryService categoryService = TransacationProxyUtils.getProxy(BeanFactory.get(CategoryService.class));

	/*
	 * 1. ��ʾͼ���ҳ�б�
	 */
	public void getBooks(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
			IOException {

		//System.out.println("ִ��client�е�BookServlet��getBooks����");
		//��client.jsp�е�src����ת�����ĺ���
		ConditionBook conditionBook = WebUtils.request2Bean(req, ConditionBook.class);

		Page<Book> page = bookService.getPage(conditionBook);   //��÷�ҳ����Ϣ

		String categorysid = conditionBook.getCategorysid();
		
		if (categorysid == null || "".equals(categorysid.trim())) {
			
			req.setAttribute("category", new Category("ȫ������"));
			
		} else {
			
			Category category = categoryService.getCategory(categorysid);//��ȡ����Ϊcategorysid���鱾����
			req.setAttribute("category", category);
		}

		WebUtils.forword(req, resp, "/client/book/books.jsp", "page", page);
	}

	/*
	 * 2. ��ʾͼ������
	 */
	public void showBook(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
			IOException {
		String id = req.getParameter("bookid");
		Book book = bookService.getBookById(id);
		Category category = categoryService.getCategory(book.getCategorysid());

		//��ӵ���ʷ��¼
		WebUtils.addToHistory(req, resp, book);

		req.setAttribute("book", book);
		req.setAttribute("category", category);

		req.getRequestDispatcher("/client/book/book.jsp").forward(req, resp);
	}

	/*
	 * 3. ��ʾͼ�������¼
	 */
	public void showHistoryBooks(HttpServletRequest request, HttpServletResponse resp)
			throws ServletException, IOException {

		List<Map<String, String>> list = WebUtils.getHistoryBooks(request);
		request.setAttribute("list", list);
		request.getRequestDispatcher("/client/book/historyBooks.jsp").forward(request, resp);
	}
	
	/*
	 * 4. ��������¼clearHistory
	 */
	public void clearHistory(HttpServletRequest request, HttpServletResponse resp)
			throws ServletException, IOException {

		WebUtils.clearHistory(request, resp);
		
		WebUtils.forwordMessageUI(request, resp, "��������¼�ɹ�!");
	}

}
