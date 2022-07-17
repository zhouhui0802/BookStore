<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@page import="java.util.Locale"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登陆页面</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/common.css">
</head>
<body>
	<%
		Locale locale = request.getLocale();
		String reqLocale = request.getParameter("locale");
		if("ch".equals(reqLocale)) {
			locale = Locale.CHINA;
		} else if("en".equals(reqLocale)) {
			locale = Locale.US;
		}
		pageContext.setAttribute("locale", locale);
	%>

	<fmt:setLocale value="${locale}"/>
	<fmt:setBundle basename="myresource"/>
	<div align="center">
		<h3>
			<font color="red">${message}</font>
		</h3>
		<form action="${pageContext.request.contextPath }/client/UserServlet" method="post">
			<input type="hidden" name="method" value="login"/>
			<table>
				<colgroup>
					<col width="30%">
					<col width="*%">
				</colgroup>
				<tr style="background: #00ddaa; height: 30px">
					<td colspan="2" align="center"><fmt:message key="item.userlogin"/></td>
				</tr>
				<tr>
					<td><fmt:message key="item.username"/></td>
					<td><input class="txt" type="text" name="username"></td>
				</tr>
				<tr>
					<td><fmt:message key="item.password"/></td>
					<td><input class="txt" type="password" name="password"></td>
				</tr>
				<tr>
					
					<td colspan="2"><input type="checkbox" name="autologin" value="auto"/><fmt:message key="item.me"/></td>
				</tr>
				<tr background="#020202">
					<td align="center" colspan="2"><input class="OperBtn"
						type="submit" value=" <fmt:message key="item.login"/> " />&nbsp;&nbsp;&nbsp; <a
						href="${pageContext.request.contextPath}/client/user/regist.jsp"> <fmt:message key="item.regist"/> </a></td>
				</tr>
			</table>
		</form>
		
		<br>
		<div>
			<a href="${pageContext.request.contextPath}/client/user/login.jsp?locale=ch">中文</a>
			|<a href="${pageContext.request.contextPath}/client/user/login.jsp?locale=en">English</a>
		</div>
	</div>
</body>
</html>