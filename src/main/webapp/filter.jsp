<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	body {
	margin: 0;
	}
	#filter-bar {
		position: absolute;
		border: 1px black solid;
		background: white;
		z-index:5;
		max-width: 200px;
		display: none;
	}
</style>
</head>
<body>
	<div id="filter-bar">
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
		<input type="submit" value="Lọc" id="btn-filter">
	</form>	
	</div>
	
	<script type="text/javascript" src="script/filter.js"></script>
</body>
</html>