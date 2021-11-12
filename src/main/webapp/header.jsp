<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link href="css/navigation.css" rel="stylesheet"/>
</head>
<body>
	
	<div class="nav">
		<ul>
			<c:forEach var="productType" items ="${listMenu}">
			<li><c:out value="${productType.name}"></c:out>
				<ul class="sub-menu">
					<c:forEach var="category" items ="${productType.listCategories}">
					<li><a href="product?categoryId=${category.categoryID}"><c:out value="${category.name}"></c:out></a></li>
					</c:forEach>
				</ul>
			</li>
			</c:forEach>
			
			<li id="cart"><a href="/ClothStoreWebDevelopement/cart">Cart</a></li>
			<c:if test="${empty user}">
				<li id="user"><a href="/ClothStoreWebDevelopement/login.jsp">Login</a></li>
			</c:if>
			<c:if test="${!empty user}">
				<li id="user"><a href="/ClothStoreWebDevelopement/profile">${user.username}</a></li>
			</c:if>
		</ul>
	</div>
	
</body>
</html>