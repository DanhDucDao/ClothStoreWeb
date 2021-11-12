<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Change Profile</title>
	<script type="text/javascript" src="script/jquery-3.6.0.min.js"></script>	
	<script type="text/javascript" src="script/bootstrap.min.js"></script>
	<link href="css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
	<div class="container">
		<jsp:include page="header.jsp"></jsp:include>
		<h1>Update Profile</h1>
		<form action="<%=request.getContextPath()%>/profile" method="post">
			<div class="form-group">
			    <label for="password">Password</label>
			    <input name="password" type="password" class="form-control" id="password" aria-describedby="passwordHelp" value="${user.password}" >
			    <small id="passwordHelp" class="form-text text-muted"></small>
			</div>
			<div class="form-group">
			    <label for="phonenumber">Phone Number</label>
			    <input name="phonenumber"type="text" class="form-control" id="phonenumber" aria-describedby="phonenumberHelp" value="${user.phoneNumber}">
			    <small id="phonenumberHelp" class="form-text text-muted"></small>
			</div>
			<div class="form-group">
			    <label for="email">Email</label>
			    <input name="email" type="text" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" value="${user.email}">
			    <small id="emailHelp" class="form-text text-muted"></small>
			</div>
			<div>
				<input type="submit" value="Update" class="btn btn-success">
			</div>
		</form>
	</div>
</body>
</html>