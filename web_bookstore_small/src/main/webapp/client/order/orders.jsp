<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<center>
		<h4>我的订单</h4>
		<table width="600px" border="1" cellpadding="10" cellspacing="0">
			<tr>
				<td>订单编号</td>
				<td>下单时间</td>
				<td>订单状态</td>
				<td>订单价格</td>
				<td>查看订单</td>
			</tr>
			<c:forEach var="order" items="${list}">
				<tr>
					<td>${order.number }</td>
					<td>${order.ordertime }</td>
					<td>${order.status?'已发货':'未发货' }</td>
					<td>${order.price }</td>
					<td><a
						href="${pageContext.request.contextPath}/client/OrderServlet?method=showOrder&orderid=${order.id}&addressid=${order.addressid}">查看详细</a>
					</td>
				</tr>
			</c:forEach>
		</table>
	</center>
</body>
</html>