package com.zh.servlet.client;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zh.bean.Address;
import com.zh.bean.Cart;
import com.zh.bean.Order;
import com.zh.bean.OrderItem;
import com.zh.bean.User;
import com.zh.factory.BeanFactory;
import com.zh.service.CartService;
import com.zh.service.OrderService;
import com.zh.service.impl.OrderServiceImpl;
import com.zh.servlet.BaseServlet;
import com.zh.util.TransacationProxyUtils;
import com.zh.util.WebUtils;

/**
 * Servlet implementation class OrderServlet
 */

/**
 * 处理订单相关请求的Servlet 
 * showAddressList() 
 * addAddress() 
 * makeOrder()
 */

public class OrderServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	private OrderService orderService = TransacationProxyUtils.getProxy(BeanFactory.get(OrderService.class));
	private CartService cartService = BeanFactory.get(CartService.class);
	/*
	 * 1. 去结算, 进入收货列表页面
	 */
	public void toAddressUI(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("toAddressUI()");
		
		User user = WebUtils.checkUser(request, response);
		
		List<Address> list = orderService.getAdressesByUserId(user.getId());
		
		WebUtils.forword(request, response, "/client/order/orderAddress.jsp", "list", list);
	}
	
	/*
	 * 2. 添加地址
	 */
	public void addAddress(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("addAddress()");

		User user = WebUtils.checkUser(request, response);
		
		Address address = WebUtils.request2Bean(request, Address.class);
		address.setUsersid(user.getId());
		
		orderService.addAddress(address);
		
		request.getRequestDispatcher("/client/OrderServlet?method=toAddressUI").forward(request, response);
	}
	
	/*
	 * 3. 下单
	 */
	public void makeOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("makeOrder()");
		
		User user = WebUtils.checkUser(request, response);

		String addressId = request.getParameter("addressid");
		
		String orderNumber = orderService.makeOrder(user.getId(), addressId, WebUtils.getCart(request));
		cartService.clear(WebUtils.getCart(request));
		
		WebUtils.forwordMessageUI(request, response, "提交订单成功,订单号为:"+orderNumber);
	}
	
	/*
	 * 4. 显示当前用户的订单列表
	 */
	public void listOrders(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("makeOrder()");
		
		User user = WebUtils.checkUser(request, response);
		
		List<Order> list = orderService.getOrdersByUserId(user.getId());
		
		WebUtils.forword(request, response, "/client/order/orders.jsp", "list", list);
	}
	
	/*
	 * 5. 显示订单详情
	 */
	public void showOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("showOrder()");
		
		//orderid=${order.id}&addressid=${order.addressid}
		String orderid = request.getParameter("orderid");
		String addressid = request.getParameter("addressid");
		
		List<OrderItem> list = orderService.getOrderItemsByOrderId(orderid);
		Address address = orderService.getAdressByAddresId(addressid);
		
		request.setAttribute("list", list);
		WebUtils.forword(request, response, "/client/order/order.jsp", "address", address);
	}
}
