<%@page import="java.util.List"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8"/>
        <title>ToDoList5 -- Login Page</title>
    </head>
    
	<body>
	
		<h2>ToDoList5 Login</h2>
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
		<form action="login.do" method="POST">
		    <table>
		        <tr>
		            <td style="font-size: x-large">User Name:</td>
		            <td>
		                <input type="text" name="userName" value="${form.userName}" autofocus />
		            </td>
		        </tr>
		        <tr>
		            <td style="font-size: x-large">Password:</td>
		            <td><input type="password" name="password" /></td>
		        </tr>
		        <tr>
		            <td colspan="2" style="text-align: center;">
		                <input type="submit" name="button" value="Login" />
		                <input type="submit" name="button" value="Register" />
		            </td>
		            
		        </tr>
			</table>
		</form>
	</body>
</html>