<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="${pageContext.request.contextPath}/client/OrderServlet?method=addAddress" method="post">
    	<table border="1" width="400px">
    		<tr>
    			<td>收货人姓名</td>
    			<td>
    				<input type="text" name="name" />
    			</td>
    		</tr>
    		<tr>
    			<td>收货人地址</td>
    			<td>
    				<input type="text" name="location" />
    			</td>
    		</tr>
    		<tr>
    			<td>收货人电话</td>
    			<td>
    				<input type="text" name="cellphone" />
    			</td>
    		</tr>
    		<tr>
    			<td colspan="2">
    				<input type="submit" value="添加收货地址" />
    			</td>
    		</tr>
    	</table>
    </form>
</body>
</html>