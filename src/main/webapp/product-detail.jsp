<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>${cloth.name}</title>
		<link href="css/product-detail.css" rel="stylesheet" type="text/css">
		
		<script type="text/javascript" src="script/jquery-3.6.0.min.js"></script>
		
		<script type="text/javascript" src="script/bootstrap.min.js"></script>
		<link href="css/bootstrap.min.css" rel="stylesheet">
		
	</head>
	
	<body>
		<div class="container">
			<jsp:include page="header.jsp"></jsp:include>
			
			<div id="main-view">
				<h2>${cloth.name}</h2>
				<img id="mainImage" alt="${cloth.name}" src="${cloth.mainImageUrl}">
				<br>
				<form id="actionForm" action="" method="post">
					<input type="hidden" name="clothID" value="${cloth.clothId}">
					<c:if test="${fn:length(cloth.avaiableColors) gt 0}">
						<label > Choose color <br>
						<c:forEach var="itemcolor" items="${cloth.avaiableColors}">
						<input type="radio" name="color" class="colorChooser"value="${itemcolor.color.colorID}" data-image="${itemcolor.imageUrl}">${itemcolor.color.name}
						</c:forEach></label>
					</c:if>
					
					<c:if test="${fn:length(cloth.avaiableSizes) gt 0}">
						<label> Choose size <br>
						<c:forEach var="size" items="${cloth.avaiableSizes}">
						<input type="radio" class="sizeChooser" name="size" value="${size.sizeID}">${size.name}
						</c:forEach>
						</label>
					</c:if>
					
					<label>Quantity <br>
					<input type="number" name="quantity" min="1" value="1"></label>
					<br>	
					<input type="submit" value="AddToCart"> 
				</form>
			</div>
			
			<h1 id="test"></h1>
			<div id="gallery" style="clear:left">
				<h1>Gallery</h1>
				<c:forEach var="image" items="${listImages}">
					<img src="${image}">
				</c:forEach>
			</div>
			
			<br style="clear: left">
			<jsp:include page="footer.jsp"></jsp:include>
			
			<!-- Modal -->
			<div class="modal fade" id="responseModal" tabindex="-1" aria-labelledby="responseModal" aria-hidden="true">
			  <div class="modal-dialog">
			    <div class="modal-content">
			      <div class="modal-header">
			        <h5 class="modal-title" id="responseModal">Modal title</h5>
			        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
			      </div>
			      <div class="modal-body">
			        ...
			      </div>
			      <div class="modal-footer">
			        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
			        <a href="/ClothStoreWebDevelopement/cart"><button type="button" class="btn btn-primary">Đi tới giỏ hàng</button></a>
			      </div>
			    </div>
			  </div>
			</div>
		</div>
		
		
		<script type="text/javascript" src="script/product-detail.js" charset="UTF-8"></script>
	</body>
</html>