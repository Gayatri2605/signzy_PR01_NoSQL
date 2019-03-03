<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>TODO List</title>
	    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
	</head>
	<body>
		<div class="container">
		<div>&nbsp;</div>
			<h2 id="article_header" class="text-primary" align="center">ToDo List</h2>
	    	<div>&nbsp;</div>
	    	
	    	<!-- Div to add a new task to the mongo database -->
	    	<div id="add_new_task">
	    			<c:url var="addUrl" value="/mainList/add" /><a id="add" href="${addUrl}" class="btn btn-primary">Add Task</a>
	    	</div>
	    	<div>&nbsp;</div>
			
	    	<!-- Table to display the user's to do list from the mongo database -->
	    	<table id="users_task" class="table">
	        	<thead>
	            	<tr align="center">
	            		<th>Title</th><th>Description</th><th colspan="2"></th>
	            	</tr>
	        	</thead>
	        	<tbody>
	            	<c:forEach items="${taskLists}" var="tasks">
	                	<tr align="center">
	                    	<td><c:out value="${tasks.title}" /></td>
	                    	<td><c:out value="${tasks.desc}" /></td>
	                    	<td>
	                        	<c:url var="editUrl" value="/mainList/edit?title=${tasks.title}" /><a id="update" href="${editUrl}" class="btn btn-info">Update</a>
	                    	</td>
	                    	<td>
	                        	<c:url var="deleteUrl" value="/mainList/delete?title=${tasks.title}" /><a id="delete" href="${deleteUrl}" class="btn btn-danger">Delete</a>
	                    	</td>
	                	</tr>
	            	</c:forEach>
	        	</tbody>
	    	</table>
		</div>	    
	</body>
</html>