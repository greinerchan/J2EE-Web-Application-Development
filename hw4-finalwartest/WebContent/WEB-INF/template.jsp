<%@page import="xc4.hw5.databean.User"%>
<%@page import="xc4.hw5.model.UserDAO"%>
<%@page import= "javax.servlet.http.HttpServletRequest" %>
<%@page import= "javax.servlet.http.HttpSession" %>
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
				<% 
				HttpSession session2 = request.getSession();
				User[] users = (User[])session2.getAttribute("users"); 
				User currUser = (User)session2.getAttribute("user");
				if (users == null || users.length == 0){ %>
				<form action="login.do" method="Post">
				  <button type="submit" name="button" value="Login" class="btn-link">Login</button>
				</form>
				<form action="register.do" method="Post">
				  <button type="submit" name="button" value="Register" class="btn-link">Register</button>
				</form><br><br>      
                <% } 
		        if (users != null && users.length > 0 && currUser == null) { %>

				<form action="login.do" method="Post">
				  <button type="submit" name="button" value="Login" class="btn-link">Login</button>
				</form>
				<form action="register.do" method="Post">
				  <button type="submit" name="button" value="Register" class="btn-link">Register</button>
				</form><br><br>          
		          <%
		          for (int i = 0; i < users.length; i++) {
		        	  User user = users[i];
				  	  String name = user.getFirstName() + " " + user.getLastName();
				 %>				
				<form action="visitor.do" method="Post">
				  <input type="hidden" name="username" value= <%=name%>>
				  <button type="submit" name="visitor" value="Visitor" class="btn-link"><%=name%></button>
				</form>
			    <%
					}
				}
				if (users != null && users.length > 0 && currUser != null){  %>
				<form action="user.do" method="Post">
				  <button type="submit" name="button" value="Home" class="btn-link">Home</button>
				</form>
				<form action="logout.do" method="Post">
				  <button type="submit" name="button" value="Logout" class="btn-link">Logout</button>
				</form><br><br>      
		          <%
		          for (int i = 0; i < users.length; i++) {
		        	  User user = users[i];
				  	  String name = user.getFirstName() + " " + user.getLastName();
				 %>			
                <form method="POST" action="user.do">
                    <input type="hidden" name="username" value= <%= name %>>
                    <button type="submit" name = "button" value = "User" class="btn-link"><%=name%></button>
                </form>			
				<%}
			}
			%>
</div>



	