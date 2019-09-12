<%@page import="xc4.hw5.databean.User"%>
<%@page import="xc4.hw5.model.UserDAO"%>
<%@ page import="xc4.hw5.databean.PostBean" %>
<%@ page import="xc4.hw5.databean.CommentBean" %>
<%@page import="xc4.hw5.model.PostDAO"%>
<%@page import="xc4.hw5.model.CommentDAO"%>
<%@page import="java.util.Date"%>
<%@page import= "javax.servlet.http.HttpServletRequest" %>
<%@page import= "javax.servlet.http.HttpSession" %>
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
   		<h3>${user.firstName} ${user.lastName}'s Home Page</h3>
	</div>	
<div class="mycomment">      		
    <c:forEach var="post" items="${posts}"> 
        <form method="POST" action="postDelete.do">
        <span style="text-align:float:left;display:inline-block;">
            <input type="hidden" name="id" value="${post.id}" />
            <button type="submit" style="width:21px;height:21px;" >X</button>
            <c:out value="${post.posts}" />
            <font style="font-style:italic;"> --${post.date}</font><br><br>
        </span>
        </form>  			 
	          
        <c:forEach var="comment" items="${comments}">
			<c:if test="${comment.commentId == post.id }">
		<form method="POST" action="commentDelete.do">	
		    <span style="text-align:float:left;padding-left:50px;display:inline-block;">
			<input type="hidden" name="id" value="${comment.id}" />
            <button type="submit"style="width:21px;height:21px;">X</button>
            <font style="font-weight:bold; font-style:italic;">
				Comment by ${comment.userName} - 
            </font>
            <c:out value="${comment.comments}" /> 
            <font style="font-style:italic;"> -- ${comment.date}</font></span><br><br>
        </form>
			</c:if>
        </c:forEach>
        <form class= "comment" method="POST" action="homeComment.do">
	        <input type = "text" size = "30" name="comment">
	    	<button style="width:80px;height:20px;" type="submit" name="button" value="commentButton">Comment</button><br><br><br>
			<input type="hidden" name = "id" value="${post.id}">
    	</form>
        </c:forEach>
            
		<form  method="POST" action="post.do">
			<textarea  rows="5" cols="45" id="post" placeholder="Give us a new post" name="post"></textarea>
			<div class="postButton">
		    <button type="submit" name="button" value="postButton">Post</button>
			</div>
		</form>
</div>			
	</body>
</html>