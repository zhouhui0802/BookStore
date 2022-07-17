<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/script/jquery-1.7.2.js"></script>
<script type="text/javascript">
	$(function(){
		$("a").each(function(){
			var href = $(this).attr("href")+"&cid=${param.cid}&cname=${param.cname}&minPrice=${param.minPrice}&maxPrice=${param.maxPrice}";
			$(this).attr("href", href);
		});
		
		$("#page").blur(function(){
			
			window.location = "${pageContext.request.contextPath}/client/BookServlet?method=getPage&pageNum="+this.value
					+"&cid=${param.cid}&cname=${param.cname}&minPrice=${param.minPrice}&maxPrice=${param.maxPrice}";
		});
	});
</script>
</head>
<body>
	<div align="center">
	
		<br>
		<c:choose>
			<c:when test="${empty CART || empty CART.map}">
				<div style="font-size: 20px; background: gray; width: 500px; height: 50px">
					购物车中没有一本书
				</div>
			</c:when>
			<c:when test="${empty param.name }">
				<div style="font-size: 20px; background: gray; width: 500px; height: 50px">
					购物车中有${CART.totalCount}本书， <a href="${pageContext.request.contextPath}/client/book/cart.jsp?1=1">查看购物车</a>
				</div>
			</c:when>
			<c:otherwise>
				<div style="font-size: 20px; background: gray; width: 500px; height: 50px">
					将<font color="red">${param.name}</font>添加到了购物车, 购物车中有${CART.totalCount}本书， <a href="${pageContext.request.contextPath}/client/book/cart.jsp?1=1">查看购物车</a>
				</div>
				
			</c:otherwise>
		</c:choose>
		<br>
		<div>
			<form action="${pageContext.request.contextPath}/client/BookServlet">
				<input type="hidden" name="method" value="getPage" /> 
				<input type="hidden" name="cid" value="${param.cid}" /> 
				<input type="hidden" name="cname" value="${param.cname}" /> 
				价格:<input type="text" name="minPrice"
						style="width: 40px; text-align: center;" value="${param.minPrice}"/> 
				---- 
				<input type="text" name="maxPrice"
					style="width: 40px; text-align: center;" value="${param.maxPrice}"/> 
				<input type="submit" value="查 询" />
			</form>
		</div>
		<br>
		
		<div style="text-align: left;font-size: 25px; background: gray; width:500px; height: 30px">
			当前分类为: ${empty param.cname ? '全部分类' : param.cname}
		</div>
		
		<c:choose>
			<c:when test="${empty page}">
				没有一本匹配的书
			</c:when>
			<c:otherwise>
				<div>
					<c:forEach items="${page.list}" var="book">
						<div style="width:500px; height:170px; ">
				   			<div style="width:150px; height:150px; border:1px #000000 solid; float:left ; margin-top: 10px; margin-right: 30px;" >
					   			<img width="150" height="150" src="${pageContext.request.contextPath}${book.imagepath}">
					   		</div>
					   		<div style="width:300px; height:150px; text-align: left;"  >
					   			<br>
					   			书名: <a href="${pageContext.request.contextPath}/client/BookServlet?method=getBook&id=${book.id}">${book.name}</a><br>
					   			作者：   ${book.author}<br>
					   			售价: ${book.price}<br>
					   			销量: ${book.salesamount}<br>
					   			库存: ${book.storenumber}<br><br>
					   			<a href="${pageContext.request.contextPath}/client/CartServlet?method=add&bookid=${book.id}&name=${book.name}">加入购物车</a>
					   		</div>
						</div>
					</c:forEach>			
					<br><br>
					
					
					
					<c:if test="${page.pageNum>1 }">
						<a href="${pageContext.request.contextPath}/client/BookServlet?method=getPage&pageNum=${page.pageNum-1}">上一页</a>
					</c:if>
					
					<c:if test="${page.firstPageNum>1 }">
						<a href="${pageContext.request.contextPath}/client/BookServlet?method=getPage&pageNum=1">1</a>
					</c:if>
					
					<c:if test="${page.firstPageNum>2 }">
						...
					</c:if>
					
					<c:forEach begin="${page.firstPageNum}" end="${page.lastPageNum}" var="number">
						<c:choose>
							<c:when test="${number==page.pageNum }"><font color="red">${number}</font></c:when>
							<c:otherwise>
								<a href="${pageContext.request.contextPath}/client/BookServlet?method=getPage&pageNum=${number}">${number}</a>
							</c:otherwise>
						</c:choose>
					</c:forEach>
					
					<c:if test="${page.lastPageNum<page.totalPageSize-1}">
						...
					</c:if>
					
					<c:if test="${page.lastPageNum<page.totalPageSize}">
						<a href="${pageContext.request.contextPath}/client/BookServlet?method=getPage&pageNum=${page.totalPageSize}">${page.totalPageSize}</a>
					</c:if>
					
					<c:if test="${page.pageNum<page.totalPageSize}">
						<a href="${pageContext.request.contextPath}/client/BookServlet?method=getPage&pageNum=${page.pageNum+1}">下一页</a>
					</c:if>
					
					&nbsp;<input type="text" style="width: 30px; text-align: center;" id="page" value="${page.pageNum}">
					<br><br>
					当前为第${page.pageNum }页 共${page.totalPageSize}页 共${page.totalRecord}条记录 
				</div>
			</c:otherwise>
		</c:choose>
		
		
	</div>
</body>
</html>