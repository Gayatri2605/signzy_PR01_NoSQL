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
<style>
ul, #myUL {
	list-style-type: none;
}

#myUL {
	margin: 0;
	padding: 0;
}

.caret {
	cursor: pointer;
	-webkit-user-select: none; /* Safari 3.1+ */
	-moz-user-select: none; /* Firefox 2+ */
	-ms-user-select: none; /* IE 10+ */
	user-select: none;
}

.caret::before {
	content: "\25B6";
	color: black;
	display: inline-block;
	margin-right: 6px;
}

.caret-down::before {
	-ms-transform: rotate(90deg); /* IE 9 */
	-webkit-transform: rotate(90deg); /* Safari */ '
	transform: rotate(90deg);
}

.nested {
	display: none;
}

.active {
	display: block;
}
</style>
</head>
<body>
	<div class="container">
		<div>&nbsp;</div>
		<h3 id="form_header" class="text-primary" align="center">Ask
			Question Task</h3>
		<div>&nbsp;</div>
		<form id="task_form" method="POST" action="addComment">
			<!-- 			<input id="comment" type="textarea" name="comment" /> -->
			<input id="name" type="hidden" name="name" value="${name}" /> <input
				id="userId" type="hidden" name="userId" value="${userId}" />
			<div>&nbsp;</div>
			<button id="saveBtn" type="submit" class="btn btn-success">Post</button>
			<div class="form-group">
				<label for="comment">Comment:</label>
				<textarea class="form-control" id="comment" name="comment" rows="5"
					form="task_form"></textarea>
			</div>
		</form>
		<div>
		<ul id="myUL">
		<c:forEach items="${commentList}" var="comments">
			<li><span class="caret">${comments.comment}</span>        <span><label class="label label-success addReply">Reply</label></span></span>
			 <c:if
					test="${comments.reply != ''}">
					<ul class="nested">
						<li>${comments.reply}</li>
					</ul>
				</c:if></li>
		</c:forEach>
	</ul>
		</div>
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
									<input id="email" type="hidden" name="email" value="${userId}"/>
							</div>
							<button type="submit" class="btn btn-primary">Reply</button>
						</form>
					</div>
				</div>
			</div>
		</div>
</body>
<script>
var toggler = document.getElementsByClassName("caret");
var i;

for (i = 0; i < toggler.length; i++) {
  toggler[i].addEventListener("click", function() {
    this.parentElement.querySelector(".nested").classList.toggle("active");
    this.classList.toggle("caret-down");
  });
}

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