<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<title></title>

</head>

<body>
	<center>
		<h4>订单的详细信息</h4>
		<table border="1" width="400px">
			<tr>
				<th colspan="2">下订单的用户信息</th>
			</tr>
			<tr>
				<td>用户名</td>
				<td>${user.username }</td>
			</tr>
			<tr>
				<td>电子邮件</td>
				<td>${user.email }</td>
			</tr>
		</table>

		<br>
		<table border="1" width="400px">
			<tr>
				<th colspan="2">收货人信息</th>
			</tr>
			<tr>
				<td>收货人姓名</td>
				<td>${address.name }</td>
			</tr>
			<tr>
				<td>收货人地址</td>
				<td>${address.location }</td>
			</tr>
			<tr>
				<td>收货人电话</td>
				<td>${address.cellphone }</td>
			</tr>
		</table>

		<br>
		<table border="1" width="400px">
			<tr>
				<th colspan="3">订单商品列表</th>
			</tr>
			<tr>
				<td>商品</td>
				<td>数量</td>
				<td>小计</td>
			</tr>
			<c:forEach var="item" items="${orderItems }">
				<tr>
					<td><img width="20" height="30"
						src="${pageContext.request.contextPath }${item.book.imagepath }" />
						<a href="#">${item.book.name }</a></td>
					<td>${item.quantity }</td>
					<td>${item.price }</td>
				</tr>
			</c:forEach>
		</table>
	</center>

</body>
</html>
