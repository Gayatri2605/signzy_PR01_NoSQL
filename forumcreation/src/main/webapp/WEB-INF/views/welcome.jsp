<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<title>Forum Page</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="container">
		<h2>Welcome to Forum!</h2>
		<p>You can post your comments here ! Login to continue</p>
		<form action="login" method="POST">
			<label for="email">Email:</label><br> <input type="email"
				class="form-control" id="email" placeholder="Enter your email"
				name="email"> <br><label for="pwd">Password:</label><br> <input
				type="password" class="form-control" id="pwd"
				placeholder="Enter password" name="pass">
			<button type="submit" class="btn btn-primary">Login</button>
		</form>
		<br>
		<h2>New User Register here !</h2>
		<button id="register_btn" class="btn btn-success">Register</button>
		<div class="modal fade" id="myModal">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<h4 class="modal-title">Register</h4>
						<button type="button" class="close" data-dismiss="modal">×</button>
					</div>
					<div class="modal-body">
						<form action="register" method="POST">
						<div class="form-group">
								<label for="name">Name:</label> <input type="text"
									class="form-control" id="name" name="name">
							</div>
							<div class="form-group">
								<label for="email">Email address:</label> <input type="email"
									class="form-control" id="email" name="email">
							</div>
							<div class="form-group">
								<label for="pwd">Password:</label> <input type="password"
									class="form-control" id="pwd" name="pass">
							</div>
							<div class="form-group">
								<label for="pwd">Confirm Password:</label> <input
									type="password" class="form-control" id="pwd_check">
							</div>
							<button type="submit" class="btn btn-primary">Register</button>
						</form>
					</div>
				</div>
			</div>
		</div>
		<br>
		<h2>${registerResult}</h2>
	</div>
</body>
<script>
	$(document).ready(function() {
		$("#register_btn").click(function() {
			$("#myModal").modal();
		});
	});
</script>
</html>
