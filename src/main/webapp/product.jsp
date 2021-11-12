<%@page import="com.mysql.cj.jdbc.ha.ReplicationMySQLConnection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<title>${category.name}</title>
		<link href="css/product-card.css" rel="stylesheet" type="text/css">
		<link href="css/filter-bar.css" rel="stylesheet" type="text/css">
	</head>

	<body>
		<div class="container">
			<jsp:include page="header.jsp"></jsp:include>
			
			<button id="btn-open-filter">Filter</button>
			
			<div id="filter-bar" class="deactivate">
			<form action="/product?">
				<h1>Màu sắc</h1>
				<c:forEach var="color" items="${listColor}">
					<input type="radio" class="colorChooser" id="color-${color.colorID}" name="color" value="${color.colorID}">
					<label for="${color.colorID}"><c:out value="${color.abbreviation}"></c:out></label>
				</c:forEach>
				<h1>Giá tiền</h1>
					<label for="minPrice"> Min
					<input type="text" id="minPrice" name="minPrice">
					</label>
					<label for="maxPrice"> Max
					<input type="text" id="maxPrice" name="maxPrice">
					</label>
				<h1>Kích cỡ</h1>
				<c:forEach var="size" items="${listSize}">
					<input type="radio" class="sizeChooser" id="size-${size.sizeID}" name="size" value="${size.sizeID}">
					<label for="${size.sizeID}"><c:out value="${size.name}"></c:out></label>
				</c:forEach>
				<br>
				<input type="submit" value="Lọc" id="btn-filter">
				<input type="submit" value="Đóng" id="btn-filter-close">
			</form>	
			</div>
			
			
			<div id="main-view">
			<h1>${category.name}</h1>
			<c:forEach var="cloth" items="${listClothes}">
				<div class="cloth-card">
					<img alt="${cloth.name}" src="${cloth.mainImageUrl}">
					<a href="viewProductDetail?id=${cloth.clothId}">View Details</a>
				</div>			
			</c:forEach>
			</div>
			<br style="clear: left;">
			
			<h1>Hiển thị ${startDisplay} - ${endDisplay} trong tổng số ${totalQuantity} kết quả</h1>
			
			<c:if test="${param.page == null }">
				<c:if test="${totalQuantity/quantityDisplay>1}">
				<a href="" id="nextPage">Next</a>
				</c:if>
			</c:if>
			<c:if test="${param.page > 1}">
				<a href="" id="previousPage">Previous</a>
			</c:if>
			<c:if test="${param.page <= (totalQuantity/quantityDisplay)}">
				<a href="" id="nextPage">Next</a>
			</c:if>
			
			
			<jsp:include page="footer.jsp"></jsp:include>
		</div>
		
		<script type="text/javascript" src="script/product.js"></script>
		<script type="text/javascript" src="script/filter.js"></script>
	</body>
</html>