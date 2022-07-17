<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

   
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="${pageContext.request.contextPath }/scripts/jquery-1.7.2.js"></script>
  <script type="text/javascript">
  	$(function(){
  		$(".delete").click(function(){
  			return window.confirm("确认要删除"+this.name+"?");
  		});
  	});
  </script>
  
</head>
<body>
  	<center>
  		<h4>所有的图书</h4>
    <table border="1" width="700px" cellpadding="10" cellspacing="0">
    <tr>	
    	<td>图书名称</td>
    	<td>作者</td>
    	<td>售价</td>
    	<td>库存</td>
    	<td>销量</td>
    	<td>封面</td>
    	<td>操作</td>
    </tr>
    <c:forEach var="book" items="${list }">
    	 <tr>	
    	 <%-- 判断够不够10个，不够10个，直接显示，够10个显示前面10个加。。。 --%>
	    	<td>${fn:substring(book.name, 0, 10)}${fn:length(book.name)>10? '...': '' }</td>
	    	<td>${book.author }</td>
	    	<td>${book.price }</td>
	    	<td>${book.storenumber }本</td>
	    	<td>${book.salesamount }本</td>
	    	<td>
	    		<a href="${pageContext.request.contextPath }${book.imagepath }">查看图片</a>
			</td>
			<td>	
				<a href="${pageContext.request.contextPath }/manager/BookServlet?method=toUpdateUI&bookid=${book.id }">修改</a>
				
				<a name="${book.name}" class="delete" href="${pageContext.request.contextPath }/manager/BookServlet?method=delete&bookid=${book.id }">删除</a>
			</td>
  		 </tr>
    </c:forEach>
    </table>
  	</center>
</body>
</html>