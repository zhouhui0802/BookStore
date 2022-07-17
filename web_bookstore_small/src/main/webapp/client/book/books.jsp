<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<script type="text/javascript" src="<%=request.getContextPath() %>/scripts/jquery-1.7.2.js"></script>
<script type="text/javascript">
		$(function(){
			
			
			//alert("=====");
			$("a").each(function(){
				this.href = this.href+"&categorysid=${category.id}&minprice=${param.minprice}&maxprice=${param.maxprice}";
			});
			
			$("#toPage").blur(function(){
				var num = this.value;
				window.location = "${pageContext.request.contextPath}/client/BookServlet?method=getBooks&minprice=${param.minprice}&maxprice=${param.maxprice}&pageNum="+num;
			});
			$("#cart_3").hide();
			var hasBook = ${CART!=null && CART.totalCount>0};
			if(hasBook) {
				$("#cart_1").show();
				//alert("=====");
				$("#cart_2").hide();
			} else {
				$("#cart_1").hide();
				$("#cart_2").show();
			}
			
			$(".addCartBtn").click(function(){
				//alert("11111");
				var bookid = this.id;
				var url = "${pageContext.request.contextPath}/client/CartServlet?method=add&bookid="+bookid+"&pageNum=${page.pageNum}&time="+new Date().toLocaleString();
				
				$.post(url, function(json){
					//{"name":"Java编程者思想",totalCount:23}
					var name = json.name;
					var totalCount = json.totalCount;
					$("#cart_1").hide();
					$("#cart_2").hide();
					$("#cart_3").show();
					$("#addNameId").text(name);
					$("#totalCountId").text(totalCount);
				}, "json");
			});
		});
</script>

<body>
	<div align="center">
		<div style="font-size: 20px; background: gray; width: 500px; height: 50px">
		
				<div id="cart_1">
					购物车中有${CART.totalCount} 本书， 
					<a href="${pageContext.request.contextPath}/client/book/cart.jsp?1=1">查看购物车</a> 
				</div>
				
				<div id="cart_2">
					购物车中还没有一个购物项
				</div>
				
				<div id="cart_3">
					您已将加入<span id="addNameId" style="color: red; font-size: 15px;"></span>购物车,
					购物车中有<span id="totalCountId" style="color: red; font-size: 15px;"></span>本书<br>
					<a href="${pageContext.request.contextPath}/client/book/cart.jsp?1=1">查看购物车</a> 
				</div>
				
		</div>
			<br>

		<div>
				<form action="${pageContext.request.contextPath}/client/BookServlet">
					<input type="hidden" name="method" value="getBooks"/>
					<input type="hidden" name="categorysid" value="${category.id}"/>  <!-- 这个数据的改变从左边的那个图书分类穿过来 -->
					价格:<input type="text" name="minprice" style="width: 40px;text-align: center;" value="${param.minprice}"/>
					----<!--${param.minprice}的作用: 取出变量名是minprice中的值 -->
					<input type="text" name="maxprice" style="width: 40px;text-align: center;" value="${param.maxprice }"/>
					<input type="submit" value="查 询"/> 
				</form>
		</div>
			
	<c:if test="${page!=null}">
		<div style="text-align: left;font-size: 25px; background: gray; width:500px; height: 30px"> 当前分类为: ${category.name}</div>
				
			<c:forEach items="${page.list}" var="book">
				<div style="width:500px; height:170px; ">
			   			<div style="width:150px; height:150px; border:1px #000000 solid; float:left ; margin-top: 10px; margin-right: 30px;" >
				   			<img width="150" height="150" src="${pageContext.request.contextPath }${book.imagepath }">
				   		</div>
				   	<div style="width:350px; height:150px; text-align: left;"  >
				   			<br>
				   			书名: <a href="${pageContext.request.contextPath}/client/BookServlet?method=showBook&bookid=${book.id}">${book.name}</a><br>
				   			作者：${book.author }<br>
				   			售价: ${book.price }<br>
				   			销量: ${book.storenumber }<br>
				   			库存: ${book.salesamount }<br><br>
				   			<input class="addCartBtn" type="button" value="加入购物车" id="${book.id}">
				   	</div>
			   	</div>
			</c:forEach>
				<br>
				
			<div>   <!-- 对页数进行处理 todo -->
				<c:if test="${page.pageNum>1}">  <!-- 判断当前页 -->
					 <a href="${pageContext.request.contextPath}/client/BookServlet?method=getBooks&pageNum=${page.pageNum-1}">上一页</a>
						&nbsp;
				</c:if>
					 
				<c:if test="${page.firstPageNum>1}">  <!-- 防止第一页出错 -->
					 <a href="${pageContext.request.contextPath}/client/BookServlet?method=getBooks&pageNum=1">1</a>
					&nbsp;
				</c:if>
					 
				<c:if test="${page.firstPageNum>2}">
					 ...
				</c:if>
					 
					 
				<c:forEach begin="${page.firstPageNum}" end="${page.lastPageNum}" var="num">
					<c:choose>
						<c:when test="${num==page.pageNum}">${num}</c:when>
						<c:otherwise><a href="${pageContext.request.contextPath}/client/BookServlet?method=getBooks&pageNum=${num}">${num}</a></c:otherwise>
					</c:choose>&nbsp;
				</c:forEach>
					
				<c:if test="${page.lastPageNum<page.totalPageCount-1}">
					 	...
				</c:if>
					
				<c:if test="${page.lastPageNum<page.totalPageCount}">
					 <a href="${pageContext.request.contextPath}/client/BookServlet?method=getBooks&pageNum=${page.totalPageCount}">${page.totalPageCount}</a>
					 &nbsp;
				</c:if>
					 
				<c:if test="${page.pageNum<page.totalPageCount}">
					 <a href="${pageContext.request.contextPath}/client/BookServlet?method=getBooks&pageNum=${page.pageNum+1}">下一页</a>
					 &nbsp;
				</c:if>
					 
				到第<input type="text" id="toPage" style="width: 30px;text-align: center;" value="${page.pageNum}"/>页
				<br>
				
				当前第&nbsp;${page.pageNum}&nbsp;页 总共&nbsp;${page.totalPageCount}&nbsp;页 总共 &nbsp;${page.totalRecord}&nbsp;条记录
					
			</div>
		</c:if>
			
		<div>
			<c:if test="${page==null}">
				没有匹配条件的书
			</c:if>
		</div>
		
	</div>
</body>
</html>