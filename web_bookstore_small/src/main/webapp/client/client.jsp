<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="com.zh.util.WebUtils"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>模拟在线书城</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/common.css">
<style type="text/css">
table {
	height: 780px;
	width: 100%;
}
</style>
</head>
<body>
	<table>
		<tr style="height: 80px; background:#ffddaa; text-align: center;">
			<td colspan="2">
				<div style="font: 30px black;">WEB在线书城欢迎你!</div>
				
				<div align="left" style="padding-left: 10px;">
					<c:choose>
					
						<c:when test="${sessionScope.user!=null}">
							Hello, ${sessionScope.user.username}
							<a href="<%=request.getContextPath() %>/client/UserServlet?method=logout">登出</a>
						</c:when>
						
						<c:otherwise>
							<a target="content_frame" href="<%=request.getContextPath() %>/client/user/login.jsp">登陆</a>
							｜<a target="content_frame" href="<%=request.getContextPath() %>/client/user/regist.jsp">注册</a>
						</c:otherwise>
						
					</c:choose>
				</div>    <!-- 这个div对应用户登录注册 -->
				
				
				<div align="right" style="padding: 10px;">
					<a target="content_frame"
						href="${pageContext.request.contextPath}/client/book/cart.jsp">查看购物车</a>
					| <a target="content_frame"
						href="${pageContext.request.contextPath}/client/OrderServlet?method=listOrders">我的订单</a>
				</div>   <!-- 查看购物车以及我的订单 -->
			</td>
		</tr>   <!-- 表格的第一行 -->
		
		<tr style="height:700px;" valign="top" >
			<td style="width: 20%; height:700px; padding: 5px;">
				<a target="content_frame" href="<%=request.getContextPath() %>/client/BookServlet?method=getBooks">全部分类</a><br/> 
				
				<c:forEach items="${allCategorys}" var="category">  <!-- 从ClientServlet中传过来的参数 -->
					<a target="content_frame" href="<%=request.getContextPath() %>/client/BookServlet?method=getBooks&categorysid=${category.id}">${category.name}</a><br />
				</c:forEach>
				
				<br>
				
				<br>
				<a target="content_frame" href="<%=request.getContextPath() %>/client/BookServlet?method=showHistoryBooks">查看浏览记录</a>
				<br/>
				
				<a target="content_frame" href="<%=request.getContextPath() %>/client/user/users.jsp">查看在线用户</a>
				<br/>
				
				<br/>
				<a href="${pageContext.request.contextPath}/manager/manager.jsp">进入后台</a>
			</td>
			
			<td style="width: 80%; height:700px;">
				<iframe name="content_frame" style="width: 100%;height: 700px;" src="<%=request.getContextPath() %>/client/BookServlet?method=getBooks"></iframe>
			</td><!-- 通过src直接进行跳转 -->
			
		</tr> <!-- 表格的第二行 -->
		
	</table>
</body>
</html>