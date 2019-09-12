<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8"/>
        <title>Login</title>
    		<link rel="stylesheet" href="template.css">
    </head>
    <body>
		<jsp:include page="template.jsp" />
			<div class="login">
				<h3>Log In Family Union</h3>
				
		<c:forEach var="error" items="${errors}">
			<span style="color:red"> ${error} </span><br>
		</c:forEach>

			<form action="login.do" method="POST">
					<div class="main">
					<table>
			            <tr>
							<td><label for="email">E-mail :</label></td>
							<td><input type="text" name = "email" size = "28" value="${form.email}"/></td>
						</tr>
						
						<tr>
							<td><label for="password">Password : </label></td>
							<td><input type="password" name = "password" size = "28" value=""/></td>
						</tr>
						</table>
						  <br>
						  <div class = "button"> 
							<input type="submit" name="button" style="width:90px;height:25px;" value="Submit" />
						    <input type="submit" name="button" style="width:90px;height:25px;" value="Register" />
						    <input type="submit" name="button" style="width:90px;height:25px;" value="Login" />
						  </div>	
					   </div>
			</form>		
		</div>
	</body>
</html>