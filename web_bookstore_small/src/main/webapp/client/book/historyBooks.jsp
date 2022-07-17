<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<center>
		<c:choose>
		<c:when test="${empty list}">
			还没有浏览过任何图书
		</c:when>
		<c:otherwise>
			<h4>所有的浏览的图书</h4>
			<table border="1" width="700px" cellpadding="10" cellspacing="0">
			    <c:forEach var="map" items="${list }">
			    	 <tr>	
				    	<td>${map.name}</td>
				    	<td><a href="${pageContext.request.contextPath}/client/BookServlet?method=showBook&bookid=${map.id}">查看书的详情</a></td>
			  		 </tr>
			    </c:forEach>
		    </table>
		    <br>
		    <a href="${pageContext.request.contextPath}/client/BookServlet?method=clearHistory">清空浏览记录</a>
		</c:otherwise>
	</c:choose>
	</center>
	
    
</body>
</html>