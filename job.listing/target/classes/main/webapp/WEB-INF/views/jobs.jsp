<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>Job List</title>
	    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
	</head>
	<body>
		<div class="container">
		<div>&nbsp;</div>
			<h2 id="article_header" class="text-primary" align="center">Job List</h2>
	    	<div>&nbsp;</div>
	    	
	    	<!-- Table to display the user's to do list from the mongo database -->
	    	<table id="users_task" class="table">
	        	<thead>
	            	<tr align="center">
	            		<th>Job Title</th><th>Location</th><th colspan="2"></th>
	            	</tr>
	        	</thead>
	        	<tbody>
	            	<c:forEach items="${jobList}" var="job">
	                	<tr align="center">
	                    	<td><c:out value="${jobList.title}" /></td>
	                    	<td><c:out value="${jobList.location}" /></td>
	                	</tr>
	            	</c:forEach>
	        	</tbody>
	    	</table>
		</div>	    
	</body>
</html>