<!doctype html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
	<head>
        <meta charset="utf-8">
		<title>AddrBook</title>
		<link href="search-form.css" rel="stylesheet" type="text/css">
		<script type="text/javascript" src="search-suggest.js"> </script>
	</head>

	
	<body onLoad="document.getElementById('userName').focus()">

		<jsp:include page="search-form.jsp"/>

        <c:forEach var="error" items="${form.formErrors}">
            <h3 style="color:red"> ${error} </h3>
        </c:forEach>
    
		<form action="delete-user.do" method="post">
			<h2>
				Delete user ${form.userName}?
				<input type="hidden" name="userName" value="${form.userName}" />
				<input type="submit" name="button" value="Yes" />
				<input type="submit" name="button" value="No"  />
			</h2>
		</form>
		
	</body>
</html>
