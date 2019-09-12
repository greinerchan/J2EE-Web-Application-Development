<%@page import="java.util.List"%>
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
			<%
				List<String> errors = (List<String>) request.getAttribute("errors");
				if (errors != null) { %>
					<% for (String error : errors) {
				%>				
				<span style="color:red"> <%= error %> </span><br>
			<%
				}
			}
			%>
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