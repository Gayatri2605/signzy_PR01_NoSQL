<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Add New Task</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
		<div>&nbsp;</div>
		<h3 id="form_header" class="text-primary" align="center">Ask
			Question Task</h3>
		<div>&nbsp;</div>
		<form id="task_form" method="POST" action="addComment">
			<input id="title_name" type="text" name="comment" />
			<input id="name" type="hidden" name="name" value="${name}"/>
			<input id="email" type="hidden" name="email" value="${email}"/>
			<div>&nbsp;</div>
			<button id="saveBtn" type="submit" class="btn btn-success">Post</button>
		</form>
		<div class="treeview">
			<h6 class="pt-3 pl-3">Forum</h6>
			<hr>
			<c:forEach items="${commentList}" var="comments" >
			<ul>
				<li><i class="fas fa-angle-right rotate"></i> <span>
						${comments.comment} <span><button class="label label-success addReply">Reply</button></span></span> 
						<c:forEach items="${comments.replyList}"
						var="reply" >
					<ul class="nested">
						<li><c:out value="${reply}" /><span><button class="label label-success postReply">Reply</button></span></li>
					</ul> </c:forEach></li>
			</ul>
			</c:forEach>
		</div>
				<div class="modal fade" id="myModal">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<h4 class="modal-title">PostReply</h4>
						<button type="button" class="close" data-dismiss="modal">×</button>
					</div>
					<div class="modal-body">
						<form action="postReply" method="POST">
						<div class="form-group">
								<label for="name">Comment</label> <input type="text"
									class="form-control" id="reply" name="reply">
									<input id="name" type="hidden" name="name" value="${name}"/>
									<input id="email" type="hidden" name="email" value="${email}"/>
							</div>
							<button type="submit" class="btn btn-primary">Reply</button>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
<script>
$(document).ready(function() {
	  $(".addReply").click(function() {
			$("#myModal").modal();
		});
	  $(".postReply").click(function() {
			$("#myModal").modal();
		});
	});
	
	

</script>
</html>