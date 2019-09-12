<!doctype html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="utf-8">
    <title>Form Error</title>
</head>
<body>
	<h1>Error processing the form you submitted</h1>
        
    <c:forEach var="error" items="${form.formErrors}">
        <h3 style="color:red"> ${error} </h3>
    </c:forEach>
</body>
</html>
