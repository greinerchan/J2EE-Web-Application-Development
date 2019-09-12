<%@page import="java.util.List"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8"/>
        <title>ToDoList3 -- Register Page</title>
    </head>
    
	<body>
	
		<h2>ToDoList3 Register</h2>
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
		<form action="Register" method="POST">
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
		                <input type="submit" name="button" value="Register" />
		            </td>
		        </tr>
			</table>
		</form>

		<p>
			Click here to <a href="Login">Login</a>.
		</p>
	</body>
</html>