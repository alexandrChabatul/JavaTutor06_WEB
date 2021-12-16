<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>

<link href="${pageContext.request.contextPath}/resources/css/header.css" rel="stylesheet" type="text/css">

	<div id="header" class="header">
		<div>
			<a href="${pageContext.request.contextPath}/books">
				<img id="logo" class="logo" src="${pageContext.request.contextPath}/resources/img/library-logo.png">
			</a>
		</div>
		<div id="params" class="params">
		
			<div id="locale" class="locale">
				<form action="${pageContext.request.contextPath}/locale" method="post">
					<button type="submit" name="lang" value="ru_RU">RU</button>
					<button type="submit" name="lang" value="en_US">EN</button>
				</form>
		
				<fmt:setLocale value="${sessionScope.lang != null ? sessionScope.lang : (param.lang != null ? param.lang : 'en_US')}"/>
				<fmt:setBundle basename="translations"/>
			</div>
		
			<div id="but" class="but">
				<c:if test="${empty sessionScope.user}">
					<form action="${pageContext.request.contextPath}/login" method="get">
						<button type="submit"><fmt:message key="page.header.login"/></button>
					</form>
					<form action="${pageContext.request.contextPath}/registration" method="get">
						<button type="submit"><fmt:message key="page.header.registration"/></button>
					</form>
				</c:if>
				<c:if test="${not empty sessionScope.user}">
					<form action="${pageContext.request.contextPath}/logout" method="post">
						<button type="submit"><fmt:message key="page.header.logout"/></button>
					</form>
				</c:if>
			</div>			
		</div>
			
	</div>	
