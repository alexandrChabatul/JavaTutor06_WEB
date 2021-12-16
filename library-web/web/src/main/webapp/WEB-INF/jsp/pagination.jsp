<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>

	<link href="${pageContext.request.contextPath}/resources/css/pagination.css" rel="stylesheet" type="text/css">

	<div id="result" class="result">
	
		<div id="book" class="book">
			<table border="1" cellpadding="5" cellspacing="5">
        		<tr>
            		<th><fmt:message key="page.pagination.name"/></th>
            		<th><fmt:message key="page.pagination.author"/></th>
            		<th><fmt:message key="page.pagination.type"/></th>
       		 	</tr>
 
        		<c:forEach var="book" items="${requestScope.books}">
            		<tr>
               			<td>${book.name}</td>
                		<td>${book.author}</td>
                		<td>${book.type}</td>
           			</tr>
        		</c:forEach>
    		</table>	
		</div>
	
		<div id="page" class="page">
			
			<c:if test="${requestScope.currentPage != 1}">
				<form action="${pageContext.request.contextPath}/books" method="post">
					<input type="hidden" value="${requestScope.currentPage - 1}" name="page">
					<c:if test="${not empty requestScope.name}">
						<input type="hidden" value="${requestScope.name}" name="name">
					</c:if>
					<c:if test="${not empty requestScope.author}">
						<input type="hidden" value="${requestScope.author}" name="author">
					</c:if>
					<button class="simpleButoon" type="submit"><fmt:message key="page.pagination.prev"/></button>
				</form>
			</c:if>	
			
			<c:choose>
				<c:when test="${requestScope.currentPage > (1+2) and requestScope.currentPage < (requestScope.numOfPages - 2)}">
					<form action="${pageContext.request.contextPath}/books" method="post">
						<input type="hidden" value="1" name="page">
						<c:if test="${not empty requestScope.name}">
							<input type="hidden" value="${requestScope.name}" name="name">
						</c:if>
						<c:if test="${not empty requestScope.author}">
							<input type="hidden" value="${requestScope.author}" name="author">
						</c:if>
						<button class="simpleButoon" type="submit">1</button>
					</form>	
					
					<button class="simpleButoon">...</button>
					
					<c:forEach begin="${requestScope.currentPage - 2}" end="${requestScope.currentPage + 2}" var="i">
                		<form action="${pageContext.request.contextPath}/books" method="post">
							<input type="hidden" value="${i}" name="page">
							<c:if test="${not empty requestScope.name}">
								<input type="hidden" value="${requestScope.name}" name="name">
							</c:if>
							<c:if test="${not empty requestScope.author}">
								<input type="hidden" value="${requestScope.author}" name="author">
							</c:if>
							<c:choose>
								<c:when test="${i == requestScope.currentPage}">
									<button class="currentButton" type="submit">${i}</button>
								</c:when>
								<c:when test="${i != requestScope.currentPage}">
									<button class="simpleButoon" type="submit">${i}</button>
								</c:when>	
							</c:choose>
						</form>
            		</c:forEach>
										
					<button class="simpleButoon">...</button>
					
					<form action="${pageContext.request.contextPath}/books" method="post">
						<input type="hidden" value="${requestScope.numOfPages}" name="page">
						<c:if test="${not empty requestScope.name}">
							<input type="hidden" value="${requestScope.name}" name="name">
						</c:if>
						<c:if test="${not empty requestScope.author}">
							<input type="hidden" value="${requestScope.author}" name="author">
						</c:if>
						<button class="simpleButoon" type="submit">${requestScope.numOfPages}</button>
					</form>			
				</c:when>
				
				<c:when test="${requestScope.currentPage > (1 + 2)}">
					<form action="${pageContext.request.contextPath}/books" method="post">
						<input type="hidden" value="1" name="page">
						<c:if test="${not empty requestScope.name}">
							<input type="hidden" value="${requestScope.name}" name="name">
						</c:if>
						<c:if test="${not empty requestScope.author}">
							<input type="hidden" value="${requestScope.author}" name="author">
						</c:if>
						<button class="simpleButoon" type="submit">1</button>
					</form>	
					
					<button class="simpleButoon">...</button>
					
					<c:forEach begin="${requestScope.currentPage - 2}" end="${requestScope.numOfPages}" var="i">
                		<form action="${pageContext.request.contextPath}/books" method="post">
							<input type="hidden" value="${i}" name="page">
							<c:if test="${not empty requestScope.name}">
								<input type="hidden" value="${requestScope.name}" name="name">
							</c:if>
							<c:if test="${not empty requestScope.author}">
								<input type="hidden" value="${requestScope.author}" name="author">
							</c:if>
							<c:choose>
								<c:when test="${i == requestScope.currentPage}">
									<button class="currentButton" type="submit">${i}</button>
								</c:when>
								<c:when test="${i != requestScope.currentPage}">
									<button class="simpleButoon" type="submit">${i}</button>
								</c:when>	
							</c:choose>
						</form>
            		</c:forEach>			
				</c:when>
				
				<c:when test="${requestScope.currentPage < (requestScope.numOfPages - 2)}">
					<c:forEach begin="1" end="${requestScope.currentPage + 2}" var="i">
                		<form action="${pageContext.request.contextPath}/books" method="post">
							<input type="hidden" value="${i}" name="page">
							<c:if test="${not empty requestScope.name}">
								<input type="hidden" value="${requestScope.name}" name="name">
							</c:if>
							<c:if test="${not empty requestScope.author}">
								<input type="hidden" value="${requestScope.author}" name="author">
							</c:if>
							<c:choose>
								<c:when test="${i == requestScope.currentPage}">
									<button class="currentButton" type="submit">${i}</button>
								</c:when>
								<c:when test="${i != requestScope.currentPage}">
									<button class="simpleButoon" type="submit">${i}</button>
								</c:when>	
							</c:choose>
						</form>
            		</c:forEach>
										
					<button class="simpleButoon">...</button>
					
					<form action="${pageContext.request.contextPath}/books" method="post">
						<input type="hidden" value="${requestScope.numOfPages}" name="page">
						<c:if test="${not empty requestScope.name}">
							<input type="hidden" value="${requestScope.name}" name="name">
						</c:if>
						<c:if test="${not empty requestScope.author}">
							<input type="hidden" value="${requestScope.author}" name="author">
						</c:if>
						<button class="simpleButoon" type="submit">${requestScope.numOfPages}</button>
					</form>			
				</c:when>
				
				<c:when test="${requestScope.numOfPages < 4}">
					<c:forEach begin="1" end="${requestScope.numOfPages}" var="i">
               	 		<form action="${pageContext.request.contextPath}/books" method="post">
							<input type="hidden" value="${i}" name="page">
							<c:if test="${not empty requestScope.name}">
								<input type="hidden" value="${requestScope.name}" name="name">
							</c:if>
							<c:if test="${not empty requestScope.author}">
								<input type="hidden" value="${requestScope.author}" name="author">
							</c:if>
							<c:choose>
								<c:when test="${i == requestScope.currentPage}">
									<button class="currentButton" type="submit">${i}</button>
								</c:when>
								<c:when test="${i != requestScope.currentPage}">
									<button class="simpleButoon" type="submit">${i}</button>
								</c:when>	
							</c:choose>
						</form>
            		</c:forEach>
				</c:when>		
				
			</c:choose>	
			
			<c:if test="${requestScope.currentPage != requestScope.numOfPages}">
				<form action="${pageContext.request.contextPath}/books" method="post">
					<input type="hidden" value="${requestScope.currentPage + 1}" name="page">
					<c:if test="${not empty requestScope.name}">
						<input type="hidden" value="${requestScope.name}" name="name">
					</c:if>
					<c:if test="${not empty requestScope.author}">
						<input type="hidden" value="${requestScope.author}" name="author">
					</c:if>
					<button class="simpleButoon" type="submit"><fmt:message key="page.pagination.next"/></button>
					<br>
				</form>
			</c:if>	
				
		</div>
	
	</div>	
