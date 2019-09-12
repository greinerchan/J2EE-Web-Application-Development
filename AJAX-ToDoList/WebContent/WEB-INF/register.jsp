<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8"/>
        <title>AJAX ToDoList -- Register Page</title>
    </head>
    
	<body onload="document.getElementById('userName').focus()">
	
		<h2>AJAX ToDoList Register</h2>
		
		<c:forEach var="error" items="${form.formErrors}">
			<h3 style="color:red"> ${error} </h3>
		</c:forEach>
	
		<form action="register.do" method="POST">
		    <table>
		        <c:forEach var="field" items="${form.visibleFields}">
        		        <tr>
        		            <td style="font-size: x-large">
                            <label>${field.label}</label>
                        </td>
        		            <td>
        		                <input id="${field.name}" type="${field.type}" name="${field.name}" value="${field.value}"/>
        		            </td>
                        <td style="color:red">
                            ${field.error}
                        </td>
        		        </tr>
                </c:forEach>
		        <tr>
		            <td colspan="2" align="center">
		                <input type="submit" name="action" value="Register" />
		            </td>
		        </tr>
			</table>
		</form>

        <p>
            Click here to <a href="login.do">Login</a>.
        </p>
	</body>
</html>