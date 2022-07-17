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
	<div align="center">
		<h4>添加图书</h4>
		<form action="${pageContext.request.contextPath }/manager/BookServlet?method=add"
			method="post" enctype="multipart/form-data">
			<input type="hidden" name="storenumber" value="100"> 
			<input type="hidden" name="salesamount" value="0">
			<table border="1" width="500px" cellpadding="10" cellspacing="0">
				<tr>
					<td>图书名称</td>
					<td><input type="text" name="name" /></td>
				</tr>
				<tr>
					<td>作者</td>
					<td><input type="text" name="author" /></td>
				</tr>
				<tr>
					<td>售价</td>
					<td><input type="text" name="price"
						onkeypress="if (event.keyCode < 45 || event.keyCode > 57) event.returnValue = false;" />
					</td>
				</tr>
				<tr>
   					<td>图书封面</td>
   					<td>
   						<input type="file" name="imagepath" />
   					</td>
   				</tr>
				<tr>
					<td>图书类别</td>
					<td><select name="categorysid">
							<c:forEach var="category" items="${allCategorys }">
								<option value="${category.id }">${category.name }</option>
							</c:forEach>
					</select></td>
				</tr>
				<tr>
					<td colspan="2"><input type="submit" value="添加图书"></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>