<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>注册页面</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/common.css">
<script type="text/javascript" src="<%=request.getContextPath() %>/scripts/jquery-1.7.2.js"></script>
<script type="text/javascript">
	$(function(){
		$("#bubmitBtn").click(function(){
			//检查用户名: 长度为6到12位, 只能输入数字和英文和下划线
			//alert("--");
			var $nameEle = $("#name");
			var reg = /^\w{6,12}$/;
			if(!reg.test($nameEle.val())) {
				alert("用户名长度为6到12位, 只能输入数字和英文和下划线");
				$nameEle[0].focus();
				return false;
			}
			
			//检查密码: 长度为6位, 只能输入数字和英文
			var $passwordEle = $("#password");
			reg = /^[a-zA-Z0-9]{6,8}$/;
			if(!reg.test($passwordEle.val())) {
				alert("密码的长度为6位, 只能输入数字和英文");
				$passwordEle[0].focus();
				return false;
			}
			
			//确认密码必须与密码相同
			var $password2Ele= $("#password2");
			if($password2Ele.val()!=$passwordEle.val()) {
				$password2Ele[0].focus();
				alert("确认密码必须与密码相同");
			}
			
			//邮箱格式必须正确, 可以不输入
			var $emailEle = $("#email");
			reg = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
			if($emailEle.val()!="" && !reg.test($emailEle.val())) {
				$emailEle[0].focus();
				alert("邮箱格式不正确!");
				return false;
			}
			return true;
		});
		
		$("img").click(function(){
			this.src = this.src;
		});
		
		$("#name").blur(function(){
			var reg = /^\w{6,12}$/;
			if(!reg.test(this.value)) {
				this.focus();
				return false;
			} else {
				var url = "${pageContext.request.contextPath}/client/UserServlet";
				$.post(
					url,
					{method:"checkNameExist",username:this.value},
					function(data) {
						$("#result").html(data);
					}
				);
			}
		});
	});
</script>
</head>
<body>
	<div align="center">
		<div style="color: red; margin: 10px;">${message }</div>
		
		<form  action="<%=request.getContextPath() %>/client/UserServlet" method="post">
			<input type="hidden" name="method" value="regist">
			<table style="width: 400px;">
				<colgroup>
					<col width="30%">
					<col width="70%">
				</colgroup>
				<tr style="background: #00ddaa;">
					<td colspan="2" align="center" style="height: 30px;">新用户注册</td>
				</tr>
				<tr>
					<td>用户名：</td>
					<td>
						<input class="txt" id="name" type="text" name="username"  value="${param.username}"/>
						<span id="result"></span>
					</td>
				</tr>
				<tr>
					<td>密码：</td>
					<td><input class="txt" id="password" type="password" name="password" /></td>
				</tr>
				<tr>
					<td>确认密码：</td>
					<td><input class="txt" id="password2" type="password" name="password2"/></td>
				</tr>
				<tr>
					<td>邮箱：</td>
					<td><input class="txt" id="email" type="text" name="email"  value="${param.email}"/></td>
				</tr>
				<tr>
					<td>验证码：</td>
					<td>
						<input id="code" type="text" name="code" style="width: 100px;"/>
						<img src="<%=request.getContextPath() %>/client/ValidateColorServlet">
					</td>
				</tr>
				<tr>
					<td align="center" colspan="2"><input id="bubmitBtn"
						type="submit" value="立即注册"> <a href="<%=request.getContextPath() %>/client/user/login.jsp"> 登 陆 </a></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>