<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Mail result</title>
<link href="${pageContext.request.contextPath}/resources/css/sendlerResult.css" rel="stylesheet" type="text/css">
</head>
<body>

	<%@include file="header.jsp"%>
	
	<div id="mailResult" class="mailResult">
	
	<c:if test="${requestScope.result eq 'good'}">
			<span><fmt:message key="page.sendlerResult.success"/></span>
	</c:if>
	
	<c:if test="${requestScope.result eq 'fail'}">
			<span><fmt:message key="page.sendlerResult.fail"/></span>
	</c:if>
	
	<form action="${pageContext.request.contextPath}/books" method="get">
			<button type="submit"><fmt:message key="page.sendlerResult.home"/></button>
	</form>
	</div>
	

</body>
</html>