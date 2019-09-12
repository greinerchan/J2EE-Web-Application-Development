<%@ page import="xc4.hw5.databean.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8"/>
        <title>Family Blog</title>
    	<link rel="stylesheet" href="template.css">
    </head>
    <body>
		<jsp:include page="template.jsp" />
		<div class = "owner">
		<h3>${param.username}'s Blog</h3>
		</div>
	<div class="mycomment">      		
    <c:forEach var="post" items="${posts}"> 
        <span style="text-align:float:left;display:inline-block;">
            <c:out value="${post.posts}" />
            <font style="font-style:italic;"> --${post.date}</font><br><br>
        </span>
			 
	          
        <c:forEach var="comment" items="${comments}">
	    <c:if test="${comment.commentId == post.id }">			
		<form method="POST" action="commentDelete.do">		
		<span style="text-align:float:left;padding-left:50px;display:inline-block;">         
            <font style="font-weight:bold; font-style:italic;">
				Comment by ${comment.userName} - 
            </font>
            <c:out value="${comment.comments}" /> 
            <font style="font-style:italic;"> -- ${comment.date}</font></span><br><br>           
        </form>          
	    </c:if>
	    
        </c:forEach>
        <form class= "comment" method="POST" action="login.do">
	        <input type = "text" size = "30" name="comment">
	    	<button style="width:80px;height:20px;" type="submit" name="button" value="commentButton">Comment</button><br><br><br>
			<input type="hidden" name = "id" value="${post.id}">
    	</form>
        </c:forEach>
            
</div>			
	</body>
</html>