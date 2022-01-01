<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Create book</title>
<link
	href="${pageContext.request.contextPath}/resources/css/createBook.css"
	rel="stylesheet" type="text/css">
</head>
<body>

	<%@include file="header.jsp"%>

	<h1>
		<fmt:message key="page.createBook.head" />
		:
	</h1>

	<div id="createBook" class="createBook">

		<div id="createBookForm" class="createBookForm">

			<form action="${pageContext.request.contextPath}/createBook"
				method="post">

				<label for="name"> <fmt:message key="page.createBook.title" />:
					<input type="text" name="title" id="title" required>
				</label><br> <label for="author"> <fmt:message
						key="page.createBook.author" />: <input type="text" name="author"
					id="author" required>
				</label><br> <span>Type:</span> <select name="type" id="type">
					<c:forEach var="type" items="${requestScope.types}">
						<option value="${type}">${type}</option>
					</c:forEach>
				</select> <br>

				<button type="submit">
					<fmt:message key="page.createBook.button" />
				</button>
				<br>
			</form>

			<div id="errors" class="errors"></div>
			<c:if test="${not empty requestScope.errors}">
				<div style="color: red">
					<c:forEach var="error" items="${requestScope.errors}">
						<span>${error.message}</span>
						<br>
					</c:forEach>
				</div>
			</c:if>

			<c:if test="${not empty requestScope.service}">
				<div style="color: red">
					<span>${requestScope.service}</span> <br>
				</div>
			</c:if>
		</div>
	</div>

</body>
</html>