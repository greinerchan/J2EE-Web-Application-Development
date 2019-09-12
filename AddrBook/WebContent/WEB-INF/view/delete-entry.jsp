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

		<form action="delete-entry.do" method="post">
			<h2>
				Delete entry for "${entry.lastName}", "${entry.firstNames}"?
				<input type="hidden" name="id" value="${entry.id}" />
				<input type="submit" name="button" value="Yes" />
				<input type="submit" name="button" value="No"  />
			</h2>
		</form>
		
	</body>
</html>
