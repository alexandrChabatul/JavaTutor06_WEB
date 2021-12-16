<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Library</title>
	<link href="${pageContext.request.contextPath}/resources/css/books.css" rel="stylesheet" type="text/css">
</head>

<body>

	<%@include file="header.jsp"%>

	<h1><fmt:message key="page.books.header"/></h1>

	<main>
		<section>
			<div id="info" class="info">
				<c:choose>	
					<c:when test="${not empty requestScope.name}">
						<span><fmt:message key="page.books.searchByName"/>: "${requestScope.name}"</span>
					</c:when>	
					<c:when test="${not empty requestScope.author}">
						<span><fmt:message key="page.books.searchByAuthor"/>: "${requestScope.author}"</span>
					</c:when>
					<c:when test="${empty requestScope.bookName && empty requestScope.bookAuthor}">
						<span><fmt:message key="page.books.tableHead"/></span>
					</c:when>
				</c:choose>
			</div>
			<c:if test="${not empty requestScope.books}">
				<%@include file="pagination.jsp"%>
			</c:if>
			<c:if test="${not empty requestScope.error}">
				<span style="text-align: center"><fmt:message key="page.books.databaseError"/>.</span>
			</c:if>
		</section>

		<aside>
			<div id="toolbar" class="toolbar">
				<span><fmt:message key="page.books.sidebar"/>:</span>
			</div>
			<div id="side" class="side">
				<form action="${pageContext.request.contextPath}/books" method="post">
					<label for="byName"><fmt:message key="page.books.findByName"/>:
						<input type="text" name="name" id="name" required>		
					</label>
					<button type="submit"><fmt:message key="page.books.find"/></button>
					<br>
				</form>
			</div>
			
			<div id="side" class="side">
				<form action="${pageContext.request.contextPath}/books" method="post">
					<label for="byAuthor"><fmt:message key="page.books.findByAuthor"/>:
						<input type="text" name="author" id="author" required>		
					</label>
					<button type="submit"><fmt:message key="page.books.find"/></button>
					<br>
				</form>
			</div>
		
			<div id="option" class="option">
				<c:if test="${sessionScope.user.role.name() eq 'ADMIN'}">
					<form action="${pageContext.request.contextPath}/createBook">
						<button type="submit"><fmt:message key="page.books.addBook"/></button>
						<br>
					</form>
				</c:if>
				<c:if test="${sessionScope.user.role.name() eq 'USER'}">
					<form action="mailto:javamailtester@mail.ru?subject=Предложение книги">
						<button type="submit"><fmt:message key="page.books.suggestBook"/></button>
						<br>
					</form>
				</c:if>
			</div>
		
		</aside>
	</main>
		
</body>
</html>