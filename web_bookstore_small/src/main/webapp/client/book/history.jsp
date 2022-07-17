<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h4>所有的浏览的图书</h4>
	
	<c:choose>
		<c:when test="${empty list}">
			<h3>还没有浏览过一本书!</h3>
		</c:when>
		<c:otherwise>
			<table border="1" width="700px">
				<c:forEach items="${list}" var="item">
					<tr>
						<td>${item.name}</td>
						<td>
							<a href="${pageContext.request.contextPath }/client/BookServlet?method=getBook&id=${item.id}">查看书的详情</a>
						</td>
					</tr>
				</c:forEach>
			</table>
			<br>
			<a href="${pageContext.request.contextPath }/client/BookServlet?method=clearHistory">清空浏览记录</a>
		</c:otherwise>
	</c:choose>
</body>
</html>