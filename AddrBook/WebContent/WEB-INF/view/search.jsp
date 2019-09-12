<!doctype html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
	<head>
		<meta charset="utf-8">
		<title>AddrBook</title>
		<link href="search-form.css" rel="stylesheet" type="text/css">
		<script type="text/javascript" src="search-suggest.js"> </script>
	</head>

	<body onLoad="document.getElementById('last').focus()">
		<jsp:include page="search-form.jsp"/>
		<jsp:include page="errors-and-messages.jsp"/>
	</body>
</html>
