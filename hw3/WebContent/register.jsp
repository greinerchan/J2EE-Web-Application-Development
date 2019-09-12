<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8"/>
        <title>Register</title>
    		<link rel="stylesheet" href="template.css">
    </head>
    <body>
		<jsp:include page="template2.html" />
			<div class="register">
				<h3>Register as Family Member</h3>
					<table>
			            <tr>
							<td><label for="rmail">E-mail :</label></td>
							<td><input type="text" id = "mail" size = "28" value=""/></td>
						</tr>
						
						<tr>
							<td><label for="rkey">Password : </label></td>
							<td><input type="text" id = "key" size = "28" value=""/></td>
						</tr>
						
						<tr>
							<td><label for="rkey2">Confirmation Password :</label></td>
							<td><input type="text" id = "rkey2" size = "28" value=""/></td>
						</tr>
						
						<tr>
							<td><label for="first">First Name : </label></td>
							<td><input type="text" id = "first" size = "28" value=""/></td>
						</tr>
						
						<tr>
							<td><label for="last">Last Name : </label></td>
							<td><input type="text" id = "last" size = "28" value=""/></td>
						</tr>						
					</table>
					
						  <br><br>
						  <div class = "button">  
							<a href="home.jsp"><input type="button" name="Submit" style="width:90px;height:25px;" value="Submit" /></a>
							<a href="register.jsp"><input type="button" name="Register" style="width:90px;height:25px;" value="Register " /></a>
							<a href="login.jsp"><input type="button" name="Login" style="width:90px;height:25px;"value="Login" /></a></div>		
				</div>
		</body>
	</html>