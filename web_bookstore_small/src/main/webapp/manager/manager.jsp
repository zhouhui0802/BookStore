<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>模拟在线书城</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/common.css">
<style type="text/css">
table {
	height: 780px;
	width: 100%;
}
</style>
</head>
<body>
	<table>
		<tr style="height: 80px; background:#ffddaa; text-align: center;">
			<td colspan="2">
				<div style="font: 30px black;">WEB在线书城后台管理</div>
			</td>
		</tr>
		<tr style="height:700px;" valign="top" >
			<td style="width: 20%; padding: 5px;">
				分类管理<br/>
				<a target="content_frame" href="${pageContext.request.contextPath}/manager/category/add.jsp">添加分类</a><br /> 
				<a target="content_frame" href="${pageContext.request.contextPath}//manager/CategoryServlet?method=list">查看分类</a><br/> <br/>
				图书管理<br/>
				<a target="content_frame" href="${pageContext.request.contextPath}/manager/BookServlet?method=toAddUI">添加图书</a><br />
				<a target="content_frame" href="${pageContext.request.contextPath}/manager/BookServlet?method=listAll">查看图书</a><br /><br/>
				订单管理<br/>
				<a target="content_frame" href="${pageContext.request.contextPath}/manager/OrderServlet?method=showOrders&status=true">已发货订单管理</a><br />
				<a target="content_frame" href="${pageContext.request.contextPath}/manager/OrderServlet?method=showOrders&status=false">未发货订单管理</a><br />
			
				<br/>
				<a href="${pageContext.request.contextPath}/index.jsp">进入前台</a>
			</td>
			<td style="width: 80%; height:700px;">
				<iframe name="content_frame" style="width: 100%;height: 100%;" src="${pageContext.request.contextPath}/manager/index.jsp"></iframe>
			</td>
		</tr>
	</table>
</body>
</html>