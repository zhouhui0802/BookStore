<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="${pageContext.request.contextPath }/scripts/jquery-1.7.2.js"></script>
<script type="text/javascript">
	$(function(){
		$("#nameText")[0].focus();
		
		$("#addBtn").click(function(){
			var name = $("#nameText").val();
			if(name.trim()=="") {
				$("#nameText")[0].focus();
				alert("分类名称不能为空");
				return false;
			} else {
				return true;
			}
		});
	});
</script>
</head>
<body style="text-align: center; padding-top: 20px;">
		<div style="font-size: 15px; color: red">${message}</div>
		
		<form action="${pageContext.request.contextPath }/manager/CategoryServlet?method=add" method="post">
			分类名称 : <input id="nameText" type="text" name="name" value="${param.name}" /><br>
			
			<input id="addBtn" type="submit" value="添加分类" />
		</form>
</body>
</html>