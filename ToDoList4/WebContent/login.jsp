<%@page import="java.util.List"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8"/>
        <title>ToDoList4 -- Login Page</title>
    </head>
    
	<body>
	
		<h2>ToDoList4 Login</h2>
<%
		List<String> errors = (List<String>) request.getAttribute("errors");
		if (errors != null) {
			for (String error : errors) {
%>		
				<h3 style="color:red"> <%= error %> </h3>
<%
			}
		}
%>	
		<form action="Login" method="POST">
		    <table>
		        <tr>
		            <td style="font-size: x-large">User Name:</td>
		            <td>
		                <input type="text" name="userName" value="${form.userName}" />
		            </td>
		        </tr>
		        <tr>
		            <td style="font-size: x-large">Password:</td>
		            <td><input type="password" name="password" /></td>
		        </tr>
		        <tr>
		        	<td> </td>
		            <td>
		                <input type="submit" name="button" value="Login" />
		            </td>
		        </tr>
			</table>
		</form>
		
		<p>
			Click here to <a href="Register">Register</a>.
		</p>
	</body>
</html>