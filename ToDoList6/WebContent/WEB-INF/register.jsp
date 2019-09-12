<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8"/>
        <title>ToDoList6 -- Register Page</title>
    </head>
    
	<body onload="document.getElementById('firstName').focus()">
	
		<h2>ToDoList6 Register</h2>
		
		<c:forEach var="error" items="${form.formErrors}">
			<h3 style="color:red"> ${error} </h3>
		</c:forEach>
	
		<form action="register.do" method="POST">
	        <c:forEach var="field" items="${form.hiddenFields}">
	            <input type="${field.type}" name="${field.name}" value="${field.value}"/>
            </c:forEach>
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
		                <input type="submit" value="Submit">
		            </td>
		        </tr>
			</table>
		</form>

		<p>
			Click here to <a href="login.do">Login</a>.
		</p>
	</body>
</html>