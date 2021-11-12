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
		<h1>Update Profile</h1>
		<form action="<%=request.getContextPath()%>/register" method="post">
			<div class="form-group">
			    <label for="userName">User Name</label>
			    <input name="userName" type="text" class="form-control" id="userName" aria-describedby="passwordHelp" value="${username}">
			    <small id="userNameHelp" class="form-text text-muted">${usernameHint}</small>
			</div>
			<div class="form-group">
			    <label for="password">Password</label>
			    <input name="password" type="password" class="form-control" id="password" aria-describedby="passwordHelp" value="${password}">
			    <small id="passwordHelp" class="form-text text-muted">${passwordHint}</small>
			</div>
			<div class="form-group">
			    <label for="phonenumber">Phone Number</label>
			    <input name="phonenumber"type="text" class="form-control" id="phonenumber" aria-describedby="phonenumberHelp" value="${phoneNumber}">
			    <small id="phonenumberHelp" class="form-text text-muted">${phoneNumberHint}</small>
			</div>
			<div class="form-group">
			    <label for="email">Email</label>
			    <input name="email" type="text" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp"  value="${email}">
			    <small id="emailHelp" class="form-text text-muted">${emailHint}</small>
			</div>
			<div>
				<input type="submit" value="Update" class="btn btn-success">
			</div>
		</form>
	</div>
</body>
</html>