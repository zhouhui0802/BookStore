<%@page import="java.util.Date"%>
<%@page import="java.text.MessageFormat"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.util.ResourceBundle"%>
<%@page import="java.util.Locale"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登陆页面</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/user.css"   />
</head>
<body>
	<%
		Locale locale = request.getLocale();
		String value = request.getParameter("locale");
		if("CN".equals(value)) {
			locale = Locale.CHINA;
		} else if("US".equals(value)) {
			locale = Locale.US;
		}
		pageContext.setAttribute("LOCALE", locale);
		
		pageContext.setAttribute("DATE", new Date());
	%>


	<fmt:setLocale value="${LOCALE}"/>
	
	<fmt:setBundle basename="myResource"/>
	
	
	<div align="center">
		<!-- 显示一个提示信息 -->
		<h2>
			<font color="red">${message}</font>
		</h2>
		<fmt:formatDate value="${DATE}" dateStyle="LONG" var="cDate"/>
		<fmt:message key="item.date">
			<fmt:param value="${cDate}"></fmt:param>
		</fmt:message>
		
		<form action="${pageContext.request.contextPath}/client/UserServlet" method="post">
			<!-- 携带一个隐含参数 -->
			<input type="hidden" name="method" value="login"/>
			<table border="1px;" width="300px">
				<tr id="head">
					<td colspan="2"><fmt:message key="item.head"/></td>
				</tr>
				<tr>
					<td><fmt:message key="item.username"/></td>
					<td><input class="input" type="text" name="username"></td>
				</tr>
				<tr>
					<td><fmt:message key="item.password"/></td>
					<td><input class="input" type="password" name="password"></td>
				</tr>
				<tr>
					<td align="center" colspan="2">
					<input class="OperBtn" type="submit" value="<fmt:message key="item.login"/>" /> 
					&nbsp;&nbsp;&nbsp; 
					<a href="${pageContext.request.contextPath}/client/user/regist.jsp"><fmt:message key="item.regist"/> </a></td>
				</tr>
			</table>
		</form>
		<a href="login_fmt.jsp?locale=CN">中文</a>
		&nbsp;<a href="login_fmt.jsp?locale=US">English</a>
	</div>
</body>
</html>