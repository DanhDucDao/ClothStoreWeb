<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Đặt hàng</title>
	<script type="text/javascript" src="script/jquery-3.6.0.min.js"></script>	
	<script type="text/javascript" src="script/bootstrap.min.js"></script>
	<link href="css/bootstrap.min.css" rel="stylesheet">
	<link href="css/order.css" rel="stylesheet">
	</head>
<body>
	<div class="container">
	<jsp:include page="header.jsp"></jsp:include>
	<h1>Order</h1>
	<form action="" id="orderForm">
		<div class="row">
			<div id="infoPart" class="col-sm-12 col-md-4" >
				<h5>Thông tin chung</h5>
				<div class="form-group">
					<label for="nameInput">Họ và tên*</label>
					<input type="text" name="name" id="nameInput" class="form-control" aria-describedly="nameHint" placeholder="Họ tên">
					<small id="nameHint" class="form-text text-muted"></small> 
				</div>
				
				<div class="row">
					<div class="col">
					<div class="form-group">
						<label for="emailInput">E-mail*</label>
						<input type="email" name="email" id="emailInput" class="form-control" aria-describedly="emailHint" placeholder="Email">
						<small id="emailHint" class="form-text text-muted"></small> 
					</div>
					</div>
					<div class="col">
					<div class="form-group">
						<label for="phoneInput">Số điện thoại*</label>
						<input type="text" name="phone" id="phoneInput" class="form-control" aria-describedly="phoneHint" placeholder="Số điện thoại">
						<small id="phoneHint" class="form-text text-muted"></small> 
					</div>
					</div>
				</div>
				
				<div class="form-group">
					<label for="addressInput">Địa chỉ*</label>
					<input type="text" name="address" id="addressInput" class="form-control" aria-describedly="addressHint" placeholder="Địa chỉ">
					<small id="addressHint" class="form-text text-muted"></small> 
				</div>
				
				<div class="form-group">
					<label for="subDistrictInput">Phường xã*</label>
					<input type="text" name="subDistrict" id="subDistrictInput" class="form-control" aria-describedly="subDistrictHint" placeholder="Phường - xã">
					<small id="subDistrictHint" class="form-text text-muted"></small> 
				</div>
				
				<div class="form-group">
					<label for="districtInput">Quận huyện*</label>
					<input type="text" name="district" id="districtInput" class="form-control" aria-describedly="districtHint" placeholder="Quận - huyện">
					<small id="districtHint" class="form-text text-muted"></small> 
				</div>
				
				<div class="form-group">
					<label for="cityInput">Thành phố*</label>
					<input type="text" name="city" id="cityInput" class="form-control" aria-describedly="cityHint" placeholder="Tỉnh - Thành phố">
					<small id="cityHint" class="form-text text-muted"></small> 
				</div>
				
				
			</div>
			<div id="infoPart" class="col-sm-12 col-md-4" >
				<h5>Phương thức thanh toán</h5>
				<div class="form-check">
					<input class="form-check-input" type="radio" name="pay" id="payMethod" value="cash" aria-describedly="payHint">
					<label class="form-check-label" for="payMethod">Thanh toán sau khi nhận hàng</label>
					<small id="payHint" class="form-text text-muted"></small> 
				</div>
				
				<div class="form-group">
					<label for="codeInput">Mã giảm giá</label>
					<input type="text" name="code" id="codeInput" class="form-control" aria-describedly="codeHint" placeholder="xxx-yyy-zzz">
					<small id="codeHint" class="form-text text-muted"></small> 
				</div>
			</div>
			<div class="col-sm-12 col-md-4" >
			<h5>Order</h5>
			<div class="table-wrapper" style="overflow: auto; max-height: 350px; display: inline-block;">
			<table class="table" style="font-size: 10px">
				<thead>
					<tr>
						<th scope="col">Tên sản phẩm</th>
						<th scope="col">Hình ảnh</th>
						<th scope="col">Số lượng</th>
						<th scope="col">Đơn giá</th>
						<th scope="col">Thành tiền</th>
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
						<td>${item.quantity}</td>
						<td>${item.cloth.price}</td>
						<td class="price">${item.cloth.price * item.quantity}</td>
					</tr>
					</c:forEach>
				</tbody>
			</table>
			
			</div>
			
			<div id="actionContainer" style="float: right; text-align: right">
				<label for="total-price">Tổng tiền</label>
				<p id="total-price"></p>
				<input type="submit" class="btn btn-success" value="Đặt hàng">
				<button class="btn btn-danger">Tiếp tục mua sắm</button>
			</div>
			
		</div>
		</div>
	</form>
		
	</div>
	
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
	  
	      </div>
	    </div>
	  </div>
	</div>
	
	<script type="text/javascript" src="script/order.js"></script>
</body>
</html>