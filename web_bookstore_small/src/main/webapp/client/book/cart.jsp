<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/jquery-1.7.2.js"></script>
<script type="text/javascript">
	$(function(){
		
		$("#clear").click(function(){
			return window.confirm("你确认清空购物车吗?");
		});
		
		$(".deleteClass").click(function(){
			var name = this.name;
			return window.confirm("你确认删除"+name+"吗?");
		});
		
		$(".increaseCount").click(function(){
			var bookid = this.name;
			var $bookCountEle = $("#"+bookid);
			var count = parseInt($bookCountEle.val())+1;
			$bookCountEle.val(count);
			updateBookCount($bookCountEle, bookid, count);
		});
		
		$(".decreaseCount").click(function(){
			var bookid = this.name;
			var $bookCountEle = $("#"+bookid);
			var count = parseInt($bookCountEle.val())-1;
			$bookCountEle.val(count);
			updateBookCount($bookCountEle,bookid, count);
		});
		
		function updateBookCount($bookCountEle, bookid, count) {
			//发送一个ajax请求去更新购物车
			$.post(
				"${pageContext.request.contextPath}/client/CartServlet",
				{method:"update",count:count, bookid:bookid},
				function(data){
					//alert(data);
					//"{"count":2,"itemTotalPrice":23,"totalPrice":2323,totalCount:23}"
					var count = data.count;
					var itemTotalPrice = data.itemTotalPrice;
					var totalPrice = data.totalPrice;
					var totalCount = data.totalCount;
					
					$bookCountEle.attr("value", count);
					$("td[id='"+bookid+"_price']").text(itemTotalPrice);
					$("#totalCountId").text(totalCount);
					$("#totalPriceId").text(totalPrice);
				},
				"json"
			);
		}
	});
</script>
<body>
	<center>
		<h2>我的购物车</h2>
		<c:choose>
			<c:when test="${CART==null || CART.totalCount==0}">
				购物车中没有任何图书, 去<a href="${pageContext.request.contextPath}/client/BookServlet?method=getBooks">购买</a>
			</c:when>
			<c:otherwise>
				<table border="1" cellpadding="10" cellspacing="0">
					<tr>
						<td>书名</td>
						<td>单价</td>
						<td>数量</td>
						<td>小计</td>
						<td>操作</td>
					</tr>
					<c:forEach items="${CART.map}" var="map">
						<tr>
							<td>${map.value.book.name}</td>
							<td>${map.value.book.price}</td>
							<td>
								<button class="increaseCount" name="${map.value.book.id}">+</button>
								<input value="${map.value.count}" style="width: 30px;text-align: center;" class="countClass" id="${map.value.book.id}"/>
								<button class="decreaseCount" name="${map.value.book.id}">-</button>
							</td>
							<td id="${map.value.book.id}_price">${map.value.itemPrice}</td>
							<td><a href="${pageContext.request.contextPath}/client/CartServlet?method=delete&pageNum=${param.pageNum}&bookid=${map.value.book.id}" class="deleteClass" name="${map.value.book.name}">删除</a></td>
						</tr>
					</c:forEach>
					<tr>
						<td><a href="${pageContext.request.contextPath}/client/CartServlet?method=clear&pageNum=${param.pageNum}" id="clear">清空购物车</a></td>
						<td><a href="${pageContext.request.contextPath}/client/BookServlet?method=getBooks">继续购物</a></td>
						<td>共<span id="totalCountId">${CART.totalCount}</span>本书</td>
						<td>总价:<span id="totalPriceId">${CART.totalPrice}</span>元</td>
						<td><a href="${pageContext.request.contextPath}/client/OrderServlet?method=toAddressUI">去结算</a></td>
					</tr>
				</table>
			</c:otherwise>
		</c:choose>
	</center>
</body>
</html>