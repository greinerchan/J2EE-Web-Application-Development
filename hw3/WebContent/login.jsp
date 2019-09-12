<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8"/>
        <title>Login</title>
    		<link rel="stylesheet" href="template.css">
    </head>
    <body>
		<jsp:include page="template2.html" />
			<div class="login">
				<h3>Log In Family Union</h3>
					<div class="main">
					<table>
			            <tr>
							<td><label for="mail">E-mail :</label></td>
							<td><input type="text" id = "mail" size = "28" value=""/></td>
						</tr>
						
						<tr>
							<td><label for="key">Password : </label></td>
							<td><input type="text" id = "key" size = "28" value=""/></td>
						</tr>
						</table>
						  <br><br>
						  <div class = "button">	  
							<a href="home.jsp"><input type="button" name="Submit" style="width:90px;height:25px;" value="Submit" /></a>
							<a href="register.jsp"><input type="button" name="Register" style="width:90px;height:25px;" value="Register "/></a>
							<a href="login.jsp"><input type="button" name="Login" style="width:90px;height:25px;"value="Login" /></a></div>	
					</div>
			</div>
	</body>
</html>