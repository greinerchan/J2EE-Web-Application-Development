<%@page import="xc4.hw5.databean.User"%>
<%@page import="xc4.hw5.model.UserDAO"%>
<%@page import= "javax.servlet.http.HttpServletRequest" %>
<%@page import= "javax.servlet.http.HttpSession" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>myBlog</title>
</head>
<div class="title">
<h2>MyFamily</h2>
	<span>Welcome to my family blog!</span>
</div>

<div class="navbar">
         	 <c:choose>
                <c:when test="${(empty user)}">
				<form action="login.do" method="Post">
				  <button type="submit" name="button" value="Login" class="btn-link">Login</button>
				</form>
				<form action="register.do" method="Post">
				  <button type="submit" name="button" value="Register" class="btn-link">Register</button>
				</form><br><br>   
                </c:when>
                  <c:otherwise>
                <form action="home.do" method="Post">
				  <button type="submit" name="button" value="Home" class="btn-link">Home</button>
				  </form>
				  <form action="logout.do" method="Post">
				  <button type="submit" name="button" value="Logout" class="btn-link">Logout</button>
				</form><br><br>     
    			</c:otherwise>
             </c:choose>   
            
          <c:choose>
            <c:when test="${(empty user)}">
            <c:forEach var="user" items="${users}">
                  <form method="POST" action="visitor.do">
                    <input type="hidden" name="username" value= "${user.firstName} ${user.lastName}">
                    <input type="hidden" name="email" value= "${user.email}">
                    <button type="submit" class="btn-link">${user.firstName} ${user.lastName}</button>
                </form>	
            </c:forEach>
            </c:when>
            <c:otherwise>
            <c:forEach var="user" items="${users}">
                <form method="POST" action="user.do">
                  <input type="hidden" name="username" value= "${user.firstName} ${user.lastName}">
                  <input type="hidden" name="email" value= "${user.email}">
                  <button type="submit" class="btn-link">${user.firstName} ${user.lastName}</button>
                </form>	
           </c:forEach>
           </c:otherwise>
           </c:choose>
</div>



	