<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body style="text-align: center">
    <form action="${pageContext.request.contextPath }/manager/BookServlet?method=update" method="post">
    	<input type="hidden" name="id" value="${book.id }" />
    	<input type="hidden" name="imagepath" value="${book.imagepath }" />
    	<input type="hidden" name="salesamount" value="${book.salesamount }" />
    	<input type="hidden" name="storenumber" value="${book.storenumber }" />

		<table width="500px" style="border: 1px;">
			<tr>
				<td>图书名称</td>
				<td><input type="text" name="name" value="${book.name }" /></td>
			</tr>
			<tr>
				<td>作者</td>
				<td><input type="text" name="author" value="${book.author }" />
				</td>
			</tr>
			<tr>
				<td>售价</td>
				<td><input type="text" name="price" value="${book.price }"
					onkeypress="if (event.keyCode < 45 || event.keyCode > 57) event.returnValue = false;" />
				</td>
			</tr>
			<tr>
				<td>图书类别</td>
				<td><select name="categorysid">
						<c:forEach var="category" items="${allCategorys }">
							<option value="${category.id }"
								${book.categorysid==category.id?'selected':'' }>${category.name }</option>
						</c:forEach>
				</select></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="更新图书"></td>
			</tr>
		</table>
	</form>
</body>
</html>