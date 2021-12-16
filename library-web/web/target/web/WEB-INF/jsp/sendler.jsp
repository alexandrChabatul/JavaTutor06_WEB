<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Sendler</title>
<link href="${pageContext.request.contextPath}/resources/css/sendler.css" rel="stylesheet" type="text/css">
</head>
<body>

	<%@include file="header.jsp"%>

	<h1><fmt:message key="page.sendler.header"/></h1>
	
	<div id="sendler" class="sendler">
	
	<form action="${pageContext.request.contextPath}/sendler?answer=true" method="post">
			<button type="submit"><fmt:message key="page.sendler.yes"/></button>
	</form>
	<form action="${pageContext.request.contextPath}/sendler?answer=false" method="post">
			<button type="submit"><fmt:message key="page.sendler.no"/></button>
	</form>
	</div>


</body>
</html>