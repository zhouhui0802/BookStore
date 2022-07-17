<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
* {
	font-size: 10pt;
}

table {
	border: 1px solid gray;
	border-collapse: collapse;
	width: 80%;
}

td {
	border: 1px solid gray;
	padding: 5px;
}

.head {
	background: #9393FF;
}

.head td {
	text-align: center;
	font: bold;
	padding: 5px;
}

td button {
	margin-left: 5px;
}
</style>

<script type="text/javascript" src="${pageContext.request.contextPath }/script/jquery-1.7.2.js"></script>
<script type="text/javascript">
	$(function(){
		$(".addressRadio").click(function(){
			//显示确认下单按纽
			$("#confirm").removeAttr("disabled");
			
			//得到当前的addressid
			var addressid = this.id;
			//将addressid保存表单中
			$("#addressid").val(addressid);
		});
	});
</script>

</head>
<body>
	<h2>以下是你的收货地址列表：</h2>
	<table id="table">
		<tr class="head">
			<td style="width: 20%;">收货人</td>
			<td style="width: 50%;">地 址</td>
			<td style="width: 20%;">电 话</td>
			<td style="width: 10%;">请选择</td>
		</tr>
		<c:forEach items="${list}" var="address">
			<tr>
				<td>${address.name}</td>
				<td>${address.location}</td>
				<td>${address.cellphone}</td>
				<td><input name="address" class="addressRadio" type="radio" id="${address.id}"/></td>
			</tr>
		</c:forEach>
			
		<tr style="background: #E0E0E0;">
			<td colspan="4" style="text-align: right;">
				<form action="${pageContext.request.contextPath}/client/OrderServlet?method=makeOrder" method="post">
					<input id="addressid" type="hidden" name="addressid" value="">
					<input id="confirm" type="submit" value="确认下单" disabled="disabled">
					<a href="${pageContext.request.contextPath}/client/order/addaddress.jsp" >　增　加　</a>
				</form>
			</td>
		</tr>
	</table>
</body>
</html>