<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8"/>
        <title>My Blog -- Error Page</title>
    </head>
    
	<body>
	
		<h2>Sorry, Errors Occur</h2>
		<c:forEach var="error" items="${errors}">
			<span style="color:red"> ${error} </span><br>
		</c:forEach>
		
		
		
       <c:choose>
             <c:when test="${(empty user)}">
			Click <a href="login.do">here</a> to login.
			 </c:when>
			<c:otherwise>
			Click <a href="home.do">here</a> to return to the Blog.
			</c:otherwise>
		</c:choose>
	
	</body>
</html>