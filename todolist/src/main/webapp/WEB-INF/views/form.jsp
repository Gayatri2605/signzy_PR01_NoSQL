<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
	    <title>Add New Task</title>
	    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
	</head>
	<body>
	    <div class="container">
	    <div>&nbsp;</div>
	        <h3 id="form_header" class="text-primary" align="center">Add New Task</h3>
	        <div>&nbsp;</div>
	
			<!-- User input form to add a new task or update the existing task-->
	        <c:url var="saveUrl" value="/mainList/save" />
	        <form:form id="user_form" modelAttribute="taskAttr" method="POST" action="${saveUrl}">
	        <form:hidden path="mode" />
	            <label for="title">Title:</label>
	            <form:input id="title_name" cssClass="form-control" path="title" />
	            <div>&nbsp;</div>
	            <label for="desc">Description</label>
	            <form:input id="description" cssClass="form-control" path="desc" />
	            <div>&nbsp;</div>

	            <button id="saveBtn" type="submit" class="btn btn-success">Save</button>
	        </form:form>
	    </div>
	</body>
</html>