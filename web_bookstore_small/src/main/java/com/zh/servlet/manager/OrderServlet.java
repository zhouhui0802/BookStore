package com.zh.servlet.manager;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zh.bean.Address;
import com.zh.bean.Order;
import com.zh.bean.OrderItem;
import com.zh.bean.User;
import com.zh.factory.BeanFactory;
import com.zh.service.OrderService;
import com.zh.service.UserService;
import com.zh.servlet.BaseServlet;
import com.zh.util.TransacationProxyUtils;
import com.zh.util.WebUtils;

/**
 * Servlet implementation class OrderServlet
 */

public class OrderServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	private OrderService orderService = TransacationProxyUtils.getProxy(BeanFactory.get(OrderService.class));
	private UserService userService = TransacationProxyUtils.getProxy(BeanFactory.get(UserService.class));

	/*
	 * 1. 显示某个状态的所有订单
	 */
	public void showOrders(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {
		//System.out.println("---OrderServlet showOrders()");

		String statusString = request.getParameter("status");
		boolean status = false;
		try {
			status = Boolean.parseBoolean(statusString);
			System.out.println("status="+status);
		} catch (Exception e) {

		}
		List<Order> list = orderService.getOrdersByStatus(status);

		WebUtils.forword(request, response, "/manager/order/orders.jsp", "orders", list);
	}
	
	/*
	 * 2. 显示某个订单详情
	 */
	public void showOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {
		System.out.println("---OrderServlet showOrder()");

		String orderid = request.getParameter("orderid");
		String addressid = request.getParameter("addressid");

		List<OrderItem> orderItems = orderService.getOrderItemsByOrderId(orderid);
		Address address = orderService.getAdressByAddresId(addressid);
		User user = userService.getUserById(address.getUsersid());

		request.setAttribute("orderItems", orderItems);
		request.setAttribute("address", address);
		request.setAttribute("user", user);

		request.getRequestDispatcher("/manager/order/order.jsp").forward(request, response);
	}

	/*
	 *  3. 发货
	 */
	public void send(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {
		System.out.println("---OrderServlet send()");

		String orderid = request.getParameter("orderid");
		
		orderService.send(orderid);
		
		WebUtils.forwordMessageUI(request, response, "发货成功");
	}


}
