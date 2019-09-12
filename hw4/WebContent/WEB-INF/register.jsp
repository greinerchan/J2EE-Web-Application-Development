<%@page import="java.util.List"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8"/>
        <title>Register</title>
    		<link rel="stylesheet" href="template.css">
    </head>
    <body>
		<jsp:include page="template.jsp" />

				<div class="register">
				<h3>Register as Family Member</h3>
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
			<form action="register.do" method="POST">
					<table>
			            <tr>
							<td><label for="rmail">E-mail :</label></td>
							<td><input type="text" name = "email" size = "28" value="${form.email}"/></td>
						</tr>
						
						<tr>
							<td><label for="password">Password : </label></td>
							<td><input type="password" name = "password" size = "28" value=""/></td>
						</tr>
						
						<tr>
							<td><label for=confirmPassword>Confirmation Password :</label></td>
							<td><input type="password" name = "confirmPassword" size = "28" value=""/></td>
						</tr>
						
						<tr>
							<td><label for="first">First Name : </label></td>
							<td><input type="text" name = "firstName" size = "28" value="${form.firstName}"/></td>
						</tr>
						
						<tr>
							<td><label for="last">Last Name : </label></td>
							<td><input type="text" name = "lastName" size = "28" value="${form.lastName}"/></td>
						</tr>						
					</table>
					<br>
						<div class = "button">  
						   	<input type="submit" name="button" style="width:90px;height:25px;" value="Submit" />
						    <input type="submit" name="button" style="width:90px;height:25px;" value="Register" />
						    <input type="submit" name="button" style="width:90px;height:25px;" value="Login" />
						</div>	
						</form>	
				</div>				 
		</body>
	</html>