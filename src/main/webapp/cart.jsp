
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Cart</title>
	<script type="text/javascript" src="script/jquery-3.6.0.min.js"></script>	
	<script type="text/javascript" src="script/bootstrap.min.js"></script>
	<link href="css/bootstrap.min.css" rel="stylesheet">
	<link href="css/cart.css" rel="stylesheet">
</head>
<body>
	<div class="container">
		<jsp:include page="header.jsp"></jsp:include>
		<h1>Cart</h1>
		<table class="table">
			<thead>
				<tr>
					<th scope="col">Tên sản phẩm</th>
					<th scope="col">Hình ảnh</th>
					<th scope="col">Số lượng</th>
					<th scope="col">Đơn giá</th>
					<th scope="col">Thành tiền</th>
					<th scope="col">Lựa chọn</th>	
				</tr>
			</thead>
			<tbody>
				<c:forEach var="item" items="${listLineItem}">
				
					<c:set target="${item.color.color.colorID}" var="s" value="${-1}" scope="request"></c:set>
				
				<tr>
					<td>${item.cloth.name} ${item.color.color.abbreviation} ${item.size.name}</td>
					<td class="w-25"> 
					<c:if test="${empty item.color.imageUrl}">
					<img src="${item.cloth.mainImageUrl}" class="img-fluid img-thumnail" style="max-height: 150px">
					</c:if>
					<c:if test="${!empty item.color.imageUrl}">
					<img src="${item.color.imageUrl}" class="img-fluid img-thumnail" style="max-height: 150px">
					</c:if>
					<td><input type="number" class="form-control" id="" 
						data-clothId="${item.cloth.clothId}" 
						data-colorId="${item.color.color.colorID}" 
						data-sizeId="${item.size.sizeID}" 
						value="${item.quantity}"
						min="1">
					</td>
					<td>${item.cloth.price}</td>
					<td class="price">${item.cloth.price * item.quantity}</td>
					<td>
						<button class="btn btn-success" 
							data-clothId="${item.cloth.clothId}" 
							data-colorId="${item.color.color.colorID}" 
							data-sizeId="${item.size.sizeID}"
							data-op="update">Update</button> 
						<button class="btn btn-danger" 
							data-clothId="${item.cloth.clothId}" 
							data-colorId="${item.color.color.colorID}" 
							data-sizeId="${item.size.sizeID}"
							data-op="delete">Delete</button>
					</td>
				</tr>
				</c:forEach>
			</tbody>
		</table>
		<div id="cart-footer">
			<div id="total-price-containter">
			<label>Total:</label>
				<p id="total-price"></p>
			</div>
			<a href="/ClothStoreWebDevelopement/order" class="btn btn-success">Mua ngay</a>
			<a href="/ClothStoreWebDevelopement/product" class="btn btn-danger">Tiếp tục mua sắm</a>
		</div>
		
		
	</div>
	
	<!--Response Modal Go here!-->
	<div class="modal fade" id="responseModal" tabindex="-1" aria-labelledby="responseModal" aria-hidden="true">
	  <div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h5 class="modal-title">Modal title</h5>
	        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
	      </div>
	      <div class="modal-body">
	        ...
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
	      </div>
	    </div>
	  </div>
	</div>
	<!--Response Modal end here!-->
	
	<script type="text/javascript" src="script/cart.js"></script>
</body>
</html>