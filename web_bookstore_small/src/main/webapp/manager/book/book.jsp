<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="<%=request.getContextPath() %>/scripts/jquery-1.7.2.js"></script>
<script type="text/javascript">
	$(function(){
		$("a").click(function(){
			window.history.go(-1); //返回上一页
			return false; //不用原有的链接     google浏览必须有
		});
	});
</script>
</head>
<body>
	<div align="center">
		<img style="width: 300px; height: 300" src="${pageContext.request.contextPath}${book.imagepath}"><br>		
			书名: ${book.name}<br>
			作者: ${book.author}<br>
			价格: ${book.price}<br>
			销量: ${book.salesamount}<br>
			库存: ${book.storenumber}<br>
			分类: ${category.name}<br>
			<a href="#">返回</a>
	</div>
</body>
</html>