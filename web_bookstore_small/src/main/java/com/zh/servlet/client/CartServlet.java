package com.zh.servlet.client;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.zh.bean.Book;
import com.zh.bean.Cart;
import com.zh.factory.BeanFactory;
import com.zh.service.BookService;
import com.zh.service.CartService;
import com.zh.service.impl.BookServiceImpl;
import com.zh.service.impl.CartServiceImpl;
import com.zh.servlet.BaseServlet;
import com.zh.util.TransacationProxyUtils;
import com.zh.util.WebUtils;

/**
�����ﳵ������� 
add() ��һ������ӵ�����
updateCount()  ����ĳ���������е��������
delete()  ɾ��һ��������
clear() ��չ��ﳵ
*/

/**
 * Servlet implementation class CartServlet
 */

public class CartServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	private CartService cartService = BeanFactory.get(CartService.class);
	private BookService bookService = TransacationProxyUtils.getProxy(BeanFactory.get(BookService.class));

	// 2.1. ��һ������ӵ����ﳵ
	public void add(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {
		
		System.out.println("---CartServlet add()");

		HttpSession session = request.getSession();
		// 1. �õ�bookid�������
		String bookid = request.getParameter("bookid");
		// 2. ����bookService��getBook()�õ�һ��book����
		Book book = bookService.getBookById(bookid);
		// 3. ����cartService��addBook()����book��������
		String result = cartService.addBook(WebUtils.getCart(request), book);
		// 4. ת����index.jsp
		response.setContentType("text/json;charset=utf-8");
		response.getWriter().write(result);
	}

	public void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {

		System.out.println("delete()");
		String bookid = request.getParameter("bookid");
		cartService.deleteItem(WebUtils.getCart(request), bookid);

		request.getRequestDispatcher("/client/book/cart.jsp").forward(request, response);
	}

	public void clear(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {

		System.out.println("clear()");
		cartService.clear(WebUtils.getCart(request));

		request.getRequestDispatcher("/client/book/cart.jsp").forward(request, response);
	}

	/*
	 * 5. �޸��������: ajax
	 */
	public void update(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("---CartServlet update()");
		
		//1. ��ȡcount/bookid�Ĳ���
		String countString = request.getParameter("count");
		String bookid = request.getParameter("bookid");
		
		int count = 1;
		try {
			count = Integer.parseInt(countString);
		} catch (Exception e) {
			//e.printStackTrace();
		}
		if(count<1)
			count = 1;
		
		//2. ����cartService��updateBookCount(session, count, bookid)������
		Cart cart = WebUtils.getCart(request);
		String json = cartService.updateItem(cart,bookid,count);
		//3. ����������һ��json����:
		//"{"count":2,"itemTotalPrice":23,"totalPrice":2323,totalCount:23}"
		response.setContentType("text/json;charset=utf-8");
		response.getWriter().write(json);
	}
}
