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
处理购物车相关请求 
add() 将一本书添加到购车
updateCount()  更新某个购物项中的书的数量
delete()  删除一个购物项
clear() 清空购物车
*/

/**
 * Servlet implementation class CartServlet
 */

public class CartServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	private CartService cartService = BeanFactory.get(CartService.class);
	private BookService bookService = TransacationProxyUtils.getProxy(BeanFactory.get(BookService.class));

	// 2.1. 将一本书添加到购物车
	public void add(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {
		
		System.out.println("---CartServlet add()");

		HttpSession session = request.getSession();
		// 1. 得到bookid请求参数
		String bookid = request.getParameter("bookid");
		// 2. 调用bookService的getBook()得到一个book对象
		Book book = bookService.getBookById(bookid);
		// 3. 调用cartService的addBook()保存book对象数据
		String result = cartService.addBook(WebUtils.getCart(request), book);
		// 4. 转发到index.jsp
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
	 * 5. 修改书的数量: ajax
	 */
	public void update(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("---CartServlet update()");
		
		//1. 获取count/bookid的参数
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
		
		//2. 调用cartService的updateBookCount(session, count, bookid)来更新
		Cart cart = WebUtils.getCart(request);
		String json = cartService.updateItem(cart,bookid,count);
		//3. 向浏览器输出一个json数据:
		//"{"count":2,"itemTotalPrice":23,"totalPrice":2323,totalCount:23}"
		response.setContentType("text/json;charset=utf-8");
		response.getWriter().write(json);
	}
}
