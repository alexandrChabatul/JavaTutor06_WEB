<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>Login</title>
<link href="${pageContext.request.contextPath}/resources/css/login.css"
	rel="stylesheet" type="text/css">
</head>
<body>

	<%@include file="header.jsp"%>

	<h1>
		<fmt:message key="page.login.header" />
		:
	</h1>

	<div id="login" class="login"></div>

	<div id="loginFrom" class="loginForm">

		<form action="${pageContext.request.contextPath}/login" method="post">
			<label for="email"> <fmt:message key="page.login.email" />: <input
				type="text" name="email" id="email" value="${param.email}" required>
			</label><br> <label for="password"> <fmt:message
					key="page.login.password" />: <input type="password"
				name="password" id="password" required>
			</label><br>
			<button type="submit">
				<fmt:message key="page.login.button" />
			</button>
			<br> <a href="${pageContext.request.contextPath}/registration">
				<button type="button">
					<fmt:message key="page.login.registrationButton" />
				</button>
			</a><br>
	</div>

	<div id="errors" class="errors">
		<c:if test="${param.email != null}">
			<div style="color: red">
				<span><fmt:message key="page.login.loginError" /></span>
			</div>
		</c:if>
		<c:if test="${param.service != null}">
			<div style="color: red">
				<span><fmt:message key="page.login.serviceError" /></span>
			</div>
		</c:if>
		</form>
	</div>

	</div>

</body>
</html>