<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Registration</title>
<link href="${pageContext.request.contextPath}/resources/css/registration.css" rel="stylesheet" type="text/css">
</head>
<body>

	<%@include file="header.jsp"%>

	<h1><fmt:message key="page.registration.header"/>:</h1>
	
	<div id="registration">
	
		<div id="registrationForm" class="registrationForm">
	
			<form action="${pageContext.request.contextPath}/registration" method="post">
		
				<label for="name"> <fmt:message key="page.registration.name"/>:
					<input type="text" name="name" id="name" required>		
				</label><br>
		
				<label for="email"> <fmt:message key="page.registration.email"/>:
					<input type="text" name="email" id="email" required>		
				</label><br>
			
				<label for="password"> <fmt:message key="page.registration.password"/>:
					<input type="password" name="password" id="password" required>		
				</label><br>
			
				<select name="role" id="role">
					<c:forEach var="role" items="${requestScope.roles}">
						<option value="${role}"> ${role} </option>		
					</c:forEach>
				
				</select>
				<br>
				
				<button type="submit"><fmt:message key="page.registration.button"/></button>
				<br>
			</form>			
		</div>
		
		<div id="errors" class="errors">
			
			<c:if test="${not empty requestScope.errors}">
				<div style="color: red">
					<c:forEach var="error" items="${requestScope.errors}">
						<span>${error.message}</span>
						<br> 
					</c:forEach>
				</div>
			</c:if>
			
			<c:if test="${not empty requestScope.fail}">
				<div style="color: red">
						<span>${requestScope.fail}</span>
						<br>
				</div>
			</c:if>
		</div>
		
	</div>

</body>
</html>